//package hellocucumber;
//
//import io.cucumber.java.en.*;
//import org.junit.After;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class change_name_StepDefinitions {
//
////    private static List<OpenCartActuator> allOpenCarts;
//    private static OpenCartActuator opencartUser;
//
//    private static List<OpenCartActuator> allopenCarts;
//
//    private String webDriver = "webdriver.chrome.driver";
//    private String path = "C:\\Users\\מאור לבני\\Desktop\\לימודים\\שנה ג\\הנדסת איכות תוכנה\\chromedriver-win64\\chromedriver.exe";
//
//
//
//
//    public void OpenCartInitUser() {
//        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
//        if(allopenCarts == null){
//            allopenCarts = new ArrayList<>();
//        }
//        opencartUser = new OpenCartActuator();
//        allopenCarts.add(opencartUser);
//        opencartUser.initSessionAsUser(webDriver, path);
//    }
//
//    @Given("User opens the website")
//    public void userIsOnHomePage() {
//        OpenCartInitUser();
//
//    }
//
//
//    @And("user add a product to cart")
//    public void userAddAProductToCart() {
//        opencartUser.AddProductiMac();
//    }
//
//    @Then("the product successfully added to the cart")
//    public void theProductSuccessfullyAdded() {
//        opencartUser.CheckProductAdded(1);
//    }
//
//    @When("User is logged in with {string} and {string}")
//    public void user_is_logged_in_with(String email, String password) {
//        opencartUser.goToLogin();
//        opencartUser.enterLoginInfo(email, password);
//    }
//
//
//    @And("User clicks on the 'Edit Account' button")
//    public void user_click_Edit_Account_button(){
//        opencartUser.Click_Edit_Account_button();
//    }
//
//    @When("User enters a new first name {string}")
//    public void user_Enter_new_First_Name(String new_name){
//        opencartUser.writeNewFirstName(new_name);
//    }
//
//    @And("User saves the changes in their Account Information")
//    public void User_saves_changes_in_Account_Information(){
//        opencartUser.submitChangesInAccountInformation();
//    }
//
//    @Then("a confirmation message is displayed")
//    public void checkConfirmationMessageOnAccountUpdate() {
//        opencartUser.checkIfConfirmationMessageOnAccountUpdated();
//    }
//
//    @And("the user should see the updated first name {string} in their Account Information")
//    public void first_name_updated_successfully(String new_name) {
//        opencartUser.Click_Edit_Account_button();
//        opencartUser.CheckIfFirstNameChange(new_name);
//    }
//    //+++++++++++++++++++++++++++++++++++++++++++++++++new
//
//    @Then("an informative error message about the length of the name is displayed")
//    public void verifyErrorMessageForNameLength() {
//        opencartUser.checkIfErrorMessageForNameLengthIsDisplayed();
//    }
//
//    @And("the user's name should remain unchanged and not change to {string}")
//    public void verifyUserNameRemainsUnchanged(String wrong_name) {
//        opencartUser.Click_Edit_Account_button();
//        opencartUser.CheckIfFirstNameUnchanged(wrong_name);
//    }
//    @And("User clicks on the 'Edit your account information' button")
//    public void user_click_Edit_your_account_information_button(){
//        opencartUser.Click_your_account_information_button();
//    }
//
//    @When("User logs out")
//    public void User_logs_out(){
//        opencartUser.logeOut();
//    }
//
//    @And("User logs in again with {string} and {string}")
//    public void User_logs_in_again(String email, String password) {
//        user_is_logged_in_with(email, password);
//    }
//
//    @When("User enters a new last name {string}")
//    public void user_Enter_new_Last_Name(String new_name){
//        opencartUser.writeNewLastName(new_name);
//    }
//    @And("the user should see the updated last name {string} in their Account Information")
//    public void last_name_updated_successfully(String new_name) {
//        opencartUser.Click_Edit_Account_button();
//        opencartUser.CheckIfLastNameChange(new_name);
//    }
//    @When("User doesn't change their name and saves the changes in their Account Information")
//    public void save_without_change(){
//        User_saves_changes_in_Account_Information();
//    }
//
//}
//
//



//================================================================================================
package hellocucumber;

