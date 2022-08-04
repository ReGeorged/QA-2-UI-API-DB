package tests;

import com.google.common.collect.Ordering;
import pojo.User;
import helpers.ComplexPojoHelper;
import helpers.GetHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Posts;
import utils.*;

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
        Assert.assertEquals(GetHelper.callGetOnSpecificUser("150",404),"{}","body is not empty");
    }


    @Test
    public void newStep5()   {
         User actualResponse = ComplexPojoHelper.UsersHelper(GetHelper.callGetOnSpecificUser("5",200));
         User expectedResponse = ComplexPojoHelper.UsersHelper(FileUtils.readFileAsString("src/main/resources/expectedResult.json"));

         Assert.assertEquals(actualResponse,expectedResponse,"actual and expected responses dont match");
    }

    @Test
    public void step6(){
        String normalizedResponse1 = NormalizeJsonString.normalizedJsonString(GetHelper.getUserById("4",200));
        String normalizedResponse2 = NormalizeJsonString.normalizedJsonString(GetHelper.callGetOnSpecificUser("5",200));

        Assert.assertEquals(normalizedResponse1,normalizedResponse2);
    }
}
