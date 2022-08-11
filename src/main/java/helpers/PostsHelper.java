package helpers;

import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostsHelper {


    public static <T> T createPost(Object obj,int expectedCode) {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .body(obj)
                .post(Endpoints.POSTS)
                .then()
                .statusCode(expectedCode)
                .extract().response();

        return (T) ComplexPojoHelper.jsonPojoHelper(response.asString(), obj.getClass());
    }
}


