package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;
import utils.exceptions.TestHasFailedException;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(id = "nav-hamburger-menu")
    private WebElement hamburgerMenu;
    @FindBy(id = "hmenu-canvas")
    private WebElement leftUserMenu;
    @FindBy(css = "a[data-menu-id=\"9\"]")
    private WebElement tvAppliancesAndElectronicsOption;
    @FindBy(xpath = "//div[contains(text(), \"tv, audio & cameras\")]")
    private WebElement tvAudioAndCamerasMenuTitle;
    @FindBy(xpath = "//div[contains(text(), \"appliances\")]")
    private WebElement appliancesMenuTitle;
    @FindBy(css = "a[href*='television']")
    private WebElement televisionsOption;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Fetches page url.
     *
     * @return {@code String} url from the current page one.
     */
    public String getUrl() {
        return super.driver.getCurrentUrl();
    }

    /**
     * Waits for {@link #hamburgerMenu} to be visible and then clicks on it.
     *
     * @throws TestHasFailedException {@link TestHasFailedException} if {@link #hamburgerMenu} is not clickable. This due
     *                                to this way the test cannot be fully completed.
     */
    public void clickOnHamburgerMenu() throws TestHasFailedException {
        super.logger.info("Trying to find hamburger menu.");
        if (super.waitForElementToBeClickable(this.hamburgerMenu, TEN_SECONDS)) {
            super.logger.info("Hamburger menu was found.");
            super.logger.info("Clicking on hamburger menu.");
            this.hamburgerMenu.click();
        } else {
            throw new TestHasFailedException("The test got broken trying to interact with hamburger menu.");
        }
    }

    /**
     * Waits for {@link #leftUserMenu} to be visible.
     *
     * @return {@code true} if {@link #leftUserMenu} is visible or {@code false} otherwise.
     * @throws TestHasFailedException {@link TestHasFailedException} if {@link #leftUserMenu} is not visible. This due
     *                                to this way the test cannot be fully completed.
     */
    public boolean leftUserMenuIsVisible() throws TestHasFailedException {
        super.logger.info("Trying to locate left user menu displayed once hamburger menu is clicked.");
        if (super.waitForElementVisibility(this.leftUserMenu, TEN_SECONDS)) {
            super.logger.info("Left user menu was found.");
            return true;
        } else {
            throw new TestHasFailedException("The test got broken, the user left menu didn't open after clicking on hamburger menu.");
        }
    }

    /**
     * Waits and click on {@link #tvAppliancesAndElectronicsOption}.
     *
     * @throws TestHasFailedException {@link TestHasFailedException} if {@link #tvAppliancesAndElectronicsOption} is not visible. This due
     *                                to this way the test cannot be fully completed.
     */
    public void clickOnTvAppliancesAndElectronicsOption() throws TestHasFailedException {
        super.logger.info("Trying to locate TV, Appliances, Electronics option displayed on user left menu.");
        if (super.waitForElementToBeClickable(this.tvAppliancesAndElectronicsOption, TEN_SECONDS)) {
            super.logger.info("TV, Appliances, Electronics option is ready to be clicked.");
            super.logger.info("Clicking on TV, Appliances, Electronics option.");
            this.tvAppliancesAndElectronicsOption.click();
        } else {
            throw new TestHasFailedException("The test got broken trying to click on TV, Appliances, Electronics option.");
        }
    }

    /**
     * Waits for {@link #tvAudioAndCamerasMenuTitle} and {@link #appliancesMenuTitle} to be visible.
     *
     * @return {@code true} if all elements are visible or {@code false} otherwise.
     * @throws TestHasFailedException {@link TestHasFailedException} if any of needed elements is
     *                                not visible. This due to this way the test cannot be fully completed.
     */
    public boolean validateTvAppliancesAndElectronicsMenuIsVisible() throws TestHasFailedException {
        List<WebElement> elementsToLocate = new ArrayList<>();
        elementsToLocate.add(this.tvAudioAndCamerasMenuTitle);
        elementsToLocate.add(this.appliancesMenuTitle);
        super.logger.info("Validating 'Tv, Audio & Cameras' and 'Appliances' titles are visible.");
        if (super.validateSomeElementsAreVisible(elementsToLocate, TEN_SECONDS)) {
            super.logger.info("'Tv, Audio & Cameras' and 'Appliances' titles were located.");
            return true;
        } else {
            throw new TestHasFailedException("'Tv, Audio & Cameras' and 'Appliances' titles weren't found.");
        }
    }

    /**
     * Waits and click on {@link #televisionsOption}.
     *
     * @return new instance for {@link TelevisionsPage}.
     * @throws TestHasFailedException {@link TestHasFailedException} if {@link #televisionsOption} is not clickable. This due
     *                                to this way the test cannot be fully completed.
     */
    public TelevisionsPage clickOnTelevisionsOption() throws TestHasFailedException {
        super.logger.info("Waiting for 'Televisions' option to be clickable.");
        if (super.waitForElementToBeClickable(this.televisionsOption, TEN_SECONDS)) {
            super.logger.info("'Televisions' option is ready.");
            super.logger.info("Clicking on 'Televisions'.");
            this.televisionsOption.click();
            return new TelevisionsPage(super.driver);
        } else {
            throw new TestHasFailedException("'Televisions' option is not available.");
        }
    }

}
