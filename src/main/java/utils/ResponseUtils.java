package utils;


import io.restassured.response.Response;

public class ResponseUtils {

    public static String responseBodyToString(Response response) {
        String responseString = response.getBody().asString();
        return responseString;
    }


}
