package constants;

public class EndPoints {

    public static final String GET_TOKEN = "http://localhost:8080/api/token/get";
    public static final String GET_JSON_RESPONSE = "http://localhost:8080/api/test/get/json";
    public static final String CREATE_NEW_TEST = "http://localhost:8080/api/test/put";
    public static final String PUT_LOG = "http://localhost:8080/api/test/put/log";
    public static final String PUT_ATTACHMENT = "http://localhost:8080/api/test/put/attachment";


    public static String authorizedWebLink() {
        StringBuilder authorisationLink = new StringBuilder("http://")
                .append(ConfigurationData.USERNAME.getData()).append(":")
                .append(ConfigurationData.PWORD.getData()).append("@").append(ConfigurationData.URL.getData());
        return authorisationLink.toString();
    }
}
