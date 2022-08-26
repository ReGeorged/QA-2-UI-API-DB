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

    public static boolean findRepeater(String string) {
        String s = string;
        int distinct = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    distinct++;
                }
                if (distinct >= 2) {
                    return true;
                }
            }
            String d = String.valueOf(s.charAt(i)).trim();
            s = s.replaceAll(d, "");
            distinct = 0;
        }
        return false;
    }
}
