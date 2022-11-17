package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Driver {
    public WebDriver getDriver(String url) {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "\\drivers\\chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }
}
