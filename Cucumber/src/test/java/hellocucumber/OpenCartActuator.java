package hellocucumber;

import io.cucumber.java.Before;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OpenCartActuator{
    public static WebDriver driver;
    private static WebDriverWait wait;

    public void initSessionAsUser(String webDriver, String path){
        // webDriver = "webdriver.chrome.driver"
        System.setProperty(webDriver, path);

        // new chrome driver object
        driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        // launch website -> localhost
        driver.get("http://localhost/opencartsite/");

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().setPosition(new Point(700, 5));

        /*
            If we wanted to test the web application on different devices -
                1. Open the web app
                2. Right click -> click inspect
                3. Click on the phone icon at the top left corner of the inspect window (the app changes preview format at this point)
                4. Locate the dimensions drop-down list at the top of the web app and choose device
                5. Copy dimensions size (on the right side of the drop-down list)
                   -> driver.manage().window().setSize(new Dimension(width, height));
         */

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }


    public void goToLogin(){
        // locate and click on web element -> login
        driver.findElement(By.xpath("//*[@id='top']/div[1]/div[2]/ul[1]/li[2]/div[1]/a[1]/span[1]")).click();
        driver.findElement(By.xpath("//*[@id='top']/div[1]/div[2]/ul[1]/li[2]/div[1]/ul[1]/li[2]/a[1]")).click();

    }



    public void enterLoginInfo(String username, String password) {
//        // locate the username input box and enter username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-email']"))).sendKeys(username);
//        // locate the password input box and enter password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-password']"))).sendKeys(password);
//        // locate Log in button and press
        driver.findElement(By.xpath("//*[@id='form-login']/div/button[1]")).click();
        // Wait for a moment
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void Click_Edit_Account_button(){
        // locate and click on Edit Account
        driver.findElement(By.xpath("//div[1]/a[2]")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void writeNewFirstName(String newName) {
        // Find the input box for the first name and wait until it is visible
        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-firstname']"))
        );

        // Clear the previous text in the input box
        firstNameField.clear();

        // Enter the new name into the input box
        firstNameField.sendKeys(newName);
    }

    public void submitChangesInAccountInformation() {
        driver.findElement(By.xpath("//div[2]/button[1]")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkIfConfirmationMessageOnAccountUpdated() {
        // Define the expected confirmation message
        String expectedMessage = "Success: Your account has been successfully updated.";

        // Locate the element that contains the confirmation message
        WebElement informationMessage = driver.findElement(By.xpath("//main[1]/div[2]/div[1]"));

        // Extract the text of the confirmation message
        String actualMessage = informationMessage.getText();
//        System.out.println("Confirmation message displayed: " + actualMessage);

        // Assert that the actual message matches the expected message
        assertEquals(expectedMessage, actualMessage, "The confirmation message is incorrect or not displayed.");
    }

    @Test
    public void CheckIfFirstNameChange(String new_name) {
        // Locate the input field for the first name and wait until it is visible
        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-firstname']"))
        );

        // Extract the value of the input field
        String actualName = firstNameField.getAttribute("value");

        // Assert that the actual name matches the expected name
        assertEquals(new_name, actualName, "The user first name is incorrect.");
    }

    @Test
    public void checkIfErrorMessageForNameLengthIsDisplayed() {
        String expectedMessage = "First Name must be between 1 and 32 characters!";
        // Locate the element that contains the confirmation message
        WebElement informationMessage = driver.findElement(By.xpath("//*[@id='error-firstname']"));
        // Extract the text of the confirmation message
        String actualMessage = informationMessage.getText();
        // Assert that the actual message matches the expected message
        assertEquals(expectedMessage, actualMessage, "The error message is incorrect or not displayed.");


    }

    @Test
    public void CheckIfFirstNameUnchanged(String wrong_name) {

        // Locate the input field for the first name and wait until it is visible
        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-firstname']"))
        );

        // Extract the value of the input field
        String actualName = firstNameField.getAttribute("value");
//        System.out.println(actualName);

        // Assert that the actual name matches the expected name
        assertNotEquals(wrong_name, actualName, "The user name is incorrect.");
    }

    public void Click_your_account_information_button() {
        // locate and click on "Edit your account information"
        driver.findElement(By.xpath("//div[2]/div[1]/div[1]/ul[1]/li[1]/a[1]")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void logeOut() {
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight);", new Object[0]);

        try {
            Thread.sleep(500L);
        } catch (InterruptedException var12) {
            var12.printStackTrace();
        }
        driver.findElement(By.xpath("//div[1]/a[13]")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//div[2]/div[1]/div[1]/div[1]/a[1]")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void writeNewLastName(String newName) {
        // Find the input box for the last name and wait until it is visible
        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-lastname']"))
        );

        // Clear the previous text in the input box
        firstNameField.clear();

        // Enter the new name into the input box
        firstNameField.sendKeys(newName);
    }

    public void CheckIfLastNameChange(String newName) {
        // Locate the input field for the last name and wait until it is visible
        WebElement lastNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-lastname']"))
        );

        // Extract the value of the input field
        String actualName = lastNameField.getAttribute("value");

        // Assert that the actual name matches the expected name
        assertEquals(newName, actualName, "The user last name is incorrect.");
    }

    public void navigateToUserRegisterPage() throws InterruptedException {
        // Navigate to the OpenCart website
        WebElement MyAccountButton= driver.findElement(By.xpath("//li[2]/div[1]/a[1]/span[1]"));
        MyAccountButton.click();
        Thread.sleep(1000);
        WebElement RegisterButton= driver.findElement(By.xpath("//li[2]/div[1]/ul[1]/li[1]/a[1]"));
        RegisterButton.click();

    }

    //Log In as User
    public void registerUser( String email, String password) throws InterruptedException {
        driver.get("http://localhost/opencartsite/");
        Thread.sleep(1000);
        // Fill in registration details
        WebElement firstNameElement = driver.findElement(By.xpath("//*[@id='input-firstname']"));
        firstNameElement.sendKeys("firstname");
        WebElement lastNameElement = driver.findElement(By.xpath("//*[@id='input-lastname']"));
        lastNameElement.sendKeys("lastname");
        WebElement emailElement = driver.findElement(By.xpath("//*[@id='input-email']"));
        emailElement.sendKeys(email);
        WebElement passwordElement = driver.findElement(By.xpath("//*[@id='input-password']"));
        passwordElement.sendKeys(password);
        Thread.sleep(1000);
        // Scroll down to the "Agree" checkbox
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Wait for a moment
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the "Agree" checkbox
        WebElement agreeCheckbox = driver.findElement(By.xpath("//*[@name='agree']"));
        agreeCheckbox.click();

        // Wait for a few seconds to observe the actions
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement continueClick = driver.findElement(By.xpath("//body/main[1]/div[2]/div[1]/div[1]/form[1]/div[1]/button[1]"));
        continueClick.click();
    }
    public void closeBrowser() {
        // Close the browser
        driver.quit();
    }

}
