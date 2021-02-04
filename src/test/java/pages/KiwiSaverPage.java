package pages;

import config.PageEngine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KiwiSaverPage extends PageEngine {

    @FindBy(xpath = "//a[@class='sw-sidenav-item-link link']//span[contains(text(), 'KiwiSaver Retirement Calculator')]")
    private WebElement retirementCalcLnk;

    public KiwiSaverPage(WebDriver driver) {
        super(driver);
    }

    public void clickKiwiSaverRetirementCalc() {
        waitForVisibilityOfElement(3, retirementCalcLnk).click();
    }

}
