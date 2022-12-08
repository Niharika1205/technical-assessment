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

    public long getPriceValue(int index) {
        String price = priceList.get(index).getText();
        price = price.substring(3, price.length() - 3).replace(",", "");
        return Long.parseLong(price);
    }

    public Boolean verifySortedResult() {
        for (int i = 0; i < priceList.size(); i++) {
            if (getPriceValue(i) < getPriceValue(i + 1)) {
                return false;
            }
        }
        return true;

    }
}
