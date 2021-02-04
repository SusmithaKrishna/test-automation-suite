package config;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;


public class PageEngine {

    public static final int TIMEOUT = 5;
    public static final int POLLING = 100;

    public WebDriver driver;
    public WebDriverWait wait;

    /**
     * Similar to the other "initElements" methods, but takes an {@link ElementLocatorFactory} which
     * is used for providing the mechanism for finding elements. If the ElementLocatorFactory returns
     * null then the field won't be decorated.
     *
     * @param driver The driver to use PageFactory
     */
    public PageEngine(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
        this.driver.manage().deleteAllCookies();
        this.driver.manage().window().maximize();
    }

    /**
     * An expectation for checking that an element, known to be present on the DOM of a page, is
     * visible. Visibility means that the element is not only displayed but also has a height and
     * width that is greater than 0.
     *
     * @param timeOutInSeconds The timeout in seconds when an expectation is called
     * @param webElement       the WebElement
     * @return the (same) WebElement once it is visible
     */
    public WebElement waitForVisibilityOfElement(long timeOutInSeconds, WebElement webElement) {
        new WebDriverWait(driver, timeOutInSeconds, 5).
                until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    /**
     * Returns true if element is displayed or else return false
     *
     * @param webElement to verify whether displayed or not
     * @throws NoSuchElementException If no matching option elements are found
     */
    public boolean isElementDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
