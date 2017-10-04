package com.here.b.c;

import dji.pilot.dji_groundstation.controller.e;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class a {
    public static final int a = ((int) TimeUnit.SECONDS.toMillis(10));
    public static final Map<String, String> b = Collections.unmodifiableMap(c);
    private static final Map<String, String> c = new HashMap<String, String>() {
        {
            put("identify", "/v1/identify");
            put("alias", "/v1/alias");
            put(e.d, "/v1/track");
            put("import", "/v1/import");
        }
    };

    public static String a(String str) {
        return "/projects/" + str + "/settings";
    }
}
