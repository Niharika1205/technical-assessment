package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonUtils {

    public static void clickOnElement(WebElement ele) {
        ele.click();
    }

    public static void scrollIntoView(WebDriver driver, WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    public static WebDriverWait explicitWait(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static boolean isPageValid(WebElement ele, String pageTitle){
        return ele.getText().contains(pageTitle);
    }
}
