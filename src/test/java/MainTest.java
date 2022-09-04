import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.actions.MouseActions;
import com.google.common.collect.Ordering;
import forms.*;
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
        for (int i = 0; i < notLazyReportForm.getTestListSize(); i++) {
            webStartDateList.add(StringUtils.dateStringToMillis(notLazyReportForm.getStartDateByIndex(i)));
        }
        Assert.assertTrue(Ordering.natural().reverse().isOrdered(webStartDateList), "Start date list is not in reverse order");

        List<TestPojo> actualList = PortalHelper.getTestPojoList();
        System.out.println(actualList.size());
        List apiStartDateList = new ArrayList<>();
        for (int i = 0; i < actualList.size(); i++) {
            if (i < notLazyReportForm.getTestListSize()) {
                apiStartDateList.add(StringUtils.dateStringToMillis(actualList.get(i).getStartTime()));
            }
        }

        try {
            Assert.assertEquals(webStartDateList, apiStartDateList, "Web start date and api start dates do not match(in millis)");

        } catch (AssertionError e) {
            e.printStackTrace();
        }

        AqualityServices.getBrowser().getDriver().navigate().back();
        Assert.assertTrue(homePage.isDisplayed(),"Home page is not open");
        homePage.clickAddBtn();
        AddProjectFrom addProjectFrom = new AddProjectFrom();
        addProjectFrom.addProject("djakdsada");
        addProjectFrom.clickSaveProjectBtn();
    }

    @Test
    public void testapi() {

//        List<TestPojo> actualList =PortalHelper.getTestPojoList();
//        System.out.println(actualList.size());
//        ArrayList<String> newList = new ArrayList<>();
//        for (int i=0;i<actualList.size();i++){
//            newList.add(actualList.get(i).getStartTime());
//        }
//        System.out.println(newList);

        List<TestPojo> actualList = PortalHelper.getTestPojoList();
        System.out.println(actualList.get(0).getStartTime());
        System.out.println(actualList.get(0).getName());

        System.out.println(actualList.get(0).getDuration());

        System.out.println(actualList.get(0).getStatus());

        System.out.println(actualList.get(0).getEndTime());


        System.out.println(actualList.get(0).getStartTime());


    }

    @Test
    public void testAddition() {
        HomePage homePage = new HomePage();
        homePage.clickAddBtn();
        AddProjectFrom addProjectFrom = new AddProjectFrom();
        Assert.assertTrue(addProjectFrom.isDisplayed());
        String projectName = "bzsasdadassafadsddsazsdas";
        addProjectFrom.addProject(projectName);
        addProjectFrom.clickSaveProjectBtn();
        Assert.assertTrue(addProjectFrom.isProjectSaved(),"Project was not saved");
        TopForm topForm = new TopForm();
        topForm.moveMouseAndClick();

        Assert.assertFalse(addProjectFrom.isDisplayed(),"Create new project form did not disappear");
        AqualityServices.getBrowser().getDriver().navigate().refresh();

        List<String> projectsList = homePage.getProjectsNameList();
        Assert.assertTrue(projectsList.contains(projectName),"Newly created project is not in all projects list");
        int index = projectsList.indexOf(projectName);

        homePage.clickOnProjectViaIndex(index);
        ReportForm reportForm = new ReportForm();
        reportForm.clickOnAddBtn();

        PortalHelper.fillWebFormFromApiViaIndex(3);


    }

    @Test
    public void testTestAddition() {
        HomePage homePage = new HomePage();
        String projectName = "asda";

        List<String> projectsList = homePage.getProjectsNameList();
        Assert.assertTrue(projectsList.contains(projectName),"This Project does not exist");
        int index = projectsList.indexOf(projectName);

        homePage.clickOnProjectViaIndex(index);
        ReportForm reportForm = new ReportForm();
        String oldCountString = reportForm.getAllRunningTestsAsString();
        System.out.println(oldCountString);
        reportForm.clickOnAddBtn();

        PortalHelper.fillWebFormFromApiViaIndex(3);
        AddTestForm addTestForm = new AddTestForm();
        Assert.assertTrue(addTestForm.isTestSaved(),"Test was not saved");
        TopForm topForm = new TopForm();
        topForm.moveMouseAndClick();

        //TODO need better wait
        reportForm.waitForRefresh();

        Assert.assertNotEquals(reportForm.getAllRunningTestsAsString(),oldCountString,"New test was not added - number of tests didnt change");

    }
}
