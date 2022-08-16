package utils;


public class StringUtils {


    public static String extractLinkFromMail(String linkFromWhatString) {
        String[] splitOne = linkFromWhatString.split("<a href=\"");
        String[] splitTwo = splitOne[1].split("\" target");
        return splitTwo[0];
    }
}
