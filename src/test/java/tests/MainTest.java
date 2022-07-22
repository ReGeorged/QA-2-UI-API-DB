package tests;

import helpers.UsersHelper;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Users;


import java.util.List;

import static io.restassured.RestAssured.given;

public class MainTest {


    @Test
    public void test23(){
        //test1("https://jsonplaceholder.typicode.com/users/150",404);
        UsersHelper usersHelper = new UsersHelper();
        //System.out.println(usersHelper.getAllUsers());
        System.out.println(usersHelper.getAllUsers(200));
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
