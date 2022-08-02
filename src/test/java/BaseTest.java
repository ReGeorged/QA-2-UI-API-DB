import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import base.BrowserBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.FileUtils;

public class BaseTest {

    private static Browser browser;

    @BeforeMethod
    public static void initialize() {


        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(FileUtils.readFromJson("/url"));
        browser.waitForPageToLoad();

    }

    @AfterMethod
    public static void quit() {
        browser.quit();


    }
}
