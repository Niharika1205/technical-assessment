package technical.assessment;

import config.BaseConfig;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import technical.assessment.pages.DressesPage;
import technical.assessment.pages.HomePage;
import technical.assessment.pages.LaptopPage;
import technical.assessment.pages.SearchResults;
import utils.CommonUtils;

public class UiTests extends BaseConfig {
    HomePage homePageObj;
    LaptopPage laptopPageObj;
    DressesPage dressesPageObj;

    SearchResults searchResultsObj;
    private WebDriver driver;

    @BeforeMethod
    public void launchHomePage() {
        browserSettings("Chrome", getBaseURL());
        this.driver = getWebDriver();
        homePageObj = new HomePage(driver);
        Assert.assertEquals(homePageObj.getPageTitle(), "Malaysia's Largest Price Comparison Site",
                "Home Page not loaded");
    }

    @Test
    public void applyFilterTest() {

        laptopPageObj = homePageObj.goToLaptopsPage();
        Assert.assertTrue(CommonUtils.isPageValid(laptopPageObj.pageHeader, "Latest Laptops in Malaysia Price List"),
                "Laptops page not loaded");
        laptopPageObj.filterByBrand("Dell");
        Assert.assertTrue(laptopPageObj.isFilterApplied("Dell"), "Filter is not working");

    }

    @Test
    public void sortResultsTest() {
        dressesPageObj = homePageObj.goToDressesPage();
        Assert.assertTrue(CommonUtils.isPageValid(dressesPageObj.pageHeader,"Dresses Price List in Malaysia"),
                "Dresses page is not loaded");
        Assert.assertTrue(dressesPageObj.isResultSorted());
        Assert.assertTrue(dressesPageObj.verifySortedResult(),
                "Results are not sorted in descending order of Price");
    }

    @Test
    public void searchDeviceTest() {
        String product = "iPhone 14";
        searchResultsObj = homePageObj.searchForDevice(product);
        Assert.assertTrue(CommonUtils.isPageValid(searchResultsObj.pageHeader,product),"Search Results not loaded");
        Boolean isSearchValid = searchResultsObj.verifySearchResults(product);
        Assert.assertTrue(isSearchValid, "Search results are not valid");
    }

    @AfterMethod
    public void closeBrowser() {
        exit();
    }
}
