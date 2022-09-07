package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class ProductPage extends BasePage {

    @FindBy(css = "h1[class*='a-size-base-plus a-text-bold']")
    private WebElement aboutThisItemText;

    protected ProductPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Validates if {@link #aboutThisItemText} is visible.
     *
     * @return {@code true} if {@link #aboutThisItemText} is visible or {@code false} otherwise.
     */
    public boolean validateAboutThisItemIsVisible() {
        super.logger.info("Trying to locate 'about this item' section.");
        if (super.waitForElementVisibility(this.aboutThisItemText, TEN_SECONDS)) {
            super.logger.info("'about this item' section was found.");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Prints using {@link java.util.logging.Logger} text found in {@link #aboutThisItemText}.
     *
     * @return {@link #aboutThisItemText} text.
     */
    public void printAboutThisItemText() {
        super.logger.info(this.aboutThisItemText.getText());
    }

}
