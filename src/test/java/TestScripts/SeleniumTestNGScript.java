package TestScripts;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumTestNGScript {
    
    // Declare the WebDriver instance globally so all annotated methods can use it
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // This runs automatically BEFORE each test method
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyGoogleTitle() {
        // This is your actual test execution block
        driver.get("https://google.com");
        
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        
        // TestNG Assertions to validate test success/failure
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match!");
        System.out.println("Test successfully verified page title: " + actualTitle);
    }

    @AfterMethod
    public void tearDown() {
        // This runs automatically AFTER each test method, ensuring the browser closes safely
        if (driver != null) {
            driver.quit();
        }
    }
}


