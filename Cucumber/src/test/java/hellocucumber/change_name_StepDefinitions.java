package hellocucumber;

import io.cucumber.java.en.*;

import org.junit.Before;
import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class change_name_StepDefinitions {

    private static List<OpenCartActuator> allOpenCarts;
    private static OpenCartActuator opencartUser;

    private static List<OpenCartActuator> allopenCarts;

    private String webDriver = "webdriver.chrome.driver";
    private String path = "C:\\Users\\מאור לבני\\Desktop\\לימודים\\שנה ג\\הנדסת איכות תוכנה\\chromedriver-win64\\chromedriver.exe";


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


    @And("user add a product to cart")
    public void userAddAProductToCart() {
        opencartUser.AddProductiMac();
    }

    @Then("the product successfully added to the cart")
    public void theProductSuccessfullyAdded() {
        opencartUser.CheckProductAdded(1);
    }

    @When("User is logged in with {string} and {string}")
    public void user_is_logged_in_with_and(String email, String password) {
        opencartUser.goToLogin();
        opencartUser.enterLoginInfo(email, password);
    }


    @And("User clicks on the Edit Account button")
    public void user_click_Edit_Account_button(){
        opencartUser.Click_Edit_Account_button();
    }

    @When("User enters a new name {string}")
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

    @And("the user should see {string} in the profile information")
    public void name_updated_successfully(String new_name) {
        opencartUser.Click_Edit_Account_button();
        opencartUser.CheckIfFirstNameChange(new_name);

    }
}

