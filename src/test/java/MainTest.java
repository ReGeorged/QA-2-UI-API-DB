import forms.NewsLetterForm;
import forms.TopForm;
import helpers.BearerAccessToken;
import org.testng.Assert;
import org.testng.annotations.Test;
import forms.HomePage;
import helpers.PojoHelper;
import pojo.Mail;
import pojo.Messages;

import java.util.List;

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
    }

    @Test(priority = 1)
    public static void tokenTest() {
        String response = BearerAccessToken.refreshBearerAccessToken(
                "1//09_o6emgycYQpCgYIARAAGAkSNgF-L9Ir09yglyXKt8sHV_I2loWeZE38t7sg6PNmwnZRxZQwNoEn_KeT93YhbNpWo3xMKNYAMw",
                "561943751603-pal968j1pchounj6qkqbmi2b7mbv9q48.apps.googleusercontent.com",
                "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"
        ).asString();

        System.out.println(response);
    }


    @Test
    public void getALlMails() {
        String token = "ya29.A0AVA9y1sFxEbqx_3RSQTWXzJ9rmT4xitNhM3Fp_PK1Lqp4UwlYenDIRb1m_4szk35LRDZJ9LvbCQz35cgNPrw67Of2Oc56VitypJclESiHwvfRwnJ6FTDpwOv48Rj1hUXDXx_t7hhGUFF1WAsqoUIJOBsN18fugaCgYKATASATASFQE65dr8qDLpcG2EnRiqxEL2mhdiGQ0165";

        String res = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get("https://gmail.googleapis.com/gmail/v1/users/me/messages/")
                .then()
                .assertThat().statusCode(200).extract().response().asString();

        List<Messages> messages = PojoHelper.veryBadWayToDeserialize(res);
        System.out.println(messages.get(0).getId());
    }
}


