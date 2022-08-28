package seleniumTraining.section7automateWebElems;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;

public class AssignmentAllUI {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        // Fill in name
        driver.findElement(By.xpath("//div[@class='form-group']//input[@name='name']")).sendKeys("Iancu Jianu");

        // Fill in email
        driver.findElement(By.name("email")).sendKeys("iancujianu98@gmail.com");

        // Fill in password
        driver.findElement(By.cssSelector("input[placeholder*='Pass']")).sendKeys("123456789");

        // Click on ice cream checkbox
        driver.findElement(By.xpath("//*[@type='checkbox']")).click();

        // Select option from static dropdown
        Select genderOptions = new Select(driver.findElement(By.id("exampleFormControlSelect1")));
        genderOptions.selectByIndex(1);

        // Click on employment status radio button
        driver.findElement(By.xpath("//*[text()='Student']//parent::div[@class='form-check form-check-inline']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("label[for='inlineRadio3']")).isEnabled(), "Entrepreneur radio button is not enabled");

        // Select birth date from calendar
        // setTimeout(function(){ debugger }, 5000)
        driver.findElement(By.name("bday")).sendKeys("03/14/1998");

        // Click submit button
        driver.findElement(By.cssSelector(".btn.btn-success")).click();

        // Print success message
        Thread.sleep(2000);
        System.out.println(driver.findElement(By.cssSelector(".alert.alert-success.alert-dismissible")).getText());
    }
}
