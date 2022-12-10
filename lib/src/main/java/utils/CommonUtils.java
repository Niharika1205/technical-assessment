package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;

public class CommonUtils {
    /**
     * Method to click on element.
     * @param ele
     */
    public static void clickOnElement(WebElement ele) {
        try{
            ele.click();
        } catch(ElementClickInterceptedException e){
            Reporter.log("Element not clickable " + e);
        } catch(NoSuchElementException e){
            Reporter.log("Element not found " + e);
        }
    }

    /**
     * Method to scroll element into view.
     * @param driver
     * @param ele
     */
    public static void scrollIntoView(WebDriver driver, WebElement ele) {
        try{
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
        }catch(NoSuchElementException e){
            Reporter.log("Element not found " + e);
        }
    }

    /**
     * Method to wait for a webelement.
     * @param driver
     * @return wait.
     */
    public static WebDriverWait explicitWait(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Method to get page header text.
     * @param ele
     * @param pageTitle
     * @return header text
     */
    public static boolean isPageValid(WebElement ele, String pageTitle){
        try{
            return ele.getText().contains(pageTitle);
        } catch(NoSuchElementException e){
            Reporter.log("Element not found " + e);
        }
        return false;
    }
}
