package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

    public static long dateStringToMillis(String stringDate) {
        String myDate = stringDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = null;
        try {
            date = sdf.parse(myDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long millis = date.getTime();
        return millis;
    }

    public static String getHostNameAsString() {
        try {
            String systemName = InetAddress.getLocalHost().getHostName();
            return systemName;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getDeclaringMethodName() {
        String methodName = new Exception().getStackTrace()[1].getMethodName();
        return methodName;
    }
}
