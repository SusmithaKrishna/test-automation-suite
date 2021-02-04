package stepdefs;

import com.google.inject.Inject;
import io.cucumber.datatable.DataTable;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pages.KiwiSaverPage;
import pages.RetirementCalcPage;
import pages.HomePage;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@ScenarioScoped
public class RetirementCalculatorSteps {

    @Inject
    World world;

    private RetirementCalcPage retirementCalcPage;
    private KiwiSaverPage kiwiSaverPage;
    private HomePage homePage;

    @Before
    public void setup() {
        try {
            retirementCalcPage = (RetirementCalcPage) world.pageManager.
                    getPageInstance("RetirementCalcPage");
            homePage = (HomePage) world.pageManager.
                    getPageInstance("HomePage");
            kiwiSaverPage = (KiwiSaverPage) world.pageManager.
                    getPageInstance("KiwiSaverPage");
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Given("^user navigates to KiwiSaver Retirement Calculator Page$")
    public void navigateToKiwiSaverCalculator() throws FileNotFoundException {
        String homePageUrl = world.testDataProvider.getConfigData("baseURL");
        homePage.navigateToHome(homePageUrl);
        homePage.clickOnKiwiSaver();
        homePage.clickOnKiwiSaverCalculator();
        kiwiSaverPage.clickKiwiSaverRetirementCalc();
        retirementCalcPage.validateRetirementCalcPage();
    }

    @Then("^user should see all the information icons beside each field$")
    public void validateInformationIcons() {
        retirementCalcPage.validateFieldIcons();
    }

    @When("^user clicks information icon besides ([^\"]*)$")
    public void clickOnInformationIcon(String fieldName) {
        retirementCalcPage.clickInformationIcon(fieldName);
    }

    @Then("^the message '([^\"]*)' is displayed below the ([^\"]*) field$")
    public void validateFieldMessage(String fieldMessage, String fieldName) {
        retirementCalcPage.validateFieldMessage(fieldName, fieldMessage);
    }

    @When("user enters below information in the calculator")
    public void enterUserDetails(DataTable userDetails) {
        List<Map<String, String>> userDetailsList = userDetails.asMaps(String.class, String.class);
        Map<String, String> userDetailsMap = userDetailsList.get(0);

        for (Map.Entry<String, String> entry : userDetailsMap.entrySet()) {
            String userDetail = entry.getKey();
            String userInformation = entry.getValue();
            retirementCalcPage.enterUserInformation(userDetail, userInformation);
        }
    }

    @Then("show user what their KiwiSaver Projected balance would be at retirement")
    public void showKiwiSaverProjectedBalance() {
        String kiwiSaverProjeciton = retirementCalcPage.clickViewYourKiwiSaverProjections();
        world.scenario.log("The KiwiSaver Projection for the given user details is " + kiwiSaverProjeciton.replaceAll("\n", ""));
    }
}
