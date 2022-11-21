package org.example.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductItem {
    private final WebDriver driver;

    public ProductItem(WebDriver driver) {
        this.driver = driver;
    }
    private final By itemTitle = By.xpath("//span[@id='productTitle']");
    private final By authors = By.xpath("//span[@class='author notFaded']");
    private final By kindlePrice = By.xpath("//span[@class='a-size-base a-color-secondary'][1]");
    private final By paperbackPrice = By.xpath("//span[@id='a-autoid-4']//span[@class='a-color-base']");
    private final By isBestseller = By.xpath("//i[contains(text(),'#1 Best Seller')]");
    private final By amazonLogoLink = By.xpath("//a[@aria-label='Amazon']");

    public String getTitle() {
        return driver.findElement(itemTitle).getText();
    }

    public String getAuthor() {
        String author = "";
        List<WebElement> authorsList = driver.findElements(authors);
        for (int i = 0; i < authorsList.size(); i++) {
            String s = authorsList.get(i).getText().trim();
            if (i == authorsList.size() - 1) {
                author += s.substring(0, s.length() - 9);
            } else {
                author += s.substring(0, s.length() - 10) + (", ");
            }
        }
        return author;
    }

    public double getPaperbackPrice() {
        List<WebElement> elements = driver.findElements(paperbackPrice);

        if (elements.size() == 0) {
            return getKindlePrice();
        }
        String price = driver.findElement(paperbackPrice).getText().replaceAll("[^\\d.]", "");
        if (price.length() > 7) {
            price = price.substring(0, 5);
        }
        return Double.parseDouble(price);
    }

    public double getKindlePrice() {
        List<WebElement> elements = driver.findElements(kindlePrice);

        if (elements.size() == 0) {
            return 0;
        }
        String price = driver.findElement(kindlePrice).getText().replaceAll("[^\\d.]", "");
        if (price.length() > 7) {
            price = price.substring(0, 5);
        }
        return Double.parseDouble(price);
    }

    public boolean getBestSellerInfo() {
        List<WebElement> elements = driver.findElements(isBestseller);
        boolean isBestSeller;
        if (elements.size() == 0) {
            isBestSeller = false;
        } else {
            isBestSeller = true;
        }
        return isBestSeller;
    }

    public Book getItemInfo() {
        String title = getTitle();
        String author = getAuthor();
        double price = getPaperbackPrice();
        boolean isBestSeller = getBestSellerInfo();
        return new Book(title, author, price, isBestSeller);
    }

    public MainPage goToMainPage() {
        driver.findElement(amazonLogoLink).click();
        return new MainPage(driver);
    }

    public ProductsPage goBack() {
        driver.navigate().back();
        return new ProductsPage(driver);
    }
}
