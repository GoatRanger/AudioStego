package com.flurry.sdk;

import com.alipay.sdk.h.a;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class gi {
    private static final String a = gi.class.getSimpleName();

    public Map<String, List<String>> a(String str) {
        in.a(3, a, "Parsing referrer map");
        if (str == null) {
            return Collections.emptyMap();
        }
        Map<String, List<String>> hashMap = new HashMap();
        String[] split = str.split(a.b);
        int length = split.length;
        for (String str2 : split) {
            String str22;
            String[] split2 = str22.split("=");
            if (split2.length != 2) {
                in.a(5, a, "Invalid referrer Element: " + str22 + " in referrer tag " + str);
            } else {
                str22 = URLDecoder.decode(split2[0]);
                String decode = URLDecoder.decode(split2[1]);
                if (hashMap.get(str22) == null) {
                    hashMap.put(str22, new ArrayList());
                }
                ((List) hashMap.get(str22)).add(decode);
            }
        }
        for (Entry entry : hashMap.entrySet()) {
            in.a(3, a, "entry: " + ((String) entry.getKey()) + "=" + entry.getValue());
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (hashMap.get("utm_source") == null) {
            stringBuilder.append("Campaign Source is missing.\n");
        }
        if (hashMap.get("utm_medium") == null) {
            stringBuilder.append("Campaign Medium is missing.\n");
        }
        if (hashMap.get("utm_campaign") == null) {
            stringBuilder.append("Campaign Name is missing.\n");
        }
        if (stringBuilder.length() > 0) {
            in.a(5, a, "Detected missing referrer keys : " + stringBuilder.toString());
        }
        return hashMap;
    }
}
