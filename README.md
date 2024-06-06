# Bing Search Automation Framework
This project is an automated test suite for Bing search functionality using Selenium WebDriver and Cucumber. The purpose is to test various search scenarios and pagination on Bing's search page.

## Project Structure
### src/test/java

**pages**: Contains page object models for Bing's search page.

**stepdefinitions:** Contains step definitions for Cucumber tests.

**utils**: Utility classes for WebDriver setup and waiting mechanisms.

**TestRunner**: Contains the test runner class for executing Cucumber tests.


### src/test/resources

**features**: Contains Cucumber feature files describing test scenarios.

## Dependencies
This project uses Maven for dependency management. The required dependencies are specified in the pom.xml file.

- Java
- Selenium WebDriver
- Cucumber
- JUnit

## Setup
### Clone the repository:

git clone https://github.com/abdueee/automation-framework.git

**Open the project in Eclipse:**

Import the project as an existing Maven project.

**Set up WebDriver:**

Download the latest ChromeDriver first.
Update the path to ChromeDriver in WebDriverUtils class:

System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

**Install Cucumber Plugin in Eclipse:**

Go to Help > Eclipse Marketplace.
Search for "Cucumber" and install the Cucumber Eclipse plugin.


## Running Tests

Right-click on the TestRunner class.
Select Run As > JUnit Test.

## Feature Files
Feature files are located in src/test/resources/features and describe the various test scenarios for Bing search functionality, including:

- Valid search term
- Search with special characters
- Pagination
- Search suggestions

## Step Definitions
Step definitions are implemented in the following classes:

- BingSearchSteps
- BingPaginationSteps
- BingSuggestionSteps

## Utilities
- WebDriverUtils: Manages WebDriver setup and teardown.
- WaitUtils: Provides utility methods for waiting for elements to be visible or clickable.

## Page Object Model
This project uses the Page Object Model (POM) design pattern to create an object repository for web elements. the Page Object here is the **BingSearchPage** Class
The BingSearchPage class represents the Bing search page and contains methods to interact with various elements on the page.

## Reporting
Test results are generated in HTML format in the target/cucumber-reports directory.

