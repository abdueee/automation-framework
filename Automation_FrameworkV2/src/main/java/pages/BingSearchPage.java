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

    @FindBy(name = "q")
    public WebElement searchBox;

    @FindBy(id = "sb_form_go")
    public WebElement searchButton;

    @FindBy(css = "li.b_algo")
    public List<WebElement> searchResults;

    @FindBy(css = "a.sb_pagN")
    public WebElement nextPageButton;

    @FindBy(css = "a.sb_pagP")
    public WebElement previousPageButton;

    public BingSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchTerm(String searchTerm) {
        WaitUtils.waitForElementToBeVisible(driver, searchBox, 10);
        searchBox.sendKeys(searchTerm);
    }

    public void clickSearchButton() {
        WaitUtils.waitForElementToBeClickable(driver, searchButton, 10);
        searchButton.click();
    }

    public int getResultsCount() {
        WaitUtils.waitForElementToBeVisible(driver, searchResults.get(0), 10);
        return searchResults.size();
    }

    public void clickNextPageButton() {
        WaitUtils.waitForElementToBeClickable(driver, nextPageButton, 10);
        nextPageButton.click();
    }

    public void clickPreviousPageButton() {
        WaitUtils.waitForElementToBeClickable(driver, previousPageButton, 10);
        previousPageButton.click();
    }

    public void navigateToPage(int pageNumber) {
        WebElement pageLink = driver.findElement(By.xpath("//a[@aria-label='Page " + pageNumber + "']"));
        WaitUtils.waitForElementToBeClickable(driver, pageLink, 10);
        pageLink.click();
    }

    public boolean isSearchResultsVisible() {
        return !searchResults.isEmpty() && searchResults.get(0).isDisplayed();
    }
    
    public boolean arePaginationControlsVisible() {
        return nextPageButton.isDisplayed() || previousPageButton.isDisplayed();
    }

    public boolean isErrorMessageVisible() {
        try {
            WebElement errorMessage = driver.findElement(By.cssSelector("div.b_no"));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
