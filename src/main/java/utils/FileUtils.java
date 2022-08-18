package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class FileUtils {

    public static String readFromJson(String whichJson, String whatValue) {
        ISettingsFile environment = new JsonSettingsFile(whichJson);
        String value = environment.getValue(whatValue).toString();
        return value;
    }
}
