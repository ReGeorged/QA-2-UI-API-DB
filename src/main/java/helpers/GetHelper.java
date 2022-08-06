package helpers;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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


    public static<T>List<T> pojoCallGetAsList(String endpoint, int statusCode,Class<T> whatClass) {
        Response response = sendGet(endpoint, statusCode);
        List<T> returnedUsers = response.jsonPath().getList(".",whatClass);
        return returnedUsers;
    }




    public static <T> T pojoGetResponse(String endpoint, int statusCode, Class<T> whatClass) {
        Response response = sendGet(endpoint, statusCode);
        return  ComplexPojoHelper.pojoHelper(response.asString(), whatClass);
    }
}
