package org.example.Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductItem {
    private WebDriver driver;

    public ProductItem(WebDriver driver) {
        this.driver = driver;
    }
    private final By itemTitle = By.xpath("//span[@id='productTitle']");
    private final By authors = By.xpath("//span[@class='author notFaded']");
    private final By kindlePrice = By.xpath("//a[@id='a-autoid-3-announce']//span[text()='Kindle']/following-sibling::span");
    private final By paperbackPrice = By.xpath("//span[@class='a-size-base a-color-price a-color-price']");
    private final By isBestseller = By.xpath("//i[contains(text(),'#1 Best Seller')]");

    public List<String> getItemInfo() {
        List<String> itemInfo = new ArrayList<>();
        itemInfo.add("Title: " + driver.findElement(itemTitle).getText());

        String author = "Author: ";
        List<WebElement> authorsList = driver.findElements(authors);
        for (int i = 0; i < authorsList.size(); i++) {
            String s = authorsList.get(i).getText().trim();
            if (i == authorsList.size() - 1) {
                author += s.substring(0, s.length() - 9);
            } else {
                author += s.substring(0, s.length() - 10) + ", ";
            }
        }
        itemInfo.add(author);

        itemInfo.add("Kindle Price: " + driver.findElement(kindlePrice).getText());

        itemInfo.add("Paperback Price: " + driver.findElement(paperbackPrice).getText());

        List<WebElement> elems = driver.findElements(isBestseller);
        if (elems.size() == 0) {
            itemInfo.add("Is a Best Seller: false");
        } else {
            itemInfo.add("Is a Best Seller: true");
        }
        return itemInfo;
    }
}
