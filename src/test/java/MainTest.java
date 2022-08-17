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
        BrowserBase.initialize().goTo(FileUtils.readFromJson("configData.json", "/url"));
        BrowserBase.initialize().waitForPageToLoad();

        HomePage homePage = new HomePage();
        homePage.clickOnPrivacyBtn();
        Assert.assertTrue(homePage.isDisplayed());

        TopForm topForm = new TopForm();
        topForm.clickOnNewsLetter();
        NewsLetterForm newsLetterForm = new NewsLetterForm();
        int randomNewsLetter = IntUtils.getRandInRange(0, newsLetterForm.getNewsLetterAmount());
        newsLetterForm.selectRandomLetter(randomNewsLetter);
        newsLetterForm.sendKeysToEmail(FileUtils.readFromJson("configData.json", "/email"));

        String refreshedToken = GmailApi.refreshBearerAccessToken();
        String response = GmailApi.getAllMail(refreshedToken, 200).asString();
        String id = PojoHelper.jsonPojoHelper(response, Mail.class).getMessages().get(0).getId();
        String body = GmailApi.getSpecificMailBodyViaId(refreshedToken, id, 200);

        BrowserBase.initialize().goTo(StringUtils.extractLinkFromMail(body));
        ConfirmationForm confirmationForm = new ConfirmationForm();
        confirmationForm.clickOnBackToSiteBtn();

        topForm.clickOnNewsLetter();
        newsLetterForm.selectRandomPreview(randomNewsLetter);
    }
}


