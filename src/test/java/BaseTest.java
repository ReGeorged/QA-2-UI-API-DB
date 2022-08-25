import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import helpers.JpaHelper;
import helpers.SqlHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.FileUtils;
import utils.IntUtils;
import utils.MyAppender;
import utils.StringUtils;

public class BaseTest {
    private static Browser browser;
    private static Appender appender;


    @BeforeMethod
    public static void initialize() {
        appender = MyAppender.getFileAppender("src/main/resources/log4j2.xml");
        appender.start();
        Configurator.setRootLevel(Level.INFO);
        LoggerContext.getContext(true).getRootLogger().addAppender(appender);

        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(FileUtils.readFromJson("configData.json", "/url"));
        browser.waitForPageToLoad();
    }


    @AfterMethod
    public static void quit() {
        browser.quit();
    }

    @AfterMethod
    public void reportIt(ITestResult result) {
        //SqlHelper.insertTestResults(result.getName(), String.valueOf(result.getMethod()), IntUtils.randNumberFrom1To10(), IntUtils.randNumberFrom1To10(), StringUtils.getHostNameAsString());
        JpaHelper.InsertTest(result.getName(), String.valueOf(result.getMethod()), 1,1, StringUtils.getHostNameAsString());
    }

}
