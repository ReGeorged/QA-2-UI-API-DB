package helpers;

import constants.EndPoints;
import constants.PortalRestHashMaps;

public class PortalHelper {

    public static String getCookie(){
        String token = RestHelper.postWithHeadersAndParams(PortalRestHashMaps.authForToken(), PortalRestHashMaps.setParamVariant(), EndPoints.getToken, 200).asString();
        return token;
    }
}
