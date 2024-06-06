package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtils {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // Correct path to chromedriver.exe
        	ChromeOptions chromeoption=new ChromeOptions();
        	chromeoption.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Abdul Mohammed\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver(chromeoption);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
