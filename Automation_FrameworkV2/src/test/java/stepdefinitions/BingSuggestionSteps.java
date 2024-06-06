package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WebDriverUtils;

import java.util.List;

import static org.junit.Assert.*;

public class BingSuggestionSteps {

    WebDriver driver;

    @Before
    public void setUp() {
        // Initialize WebDriver before each test
        driver = WebDriverUtils.getDriver();
    }

    @After
    public void tearDown() {
        // Quit WebDriver after each test
        WebDriverUtils.quitDriver();
    }

    @Given("I open Bing search page for suggestions functionality")
    public void i_open_bing_search_page_for_suggestions_functionality() {
        // Open Bing search page
        driver.get("https://www.bing.com");
    }

    @When("I enter partial search term {string} in the search box")
    public void i_enter_partial_search_term_in_the_search_box(String partialSearchTerm) {
        // Find search box and click to focus
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.click();

        // Enter the search term one letter at a time
        for (char ch : partialSearchTerm.toCharArray()) {
            searchBox.sendKeys(String.valueOf(ch));
            try {
                // Adding a small delay to simulate typing
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Trigger suggestions with key presses
        searchBox.sendKeys(Keys.DOWN);
        searchBox.sendKeys(Keys.UP);
    }

    @Then("I should see search suggestions dropdown")
    public void i_should_see_search_suggestions_dropdown() {
        // Verify search suggestions dropdown is visible
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement suggestionsDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sa_sg")));
        assertTrue(suggestionsDropdown.isDisplayed());
    }

    @Then("the search suggestions should contain {string}")
    public void the_search_suggestions_should_contain(String expectedSuggestion) {
        // Verify expected suggestion is present in the suggestions dropdown
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".sa_sg .sa_tm")));
        boolean suggestionFound = suggestions.stream().anyMatch(suggestion -> suggestion.getText().contains(expectedSuggestion));
        assertTrue("Expected suggestion not found in the dropdown", suggestionFound);
    }

    @Then("I should see no search suggestions dropdown")
    public void i_should_see_no_search_suggestions_dropdown() {
        // Verify search suggestions dropdown is not visible
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean suggestionsDropdownNotVisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".sa_sg")));
        assertTrue(suggestionsDropdownNotVisible);
    }
}
