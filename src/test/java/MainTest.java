import forms.TopForm;
import org.testng.Assert;
import org.testng.annotations.Test;
import forms.HomePage;

public class MainTest extends BaseTest   {


    @Test
    public void demoTest(){
        HomePage homePage = new HomePage();
        homePage.clickOnPrivacyBtn();
        Assert.assertTrue(homePage.isDisplayed());
        TopForm topForm = new TopForm();
        topForm.clickOnNewsLetter();

    }

}
