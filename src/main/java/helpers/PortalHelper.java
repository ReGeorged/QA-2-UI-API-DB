package helpers;

import constants.EndPoints;
import constants.PortalHashMaps;
import pojo.TestPojo;

import java.util.List;
public class PortalHelper {

    public static String getCookie(){
        String token = RestHelper.postWithHeadersAndParams(PortalHashMaps.auth(), PortalHashMaps.setParamVariant(), EndPoints.getToken, 200).asString();
        return token;
    }




    public static List<TestPojo> getTestPojoList(){
        List<TestPojo> responseList = RestHelper.pojoCallPostAsList(EndPoints.getJsonResponse,200,TestPojo.class);
        return responseList;
    }
}
