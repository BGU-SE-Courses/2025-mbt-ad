Feature: User Account Management and Administrative Control

  #Use case1:
  Scenario Outline: User tries to change their name
    Given User opens the website
    And User is logged in with "<Email>" and "<Password>"
    And And User navigates to the 'My Account Information' page
    When User enters a new first name "<NewFirstName>"
    And User saves the changes in their Account Information
    Then a confirmation message is displayed
    And the user should see the updated first name "<NewFirstName>" in their Account Information

    Examples:
      | Email                   | Password | NewFirstName |
      | maorlivni050@gmail.com  | 1234     | Ran    |


  #Use case2:
  Scenario Outline: Admin deactivates a user
    Given Admin is logged in with "<AdminName>" and "<AdminPassword>"
    And   Admin navigates to the Customers List
    When  Admin selects And deactivates status of user with email "<Useremail>"
    And   Admin navigates back to the Customers List
    Then  when search User with email "<Useremail>" , it should be disabled

    Examples:
      | AdminName            | AdminPassword     | Useremail                |
      | MaorLivni                | 1234              | maorlivni050@gmail.com |

