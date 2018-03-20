Feature: Cucumber implementation Page2

  Scenario: Docs2
    Given I navigate to "https://cucumber.io/"
    When I take a look at the Docs
    Then I see a browser title containing "Cucumber"

  Scenario: Grid2
    Given I navigate to "https://www.seleniumhq.org/"
    When I take a look at the Selenium Webdriver Grid
    Then I see a browser title containing "Selenium WebDriver"