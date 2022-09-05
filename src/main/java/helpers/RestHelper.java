package helpers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RestHelper {

    public static Response postWithHeadersAndParams(HashMap headersHashMap, HashMap paramsHashMap, String endPoint, int expectedCode) {
        Response res = given()
                .headers(headersHashMap)
                .params(paramsHashMap)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(expectedCode).extract().response();
        return res;
    }

    public static Response postAndAcceptJson(HashMap headersHashMap, HashMap paramsHashMap, String endpoint, int statusCode) {
        Response res = given()
                .accept(ContentType.JSON)
                .headers(headersHashMap)
                .params(paramsHashMap)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(statusCode).extract().response();
        return res;
    }


}
