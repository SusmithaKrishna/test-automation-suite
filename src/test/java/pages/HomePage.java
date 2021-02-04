package pages;

import config.PageEngine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.AssertJUnit.assertTrue;

public class HomePage extends PageEngine {

    @FindBy(css = "a[id='logo']")
    private WebElement homePageLogo;

    @FindBy(css = "a[id='ubermenu-section-link-kiwisaver-ps']")
    private WebElement kiwiSaverLnk;

    @FindBy(css = "a[id='ubermenu-item-cta-kiwisaver-calculators-ps']")
    private WebElement kiwiSaverCalculatorsLnk;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHome(String homePageUrl) {
        driver.get(homePageUrl);
        assertTrue(isElementDisplayed(homePageLogo));
    }

    public void clickOnKiwiSaver(){
        waitForVisibilityOfElement(1, kiwiSaverLnk).click();
        assertTrue(isElementDisplayed(kiwiSaverCalculatorsLnk));
    }

    public void clickOnKiwiSaverCalculator() {
        kiwiSaverCalculatorsLnk.click();
    }
}
