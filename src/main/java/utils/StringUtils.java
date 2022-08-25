package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class StringUtils{
    public static int filteredToInteger(String str) {
        String FilteredToNUmber = str.replaceAll("[^0-9]", "");
        int filteredInt = Integer.parseInt(FilteredToNUmber);
        return filteredInt;
    }

    public static String getHostNameAsString(){

        try {
            String systemName = InetAddress.getLocalHost().getHostName();
            return systemName;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread()
                .getStackTrace();
        return stackTrace[2].getMethodName();
    }

    public static void main(String[] args) {
        getMethodName();
    }
}
