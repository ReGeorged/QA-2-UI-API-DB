package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class StringUtils {
    public static int filteredToInteger(String str) {
        String FilteredToNUmber = str.replaceAll("[^0-9]", "");
        int filteredInt = Integer.parseInt(FilteredToNUmber);
        return filteredInt;
    }

    public static String getHostNameAsString() {
        try {
            String systemName = InetAddress.getLocalHost().getHostName();
            return systemName;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
