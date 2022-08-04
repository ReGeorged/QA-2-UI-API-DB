package helpers;
import com.fasterxml.jackson.core.type.TypeReference;
import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import pojo.Posts;
import utils.ConfigManager;
import utils.StringUtils;
public class PostsHelper {

    public static Response createPost() {
        Posts posts = new Posts();
        posts.setUserId(StringUtils.stringToInt(ConfigManager.getFromConfig("postUSERID")));
        posts.setId(StringUtils.stringToInt(ConfigManager.getFromConfig("postID")));
        posts.setTitle(ConfigManager.getFromConfig("postTITLE"));
        posts.setBody(ConfigManager.getFromConfig("postBODY"));
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

