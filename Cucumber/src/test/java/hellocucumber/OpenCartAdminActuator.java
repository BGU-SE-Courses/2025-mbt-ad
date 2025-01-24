package hellocucumber;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OpenCartAdminActuator {
    public static WebDriver driver;
    private static WebDriverWait wait;

    /**
     * Constructor for OpenCartAdminActuator.
     */
    public OpenCartAdminActuator() {
    }

    /**
     * Initializes the WebDriver session for the admin, sets up the browser, and navigates to the OpenCart admin page.
     *
     * @param webDriver The system property key for the WebDriver.
     * @param path      The path to the WebDriver executable.
     */
    public void InitAdminActuator(String webDriver, String path) {
        System.setProperty(webDriver, path);
        driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("http://localhost/opencartsite/MaorAdmin/");
        driver.manage().window().setPosition(new Point(700, 5));
        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    /**
     * Navigates to the OpenCart admin login page.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void navigateToAdminLogInPage() throws InterruptedException {
        driver.get("http://localhost/opencartsite/MaorAdmin/");
        Thread.sleep(1000);
    }

    /**
     * Logs the admin into the OpenCart admin panel using the provided username and password.
     *
     * @param Username The admin's username.
     * @param password The admin's password.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void AdminLogIn(String Username, String password) throws InterruptedException {
        WebElement UsernameElement = driver.findElement(By.xpath("//*[@id='input-username']"));
        UsernameElement.sendKeys(Username);
        WebElement lastNameElement = driver.findElement(By.xpath("//*[@id='input-password']"));
        lastNameElement.sendKeys(password);
        Thread.sleep(1000);
        WebElement LogInButton = driver.findElement(By.xpath("//button[1]"));
        LogInButton.click();
        Thread.sleep(5000);
    }

    /**
     * Navigates to the Customers List in the OpenCart admin panel.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void EnterToCustomersList() throws InterruptedException {
        WebElement expandCustomers = driver.findElement(By.xpath("//body/div[1]/nav[1]/ul[1]/li[6]/a[1]"));
        expandCustomers.click();
        Thread.sleep(1000);
        WebElement ToList = driver.findElement(By.xpath("//li[6]/ul[1]/li[1]/a[1]"));
        ToList.click();
        Thread.sleep(1000);
    }

    /**
     * Searches for a user in the Customers List using their email address.
     *
     * @param email The email address of the user to search for.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void SearchByEmail(String email) throws InterruptedException {
        WebElement SearchByName = driver.findElement(By.xpath("//*[@id='input-email']"));
        SearchByName.clear();
        SearchByName.sendKeys(email);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);
        WebElement Filter = driver.findElement(By.xpath("//*[@id='button-filter']\n"));
        Filter.click();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);
    }

    /**
     * Deactivates the first user in the filtered Customers List if their status is "Enabled".
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void DeactivateFirstOnList() throws InterruptedException {
        WebElement disabled = driver.findElement(By.xpath("//small[1]"));
        if (disabled.getText().equals("Enabled")) {
            WebElement editFirst = driver.findElement(By.xpath("//td[6]/div[1]/a[1]/i[1]"));
            editFirst.click();
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);
            WebElement ChangeStatus = driver.findElement(By.xpath("//*[@id='input-status']"));
            ChangeStatus.click();
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            Thread.sleep(1000);
            WebElement SaveButton = driver.findElement(By.xpath("//div[1]/div[1]/button[1]/i[1]\n"));
            SaveButton.click();
            Thread.sleep(1000);
        }
    }

    /**
     * Verifies if the first user in the filtered Customers List is disabled.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void CheckIfDisabled() throws InterruptedException {
        WebElement disabled = driver.findElement(By.xpath("//small[1]"));
        if (!disabled.getText().equals("Disabled")) {
            throw new RuntimeException("User is not disabled");
        }
    }

    /**
     * Navigates back to the Customers List in the OpenCart admin panel.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void BackToCustomersList() throws InterruptedException {
        WebElement BackButton = driver.findElement(By.xpath("//ol[1]/li[2]/a[1]"));
        BackButton.click();
        Thread.sleep(1000);
    }

    /**
     * Closes the browser and terminates the WebDriver session.
     */
    public void closeBrowser() {
        driver.quit();
    }
}
