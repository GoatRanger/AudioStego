package dji.common.util;

import java.util.HashMap;

public class ConvertKeyUtil {
    public static HashMap<String, String> flightControllerConvertMap = new HashMap<String, String>() {
        {
            put("DistanceLimitation", "g_config.flying_limit.max_radius_0");
            put("HeightLimitation", "g_config.flying_limit.max_height_0");
        }
    };
    public static HashMap<String, String> gimbalConvertMap = new HashMap();
}
