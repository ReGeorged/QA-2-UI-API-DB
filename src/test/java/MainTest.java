import base.BrowserBase;
import forms.*;
import helpers.BearerAccessToken;
import helpers.PojoHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Mail;
import utils.FileUtils;


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
        newsLetterForm.sendKeysToEmail(FileUtils.readFromJson("configData.json","/email"));


        String refreshedToken = BearerAccessToken.refreshBearerAccessToken();
        String response = BearerAccessToken.getAllMail(refreshedToken).asString();
        String id = PojoHelper.jsonPojoHelper(response, Mail.class).getMessages().get(0).getId();
        String body =BearerAccessToken.getSpecificMailBodyViaId(refreshedToken,id);

        FileUtils.mailBodyToFile(body,"index");
        BrowserBase.initialize().goTo(FileUtils.getAbsolutePath("src/main/resources/index.html"));

        MailForm mailForm = new MailForm();
        mailForm.clickOnConfirmBtn();
        ConfirmationForm confirmationForm = new ConfirmationForm();
        confirmationForm.clickOnBackToSiteBtn();

        topForm.clickOnNewsLetter();
        newsLetterForm.reSelectSubject();



    }
}


