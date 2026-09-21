package TestScripts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDriven {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // 1. Define the Data Provider method
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        // This 2D array contains 3 sets of test inputs (Rows = iterations, Columns = parameters)
        return new Object[][] {
            { "standard_user", "secret_sauce", true },   // Valid User
            { "locked_out_user", "secret_sauce", false }, // Locked out User
            { "invalid_user", "wrong_password", false }   // Completely Invalid User
        };
    }

    // 2. Link the test method to the Data Provider using its name
    @Test(dataProvider = "loginData")
    public void verifyLoginFlow(String username, String password, boolean isSuccessExpected) {
        // Navigate to a practice website (SauceLabs demo site)
        driver.get("https://saucedemo.com");

        // Locate elements and input the current dataset values
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        if (isSuccessExpected) {
            // Verify successful login by checking if product title element is loaded
            WebElement productTitle = driver.findElement(By.className("title"));
            Assert.assertTrue(productTitle.isDisplayed(), "Login failed for valid user!");
            System.out.println("Passed: successfully logged in with user: " + username);
        } else {
            // Verify error message container appears for bad credentials
            WebElement errorContainer = driver.findElement(By.className("error-message-container"));
            Assert.assertTrue(errorContainer.isDisplayed(), "Error message did not show for invalid user!");
            System.out.println("Passed: Correctly blocked invalid user: " + username);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

