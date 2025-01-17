Feature: User Profile Management
  Scenario Outline: User tries to change their name
    Given User opens the website
    And User is logged in with "<Email>" and "<Password>"
    And User clicks on the Edit Account button
    When User enters a new name "<NewName>"
    And User saves the changes in their Account Information
    Then a confirmation message is displayed
    And the user should see "<NewName>" in the profile information

    Examples:
      | Email                   | Password | NewName |
      | maorlivni050@gmail.com  | 1234     | Ran     |