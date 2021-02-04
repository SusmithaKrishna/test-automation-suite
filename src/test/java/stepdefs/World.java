package stepdefs;

import com.google.inject.Inject;
import config.DriverManager;
import config.PageManager;
import config.TestDataProvider;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;


@ScenarioScoped
public class World {

    public TestDataProvider testDataProvider;
    public DriverManager driverManager;
    public PageManager pageManager;
    public WebDriver driver;
    public Scenario scenario;

    @Inject
    World(TestDataProvider testDataProvider) {
        String browserName = System.getProperty("browser");
        String headlessMode = System.getProperty("headless");

        if (browserName == null) {
            browserName = "chrome";
        }

        this.scenario = null;
        this.driverManager = new DriverManager(browserName);
        this.driver = this.driverManager.getDriver();
        this.pageManager = new PageManager(this.driver);
        this.testDataProvider = testDataProvider;
    }
}
