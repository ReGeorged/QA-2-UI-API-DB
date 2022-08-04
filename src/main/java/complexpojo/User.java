package complexpojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

@Data
public class User {
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
    @JsonProperty("$")
    public void unpackUser(Map<String, Object> user) {
        this.id = (int) user.get("id");
        this.name = (String) user.get("name");
        this.username = (String) user.get("username");
        this.email = (String) user.get("email");
    }


    @SuppressWarnings("unchecked")
    @JsonProperty("address")
    public void unpackAddress(Map<String, Object> address) {
        this.street = (String) address.get("street");
        this.suite = (String) address.get("suite");
        this.city = (String) address.get("city");
        this.zipcode = (String) address.get("zipcode");
        Map<String, String> geo = (Map<String, String>) address.get("geo");
        this.lat = geo.get("lat");
        this.lng = geo.get("lng");
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("company")
    public void unpackCompany(Map<String, Object> company) {
        this.compName = (String) company.get("name");
        this.catchPhrase = (String) company.get("catchPhrase");
        this.bs = (String) company.get("bs");
    }


}