package tests;

import com.google.common.collect.Ordering;
import constants.Endpoints;
import pojo.User;
import helpers.ComplexPojoHelper;
import helpers.GetHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Posts;
import utils.*;

import java.util.ArrayList;
import java.util.List;


public class GetTest extends BaseTest {
    @Test
    public void step1() {
        List<Posts> responseList = GetHelper.pojoCallGetAsList(Endpoints.POSTS, 200, Posts.class);
        ArrayList<Integer> actualList = new ArrayList<>();

        for (int i = 0; i < responseList.size(); i++) {
            actualList.add(responseList.get(i).getId());
        }
        Assert.assertTrue(Ordering.natural().isOrdered(actualList), "id ordering is not ascending");
    }


    @Test
    public void step2() {
        Posts actualPost = GetHelper.pojoGetResponse(Endpoints.POSTS + "/99", 200, Posts.class);
        Posts expectedPost = new Posts();
        expectedPost.setTitle(FileUtils.returnFromJson("expectedTitle", FileUtils.testDataJsonPath));
        expectedPost.setBody(FileUtils.returnFromJson("expectedBody", FileUtils.testDataJsonPath));
        expectedPost.setUserId(10);
        expectedPost.setId(99);
        System.out.println(actualPost);

        Assert.assertEquals(actualPost, expectedPost, "responses dont match");
    }

    @Test

    public void step3() {
        String errorPostResponse = GetHelper.pojoGetResponse(Endpoints.POSTS + "/150", 404, Posts.class).getBody();
        Assert.assertNull(errorPostResponse, "body is not empty");
    }


    @Test

    public void newStep5() {
        User actualResponse = GetHelper.pojoCallGetAsList(Endpoints.USERS, 200, User.class).get(4);
        User expectedResponse = ComplexPojoHelper.pojoHelper(FileUtils.readFileAsString("src/main/resources/expectedResult.json"), User.class);

        Assert.assertEquals(actualResponse, expectedResponse, "actual and expected responses dont match");
    }

    @Test
    public void step6() {
        User newCallGet = GetHelper.pojoGetResponse(Endpoints.USERS + "/5", 200, User.class);
        User oldCallGet = GetHelper.pojoCallGetAsList(Endpoints.USERS, 200, User.class).get(4);

        Assert.assertEquals(newCallGet, oldCallGet);
    }
}
