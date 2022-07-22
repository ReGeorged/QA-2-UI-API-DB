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

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsHelper {


    private static final String BASE_URL = ConfigManager.getFromConfig("URL");



    public PostsHelper(){
        RestAssured.baseURI = BASE_URL;
    }

    public List<Posts> OLDDDDDgetAllPosts(){
//        RestAssured.baseURI = BASE_URL;
        Response response = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)

                .get(Endpoints.POSTS)
                .andReturn();
        Type type = new TypeReference<List<Posts>>(){}.getType();
        List<Posts> postsList = response.as(type);
        return postsList;

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

    public Response createPost(){
        Posts posts = new Posts();
        posts.setUserId(StringUtils.stringToInt(ConfigManager.getFromConfig("postUSERID")));
        posts.setId(StringUtils.stringToInt(ConfigManager.getFromConfig("postID")));
        posts.setTitle(ConfigManager.getFromConfig("postTITLE"));
        posts.setBody(ConfigManager.getFromConfig("postBODY"));

        Response response =  RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                .log().all()
                .when()
                    .body(posts)
                    .post(Endpoints.POSTS)
                    .andReturn();
        Assert.assertEquals(response.getStatusCode(),201);


        return response;
    }

}
