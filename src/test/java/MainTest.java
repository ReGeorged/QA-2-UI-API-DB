import forms.ConfirmationForm;
import forms.NewsLetterForm;
import forms.TopForm;
import org.testng.Assert;
import org.testng.annotations.Test;
import forms.HomePage;

public class MainTest extends BaseTest {


    @Test
    public void demoTest() {
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

}
