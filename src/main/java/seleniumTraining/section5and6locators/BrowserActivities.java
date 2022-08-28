package seleniumTraining.section5and6locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserActivities {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/locatorspractice/"); //loads until page is fully loaded
        driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice/"); // loads without wait
        driver.navigate().back(); // loads without wait
        driver.navigate().forward();
    }
}
