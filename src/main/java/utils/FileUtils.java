package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.io.*;

public class FileUtils {

    public static String readFromJson(String whichJson, String whatValue) {
        ISettingsFile environment = new JsonSettingsFile(whichJson);
        String value = environment.getValue(whatValue).toString();
        return value;
    }

    public static String logToString() {
        try {
            StringBuilder logString = new StringBuilder();
            FileInputStream fstream = new FileInputStream("logs/TestLogs.log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                logString.append(strLine);
                logString.append("\n");
            }
            fstream.close();
            return logString.toString();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;

    }
}



