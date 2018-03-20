Feature: Cucumber implementation Page

  Scenario: Docs
    Given I navigate to "https://cucumber.io/"
    When I take a look at the Docs
    Then I see a browser title containing "Cucumber"

  Scenario: Grid
    Given I navigate to "https://www.seleniumhq.org/"
    When I take a look at the Selenium Webdriver Grid
    Then I see a browser title containing "Selenium WebDriver"