package tests;

import helpers.PostsHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class MainTest {


    @Test
    public void test23(){
        //test1("https://jsonplaceholder.typicode.com/users/150",404);
        PostsHelper postsHelper = new PostsHelper();
        //System.out.println(usersHelper.getAllUsers());
        //System.out.println(usersHelper.getAllUsers(200));
//        Assert.assertNotNull(postsHelper.OLDDDDDgetAllUsers());
//        System.out.println(postsHelper.OLDDDDDgetAllUsers().toString());
    }


    public String test1(String URL, int expectedCode){
        String response =
                given().
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        get(URL).
                then().
                        statusCode(expectedCode).
                        extract().
                        response().
                        asString();
        return response;
    }


    @Test
    public static void testStep4(){
        PostsHelper postsHelper = new PostsHelper();
        Response response= postsHelper.createPost();

        int id = response.jsonPath().getInt("id");
        Assert.assertNotNull(id);

        int userId = response.jsonPath().getInt("userId");
        Assert.assertEquals(userId,1);

        String title = response.jsonPath().getString("title");
        Assert.assertEquals(title,"AlexExample");

        String body = response.jsonPath().getString("body");
        Assert.assertEquals(body,"example_body");


        System.out.println(id);
    }




}
