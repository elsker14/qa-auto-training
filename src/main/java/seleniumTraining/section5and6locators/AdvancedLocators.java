package seleniumTraining.section5and6locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class AdvancedLocators {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.id("inputUsername")).sendKeys("rahul");
        driver.findElement(By.name("inputPassword")).sendKeys("rahulshettyacademy");
        driver.findElement(By.className("submit")).click();
        Thread.sleep(1000);

        String successfulLogInMessage = driver.findElement(By.tagName("p")).getText();
        System.out.println(successfulLogInMessage);
        Assert.assertEquals(successfulLogInMessage, "You are successfully logged in.", "Unsuccessful log in.");

//        driver.findElement(By.xpath("//button[text()='Log Out']")).click();
        driver.findElement(By.xpath("//*[text()='Log Out']")).click();
        Thread.sleep(1000);

        driver.close();
    }
}
