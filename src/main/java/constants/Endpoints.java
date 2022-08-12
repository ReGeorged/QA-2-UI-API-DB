package constants;

import utils.FileUtils;

public class Endpoints {

    public static final String gmailApi = "https://accounts.google.com/o/oauth2/v2/auth";
    public static String scope = "https://www.googleapis.com/auth/gmail.readonly";

    public static String getGoogleCodeUrl(String client_id, String redirect_uri) {
        String finalUrl =gmailApi+scope+"&access_type=offline&include_granted_scopes=true&response_type=code&redirect_uri="+redirect_uri+"&client_id="+client_id;
        return finalUrl;
    }
}
