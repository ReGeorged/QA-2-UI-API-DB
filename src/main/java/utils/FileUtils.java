package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static String getAbsolutePath(String relativePath){
        String absolute = null;
        try {
            absolute = new File(relativePath).getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return absolute;
    }


    public static String readFromJson(String whatValue){
        ISettingsFile environment = new JsonSettingsFile("configData.json");
        String value = environment.getValue(whatValue).toString();
        return value;
    }

    public static String getPathToResource(String resourceName)   {
        String file = resourceName;
        var path = Paths.get("src/main/resources/", file);
        return path.toString();
    }

}
