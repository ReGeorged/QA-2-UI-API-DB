import base.BrowserBase;
import forms.*;
import helpers.BearerAccessToken;
import helpers.PojoHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Mail;
import utils.FileUtils;
import utils.StringUtils;


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

        BrowserBase.initialize().goTo(StringUtils.extractLinkFromMail(body));
        ConfirmationForm confirmationForm = new ConfirmationForm();
        confirmationForm.clickOnBackToSiteBtn();

        topForm.clickOnNewsLetter();
        newsLetterForm.reSelectSubject();



    }
}


