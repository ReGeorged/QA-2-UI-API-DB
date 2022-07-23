package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import complexpojo.Address;
import complexpojo.Company;
import complexpojo.Geo;
import complexpojo.Users;
import constants.Endpoints;
import io.restassured.http.ContentType;
import utils.ConfigManager;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ComplexPojoHelper {
    private static final String BASE_URL = ConfigManager.getFromConfig("URL");

    public ComplexPojoHelper(){
        baseURI = BASE_URL;
        Users users;
        Address address;
        Geo geo;
        Company company;


    }


    public  void deserializeToComplexPojo(String whichIndex, int expectedCode){
        ObjectMapper mapper = new ObjectMapper();
        String response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .get(Endpoints.GET_SINGLE_USERS+whichIndex)
                        .then()
                        .statusCode(expectedCode)
                        .extract()
                        .response().asString();
        try {
            Users users = mapper.readValue(response,Users.class);
            Address address = users.getAddress();
            Geo geo = address.getGeo();
            Company company = users.getCompany();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
