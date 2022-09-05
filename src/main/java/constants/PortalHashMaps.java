package constants;

import java.util.HashMap;

public class PortalHashMaps {

    public static HashMap auth() {
        HashMap<String, Object> authParams = new HashMap();
        authParams.put("Username", ConfigurationData.USERNAME.getData());
        authParams.put("Password", ConfigurationData.PWORD.getData());
        return authParams;
    }

    public static HashMap setParamVariant() {
        HashMap<String, Object> variantHashMap = new HashMap();
        variantHashMap.put("variant", ConfigurationData.VARIANT.getData());
        return variantHashMap;
    }

    public static HashMap setProjectId() {
        HashMap<String, Object> authParams = new HashMap();
        authParams.put("projectId", ConfigurationData.PROJECT_ID.getData());
        return authParams;
    }

    public static HashMap createNewTestParams(Object sId, Object projectName, Object testName, Object methodName, Object env) {
        HashMap<String, Object> testDetails = new HashMap();
        testDetails.put("SID", sId);
        testDetails.put("projectName", projectName);
        testDetails.put("testName", testName);
        testDetails.put("methodName", methodName);
        testDetails.put("env", env);
        return testDetails;
    }

    public static HashMap putLogParams(Object testId, Object content) {
        HashMap<String, Object> logs = new HashMap();
        logs.put("testId", testId);
        logs.put("content", content);
        return logs;
    }

    public static HashMap putAttachmentParams(Object testId, Object content, Object contentType) {
        HashMap<String, Object> attach = new HashMap();
        attach.put("testId", testId);
        attach.put("content", content);
        attach.put("contentType", contentType);
        return attach;
    }
}
