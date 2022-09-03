import aquality.selenium.browser.AqualityServices;
import forms.HomePage;
import forms.ReportForm;
import helpers.PortalHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.TestPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class MainTest {
    @Test
    public static void asda() throws InterruptedException {

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isDisplayed(), "Home page not open");
        homePage.setCookie("token", PortalHelper.getCookie());
        AqualityServices.getBrowser().getDriver().navigate().refresh();
        String ver = homePage.getVersion();
        Assert.assertEquals(ver, "Version: 2", "Versions dont match");
        homePage.clickOnNexage();
        ReportForm reportForm = new ReportForm();
        Assert.assertTrue(reportForm.isDisplayed(), "Report form not open");
        Assert.assertEquals(reportForm.getReportName(), "Nexage", "Incorrect report form is opened");
        ReportForm notLazyReportForm = new ReportForm();
        System.out.println(notLazyReportForm.getTestListSize());
    }

    @Test
    public void testapi() {

        List<TestPojo> actualList =PortalHelper.getTestPojoList();
        System.out.println(actualList.size());
        ArrayList<String> newList = new ArrayList<>();
        for (int i=0;i<actualList.size();i++){
            newList.add(actualList.get(i).getName());
        }
        System.out.println(newList);

    }
}
