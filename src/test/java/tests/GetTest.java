package tests;

import com.google.common.collect.Ordering;
import constants.Endpoints;
import helpers.JsonMockApiHelper;
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
        List<Posts> responseList = JsonMockApiHelper.getAllPostsList();
        ArrayList<Integer> actualList = new ArrayList<>();

        for (int i = 0; i < responseList.size(); i++) {
            actualList.add(responseList.get(i).getId());
        }
        Assert.assertTrue(Ordering.natural().isOrdered(actualList), "id ordering is not ascending");
    }


    @Test
    public void step2() {
        Posts actualPost = GetHelper.getPostById("99", 200);
        Posts expectedPost = new Posts();
        expectedPost.setTitle(FileUtils.returnFromJson("expectedTitle", FileUtils.testDataJsonPath));
        expectedPost.setBody(FileUtils.returnFromJson("expectedBody", FileUtils.testDataJsonPath));
        expectedPost.setUserId(10);
        expectedPost.setId(99);

        Assert.assertEquals(actualPost, expectedPost, "responses dont match");
    }

    @Test

    public void step3() {
        String errorPostResponse = GetHelper.getPostById("150", 404).getBody();
        Assert.assertNull(errorPostResponse, "body is not empty");
    }


    @Test

    public void newStep5() {
        User actualResponse = JsonMockApiHelper.getAllUsersList().get(4);
        User expectedResponse = ComplexPojoHelper.jsonPojoHelper(FileUtils.readFileAsString("src/main/resources/expectedResult.json"), User.class);

        Assert.assertEquals(actualResponse, expectedResponse, "actual and expected responses dont match");
    }

    @Test
    public void step6() {
        User newCallGet = GetHelper.getUserById("5", 200);
        User oldCallGet = GetHelper.pojoCallGetAsList(Endpoints.USERS, 200, User.class).get(4);

        Assert.assertEquals(newCallGet, oldCallGet, "responses dont match");
    }
}
