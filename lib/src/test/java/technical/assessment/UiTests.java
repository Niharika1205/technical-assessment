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
import utils.Constants;

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
        Assert.assertEquals(homePageObj.getPageTitle(), Constants.expectedHomePageTitle, Constants.homeErrorMessage);
    }

    /**
     * Test method to validate that the results returned from page matches the selected brand.
     */
    @Test
    public void applyFilterTest() {

        laptopPageObj = homePageObj.goToLaptopsPage();
        Assert.assertTrue(CommonUtils.isPageValid(laptopPageObj.pageHeader, Constants.expectedLaptopPageTitle),
                Constants.laptopErrorMessage);
        laptopPageObj.filterByBrand(Constants.deviceName);
        Assert.assertTrue(laptopPageObj.isFilterApplied(Constants.deviceName), Constants.filterErrorMessage);

    }

    /**
     * Test method to validate that the results are sorted in descending order of Price.
     */
    @Test
    public void sortResultsTest() {
        dressesPageObj = homePageObj.goToDressesPage();
        Assert.assertTrue(CommonUtils.isPageValid(dressesPageObj.pageHeader, Constants.expectedDressesPageTitle),
                Constants.dressesErrorMessage);
        Assert.assertTrue(dressesPageObj.isResultSorted());
            Assert.assertTrue(dressesPageObj.verifySortedResult(), Constants.sortErrorMessage);
    }

    /**
     * Test method to validate that the search results returned matches the search criteria.
     */
    @Test
    public void searchDeviceTest() {

        searchResultsObj = homePageObj.searchForDevice(Constants.searchProduct);
        Assert.assertTrue(CommonUtils.isPageValid(searchResultsObj.pageHeader,Constants.searchProduct),
                Constants.searchErrorMessage);
        Boolean isSearchValid = searchResultsObj.verifySearchResults(Constants.searchProduct);
        Assert.assertTrue(isSearchValid, Constants.invalidSearchError);
    }

    @AfterMethod
    public void closeBrowser() {
        exit();
    }
}
