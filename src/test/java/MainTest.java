import forms.NewsLetterForm;
import forms.TopForm;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import forms.HomePage;

import static io.restassured.RestAssured.given;

public class MainTest {


    @Test
    public void euroNews() {
        HomePage homePage = new HomePage();
        homePage.clickOnPrivacyBtn();
        Assert.assertTrue(homePage.isDisplayed());
        TopForm topForm = new TopForm();
        topForm.clickOnNewsLetter();
        NewsLetterForm newsLetterForm = new NewsLetterForm();
        newsLetterForm.selectRandomLetter();
        newsLetterForm.sendKeysToEmail();
        ConfirmationForm confirmationForm = new ConfirmationForm();
        Assert.assertTrue(confirmationForm.isDisplayed());
    }

    @Test(priority = 1)
    public static void getBearerAccessToken() {
        Response res = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("code", "4/0AdQt8qgewrZzn7qnYqziwe_OqxxN8-8Y48wGMkSbQ0cCBGT_r5k6dKAhgSCYsD8VIPU8xg")
                .queryParam("client_id", "561943751603-pal968j1pchounj6qkqbmi2b7mbv9q48.apps.googleusercontent.com")
                .queryParam("client_secret", "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")
                .queryParam("redirect_uri", "http://localhost")
                .queryParam("grant_type","authorization_code").
                when()
                .post("https://oauth2.googleapis.com/token").
                then()
                .assertThat().statusCode(200).extract().response();

        System.out.println("The response with Access Token is : " +res.asString());

        JsonPath json = res.jsonPath();
//Storing AccessToken in a Variable
        String AccessToken = json.get("access_token");
    }

}
