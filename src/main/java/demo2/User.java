package demo2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Data
public class User {


    public String json="{\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Leanne Graham\",\n" +
            "    \"username\": \"Bret\",\n" +
            "    \"email\": \"Sincere@april.biz\",\n" +
            "    \"address\": {\n" +
            "      \"street\": \"Kulas Light\",\n" +
            "      \"suite\": \"Apt. 556\",\n" +
            "      \"city\": \"Gwenborough\",\n" +
            "      \"zipcode\": \"92998-3874\",\n" +
            "      \"geo\": {\n" +
            "        \"lat\": \"-37.3159\",\n" +
            "        \"lng\": \"81.1496\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"phone\": \"1-770-736-8031 x56442\",\n" +
            "    \"website\": \"hildegard.org\",\n" +
            "    \"company\": {\n" +
            "      \"name\": \"Romaguera-Crona\",\n" +
            "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
            "      \"bs\": \"harness real-time e-markets\"\n" +
            "    }\n" +
            "  }";




    private int id;
    private String name;
    private String username;
    private String email;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private String lat;
    private String lng;
    private String phone;
    private String website;
    private String compName;
    private String catchPhrase;
    private String bs;

    @SuppressWarnings("unchecked")
    @JsonProperty("address")
    private void unpackAddress(Map<String,Object> address){
        this.street =(String)address.get("street");
        this.suite =(String)address.get("suite");
        this.city =(String)address.get("city");
        this.zipcode =(String)address.get("zipcode");
        Map<String,String> geo = (Map<String,String>)address.get("geo");
        this.lat =geo.get("lat");
        this.lng =geo.get("lng");}
    @SuppressWarnings("unchecked")
    @JsonProperty("company")
    private void unpackCompany(Map<String,Object> company){
        this.compName = (String)company.get("name");
        this.catchPhrase = (String)company.get("catchPhrase");
        this.bs = (String)company.get("bs");}
    @Test
    public void whenUsingAnnotations_thenOk() throws IOException {
        User user = new ObjectMapper()
                .readerFor(User.class)
                .readValue(json);
        assertEquals(user.getName(), "Leanne Graham");
        assertEquals(user.getCity(), "Gwenborough");
        assertEquals(user.getLat(), "-37.3159");
    }


}
