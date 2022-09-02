import aquality.selenium.browser.AqualityServices;
import forms.HomePage;
import forms.ReportForm;
import helpers.PortalHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest extends BaseTest {
    @Test
    public static void asda(){

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isDisplayed(),"Home page not open");
        homePage.setCookie("token", PortalHelper.getCookie());
        AqualityServices.getBrowser().getDriver().navigate().refresh();
        String ver = homePage.getVersion();
        Assert.assertEquals(ver,"Version: 2","Versions dont match");
        homePage.clickOnNexage();
        ReportForm reportForm = new ReportForm();
        Assert.assertTrue(reportForm.isDisplayed(),"Report form not open");
        Assert.assertEquals(reportForm.getReportName(),"Nexage","Incorrect report form is opened");
        System.out.println("list size is "+ reportForm.getTestListSize());
    }
}
