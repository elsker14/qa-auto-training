package seleniumTraining.section7automateWebElems.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class AutoSuggestiveDropDown {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("autosuggest")).sendKeys("ind");
        Thread.sleep(3000);

        // My code (only text)
        List<WebElement> myOptionsList = driver.findElements(By.id("ui-id-1"));
        for (WebElement we : myOptionsList)
            System.out.println(we.getText());

        System.out.println();

        // Course code (clickable option)
        List<WebElement> courseOptionsList = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
        for (WebElement we : courseOptionsList)
            System.out.println(we.getText());

        // Click option
        for (WebElement we : courseOptionsList)
            if (we.getText().equalsIgnoreCase("India")) {
                we.click();
                break;
            }
    }
}
