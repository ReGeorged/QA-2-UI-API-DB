package utils;

import java.util.Random;

public class IntUtils {
    public static int randNumberFrom1To10() {
        int min=1;
        int max=10;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
