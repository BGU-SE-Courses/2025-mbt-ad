package hellocucumber;

import io.cucumber.java.en.*;
import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {

    private static OpenCartActuator opencartUser;
    private static List<OpenCartActuator> allopenCarts;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "C:\\Users\\מאור לבני\\Desktop\\לימודים\\שנה ג\\הנדסת איכות תוכנה\\chromedriver-win64\\chromedriver.exe";
    private static OpenCartAdminActuator adminActuator;

    /**
     * Initializes a user session for OpenCart and adds it to the list of sessions.
     * Opens the browser and navigates to the OpenCart user interface.
     */
    public void OpenCartInitUser() {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
        if (allopenCarts == null) {
            allopenCarts = new ArrayList<>();
        }
        opencartUser = new OpenCartActuator();
        allopenCarts.add(opencartUser);
        opencartUser.initSessionAsUser(webDriver, path);
    }

    /**
     * Step definition: Initializes the user session when the user opens the website.
     */
    @Given("User opens the website")
    public void userIsOnHomePage() {
        OpenCartInitUser();
    }

    /**
     * Step definition: Logs the user into the OpenCart website with the given email and password.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     */
    @When("User is logged in with {string} and {string}")
    public void user_is_logged_in_with(String email, String password) {
        opencartUser.goToLogin();
        opencartUser.enterLoginInfo(email, password);
    }

    /**
     * Step definition: Simulates clicking the 'Edit Account' button in the user's account settings.
     */
    @And("User clicks on the 'Edit Account' button")
    public void user_click_Edit_Account_button() {
        opencartUser.Click_Edit_Account_button();
    }

    /**
     * Step definition: Updates the user's first name with the provided new name.
     *
     * @param new_name The new first name for the user.
     */
    @When("User enters a new first name {string}")
    public void user_Enter_new_First_Name(String new_name) {
        opencartUser.writeNewFirstName(new_name);
    }

    /**
     * Step definition: Saves the changes in the user's account information.
     */
    @And("User saves the changes in their Account Information")
    public void User_saves_changes_in_Account_Information() {
        opencartUser.submitChangesInAccountInformation();
    }

    /**
     * Step definition: Checks if a confirmation message is displayed after updating account information.
     */
    @Then("a confirmation message is displayed")
    public void checkConfirmationMessageOnAccountUpdate() {
        opencartUser.checkIfConfirmationMessageOnAccountUpdated();
    }

    /**
     * Step definition: Verifies that the updated first name is reflected in the user's account information.
     *
     * @param new_name The updated first name to verify.
     */
    @And("the user should see the updated first name {string} in their Account Information")
    public void first_name_updated_successfully(String new_name) {
        opencartUser.Click_Edit_Account_button();
        opencartUser.CheckIfFirstNameChange(new_name);
        opencartUser.closeBrowser();
    }

    /**
     * Step definition: Navigates the user to the 'My Account Information' page.
     */
    @And("User navigates to the 'My Account Information' page")
    public void user_click_Edit_your_account_information_button() {
        opencartUser.Click_your_account_information_button();
    }

    /**
     * Initializes the admin session for OpenCart.
     * Opens the browser and navigates to the OpenCart admin interface.
     */
    public void AdminSignIn() {
        adminActuator = new OpenCartAdminActuator();
        adminActuator.InitAdminActuator(webDriver, path);
    }

    /**
     * Step definition: Logs the admin into the OpenCart admin panel with the given username and password.
     *
     * @param username The admin username.
     * @param password The admin password.
     */
    @And("Admin is logged in with {string} and {string}")
    public void adminIsLoggedInWith(String username, String password) {
        AdminSignIn();
        try {
            adminActuator.navigateToAdminLogInPage();
            adminActuator.AdminLogIn(username, password);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Step definition: Navigates the admin to the Customers List in the admin panel.
     */
    @And("Admin navigates to the Customers List")
    public void adminNavigatesToTheCustomersList() {
        try {
            adminActuator.EnterToCustomersList();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Step definition: Admin selects and deactivates the user with the given email.
     *
     * @param email The email of the user to deactivate.
     */
    @When("Admin selects And deactivates status of user with email {string}")
    public void adminSelectsAndDeactivatesTheUserWithEmail(String email) {
        try {
            adminActuator.SearchByEmail(email);
            adminActuator.DeactivateFirstOnList();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Step definition: Admin navigates back to the Customers List in the admin panel.
     */
    @And("Admin navigates back to the Customers List")
    public void adminNavigatesBackToTheCustomersList() {
        try {
            adminActuator.BackToCustomersList();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Step definition: Verifies that the user with the given email is disabled in the Customers List.
     *
     * @param email The email of the user to verify.
     */
    @Then("when search User with email {string} , it should be disabled")
    public void inAdminPanelUserWithEmailShouldNoLongerBeInListOfActiveUsers(String email) {
        try {
            adminActuator.SearchByEmail(email);
            adminActuator.CheckIfDisabled();
            adminActuator.closeBrowser();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
