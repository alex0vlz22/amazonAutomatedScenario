import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.TelevisionsPage;
import utils.enums.SortByEnum;
import utils.exceptions.TestHasFailedException;

public class AmazonTest extends BaseTest {

    private HomePage homePage;
    private TelevisionsPage televisionsPage;
    private ProductPage productPage;


    @Test(dataProvider = "dataProviderTest", dataProviderClass = BaseTest.class, description = "Executes full scenario" +
            " based on automationTest.pdf test from Lean Teach.")
    public void testingAmazonFunctionality(String url) throws TestHasFailedException {
        openingUrl(url); // Step: 1
        clickingOnHamburgerMenu(); // Step: 2
        clickingOnTvAppliancesAndElectronicsOption(); // Step: 3
        clickingOnTelevisionsOption(); // Step: 4
        clickingOnSamsungBrand(); // Step: 5
        SortByHighToLow(); // Step: 6
        clickOnSecondHighestPrice(); // Step: 7
        switchToWindow(); // Step: 8
        aboutThisItem(); // Step: 9
    }

    /**
     * 1. Opens https://www.amazon.in/.
     *
     * @param url {@code String} used for opening the respective url.
     */
    private void openingUrl(String url) {
        super.driver.get(url);
        super.driver.manage().window().maximize();
        this.homePage = new HomePage(super.driver);
        Assert.assertEquals(url, homePage.getUrl(), "The url was opened.");
    }

    /**
     * 2. Clicks on the hamburger menu in the top left corner.
     *
     * @throws TestHasFailedException {@link TestHasFailedException} if hamburger menu is not clickable. This due
     *                                to this way the test cannot be fully completed.
     */
    private void clickingOnHamburgerMenu() throws TestHasFailedException {
        this.homePage.clickOnHamburgerMenu();
        Assert.assertTrue(this.homePage.leftUserMenuIsVisible(), "Hamburger menu was clicked.");
    }

    /**
     * 3. Scrolls down and then click on the TV, Appliances, and Electronics link under the Shop by Department section.
     *
     * @throws TestHasFailedException {@link TestHasFailedException} if any of needed elements is not visible. This due
     *                                to this way the test cannot be fully completed.
     */
    private void clickingOnTvAppliancesAndElectronicsOption() throws TestHasFailedException {
        this.homePage.clickOnTvAppliancesAndElectronicsOption();
        Assert.assertTrue(this.homePage.validateTvAppliancesAndElectronicsMenuIsVisible(), "TV, Appliances, Electronics option was clicked.");
    }

    /**
     * 4. Clicks on Televisions under the Tv, Audio & Cameras subsection.
     *
     * @throws TestHasFailedException {@link TestHasFailedException} if search dropdown doesn't appear. This due to
     *                                this way the test cannot be fully completed.
     */
    private void clickingOnTelevisionsOption() throws TestHasFailedException {
        this.televisionsPage = this.homePage.clickOnTelevisionsOption();
        Assert.assertEquals("Televisions", this.televisionsPage.getSearchDropdownText(), "'Televisions' option was clicked.");
    }

    /**
     * 5. Scroll down and filter the results by Brand ‘Samsung’.
     *
     * @throws TestHasFailedException {@link TestHasFailedException} if Samsung checkbox is not selected or 'Samsung'
     *                                text next to this last one doesn't appear either. This due to this way the test
     *                                cannot be fully completed.
     */
    private void clickingOnSamsungBrand() throws TestHasFailedException {
        this.televisionsPage.clickOnSamsungCheckbox();
        Assert.assertEquals("Samsung", this.televisionsPage.validateSamsungCheckboxIsSelected(), "'Televisions' option is selected.");
    }

    /**
     * 6. Sort the Samsung results with Price High to Low.
     *
     * @throws TestHasFailedException {@link TestHasFailedException} if 'sort by' menu doesn't appear. This due
     *                                to this way the test cannot be fully completed.
     */
    private void SortByHighToLow() throws TestHasFailedException {
        this.televisionsPage.selectSortBy(SortByEnum.PRICE_HIGH_TO_LOW);
        Assert.assertEquals("Price: High to Low", this.televisionsPage.getSortBySelectedOption(), "Results are ordered by 'High to Low'.");
    }

    /**
     * 7. Clicks on highest second prince displayed and waits for another window to be available.
     *
     * @throws TestHasFailedException {@link TestHasFailedException} in case of second-highest price is not found.
     */
    private void clickOnSecondHighestPrice() throws TestHasFailedException {
        this.productPage = this.televisionsPage.clickOnHighestSecondPrice();
        Assert.assertTrue(this.televisionsPage.validateThereAreTwoWindows(), "Two windows are detected.");
    }

    /**
     * 8. Switches to second window.
     */
    private void switchToWindow() {
        this.televisionsPage.switchToSecondWindow();
    }

    /**
     * 9. Assert that the “About this item” section is present and log this section text to console/report.
     */
    private void aboutThisItem() {
        Assert.assertTrue(this.productPage.validateAboutThisItemIsVisible(), "'About this item' section is present.");
        this.productPage.printAboutThisItemText();
    }

}
