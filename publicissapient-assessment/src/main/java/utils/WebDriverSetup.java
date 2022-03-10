package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

public class WebDriverSetup {

    public WebDriver getDriver(Properties properties) {
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chromedriver.path"));
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

}
