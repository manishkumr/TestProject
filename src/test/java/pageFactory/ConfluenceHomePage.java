package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfluenceHomePage extends BasePage{
    public ConfluenceHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//*[@id=\"root\"]/div[3]/div/div[2]/header/div/div[5]/button")
    private WebElement profileButton;
    public void clickOnProfileButton() {
        waitForPageLoad();
        hover(profileButton);
        waitAndClick(profileButton);
    }
    @FindBy (xpath = "/html/body/div[3]/div[2]/div/div/div[1]/div")
    private WebElement profileNameText;
    public String verifyProfileName(){
        return getElementText(profileNameText);
    }


}
