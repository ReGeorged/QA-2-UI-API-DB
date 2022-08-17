import base.BrowserBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.FileUtils;


public class BaseTest {


    @BeforeMethod
    public static void start() {
        BrowserBase.initialize();
        BrowserBase.initialize().goTo(FileUtils.readFromJson("configData.json", "/url"));
        BrowserBase.initialize().waitForPageToLoad();
    }


    @AfterMethod
    public static void quit() {
        BrowserBase.quit();
    }

}
