package hellocucumber;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenCartActuator {
    public static WebDriver driver;
    private static WebDriverWait wait;

    /**
     * Initializes the WebDriver session for a user, sets up the browser, and navigates to the OpenCart website.
     *
     * @param webDriver The system property key for the WebDriver.
     * @param path      The path to the WebDriver executable.
     */
    public void initSessionAsUser(String webDriver, String path) {
        System.setProperty(webDriver, path);
        driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("http://localhost/opencartsite/");
        driver.manage().window().setPosition(new Point(700, 5));
        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    /**
     * Navigates to the login page by interacting with the necessary UI elements.
     */
    public void goToLogin() {
        driver.findElement(By.xpath("//*[@id='top']/div[1]/div[2]/ul[1]/li[2]/div[1]/a[1]/span[1]")).click();
        driver.findElement(By.xpath("//*[@id='top']/div[1]/div[2]/ul[1]/li[2]/div[1]/ul[1]/li[2]/a[1]")).click();
    }

    /**
     * Enters the provided username and password into the login fields and submits the form.
     *
     * @param username The user's email address.
     * @param password The user's password.
     */
    public void enterLoginInfo(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-email']"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-password']"))).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='form-login']/div/button[1]")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clicks on the "Edit Account" button to navigate to the account information page.
     */
    public void Click_Edit_Account_button() {
        driver.findElement(By.xpath("//div[1]/a[2]")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the first name field with the provided new name.
     *
     * @param newName The new first name to set.
     */
    public void writeNewFirstName(String newName) {
        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-firstname']"))
        );
        firstNameField.clear();
        firstNameField.sendKeys(newName);
    }

    /**
     * Submits the changes made to the account information.
     */
    public void submitChangesInAccountInformation() {
        driver.findElement(By.xpath("//div[2]/button[1]")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if a confirmation message is displayed after updating the account information.
     */
    @Test
    public void checkIfConfirmationMessageOnAccountUpdated() {
        String expectedMessage = "Success: Your account has been successfully updated.";
        WebElement informationMessage = driver.findElement(By.xpath("//main[1]/div[2]/div[1]"));
        String actualMessage = informationMessage.getText();
        assertEquals(expectedMessage, actualMessage, "The confirmation message is incorrect or not displayed.");
    }

    /**
     * Verifies if the first name field is updated with the new name.
     *
     * @param new_name The expected new first name.
     */
    @Test
    public void CheckIfFirstNameChange(String new_name) {
        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-firstname']"))
        );
        String actualName = firstNameField.getAttribute("value");
        assertEquals(new_name, actualName, "The user first name is incorrect.");
    }

    /**
     * Clicks on the "Edit your account information" button to navigate to the account information page.
     */
    public void Click_your_account_information_button() {
        driver.findElement(By.xpath("//div[2]/div[1]/div[1]/ul[1]/li[1]/a[1]")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the browser and terminates the WebDriver session.
     */
    public void closeBrowser() {
        driver.quit();
    }
}
