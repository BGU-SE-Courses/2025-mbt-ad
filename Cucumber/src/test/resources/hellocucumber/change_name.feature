Feature: User tries to change their name

  Scenario Outline: User updates their first name
    Given User opens the website
    And User is logged in with "<Email>" and "<Password>"
    And User clicks on the 'Edit your account information' button
    When User enters a new first name "<NewFirstName>"
    And User saves the changes in their Account Information
    Then a confirmation message is displayed
    And the user should see the updated first name "<NewFirstName>" in their Account Information

    Examples:
      | Email                   | Password | NewFirstName |
      | maorlivni050@gmail.com  | 1234     | Ran         |


#  Scenario Outline: User dont updates their name via the 'Edit your account information' button

#
#  Scenario Outline: User updates their last name via the 'Edit Account' button
#    Given User opens the website
#    And User is logged in with "<Email>" and "<Password>"
#    And User clicks on the 'Edit Account' button
#    When User enters a new last name "<NewLastName>"
#    And User saves the changes in their Account Information
#    Then a confirmation message is displayed
#    And the user should see the updated last name "<NewLastName>" in their Account Information
#
#    Examples:
#      | Email                   | Password | NewLastName |
#      | maorlivni050@gmail.com  | 1234     | Erez        |
#
#  Scenario Outline: User updates their first name and last name
#    Given User opens the website
#    And User is logged in with "<Email>" and "<Password>"
#    And User clicks on the 'Edit Account' button
#    When User enters a new first name "<NewFirstName>"
#    And User enters a new last name "<NewLastName>"
#    And User saves the changes in their Account Information
#    Then a confirmation message is displayed
#    And the user should see the updated first name "<NewFirstName>" in their Account Information
#    And the user should see the updated last name "<NewLastName>" in their Account Information
#
#    Examples:
#      | Email                   | Password |NewFirstName | NewLastName |
#      | maorlivni050@gmail.com  | 1234     |Ran             | Erez        |
#
#
#  Scenario Outline: User doesn't update their name
#    Given User opens the website
#    And User is logged in with "<Email>" and "<Password>"
#    And User clicks on the 'Edit Account' button
#    When User doesn't change their name and saves the changes in their Account Information
#    Then a confirmation message is displayed
#    Examples:
#      | Email                   | Password |
#      | maorlivni050@gmail.com  | 1234     |
#
#
#
#  Scenario Outline: User tries to change their first name to a very long name
#    # more than 32 characters
#    Given User opens the website
#    And User is logged in with "<Email>" and "<Password>"
#    And User clicks on the 'Edit Account' button
#    When User enters a new first name "<NewFirstName>"
#    And User saves the changes in their Account Information
#    Then an informative error message about the length of the name is displayed
#    And the user's name should remain unchanged and not change to "<NewFirstName>"
#
#    Examples:
#      | Email                  | Password | NewFirstName                             |
#      | maorlivni050@gmail.com | 1234     | AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA   |
#      | maorlivni050@gmail.com | 1234     | BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB   |
#
#
#  Scenario Outline: User tries to change their first name to a name with only spaces
#    Given User opens the website
#    And User is logged in with "<Email>" and "<Password>"
#    And User clicks on the 'Edit Account' button
#    When User enters a new first name "<NewFirstName>"
#    And User saves the changes in their Account Information
#    Then an informative error message about the length of the name is displayed
#    And the user's name should remain unchanged and not change to "<NewFirstName>"
#
#    Examples:
#      | Email                  | Password | NewFirstName |
#      # 3 spaces
#      | maorlivni050@gmail.com | 1234     |         |
##      #one space
##      | maorlivni050@gmail.com | 1234     |   |
##      | maorlivni050@gmail.com | 1234     ||
#
#  Scenario Outline: User changes their first name, logs out, and logs in again
#    Given User opens the website
#    And User is logged in with "<Email>" and "<Password>"
#    And User clicks on the 'Edit Account' button
#    And User enters a new first name "<NewFirstName>"
#    And User saves the changes in their Account Information
#    When User logs out
#    And User logs in again with "<Email>" and "<Password>"
#    Then the user should see the updated first name "<NewFirstName>" in their Account Information
#
#    Examples:
#      | Email                   | Password | NewFirstName |
#      | maorlivni050@gmail.com  | 1234     | Maor    |



# Scenario Outline: User enters a new name but does not save the changes
#    Given User opens the website
#    And User is logged in with "maorlivni050@gmail.com" and "1234"
#    And User clicks on the Edit Account button
#    When User enters a new name "John Doe"
#    And User navigates away without saving
#    Then the user's name should remain unchanged