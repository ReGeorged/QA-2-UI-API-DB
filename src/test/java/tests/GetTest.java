package tests;

import com.google.common.collect.Ordering;
import complexpojo.Users;
import helpers.ComplexPojoHelper;
import helpers.GetHelper;
import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseUtils;

import java.util.List;

public class GetTest {


    @Test
    public void step1(){
        GetHelper getHelper = new GetHelper();
        List<String> responseList = getHelper.callGetOnAllUsers(200).jsonPath().getList("id");
        Assert.assertTrue(Ordering.natural().isOrdered(responseList),"ordering is not ascending");

    }
    @Test
    public void step2() {
        GetHelper getHelper = new GetHelper();
        Response response = GetHelper.callGetOnSpecificPosts("99",200);

        Assert.assertEquals(ResponseUtils.extractFieldFromResponse(response,"userId"),"10","userId -s dont match");
        Assert.assertEquals(ResponseUtils.extractFieldFromResponse(response,"id"),"99","id -s not match");
        Assert.assertNotNull(ResponseUtils.extractFieldFromResponse(response,"title"),"title is null");
        Assert.assertNotNull(ResponseUtils.extractFieldFromResponse(response,"body"),"body is null");
    }
    @Test
    public void step3(){
        GetHelper getHelper = new GetHelper();
        Assert.assertEquals(ResponseUtils.responseBodyToString(getHelper.callGetOnSpecificUser("150",404)),"{}","body not empty");
    }


    @Test
    public void step5()   {
        ComplexPojoHelper complexPojoHelper = new ComplexPojoHelper();
        GetHelper getHelper = new GetHelper();

        String users = getHelper.callGetOnSpecificUser("5",200).asString();
        Users response =complexPojoHelper.deserializeToComplexPojo(users);

        Assert.assertEquals(response.getId(),5,"id -s dont match");
        Assert.assertEquals(response.getName(),"Chelsey Dietrich","Name -s dont match");
        Assert.assertEquals(response.getUsername(),"Kamren","Username -s dont match");
        Assert.assertEquals(response.getEmail(),"Lucio_Hettinger@annie.ca","Email -s dont match");
        Assert.assertEquals(response.getAddress().getStreet(),"Skiles Walks","streets dont match");
        Assert.assertEquals(response.getAddress().getSuite(),"Suite 351","suites dont match");
        Assert.assertEquals(response.getAddress().getCity(),"Roscoeview","citys dont match");
        Assert.assertEquals(response.getAddress().getZipcode(),"33263","zip codes dont match");
        Assert.assertEquals(response.getAddress().getGeo().getLat(),-31.8129,"Lat -s dont match");
        Assert.assertEquals(response.getAddress().getGeo().getLng(),62.5342,"Lng -s dont match");
        Assert.assertEquals(response.getPhone(),"(254)954-1289","phone numbers dont match");
        Assert.assertEquals(response.getWebsite(),"demarco.info","websites dont match");
        Assert.assertEquals(response.getCompany().getName(),"Keebler LLC","company names dont match");
        Assert.assertEquals(response.getCompany().getCatchPhrase(),"User-centric fault-tolerant solution","catch phrases dont match");
        Assert.assertEquals(response.getCompany().getBs(),"revolutionize end-to-end systems","BS -s dont match");
    }

    @Test
    public void step6()   {

        GetHelper getHelper = new GetHelper();
        String response1 = getHelper.callGetAllUsersAndExtractSpecific("4",200);
        String response2 = getHelper.callGetOnSpecificUser("5",200).asString();

        try {

            JSONAssert.assertEquals(response2,response1, JSONCompareMode.NON_EXTENSIBLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
