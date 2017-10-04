package com.here.a.a.a;

import com.alipay.sdk.f.d;
import com.here.a.a.a.a.m;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class n extends o {
    private String b;
    private a c;

    public enum a {
        a("strict"),
        FUZZY("fuzzy");
        
        public final String c;

        private a(String str) {
            this.c = str;
        }
    }

    public n(String str, String str2, String str3, m mVar, String str4) {
        super(str, str2, str3, mVar);
        if (str4 == null || str4.isEmpty()) {
            throw new IllegalArgumentException("Station name should be specified.");
        }
        this.b = str4;
    }

    protected String a() {
        return "search/by_name";
    }

    protected Map<String, String> b() {
        Map hashMap = new HashMap(super.b());
        hashMap.put("name", this.b);
        if (this.c != null) {
            hashMap.put(d.q, this.c.c);
        }
        if (p() == null) {
            hashMap.put("details", "0");
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public n a(a aVar) {
        this.c = aVar;
        return this;
    }

    public String c() {
        return this.b;
    }
}
