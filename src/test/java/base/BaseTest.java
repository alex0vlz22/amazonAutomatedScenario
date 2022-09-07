package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class BaseTest {

    protected WebDriver driver;

    /**
     * Initialize {@link ChromeDriver}.
     */
    @BeforeClass
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    /**
     * Finishes {@link WebDriver} working.
     */
    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }

    /**
     * Provides all data needed for testing.
     *
     * @return new {@code Object[][]} with data.
     */
    @DataProvider(name = "dataProviderTest")
    public Object[][] dataProvider() {
        return new Object[][]{
                {"https://www.amazon.in/"}
        };
    }

}
