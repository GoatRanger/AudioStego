package com.here.a.a.a;

import com.here.a.a.a.j.a;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class r extends j {
    private String b;
    private String c;
    private a d;
    private Integer e;

    public r(String str, String str2, String str3, String str4, String str5) {
        super(str, str2, str3);
        if (str4 == null || str4.isEmpty() || str5 == null || str5.isEmpty()) {
            throw new NullPointerException("Both service URL and session context should be specified.");
        }
        this.b = str4;
        this.c = str5;
    }

    protected String a() {
        return "metarouter/rest/routeservice/v2/mroute";
    }

    public Map<String, String> b() {
        Map<String, String> hashMap = new HashMap();
        if (h() != null) {
            hashMap.put("client", h());
        }
        if (i() != null) {
            hashMap.put("lang", i().toLowerCase());
        }
        if (l() != null) {
            hashMap.put("routing", l().e);
        }
        if (k() != null) {
            hashMap.put("dbg", k().booleanValue() ? "2" : "0");
        }
        if (n() != null) {
            hashMap.put("graph", n().booleanValue() ? "1" : "0");
        }
        if (o() != null) {
            hashMap.put("details", o().booleanValue() ? "1" : "0");
        }
        if (p() != null) {
            hashMap.put("alerts", p().booleanValue() ? "1" : "0");
        }
        if (q() != null) {
            hashMap.put("walk_ctx", q().booleanValue() ? "1" : "0");
        }
        if (r() != null) {
            hashMap.put("maneuvers", r().booleanValue() ? "1" : "0");
        }
        hashMap.put("serviceUrl", this.b);
        hashMap.put("ctx", this.c);
        if (this.d != null) {
            hashMap.put("direction", this.d.e);
        }
        if (this.e != null) {
            hashMap.put("max", String.valueOf(this.e));
        }
        return hashMap;
    }

    public r a(a aVar) {
        if (aVar == null || a.BACKWARD == aVar || a.FORWARD == aVar) {
            this.d = aVar;
            return this;
        }
        throw new IllegalArgumentException("AllowedDirection could be either forward or backward.");
    }

    public r e(Integer num) {
        if (num == null || num.intValue() >= 0) {
            this.e = num;
            return this;
        }
        throw new IllegalArgumentException("Number of maximum routes should be greater than zero.");
    }

    public Integer s() {
        return this.e;
    }

    public j a(Boolean bool) {
        throw new UnsupportedOperationException("Not supported by SubsequentRouteRequest.");
    }

    public j b(Integer num) {
        throw new UnsupportedOperationException("Not supported by SubsequentRouteRequest.");
    }

    public j a(Integer num) {
        throw new UnsupportedOperationException("Not supported by SubsequentRouteRequest.");
    }

    public j a(Date date) {
        throw new UnsupportedOperationException("Not supported by SubsequentRouteRequest.");
    }

    public j c(Integer num) {
        throw new UnsupportedOperationException("Not supported by SubsequentRouteRequest.");
    }

    public j d(Integer num) {
        throw new UnsupportedOperationException("Not supported by SubsequentRouteRequest.");
    }
}
