package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import utils.Constants;

import java.time.Duration;

public class BaseConfig {

    private WebDriver driver;
    private String baseURL = Constants.baseURL;

    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void openURL(final String url)
    {
        getWebDriver().get(url);
    }

    public void exit()
    {
        driver.quit();
    }

    /**
     * setup browser, driver and launch url
     * @param browser
     * @param url
     */
    public void browserSettings(final String browser, final String url) {
        try {

            if ("Chrome".equals(browser))
            {
                WebDriverManager.chromedriver().setup();
                setWebDriver(new ChromeDriver());
            }
            else if ("Firefox".equals(browser))
            {
                WebDriverManager.firefoxdriver().setup();
                setWebDriver(new FirefoxDriver());
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            openURL(url);

        } catch (IllegalStateException e) {
            Reporter.log("Exception Occurred while setting up browser " + e);
        }
    }
    public WebDriver getWebDriver()
    {
        return driver;
    }
}
