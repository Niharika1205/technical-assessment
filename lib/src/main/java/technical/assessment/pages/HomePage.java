package technical.assessment.pages;

import config.BaseConfig;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import utils.CommonUtils;

public class HomePage extends BaseConfig {

    private WebDriver driver;
    @FindBy(xpath = "//a[@title='iPrice Mascot Malaysia']/../div/div[2]")
    private WebElement pageTitle;

    @FindBy(xpath = "//a[@href='computing/laptops/']")
    private WebElement laptopLink;

    @FindBy(xpath = "//input[@id='see-more-PLP-FA-Category-Carousel']/../div/label/span[contains(text(),'See')]")
    private WebElement seeMoreLink;

    @FindBy(xpath = "//a[@href='clothing/dresses/']")
    private WebElement dressLink;

    @FindBy(xpath="//input[@id='term-desktop']")
    private WebElement searchInput;

    @FindBy(xpath="//button[@id='search-btn']/i")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to get page header text
     * @return homepage header text
     */
    public String getPageTitle() {
        return this.pageTitle.getText();
    }

    /**
     * Redirect to computing laptops page
     * @return object of laptop page
     */
    public LaptopPage goToLaptopsPage() {
        CommonUtils.scrollIntoView(driver, laptopLink);
        CommonUtils.clickOnElement(laptopLink);
        return new LaptopPage(this.driver);
    }

    /**
     * Redirect to clothing dresses page
     * @return object of dresses page
     */
    public DressesPage goToDressesPage() {
        CommonUtils.scrollIntoView(driver, seeMoreLink);
        CommonUtils.clickOnElement(seeMoreLink);
        CommonUtils.clickOnElement(dressLink);

        return new DressesPage(this.driver);
    }

    /**
     * Enter search text in input box and click on search button
     * @param deviceName
     * @return object of search results page
     */
    public SearchResults searchForDevice(String deviceName){
        try{
            searchInput.click();
            searchInput.sendKeys(deviceName);
            searchButton.click();
        }catch(ElementClickInterceptedException e){
            Reporter.log("Search element not clickable on home page " + e);
        }catch(NoSuchElementException e){
            Reporter.log("Search element not found " + e);
        }

        return new SearchResults(this.driver);
    }

}
