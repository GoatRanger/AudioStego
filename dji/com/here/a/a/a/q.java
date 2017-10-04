package com.here.a.a.a;

import com.here.a.a.a.a.m;
import dji.pilot.usercenter.mode.n;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class q extends i {
    private m b;
    private String c;
    private Date d;
    private Integer e;
    private Boolean f;

    public q(String str, String str2, String str3, m mVar, String str4) {
        super(str, str2, str3);
        if (mVar == null) {
            throw new NullPointerException("Location can't be null.");
        } else if (str4 == null || str4.isEmpty()) {
            throw new IllegalArgumentException("Station ID should be non-null and non-empty.");
        } else {
            this.b = mVar;
            this.c = str4;
            this.d = new Date();
        }
    }

    protected String a() {
        return "metarouter/rest/boardservice/v2/stationboard";
    }

    protected Map<String, String> b() {
        Map hashMap = new HashMap(super.b());
        hashMap.put("startX", String.valueOf(this.b.b));
        hashMap.put("startY", String.valueOf(this.b.a));
        hashMap.put("stnId", this.c);
        hashMap.put(n.ax, s.a(this.d));
        if (j() != null) {
            hashMap.put("strict", j().booleanValue() ? "1" : "0");
        }
        if (m() != null) {
            hashMap.put("prod", m());
        }
        if (this.e != null) {
            hashMap.put("max", String.valueOf(this.e));
        }
        if (this.f != null) {
            hashMap.put("rt", this.f.booleanValue() ? "1" : "0");
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public q a(Date date) {
        if (date == null) {
            date = new Date();
        }
        this.d = date;
        return this;
    }

    public q a(Integer num) {
        if (num == null || num.intValue() >= 1) {
            this.e = num;
            return this;
        }
        throw new IllegalArgumentException("Max departures should be greater that zero.");
    }

    public q a(Boolean bool) {
        this.f = bool;
        return this;
    }

    public String c() {
        return this.c;
    }

    public Date n() {
        return this.d;
    }

    public Integer o() {
        return this.e;
    }

    public Boolean p() {
        return this.f;
    }
}
