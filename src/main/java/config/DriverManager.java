package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.logging.Logger;

public class DriverManager {

    private final String browser;

    private WebDriver driver;

    static Logger logger = Logger.getLogger(DriverManager.class.getName());


    /**
     * It takes browser and generateGrid as an arguments that let's you decides that you want to run
     * for which browser and with Grid or not.
     *
     * @param browser The browser to be set to chrome/IE/Firefox
     */
    public DriverManager(String browser) {
        this.browser = browser.toLowerCase();
    }

    /**
     * Based on the generateGrid flag it invokes the
     * selenium grid driver instance or it invokes the standalone driver instance.
     *
     * @return the driver instance
     */
    public WebDriver getDriver() {
        this.driver = createDriver();
        return driver;

    }


    /**
     * @return a driver instance
     * Create an instance for the Chrome driver
     */
    private WebDriver createDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");

            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            capabilities.setCapability("applicationCacheEnabled", false);

            ChromeDriverService chromeDriverService = ChromeDriverService.createDefaultService();
            chromeDriverService.start();

            driver = new RemoteWebDriver(chromeDriverService.getUrl(), capabilities);
            driver.manage().deleteAllCookies();
            logger.info(browser.toUpperCase() + " browser: is started");
        } catch (IOException e) {
            e.getMessage();
        }

        assert driver != null;
        return driver;
    }

}
