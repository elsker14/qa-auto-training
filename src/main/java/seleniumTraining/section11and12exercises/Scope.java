package seleniumTraining.section11and12exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;


public class Scope {

    // 1. Give me the count of links on the page -> all WebElements with tagName: a
    @Test(priority = 1)
    public void countAllLinksOnPage() {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Calculation of all the links
        System.out.println("Nr of all links found on page: " + driver.findElements(By.tagName("a")).size());

        // End test
        driver.quit();
    }

    // 2. Give me the count of links in the footer section
    @Test(priority = 2)
    public void countAllLinksOnFooterSection() {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Scroll until you find footer id
        actions.scrollToElement(driver.findElement(By.id("gf-BIG")));

        // Calculation of footer links
        System.out.println("Nr of all links found on footer: " + driver.findElements(By.xpath("//div[@id='gf-BIG']//a")).size());

        // End test
        driver.quit();
    }

    // 3. Give me the count of links of the first column in the footer section
    @Test(priority = 3)
    public void countAllLinksFromFirstColumnOnFooterSection() {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        System.out.println("Nr of all links of the 1st column found on footer (xpath): " + driver.findElements(By.xpath("//div[@id='gf-BIG']//table//td[1]//a")).size());
        System.out.println("Nr of all links of the 1st column found on footer (cssSelector): " + driver.findElements(By.cssSelector("div[id=gf-BIG] table td:nth-child(1) a")).size());

        driver.quit();
    }

    // 4. Click on each link in the column and check if the pages are opening
    @Test(priority = 4)
    public void clickOnAllLinksAndCheckIfTheyWork() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        List<WebElement> allFooterLinksInFirstColumnArray = driver.findElements(By.xpath("//div[@id='gf-BIG']//table//td[1]//a"));
        int sizeOfFooterLinksInFirstColumn = allFooterLinksInFirstColumnArray.size();
        for (int i = 1; i < sizeOfFooterLinksInFirstColumn; i++) {
            String clickOnLinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);

            allFooterLinksInFirstColumnArray.get(i).sendKeys(clickOnLinkTab);
            Thread.sleep(3000);
        }

        Set<String> allOpenedWindows = driver.getWindowHandles();
        for (String current : allOpenedWindows) {
            driver.switchTo().window(current);
            Thread.sleep(3000);
            System.out.println(driver.getTitle());
        }


        driver.quit();
    }
}
