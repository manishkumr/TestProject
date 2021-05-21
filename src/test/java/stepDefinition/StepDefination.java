package stepDefinition;

import base.BaseDriver;
import config.LoadConfiguration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import pageFactory.AtlassianHomePage;
import pageFactory.AtlassianLoginPage;
import pageFactory.ConfluenceHomePage;
import pageFactory.ConfluenceTestPage;

import java.io.File;

public class StepDefination extends BaseDriver {
    private String atlassianLoginURl;
    private String confluenceURL;
    private String testConfluencePageURL;
    private String restrictionoption;
    protected AtlassianLoginPage atlassianLoginPage;
    protected AtlassianHomePage atlassianHomePage;
    protected ConfluenceHomePage confluenceHomePage;
    protected ConfluenceTestPage confluenceTestPage;

    @Before
    public void startUp() {
        setDriver(LoadConfiguration.getProperty("driverPath"));
        atlassianLoginPage = PageFactory.initElements(getDriver(), AtlassianLoginPage.class);
        atlassianHomePage = PageFactory.initElements(getDriver(), AtlassianHomePage.class);
        confluenceHomePage = PageFactory.initElements(getDriver(), ConfluenceHomePage.class);
        confluenceTestPage = PageFactory.initElements(getDriver(), ConfluenceTestPage.class);
    }

    @After
    public void cleanUp() {
        quitDriver();
    }

    @Given("^user is on confluence login page$")
    public void userIsOnAtlassianLoginPage() {
        atlassianLoginURl = LoadConfiguration.getProperty("atlassianLoginURL");
        getDriver().get(atlassianLoginURl);
    }

    public void userEntersUsernameAndPassword(String username, String password) {
        atlassianLoginPage.enterUserName(username);
        //click continue
        atlassianLoginPage.clickOnContinue();
        // enter password
        atlassianLoginPage.enterPassword(password);


    }

    public void userClickOnLoginButton() {
        // click on login
        atlassianLoginPage.clickOnLogin();
    }


    @Then("verify user is logged in")
    public void verifyUserIsLoggedIn() {
        atlassianHomePage.waitForPageTitle("Atlassian | Start page");
        Assert.assertEquals("Atlassian | Start page", getDriver().getTitle());
    }

    public void navigateToConfluence() {
        confluenceURL = LoadConfiguration.getProperty("confluenceURL");
        getDriver().get(confluenceURL);
    }

    public void verifyUserIsOnTestConfluencePage() {
        String configuredTestConfluencePageTitle = LoadConfiguration.getProperty("testConfluencePageTitle");
        String actualTestConfluencePageTitle = confluenceTestPage.getTitleText();
        Assert.assertEquals(configuredTestConfluencePageTitle, actualTestConfluencePageTitle);
    }

    @Given("User \"([^\"]*)\" navigates to test confluence page")
    public void userIsOnTestConfluencePage() {
        testConfluencePageURL = LoadConfiguration.getProperty("testConfluencePageURL");
        getDriver().get(testConfluencePageURL);
        verifyUserIsOnTestConfluencePage();
    }

    @And("^User \"([^\"]*)\" sets restriction to \"([^\"]*)\" for the test confluence page$")
    public void setRestriction(String username, String permissionType) {
        restrictionoption = permissionType;
        //Anyone on confluence can view and edit
        confluenceTestPage.clickOnRestrictionButton();
        confluenceTestPage.clickOnRestrictionDropDown();
        //select from dropdown
        confluenceTestPage.clickOnDropDownOption(permissionType);
        // click on apply
        confluenceTestPage.clickOnApply();
    }

    @Given ("^User log in to confluence with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void loginToConfluence(String username, String password) {
        userIsOnAtlassianLoginPage();
        userEntersUsernameAndPassword(username,password);
        userClickOnLoginButton();

    }
    @And ("^User \"([^\"]*)\" logs out$")
    public void logOutOfConfluence(String username) {
        confluenceTestPage.clickOnProfileButton();
        // click on log out button
        confluenceTestPage.clickOnLogOutMenuItem();
        confluenceTestPage.clickOnConfirmLogout();

    }

    @Then("^verify user is logged in as username \"([^\"]*)\"$")
    public void verifyLoggedInUser(String displayName) {
        // click on profile button
        confluenceHomePage.clickOnProfileButton();
        // confirm display name is username
        String currentUserName = confluenceHomePage.verifyProfileName();
        Assert.assertEquals(displayName, currentUserName);
    }

    @Then("^verify user \"([^\"]*)\" \"([^\"]*)\" edit button")
    public void verifyUserCanViewEditButton(String username, String viewType) {
        Boolean buttonStatus = confluenceTestPage.isViewButtonPresent();
        if(viewType.equals("can see")) {
            Assert.assertTrue(buttonStatus);
        }
        if(viewType.equals("cannot see")) {
            Assert.assertTrue(!buttonStatus);
        }
    }
    @And ("^User \"([^\"]*)\" sets \"([^\"]*)\" permission to user \"([^\"]*)\"$")
    //
    public void setUserPermissions(String loggedInUser, String permissionType,String userToAdd ){
        confluenceTestPage.clickOnRestrictionButton();
        confluenceTestPage.addUser(userToAdd, permissionType, restrictionoption);
        confluenceTestPage.clickOnApply();
    }

    @Then("^User sees \"([^\"]*)\" text$")
    public void verifyUserSeesText(String expectedText) {
        String displayedText = confluenceTestPage.verifyRestrictedText();
        Assert.assertEquals(displayedText, expectedText);
    }

    @When("User tries to navigate to test confluence page")
    public void userTriesToNavigateToTestConfluencePage() {
        testConfluencePageURL = LoadConfiguration.getProperty("testConfluencePageURL");
        getDriver().get(testConfluencePageURL);
    }

}
