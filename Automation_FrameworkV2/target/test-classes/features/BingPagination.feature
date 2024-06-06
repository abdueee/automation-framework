Feature: Bing Search Pagination
  As a user
  I want to perform a search on Bing
  And navigate through the search results pages

  Scenario: Basic pagination from first to second page
    Given I open Bing search page for pagination
    When I search for "selenium testing" for pagination
    Then I should see search results for pagination
    And I navigate to the next page for pagination
    Then I should see search results on the next page for pagination

  Scenario: Multi-page navigation
    Given I open Bing search page for pagination
    When I search for "selenium testing" for pagination
    And I navigate through 5 pages for pagination
    Then I should see search results on the fifth page for pagination

  Scenario: Specific page navigation
    Given I open Bing search page for pagination
    When I search for "selenium testing" for pagination
    And I navigate to page 10 for pagination
    Then I should see search results on page 10 for pagination

  Scenario: Invalid page number navigation
    Given I open Bing search page for pagination
    When I search for "selenium testing" for pagination
    And I navigate to an invalid page number 1000 for pagination
    Then I should see an error message or no results for pagination

  Scenario: First page navigation
    Given I open Bing search page for pagination
    When I search for "selenium testing" for pagination
    And I navigate to the first page for pagination
    Then I should see search results on the first page for pagination

  Scenario: Last page navigation
    Given I open Bing search page for pagination
    When I search for "selenium testing" for pagination
    And I navigate to the last page for pagination
    Then I should see search results on the last page for pagination

  Scenario: Search with zero results
    Given I open Bing search page for pagination
    When I search for "asdfghjklqwertyuiop1234567890zxcvbnm" for pagination
    Then I should see no search results for pagination
    And pagination controls should be hidden for pagination
