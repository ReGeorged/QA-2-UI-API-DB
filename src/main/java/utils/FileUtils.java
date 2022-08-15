package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import java.io.FileOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static boolean isEmpty(String pathString)  {
        Path path = Paths.get(pathString);
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)) {
                return !directory.iterator().hasNext();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static void deleteIfNotEmpty(String path) {
        try {
            if (isEmpty(path) == false) {
                File directory = new File(path);
                org.apache.commons.io.FileUtils.cleanDirectory(directory);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAbsolutePath(String relativePath){
        String absolute = null;
        try {
            absolute = new File(relativePath).getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return absolute;
    }

    public static String readFromJson(String whichJson,String whatValue){
        ISettingsFile environment = new JsonSettingsFile(whichJson);
        String value = environment.getValue(whatValue).toString();
        return value;
    }

    public static String getPathToResource(String resourceName)   {
        String file = resourceName;
        var path = Paths.get("src/main/resources/", file);
        return path.toString();
    }

    public static void mailBodyToFile(String whatString, String whatPath) throws IOException {

        String finalPath = "src/main/resources/"+whatPath;

//        Files.createDirectories(Paths.get(finalPath));

        String str = whatString;
        FileOutputStream outputStream = new FileOutputStream(finalPath+".html");
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);

        outputStream.close();

    }
}
