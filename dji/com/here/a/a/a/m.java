package com.here.a.a.a;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class m extends o {
    public m(String str, String str2, String str3, com.here.a.a.a.a.m mVar) {
        super(str, str2, str3, mVar);
    }

    protected String a() {
        return "search/by_geocoord";
    }

    protected Map<String, String> b() {
        Map hashMap = new HashMap(super.b());
        if (p() == null) {
            hashMap.put("details", "1");
        }
        return Collections.unmodifiableMap(hashMap);
    }
}
