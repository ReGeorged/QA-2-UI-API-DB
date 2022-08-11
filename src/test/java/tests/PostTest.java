package tests;

import helpers.PostsHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Posts;
import utils.FileUtils;

public class PostTest extends BaseTest {

    @Test
    public void createUserAndCheckIT() {
        Posts actualPost = new Posts();
        actualPost.setUserId(Integer.parseInt(FileUtils.returnFromJson("postUSERID", FileUtils.testDataJsonPath)));
        actualPost.setId(Integer.parseInt(FileUtils.returnFromJson("postID", FileUtils.testDataJsonPath)));
        actualPost.setTitle(FileUtils.returnFromJson("postTITLE", FileUtils.testDataJsonPath));
        actualPost.setBody(FileUtils.returnFromJson("postBODY", FileUtils.testDataJsonPath));
        Posts createdPosts = PostsHelper.createPost(actualPost, 201);

        Assert.assertEquals(actualPost, createdPosts, "sent data and received -- dont match");
    }
}
