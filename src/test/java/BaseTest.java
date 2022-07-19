import base.BrowserBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public static void initialize(){
        BrowserBase.startBrowser();

    }

    @AfterMethod
    public static void quit(){
        BrowserBase.quitBrowser();


    }
}
