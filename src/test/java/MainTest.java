import aquality.selenium.browser.AqualityServices;
import constants.Enums;
import forms.*;
import helpers.RestfullHelper;
import helpers.PojoHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Mail;
import utils.IntUtils;
import utils.StringUtils;

public class MainTest extends BaseTest {

    @Test
    public void euroNews() {
        HomePage homePage = new HomePage();
        homePage.clickOnPrivacyBtn();
        Assert.assertTrue(homePage.isDisplayed(), "Home page is not open");
        TopForm topForm = new TopForm();
        topForm.clickOnNewsLetter();

        NewsLetterForm newsLetterForm = new NewsLetterForm();
        Assert.assertTrue(newsLetterForm.isDisplayed(), "Homepage not found");

        int randomNewsLetter = IntUtils.getRandInRange(0, newsLetterForm.getNewsLetterAmount());
        if (randomNewsLetter == 0 || randomNewsLetter == 2 || randomNewsLetter == 7) {
            randomNewsLetter = IntUtils.getRandInRange(0, newsLetterForm.getNewsLetterAmount());

        }
        newsLetterForm.selectRandomLetter(randomNewsLetter);
        Assert.assertTrue(newsLetterForm.isEmailFieldDisplayed(), "Email input field not found");
        newsLetterForm.submitEmail(Enums.EMAIL.getData());

        String refreshedToken = RestfullHelper.refreshBearerAccessToken(200);
        String response = RestfullHelper.getAllMail(refreshedToken, 200).asString();
        String id = PojoHelper.jsonPojoHelper(response, Mail.class).getMessages().get(0).getId();
        String body = RestfullHelper.getSpecificMailBodyViaId(refreshedToken, id, 200);

        Assert.assertTrue(body.contains("<title>Euronews"), "Email response has incorrect title");
        Assert.assertTrue(body.contains("Please Confirm Subscription</title>"), "Incorrect email");

        AqualityServices.getBrowser().goTo(StringUtils.extractLinkFromMail(body));
        ConfirmationForm confirmationForm = new ConfirmationForm();
        Assert.assertTrue(confirmationForm.isDisplayed(), "Newsletter confirmation page not found");
        confirmationForm.clickOnBackToSiteBtn();
        Assert.assertTrue(homePage.isDisplayed(), "Home page is not open");
        topForm.clickOnNewsLetter();

        PreviewForm previewForm = newsLetterForm.selectRandomPreview(randomNewsLetter);
        Assert.assertTrue(previewForm.isDisplayed(), "Preview is not open");
        Assert.assertTrue(previewForm.isCloseBtnDisplayed(), "Close button is not displayed");
        AqualityServices.getBrowser().getDriver().switchTo().frame(7);
        String unsubscribeLink = previewForm.getUnsubscribeLink();
        AqualityServices.getBrowser().goTo(unsubscribeLink);

        UnsubscribeForm unsubscribeForm = new UnsubscribeForm();
        Assert.assertTrue(unsubscribeForm.isDisplayed(), "Unsubscribe page not found");
        unsubscribeForm.sendEmail(Enums.EMAIL.getData());
        unsubscribeForm.clickUnsubscribeBtn();
        Assert.assertTrue(unsubscribeForm.isUnsubscribeMessageDisplayed(), "Unsubscribed -- confirmation not found");

        String response2 = RestfullHelper.getAllMail(refreshedToken, 200).asString();
        String id2 = PojoHelper.jsonPojoHelper(response2, Mail.class).getMessages().get(0).getId();
        String body2 = RestfullHelper.getSpecificMailBodyViaId(refreshedToken, id2, 200);
        Assert.assertTrue(body.equals(body2), "Last emails dont match ");
    }
}


