package constants;

import helpers.BearerAccessToken;
import utils.FileUtils;

public class Endpoints {

    public static final String gmailApi = "https://accounts.google.com/o/oauth2/v2/auth";
    public static String scope = "https://mail.google.com/";

    public static String getGoogleCodeUrl(String client_id, String redirect_uri) {
        String finalUrl =gmailApi+"?scope="+scope+"&access_type=offline&include_granted_scopes=true&response_type=code&redirect_uri="+redirect_uri+"&client_id="+client_id;
        return finalUrl;
    }

    public static void main(String[] args) {

        String code = "4/0AdQt8qgDgyXUGJ8vrZjeaxoeLfQSG8MVQXy5X1yDcSGIViQ-TM1W9aMEpT1w-b-fZ6SZaA";
        String token = BearerAccessToken.getBearerAccessToken(code,"561943751603-pal968j1pchounj6qkqbmi2b7mbv9q48.apps.googleusercontent.com",
                "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","http://localhost").asString();
        System.out.println(token);
    }
}
