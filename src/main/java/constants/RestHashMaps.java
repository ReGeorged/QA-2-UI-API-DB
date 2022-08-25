package constants;

import java.util.HashMap;

public class RestHashMaps {

    public static HashMap gmailRefreshParamsMap() {
        HashMap<String, String> refreshTokenParams = new HashMap();
        refreshTokenParams.put("client_id", ConfigurationData.CLIENT_ID.getData());
        refreshTokenParams.put("client_secret", ConfigurationData.CLIENT_SECRET.getData());
        refreshTokenParams.put("refresh_token", ConfigurationData.REFRESH_TOKEN.getData());
        refreshTokenParams.put("grant_type", "refresh_token");
        return refreshTokenParams;
    }

    public static HashMap headerXWwwMap() {
        HashMap<String, String> headerXWWW = new HashMap();
        headerXWWW.put("Content-Type", "application/x-www-form-urlencoded");
        return headerXWWW;
    }

    public static HashMap tokenHeaderMap(String token) {
        HashMap<String, String> header = new HashMap();
        header.put("Authorization", "Bearer " + token);
        header.put("Content-Type", "application/json");
        return header;
    }
}
