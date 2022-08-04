package helpers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import complexpojo.User;
import pojo.Posts;

public class ComplexPojoHelper {


//    public static Users deserializeToComplexPojo(String responseJsonAsString) {
//        ObjectMapper mapper = new ObjectMapper();
//        //TODO tried to work with jsonpath() still to no avail
////        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//
//        try {
//            Users users = mapper.readValue(responseJsonAsString, Users.class);
//            Address address = users.getAddress();
//            Geo geo = address.getGeo();
//            Company company = users.getCompany();
//            return users;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static User UsersHelper(String whatToRead){
        try {
            User user = new ObjectMapper()
                    .readerFor(User.class)
                    .readValue(whatToRead);
            return user;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static Posts PostsHelper(String whatToRead){
        try {
            Posts posts = new ObjectMapper()
                    .readerFor(Posts.class)
                    .readValue(whatToRead);
            return posts;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

