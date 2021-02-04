package stepdefs;

import com.google.inject.Inject;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriverException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

@ScenarioScoped
public class Hooks {

    @Inject
    World world;

    public Connection databaseConnection;
    public Statement statement;
    static Logger logger = Logger.getLogger(Hooks.class.getName());


    @Before(order = 1)
    public void beforeScenario(Scenario scenario) throws SQLException {
        world.scenario = scenario;
        String testRunStartMessage = "===============Scenario: " + world.scenario.getName() + " has started=========";
        logger.info(testRunStartMessage);
    }

    @After
    public void afterScenario() {
        try {
            String testRunEndMessage = "Scenario execution for " + world.scenario.getName()
                    + "has finished and it is" + world.scenario.getStatus();
            logger.info(testRunEndMessage);
        } catch (WebDriverException | NullPointerException e) {
            logger.severe(e.getMessage());
            world.driver.quit();
        } finally {
            if (world.driver != null) {
                world.driver.quit();
            }
        }
    }

}
