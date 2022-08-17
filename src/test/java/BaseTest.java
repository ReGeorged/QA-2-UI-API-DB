import base.BrowserBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {


    @BeforeMethod
    public static void start() {
        BrowserBase.initialize();
    }


    @AfterMethod
    public static void quit() {
        BrowserBase.quit();
    }

}
