package helpers;

import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Posts;

public class PostsHelper {

    public static Response createPost(Posts posts) {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .body(posts)
                .post(Endpoints.POSTS)
                .then()
                .statusCode(201)
                .extract().response();
        return response;
    }
}

