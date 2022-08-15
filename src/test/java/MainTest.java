import base.BrowserBase;
import forms.NewsLetterForm;
import forms.TopForm;
import helpers.BearerAccessToken;
import helpers.PojoHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import forms.HomePage;
import pojo.Mail;
import utils.FileUtils;

import java.io.IOException;

public class MainTest {


    @Test
    public void euroNews() {

        BrowserBase.initialize().goTo(FileUtils.readFromJson("configData.json", "/url"));
        BrowserBase.initialize().waitForPageToLoad();
        HomePage homePage = new HomePage();
        homePage.clickOnPrivacyBtn();
        Assert.assertTrue(homePage.isDisplayed());
        TopForm topForm = new TopForm();
        topForm.clickOnNewsLetter();
        NewsLetterForm newsLetterForm = new NewsLetterForm();
        newsLetterForm.selectRandomLetter();
        newsLetterForm.sendKeysToEmail();

    }

    @Test()
    public static void tokenTest() {
        String response = BearerAccessToken.refreshBearerAccessToken();
        System.out.println(response);
    }


    @Test
    public void getALlMails() throws IOException {
        String refreshedToken = BearerAccessToken.refreshBearerAccessToken();
        String response = BearerAccessToken.getAllMail(refreshedToken).asString();
        String id = PojoHelper.jsonPojoHelper(response, Mail.class).getMessages().get(0).getId();
        System.out.println(id);
        String body =BearerAccessToken.getSpecificMailBodyViaId(refreshedToken,id);
        System.out.println(body);
        FileUtils.mailBodyToFile(body,"index");

        BrowserBase.initialize().goTo("C:\\Users\\N\\IdeaProjects\\Level2API\\src\\main\\resources\\index.html");
    }
}


