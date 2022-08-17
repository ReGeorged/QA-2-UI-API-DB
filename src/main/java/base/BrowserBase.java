package base;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;

public class BrowserBase {

    private static Browser browser;

    public static Browser initialize() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
        return browser;
    }

    public static void quit(){
        browser.quit();
        browser=null;
    }
}
