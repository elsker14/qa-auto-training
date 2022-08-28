package seleniumTraining.section5and6locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SiblingParentTraverseLocators {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.xpath("//header/div/button[1]//following-sibling::button[1]")).click();
        System.out.println(driver.findElement(By.xpath("//header/div/button[1]//following-sibling::button[1]")).getText());
        System.out.println(driver.findElement(By.xpath("//header/div/button[2]//parent::div//button[2]")).getText());


    }
}
