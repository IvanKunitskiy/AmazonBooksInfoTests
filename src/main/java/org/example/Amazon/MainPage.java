package org.example.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By booksOption = By.xpath("//select[@id='searchDropdownBox']//option[text()='Books']");
    private final By productSpan = By.xpath("//span[@id='nav-search-label-id']");
    private final By searchField = By.xpath("//input[@id='twotabsearchtextbox']");
    private final By submitButton = By.xpath("//input[@type='submit']");

    public MainPage selectBooksOption() {
        driver.findElement(booksOption).click();
        return this;
    }

    public String checkSearchDropdownBoxValue() {
        return driver.findElement(productSpan).getText();
    }

    public ProductsPage clickSearchButton() {
        driver.findElement(submitButton).submit();
        return new ProductsPage(driver);
    }

    public ProductsPage typeIntoSearchField(String input) {
        selectBooksOption();
        driver.findElement(searchField).sendKeys(input);
        clickSearchButton();
        return new ProductsPage(driver);
    }
}
