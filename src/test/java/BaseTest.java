import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import constants.EndPoints;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private static Browser browser;

    @BeforeMethod
    public static void start() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(EndPoints.getLink());
        browser.waitForPageToLoad();
    }

//    @AfterMethod
//    public static void quit() {
//        browser.quit();
//    }
}
