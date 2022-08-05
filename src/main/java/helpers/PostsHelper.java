package helpers;

import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostsHelper {


    public static <T> T createPost(Object obj) {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .body(obj)
                .post(Endpoints.POSTS)
                .then()
                .statusCode(201)
                .extract().response();


        return (T) ComplexPojoHelper.pojoHelper(response.asString(), obj.getClass());

    }
}


