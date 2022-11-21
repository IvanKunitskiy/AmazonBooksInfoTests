package org.example.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    private final WebDriver driver;
    private ProductItem productItem;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By pageId = By.xpath("//span[@class='a-color-state a-text-bold']");
    private final By firstItem = By.xpath("//div[@data-index='1'][1]//img[@class='s-image']");
    private final By titlesList = By.xpath("//div[@data-component-type='s-search-result']//h2");
    private final By authorsList = By.xpath("//div[@class='a-row']//span[contains(text(), 'by')]/following::*[1]");
    private final String bestSellerLabel = "//div[@data-index='%d']//span[text()='Best Seller']";
    private final String booksImage = "//div[@data-index='%d'][1]//img[@class='s-image']";

    public String checkPageId() {
        return driver.findElement(pageId).getText();
    }

    public ProductItem getItemPage(String path) {
        driver.findElement(By.xpath(path)).click();
        return new ProductItem(driver);
    }

    private List<String> getListOfTitles() {
        List<WebElement> webElementsList = driver.findElements(titlesList);
        List<String> titlesList = new ArrayList<>();
        for (WebElement element : webElementsList) {
            titlesList.add(element.getText());
        }
        return titlesList;
    }

    private List<String> getListOfAuthors() {
        List<WebElement> webElementsList = driver.findElements(authorsList);
        List<String> authorsList = new ArrayList<>();
        for (WebElement element : webElementsList) {
            authorsList.add(element.getText());
        }
        return authorsList;
    }

    public List<Boolean> getListOfBestSellers() {
        List<Boolean> listOfBestSellers = new ArrayList<>();
        for (int i = 1; i < 19; i++) {
            if (i != 4 && i != 11) {
                String label = String.format(bestSellerLabel, i);
                List<WebElement> webElementsList = driver.findElements(By.xpath(label));
                if (webElementsList.size() == 0) {
                    listOfBestSellers.add(false);
                } else {
                    listOfBestSellers.add(true);
                }
            }
        }
      return listOfBestSellers;
    }

    private List<Book> getListOfBooksFromProductsPage() {
        List<Book> booksList = new ArrayList<>();
        List<String> titles = getListOfTitles();
        List<String> authors = getListOfAuthors();
        List<Boolean> bestSellers = getListOfBestSellers();
        for (int i = 0; i < titles.size(); i++) {
            booksList.add(new Book(titles.get(i), authors.get(i), bestSellers.get(i)));
        }
        return booksList;
    }

    public boolean compareBooksFromSearchPage(Book book) {
        List<Book> booksList = getListOfBooksFromProductsPage();
//        String testBook = book.getTitle()
//                + " "
//                + book.getAuthor()
//                + " "
//                + book.getBestSeller();
        for (Book bookItem : booksList) {
            String item = bookItem.getTitle()
                    + " "
                    + bookItem.getAuthor()
                    + " "
                    + bookItem.getBestSeller();
            System.out.println("Test items: " + bookItem.getTitle());
            System.out.println("Test book: " + book.getTitle());
            if (bookItem.getAuthor().equals(book.getTitle())) return true;
        }
        return false;
    }

    private List<Book> getListOfBooksFromProductItem() {
        List<Book> listOfBooks = new ArrayList<>();
        ProductsPage productPage = new ProductsPage(driver);
        for (int i = 1; i < 19; i++) {
            if (i != 4 && i != 11) {
                productPage.getItemPage(String.format(booksImage, i));
                productItem = new ProductItem(driver);
                listOfBooks.add(productItem.getItemInfo());
                productItem.goBack();
            }
        }
        return listOfBooks;
    }

    public boolean compareBooks(Book book) {
        List<Book> booksList = getListOfBooksFromProductItem();
        String testBook = book.getTitle()
                + " "
                + book.getAuthor()
                + " "
                + book.getPrice()
                + " "
                + book.getBestSeller();

        for (Book bookItem : booksList) {
            String item = bookItem.getTitle()
                    + " "
                    + bookItem.getAuthor()
                    + " "
                    + bookItem.getPrice()
                    + " "
                    + bookItem.getBestSeller();
            System.out.println(item);
            if (testBook.contains(item)) return true;
        }
        return false;
    }

    public ProductItem selectFirstItem() {
        driver.findElement(firstItem).click();
        return new ProductItem(driver);
    }
}
