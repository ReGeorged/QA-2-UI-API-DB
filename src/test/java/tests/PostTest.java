package tests;

import helpers.PostsHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigManager;
import utils.StringUtils;

public class PostTest extends BaseTest {

    @Test
    public void createUserAndCheckIT() {
        PostsHelper postsHelper = new PostsHelper();
        Response response = postsHelper.createPost();

        int id = response.jsonPath().getInt("id");
        Assert.assertNotNull(id,"id is null");

        int userId = response.jsonPath().getInt("userId");
        Assert.assertEquals(userId, StringUtils.stringToInt(ConfigManager.getFromConfig("postUSERID")),"UserId -s dont match");

        String title = response.jsonPath().getString("title");
        Assert.assertEquals(title, ConfigManager.getFromConfig("postTITLE"),"titles dont match");

        String body = response.jsonPath().getString("body");
        Assert.assertEquals(body, ConfigManager.getFromConfig("postBODY"),"body -s dont match");
    }

}
