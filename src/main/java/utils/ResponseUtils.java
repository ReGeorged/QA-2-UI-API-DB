package utils;


import io.restassured.response.Response;

public class ResponseUtils {

    public static String responseBodyToString(Response response) {
        String responseString = response.getBody().asString();
        return responseString;
    }

    public static String fieldName(Response response, String whatToExtract) {
        String responseString = response.getBody().jsonPath().getString(whatToExtract);
        return responseString;
    }


}
