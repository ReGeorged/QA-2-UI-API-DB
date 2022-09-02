package helpers;

import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RestHelper {

    public static Response postWithHeadersAndParams(HashMap headersHashMap,HashMap paramsHashMap, String endPoint, int expectedCode) {
        Response res = given()
                .headers(headersHashMap)
                .params(paramsHashMap)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(expectedCode).extract().response();
        return res;
    }
}
