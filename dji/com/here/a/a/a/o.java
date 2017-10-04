package com.here.a.a.a;

import com.here.a.a.a.a.m;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class o extends i {
    private m b;
    private Integer c;
    private Integer d;
    private Boolean e;

    public o(String str, String str2, String str3, m mVar) {
        super(str, str2, str3);
        if (mVar == null) {
            throw new NullPointerException("GeoPoint location can't be null.");
        }
        this.b = mVar;
    }

    protected Map<String, String> b() {
        Map hashMap = new HashMap(super.b());
        hashMap.put("x", String.valueOf(this.b.b));
        hashMap.put("y", String.valueOf(this.b.a));
        if (this.c != null) {
            hashMap.put("radius", String.valueOf(this.c));
        }
        if (this.d != null) {
            hashMap.put("max", String.valueOf(this.d));
        }
        if (this.e != null) {
            hashMap.put("details", this.e.booleanValue() ? "1" : "0");
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public o a(Integer num) {
        if (num == null || num.intValue() >= 0) {
            this.c = num;
            return this;
        }
        throw new IllegalArgumentException("Radius can't be below zero.");
    }

    public o b(Integer num) {
        if (num == null || num.intValue() >= 1) {
            this.d = num;
            return this;
        }
        throw new IllegalArgumentException("Max results number must be greater than 0.");
    }

    public o a(Boolean bool) {
        this.e = bool;
        return this;
    }

    public Integer n() {
        return this.c;
    }

    public Integer o() {
        return this.d;
    }

    public Boolean p() {
        return this.e;
    }
}
