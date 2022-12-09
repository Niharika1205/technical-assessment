package technical.assessment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.CommonUtils;

import java.util.List;

public class DressesPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@data-vars-lb='Price']/i")
    private WebElement sortIcon;

    @FindBys(@FindBy(xpath = "//div[contains(text(),'RM')]"))
    private List<WebElement> priceList;

    @FindBy(xpath = "//div[@id='breadcrumb']/div//h1")
    public WebElement pageHeader;

    public DressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to verify if results are sorted in descending order
     * @return boolean value if sorted
     */
    public Boolean isResultSorted() {

        CommonUtils.clickOnElement(sortIcon);
        CommonUtils.explicitWait(driver).until(ExpectedConditions.attributeContains(sortIcon, "class", "ascending"));
        CommonUtils.clickOnElement(sortIcon);
        CommonUtils.explicitWait(driver).until(ExpectedConditions.attributeContains(sortIcon, "class", "descending"));

        if (sortIcon.getAttribute("class").contains("i-descending-green-active")) {
            return true;
        }
        return false;
    }

    /**
     * Method to extract price of dresses
     * @param index of product in list
     * @return price in long format
     */
    public double getPriceValue(int index) {
        String price = priceList.get(index).getText();
        price = price.substring(3, price.length()).replace(",", "");
        return Double.parseDouble(price);
    }

    /**
     * Verify if results are sorted based on price value
     * @return true if sorted in descending
     */
    public Boolean verifySortedResult() {
        for (int i = 0; i < priceList.size()-1; i++) {
            if (getPriceValue(i) < getPriceValue(i + 1)) {
                return false;
            }
        }
        return true;

    }
}
