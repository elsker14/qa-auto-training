package seleniumTraining.section7automateWebElems.calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class Calendar {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // Check if second date is disabled
        Assert.assertTrue(driver.findElement(By.id("ctl00_mainContent_view_date1")).isEnabled(), "Depart date box is not enabled");
//        Assert.assertFalse(driver.findElement(By.id("ctl00_mainContent_view_date2")).isEnabled(), "Return date box is not enabled");

        // isEnabled not working properly in the latest UI websites bc web element look disabled, but when clicking they activate
        // they should remain inactive
        // So to check this we must assert which attribute changes -> usually style
        System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
        System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));

        if (driver.findElement(By.id("Div1")).getAttribute("style").contains("1")) {
            System.out.println("It's enabled");
            Assert.assertTrue(true);
        } else {
            System.out.println("It's not enabled");
            Assert.assertTrue(false);
        }

        // Pick a date from calendar
        driver.findElement(By.cssSelector(".ui-datepicker-trigger")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".ui-datepicker-trigger")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='15']//parent::*[@data-month='8']")).click();
    }
}
