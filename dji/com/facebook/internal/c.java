package com.facebook.internal;

import android.content.Context;
import com.facebook.y;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private static final Map<a, String> a = new HashMap<a, String>() {
        {
            put(a.MOBILE_INSTALL_EVENT, "MOBILE_APP_INSTALL");
            put(a.CUSTOM_APP_EVENTS, "CUSTOM_APP_EVENTS");
        }
    };

    public enum a {
        MOBILE_INSTALL_EVENT,
        CUSTOM_APP_EVENTS
    }

    public static JSONObject a(a aVar, d dVar, String str, boolean z, Context context) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("event", a.get(aVar));
        ah.a(jSONObject, dVar, str, z);
        try {
            ah.a(jSONObject, context);
        } catch (Exception e) {
            x.a(y.APP_EVENTS, "AppEvents", "Fetching extended device info parameters failed: '%s'", e.toString());
        }
        jSONObject.put("application_package_name", context.getPackageName());
        return jSONObject;
    }
}
