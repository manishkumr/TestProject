package pageFactory;

import org.openqa.selenium.NoSuchElementException;
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
        waitForModal(modalBox);
        driver.switchTo().activeElement();
    }

    @FindBy (xpath = "//*[@id=\"com-atlassian-confluence\"]/div[3]/div[3]/div/div[3]/div[2]/div/div/div[1]/div/div/div[1]")
    private WebElement restrictionDropDown;
    public void clickOnRestrictionDropDown() {
        waitForElementPresent(restrictionDropDown);
        hover(restrictionDropDown);
        waitAndClick(restrictionDropDown);
    }

    @FindBy (xpath = "//*[@id=\"react-select-2-option-0\"]")
    private WebElement dropDownOption0;
    @FindBy (xpath = "//*[@id=\"react-select-2-option-1\"]")
    private WebElement dropDownOption1;
    @FindBy (xpath = "//*[@id=\"react-select-2-option-2\"]")
    private WebElement dropDownOption2;
    public void clickOnDropDownOption(String permissionType){
        if(permissionType.equals("Anyone on confluence can view and edit")){
            hover(dropDownOption0);
            waitAndClick(dropDownOption0);
        }
        if(permissionType.equals("Anyone on Confluence can view, some can edit")) {
            hover(dropDownOption1);
            waitAndClick(dropDownOption1);
        }
        if(permissionType.equals("Only specific people can view and edit")) {
            hover(dropDownOption2);
            waitAndClick(dropDownOption2);
        }

    }

    @FindBy (xpath = "/html/body/div[3]/div[3]/div/div[3]/div[2]/div/div/div[2]/footer/div/div[2]/button")
    private WebElement applyButton;
    public void clickOnApply() {
        hover(applyButton);
        waitAndClick(applyButton);
    }

    @FindBy (xpath = "//*[@id=\"title-text\"]")
    private WebElement titleText;
    public String getTitleText() {
        waitForElementPresent(titleText);
        return titleText.getText();
    }

    @FindBy (xpath = "//*[@id=\"AkTopNav\"]/div[1]/header/div/span[3]")
    private WebElement profileButton;
    public void clickOnProfileButton() {
        hover(profileButton);
        waitAndClick(profileButton);
        driver.switchTo().activeElement();
    }

    @FindBy (xpath = "/html/body/div[3]/div[3]/div/div/div/div/div[3]/a")
    private WebElement logOutMenuItem;
    public void clickOnLogOutMenuItem(){
        driver.switchTo().activeElement();
        hover(logOutMenuItem);
        waitAndClick(logOutMenuItem);
    }
    @FindBy (xpath = "//*[@id=\"logout-submit\"]")
    private WebElement logOutButton;
    public void clickOnConfirmLogout() {
        hover(logOutButton);
        waitAndClick(logOutButton);
    }
    @FindBy (xpath = "//*[@id=\"editPageLink\"]")
    private WebElement editButton;
    public Boolean isViewButtonPresent() {
        try {
            editButton.isDisplayed();
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }
    @FindBy (xpath = "//*[@id=\"react-select-restrictions:user-and-group-search:user-and-group-picker-input\"]")
    private WebElement userSearchBox;
    @FindBy (id = "react-select-restrictions:user-and-group-search:user-and-group-picker-option-0")
    private WebElement selectedUser;
    @FindBy (xpath = "/html/body/div[3]/div[3]/div/div[3]/div[2]/div/div/div[1]/div/div/div[2]/div/div/div[3]/button")
    private WebElement addButton;
    @FindBy (xpath = "//html/body/div[3]/div[3]/div/div[3]/div[2]/div/div/div[1]/div/div/div[2]/div/div/div[2]/div/div[2]/div/div[1]")
    private WebElement canViewOption;
    @FindBy (xpath = "/html/body/div[3]/div[3]/div/div[3]/div[2]/div/div/div[1]/div/div/div[2]/div/div/div[2]/div/div[2]/div/div[2]")
    private WebElement canEditOption;
    @FindBy(xpath = "//*[@id=\"com-atlassian-confluence\"]/div[3]/div[3]/div/div[3]/div[2]/div/div/div[1]/div/div/div[2]/div/div/div[2]/div")
    private WebElement permissionDropDown;

    public void addUser(String userToAdd, String permissionType, String restrictionOption) {
        driver.switchTo().activeElement();
        waitForElementClickable(userSearchBox);
        hover(userSearchBox);
        userSearchBox.sendKeys(userToAdd);
        selectedUser.click();
        // select permission Type
        hover(permissionDropDown);
        waitAndClick(permissionDropDown);
        if(permissionType.equals("can view")){
            hover(canViewOption);
            waitAndClick(canViewOption);
        }
        if(permissionType.equals("can edit") &&
                !restrictionOption.equals("Anyone on Confluence can view, some can edit")){
            waitAndClick(canEditOption);
        }

        //click on Add
        waitForElementClickable(addButton);
        hover(addButton);
        waitAndClick(addButton);
        clickOnApply();

    }
    @FindBy (xpath = "//*[@id=\"content-body\"]/div/div/div/div/div[1]/div[2]/div[1]/span")
    private WebElement restrictedSpan;
    public String verifyRestrictedText() {
        return getElementText(restrictedSpan);
    }
}
