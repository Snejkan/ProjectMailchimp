import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


import static org.junit.Assert.assertEquals;

public class MailchimpStepdefs {
    private WebDriver driver;
    private WebDriverWait wait;



    @Given("user is on the Sign Up page")
    public void userIsOnTheSignUpPage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://login.mailchimp.com/signup/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Given("the user has accepted cookies")
    public void theUserHasAcceptedCookies() {
        sendClick(By.id("onetrust-accept-btn-handler"));
    }

    @When("the required fields been correctly filled")
    public void theRequiredFieldsBeenCorrectlyFilled() {
        sendKeys(By.xpath("//*[@id=\"email\"]"), "snejkie@bluff.com");
        sendKeys(By.id("new_username"), "Stolpskott");
        sendKeys(By.id("new_password"), "YeeCmon666#");
    }

    @When("user clicks the Sign Up button")
    public void userClicksTheSignUpButton() {
        sendClick(By.id("create-account-enabled"));
    }

    @Then("user will be registered")
    public void userWillBeRegistered() {
        WebElement registered = driver.findElement(By.className("!margin-bottom--lv3 no-transform center-on-medium success-h2"));
        String actual = registered.getText();
        String expected = "Check your email";
        assertEquals(expected, actual);
        
    }
    private void sendKeys(By by, String text){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.sendKeys(text);
    }
    private void sendClick(By by){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.click();
    }


    @When("user enter username with more than {int} characters")
    public void userEnterUsernameWithMoreThanCharacters(int arg0) {
        sendKeys(By.id("new_username"), "a".repeat(150));
    }

    @When("user enters correct mail and password")
    public void userEntersCorrectMailAndPassword() {
        sendKeys(By.xpath("//*[@id=\"email\"]"), "snejkie@bluff.com");
        sendKeys(By.id("new_password"), "YeeCmon666#");
    }



    @When("user enters username already taken")
    public void userEntersUsernameAlreadyTaken() {
        sendKeys(By.id("new_username"), "Anna");
    }

    @When("user enters correct username and password")
    public void userEntersCorrectUsernameAndPassword() {
        sendKeys(By.id("new_username"), "Stolpskott");
        sendKeys(By.id("new_password"), "YeeCmon666#");
    }




    @Then("user will get an error message indicating username is too long")
    public void userWillGetAnErrorMessageIndicatingUsernameIsTooLong() {
        WebElement tooLong = driver.findElement(By.className("invalid-error"));
        String actual = tooLong.getText();
        String expected = "Enter a value less than 100 characters long";
        assertEquals(expected, actual);
    }

    @Then("user will get an error message indicating username is taken")
    public void userWillGetAnErrorMessageIndicatingUsernameIsTaken() {
        WebElement isTaken = driver.findElement(By.className("invalid-error"));
        String actual = isTaken.getText();
        String expected = "Great minds think alike - someone already has this username.";
        assertEquals(expected, actual);
    }

    @Then("user will get an error message indicating email is missing")
    public void userWillGetAnErrorMessageIndicatingEmailIsMissing() {
        WebElement isMissing = driver.findElement(By.className("invalid-error"));
        String actual = isMissing.getText();
        String expected = "An email address must contain a single @.";
        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
