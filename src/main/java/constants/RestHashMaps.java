package constants;

import java.util.HashMap;

public class RestHashMaps {

    public static HashMap gmailRefreshParamsMap() {
        HashMap<String, String> refreshTokenParams = new HashMap();
        refreshTokenParams.put("client_id", Enums.CLIENT_ID.getData());
        refreshTokenParams.put("client_secret", Enums.CLIENT_SECRET.getData());
        refreshTokenParams.put("refresh_token", Enums.REFRESH_TOKEN.getData());
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
