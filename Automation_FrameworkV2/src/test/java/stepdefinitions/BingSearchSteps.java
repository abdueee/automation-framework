package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BingSearchPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class BingSearchSteps {

    WebDriver driver;
    BingSearchPage bingSearchPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        bingSearchPage = new BingSearchPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I open Bing search page")
    public void i_open_bing_search_page_for_search() {
        driver.get("https://www.bing.com");
    }

    @When("I search for {string}")
    public void i_search_for_particular_string(String searchTerm) {
        bingSearchPage.enterSearchTerm(searchTerm);
        bingSearchPage.clickSearchButton();
    }

    @Then("I should see search results")
    public void i_should_see_search_results_displayed_to_me() {
        assertTrue(bingSearchPage.getResultsCount() > 0);
    }

    @Then("I should see no search results")
    public void i_should_see_no_search_results_displayed_to_me() {
        assertEquals(0, bingSearchPage.getResultsCount());
    }

    @Then("pagination controls should be hidden")
    public void pagination_controls_should_be_hidden_for_me() {
        assertFalse(bingSearchPage.arePaginationControlsVisible());
    }

    @Then("I should see an error message or no results")
    public void i_should_see_an_error_message_or_no_results_displayed_to_me() {
        assertTrue(bingSearchPage.isErrorMessageVisible() || bingSearchPage.getResultsCount() == 0);
    }
}
