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
}
