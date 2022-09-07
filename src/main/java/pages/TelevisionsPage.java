package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;
import utils.enums.SortByEnum;
import utils.exceptions.TestHasFailedException;

import java.util.ArrayList;
import java.util.List;

public class TelevisionsPage extends BasePage {

    private static final String SEARCH_DROPDOWN_SELECTED_OPTION_CSS_SELECTOR = "option[selected]";
    private static final String FIRST_SELECTED_CHECKBOX_TEXT_XPATH_SELECTOR = "//a[@class='a-link-normal s-navigation-item']/span[contains(text(), 'Samsung')]";
    private static final String SORT_BY_OPTION_SELECTED_CSS_SELECTOR = "span[class*='a-dropdown-prompt']";
    private static final String SECOND_MOST_EXPENSIVE_DISPLAYED_TELEVISION_XPATH_SELECTOR = "(//span[@class='a-price-whole'])[2]";

    @FindBy(id = "nav-search-dropdown-card")
    private WebElement searchDropdown;
    @FindBy(css = "span[class*='a-list-item'] a[href*='Samsung']")
    private WebElement samsungCheckbox;
    @FindBy(id = "s-result-sort-select")
    private WebElement sortByDropDown;
    @FindBy(xpath = "//a[@class='a-link-normal s-navigation-item']//input[@checked]")
    private WebElement samsungFirstSelectedCheckbox;

