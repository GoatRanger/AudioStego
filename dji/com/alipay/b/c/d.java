package com.alipay.b.c;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public final class d {
    public static synchronized Map<String, String> a(Context context) {
        Map<String, String> hashMap;
        synchronized (d.class) {
            com.alipay.e.a.a.c.d instance = com.alipay.e.a.a.c.d.getInstance();
            hashMap = new HashMap();
            hashMap.put("AE1", instance.a());
            hashMap.put("AE2", (instance.b() ? "1" : "0"));
            hashMap.put("AE3", (instance.a(context) ? "1" : "0"));
            hashMap.put("AE4", instance.c());
            hashMap.put("AE5", instance.d());
            hashMap.put("AE6", instance.e());
            hashMap.put("AE7", instance.f());
            hashMap.put("AE8", instance.g());
            hashMap.put("AE9", instance.h());
            hashMap.put("AE10", instance.i());
            hashMap.put("AE11", instance.j());
            hashMap.put("AE12", instance.k());
            hashMap.put("AE13", instance.l());
            hashMap.put("AE14", instance.m());
            hashMap.put("AE15", instance.n());
        }
        return hashMap;
    }
}
