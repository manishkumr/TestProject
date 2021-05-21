package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseDriver {

    private WebDriver driver;

    public WebDriver setDriver(String driverLocation) {
        System.setProperty("webdriver.chrome.driver", driverLocation);
        System.setProperty("webdriver.chrome.driver", driverLocation);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        return driver;
    }
    public WebDriver getDriver() {
        return driver;
    }
    public void quitDriver() {
        driver.quit();
    }


}
