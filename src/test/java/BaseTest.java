import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import base.BrowserBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.FileUtils;

public class BaseTest {



    @BeforeMethod
    public static void start() {

        BrowserBase.initialize();
    }


    @AfterMethod
    public static void quit() {

        BrowserBase.initialize().quit();
    }

}
