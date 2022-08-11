package utils;

import aquality.selenium.elements.interfaces.IElement;

import java.util.List;
import java.util.Random;

public class ElementUtils {

    public static <T extends IElement> void selectRandomElementFromList(List<T> whatList, int howManyElements) {
        Random rand = new Random();
        int numberOfElements = howManyElements;
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(whatList.size());
            T randomElements = whatList.get(randomIndex);
            randomElements.state().waitForDisplayed();
            randomElements.getJsActions().scrollToTheCenter();
            randomElements.clickAndWait();
        }
    }


    public static <T extends IElement> T randomElementFromList(List<T> whatList, int howManyElements) {
        Random rand = new Random();
        int numberOfElements = howManyElements;
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(whatList.size());
            T randomElements = whatList.get(randomIndex);
            return randomElements;
        }
        return null;
    }
}
