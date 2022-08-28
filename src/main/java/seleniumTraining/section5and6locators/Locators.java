package seleniumTraining.section5and6locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Locators {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // implicit wait = tells selenium to wait  5 seconds if an element doesn't show up on the page right away
        // globally applicable
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://rahulshettyacademy.com/locatorspractice/");

        // Locator using ID
        driver.findElement(By.id("inputUsername")).sendKeys("rahul");

        // Locator using Name
        driver.findElement(By.name("inputPassword")).sendKeys("hello1234");

        // Locator using Class Name
        driver.findElement(By.className("submit")).click();

        // Locator using CSS Selector
        System.out.println(driver.findElement(By.cssSelector("p.error")).getText());

        // Locator using Link Text
        driver.findElement(By.linkText("Forgot your password?")).click();

        // A better method is explicit wait, but for now we use Thread sleep
        // We need this because pressing on the link above puts the webpage in a changing state
        // And later when we'll try to press a button, it won't be clickable because of this instability
        // So we wait to stabilize, then pursue
        Thread.sleep(5000);

        // Locator using xPath
        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("cacamaca");

        driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).sendKeys("0753016068");
        driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).clear();
        driver.findElement(By.xpath("//form/input[3]")).sendKeys("0101010101");

        // Clear method after finding an element
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("hahaha@gmail.com");
        driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();

        // Accessing a tagname through parent-child or index relationship
        driver.findElement(By.xpath("//input[@type='text'][2]")).sendKeys("hihihi@gmail.com");
        driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
        driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("hohoho@gmail.com");

        // Different ways to get a text
        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
        System.out.println(driver.findElement(By.className("infoMsg")).getText());
        System.out.println(driver.findElement(By.cssSelector("form p")).getText());

        // Get back on Login page
        driver.findElement(By.cssSelector(".go-to-login-btn")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("#inputUsername")).sendKeys("Jijel");

        // Identify element with regular expression or partial textual match
        driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyaccademy");
        driver.findElement(By.id("chkboxOne")).click();
        driver.findElement(By.id("chkboxTwo")).click();
//        driver.findElement(By.xpath("//button[@class='submit signInBtn']")).click();
        driver.findElement(By.xpath("//button[contains(@class, 'submit')]")).click();
    }
}
