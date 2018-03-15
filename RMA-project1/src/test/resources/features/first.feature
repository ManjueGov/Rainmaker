Feature: Registered user will login and verifies then logs out.

  Scenario: User profile login and logout
    Given user logs in
    And verifies dashboard
    Then logs out