package com.here.a.a.a;

import com.here.a.a.a.a.m;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class h extends i {
    private m b;
    private boolean c;
    private Boolean d;

    public h(String str, String str2, String str3, m mVar, boolean z) {
        super(str, str2, str3);
        if (mVar == null) {
            throw new IllegalArgumentException("Location can't be null.");
        }
        this.b = mVar;
        this.c = z;
    }

    protected String a() {
        return "coverage/v1/nearby";
    }

    protected Map<String, String> b() {
        Map hashMap = new HashMap(super.b());
        hashMap.put("chinaconfig", this.c ? "1" : "0");
        hashMap.put("x", String.valueOf(this.b.b));
        hashMap.put("y", String.valueOf(this.b.a));
        if (this.d != null) {
            hashMap.put("details", this.d.booleanValue() ? "1" : "0");
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public h a(Boolean bool) {
        this.d = bool;
        return this;
    }
}
