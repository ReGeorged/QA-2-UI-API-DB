import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import constants.EndPoints;
import constants.RestHashMaps;
import helpers.RestHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.FileUtils;

public class BaseTest {

    private static Browser browser;

    @BeforeMethod
    public static void start() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
        System.out.println(FileUtils.readFromJson("configData.json", "/url"));
        browser.goTo(FileUtils.readFromJson("configData.json", "/url"));
        browser.waitForPageToLoad();
    }

//    @AfterMethod
//    public static void quit() {
//        browser.quit();
//    }
}
