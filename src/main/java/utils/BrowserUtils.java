package utils;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.OutputType;

import java.io.File;

public class BrowserUtils {

    public static String takeScreenAndGetPath(){
        File screenshot = AqualityServices.getBrowser().getDriver().getScreenshotAs(OutputType.FILE);
        return screenshot.getAbsolutePath();

    }
}
