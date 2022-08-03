package utils;
public class StringUtils{
    public static int filteredToInteger(String str) {
        String FilteredToNUmber = str.replaceAll("[^0-9]", "");
        int filteredInt = Integer.parseInt(FilteredToNUmber);
        return filteredInt;
    }
}
