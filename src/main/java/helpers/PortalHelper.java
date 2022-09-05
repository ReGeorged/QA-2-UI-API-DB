package helpers;

import constants.EndPoints;
import constants.PortalHashMaps;
import pojo.TestPojo;

import java.util.List;


public class PortalHelper {

    public static String getCookie() {
        String token = RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(), PortalHashMaps.setParamVariant(), EndPoints.GET_TOKEN, 200).asString();
        return token;
    }


    public static List<TestPojo> getTestPojoList() {
        List<TestPojo> responseList = PojoHelper.pojoCallPostAsList(EndPoints.GET_JSON_RESPONSE, 200, TestPojo.class);
        return responseList;
    }


    private static String createNewTestAndGetId(Object sId, Object projectName, Object testName, Object methodName, Object env, int expectedCode) {
        String id = RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(), PortalHashMaps.createNewTestParams(sId, projectName, testName, methodName, env), EndPoints.CREATE_NEW_TEST, expectedCode).asString();
        return id;
    }

    private static void putLog(Object id, Object content, int expectedCode) {
        RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(), PortalHashMaps.putLogParams(id, content), EndPoints.PUT_LOG, expectedCode);
    }

    private static void putScreenshot(Object id, Object screenByte64, int expectedCode) {
        RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(), PortalHashMaps.putAttachmentParams(id, screenByte64, "image/png"), EndPoints.PUT_ATTACHMENT, expectedCode);
    }

    public static void createNewTestWithLogAndScreenshot(String sId, String projectName, String testName, String methodName, String env, String content, String attachContent, int expectedCode) {
        String id = createNewTestAndGetId(sId, projectName, testName, methodName, env, expectedCode);
        putLog(id, content, expectedCode);
        putScreenshot(id, attachContent, expectedCode);
    }


}
