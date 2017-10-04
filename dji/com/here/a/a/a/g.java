package com.here.a.a.a;

import com.here.a.a.a.a.m;
import dji.pilot.usercenter.mode.n;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class g extends i {
    private m b;
    private Set<String> c;
    private Date d;
    private Integer e;
    private Integer f;
    private Integer g;
    private Boolean h;

    public g(String str, String str2, String str3, m mVar) {
        super(str, str2, str3);
        if (mVar == null) {
            throw new NullPointerException("Location can't be null.");
        }
        this.b = mVar;
        this.d = new Date();
    }

    protected String a() {
        return "metarouter/rest/boardservice/v1/multiboard";
    }

    protected Map<String, String> b() {
        Map hashMap = new HashMap(super.b());
        hashMap.put("startX", String.valueOf(this.b.b));
        hashMap.put("startY", String.valueOf(this.b.a));
        if (this.c != null && this.c.size() > 0) {
            hashMap.put("stopIds", s.a(this.c));
        }
        if (this.e != null) {
            hashMap.put("radius", String.valueOf(this.e));
        }
        hashMap.put(n.ax, s.a(this.d));
        if (j() != null) {
            hashMap.put("strict", j().booleanValue() ? "1" : "0");
        }
        if (m() != null) {
            hashMap.put("prod", m());
        }
        if (this.f != null) {
            hashMap.put("max", String.valueOf(this.f));
        }
        if (this.g != null) {
            hashMap.put("max_stn", String.valueOf(this.g));
        }
        if (this.h != null) {
            hashMap.put("rt", this.h.booleanValue() ? "1" : "0");
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public g a(Date date) {
        if (date == null) {
            date = new Date();
        }
        this.d = date;
        return this;
    }

    public g a(Integer num) {
        if (num == null || num.intValue() >= 1) {
            this.f = num;
            return this;
        }
        throw new IllegalArgumentException("Max departures should be greater that zero.");
    }

    public g b(Integer num) {
        if (num == null || num.intValue() >= 1) {
            this.g = num;
            return this;
        }
        throw new IllegalArgumentException("Max stations should be greater that zero.");
    }

    public g a(boolean z) {
        this.h = Boolean.valueOf(z);
        return this;
    }

    public g a(Set<String> set) {
        this.c = set;
        return this;
    }

    public g c(Integer num) {
        if (num == null || num.intValue() >= 0) {
            this.e = num;
            return this;
        }
        throw new IllegalArgumentException("Radius can't be below zero.");
    }

    public Integer c() {
        return this.f;
    }
}
