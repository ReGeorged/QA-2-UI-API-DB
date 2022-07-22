package helpers;
import com.fasterxml.jackson.core.type.TypeReference;
import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Users;
import utils.ConfigManager;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersHelper {


    private static final String BASE_URL = ConfigManager.getFromConfig("URL");



    public UsersHelper(){
        RestAssured.baseURI = BASE_URL;
    }

    public String OLDDDDDgetAllUsers(){
//        RestAssured.baseURI = BASE_URL;
        String response = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)

                .get("https://jsonplaceholder.typicode.com/users")
            .then()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        return response;
    }

    public String getAllUsers(int expectedCode) {
        RestAssured.baseURI = BASE_URL;


        String response =
                given().
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        get(Endpoints.GET_ALL_USERS).
                        then().
                        statusCode(expectedCode).
                        extract().
                        response().
                        asString();
        return response;
    }
}
