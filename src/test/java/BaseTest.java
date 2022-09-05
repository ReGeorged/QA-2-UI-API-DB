import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import constants.EndPoints;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.IntUtils;
import utils.MyAppender;

public class BaseTest {

    private static Browser browser;
    private static Appender appender;
    protected static int randomSessionId;

    @BeforeSuite
    public static void randId() {
        randomSessionId = IntUtils.getRandInRange(1, 100000000);
    }

    @BeforeMethod
    public static void start() {
        appender = MyAppender.getFileAppender("src/main/resources/log4j2.xml");
        appender.start();
        Configurator.setRootLevel(Level.INFO);
        LoggerContext.getContext(true).getRootLogger().addAppender(appender);

        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(EndPoints.authorizedWebLink());
        browser.waitForPageToLoad();
    }

    @AfterMethod
    public static void quit() {
        browser.quit();
    }
}
