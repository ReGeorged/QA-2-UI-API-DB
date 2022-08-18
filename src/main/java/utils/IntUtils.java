package utils;
import java.util.Random;
public class IntUtils {
    public static int getRandInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
