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

public class FilterWebTable {

    @Test
    public void test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        Thread.sleep(1000);

        /* Check that all found veggies contain the searched string */
        String searchedString = "ri";
        driver.findElement(By.id("search-field")).sendKeys(searchedString);

        List<WebElement> allSearchedVeggies = driver.findElements(By.xpath("//tr//td[1]"));
        List<String> filteredVeggies = allSearchedVeggies.stream().map(s -> s.getText().toLowerCase()).filter(s -> s.contains(searchedString)).collect(Collectors.toList());
        Assert.assertEquals(allSearchedVeggies.size(), filteredVeggies.size(), "Size is different");

        System.out.print("All searched veggies: ");
        allSearchedVeggies.forEach(s -> System.out.print(s.getText().toLowerCase() + " "));
        System.out.println();
        System.out.print("Filtered veggies: ");
        filteredVeggies.forEach(s -> System.out.print(s + " "));

        driver.quit();
    }
}
