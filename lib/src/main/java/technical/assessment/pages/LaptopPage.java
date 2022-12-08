package technical.assessment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CommonUtils;

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

    public void filterByBrand(String brand) {
        spriteIcon.click();
        for (int i = 0; i < brands.size(); i++) {
            WebElement brandValue = brands.get(i);
            String brandName = brandValue.getText();
            if (brandName.equals(brand)) {
                brandValue.click();
                CommonUtils.explicitWait(driver).until(ExpectedConditions.titleContains("Compare Latest Dell Laptops Price in Malaysia"));
                break;
            }
        }

    }

    public Boolean isFilterApplied(String brandName) {
        for (int i = 0; i < productName.size(); i++) {
            if (productName.get(i).getText().contains(brandName)) {
                return true;
            }

        }
        return false;
    }


}
