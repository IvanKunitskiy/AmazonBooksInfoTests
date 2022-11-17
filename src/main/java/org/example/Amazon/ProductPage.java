package org.example.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private final WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By pageId = By.xpath("//span[@class='a-color-state a-text-bold']");
    private final By bookName = By.xpath("//span[text()='Head First Java: A Brain-Friendly Guide']");

    public String checkPageId() {
        return driver.findElement(pageId).getText();
    }

    public ProductItem selectItem() {
        driver.findElement(bookName).click();
        return new ProductItem(driver);
    }
}
