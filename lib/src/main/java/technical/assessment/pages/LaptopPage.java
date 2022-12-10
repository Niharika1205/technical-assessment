package technical.assessment.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import utils.CommonUtils;
import utils.Constants;

import java.util.List;

public class LaptopPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@id='open-filter-panel']")
    private WebElement spriteIcon;

    @FindBys(@FindBy(xpath = "//span[@data-vars-cgt='click_brand_filter_label']//span"))
    private List<WebElement> brands;

    @FindBys(@FindBy(xpath = "//a[@data-vars-cgt='click_comparable_product']/div//h3"))
    private List<WebElement> productName;

    @FindBy(xpath = "//div[@id='breadcrumb']/div//h1")
    public WebElement pageHeader;

    public LaptopPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to apply filter based on brand name.
     *
     * @param brand
     */
    public void filterByBrand(String brand) {
        try {
            spriteIcon.click();
            for (int i = 0; i < brands.size(); i++) {
                WebElement brandValue = brands.get(i);
                String brandName = brandValue.getText();
                if (brandName.equals(brand)) {
                    brandValue.click();
                    CommonUtils.explicitWait(driver).until(ExpectedConditions.
                            titleContains(Constants.laptopTitle));
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            Reporter.log("Brand not Found" + e);
        } catch (IndexOutOfBoundsException e) {
            Reporter.log("Product list length exceeded for Laptop Page " + e);
        } catch(TimeoutException e){
            Reporter.log("Timeout occurred while waiting for laptop page to load " + e);
        }
    }

    /**
     * Verify if filter got applied or not.
     *
     * @param brandName
     * @return true if filter is applied
     */
    public Boolean isFilterApplied(String brandName) {
        try {
            for (int i = 0; i < productName.size(); i++) {
                if (productName.get(i).getText().contains(brandName)) {
                    return true;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            Reporter.log("Product list length exceeded for filter results " + e);
        } catch (NoSuchElementException e) {
            Reporter.log("Element not found for filters " + e);
        }
        return false;
    }


}
