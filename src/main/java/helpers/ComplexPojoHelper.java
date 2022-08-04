package helpers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.User;
import pojo.Posts;

public class ComplexPojoHelper {

    public static User UsersHelper(String whatToRead) {
        try {
            User user = new ObjectMapper()
                    .readerFor(User.class)
                    .readValue(whatToRead);
            return user;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static Posts PostsHelper(String whatToRead) {
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

