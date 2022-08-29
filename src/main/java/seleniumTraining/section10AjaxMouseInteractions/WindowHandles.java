package seleniumTraining.section10AjaxMouseInteractions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

@Test
public class WindowHandles {
    public void windowHandles() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        Thread.sleep(2000);

        // Click on top right corner link to open a child window
        driver.findElement(By.cssSelector(".blinkingText")).click();
        Thread.sleep(2000);

        // Get all opened windows id
        Set<String> openedWindows = driver.getWindowHandles();
        Iterator<String> it = openedWindows.iterator();
        String parentId = it.next();
        String childId = it.next();

        // Play switching between windows
        Thread.sleep(2000);
        driver.switchTo().window(parentId);
        Thread.sleep(2000);
        driver.switchTo().window(childId);
        Thread.sleep(2000);
        driver.switchTo().window(parentId);
        Thread.sleep(2000);
        driver.switchTo().window(childId);

        // Take text from child window
        System.out.println(driver.findElement(By.cssSelector(".im-para.red")).getText());

        // Parse string to extract email id
        String emailId = driver.findElement(By.cssSelector(".im-para.red")).getText().split("at")[1].trim().split(" ")[0];
        System.out.println(emailId);

        // Fill in log in for with email
        Thread.sleep(2000);
        driver.switchTo().window(parentId);
        driver.findElement(By.id("username")).sendKeys(emailId);

        // End test
        Thread.sleep(2000);
        driver.quit();
    }
}
