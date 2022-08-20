import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import constants.Enums;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private static Browser browser;

    @BeforeMethod
    public static void start() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(Enums.URL.getData());
        browser.waitForPageToLoad();
    }

    @AfterMethod
    public static void quit() {
        browser.quit();
    }
}
