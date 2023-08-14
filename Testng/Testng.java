import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SnapdealLoginTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the path of the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Launch the Chrome browser
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void loginTest() {
        // Navigate to Snapdeal site
        driver.get("http://www.snapdeal.com");

        // Move to Sign In button and hold
        WebElement signInButton = driver.findElement(By.linkText("Sign In"));
        Actions actions = new Actions(driver);
        actions.moveToElement(signInButton).build().perform();

        // Move to the Sign In button and click
        WebElement signInOption = driver.findElement(By.linkText("login-register"));
        actions.moveToElement(signInOption).click().build().perform();

        // Enter valid Email Id and click continue
        WebElement emailInput = driver.findElement(By.id("userName"));
        emailInput.sendKeys("example@example.com");
        WebElement continueButton = driver.findElement(By.id("checkUser"));
        continueButton.click();

        // Enter valid password and click LOGIN
        WebElement passwordInput = driver.findElement(By.id("j_password_login_uc"));
        passwordInput.sendKeys("password123");
        WebElement loginButton = driver.findElement(By.id("submitLoginUC"));
        loginButton.click();

        // Verify that the user is logged in successfully
        WebElement userName = driver.findElement(By.className("usernameText"));
        Assert.assertTrue(userName.getText().contains("Your Name"), "User login failed");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}