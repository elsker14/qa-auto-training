package seleniumTraining.section5and6locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DynamicPassword {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String username = "Jijel";
        String password = "floridemar";
        String unsuccessfulLogInMessage = "* Incorrect username or password";
        String resetPassMessage = "";

        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.id("inputUsername")).sendKeys(username);
        driver.findElement(By.name("inputPassword")).sendKeys(password);
        driver.findElement(By.className("submit")).click();
        Thread.sleep(1000);

        if (driver.findElement(By.cssSelector("form p[class='error']")).getText().equals(unsuccessfulLogInMessage)) {
            driver.findElement(By.xpath("//*[text()='Forgot your password?']")).click();
            Thread.sleep(1000);

            driver.findElement(By.xpath("//form//input[@type='text'][1]")).sendKeys(username);
            driver.findElement(By.cssSelector("form input:nth-child(3)")).sendKeys("iancujianu98@gmail.com");
            driver.findElement(By.cssSelector("input[placeholder*='Ph']")).sendKeys("0753016068");
            Thread.sleep(1000);

            // Get password from shown message
            driver.findElement(By.className("reset-pwd-btn")).click();
            resetPassMessage = driver.findElement(By.className("infoMsg")).getText();
            String[] passwordArray = resetPassMessage.split("'");
            password = passwordArray[1].split("'")[0];
            System.out.println(password);

            // Go back to login page
            driver.findElement(By.className("go-to-login-btn")).click();
            Thread.sleep(1000);

            // Relogin
            driver.findElement(By.id("inputUsername")).sendKeys(username);
            driver.findElement(By.name("inputPassword")).sendKeys(password);
            Thread.sleep(1000);
            driver.findElement(By.className("submit")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("logout-btn")).click();
            driver.close();
            ;
        }
    }
}
