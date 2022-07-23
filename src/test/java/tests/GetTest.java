package tests;

import com.google.common.collect.Ordering;
import helpers.GetHelper;
import helpers.PostsHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseUtils;

import java.util.List;

public class GetTest {

    @Test
    public void step1(){
        GetHelper getHelper = new GetHelper();
        List<String> responseList = getHelper.callGetOnAllUsers(200).jsonPath().getList("id");
        Assert.assertTrue(Ordering.natural().isOrdered(responseList));

    }
    @Test
    public void step2() {
        PostsHelper postsHelper = new PostsHelper();
        Response response = postsHelper.callGetOnSpecificPosts("99",200);

        Assert.assertEquals(ResponseUtils.extractFieldFromResponse(response,"userId"),"10");
        Assert.assertEquals(ResponseUtils.extractFieldFromResponse(response,"id"),"99");
        Assert.assertNotNull(ResponseUtils.extractFieldFromResponse(response,"title"));
        Assert.assertNotNull(ResponseUtils.extractFieldFromResponse(response,"body"));
    }
    @Test
    public void step3(){
        GetHelper getHelper = new GetHelper();
        Assert.assertEquals(ResponseUtils.responseBodyToString(getHelper.callGetOnSpecificUser("150",404)),"{}");
    }


    @Test
    public void step5() {
        GetHelper getHelper = new GetHelper();
        System.out.println(getHelper.callGetAllUsersAndExtractSpecific(200,"4"));
    }

}
