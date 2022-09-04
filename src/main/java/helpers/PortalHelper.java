package helpers;

import constants.EndPoints;
import constants.PortalHashMaps;
import forms.AddTestForm;
import pojo.TestPojo;
import utils.FileUtils;

import java.util.List;

public class PortalHelper {

    public static String getCookie() {
        String token = RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(), PortalHashMaps.setParamVariant(), EndPoints.getToken, 200).asString();
        return token;
    }


    public static List<TestPojo> getTestPojoList() {
        List<TestPojo> responseList = RestHelper.pojoCallPostAsList(EndPoints.getJsonResponse, 200, TestPojo.class);
        return responseList;
    }

    public static void fillWebFormFromApiViaIndex(int index) {
        TestPojo tstPojo = getTestPojoList().get(index);
        AddTestForm addTestForm = new AddTestForm();
        addTestForm.fillForm(tstPojo.getName(), tstPojo.getMethod(), tstPojo.getStartTime(), tstPojo.getEndTime(), "randomEnv", "randBrowser", FileUtils.logToString(), tstPojo.getStatus());


    }


}
