package constants;

public class Endpoints {
    public static final String gmailApi = "https://accounts.google.com/o/oauth2/v2/auth";
    public static final String gmailMessages = "https://gmail.googleapis.com/gmail/v1/users/me/messages/";
    public static final String gmailOath = "https://oauth2.googleapis.com/token";
    public static String scope = "https://mail.google.com/";

    public static String getGoogleCodeUrl(String client_id, String redirect_uri) {
        String finalUrl = gmailApi + "?scope=" + scope + "&access_type=offline&include_granted_scopes=true&response_type=code&redirect_uri=" + redirect_uri + "&client_id=" + client_id;
        return finalUrl;
    }

}
