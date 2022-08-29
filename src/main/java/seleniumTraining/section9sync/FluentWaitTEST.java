package seleniumTraining.section9sync;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

@Test
public class FluentWaitTEST {
    public void fluentWaitTest() {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        // Press Start button
        driver.findElement(By.cssSelector("[id='start'] button")).click();

        // Implement FluentWait to wait until loading is done
        // Timeout of 30 seconds, and every 3 seconds it will check if loading is done
        // During this timeout we ignore the exception NoSuchElement
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);

        WebElement foo = fluentWait.until(driver1 -> {
            if (driver1.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed())
                return driver1.findElement(By.cssSelector("[id='finish'] h4"));
            else
                return null;
        });
        Assert.assertTrue(driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed());
        System.out.println(driver.findElement(By.cssSelector("[id='finish'] h4")).getText());

        driver.quit();

    }
}
