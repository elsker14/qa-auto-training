package seleniumTraining.section9sync.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class AssignmentWait {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");

        // Get username && password message
        String userPassMessage = driver.findElement(By.cssSelector(".text-center.text-white")).getText();
        System.out.println(userPassMessage);
        CustomPair credentials = getUsernameAndPasswordFromText(userPassMessage);
        System.out.println(credentials.getKey() + " -> " + credentials.getValue());
        Assert.assertEquals(credentials.getKey(), "rahulshettyacademy", "Provided username does not match the hardcoded one.");
        Assert.assertEquals(credentials.getValue(), "learning", "Provided password does not match the hardcoded one.");

        // Log in with credentials
        logInUser(driver, driverWait, credentials);

        // Wait to reach the requested webpage
        driverWait.until(ExpectedConditions.urlToBe("https://rahulshettyacademy.com/angularpractice/shop"));

        // Select products randomly
        selectProductsRandomly(driver);

        // Go to checkout
        driver.findElement(By.xpath("//*[contains(text(), 'Checkout')]")).click();
    }

    public static CustomPair getUsernameAndPasswordFromText(String text) {
        CustomPair result = new CustomPair();
        String[] splittedText = text.split(" ");
        result.setKey(splittedText[2].trim());
        result.setValue(splittedText[splittedText.length - 1].trim());
        result.setValue(result.getValue().substring(0, result.getValue().length() - 1));
        return result;
    }

    public static void logInUser(WebDriver driver, WebDriverWait driverWait, CustomPair credentials) {
        // Fill in username and password
        driver.findElement(By.name("username")).sendKeys(credentials.getKey());
        driver.findElement(By.id("password")).sendKeys(credentials.getValue());

        // Click on User radio button
        driver.findElement(By.xpath("(//label[@class='customradio']//following-sibling::span[@class='checkmark'])[2]")).click();

        // Select Consultant option and accept modal form
        Select optionsRole = new Select(driver.findElement(By.cssSelector("select[data-style='btn-info']")));
        optionsRole.selectByIndex(2);
        /*
        // Accepting Alert approach in case it was, but it wasn't
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        */
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
        driver.findElement(By.id("okayBtn")).click();

        // Click on terms and condition checkbox
        driver.findElement(By.id("terms")).click();

        // Click on signInBtn
        driver.findElement(By.name("signin")).click();
    }

    public static void selectProductsRandomly(WebDriver driver) {
        // Add randomly between 1 and total nr of products
        System.out.println();
        List<WebElement> productsList = driver.findElements(By.xpath("//app-card-list[@class='row']//app-card"));
        for (WebElement we : productsList) {
            System.out.println(we.getText());
            System.out.println();
        }

        Random random = new Random();
        int nrOfProducts = random.nextInt(productsList.size()) + 1;
        for (int i = 1; i <= nrOfProducts; i++) {
            String productLocator = "(//*[@class='btn btn-info'])[ " + i + "]";
            driver.findElement(By.xpath(productLocator)).click();
        }
    }
}
