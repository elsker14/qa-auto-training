package seleniumTraining.section7automateWebElems;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class E2E {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // Click radio button
        driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();

        // Select From-To Cities
        driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']")).click();
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']//a[@value='DEL']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='MAA']")).click();

        // Select depart and return date
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("ctl00$mainContent$view_date2")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='22']//ancestor::table[@class='ui-datepicker-calendar']")).click();

        // PASSENGERS DROPDOWN:
        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            driver.findElement(By.xpath("//span[@id='hrefIncAdt']")).click();
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[@id='hrefDecAdt']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("btnclosepaxoption")).click();

        // Select Currency
        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByIndex(3);

        // Press search
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='submit']//parent::span[@class='btn-find-flight-home']")).click();
    }
}
