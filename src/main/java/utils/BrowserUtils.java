package utils;

import aquality.selenium.browser.AqualityServices;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class BrowserUtils {

    public static String takeScreenAs64String() {
        File screenshot = AqualityServices.getBrowser().getDriver().getScreenshotAs(OutputType.FILE);
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }

    public static void switchWindowsByIndex(int index) {
        ArrayList<String> windows = new ArrayList(AqualityServices.getBrowser().getDriver().getWindowHandles());
        AqualityServices.getBrowser().getDriver().switchTo().window(windows.get(index));
    }

    public static void setCookie(String cookieName, String cookieValue) {
        Cookie token = new Cookie(cookieName, cookieValue);
        AqualityServices.getBrowser().getDriver().manage().addCookie(token);
    }

}
