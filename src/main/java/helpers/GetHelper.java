package helpers;

import static io.restassured.RestAssured.*;

import constants.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.ConfigManager;

public class GetHelper {
    private static final String BASE_URL = ConfigManager.getFromConfig("URL");

    public GetHelper() {
        baseURI = BASE_URL;
    }

    public String callGetAllUsersAndExtractSpecific(String whichIndex, int expectedCode) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get(Endpoints.GET_ALL_USERS)
                        .then()
                        .statusCode(expectedCode)
                        .extract()
                        .response();
        JsonPath path = response.jsonPath();
        String calledString = path.getString("[" + whichIndex + "]");
        return calledString;
    }


    public Response callGetOnAllUsers(int statusCode) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get(Endpoints.GET_ALL_USERS)
                        .then()
                        .statusCode(statusCode)
                        .extract()
                        .response();
        return response;
    }

    public Response callGetOnSpecificUser(String whichIndex, int expectedCode) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get(Endpoints.GET_SINGLE_USERS + whichIndex)
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
                        .get(Endpoints.GET_SINGLE_POSTS + whichIndex)
                        .then()
                        .statusCode(expectedCode)
                        .extract()
                        .response();
        return response;
    }

    public String a(String whichIndex, int expectedCode) {
        String response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get(Endpoints.GET_ALL_USERS)
                        .then()
                        .statusCode(expectedCode)
                        .extract()
                        .body()
                        .jsonPath()
                        .get("[" + whichIndex + "]")
                        .toString();
        return response;
    }


}
