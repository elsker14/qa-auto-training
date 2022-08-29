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
public class AssignmentAutocompleteDropdown {
    public void assignmentAutocompleteDropdown() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        driver.findElement(By.cssSelector("input[id='autocomplete']")).sendKeys("ro");
        List<WebElement> courseOptionsList = driver.findElements(By.cssSelector("li[class='ui-menu-item'] div"));
        for (WebElement we : courseOptionsList)
            System.out.println(we.getText());

        for (WebElement we : courseOptionsList)
            if (we.getText().equals("Romania")) {
                we.click();
                break;
            }

        // End test
        Thread.sleep(3000);
        driver.quit();
    }
}
