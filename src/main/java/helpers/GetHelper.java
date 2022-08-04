package helpers;

import static io.restassured.RestAssured.*;

import constants.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class GetHelper {
    public static String getUserById(String whichIndex, int expectedCode) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get(Endpoints.USERS)
                        .then()
                        .statusCode(expectedCode)
                        .extract()
                        .response();
        JsonPath path = response.jsonPath();
        String calledString = path.getString("[" + whichIndex + "]");
        return calledString;
    }


    public static Response callGetOnAllUsers(int statusCode) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get(Endpoints.USERS)
                        .then()
                        .statusCode(statusCode)
                        .extract()
                        .response();
        return response;
    }

    public static  Response callGetOnSpecificUser(String whichIndex, int expectedCode) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get(Endpoints.USERS +"/"+ whichIndex)
                        .then()
                        .statusCode(expectedCode)
                        .extract()
                        .response();
        return response;


    }


    public static Response callGetOnSpecificPosts(String whichIndex, int expectedCode) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get(Endpoints.POSTS +"/"+ whichIndex)
                        .then()
                        .statusCode(expectedCode)
                        .extract()
                        .response();
        return response;
    }

    public static String a( int expectedCode) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get(Endpoints.USERS)
                        .then()
                        .statusCode(expectedCode).extract().response();

        String whatWelookingFor = response.path("address.geo.lng.min {it}");
        return whatWelookingFor;
    }

}
