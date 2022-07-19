import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.RobotClass;

public class MainTest extends BaseTest {

    @Test
    public static void test1() throws InterruptedException {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isDisplayed(),"home page is not open");
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
        informationForm.uploadFile();
        RobotClass.robotsAreSoCool("src/main/resources/125468339_2756572001294425_628201579656761708_n.jpg");
        informationForm.waitForImageToUpload();
        informationForm.selectBox();
        informationForm.clickNextBtn();

        Assert.assertEquals(cardsCookiesNHelpForm.getCurrentPage(),"3 / 4","current page is not 2/4");




    }

    @Test
    public static void case2(){
        HomePage homePage = new HomePage();
        HelpForm helpForm = new HelpForm();
        Assert.assertTrue(homePage.isDisplayed(),"home page is not open");
        homePage.clickOnBtn();
        helpForm.closeHelpMenu();
        Assert.assertTrue(helpForm.isHelpBoxHiddenDisplayed(),"help box is not hidden");
    }

    @Test
    public static void case3() {
        HomePage homePage = new HomePage();
        GameForm gameForm = new GameForm();
        CardsCookiesNHelpForm cardsCookiesNHelpForm = new CardsCookiesNHelpForm();
        Assert.assertTrue(homePage.isDisplayed(),"home page is not open");
        homePage.clickOnBtn();
        Assert.assertTrue(gameForm.isDisplayed(),"\"game\"form is not open");
        cardsCookiesNHelpForm.acceptCookies();
        Assert.assertFalse(cardsCookiesNHelpForm.checkCookiesBanner(), "cookies banner did not disappear");

    }

    @Test
    public static void case4(){
        HomePage homePage = new HomePage();
        GameForm gameForm = new GameForm();
        CardsCookiesNHelpForm cardsCookiesNHelpForm = new CardsCookiesNHelpForm();
        Assert.assertTrue(homePage.isDisplayed(),"home page is not open");
        homePage.clickOnBtn();
        Assert.assertEquals(cardsCookiesNHelpForm.getTimerTime(), 0, "timer should start at 0 --seconds");
    }


}
