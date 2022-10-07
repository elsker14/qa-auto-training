package interviews.threatConnect;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Assignment {
    public WebDriver driver;
    public Actions actions;

    @BeforeSuite(alwaysRun = true)
    public void setupTestClass() {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        driver = new ChromeDriver();
        actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.airbnb.com/");
    }

    @AfterSuite(alwaysRun = true)
    public void teardownTestClass() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test(description = "", priority = 1)
    public void test() throws InterruptedException {
        // Step 1
        driver.findElement(By.xpath("//div[text()='Anywhere']//parent::button[@data-index='0']")).click();

        // Step 2
        driver.findElement(By.id("bigsearch-query-location-input")).sendKeys("Spain");
        driver.findElement(By.xpath("//div[@data-index='0']//div[text()='Spain']")).click();

        String startDate = getDate(0);
        String endDate = getDate(4);
        driver.findElement(By.xpath(String.format("//div[@data-testid='calendar-day-%s']", startDate))).click();
        driver.findElement(By.xpath(String.format("//div[@data-testid='calendar-day-%s']", endDate))).click();

        // Step 3
        String lastDisabledDate = getDate(-1);
        Assert.assertEquals(driver.findElement(By.xpath(String.format("//div[@data-testid='calendar-day-%s']//parent::td", lastDisabledDate))).getAttribute("aria-disabled"),
                Boolean.TRUE.toString(),
                "Date is enabled!");
        for (int i = 1; i <= 3; i++) {
            String selectedDateRangeMessage = "Part of selected date range.";
            String selectedDateInRange = getDate(i);
            String textFromUI = driver.findElement(By.xpath(String.format("//div[@data-testid='calendar-day-%s']//following-sibling::span", selectedDateInRange))).getText();
            Assert.assertEquals(textFromUI,
                    selectedDateRangeMessage,
                    "Day is out of interval");
        }

        // Step 4
        driver.findElement(By.xpath("//div[@data-testid='structured-search-input-field-dates-panel']//button[2]")).click();
        driver.findElement(By.id("flexible_trip_lengths-weekend_trip")).click();

        // Step 5
        driver.findElement(By.xpath("//div[@data-testid='structured-search-input-field-dates-panel']//button[2]//parent::div//button[1]")).click();
        driver.findElement(By.xpath("//button[@data-testid='structured-search-input-search-button']")).click();
        Thread.sleep(2000);

        List<WebElement> listOfPrices = driver.findElements(By.xpath("//*[contains(text(), 'night')]"));
        String[] words = listOfPrices.get(2).getText().split(" ");
        /*
        // Hovering not working
        actions
                .moveToElement(listOfPrices.get(0))
                .build().perform();
        WebElement priceOnMap = driver.findElement(By.xpath("//span[contains(text(), 'selected')]"));
        Assert.assertTrue(priceOnMap.isDisplayed(), "Price is not selected on map");
        */

        // Step 6
        driver.findElement(By.xpath(String.format("//span[contains(text(), '%s')]//ancestor::button", words[1]))).click();
        Thread.sleep(2000);
    }

    private String getDate(int offset) {
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();

        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, offset);
        date = calendar.getTime();

        return simpleDateFormat.format(date);
    }
}
