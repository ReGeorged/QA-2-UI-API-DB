package utils;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

public class StringUtils <T>{

    public static int filteredToInteger(String str) {
        String FilteredToNUmber = str.replaceAll("[^0-9]", "");
        int filteredInt = Integer.parseInt(FilteredToNUmber);
        return filteredInt;
    }




}
