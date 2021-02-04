package pages;

import Utils.StringUtils;
import config.PageEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class RetirementCalcPage extends PageEngine {

    private StringUtils stringUtils = new StringUtils();

    @FindBy(css = "div h1")
    private WebElement pageHeading;

    @FindBy(css = "div[class='field-group-set'] label[class='ng-binding']")
    private List<WebElement> listOfCalcFields;

    @FindBy(css = "div[class='field-group-set'] i[class='icon']")
    private List<WebElement> listOfIcons;

    @FindBy(css = "iframe[src*='/calculator-widgets/kiwisaver-calculator/']")
    private WebElement calculatorIFrame;

    @FindBy(css = "div[help-id='CurrentAge'] input")
    private WebElement currentAgeInput;

    @FindBy(css = "div[help-id='EmploymentStatus'] div[class*='control-well']")
    private WebElement employmentStatusDropdownBtn;

    @FindBy(css = "div[help-id='EmploymentStatus'] li[class*='option-item'] span")
    private List<WebElement> employmentStatusOptionsLst;

    @FindBy(css = "div[help-id='EmploymentStatus'] div[ng-bind-html='selectedContent'] span[class='ng-scope']")
    private WebElement selectedEmploymentStatus;

    @FindBy(css = "div[help-id='AnnualIncome'] input")
    private WebElement enterAnnualIncome;

    @FindBy(css = "div[help-id='KiwiSaverMemberContribution'] input")
    private List<WebElement> contributionList;

    @FindBy(css = "div[help-id='KiwiSaverMemberContribution'] div[class*='selected']")
    private WebElement selectedContribution;

    @FindBy(css = "div[help-id='KiwiSaverBalance'] input")
    private WebElement currentKiwiSaverBalance;

    @FindBy(css = "div[help-id='VoluntaryContributions'] input")
    private WebElement voluntrayConribution;

    @FindBy(css = "div[help-id='RiskProfile'] span[class=input-label] span")
    private List<WebElement> riskProfilesList;

    @FindBy(css = "div[help-id='RiskProfile'] div[class*='selected'] span[class='input-label'] span")
    private WebElement selectedRiskProfile;

    @FindBy(css = "div[help-id='SavingsGoal'] input")
    private WebElement savingsGoal;

    @FindBy(css = "button[ng-click='ctrl.showResultsPanel()']")
    private WebElement kiwiSaverProjectionsBtn;

    @FindBy(css = "span[class*='result-value']")
    private WebElement kiwiSaverProjectionsResult;

    @FindBy(css = "div[help-id='VoluntaryContributions'] div[ng-click='toggle()']")
    private WebElement frequencyDropdownBtn;

    @FindBy(css="div[help-id='VoluntaryContributions'] div[class='label'] span[class*='ng-binding']")
    private List<WebElement> frequencyList;

    public RetirementCalcPage(WebDriver driver) {
        super(driver);
    }

    public void validateRetirementCalcPage() {
        assertEquals("KiwiSaver Retirement Calculator", waitForVisibilityOfElement(2, pageHeading).getText());
        driver.switchTo().frame(calculatorIFrame);
    }

    public void validateFieldIcons() {
        int fieldsSize = listOfCalcFields.size();
        int iconsSize = listOfIcons.size();
        assertEquals(fieldsSize, iconsSize);
    }

    public void clickInformationIcon(String fieldName) {
        String helpId = stringUtils.toCamelCase(fieldName);
        String iconIdCssSelector = "//div[@help-id='" + helpId + "']//button";
        WebElement iconButton = driver.findElement(By.xpath(iconIdCssSelector));
        waitForVisibilityOfElement(2, iconButton).click();
    }

    public void validateFieldMessage(String fieldName, String fieldMessage) {
        String helpId = stringUtils.toCamelCase(fieldName);
        String fieldMessageCssSelector = "div[help-id='" + helpId + "'] p";
        String actualFieldMessage = driver.findElement(By.cssSelector(fieldMessageCssSelector)).getText();
        assertEquals(fieldMessage, actualFieldMessage);
    }

    public void enterUserInformation(String userDetail, String userInformation) {
        String employmentStatusSelected = "";
        switch (userDetail) {
            case "CurrentAge": {
                currentAgeInput.sendKeys(userInformation);
                break;
            }
            case "EmploymentStatus": {
                employmentStatusDropdownBtn.click();
                for (WebElement employmentStatus : employmentStatusOptionsLst) {
                    if (employmentStatus.getText().equals(userInformation)) {
                        employmentStatus.click();
                        break;
                    }
                }
                break;
            }
            case "AnnualIncome": {
                employmentStatusSelected = waitForVisibilityOfElement(2,
                        selectedEmploymentStatus).getText();
                if (employmentStatusSelected.equals("Employed")) {
                    enterAnnualIncome.sendKeys(userInformation);
                }
                break;
            }
            case "KiwiSaverMemberContribution": {
                employmentStatusSelected = waitForVisibilityOfElement(2,
                        selectedEmploymentStatus).getText();
                if (employmentStatusSelected.equals("Employed")) {
                    for (WebElement contribution : contributionList) {
                        if (contribution.getAttribute("value").
                                equals(userInformation.replaceAll("%", ""))) {
                            contribution.click();
                            break;
                        }
                    }
                    assertTrue(selectedContribution.getAttribute("value").
                            equals(userInformation.replaceAll("%", "")));
                }
                break;
            }
            case "KiwiSaverBalance": {
                if (userInformation != null) {
                    currentKiwiSaverBalance.sendKeys(userInformation);
                }
                break;
            }
            case "VoluntaryContributions": {
                if (userInformation != null) {
                    voluntrayConribution.sendKeys(userInformation);
                }
                break;
            }
            case "Frequency": {
                frequencyDropdownBtn.click();
                for (WebElement frequency : frequencyList) {
                    if (frequency.getText().equals(userInformation)) {
                        frequency.click();
                        break;
                    }
                }
                break;
            }
            case "RiskProfile": {
                for (WebElement riskProfile : riskProfilesList) {
                    if (riskProfile.getText().equals(userInformation)) {
                        riskProfile.click();
                        break;
                    }
                }
                assertTrue(selectedRiskProfile.getText().equals(userInformation));
                break;
            }
            case "SavingsGoal": {
                if (userInformation != null) {
                    savingsGoal.sendKeys(userInformation);
                }
                break;
            }
            default:
        }
    }

    public String clickViewYourKiwiSaverProjections() {
        kiwiSaverProjectionsBtn.click();
        return waitForVisibilityOfElement(2, kiwiSaverProjectionsResult).getText();
    }
}
