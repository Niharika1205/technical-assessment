package technical.assessment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResults {

    private WebDriver driver;

    @FindBys(@FindBy(xpath = "//a[@data-vars-cgt='click_comparable_product']/div//h3"))
    private List<WebElement> productName;

    @FindBy(xpath = "//div[@id='breadcrumb']/div//h1")
    public WebElement pageHeader;

    public SearchResults(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Verify if results are displayed based on search text or not
     * @param deviceName
     * @return true if expected results are displayed
     */
    public Boolean verifySearchResults(String deviceName){
        for(int i=0; i< productName.size(); i++){
            if(!(productName.get(i).getText().contains(deviceName))){
                return false;
            }
        }
        return true;
    }
}
