package com.here.a.a.a;

import com.here.a.a.a.a.m;
import dji.pilot.usercenter.mode.n;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class a extends i {
    private boolean b;
    private Boolean c;
    private Integer d;
    private Integer e;
    private m f;
    private Date g;
    private a h;
    private Integer i;

    public enum a {
        ALL("all"),
        NEW("new"),
        UPDATED("updated");
        
        public final String d;

        private a(String str) {
            this.d = str;
        }
    }

    public a(String str, String str2, String str3, boolean z) {
        super(str, str2, str3);
        this.b = z;
    }

    protected String a() {
        return "coverage/v1/city";
    }

    protected Map<String, String> b() {
        Map hashMap = new HashMap(super.b());
        hashMap.put("chinaconfig", this.b ? "1" : "0");
        if (this.c != null) {
            hashMap.put("details", this.c.booleanValue() ? "1" : "0");
        }
        if (this.d != null) {
            hashMap.put("nearby_max", String.valueOf(this.d));
        }
        if (this.e != null) {
            hashMap.put("radius", String.valueOf(this.e));
        }
        if (this.f != null) {
            hashMap.put("x", String.valueOf(this.f.b));
            hashMap.put("y", String.valueOf(this.f.a));
        }
        if (this.g != null) {
            hashMap.put(n.ax, s.a(this.g));
        }
        if (this.h != null) {
            hashMap.put("updatetype", this.h.d);
        }
        if (this.i != null) {
            hashMap.put("max", String.valueOf(this.i));
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public a a(Boolean bool) {
        this.c = bool;
        return this;
    }

    public a a(Integer num) {
        if (num == null || num.intValue() >= 1) {
            this.d = num;
            return this;
        }
        throw new IllegalArgumentException("Nearby max should be greater that zero.");
    }

    public a b(Integer num) {
        if (num == null || num.intValue() >= 0) {
            this.e = num;
            return this;
        }
        throw new IllegalArgumentException("Radius can't be below zero.");
    }

    public a a(m mVar) {
        this.f = mVar;
        return this;
    }

    public a a(Date date) {
        this.g = date;
        return this;
    }

    public a a(a aVar) {
        this.h = aVar;
        return this;
    }

    public a c(Integer num) {
        if (num == null || num.intValue() >= 1) {
            this.i = num;
            return this;
        }
        throw new IllegalArgumentException("Max results should be greater that zero.");
    }

    public Integer c() {
        return this.i;
    }
}
