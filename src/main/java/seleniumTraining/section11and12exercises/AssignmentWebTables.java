package seleniumTraining.section11and12exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

@Test
public class AssignmentWebTables {
    public void assignmentWebTables() {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Nr of rows
        System.out.println("Nr of rows: " + driver.findElements(By.xpath("//table[@id='product' and @name='courses']//tr")).size());

        // Nr of columns
        System.out.println("Nr of columns: " + driver.findElements(By.xpath("//table[@id='product' and @name='courses']//tr//th")).size());

        // Content on 2nd row
        List<WebElement> contentOfRow = driver.findElements(By.xpath("//table[@id='product' and @name='courses']//tr[3]//td"));
        System.out.println("Nr of elements from 2nd row: " + contentOfRow.size());
        System.out.println("Content from 2nd row:");
        for (WebElement we : contentOfRow) {
            System.out.println(we.getText());
        }

        // End test
        driver.quit();
    }
}
