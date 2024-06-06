Feature: Bing Search Functionality

  Scenario: Valid search term
    Given I open Bing search page
    When I search for "selenium testing"
    Then I should see search results

  Scenario: Invalid search term
    Given I open Bing search page
    When I search for "asdfghjklqwertyuiop1234567890zxcvbnm"
    Then I should see no search results
    And pagination controls should be hidden

  Scenario: Search with special characters
    Given I open Bing search page
    When I search for "!@#$%^&*()_+"
    Then I should see search results

  Scenario: Search with a very long string
    Given I open Bing search page
    When I search for "thisisaverylongsearchtermthatisunlikelytohaveanyresultsbutwearetestingtheedgelimitofthesearchfunctionality"
    Then I should see search results

  Scenario: Search with an empty string
    Given I open Bing search page
    When I search for ""
    Then I should see an error message or no results
    And pagination controls should be hidden

  Scenario: Search with a single character
    Given I open Bing search page
    When I search for "a"
    Then I should see search results

  Scenario: Search with a numeric string
    Given I open Bing search page
    When I search for "1234567890"
    Then I should see search results

  Scenario: Search with a mix of letters and numbers
    Given I open Bing search page
    When I search for "selenium123"
    Then I should see search results
