import org.example.Amazon.Book;
import org.example.Amazon.MainPage;
import org.example.Amazon.ProductItem;
import org.example.Amazon.ProductsPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class ProductSelectionTests {
    private WebDriver driver;
    private MainPage mainPage;
    private ProductsPage productPage;
    private ProductItem productItem;
    private final static String URL = "https://www.amazon.com/";
    private final static String productName = "Head First Java, 2nd Edition";

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
        productPage = new ProductsPage(driver);
        String pageIdentification = productPage.checkPageId();
        Assert.assertEquals("\"" + productName + "\"", pageIdentification);
    }

    @Test
    public void testItemTest() {
        Book testBook = new Book("Head First Java, 2nd Edition","Kathy Sierra, Bert Bates", 19.74, false);

        mainPage.selectBooksOption();
        mainPage.typeIntoSearchField(productName);
        mainPage.clickSearchButton();
        productPage = new ProductsPage(driver);
        productPage.selectFirstItem();
        productItem = new ProductItem(driver);

        Book book = productItem.getItemInfo();

        String infoTestBook = testBook.getTitle() + " " + testBook.getAuthor();
        String infoBook = book.getTitle() + " " + book.getAuthor();
        Assert.assertEquals("Objects are not equal!", infoTestBook, infoBook);
    }

    @Test
    public void compareTitlesFromSearch () {
        mainPage.selectBooksOption();
        mainPage.typeIntoSearchField(productName);
        mainPage.clickSearchButton();

        productPage = new ProductsPage(driver);
        productPage.selectFirstItem();

        productItem = new ProductItem(driver);
        Book testBook = new Book(productItem.getTitle(), productItem.getAuthor(), productItem.getBestSellerInfo());
        productItem.goToMainPage();

        mainPage.typeIntoSearchField("Java");
        mainPage.clickSearchButton();

        productPage = new ProductsPage(driver);
        boolean isBooksListContainsTestBook = productPage.compareBooksFromSearchPage(testBook);
        Assert.assertTrue(isBooksListContainsTestBook);
    }

    @Test
    public void compareTestBookWithListOfBooks() {
        mainPage.selectBooksOption();
        mainPage.typeIntoSearchField(productName);
        mainPage.clickSearchButton();

        productPage = new ProductsPage(driver);
        productPage.selectFirstItem();

        productItem = new ProductItem(driver);
        Book testBook = productItem.getItemInfo();
        productItem.goToMainPage();

        mainPage.typeIntoSearchField("Java");
        mainPage.clickSearchButton();

        productPage = new ProductsPage(driver);
        boolean isContainTestBook = productPage.compareBooks(testBook);
        Assert.assertTrue(isContainTestBook);
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
