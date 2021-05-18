package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfluenceTestPage extends BasePage{
    public ConfluenceTestPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (id = "system-content-items-extracted")
    private WebElement restrictionButton;
    //*[@id="com-atlassian-confluence"]/div[3]/div[3]/div/div[3]/div[2]/div
    @FindBy (xpath = "//*[@id=\"com-atlassian-confluence\"]/div[3]/div[3]/div/div[3]/div[2]/div")
    private WebElement modalBox;

    public void clickOnRestrictionButton() {
        hover(restrictionButton);
        waitAndClick(restrictionButton);
        WaitForModal(modalBox);
        driver.switchTo().activeElement();
    }

    @FindBy (xpath = "//*[@id=\"com-atlassian-confluence\"]/div[3]/div[3]/div/div[3]/div[2]/div/div/div[1]/div/div/div[1]")
    private WebElement permissionDropDown;
    public void clickOnPermisssionDropDown() {
        hover(permissionDropDown);
        waitAndClick(permissionDropDown);
    }

    @FindBy (xpath = "//*[@id=\"react-select-2-option-0\"]")
    private WebElement dropDownOption0;
    public void  clickOnDropDownOption0(){
        hover(dropDownOption0);
        waitAndClick(dropDownOption0);
    }

    @FindBy (xpath = "//*[@id=\"com-atlassian-confluence\"]/div[3]/div[3]/div/div[3]/div[2]/div/div/div[2]/footer/div/div[2]/button")
    private WebElement applyButton;
    public void clickOnApply() {
        hover(applyButton);
        waitAndClick(applyButton);
    }
}
