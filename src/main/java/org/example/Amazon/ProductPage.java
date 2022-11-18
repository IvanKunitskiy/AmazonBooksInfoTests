package org.example.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private final WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By pageId = By.xpath("//span[@class='a-color-state a-text-bold']");
    private final By book1Name = By.xpath("//span[text()='Head First Java: A Brain-Friendly Guide']");
    private final By book2Name = By.xpath("//span[text()='Effective Java']");

    public String checkPageId() {
        return driver.findElement(pageId).getText();
    }

    public ProductItem selectFirstItem() {
        driver.findElement(book1Name).click();
        return new ProductItem(driver);
    }

    public ProductItem selectSecondItem() {
        driver.findElement(book2Name).click();
        return new ProductItem(driver);
    }
}
