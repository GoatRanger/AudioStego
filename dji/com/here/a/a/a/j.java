package com.here.a.a.a;

import com.here.a.a.a.a.m;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.usercenter.mode.n;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class j extends i {
    private final m b;
    private final m c;
    private Date d;
    private String e;
    private String f;
    private Boolean g;
    private Integer h;
    private Integer i;
    private Boolean j;
    private Boolean k;
    private Boolean l;
    private Boolean m;
    private Integer n;
    private Integer o;
    private Integer p;
    private Boolean q;
    private Boolean r;
    private Boolean s;

    public enum a {
        BOTH("BOTH"),
        BACKWARD("B"),
        FORWARD("F"),
        NONE("N");
        
        public final String e;

        private a(String str) {
            this.e = str;
        }

        public static a a(String str) {
            if (BACKWARD.e.equalsIgnoreCase(str)) {
                return BACKWARD;
            }
            if (FORWARD.e.equalsIgnoreCase(str)) {
                return FORWARD;
            }
            if (NONE.e.equalsIgnoreCase(str)) {
                return NONE;
            }
            return BOTH;
        }
    }

    public j(String str, String str2, String str3, m mVar, m mVar2) {
        super(str, str2, str3);
        if (mVar == null || mVar2 == null) {
            throw new NullPointerException("Start and Destination points can't be null.");
        }
        this.b = mVar;
        this.c = mVar2;
        this.d = new Date();
    }

    j(String str, String str2, String str3) {
        super(str, str2, str3);
        this.b = null;
        this.c = null;
    }

    protected String a() {
        return "metarouter/rest/routeservice/v2/route";
    }

    protected Map<String, String> b() {
        Map hashMap = new HashMap(super.b());
        hashMap.put("startX", String.valueOf(this.b.b));
        hashMap.put("startY", String.valueOf(this.b.a));
        hashMap.put("destX", String.valueOf(this.c.b));
        hashMap.put("destY", String.valueOf(this.c.a));
        hashMap.put(n.ax, s.a(this.d));
        if (this.e != null) {
            hashMap.put(MessageKey.MSG_ACCEPT_TIME_START, this.e);
        }
        if (this.f != null) {
            hashMap.put("dest", this.f);
        }
        if (this.g != null) {
            hashMap.put("arrival", this.g.booleanValue() ? "1" : "0");
        }
        if (this.h != null) {
            hashMap.put("forward", String.valueOf(this.h));
        }
        if (this.i != null) {
            hashMap.put("backward", String.valueOf(this.i));
        }
        if (j() != null) {
            hashMap.put("strict", j().booleanValue() ? "1" : "0");
        }
        if (this.j != null) {
            hashMap.put("graph", this.j.booleanValue() ? "1" : "0");
        }
        if (this.k != null) {
            hashMap.put("details", this.k.booleanValue() ? "1" : "0");
        }
        if (this.l != null) {
            hashMap.put("alerts", this.l.booleanValue() ? "1" : "0");
        }
        if (this.m != null) {
            hashMap.put("walk_ctx", this.m.booleanValue() ? "1" : "0");
        }
        if (!(this.o == null && this.n == null)) {
            String str = "walk";
            String str2 = "%d,%d";
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(this.o != null ? this.o.intValue() : 2000);
            objArr[1] = Integer.valueOf(this.n != null ? this.n.intValue() : 100);
            hashMap.put(str, String.format(str2, objArr));
        }
        if (this.p != null) {
            hashMap.put("changes", String.valueOf(this.p));
        }
        if (m() != null) {
            hashMap.put("prod", m());
        }
        if (this.q != null) {
            hashMap.put("um", this.q.booleanValue() ? "1" : "0");
        }
        if (this.r != null) {
            hashMap.put("tariff", this.r.booleanValue() ? "1" : "0");
        }
        if (this.s != null) {
            hashMap.put("maneuvers", this.s.booleanValue() ? "1" : "0");
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public j a(Date date) {
        if (date == null) {
            date = new Date();
        }
        this.d = date;
        return this;
    }

    public j a(Boolean bool) {
        this.g = bool;
        return this;
    }

    public j a(Integer num) {
        if (num == null || num.intValue() >= 0) {
            this.h = num;
            return this;
        }
        throw new IllegalArgumentException("Number of routes should be greater than zero.");
    }

    public j c(Boolean bool) {
        this.j = bool;
        return this;
    }

    public j b(Integer num) {
        if (num == null || num.intValue() >= 0) {
            this.p = num;
            return this;
        }
        throw new IllegalArgumentException("Maximum number of changes must be greater or equal zero.");
    }

    public j c(Integer num) {
        if (num == null || num.intValue() >= 0) {
            this.o = num;
            return this;
        }
        throw new IllegalArgumentException("Walking distance must be greater or equal zero.");
    }

    public j d(Integer num) {
        if (num == null || num.intValue() >= 0) {
            this.n = num;
            return this;
        }
        throw new IllegalArgumentException("Walking speed can't be less than zero.");
    }

    public j d(Boolean bool) {
        this.s = bool;
        return this;
    }

    public Integer c() {
        return this.h;
    }

    public Boolean n() {
        return this.j;
    }

    public Boolean o() {
        return this.k;
    }

    public Boolean p() {
        return this.l;
    }

    public Boolean q() {
        return this.m;
    }

    public Boolean r() {
        return this.s;
    }
}
