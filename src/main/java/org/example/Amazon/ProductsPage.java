package org.example.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage {
    private final WebDriver driver;
    private ProductItem productItem;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By pageId = By.xpath("//span[@class='a-color-state a-text-bold']");
    private final By firstItem = By.xpath("//div[@data-index='1'][1]//img[@class='s-image']");
    private final By secondItem = By.xpath("//span[text()='Effective Java']");
    private final By titlesList = By.xpath("//div[@data-component-type='s-search-result']//h2");
    private final By authorsList = By.xpath("//div[@class='a-row']//span[contains(text(), 'by')]/following::*[1]");
    final String booksImage = "//div[@data-index='%d'][1]//img[@class='s-image']";

    public String checkPageId() {
        return driver.findElement(pageId).getText();
    }

    public ProductItem getItemPage(String path) {
        By xPath = By.xpath(path);
        driver.findElement(xPath).click();
        return new ProductItem(driver);
    }

    public List<String> getListOfTitles() {
        List<WebElement> webElementsList = driver.findElements(titlesList);
        List<String> titlesList = new ArrayList<>();
        for (WebElement element : webElementsList) {
            titlesList.add(element.getText());
        }
        return titlesList;
    }

    public List<String> getListOfAuthors() {
        List<WebElement> webElementsList = driver.findElements(authorsList);
        List<String> authorsList = new ArrayList<>();
        for (WebElement element : webElementsList) {
            authorsList.add(element.getText());
        }
        return authorsList;
    }

    public List<Book> getListOfBooks() {
        List<Book> listOfBooks = new ArrayList<>();
        ProductPage productPage = new ProductPage(driver);
        for (int i = 18; i < 19; i++) {
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
        List<Book> booksList = getListOfBooks();
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
