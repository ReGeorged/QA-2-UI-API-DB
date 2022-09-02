package constants;

import java.util.HashMap;

public class PortalRestHashMaps {

    public static HashMap authForToken() {
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
}
