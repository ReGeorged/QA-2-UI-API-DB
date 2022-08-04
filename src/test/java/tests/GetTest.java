package tests;

import com.google.common.collect.Ordering;
import complexpojo.User;
import helpers.ComplexPojoHelper;
import helpers.GetHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Posts;
import utils.ConfigManager;
import utils.NormalizeJsonString;
import utils.ResponseUtils;
import utils.StringUtils;

import java.util.List;

public class GetTest extends BaseTest {



    @Test
    public void step1(){
        List<String> responseList = GetHelper.callGetOnAllUsers(200).jsonPath().getList("id");
        Assert.assertTrue(Ordering.natural().isOrdered(responseList),"id ordering is not ascending");

    }
    @Test
    public void step2() {


        Posts actualPost = ComplexPojoHelper.PostsHelper(GetHelper.callGetOnSpecificPosts("99",200));
        Posts expectedPost = new Posts();

        expectedPost.setTitle("temporibus sit alias delectus eligendi possimus magni");
        expectedPost.setBody("quo deleniti praesentium dicta non quod\n" +
                "aut est molestias\n" +
                "molestias et officia quis nihil\n" +
                "itaque dolorem quia");
        expectedPost.setUserId(10);
        expectedPost.setId(99);

        Assert.assertTrue(actualPost.equals(expectedPost));
    }
    @Test
    public void step3(){
        Assert.assertEquals(ResponseUtils.responseBodyToString(GetHelper.callGetOnSpecificUser("150",404)),"{}","body is not empty");
    }


    @Test
    public void newStep5()   {
        String users = GetHelper.callGetOnSpecificUser("5",200).asString();
         User response = ComplexPojoHelper.UsersHelper(users);
        Assert.assertEquals(response.getId(), StringUtils.stringToInt(ConfigManager.getFromConfig("expectedID")),"id -s dont match");
        Assert.assertEquals(response.getName(), ConfigManager.getFromConfig("expectedName"),"Name -s dont match");
        Assert.assertEquals(response.getUsername(),ConfigManager.getFromConfig("expectedUserName"),"Username -s dont match");
        Assert.assertEquals(response.getEmail(),ConfigManager.getFromConfig("expectedEmail"),"Email -s dont match");
        Assert.assertEquals(response.getStreet(),ConfigManager.getFromConfig("expectedAddress"),"streets dont match");
        Assert.assertEquals(response.getSuite(),ConfigManager.getFromConfig("expectedSuite"),"suites dont match");
        Assert.assertEquals(response.getCity(),ConfigManager.getFromConfig("expectedCity"),"city -s dont match");
        Assert.assertEquals(response.getZipcode(),ConfigManager.getFromConfig("expectedZipCode"),"zip codes dont match");
        Assert.assertEquals(response.getLat(),ConfigManager.getFromConfig("expectedLat"),"Lat -s dont match");
        Assert.assertEquals(response.getLng(),ConfigManager.getFromConfig("expectedLng"),"Lng -s dont match");
        Assert.assertEquals(response.getPhone(),ConfigManager.getFromConfig("expectedPhone"),"phone numbers dont match");
        Assert.assertEquals(response.getWebsite(),ConfigManager.getFromConfig("expectedWebsite"),"websites dont match");
        Assert.assertEquals(response.getCompName(),ConfigManager.getFromConfig("expectedCompanyName"),"company names dont match");
        Assert.assertEquals(response.getCatchPhrase(),ConfigManager.getFromConfig("expectedCatchPhrase"),"catch phrases dont match");
        Assert.assertEquals(response.getBs(),ConfigManager.getFromConfig("expectedBs"),"BS -s dont match");


    }

    @Test
    public void step6(){
        String normalizedResponse1 = NormalizeJsonString.normalizedJsonString(GetHelper.getUserById("4",200));
        String normalizedResponse2 = NormalizeJsonString.normalizedJsonString(GetHelper.callGetOnSpecificUser("5",200).asString());

        Assert.assertEquals(normalizedResponse1,normalizedResponse2);
    }

    @Test
    public void b(){
        System.out.println(GetHelper.a(200));
    }
}
