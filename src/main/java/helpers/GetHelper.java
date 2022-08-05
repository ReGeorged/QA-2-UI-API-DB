package helpers;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.User;

import java.util.Arrays;
import java.util.List;

public class GetHelper {

    private static Response sendGet(String endpoint, int statusCode) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get(endpoint)
                        .then()
                        .statusCode(statusCode)
                        .extract()
                        .response();
        return response;
    }
    public static List<User> callGetOnAllUsers(String endpoint, int statusCode) {
        Response response = sendGet(endpoint, statusCode);
        List<User> returnedUsers = Arrays.asList(response.getBody().as(User[].class));
        return returnedUsers;
    }

    public static <T> T pojoGetResponse(String endpoint, int statusCode, Class<T> whatClass) {
        Response response = sendGet(endpoint, statusCode);
        return  ComplexPojoHelper.pojoHelper(response.asString(), whatClass);
    }
}
