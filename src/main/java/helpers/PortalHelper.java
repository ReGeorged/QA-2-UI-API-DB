package helpers;

import constants.EndPoints;
import constants.PortalHashMaps;
import forms.AddTestForm;
import pojo.TestPojo;
import utils.BrowserUtils;
import utils.FileUtils;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PortalHelper {

    public static String getCookie() {
        String token = RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(), PortalHashMaps.setParamVariant(), EndPoints.getToken, 200).asString();
        return token;
    }


    public static List<TestPojo> getTestPojoList() {
        List<TestPojo> responseList = PojoHelpepr.pojoCallPostAsList(EndPoints.getJsonResponse, 200, TestPojo.class);
        return responseList;
    }

    @Deprecated
    public static void fillWebFormFromApiViaIndex(int index) {
        TestPojo tstPojo = getTestPojoList().get(index);
        AddTestForm addTestForm = new AddTestForm();
        addTestForm.fillFormAndSave(tstPojo.getName(), tstPojo.getMethod(), tstPojo.getStartTime(), tstPojo.getEndTime(), "randomEnv", "randBrowser", FileUtils.logToString(), tstPojo.getStatus());
    }


    public static String createNewTestAndGetId(String sId,String projectName,String testName, String methodName, String env ){
        String id = RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(),PortalHashMaps.createNewTestParams(sId,projectName,testName,methodName,env),EndPoints.createNewTest,200).asString();
        return id;
    }

    public static void putLog(String id,String content){
        RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(),PortalHashMaps.putLog(id,content),EndPoints.putLog,200);


    }
    public static void putScreenshot(String id, Object screenByte64){
        RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(),PortalHashMaps.putAttachment(id,screenByte64,"image/png"),EndPoints.putAttachment,200);
    }

    public static void createNewTestWithLogAndAttachment(String sId,String projectName,String testName, String methodName, String env,String content,Object attachContent){
        String id = createNewTestAndGetId(sId,projectName,testName,methodName,env);
        putLog(id,content);
        putScreenshot(id,attachContent);
    }




}
