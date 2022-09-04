package helpers;

import constants.PortalHashMaps;
import io.restassured.response.Response;

import java.util.List;

public class PojoHelpepr {

    public static <T> List<T> pojoCallPostAsList(String endpoint, int statusCode, Class<T> whatClass) {
        Response response = RestHelper.getAllJsonResponses(PortalHashMaps.auth(), PortalHashMaps.setProjectId(),endpoint, statusCode);
        List<T> returnedAnyList = response.jsonPath().getList(".", whatClass);
        return returnedAnyList;
    }
}
