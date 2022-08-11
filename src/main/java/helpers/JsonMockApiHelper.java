package helpers;

import constants.Endpoints;
import pojo.Posts;
import pojo.User;

import java.util.List;

public class JsonMockApiHelper {


    public static List<Posts> getAllPostsList() {
        List<Posts> responseList = GetHelper.pojoCallGetAsList(Endpoints.POSTS, 200, Posts.class);
        return responseList;
    }

    public static List<User> getAllUsersList() {
        List<User> response = GetHelper.pojoCallGetAsList(Endpoints.USERS, 200, User.class);
        return response;
    }
}
