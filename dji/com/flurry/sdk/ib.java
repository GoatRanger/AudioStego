package com.flurry.sdk;

import java.util.HashMap;
import java.util.Map;

public class ib {
    private static ib a;
    private static final String b = ib.class.getSimpleName();
    private static final HashMap<String, Map<String, String>> c = new HashMap();

    public static synchronized ib a() {
        ib ibVar;
        synchronized (ib.class) {
            if (a == null) {
                a = new ib();
            }
            ibVar = a;
        }
        return ibVar;
    }

    public static synchronized void b() {
        synchronized (ib.class) {
            a = null;
        }
    }

    public synchronized void a(String str, String str2, Map<String, String> map) {
        if (map == null) {
            map = new HashMap();
        }
        if (map.size() >= 10) {
            in.e(b, "MaxOriginParams exceeded: " + map.size());
        } else {
            map.put("flurryOriginVersion", str2);
            synchronized (c) {
                if (c.size() < 10 || c.containsKey(str)) {
                    c.put(str, map);
                } else {
                    in.e(b, "MaxOrigins exceeded: " + c.size());
                }
            }
        }
    }

    public synchronized HashMap<String, Map<String, String>> c() {
        HashMap<String, Map<String, String>> hashMap;
        synchronized (c) {
            hashMap = new HashMap(c);
        }
        return hashMap;
    }
}
