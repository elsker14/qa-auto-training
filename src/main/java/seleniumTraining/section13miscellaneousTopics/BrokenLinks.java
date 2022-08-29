package seleniumTraining.section13miscellaneousTopics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

@Test
public class BrokenLinks {
    public void brokenLinks() throws IOException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Java methods will call URLs and get you the status code
        // Step 1 : get all urls to get all links
        String url = driver.findElement(By.cssSelector("a[href*='soapui']")).getAttribute("href");
        System.out.println(url);

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("HEAD");
        conn.connect();

        // if status code > 400 then URL is not working -> link is broken
        int responseCode = conn.getResponseCode();
        System.out.println("response code: " + responseCode);

        // End Test
        driver.quit();
    }
}
