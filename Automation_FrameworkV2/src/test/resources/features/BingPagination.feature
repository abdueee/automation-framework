Feature: Bing Search Pagination
  As a user
  I want to perform a search on Bing
  And navigate through the search results pages

  Scenario: Basic pagination from first to second page
    Given I open Bing search page
    When I search for "selenium testing"
    Then I should see search results
    And I navigate to the next page
    Then I should see search results on the next page

  Scenario: Multi-page navigation
    Given I open Bing search page
    When I search for "selenium testing"
    And I navigate through 5 pages
    Then I should see search results on the fifth page

  Scenario: Specific page navigation
    Given I open Bing search page
    When I search for "selenium testing"
    And I navigate to page 10
    Then I should see search results on page 10

  Scenario: Invalid page number navigation
    Given I open Bing search page
    When I search for "selenium testing"
    And I navigate to an invalid page number 1000
    Then I should see an error message or no results

  Scenario: First page navigation
    Given I open Bing search page
    When I search for "selenium testing"
    And I navigate to the first page
    Then I should see search results on the first page

  Scenario: Last page navigation
    Given I open Bing search page
    When I search for "selenium testing"
    And I navigate to the last page
    Then I should see search results on the last page

  Scenario: Search with zero results
    Given I open Bing search page
    When I search for "asdfghjklqwertyuiop1234567890zxcvbnm"
    Then I should see no search results
    And pagination controls should be hidden
