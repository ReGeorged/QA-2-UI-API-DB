package tests;


import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigManager;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {
    @BeforeMethod
    public void setUp(){
    String BASE_URL = ConfigManager.getFromConfig("URL");
        baseURI = BASE_URL;
    }

    @AfterMethod
    public void reset(){
        RestAssured.reset();
    }

}
