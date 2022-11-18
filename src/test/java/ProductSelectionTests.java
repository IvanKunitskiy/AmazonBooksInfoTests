import org.example.Amazon.MainPage;
import org.example.Amazon.ProductItem;
import org.example.Amazon.ProductPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductSelectionTests {
    private WebDriver driver;
    private MainPage mainPage;
    private ProductPage productPage;
    private ProductItem productItem;
    private final static String URL = "https://www.amazon.com/";
    private final static String productName = "Java";

    @Before
    public void setDriver() {
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
        mainPage = new MainPage(driver);
    }

    @Test
    public void dropdownBoxValueTest() {
        mainPage.selectBooksOption();
        String value = mainPage.checkSearchDropdownBoxValue();
        Assert.assertEquals("Books", value);
    }

    @Test
    public void productSearchTest() {
        mainPage.selectBooksOption();
        mainPage.typeIntoSearchField(productName);
        mainPage.clickSearchButton();
        productPage = new ProductPage(driver);
        String pageIdentification = productPage.checkPageId();
        Assert.assertEquals("\"" + productName + "\"", pageIdentification);
    }

    @Test
    public void firstBookInfoTest() {
        List<String> testBookInfo = new ArrayList<>();
        testBookInfo.add("Title: Head First Java: A Brain-Friendly Guide");
        testBookInfo.add("Author: Kathy Sierra, Trisha Gee, Bert Bates");
        testBookInfo.add("Kindle Price: $43.22");
        testBookInfo.add("Paperback Price: $21.99");
        testBookInfo.add("Is a Best Seller: false");
        mainPage.selectBooksOption();
        mainPage.typeIntoSearchField(productName);
        mainPage.clickSearchButton();
        productPage = new ProductPage(driver);
        productPage.selectFirstItem();
        productItem = new ProductItem(driver);
        List <String> bookInfo = productItem.getItemInfo();
        Assert.assertEquals(testBookInfo, bookInfo);
    }

    @Test
    public void secondBookInfoTest() {
        List<String> testBookInfo = new ArrayList<>();
        testBookInfo.add("Title: Effective Java");
        testBookInfo.add("Author: Joshua Bloch");
        testBookInfo.add("Kindle Price: $29.69");
        testBookInfo.add("Paperback Price: $41.39");
        testBookInfo.add("Is a Best Seller: true");
        mainPage.selectBooksOption();
        mainPage.typeIntoSearchField(productName);
        mainPage.clickSearchButton();
        productPage = new ProductPage(driver);
        productPage.selectSecondItem();
        productItem = new ProductItem(driver);
        List <String> bookInfo = productItem.getItemInfo();
        Assert.assertEquals(testBookInfo, bookInfo);
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
