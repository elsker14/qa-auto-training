package seleniumTraining.section13miscellaneousTopics;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Test
public class ChromeProxiesPathsPlugins {
    public void chromeProxiesPathsPlugins() {
        // Always set chrome options before invoking the driver, so it know how to configure itself
        ChromeOptions chromeOptions = new ChromeOptions();

        // To set extension you have to download the extension file and put the path
        // chromeOptions.addExtensions("put extension path here from your pc");

        // Set proxy
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("ip address:4444");
        chromeOptions.setCapability("proxy", proxy);

        // Block chrome pop ups: like location to reveal, microphone or video camera to allow, etc
        chromeOptions.setExperimentalOption("excludeSwitches",
                Arrays.asList("disable-popup-blocking"));

        // Set custom path to download stuff
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "/directory/path");
        chromeOptions.setExperimentalOption("prefs", prefs);

        System.setProperty("webdriver.chrome.driver", "J:/Coding/Practice/UkrainaPower/chromedriver.exe");

        WebDriver driver = new ChromeDriver(chromeOptions);
        Actions actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://expired.badssl.com/");
    }
}
