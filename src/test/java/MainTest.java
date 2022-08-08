import aquality.selenium.core.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.*;
import utils.FileUtils;
import utils.RobotClass;

public class MainTest extends BaseTest {
    @Test
    public static void createProfileAndCheckIt() throws InterruptedException {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isDisplayed(),"home page is not open");
        Logger.getInstance().info("VERY SCARY MESSAGE");

        homePage.clickOnBtn();
        GameForm gameForm = new GameForm();
        CardsCookiesNHelpForm cardsCookiesNHelpForm = new CardsCookiesNHelpForm();
        Assert.assertTrue(gameForm.isDisplayed(),"\"game\" page is not open");
        Assert.assertEquals(cardsCookiesNHelpForm.getCurrentPage(),"1 / 4","current page is not 1/4");

        gameForm.fillPassword();
        gameForm.fillMail();
        gameForm.fillDomain();
        gameForm.clickOnDropDown();
        gameForm.clickOnTC();
        gameForm.clickOnNextBtn();
        Assert.assertEquals(cardsCookiesNHelpForm.getCurrentPage(),"2 / 4","current page is not 2/4");

        InformationForm informationForm = new InformationForm();
        Assert.assertTrue(informationForm.isDisplayed(),"info page is not open");
        informationForm.selectBox();
        informationForm.uploadFile();
        RobotClass.uploadFileUsingRobot(FileUtils.getPathToResource("image.jpg"));
        informationForm.waitForImageToUpload();
        informationForm.clickNextBtn();
        Assert.assertEquals(cardsCookiesNHelpForm.getCurrentPage(),"3 / 4","current page is not 3/4");
    }
    @Test
    public static void hideHelpForm(){
        HomePage homePage = new HomePage();
        HelpForm helpForm = new HelpForm();
        Assert.assertTrue(homePage.isDisplayed(),"home page is not open");
        homePage.clickOnBtn();
        helpForm.closeHelpMenu();
        Assert.assertFalse(helpForm.isHelpBoxDisplayed(),"help box is not hidden");
    }
    @Test
    public static void acceptCookies() {
        HomePage homePage = new HomePage();
        GameForm gameForm = new GameForm();
        CardsCookiesNHelpForm cardsCookiesNHelpForm = new CardsCookiesNHelpForm();
        Assert.assertTrue(homePage.isDisplayed(),"home page is not open");
        homePage.clickOnBtn();
        Assert.assertTrue(gameForm.isDisplayed(),"\"game\"form is not open");
        cardsCookiesNHelpForm.acceptCookies();
        Assert.assertFalse(cardsCookiesNHelpForm.isCookiesBannerDisplayed(), "cookies banner did not disappear");
    }
    @Test
    public static void validateTimer(){
        HomePage homePage = new HomePage();
        CardsCookiesNHelpForm cardsCookiesNHelpForm = new CardsCookiesNHelpForm();
        Assert.assertTrue(homePage.isDisplayed(),"home page is not open");
        homePage.clickOnBtn();
        Assert.assertEquals(cardsCookiesNHelpForm.getTimerTime(), 0, "timer should start at 0 --seconds");
    }
}
