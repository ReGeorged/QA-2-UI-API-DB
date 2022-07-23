package tests;

import helpers.ComplexPojoHelper;
import helpers.PostsHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigManager;
import utils.StringUtils;


import static io.restassured.RestAssured.given;

public class MainTest {


    @Test
    public void test23(){
        //test1("https://jsonplaceholder.typicode.com/users/150",404);
        //PostsHelper postsHelper = new PostsHelper();
        //System.out.println(usersHelper.getAllUsers());
        //System.out.println(usersHelper.getAllUsers(200));
//        Assert.assertNotNull(postsHelper.OLDDDDDgetAllUsers());
//        System.out.println(postsHelper.OLDDDDDgetAllUsers().toString());

        ComplexPojoHelper complexPojoHelper = new ComplexPojoHelper();
        complexPojoHelper.deserializeToComplexPojo("4",200);

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
}
