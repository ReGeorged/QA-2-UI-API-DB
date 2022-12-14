import helpers.DBHelper;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import pages.*;
import utils.FileUtils;
import utils.IntUtils;
import utils.RobotClass;
import utils.StringUtils;

public class MainTest extends BaseTest {

    @AfterMethod
    public void saveTestResultsToDB(ITestResult result) {
        Assert.assertTrue(DBHelper.createNewTest(result.getName(), String.valueOf(result.getMethod()), IntUtils.randNumberInRange(1, 3), IntUtils.randNumberInRange(1, 3), StringUtils.getHostNameAsString()));
    }

    @AfterSuite()
    public void a1updateDoubles() {
        int howMany = 2;
        Assert.assertEquals(DBHelper.updateDoubleIds(howMany, pojo.Test.class), howMany);
    }

    @AfterSuite
    public void a2deleteDoubles() {
        int howMany = 2;
        Assert.assertEquals(DBHelper.deleteDoubleIds(howMany, pojo.Test.class), howMany);
    }


    @Test
    public static void createProfileAndCheckIt() throws InterruptedException {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isDisplayed(), "home page is not open");

        homePage.clickOnBtn();
        GameForm gameForm = new GameForm();
        CardsCookiesNHelpForm cardsCookiesNHelpForm = new CardsCookiesNHelpForm();
        Assert.assertTrue(gameForm.isDisplayed(), "\"game\" page is not open");
        Assert.assertEquals(cardsCookiesNHelpForm.getCurrentPage(), "1 / 4", "current page is not 1/4");

        gameForm.fillPassword();
        gameForm.fillMail();
        gameForm.fillDomain();
        gameForm.clickOnDropDown();
        gameForm.clickOnTC();
        gameForm.clickOnNextBtn();
        Assert.assertEquals(cardsCookiesNHelpForm.getCurrentPage(), "2 / 4", "current page is not 2/4");

        InformationForm informationForm = new InformationForm();
        Assert.assertTrue(informationForm.isDisplayed(), "info page is not open");
        informationForm.selectBox();
        informationForm.uploadFile();
        RobotClass.uploadFileUsingRobot(FileUtils.getPathToResource("image.jpg"));
        informationForm.waitForImageToUpload();
        informationForm.clickNextBtn();
        Assert.assertEquals(cardsCookiesNHelpForm.getCurrentPage(), "3 / 4", "current page is not 3/4");
    }

    @Test
    public static void hideHelpForm() {
        HomePage homePage = new HomePage();
        HelpForm helpForm = new HelpForm();
        Assert.assertTrue(homePage.isDisplayed(), "home page is not open");
        homePage.clickOnBtn();
        helpForm.closeHelpMenu();
        Assert.assertFalse(helpForm.isHelpBoxDisplayed(), "help box is not hidden");
    }

    @Test
    public static void acceptCookies() {
        HomePage homePage = new HomePage();
        GameForm gameForm = new GameForm();
        CardsCookiesNHelpForm cardsCookiesNHelpForm = new CardsCookiesNHelpForm();
        Assert.assertTrue(homePage.isDisplayed(), "home page is not open");
        homePage.clickOnBtn();
        Assert.assertTrue(gameForm.isDisplayed(), "\"game\"form is not open");
        cardsCookiesNHelpForm.acceptCookies();
        Assert.assertFalse(cardsCookiesNHelpForm.isCookiesBannerDisplayed(), "cookies banner did not disappear");
    }

    @Test
    public static void validateTimer() {
        HomePage homePage = new HomePage();
        CardsCookiesNHelpForm cardsCookiesNHelpForm = new CardsCookiesNHelpForm();
        Assert.assertTrue(homePage.isDisplayed(), "home page is not open");
        homePage.clickOnBtn();
        Assert.assertEquals(cardsCookiesNHelpForm.getTimerTime(), 0, "timer should start at 0 --seconds");
    }
}
