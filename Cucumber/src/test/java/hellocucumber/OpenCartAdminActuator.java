package hellocucumber;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenCartAdminActuator {

    public static WebDriver driver;
    private static WebDriverWait wait;

    public OpenCartAdminActuator() {

    }

    public void InitAdminActuator(String webDriver, String path){
        // webDriver = "webdriver.chrome.driver"
        System.setProperty(webDriver, path);

        // new chrome driver object
        driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        // launch website -> localhost
        driver.get("http://localhost/opencartsite/MaorAdmin/");

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

    public OpenCartAdminActuator(String webDriver , String path) {
        // Set the path of the ChromeDriver executable
        System.setProperty(webDriver, path);

        // Create an instance of ChromeDriver and WebDriverWait
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait
    }

    //Open Admin Page
    public void navigateToAdminLogInPage() throws InterruptedException {
        // Navigate to the OpenCart website
//        driver.get("http://localhost/opencartsite/admin12345/");
        driver.get("http://localhost/opencartsite/MaorAdmin/");
        Thread.sleep(1000);

    }

    //Log In as Admin
    public void AdminLogIn(String Username,String password) throws InterruptedException {
        // Fill in LogIn details
        WebElement UsernameElement = driver.findElement(By.xpath("//*[@id='input-username']"));
        UsernameElement.sendKeys(Username);
        WebElement lastNameElement = driver.findElement(By.xpath("//*[@id='input-password']"));
        lastNameElement.sendKeys(password);

        Thread.sleep(1000);
        // Click the "Login" button
        WebElement LogInButton= driver.findElement(By.xpath("//button[1]"));
        LogInButton.click();
        Thread.sleep(5000);
    }

    //open Customer List
    public void EnterToCustomersList() throws InterruptedException {
        WebElement expandCustomers = driver.findElement(By.xpath("//body/div[1]/nav[1]/ul[1]/li[6]/a[1]"));
        expandCustomers.click();
        Thread.sleep(1000);
        WebElement ToList = driver.findElement(By.xpath("//li[6]/ul[1]/li[1]/a[1]"));
        ToList.click();
        Thread.sleep(1000);
    }

    //Search and Deactivate
    public void SearchByEmail(String email) throws InterruptedException {
        //Serach given name and deactivates it
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
    public void DeactivateFirstOnList() throws InterruptedException {
        WebElement disabled = driver.findElement(By.xpath("//small[1]"));
        if(disabled.getText().equals("Enabled")){
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
        else
        {

        }
    }

    public void CheckIfDisabled() throws InterruptedException {
        WebElement disabled = driver.findElement(By.xpath("//small[1]"));
        if(!disabled.getText().equals("Disabled")){
            throw new RuntimeException("User is not disabled");
        }
    }
    public void BackToCustomersList() throws InterruptedException {
        WebElement BackButton = driver.findElement(By.xpath("//ol[1]/li[2]/a[1]"));
        BackButton.click();
        Thread.sleep(1000);
    }



    public void closeBrowser() {
        // Close the browser
        driver.quit();
    }



}
