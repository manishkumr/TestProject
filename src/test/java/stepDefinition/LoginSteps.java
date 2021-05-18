package stepDefinition;

import base.BaseDriver;
import config.LoadConfiguration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import pageFactory.AtlassianHomePage;
import pageFactory.AtlassianLoginPage;
import pageFactory.ConfluenceHomePage;
import pageFactory.ConfluenceTestPage;

import java.io.File;

public class LoginSteps extends BaseDriver {
    private String atlassianLoginURl;
    private String confluenceURL;
    private String testConfluencePageURL;
    protected AtlassianLoginPage atlassianLoginPage;
    protected AtlassianHomePage atlassianHomePage;
    protected ConfluenceHomePage confluenceHomePage;
    protected ConfluenceTestPage confluenceTestPage;

    @Before
    public void startUp() {
        setDriver("C:\\Program Files\\chrome_driver\\chromedriver.exe");
        atlassianLoginPage = PageFactory.initElements(getDriver(), AtlassianLoginPage.class);
        atlassianHomePage = PageFactory.initElements(getDriver(), AtlassianHomePage.class);
        confluenceHomePage = PageFactory.initElements(getDriver(), ConfluenceHomePage.class);
        confluenceTestPage = PageFactory.initElements(getDriver(), ConfluenceTestPage.class);
    }

    @After
    public void cleanUp(Scenario scenario) {
        //If test fails, save the screenshot and dom for debug
        if (scenario.isFailed()) {
            try {
                File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
                // FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/results/screenshots/" + scenario.getName() + ".jpg"));
                //PrintWriter pagesrc = new PrintWriter(scenario.getName() + ".html", "UTF-8");
                //pagesrc.write(getDriver().getPageSource());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot:");
                e.printStackTrace();
            }
        }
        quitDriver();
    }
    @Given("^user is on confluence login page$")
    public void userIsOnAtlassianPage() {
        atlassianLoginURl = LoadConfiguration.getProperty("atlassianLoginURL");
        getDriver().get(atlassianLoginURl);
    }

    @And("^user logs in with username and password$")
    public void userEntersEmail() {
        atlassianLoginPage.enterUserName("blaze.meter@yahoo.com");
        //click continue
        atlassianLoginPage.clickOnContinue();
        // enter password
        atlassianLoginPage.enterPassword("admin123");
        // click on login
        atlassianLoginPage.clickOnLogin();

    }

    @Then("verify user is logged in")
    public void verifyUserIsLoggedIn() {
        atlassianHomePage.waitForPageTitle("Atlassian | Start page");
        Assert.assertEquals("Atlassian | Start page", getDriver().getTitle());
    }

    @And("user navigates to confluence page")
    public void navigateToConfluence() {
        confluenceURL = LoadConfiguration.getProperty("confluenceURL");
        getDriver().get(confluenceURL);
    }

    @Then("verify user is on confluence page")
    public void verifyUserIsOnConfluencePage() {
        confluenceHomePage.waitForPageTitle("Home - Confluence");
        Assert.assertEquals("Home - Confluence", getDriver().getTitle());
    }

    @Given("User is on test confluence page")
    public void userIsOnTestConfluencePage() {
        testConfluencePageURL = LoadConfiguration.getProperty("testConfluencePageURL");
        getDriver().get(testConfluencePageURL);
    }

    @And("user sets restriction to Anyone on confluence can view and edit")
    public void setPermission() {
        confluenceTestPage.clickOnRestrictionButton();
        confluenceTestPage.clickOnPermisssionDropDown();
        //select from dropdown
        confluenceTestPage.clickOnDropDownOption0();
        // click apply
        confluenceTestPage.clickOnApply();


    }
}
