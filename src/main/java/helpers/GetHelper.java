package helpers;

import static io.restassured.RestAssured.*;

import constants.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Posts;
import pojo.User;

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


    public static <T> List<T> pojoCallGetAsList(String endpoint, int statusCode, Class<T> whatClass) {
        Response response = sendGet(endpoint, statusCode);
        List<T> returnedAnyList = response.jsonPath().getList(".", whatClass);
        return returnedAnyList;
    }


    public static <T> T pojoGetResponse(String endpoint, int statusCode, Class<T> whatClass) {
        Response response = sendGet(endpoint, statusCode);
        return ComplexPojoHelper.jsonPojoHelper(response.asString(), whatClass);
    }

    public static Posts getPostById(String whichId, int statusCode) {
        Response response = sendGet(Endpoints.POSTS + "/" + whichId, statusCode);
        return ComplexPojoHelper.jsonPojoHelper(response.asString(), Posts.class);
    }

    public static User getUserById(String whichId, int statusCode) {
        Response response = sendGet(Endpoints.USERS + "/" + whichId, statusCode);
        return ComplexPojoHelper.jsonPojoHelper(response.asString(), User.class);
    }

}
