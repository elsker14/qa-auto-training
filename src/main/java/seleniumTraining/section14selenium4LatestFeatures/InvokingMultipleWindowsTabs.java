package seleniumTraining.section14selenium4LatestFeatures;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class InvokingMultipleWindowsTabs {

    @Test
    public void test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        Thread.sleep(2000);

        driver.switchTo().newWindow(WindowType.TAB).get("https://rahulshettyacademy.com");
        Thread.sleep(2000);

        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> it = windowHandles.iterator();
        String parentId = it.next();
        String childId = it.next();

        driver.switchTo().window(parentId);
        Thread.sleep(2000);
        driver.switchTo().window(childId);

        List<WebElement> allCoursesOnPage = driver.findElements(By.cssSelector("h2 a[href*='https://courses.rahulshettyacademy.com/p']"));
        String titleFirstCourse = allCoursesOnPage.get(1).getText();
        System.out.println(titleFirstCourse);
        Thread.sleep(2000);

        driver.switchTo().window(parentId);
        driver.findElement(By.name("name")).sendKeys(titleFirstCourse);
        Thread.sleep(2000);
    }
}
