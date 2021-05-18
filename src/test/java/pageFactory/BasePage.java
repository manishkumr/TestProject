package pageFactory;

import config.LoadConfiguration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    public long defaultTimeOut;

    public BasePage(WebDriver driver){
        this.driver = driver;
        defaultTimeOut=Long.parseLong(LoadConfiguration.getProperty("defaultTimeOut"));
    }
    public void waitForElementPresent(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeOut);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitAndEnterText(WebElement element,String text)
    {
        waitForElementPresent(element);
        element.clear();
        element.sendKeys(text);
    }
    public void waitAndClick(WebElement element)
    {
        waitForElementPresent(element);
        element.click();
        waitForPageLoad();
    }
    public void hover(WebElement element)
    {
        waitForElementPresent(element);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }
    public void waitForPageLoad()
    {
        Object wait = new WebDriverWait(driver, 30).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));;

    }
    public void waitForPageTitle(String title)
    {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return (driver.getTitle().equalsIgnoreCase(title));
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
    public void WaitForModal(WebElement modal)
    {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(modal));
    }
}
