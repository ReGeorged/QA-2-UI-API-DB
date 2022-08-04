package tests;

import helpers.PostsHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Posts;
import utils.FileUtils;
import utils.StringUtils;

public class PostTest extends BaseTest {

    @Test
    public void createUserAndCheckIT() {
        Posts newPost = new Posts();
        newPost.setUserId(StringUtils.stringToInt(FileUtils.returnFromJson("postUSERID")));
        newPost.setId(StringUtils.stringToInt(FileUtils.returnFromJson("postID")));
        newPost.setTitle(FileUtils.returnFromJson("postTITLE"));
        newPost.setBody(FileUtils.returnFromJson("postBODY"));
        Response response = PostsHelper.createPost(newPost);

        int id = response.jsonPath().getInt("id");
        Assert.assertNotNull(id,"id is null");

        int userId = response.jsonPath().getInt("userId");
        Assert.assertEquals(userId, StringUtils.stringToInt(FileUtils.returnFromJson("postUSERID")),"UserId -s dont match");

        String title = response.jsonPath().getString("title");
        Assert.assertEquals(title, FileUtils.returnFromJson("postTITLE"),"titles dont match");

        String body = response.jsonPath().getString("body");
        Assert.assertEquals(body, FileUtils.returnFromJson("postBODY"),"body -s dont match");
    }

}
