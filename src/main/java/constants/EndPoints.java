package constants;

public class EndPoints {

    public static final String getToken = "http://localhost:8080/api/token/get";

    public static String getLink(){
        StringBuilder authorisationLink = new StringBuilder("http://")
                .append(ConfigurationData.Username.getData()).append(":")
                .append(ConfigurationData.Pword.getData()).append("@localhost:8080/web");
        return authorisationLink.toString();
    }
}
