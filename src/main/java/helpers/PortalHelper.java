package helpers;

import constants.EndPoints;
import constants.PortalHashMaps;
import pojo.TestPojo;

import java.util.List;


public class PortalHelper {

    public static String getCookie() {
        String token = RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(), PortalHashMaps.setParamVariant(), EndPoints.getToken, 200).asString();
        return token;
    }


    public static List<TestPojo> getTestPojoList() {
        List<TestPojo> responseList = PojoHelper.pojoCallPostAsList(EndPoints.getJsonResponse, 200, TestPojo.class);
        return responseList;
    }


    private static String createNewTestAndGetId(String sId, String projectName, String testName, String methodName, String env, int expectedCode) {
        String id = RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(), PortalHashMaps.createNewTestParams(sId, projectName, testName, methodName, env), EndPoints.createNewTest, expectedCode).asString();
        return id;
    }

    private static void putLog(String id, String content, int expectedCode) {
        RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(), PortalHashMaps.putLogParams(id, content), EndPoints.putLog, expectedCode);
    }

    private static void putScreenshot(String id, Object screenByte64, int expectedCode) {
        RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(), PortalHashMaps.putAttachmentParams(id, screenByte64, "image/png"), EndPoints.putAttachment, expectedCode);
    }

    public static void createNewTestWithLogAndScreenshot(String sId, String projectName, String testName, String methodName, String env, String content, Object attachContent, int expectedCode) {
        String id = createNewTestAndGetId(sId, projectName, testName, methodName, env, expectedCode);
        putLog(id, content, expectedCode);
        putScreenshot(id, attachContent, expectedCode);
    }


}
