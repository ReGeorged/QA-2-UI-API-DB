package tests;

import com.google.common.collect.Ordering;
import complexpojo.Users;
import helpers.ComplexPojoHelper;
import helpers.GetHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
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
        Response response = GetHelper.callGetOnSpecificPosts("99",200);

        Assert.assertEquals(ResponseUtils.fieldName(response,"userId"),"10","userId -s dont match");
        Assert.assertEquals(ResponseUtils.fieldName(response,"id"),"99","id -s not match");
        Assert.assertNotNull(ResponseUtils.fieldName(response,"title"),"title is null");
        Assert.assertNotNull(ResponseUtils.fieldName(response,"body"),"body is null");
    }
    @Test
    public void step3(){
        Assert.assertEquals(ResponseUtils.responseBodyToString(GetHelper.callGetOnSpecificUser("150",404)),"{}","body is not empty");
    }


    @Test
    public void step5()   {
        String users = GetHelper.callGetOnSpecificUser("5",200).asString();
        Users response =ComplexPojoHelper.deserializeToComplexPojo(users);

        Assert.assertEquals(response.getId(), StringUtils.stringToInt(ConfigManager.getFromConfig("expectedID")),"id -s dont match");
        Assert.assertEquals(response.getName(), ConfigManager.getFromConfig("expectedName"),"Name -s dont match");
        Assert.assertEquals(response.getUsername(),ConfigManager.getFromConfig("expectedUserName"),"Username -s dont match");
        Assert.assertEquals(response.getEmail(),ConfigManager.getFromConfig("expectedEmail"),"Email -s dont match");
        Assert.assertEquals(response.getAddress().getStreet(),ConfigManager.getFromConfig("expectedAddress"),"streets dont match");
        Assert.assertEquals(response.getAddress().getSuite(),ConfigManager.getFromConfig("expectedSuite"),"suites dont match");
        Assert.assertEquals(response.getAddress().getCity(),ConfigManager.getFromConfig("expectedCity"),"city -s dont match");
        Assert.assertEquals(response.getAddress().getZipcode(),ConfigManager.getFromConfig("expectedZipCode"),"zip codes dont match");
        Assert.assertEquals(response.getAddress().getGeo().getLat(),StringUtils.stringToDouble(ConfigManager.getFromConfig("expectedLat")),"Lat -s dont match");
        Assert.assertEquals(response.getAddress().getGeo().getLng(),StringUtils.stringToDouble(ConfigManager.getFromConfig("expectedLng")),"Lng -s dont match");
        Assert.assertEquals(response.getPhone(),ConfigManager.getFromConfig("expectedPhone"),"phone numbers dont match");
        Assert.assertEquals(response.getWebsite(),ConfigManager.getFromConfig("expectedWebsite"),"websites dont match");
        Assert.assertEquals(response.getCompany().getName(),ConfigManager.getFromConfig("expectedCompanyName"),"company names dont match");
        Assert.assertEquals(response.getCompany().getCatchPhrase(),ConfigManager.getFromConfig("expectedCatchPhrase"),"catch phrases dont match");
        Assert.assertEquals(response.getCompany().getBs(),ConfigManager.getFromConfig("expectedBs"),"BS -s dont match");
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
