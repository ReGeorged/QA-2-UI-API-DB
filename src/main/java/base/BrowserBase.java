package base;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import utils.FileUtils;


@Deprecated
public class BrowserBase {

    private static Browser browser;

    public static void startBrowser(){
            browser = AqualityServices.getBrowser();
            browser.maximize();
            browser.goTo(FileUtils.readFromJson("/url"));
            browser.waitForPageToLoad();

    }

    public static void quitBrowser(){
        browser.quit();
    }
}
