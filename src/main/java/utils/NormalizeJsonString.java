package utils;

public class NormalizeJsonString {

    public static String normalizedJsonString(String input) {
        String newString = input.replace("[", "{");
        String stepTwoString = newString.replace("]", "}");
        String stepThree= stepTwoString.replace("\"","");
        return stepThree.replaceAll("\\s+","");
    }
}
