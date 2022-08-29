package seleniumTraining.section13miscellaneousTopics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

@Test
public class ManageCookies {
    public void manageCookies() {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().deleteAllCookies();
        driver.manage().deleteCookieNamed("Sandel Prajiturica");
//        driver.manage().addCookie(new Cookie("prajiturica");

        driver.get("https://www.google.com");
    }
}
