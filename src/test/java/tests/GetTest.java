package tests;

import helpers.GetHelper;
import helpers.PostsHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseUtils;

public class GetTest {
    @Test
    public void step3(){
        GetHelper getHelper = new GetHelper();
        Assert.assertEquals(ResponseUtils.responseBodyToString(getHelper.callGetOnSpecificUser("150",404)),"{}");
    }

}
