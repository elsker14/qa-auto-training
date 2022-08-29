package seleniumTraining.section13miscellaneousTopics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

@Test
public class AllBrokenLinks {
    public void allBrokenLinks() throws IOException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Find all links
        SoftAssert softAssert = new SoftAssert();
        List<WebElement> listOfLinks = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
        for (WebElement we : listOfLinks) {
            String url = we.getAttribute("href");
            System.out.println(url);

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();

            // if status code > 400 then URL is not working -> link is broken
            int responseCode = conn.getResponseCode();
            System.out.println("response code: " + responseCode);
            /*
            // hard assertion, if condition is found false -> test fails
            Assert.assertTrue(responseCode < 400,
                    "You have broken links, found: " + we.getText() + " -> " + we.getAttribute("href") + " -> " + responseCode);
            */
            // soft assertion, if condition is found false -> execution will continue and store value until end, where it will display the failure
            softAssert.assertTrue(responseCode < 400,
                    "You have broken links, found: " + we.getText() + " -> " + we.getAttribute("href") + " -> " + responseCode);
        }

        // Display all failures
        softAssert.assertAll();

        // End test
        driver.quit();
    }
}
