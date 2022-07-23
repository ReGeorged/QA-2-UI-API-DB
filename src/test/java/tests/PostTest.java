package tests;

import helpers.PostsHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ConfigManager;
import utils.ResponseUtils;
import utils.StringUtils;

public class PostTest {

    @Test
    public  void testStep4(){
        PostsHelper postsHelper = new PostsHelper();
        Response response= postsHelper.createPost();

        int id = response.jsonPath().getInt("id");
        Assert.assertNotNull(id);

        int userId = response.jsonPath().getInt("userId");
        Assert.assertEquals(userId, StringUtils.stringToInt(ConfigManager.getFromConfig("postUSERID")));

        String title = response.jsonPath().getString("title");
        Assert.assertEquals(title,ConfigManager.getFromConfig("postTITLE"));

        String body = response.jsonPath().getString("body");
        Assert.assertEquals(body,ConfigManager.getFromConfig("postBODY"));
    }

}
