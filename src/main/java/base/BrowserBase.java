package base;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;

public class BrowserBase {

    private static Browser browser;

    public static void startBrowser(){
        if(browser == null){

            browser = AqualityServices.getBrowser();
            browser.maximize();
            browser.goTo("https://userinyerface.com/");
            browser.waitForPageToLoad();
        }
    }

    public static void quitBrowser(){
        browser.quit();
        browser = null;
    }
}
