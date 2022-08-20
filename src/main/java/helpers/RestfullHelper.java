package helpers;

import constants.Endpoints;
import constants.RestHashMaps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RestfullHelper {

    public static Response postWithHeadersAndParams(HashMap headersHashMap, HashMap queryHashMap, String endPoint, int expectedCode) {
        Response res = given()
                .headers(headersHashMap)
                .queryParams(queryHashMap)
                .when()
                .post(endPoint).
                then()
                .assertThat().statusCode(expectedCode).extract().response();
        return res;
    }

    public static Response getWithHeaders(HashMap headersHashMap, String endPoint, int expectedCode) {
        Response res = given()
                .headers(headersHashMap)
                .when()
                .get(endPoint)
                .then()
                .assertThat().statusCode(expectedCode).extract().response();
        return res;
    }

    public static String refreshBearerAccessToken(int expectedCode) {
        HashMap<String, String> headerHashMap = RestHashMaps.headerXWwwMap();
        HashMap<String, String> refreshTokenParams = RestHashMaps.gmailRefreshParamsMap();
        Response res = postWithHeadersAndParams(headerHashMap, refreshTokenParams, Endpoints.gmailOath, expectedCode);
        JsonPath json = res.jsonPath();
        String refreshedAccessToken = json.get("access_token");
        return refreshedAccessToken;
    }


    public static Response getAllMail(String token, int expectedCode) {
        HashMap<String, String> headerHashMap = RestHashMaps.tokenHeaderMap(token);
        Response res = getWithHeaders(headerHashMap, Endpoints.gmailMessages, expectedCode);
        return res;
    }

    public static String getSpecificMailBodyViaId(String token, String id, int expectedCode) {
        HashMap headerHashMap = RestHashMaps.tokenHeaderMap(token);
        Response res = getWithHeaders(headerHashMap, Endpoints.gmailMessages + id, expectedCode);
        JsonPath json = res.jsonPath();
        String base64Body = json.get("payload.parts[0].body.data");
        byte[] decodedArray = Base64.getUrlDecoder().decode(base64Body);
        try {
            String decodedText = new String(decodedArray, "UTF-8");
            return decodedText;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
