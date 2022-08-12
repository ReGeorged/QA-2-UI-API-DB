package helpers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BearerAccessToken {


    public static Response getBearerAccessToken(String code,String client_id, String client_secret,String redirect_uri) {
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

        return res;
    }

    public static Response refreshBearerAccessToken(String refresh_token,String client_id, String client_secret){

        Response res = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("client_id", client_id)
                .queryParam("client_secret", client_secret)
                .queryParam("refresh_token", refresh_token)
                .queryParam("grant_type","refresh_token").
                when()
                .post("https://oauth2.googleapis.com/token").
                then()
                .assertThat().statusCode(200).extract().response();
        return res;

    }



}
