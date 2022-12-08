package technical.assessment.pages;

import config.BaseConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtils;

public class HomePage extends BaseConfig {

    private WebDriver driver;
    @FindBy(xpath = "//div[contains(text(),'Largest Price Comparison Site')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//a[@href='computing/laptops/']")
    private WebElement laptopLink;

    @FindBy(xpath = "//div/input[@id='see-more-PLP-FA-Category-Carousel']//..//div//span[text()='See More ˅']")
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

    public String getPageTitle() {
        return this.pageTitle.getText();
    }

    public LaptopPage goToLaptopsPage() {
        CommonUtils.scrollIntoView(driver, laptopLink);
        CommonUtils.clickOnElement(laptopLink);
        return new LaptopPage(this.driver);
    }

    public DressesPage goToDressesPage() {
        CommonUtils.scrollIntoView(driver, seeMoreLink);
        CommonUtils.clickOnElement(seeMoreLink);
        CommonUtils.clickOnElement(dressLink);

        return new DressesPage(this.driver);
    }

    public SearchResults searchForDevice(String deviceName){
        searchInput.click();
        searchInput.sendKeys(deviceName);
        searchButton.click();
        return new SearchResults(this.driver);
    }

}
