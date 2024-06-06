package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtils {

    private static WebDriver driver;

    // Get the WebDriver instance, initialize it if it is not already
    public static WebDriver getDriver() {
        if (driver == null) {
            // Correct path to chromedriver.exe
            ChromeOptions chromeoption = new ChromeOptions();
            chromeoption.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Abdul Mohammed\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver(chromeoption);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }

    // Quit the WebDriver instance and set it to null
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
