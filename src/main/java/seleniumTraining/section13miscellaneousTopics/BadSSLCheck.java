package seleniumTraining.section13miscellaneousTopics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;

@Test
public class BadSSLCheck {

    public void badSSLCheck() throws InterruptedException {
        // When you come across bad ssl certifications, accept them as they are
        // Always set chrome options before invoking the driver, so it know how to configure itself
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);

        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");

        WebDriver driver = new ChromeDriver(chromeOptions);
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://expired.badssl.com/");

        System.out.println(driver.getTitle());

        // End test
        Thread.sleep(2000);
        driver.quit();
    }
}
