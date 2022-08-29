package seleniumTraining.section10AjaxMouseInteractions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

@Test
public class AssignmentFrame {
    public void assignmentFrame() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/");
        Thread.sleep(2000);

        // Go to Nested frames
        WebElement multipleWindows = driver.findElement(By.linkText("Nested Frames"));
        actions.scrollToElement(multipleWindows).build().perform();
        Assert.assertTrue(multipleWindows.isDisplayed());
        multipleWindows.click();

        // Find out how many frames we have
        System.out.println("1st layer of frames has: " + driver.findElements(By.tagName("frame")).size() + " child frames");

        // Switch to frame-top from 1st layer
        driver.switchTo().frame(0);
        System.out.println("2nd layer of frames has: " + driver.findElements(By.tagName("frame")).size() + " child frames");

        // Switch to frame-middle from 2nd layer
        driver.switchTo().frame(driver.findElement(By.name("frame-middle")));

        // Print text from that frame
        System.out.println(driver.findElement(By.id("content")).getText());

        // End test
        Thread.sleep(2000);
        driver.quit();
    }
}
