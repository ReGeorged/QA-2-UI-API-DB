package utils;

import base.BrowserBase;

import java.util.ArrayList;

public class DriverUtils {

    public static void switchToTabByInt(int whichTabToSwitchTo) {

        ArrayList<String> tabs = new ArrayList<String>(BrowserBase.initialize().getDriver().getWindowHandles());
        BrowserBase.initialize().getDriver().switchTo().window(tabs.get(whichTabToSwitchTo));
    }
}
