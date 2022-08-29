package seleniumTraining.section11and12exercises;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;

// Selenium has no scrolling implementation
@Test
public class Scrolling {
    public void scrolling() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Scrolling is done through JavaScript Executor
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0, 500)");

        // To scroll inside a web element we focus on it and use javascriptExecutor
        Thread.sleep(3000);
        javascriptExecutor.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");

        // End test
        Thread.sleep(3000);
        driver.quit();
    }
}
