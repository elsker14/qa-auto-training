package seleniumTraining.section10AjaxMouseInteractions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

@Test
public class AssignmentActionWindow {
    public void assignmentActionWindow() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/");
        Thread.sleep(2000);

        // Go to Multiple Windows page
        WebElement multipleWindows = driver.findElement(By.linkText("Multiple Windows"));
        actions.scrollToElement(multipleWindows).build().perform();
        Assert.assertTrue(multipleWindows.isDisplayed());
        Thread.sleep(2000);
        multipleWindows.click();

        // Click "Click Here" link
        driver.findElement(By.linkText("Click Here")).click();
        Set<String> openedWindows = driver.getWindowHandles();
        Iterator<String> it = openedWindows.iterator();
        String parentId = it.next();
        String childId = it.next();
        Thread.sleep(2000);
        driver.switchTo().window(childId);

        // Get text from child page
        System.out.println(driver.findElement(By.cssSelector(".example")).getText());
        Thread.sleep(2000);
        driver.switchTo().window(parentId);
        System.out.println(driver.findElement(By.cssSelector(".example")).getText().split("\n")[0]);

        // End test
        driver.quit();
    }
}
