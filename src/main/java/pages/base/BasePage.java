package pages.base;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Logger;

public class BasePage {

    protected final Logger logger = Logger.getLogger(this.getClass().getName());
    protected WebDriverWait wait;
    protected WebDriver driver;

    // Default wait timers.
    protected final static int TEN_SECONDS = 10;
    protected final static int TWENTY_SECONDS = 20;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Used for validating visibility of different elements received via params.
     *
     * @param elements       {@code List<WebElement>} used for fetching each WebElement and validate visibility.
     * @param waitingSeconds {@code int} seconds to wait for each WebElement.
     * @return {@code true} if every WebElement is visible or {@code false} otherwise.
     */
    protected boolean validateSomeElementsAreVisible(List<WebElement> elements, int waitingSeconds) {
        for (WebElement element : elements) {
            if (!waitForElementVisibility(element, waitingSeconds)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Waits for visibility of a single {@link WebElement}.
     *
     * @param element        {@link WebElement} what is going to be searched.
     * @param waitingSeconds {@code int} seconds to wait for each WebElement.
     * @return
     */
    protected boolean waitForElementVisibility(WebElement element, int waitingSeconds) {
        initWaiter(waitingSeconds);
        try {
            this.wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException exception) {
            this.logger.warning(String.format("Error locating WebElement: %s", element));
        }
        return false;
    }

    /**
     * Waits for {@link WebElement} to be clickable.
     *
     * @param element        {@link WebElement} what is going to be searched.
     * @param waitingSeconds {@code int} seconds to wait for each WebElement.
     * @return
     */
    protected boolean waitForElementToBeClickable(WebElement element, int waitingSeconds) {
        initWaiter(waitingSeconds);
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException exception) {
            this.logger.warning(String.format("Error locating clickable WebElement: %s", element));
        }
        return false;
    }

    /**
     * Initializes {@link WebDriverWait} with a respective time.
     *
     * @param waitingSeconds {@code int} time used for init {@link WebDriverWait}.
     */
    protected void initWaiter(int waitingSeconds) {
        this.wait = new WebDriverWait(this.driver, waitingSeconds);
    }
}