import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {


    private static OpenCartActuator opencartUser;

    private static List<OpenCartActuator> allopenCarts;

    private String webDriver = "webdriver.chrome.driver";
    private String path = "C:\\Users\\מאור לבני\\Desktop\\לימודים\\שנה ג\\הנדסת איכות תוכנה\\chromedriver-win64\\chromedriver.exe";

    private static OpenCartAdminActuator adminActuator ;



    public void OpenCartInitUser() {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
        if(allopenCarts == null){
            allopenCarts = new ArrayList<>();
        }
        opencartUser = new OpenCartActuator();
        allopenCarts.add(opencartUser);
        opencartUser.initSessionAsUser(webDriver, path);
    }

    @Given("User opens the website")
    public void userIsOnHomePage() {
        OpenCartInitUser();

    }



    @When("User is logged in with {string} and {string}")
    public void user_is_logged_in_with(String email, String password) {
        opencartUser.goToLogin();
        opencartUser.enterLoginInfo(email, password);
    }


    @And("User clicks on the 'Edit Account' button")
    public void user_click_Edit_Account_button(){
        opencartUser.Click_Edit_Account_button();
    }

    @When("User enters a new first name {string}")
    public void user_Enter_new_First_Name(String new_name){
        opencartUser.writeNewFirstName(new_name);
    }

    @And("User saves the changes in their Account Information")
    public void User_saves_changes_in_Account_Information(){
        opencartUser.submitChangesInAccountInformation();
    }

    @Then("a confirmation message is displayed")
    public void checkConfirmationMessageOnAccountUpdate() {
        opencartUser.checkIfConfirmationMessageOnAccountUpdated();
    }

    @And("the user should see the updated first name {string} in their Account Information")
    public void first_name_updated_successfully(String new_name) {
        opencartUser.Click_Edit_Account_button();
        opencartUser.CheckIfFirstNameChange(new_name);
        closeBrowser();
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++new

    @Then("an informative error message about the length of the name is displayed")
    public void verifyErrorMessageForNameLength() {
        opencartUser.checkIfErrorMessageForNameLengthIsDisplayed();
    }

    @And("the user's name should remain unchanged and not change to {string}")
    public void verifyUserNameRemainsUnchanged(String wrong_name) {
        opencartUser.Click_Edit_Account_button();
        opencartUser.CheckIfFirstNameUnchanged(wrong_name);
    }
    @And("User clicks on the 'Edit your account information' button")
    public void user_click_Edit_your_account_information_button(){
        opencartUser.Click_your_account_information_button();
    }

    @When("User logs out")
    public void User_logs_out(){
        opencartUser.logeOut();
    }

    @And("User logs in again with {string} and {string}")
    public void User_logs_in_again(String email, String password) {
        user_is_logged_in_with(email, password);
    }

    @When("User enters a new last name {string}")
    public void user_Enter_new_Last_Name(String new_name){
        opencartUser.writeNewLastName(new_name);
    }
    @And("the user should see the updated last name {string} in their Account Information")
    public void last_name_updated_successfully(String new_name) {
        opencartUser.Click_Edit_Account_button();
        opencartUser.CheckIfLastNameChange(new_name);
    }
    @When("User doesn't change their name and saves the changes in their Account Information")
    public void save_without_change(){
        User_saves_changes_in_Account_Information();
    }


    @Given("User is registered with {string} and {string}")
    public void RegisterUser(String email, String password) {
         userIsOnHomePage();
        try {
            opencartUser.registerUser(email, password);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void AdminSignIn(){
        adminActuator = new OpenCartAdminActuator();
        adminActuator.InitAdminActuator(webDriver, path);
    }
    @And( "Admin is logged in with {string} and {string}")
    public void adminIsLoggedInWith(String username, String password) {
        AdminSignIn();
        try {
            adminActuator.navigateToAdminLogInPage();
            adminActuator.AdminLogIn(username, password);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @And("Admin navigates to the Customers List")
    public void adminNavigatesToTheCustomersList() {
        try {
            adminActuator.EnterToCustomersList();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @When("Admin selects And deactivates status of user with email {string}")
    public void adminSelectsAndDeactivatesTheUserWithEmail(String email) {
        try {
            adminActuator.SearchByEmail(email);
            adminActuator.DeactivateFirstOnList();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @And ("Admin navigates back to the Customers List")
    public void adminNavigatesBackToTheCustomersList() {
        try {
            adminActuator.BackToCustomersList();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Then("when search User with email {string} , it should be disabled")
    public void inAdminPanelUserWithEmailShouldNoLongerBeInListOfActiveUsers(String email) {
        try {
            adminActuator.SearchByEmail(email);
            adminActuator.CheckIfDisabled();
//            closeBrowser();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //    @AfterAll
    public void closeBrowser() {
        adminActuator.closeBrowser();
        opencartUser.closeBrowser();
    }
}


