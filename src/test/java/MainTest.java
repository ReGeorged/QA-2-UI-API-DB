import aquality.selenium.browser.AqualityServices;
import com.google.common.collect.Ordering;
import constants.ConfigurationData;
import forms.*;
import helpers.PortalHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.TestPojo;
import utils.BrowserUtils;
import utils.FileUtils;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class MainTest extends BaseTest {

    private static String projectName = ConfigurationData.NEW_PROJECT_NAME.getData();

    @Test(enabled = false)
    public static void testUnionReporting() {
        List<TestPojo> apiTestList = PortalHelper.getTestPojoList();


        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isDisplayed(), "Home page not open");
        BrowserUtils.setCookie("token", PortalHelper.getCookie());
        AqualityServices.getBrowser().getDriver().navigate().refresh();
        String ver = homePage.getVersion();
        Assert.assertEquals(ver, "Version: 2", "Versions do not match");
        homePage.clickOnNexage();

        ReportForm reportForm = new ReportForm();
        Assert.assertTrue(reportForm.isDisplayed(), "Report form not open");
        Assert.assertEquals(reportForm.getReportName(), "Nexage", "Incorrect report form is opened");
        ReportForm notLazyReportForm = new ReportForm();

        List webStartDateList = new ArrayList();
        for (int i = 0; i < notLazyReportForm.getTestListSize(); i++) {
            webStartDateList.add(StringUtils.dateStringToMillis(notLazyReportForm.getStartDateByIndex(i)));
        }
        List apiStartDateList = new ArrayList<>();
        for (int i = 0; i < apiTestList.size(); i++) {
            apiStartDateList.add(StringUtils.dateStringToMillis(apiTestList.get(i).getStartTime()));
        }
        Assert.assertTrue(Ordering.natural().reverse().isOrdered(webStartDateList), "Start date list is not in descending order");
        Assert.assertTrue(apiStartDateList.containsAll(webStartDateList), "Api tests list and web tests list do not match");

        AqualityServices.getBrowser().getDriver().navigate().back();
        Assert.assertTrue(homePage.isDisplayed(), "Home page is not open");

        homePage.clickAddBtn();
        AddProjectFrom addProjectFrom = new AddProjectFrom();
        BrowserUtils.switchWindowsByIndex(1);
        Assert.assertTrue(addProjectFrom.isDisplayed(), "Add new project form is not open");
        addProjectFrom.createNewProjectAndSave(projectName);
        Assert.assertTrue(addProjectFrom.isProjectSaved(), "New project was not saved");
        BrowserUtils.switchWindowsByIndex(0);
        Assert.assertTrue(homePage.isDisplayed(), "Home page is not open");

        AqualityServices.getBrowser().getDriver().navigate().refresh();
        List<String> projectsList = homePage.getProjectsNameList();
        Assert.assertTrue(projectsList.contains(projectName), "Newly created project is not in all projects list");

        int index = projectsList.indexOf(projectName);
        homePage.clickOnProjectViaIndex(index);
        Assert.assertTrue(reportForm.isDisplayed());
        String oldCountString = reportForm.getAllRunningTestsAsString();
        String log = FileUtils.logToString();
        PortalHelper.createNewTestWithLogAndScreenshot(
                String.valueOf(BaseTest.randomSessionId),
                projectName,
                "9",
                "9",
                "randomEnv",
                log,
                BrowserUtils.takeScreenAs64String(),
                200
        );
        reportForm.waitForAddBtnToDisappear();
        reportForm.waitForAddBtnAppear();
        Assert.assertNotEquals(reportForm.getAllRunningTestsAsString(), oldCountString, "New test was not added - number of tests did not change");
    }


}
