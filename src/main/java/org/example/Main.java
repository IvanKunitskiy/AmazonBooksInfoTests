package org.example;

import org.example.Amazon.MainPage;
import org.example.Amazon.ProductItem;
import org.example.Amazon.ProductsPage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String URL = "https://www.amazon.com";
    public static void main(String[] args) {
        final String bestSeller = "//div[@data-index='%d']//span[text()='Best Seller']";
        WebDriver driver1;
        Driver driver = new Driver();
        driver1 = driver.getDriver(URL);

        ProductsPage productPage;

        MainPage mainPage = new MainPage(driver1);
        mainPage.selectBooksOption();
        mainPage.typeIntoSearchField("Java");
        mainPage.clickSearchButton();
        productPage = new ProductsPage(driver1);

        List<Boolean> bestSellers = productPage.getListOfBestSellers();

        for (Boolean isBest : bestSellers) {
            System.out.println(isBest);
        }
    }
}
