import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

/*
try mvn clean test -DtestSuite = TestSuites/tests.xml -DtestLocal=false -DtestHeadless=true
 */

public class AssignmentScope {

    @Test
    public void assignment() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // 1. Click on random checkbox
        Random random = new Random();
        List<WebElement> allCheckboxesArray = driver.findElements(By.xpath("//div[@id='checkbox-example']//label"));
        int nrOfCheckboxes = allCheckboxesArray.size();
        int indexSelectedCheckbox = random.nextInt(nrOfCheckboxes);
        allCheckboxesArray.get(indexSelectedCheckbox).findElement(By.cssSelector("input")).click();

        // 2. Take text of the selected checkbox
        String selectedCheckboxText = allCheckboxesArray.get(indexSelectedCheckbox).getText();
        System.out.println("Selected checkbox is: " + selectedCheckboxText);
        Thread.sleep(3000);

        // 3. Select same value in dropdown as you did with the checkbox

        /* 1st way */
        Select dropdown = new Select(driver.findElement(By.cssSelector("select[id='dropdown-class-example']")));
        dropdown.selectByVisibleText(selectedCheckboxText);

        /* 2nd way */
//        WebElement dropdown = driver.findElement(By.cssSelector("select[id='dropdown-class-example']"));
//        dropdown.click();
//        String optionLocator = "//option[text()='%s']";
//        Thread.sleep(1000);
//        dropdown.findElement(By.xpath(String.format(optionLocator, selectedCheckboxText))).click();
//        Thread.sleep(2000);

        // 4. Introduce selected checkbox text to Switch to Alert Example textbox
        driver.findElement(By.name("enter-name")).sendKeys(selectedCheckboxText);

        // 5. Click on Alert
        driver.findElement(By.cssSelector("#alertbtn")).click();
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        System.out.println("Alert message: " + alertMessage);
        Assert.assertTrue(alertMessage.contains(selectedCheckboxText),
                "Selected checkbox text does not appear in alert message");

        // End Test
        Thread.sleep(3000);
        driver.quit();
    }
}
