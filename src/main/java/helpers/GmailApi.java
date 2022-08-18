package helpers;

import constants.Endpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.FileUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class GmailApi {


    public static Response getBearerAccessToken(String code, String client_id, String client_secret, String redirect_uri) {
        Response res = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("code", code)
                .queryParam("client_id", client_id)
                .queryParam("client_secret", client_secret)
                .queryParam("redirect_uri", redirect_uri)
                .queryParam("grant_type", "authorization_code").
                when()
                .post(Endpoints.gmailOath).
                then()
                .assertThat().statusCode(200).extract().response();

        return res;
    }

    public static String refreshBearerAccessToken() {

        Response res = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("client_id", FileUtils.readFromJson("client_secret.json", "/installed/client_id"))
                .queryParam("client_secret", FileUtils.readFromJson("client_secret.json", "/installed/client_secret"))
                .queryParam("refresh_token", FileUtils.readFromJson("client_secret.json", "/installed/refresh_token"))
                .queryParam("grant_type", "refresh_token").
                when()
                .post(Endpoints.gmailOath).
                then()
                .assertThat().statusCode(200).extract().response();

        JsonPath json = res.jsonPath();
        String refreshedAccessToken = json.get("access_token");
        return refreshedAccessToken;
    }
    public static Response getAllMail(String token, int expectedCode) {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(Endpoints.gmailMessages)
                .then()
                .assertThat().statusCode(expectedCode).extract().response();
        return res;
    }

    public static String getSpecificMailBodyViaId(String token, String id, int expectedCode) {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get(Endpoints.gmailMessages + id)
                .then()
                .assertThat().statusCode(expectedCode).extract().response();

        JsonPath json = res.jsonPath();
        String base64Body = json.get("payload.parts[0].body.data");

        byte[] decodedArray = Base64.getUrlDecoder().decode(base64Body);
        try {
            String decodedText = new String(decodedArray, "UTF-8");
            return decodedText;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
