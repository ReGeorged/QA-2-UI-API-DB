import base.BrowserBase;
import forms.*;
import helpers.GmailApi;
import helpers.PojoHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Mail;
import utils.FileUtils;
import utils.IntUtils;
import utils.StringUtils;


public class MainTest extends BaseTest {


    @Test
    public void euroNews() {
//step 1
        HomePage homePage = new HomePage();
        homePage.clickOnPrivacyBtn();
        Assert.assertTrue(homePage.isDisplayed(),"home page not open");
        TopForm topForm = new TopForm();
        topForm.clickOnNewsLetter();
//step 2
        NewsLetterForm newsLetterForm = new NewsLetterForm();
        Assert.assertTrue(newsLetterForm.isDisplayed(),"homepage not found");

        int randomNewsLetter = IntUtils.getRandInRange(0, newsLetterForm.getNewsLetterAmount());
        if (randomNewsLetter == 0 || randomNewsLetter == 2 || randomNewsLetter == 7) {
            randomNewsLetter = IntUtils.getRandInRange(0, newsLetterForm.getNewsLetterAmount());

        }
        //step 3
        newsLetterForm.selectRandomLetter(randomNewsLetter);
        Assert.assertTrue(newsLetterForm.checkEmailInputField(),"email input field not found");
        newsLetterForm.sendKeysToEmail(FileUtils.readFromJson("configData.json", "/email"));

        String refreshedToken = GmailApi.refreshBearerAccessToken();
        String response = GmailApi.getAllMail(refreshedToken, 200).asString();
        String id = PojoHelper.jsonPojoHelper(response, Mail.class).getMessages().get(0).getId();
        String body = GmailApi.getSpecificMailBodyViaId(refreshedToken, id, 200);

        //step 4
        Assert.assertTrue(body.contains("<title>Euronews"),"email response has incorrect title");
        Assert.assertTrue(body.contains("Please Confirm Subscription</title>"),"incorrect email");


//step5
        BrowserBase.initialize().goTo(StringUtils.extractLinkFromMail(body));
        ConfirmationForm confirmationForm = new ConfirmationForm();
        Assert.assertTrue(confirmationForm.isDisplayed(),"newsletter confirmation page not found");
        confirmationForm.clickOnBackToSiteBtn();
        //step6
        Assert.assertTrue(homePage.isDisplayed(),"home page is not open");
        topForm.clickOnNewsLetter();
        newsLetterForm.selectRandomPreview(randomNewsLetter);
        //step 7
        Assert.assertTrue(newsLetterForm.checkPreviewOpen(),"preview is not open");
        newsLetterForm.switchToIFrame(7);
        String unsubscribeLink = newsLetterForm.getUnsubscribeLink();
        BrowserBase.initialize().goTo(unsubscribeLink);

        UnsubscribeForm unsubscribeForm = new UnsubscribeForm();
        Assert.assertTrue(unsubscribeForm.isDisplayed(),"unsubscribe page not found");
        unsubscribeForm.sendEmail(FileUtils.readFromJson("configData.json", "/email"));
        unsubscribeForm.clickUnsubscribe();
        //step 8
        Assert.assertTrue(unsubscribeForm.checkUnsubscribe(),"unsubscribed -- confirmation not found");


        String response2 = GmailApi.getAllMail(refreshedToken, 200).asString();
        String id2 = PojoHelper.jsonPojoHelper(response2, Mail.class).getMessages().get(0).getId();
        String body2 = GmailApi.getSpecificMailBodyViaId(refreshedToken, id2, 200);
        //step 9
        Assert.assertTrue(body.equals(body2),"last emails dont match ");



    }
}


