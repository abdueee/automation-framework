package stepdefinitions;

import org.openqa.selenium.By;
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
    	 WebDriver driver = WebDriverUtils.getDriver();
    	 BingSearchPage searchPage = new BingSearchPage(driver);
    }

    @After
    public void tearDown() {
        WebDriverUtils.quitDriver();
   }

    @Given("I open Bing search page")
    public void i_open_bing_search_page() {
        driver.get("https://www.bing.com");
    }

    @When("I search for {string}")
    public void i_search_for(String query) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    @Then("I should see search results")
    public void i_should_see_search_results() {
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }

    @And("I navigate to the next page")
    public void i_navigate_to_the_next_page() {
        WebElement nextButton = driver.findElement(By.cssSelector("a.sb_pagN"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
    }

    @Then("I should see search results on the next page")
    public void i_should_see_search_results_on_the_next_page() {
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }

    @And("I navigate through {int} pages")
    public void i_navigate_through_pages(int pageCount) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (int i = 0; i < pageCount; i++) {
            WebElement nextButton = driver.findElement(By.cssSelector("a.sb_pagN"));
            wait.until(ExpectedConditions.elementToBeClickable(nextButton));
            nextButton.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("b_content")));
        }
    }

    @And("I navigate to page {int}")
    public void i_navigate_to_page(int pageNumber) {
        WebElement pageLink = driver.findElement(By.xpath("//a[@aria-label='Page " + pageNumber + "']"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(pageLink));
        pageLink.click();
    }

    @Then("I should see search results on page {int}")
    public void i_should_see_search_results_on_page(int pageNumber) {
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }

    @And("I navigate to an invalid page number {int}")
    public void i_navigate_to_an_invalid_page_number(int pageNumber) {
        try {
            WebElement pageLink = driver.findElement(By.xpath("//a[@aria-label='Page " + pageNumber + "']"));
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(pageLink));
            pageLink.click();
        } catch (Exception e) {
            System.out.println("Invalid page number, no link found");
        }
    }

    @Then("I should see an error message or no results")
    public void i_should_see_an_error_message_or_no_results() {
        WebElement results = driver.findElement(By.id("b_content"));
        if (!results.isDisplayed()) {
            System.out.println("No results or error message displayed");
        }
    }

    @Then("I should see no search results")
    public void i_should_see_no_search_results() {
        WebElement results = driver.findElement(By.id("b_content"));
        assert(!results.isDisplayed());
    }

    @Then("pagination controls should be hidden")
    public void pagination_controls_should_be_hidden() {
        WebElement paginationControls = driver.findElement(By.cssSelector("a.sb_pagN"));
        assert(!paginationControls.isDisplayed());
    }

    @Then("I should see search results on the fifth page")
    public void i_should_see_search_results_on_the_fifth_page() {
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }

    @When("I navigate to the first page")
    public void i_navigate_to_the_first_page() {
        WebElement firstPageLink = driver.findElement(By.cssSelector("a.sb_pagP"));
        firstPageLink.click();
    }

    @Then("I should see search results on the first page")
    public void i_should_see_search_results_on_the_first_page() {
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }

    @When("I navigate to the last page")
    public void i_navigate_to_the_last_page() {
        WebElement lastPageLink = driver.findElement(By.cssSelector("a.sb_pagP"));
        lastPageLink.click();
    }

    @Then("I should see search results on the last page")
    public void i_should_see_search_results_on_the_last_page() {
        WebElement results = driver.findElement(By.id("b_content"));
        assert(results.isDisplayed());
    }
}
