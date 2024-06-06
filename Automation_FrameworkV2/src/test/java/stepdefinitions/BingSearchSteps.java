package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pages.BingSearchPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WebDriverUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BingSearchSteps {

    WebDriver driver = WebDriverUtils.getDriver();
    BingSearchPage bingSearchPage = new BingSearchPage(driver);

    @Before
    public void setUp() {
        // Initialize WebDriver and BingSearchPage before each test
        driver = WebDriverUtils.getDriver();
    }

    @After
    public void tearDown() {
        // Quit WebDriver after each test
        WebDriverUtils.quitDriver();
    }

    @Given("I open Bing search page")
    public void i_open_bing_search_page() {
        // Open Bing search page
        driver.get("https://www.bing.com");
    }

    @When("I search for {string}")
    public void i_search_for(String searchTerm) {
        // Enter search term and submit search
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
    }

    @Then("I should see search results")
    public void i_should_see_search_results() {
        // Verify search results are displayed
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("b_content")));
        assertTrue(results.isDisplayed());
    }
    
    @Then("I should see no search results")
    public void i_should_see_no_search_results_plain() {
        // Verify no search results are displayed
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("b_content")));
        assertEquals(0, bingSearchPage.getResultsCount());
    }
    
    @Then("I should see an error message or no results")
    public void i_should_see_an_error_message_or_no_results_plain() {
        // Verify error message or no results are displayed
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean isErrorMessageVisible = bingSearchPage.isErrorMessageVisible();
        boolean noResults = bingSearchPage.getResultsCount() == 0;
        assertTrue(isErrorMessageVisible || noResults);
    }
}
