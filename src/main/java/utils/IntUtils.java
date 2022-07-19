package utils;

import aquality.selenium.elements.Label;
import aquality.selenium.elements.interfaces.ICheckBox;
import java.util.List;
import java.util.Random;

public class IntUtils {

    public static void randomICheckBoxFromList(List whatList, int howManyElements) {
        Random rand = new Random();
        int numberOfElements = howManyElements;
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(whatList.size());
            ICheckBox randomElements = (ICheckBox) whatList.get(randomIndex);
            whatList.remove(randomIndex);
            randomElements.check();
        }
    }

    public static void clickRandomFromList(List whatList, int howManyElements){

        Random rand = new Random();
        int numberOfElements = howManyElements;
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(whatList.size());
            Label randomElements = (Label) whatList.get(randomIndex);
            whatList.remove(randomIndex);
            randomElements.click();
        }

    }
}
