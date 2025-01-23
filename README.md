# Software Quality Engineering - System Testing
This is a repository for the system-testing assignment of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called [Opencart](https://address-of-the-project.com).

OpenCart is a simple and flexible open-source platform for creating and managing online stores. Users can register on the site to organize products, interact with customers, and handle payments, all through an easy-to-use interface.

## What we tested
We tested the User Account Management and Administrative Control features, focusing on two main use cases.

*User story:* User tries to change first name in their account information.

*Preconditions:* The user has an existing account and is logged in.
The user navigates to the "My Account Information" page.

*Expected outcome:* A confirmation message is displayed.
The updated first name appears in the user’s account information.

*User story:* An admin deactivates a specific user from the Customers List.

*Preconditions:*
The admin has valid credentials to access the admin interface.
The user to be deactivated exists in the Customers List.

*Expected outcome:* The user's account status is updated to "disabled" in the Customers List.


## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a behavior-driven testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Detected Bugs
We detected the following bugs:

1. Bug 1: 
   1. General description: ...
   2. Steps to reproduce: ...
   3. Expected result: ...
   4. Actual result: ...
   5. Link to the bug report: (you are encouraged to report the bug to the developers of the software)
2. Bug 2: ...

$$*TODO* if you did not detect the bug, you should delete this section$$  
