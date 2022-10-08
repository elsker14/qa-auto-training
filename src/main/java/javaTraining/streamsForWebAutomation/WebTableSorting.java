package javaTraining.streamsForWebAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class WebTableSorting {

    @Test
    public void test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        Thread.sleep(1000);

        /* Step 1 - Click on column */
        driver.findElement(By.xpath("//th[contains(@aria-label, \"Veg/fruit name\")]")).click();
        Thread.sleep(1000);

        /* Step 2 - Capture all WebElements into a list */
        List<WebElement> originalWEList = driver.findElements(By.xpath("//tbody//tr//td[1]"));

        /* Step 3 - Capture text of all WebElements into a new list called originalTextsList */
        List<String> originalTextsList = originalWEList.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> originalTextsList2 = originalWEList.stream().map(s -> s.getText()).collect(Collectors.toList());
        System.out.print("Original List: ");
        originalTextsList.forEach(s -> System.out.print(s + " "));
        System.out.println();

        /* Step 4 - Sort original list into a new list called sortedTextsList */
        List<String> sortedTextsList = originalTextsList.stream().sorted().collect(Collectors.toList());
        System.out.print("Sorted List: ");
        sortedTextsList.forEach(s -> System.out.print(s + " "));
        System.out.println();

        /* Step 5 - Compare originalTextsList vs sortedTextsList */
        Assert.assertEquals(originalTextsList, sortedTextsList, "Lists are not identical!");
        Assert.assertTrue(originalTextsList.equals(sortedTextsList), "Lists are not identical!");

        driver.quit();
    }
}
