package constants;

public class EndPoints {

    public static final String getToken = "http://localhost:8080/api/token/get";


    public static final String getJsonResponse = "http://localhost:8080/api/test/get/json";



    public static String getLink(){
        StringBuilder authorisationLink = new StringBuilder("http://")
                .append(ConfigurationData.Username.getData()).append(":")
                .append(ConfigurationData.Pword.getData()).append("@").append(ConfigurationData.Url.getData());
        return authorisationLink.toString();
    }
}