    protected TelevisionsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Validates if 'Televisions' appear as text on {@link #searchDropdown}, this happens once we're located in 'Televisions' section.
     *
     * @return {@code String} text we can find in {@link #searchDropdown}.
     * @throws TestHasFailedException {@link TestHasFailedException} if {@link #searchDropdown} doesn't appear. This due
     *                                to this way the test cannot be fully completed.
     */
    public String getSearchDropdownText() throws TestHasFailedException {
        super.logger.info("Trying to locate search dropdown.");
        if (super.waitForElementToBeClickable(this.searchDropdown, TEN_SECONDS)) {
            super.logger.info("The search dropdown was located.");
            WebElement dropdownSelectedOption = super.driver.findElement(By.cssSelector(SEARCH_DROPDOWN_SELECTED_OPTION_CSS_SELECTOR));
            return dropdownSelectedOption.getText();
        } else {
            throw new TestHasFailedException("Search dropdown didn't appear, validation uncompleted.");
        }
    }

    /**
     * Waits and click on {@link #samsungCheckbox}.
     *
     * @throws TestHasFailedException if {@link #samsungCheckbox} doesn't appear. This due to this way the test cannot
     *                                be fully completed.
     */
    public void clickOnSamsungCheckbox() throws TestHasFailedException {
        super.logger.info("Trying to locate Samsung checkbox.");
        if (super.waitForElementToBeClickable(this.samsungCheckbox, TWENTY_SECONDS)) {
            super.logger.info("Samsung checkbox was found.");
            super.logger.info("Clicking on Samsung checkbox.");
            this.samsungCheckbox.click();
            super.wait.until(ExpectedConditions.urlContains("Samsung"));
        } else {
            throw new TestHasFailedException("Samsung checkbox is not clickable, validation uncompleted.");
        }
    }

    /**
     * Validates if the same checkbox what is checked is that one next to the 'Samsung'.
     *
     * @return {@code String} the text next to the selected checkbox, it must be 'Samsung'.
     * @throws TestHasFailedException {@link TestHasFailedException} if Samsung checkbox is not selected or 'Samsung'
     *                                text next to this last one doesn't appear either. This due to this way the test
     *                                cannot be fully completed.
     */
    public String validateSamsungCheckboxIsSelected() throws TestHasFailedException {
        super.logger.info("Trying to locate Samsung selected checkbox.");
        if (super.waitForElementVisibility(samsungFirstSelectedCheckbox, TEN_SECONDS)) {
            super.logger.info("Samsung selected checkbox was found.");
            super.logger.info("Trying to locate text next to Samsung selected checkbox.");
            WebElement textSamsungSelectedCheckbox = super.driver.findElement(By.xpath(FIRST_SELECTED_CHECKBOX_TEXT_XPATH_SELECTOR));
            if (super.waitForElementVisibility(samsungFirstSelectedCheckbox, TEN_SECONDS)) {
                super.logger.info("Samsung selected checkbox text was found.");
                return textSamsungSelectedCheckbox.getText();
            } else {
                throw new TestHasFailedException("Samsung checked checkbox text was not located.");
            }
        } else {
            throw new TestHasFailedException("Samsung checked checkbox was not found.");
        }
    }

    /**
     * Waits for {@link #sortByDropDown} visibility and then selects the received option in this comb.
     *
     * @param sortByEnum {@link SortByEnum} used for selecting the option needed.
     * @throws TestHasFailedException {@link TestHasFailedException} if {@link #sortByDropDown} doesn't appear, this way
     *                                the test scenario cannot be completed.
     */
    public void selectSortBy(SortByEnum sortByEnum) throws TestHasFailedException {
        super.logger.info("Trying to find 'sort by' dropdown.");
        if (super.waitForElementVisibility(this.sortByDropDown, TEN_SECONDS)) {
            super.logger.info("'sort by' dropdown was found.");
            Select sortByComb = new Select(this.sortByDropDown);
            selectSortByOption(sortByEnum, sortByComb);
        } else {
            throw new TestHasFailedException("The 'sort by dropdown' wasn't found.");
        }
    }

    /**
     * Selects in given {@link Select} received by params a respective value received the same way.
     *
     * @param option {@link SortByEnum} used for searching the value to select.
     * @param select {@link Select} used for accessing the respective UI dropdown where we're going to select the value.
     */
    private void selectSortByOption(SortByEnum option, Select select) {
        super.logger.info(String.format("Selecting '%s' in dropdown.", option.getSortBy()));
        select.selectByValue(option.getOptionValue());
        super.wait.until(ExpectedConditions.urlContains("price-desc-rank"));
        super.logger.info("The option was selected.");
    }

    /**
     * Gets 'sort by' selected value text.
     *
     * @return {@code String} text from {@link #sortByDropDown} selected option.
     * @throws TestHasFailedException {@link TestHasFailedException} if {@link #sortByDropDown} is not found.
     */
    public String getSortBySelectedOption() throws TestHasFailedException {
        super.logger.info("Trying to find 'sort by' dropdown.");
        if (super.waitForElementVisibility(this.sortByDropDown, TEN_SECONDS)) {
            super.logger.info("'sort by' dropdown was found.");
            WebElement sortBySelectedOption = super.driver.findElement(By.cssSelector(SORT_BY_OPTION_SELECTED_CSS_SELECTOR));
            return sortBySelectedOption.getText();
        } else {
            throw new TestHasFailedException("The 'sort by dropdown' wasn't found.");
        }
    }

    /**
     * Clicks on highest second prince displayed and waits for another window to be available.
     *
     * @throws TestHasFailedException {@link TestHasFailedException} in case of second-highest price is not found.
     */
    public ProductPage clickOnHighestSecondPrice() throws TestHasFailedException {
        super.logger.info("Trying to find second most expensive television displayed.");
        WebElement textSamsungSelectedCheckbox = super.driver.findElement(By.xpath(SECOND_MOST_EXPENSIVE_DISPLAYED_TELEVISION_XPATH_SELECTOR));
        if (super.waitForElementToBeClickable(textSamsungSelectedCheckbox, TWENTY_SECONDS)) {
            super.logger.info("Second most expensive television was found.");
            textSamsungSelectedCheckbox.click();
            super.wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            return new ProductPage(super.driver);
        } else {
            throw new TestHasFailedException("The second highest price wasn't found.");
        }
    }

    /**
     * Validates if {@link WebDriver} detects two windows working in-test.
     *
     * @return {@code true} if there are two windows working, (which should be), or {@code false} otherwise.
     */
    public boolean validateThereAreTwoWindows() {
        if (super.driver.getWindowHandles().size() == 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Switches to second window.
     */
    public void switchToSecondWindow() {
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        super.driver.switchTo().window(windows.get(1));
    }

}
