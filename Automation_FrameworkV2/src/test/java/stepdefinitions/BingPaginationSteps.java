package stepdefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pages.BingSearchPage;
import utils.WebDriverUtils;

public class BingPaginationSteps {
    
    WebDriver driver = WebDriverUtils.getDriver();
    BingSearchPage searchPage = new BingSearchPage(driver);
    
    @Before
    public void setUp() {
        // Initialize WebDriver and BingSearchPage before each test
        WebDriver driver = WebDriverUtils.getDriver();
        BingSearchPage searchPage = new BingSearchPage(driver);
    }

    @After
    public void tearDown() {
        // Quit WebDriver after each test
        WebDriverUtils.quitDriver();
    }
    
    @Given("I open Bing search page for pagination")
    public void i_open_bing_search_page_pagination() {
        // Open Bing search page
        driver.get("https://www.bing.com");
    }

    @When("I search for {string} for pagination")
    public void i_search_for_pagination(String searchTerm) {
        // Enter search term and submit search
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
    }

    @Then("I should see search results for pagination")
    public void i_should_see_search_results_pagination() {
        // Verify search results are displayed
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }

    @Then("I should see no search results for pagination")
    public void i_should_see_no_search_results_pagination() {
        // Verify no search results are displayed
        WebElement results = driver.findElement(By.id("b_content"));
        assert(!results.isDisplayed());
    }

    @Then("pagination controls should be hidden for pagination")
    public void pagination_controls_should_be_hidden_pagination() {
        // Verify pagination controls are hidden
        WebElement paginationControls = driver.findElement(By.cssSelector("a.sb_pagN"));
        assert(!paginationControls.isDisplayed());
    }

    @Then("I should see an error message or no results for pagination")
    public void i_should_see_an_error_message_or_no_results_pagination() {
        // Verify error message or no results are displayed
        WebElement results = driver.findElement(By.id("b_content"));
        if (!results.isDisplayed()) {
            System.out.println("No results or error message displayed");
        }
    }

    @And("I navigate to the next page for pagination")
    public void i_navigate_to_the_next_page_pagination() {
        // Navigate to the next page
        WebElement nextButton = driver.findElement(By.cssSelector("a.sb_pagN"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
    }

    @Then("I should see search results on the next page for pagination")
    public void i_should_see_search_results_on_the_next_page_pagination() {
        // Verify search results are displayed on the next page
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }

    @And("I navigate through {int} pages for pagination")
    public void i_navigate_through_pages_pagination(int pageCount) {
        // Navigate through specified number of pages
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (int i = 0; i < pageCount; i++) {
            List<WebElement> nextButtons = driver.findElements(By.cssSelector("a.sb_pagN"));
            if (!nextButtons.isEmpty()) {
                WebElement nextButton = nextButtons.get(0);
                wait.until(ExpectedConditions.elementToBeClickable(nextButton));
                try {
                    nextButton.click();
                } catch (ElementClickInterceptedException e) {
                    // Click using JavaScript as a fallback
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
                }
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("b_content")));
            } else {
                System.out.println("No next button found, stopping navigation.");
                break;
            }
        }
    }

    @And("I navigate to page {int} for pagination")
    public void i_navigate_to_page_pagination(int pageNumber) {
        // Navigate to specified page number
        List<WebElement> pageLinks = driver.findElements(By.xpath("//a[@aria-label='Page " + pageNumber + "']"));
        if (!pageLinks.isEmpty()) {
            WebElement pageLink = pageLinks.get(0);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(pageLink));
            pageLink.click();
        } else {
            System.out.println("Page " + pageNumber + " link not found.");
        }
    }

    @Then("I should see search results on page {int} for pagination")
    public void i_should_see_search_results_on_page_pagination(int pageNumber) {
        // Verify search results are displayed on the specified page
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }

    @And("I navigate to an invalid page number {int} for pagination")
    public void i_navigate_to_an_invalid_page_number_pagination(int pageNumber) {
        // Attempt to navigate to an invalid page number
        try {
            WebElement pageLink = driver.findElement(By.xpath("//a[@aria-label='Page " + pageNumber + "']"));
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(pageLink));
            pageLink.click();
        } catch (Exception e) {
            System.out.println("Invalid page number, no link found");
        }
    }

    @Then("I should see search results on the fifth page for pagination")
    public void i_should_see_search_results_on_the_fifth_page_pagination() {
        // Verify search results are displayed on the fifth page
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }

    @When("I navigate to the first page for pagination")
    public void i_navigate_to_the_first_page_pagination() {
        // Navigate to the first page
        WebElement firstPageLink = driver.findElement(By.cssSelector("a.sb_pagP"));
        firstPageLink.click();
    }

    @Then("I should see search results on the first page for pagination")
    public void i_should_see_search_results_on_the_first_page_pagination() {
        // Verify search results are displayed on the first page
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }

    @When("I navigate to the last page for pagination")
    public void i_navigate_to_the_last_page_pagination() {
        // Navigate to the last page
        List<WebElement> pageLinks = driver.findElements(By.cssSelector("a.b_widePag.sb_bp"));
        WebElement lastPageLink = pageLinks.get(pageLinks.size() - 1);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(lastPageLink));
        lastPageLink.click();
    }

    @Then("I should see search results on the last page for pagination")
    public void i_should_see_search_results_on_the_last_page_pagination() {
        // Verify search results are displayed on the last page
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }
}
