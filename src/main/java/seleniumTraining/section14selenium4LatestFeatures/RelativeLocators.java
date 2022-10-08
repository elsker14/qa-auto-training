package seleniumTraining.section14selenium4LatestFeatures;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocators {

    @Test
    public void relativeLocatorsTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        // relative locators: above, below, toLeftOf, toRightOf
        // above
        WebElement nameEditBox = driver.findElement(By.cssSelector("[name='name']"));
        String text = driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText();
        System.out.println(text);
        Thread.sleep(2000);

        // below
        WebElement dobText = driver.findElement(By.xpath("//label[@for='dateofBirth']"));
        driver.findElement(with(By.tagName("input")).below(dobText)).click();
        Thread.sleep(2000);

        // toLeftOf
        WebElement checkboxRightText = driver.findElement(By.xpath("//label[@for='exampleCheck1']"));
        driver.findElement(with(By.tagName("input")).toLeftOf(checkboxRightText)).click();
        Thread.sleep(2000);

        // toRightOf
        WebElement checkboxLeft = driver.findElement(By.xpath("//input[@id='exampleCheck1']"));
        text = driver.findElement(with(By.tagName("label")).toRightOf(checkboxLeft)).getText();
        System.out.println(text);

        WebElement rdb = driver.findElement(By.id("inlineRadio1"));
        text = driver.findElement(with(By.tagName("label")).toRightOf(rdb)).getText();
        System.out.println(text);
    }

}
