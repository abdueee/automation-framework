Feature: Bing Search Suggestions

  Scenario: Valid partial search term
    Given I open Bing search page for suggestions functionality
    When I enter partial search term "selenium t" in the search box
    Then I should see search suggestions dropdown
    And the search suggestions should contain "selenium testing"

  Scenario: Partial search term with no suggestions
    Given I open Bing search page for suggestions functionality
    When I enter partial search term "asdfghjklqwerty" in the search box
    Then I should see no search suggestions dropdown

  Scenario: Search with special characters
    Given I open Bing search page for suggestions functionality
    When I enter partial search term "!@#$%" in the search box
    Then I should see no search suggestions dropdown

  Scenario: Search with a very long partial string
    Given I open Bing search page for suggestions functionality
    When I enter partial search term "thisisaverylongpartialsearchtermidoubtanyonewillactuallyseethisimveryhungrygonnaorderburgerking" in the search box
    Then I should see no search suggestions dropdown
