import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.actions.MouseActions;
import com.google.common.collect.Ordering;
import forms.AddProjectFrom;
import forms.HomePage;
import forms.ReportForm;
import helpers.PortalHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.TestPojo;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class MainTest extends BaseTest {

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

        List webStartDateList = new ArrayList();
        for(int i=0;i<notLazyReportForm.getTestListSize();i++){
            webStartDateList.add(StringUtils.dateStringToMillis(notLazyReportForm.getStartDateByIndex(i)));
        }
        Assert.assertTrue(Ordering.natural().reverse().isOrdered(webStartDateList),"Start date list is not in reverse order");

        List<TestPojo> actualList =PortalHelper.getTestPojoList();
        System.out.println(actualList.size());
        List apiStartDateList = new ArrayList<>();
        for (int i=0;i<actualList.size();i++){
            if(i< notLazyReportForm.getTestListSize()){
                apiStartDateList.add(StringUtils.dateStringToMillis(actualList.get(i).getStartTime()));
            }
        }

        try{
            Assert.assertEquals(webStartDateList,apiStartDateList,"Web start date and api start dates do not match(in millis)");

        }catch (AssertionError e){
            e.printStackTrace();
        }

        AqualityServices.getBrowser().getDriver().navigate().back();
        Assert.assertTrue(homePage.isDisplayed());
        homePage.clickAddBtn();
        AddProjectFrom addProjectFrom = new AddProjectFrom();
        addProjectFrom.addProject("djakdsada");
        addProjectFrom.clickSaveProjectBtn();
    }

    @Test
    public void testapi() {

        List<TestPojo> actualList =PortalHelper.getTestPojoList();
        System.out.println(actualList.size());
        ArrayList<String> newList = new ArrayList<>();
        for (int i=0;i<actualList.size();i++){
            newList.add(actualList.get(i).getStartTime());
        }
        System.out.println(newList);

    }

    @Test
    public void testAddition(){
        HomePage homePage = new HomePage();
        homePage.clickAddBtn();
        AddProjectFrom addProjectFrom = new AddProjectFrom();
        addProjectFrom.addProject("sdadadsassdssdssdassssdsa");
        addProjectFrom.clickSaveProjectBtn();
        Assert.assertTrue(addProjectFrom.isProjectSaved());
        String confirmationMessage = AqualityServices.getBrowser().getDriver().getWindowHandle();
        AqualityServices.getBrowser().getDriver().executeScript("window","close");
        homePage.moveMouseAndClick();
    }
}
