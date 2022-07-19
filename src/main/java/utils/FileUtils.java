package utils;

import java.io.File;
import java.io.IOException;

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

}
