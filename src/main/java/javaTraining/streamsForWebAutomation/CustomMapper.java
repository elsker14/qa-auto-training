package javaTraining.streamsForWebAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CustomMapper {

    @Test
    public void test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        Thread.sleep(1000);

        // Click on name column to sort elements
        driver.findElement(By.xpath("//th[contains(@aria-label, \"Veg/fruit name\")]")).click();
        Thread.sleep(2000);

        // Get list of all web elements on the name column
        List<WebElement> originalWEList = driver.findElements(By.xpath("//tbody//tr//td[1]"));

        // Get price of veggie called Beans
        List<String> beansPrice = originalWEList.stream().filter(s -> s.getText().contains("Beans")).map(this::getPriceVeggie).collect(Collectors.toList());
        beansPrice.forEach(s -> System.out.println(s));

        driver.quit();
    }

    private String getPriceVeggie(WebElement temp) {
        return temp.findElement(By.xpath("following-sibling::td[1]")).getText();
    }
}
