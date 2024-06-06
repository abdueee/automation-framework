Feature: Bing Search Functionality

  Scenario: Valid search term
    Given I open Bing search page for search functionality
    When I search for "selenium testing" for search functionality
    Then I should see search results for search functionality

  Scenario: Invalid search term
    Given I open Bing search page for search functionality
    When I search for "asdfghjklqwertyuiop1234567890zxcvbnm" for search functionality
    Then I should see no search results for search functionality

  Scenario: Search with special characters
    Given I open Bing search page for search functionality
    When I search for "!@#$%^&*()_+" for search functionality
    Then I should see search results for search functionality

  Scenario: Search with a very long string
    Given I open Bing search page for search functionality
    When I search for "thisisaverylongsearchtermthatisunlikelytohaveanyresultsbutwearetestingtheedgelimitofthesearchfunctionality" for search functionality
    Then I should see search results for search functionality

  Scenario: Search with an empty string
    Given I open Bing search page for search functionality
    When I search for "" for search functionality
    Then I should see an error message or no results for search functionality

  Scenario: Search with a single character
    Given I open Bing search page for search functionality
    When I search for "a" for search functionality
    Then I should see search results for search functionality

  Scenario: Search with a numeric string
    Given I open Bing search page for search functionality
    When I search for "1234567890" for search functionality
    Then I should see search results for search functionality

  Scenario: Search with a mix of letters and numbers
    Given I open Bing search page for search functionality
    When I search for "selenium123" for search functionality
    Then I should see search results for search functionality
