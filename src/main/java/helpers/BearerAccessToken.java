package helpers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BearerAccessToken {


    public static String getBearerAccessToken(String code,String client_id, String client_secret,String redirect_uri) {
        Response res = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("code", code)
                .queryParam("client_id", client_id)
                .queryParam("client_secret", client_secret)
                .queryParam("redirect_uri", redirect_uri)
                .queryParam("grant_type","authorization_code").
                when()
                .post("https://oauth2.googleapis.com/token").
                then()
                .assertThat().statusCode(200).extract().response();

        System.out.println("The response with Access Token is : " +res.asString());
        JsonPath json = res.jsonPath();
        String accessToken = json.get("access_token");
        return accessToken;
    }



}
