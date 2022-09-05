package constants;

public class EndPoints {

    public static final String getToken = "http://localhost:8080/api/token/get";
    public static final String getJsonResponse = "http://localhost:8080/api/test/get/json";
    public static final String createNewTest = "http://localhost:8080/api/test/put";
    public static final String putLog = "http://localhost:8080/api/test/put/log";
    public static final String putAttachment = "http://localhost:8080/api/test/put/attachment";


    public static String authorizedWebLink(){
        StringBuilder authorisationLink = new StringBuilder("http://")
                .append(ConfigurationData.USERNAME.getData()).append(":")
                .append(ConfigurationData.PWORD.getData()).append("@").append(ConfigurationData.URL.getData());
        return authorisationLink.toString();
    }
}
