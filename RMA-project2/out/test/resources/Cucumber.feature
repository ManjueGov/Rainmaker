Feature: Cucumber Home Page

  Scenario: Docs
    Given I navigate to "https://cucumber.io/"
    When I take a look at the Docs
    Then I see a browser title containing "Cucumber"

  Scenario: Google
    Given I navigate to "https://google.com"