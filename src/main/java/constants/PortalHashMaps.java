package constants;

import java.util.HashMap;

public class PortalHashMaps {

    public static HashMap auth() {
        HashMap<String, String> authParams = new HashMap();
        authParams.put("Username", ConfigurationData.Username.getData());
        authParams.put("Password", ConfigurationData.Pword.getData());
        return authParams;
    }

    public static HashMap setParamVariant() {
        HashMap<String, String> variantHashMap = new HashMap();
        variantHashMap.put("variant", ConfigurationData.Variant.getData());

        return variantHashMap;
    }

    public static HashMap setProjectId() {
        HashMap<String, String> authParams = new HashMap();
        authParams.put("projectId", ConfigurationData.ProjectId.getData());
        return authParams;
    }

    public static HashMap createNewTestParams(String sId, String projectName, String testName, String methodName, String env) {
        HashMap<String, String> testDetails = new HashMap();
        testDetails.put("SID", sId);
        testDetails.put("projectName", projectName);
        testDetails.put("testName", testName);
        testDetails.put("methodName", methodName);
        testDetails.put("env", env);
        return testDetails;
    }

    public static HashMap putLog(String testId, String content) {
        HashMap<String, String> logs = new HashMap();
        logs.put("testId", testId);
        logs.put("content", content);
        return logs;
    }

    public static HashMap putAttachment(String testId, Object content, String contentType) {
        HashMap<String, Object> attach = new HashMap();
        attach.put("testId", testId);
        attach.put("content", content);
        attach.put("contentType", contentType);
        return attach;
    }
}
