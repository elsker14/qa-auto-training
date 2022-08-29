package seleniumTraining.section10AjaxMouseInteractions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;

@Test
public class FrameTest {
    public void frameTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://jqueryui.com/draggable/");
        Thread.sleep(2000);

        /* 1st way to find the frame */
//        driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));

        /* 2nd way to find the frame */
        System.out.println(driver.findElements(By.tagName("iframe")).size());
        driver.switchTo().frame(0);

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable")); //not working because site lack of the element with id droppable
        actions.dragAndDrop(source, target).build().perform();

        // Get back from frame to default html page
        driver.switchTo().defaultContent();

        // End test
        driver.quit();
    }
}
