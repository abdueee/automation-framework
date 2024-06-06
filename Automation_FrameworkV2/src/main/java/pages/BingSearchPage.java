package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

import java.util.List;

public class BingSearchPage {

    WebDriver driver;

    // Locate the search box element by its name attribute
    @FindBy(name = "q")
    public WebElement searchBox;

    // Locate the search button element by its ID attribute
    @FindBy(id = "sb_form_go")
    public WebElement searchButton;

    // Locate the search result elements by their CSS selector
    @FindBy(css = "li.b_algo")
    public List<WebElement> searchResults;

    // Locate the next page button element by its CSS selector
    @FindBy(css = "a.sb_pagN")
    public WebElement nextPageButton;

    // Locate the previous page button element by its CSS selector
    @FindBy(css = "a.sb_pagP")
    public WebElement previousPageButton;

    // Constructor to initialize the WebDriver and WebElements
    public BingSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to enter a search term into the search box
    public void enterSearchTerm(String searchTerm) {
        WaitUtils.waitForElementToBeVisible(driver, searchBox, 10);
        searchBox.sendKeys(searchTerm);
    }

    // Method to click the search button
    public void clickSearchButton() {
        WaitUtils.waitForElementToBeClickable(driver, searchButton, 10);
        searchButton.click();
    }

    // Method to get the count of search results
    public int getResultsCount() {
        WaitUtils.waitForElementToBeVisible(driver, searchResults.get(0), 10);
        return searchResults.size();
    }

    // Method to click the next page button
    public void clickNextPageButton() {
        WaitUtils.waitForElementToBeClickable(driver, nextPageButton, 10);
        nextPageButton.click();
    }

    // Method to click the previous page button
    public void clickPreviousPageButton() {
        WaitUtils.waitForElementToBeClickable(driver, previousPageButton, 10);
        previousPageButton.click();
    }

    // Method to navigate to a specific page number
    public void navigateToPage(int pageNumber) {
        WebElement pageLink = driver.findElement(By.xpath("//a[@aria-label='Page " + pageNumber + "']"));
        WaitUtils.waitForElementToBeClickable(driver, pageLink, 10);
        pageLink.click();
    }

    // Method to check if search results are visible
    public boolean isSearchResultsVisible() {
        return !searchResults.isEmpty() && searchResults.get(0).isDisplayed();
    }
    
    // Method to check if pagination controls are visible
    public boolean arePaginationControlsVisible() {
        return nextPageButton.isDisplayed() || previousPageButton.isDisplayed();
    }

    // Method to check if an error message is visible
    public boolean isErrorMessageVisible() {
        try {
            WebElement errorMessage = driver.findElement(By.cssSelector("div.b_no"));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
