package seleniumTraining.section14selenium4LatestFeatures;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class PartialScreenshotTest {

    @Test
    public void test() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        Thread.sleep(2000);

        WebElement name = driver.findElement(By.name("name"));
        name.sendKeys("Checu Miaw");
        File partialSS = name.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(partialSS, new File("src/main/resources/screenshots/partialSS.png"));

        File fullSS = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(fullSS, new File("src/main/resources/screenshots/fullSS.png"));

        driver.quit();
    }
}
