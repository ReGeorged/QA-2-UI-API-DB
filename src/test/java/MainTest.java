import aquality.selenium.browser.AqualityServices;
import forms.*;
import helpers.RestfullHelper;
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
        HomePage homePage = new HomePage();
        homePage.clickOnPrivacyBtn();
        Assert.assertTrue(homePage.isDisplayed(), "home page not open");
        TopForm topForm = new TopForm();
        topForm.clickOnNewsLetter();

        NewsLetterForm newsLetterForm = new NewsLetterForm();
        Assert.assertTrue(newsLetterForm.isDisplayed(), "homepage not found");

        int randomNewsLetter = IntUtils.getRandInRange(0, newsLetterForm.getNewsLetterAmount());
        if (randomNewsLetter == 0 || randomNewsLetter == 2 || randomNewsLetter == 7) {
            randomNewsLetter = IntUtils.getRandInRange(0, newsLetterForm.getNewsLetterAmount());

        }
        newsLetterForm.selectRandomLetter(randomNewsLetter);
        Assert.assertTrue(newsLetterForm.isEmailFieldDisplayed(), "email input field not found");
        newsLetterForm.submitEmail(FileUtils.readFromJson("configData.json", "/email"));

        String refreshedToken = RestfullHelper.refreshBearerAccessToken(200);
        String response = RestfullHelper.getAllMail(refreshedToken, 200).asString();
        String id = PojoHelper.jsonPojoHelper(response, Mail.class).getMessages().get(0).getId();
        String body = RestfullHelper.getSpecificMailBodyViaId(refreshedToken, id, 200);

        Assert.assertTrue(body.contains("<title>Euronews"), "email response has incorrect title");
        Assert.assertTrue(body.contains("Please Confirm Subscription</title>"), "incorrect email");

        AqualityServices.getBrowser().goTo(StringUtils.extractLinkFromMail(body));
        ConfirmationForm confirmationForm = new ConfirmationForm();
        Assert.assertTrue(confirmationForm.isDisplayed(), "newsletter confirmation page not found");
        confirmationForm.clickOnBackToSiteBtn();
        Assert.assertTrue(homePage.isDisplayed(), "home page is not open");
        topForm.clickOnNewsLetter();
        newsLetterForm.selectRandomPreview(randomNewsLetter);

        Assert.assertTrue(newsLetterForm.checkPreviewOpen(), "preview is not open");
        newsLetterForm.waitForCloseButtonInPreview();
        AqualityServices.getBrowser().getDriver().switchTo().frame(7);
        String unsubscribeLink = newsLetterForm.getUnsubscribeLink();
        AqualityServices.getBrowser().goTo(unsubscribeLink);

        UnsubscribeForm unsubscribeForm = new UnsubscribeForm();
        Assert.assertTrue(unsubscribeForm.isDisplayed(), "unsubscribe page not found");
        unsubscribeForm.sendEmail(FileUtils.readFromJson("configData.json", "/email"));
        unsubscribeForm.clickUnsubscribeBtn();
        Assert.assertTrue(unsubscribeForm.isUnsubscribeMessageDisplayed(), "unsubscribed -- confirmation not found");

        String response2 = RestfullHelper.getAllMail(refreshedToken, 200).asString();
        String id2 = PojoHelper.jsonPojoHelper(response2, Mail.class).getMessages().get(0).getId();
        String body2 = RestfullHelper.getSpecificMailBodyViaId(refreshedToken, id2, 200);
        Assert.assertTrue(body.equals(body2), "last emails dont match ");
    }
}


