package helpers;

import constants.PortalHashMaps;
import io.restassured.response.Response;

import java.util.List;

public class PojoHelper {

    public static <T> List<T> pojoCallPostAsList(String endpoint, int statusCode, Class<T> whatClass) {
        Response response = RestHelper.postAndAcceptJson(PortalHashMaps.auth(), PortalHashMaps.setProjectId(), endpoint, statusCode);
        List<T> returnedAnyList = response.jsonPath().getList(".", whatClass);
        return returnedAnyList;
    }
}
