package helpers;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import complexpojo.Address;
import complexpojo.Company;
import complexpojo.Geo;
import complexpojo.Users;

public class ComplexPojoHelper {
    public ComplexPojoHelper() {
    }


    public Users deserializeToComplexPojo(String responseJsonAsString) {
        ObjectMapper mapper = new ObjectMapper();
        //TODO tried to work with jsonpath() still to no avail
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        try {
            Users users = mapper.readValue(responseJsonAsString, Users.class);
            Address address = users.getAddress();
            Geo geo = address.getGeo();
            Company company = users.getCompany();
            return users;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

