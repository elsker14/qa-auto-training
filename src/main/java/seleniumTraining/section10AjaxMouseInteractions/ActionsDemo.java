package seleniumTraining.section10AjaxMouseInteractions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;

@Test
public class ActionsDemo {
    public void actionsDemo() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.amazon.com/");

        // Action: hover over Sign In section
        Actions actions = new Actions(driver);
        WebElement move = driver.findElement(By.cssSelector("a[id='nav-link-accountList']"));
        actions
                .moveToElement(move)
                .build().perform();
        Thread.sleep(2000);

        // Action: go to Search bar and write in capital letters something and double click on text
        actions
                .moveToElement(driver.findElement(By.id("twotabsearchtextbox")))
                .click().keyDown(Keys.SHIFT).sendKeys("cats")
                .doubleClick()
                .build().perform();
        Thread.sleep(2000);

        // Action: right click (CONTEXT CLICK) on specific element
        actions
                .moveToElement(move)
                .contextClick()
                .build().perform();
        Thread.sleep(2000);

        // End test
        driver.quit();
    }
}
