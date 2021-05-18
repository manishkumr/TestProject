package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AtlassianLoginPage extends BasePage{
    public AtlassianLoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy (id = "username")
    private WebElement usernameTextBox;

    public void enterUserName(String text) {
        waitAndEnterText(usernameTextBox, text);
    }

    @FindBy (id = "login-submit")
    private WebElement continueButton;

    public void clickOnContinue() {
        waitAndClick(continueButton);
    }

    @FindBy (id = "password")
    private WebElement passwordTextBox;
    public void enterPassword(String password){
        waitAndEnterText(passwordTextBox,password);
    }

    @FindBy (id = "login-submit")
    private WebElement loginButton;
    public void clickOnLogin() {
        waitAndClick(loginButton);
    }
}
