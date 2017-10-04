package com.here.a.a.a;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class i {
    static final /* synthetic */ boolean a = (!i.class.desiredAssertionStatus());
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private Boolean k;
    private Boolean l;
    private String m;
    private String n;

    public enum a {
        LOCALHOST("http://localhost:5552/"),
        DEV("http://devel.rnd.transit.api.here.com/"),
        DATA_INTEGRATION("http://integration.rnd.transit.api.here.com/"),
        DATA_TESTING("http://qa.rnd.transit.api.here.com/"),
        FUNCTIONAL_TESTING("http://ftest.rnd.transit.api.here.com/"),
        LOAD_TESTING("http://loadtest.rnd.transit.api.here.com/"),
        DEMO("http://demo.rnd.transit.api.here.com/"),
        STAGING("http://staging.transit.api.here.com/"),
        CUSTOMER_INTEGRATION("https://cit.transit.api.here.com/"),
        PROD("https://transit.api.here.com/");
        
        public final String k;

        private a(String str) {
            this.k = str;
        }
    }

    public enum b {
        a("json"),
        THRIFT("thrift");
        
        public final String c;

        private b(String str) {
            this.c = str;
        }
    }

    public enum c {
        REAL_TIME("rt"),
        TIME_TABLE("tt"),
        SIMPLE("sr"),
        ALL("all");
        
        public final String e;

        private c(String str) {
            this.e = str;
        }

        static c a(String str) {
            if (REAL_TIME.e.equalsIgnoreCase(str)) {
                return REAL_TIME;
            }
            if (TIME_TABLE.e.equalsIgnoreCase(str)) {
                return TIME_TABLE;
            }
            if (SIMPLE.e.equalsIgnoreCase(str)) {
                return SIMPLE;
            }
            if (ALL.e.equalsIgnoreCase(str)) {
                return ALL;
            }
            if (str == null) {
                return null;
            }
            throw new IllegalArgumentException("Unknown routing type value: " + str);
        }
    }

    public enum d {
        TRAIN_HIGH_SPEED,
        TRAIN_INTERCITY,
        TRAIN_INTERREGIONAL_AND_FAST,
        TRAIN_REGIONAL_AND_OTHER,
        TRAIN_CITY,
        BUS,
        WATER_BOAT_OR_FERRYS,
        RAIL_UNDEGROUND_OR_SUBWAY,
        RAIL_TRAM,
        ORDERED_SERVICES_OR_TAXI,
        RAIL_INCLINED,
        AERIAL,
        BUS_RAPID,
        RAIL_MONORAIL,
        FLIGHT,
        UNKNOWN;

        public static d[] a() {
            return (d[]) q.clone();
        }
    }

    protected abstract String a();

    public i(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null || str.isEmpty() || str2.isEmpty() || str3.isEmpty()) {
            throw new IllegalArgumentException("All constructor parameters must be non-null and non-empty.");
        }
        this.b = c(str);
        this.c = a();
        this.e = str2;
        this.f = str3;
        this.d = null;
    }

    public final String d() {
        if (a || Charset.isSupported("UTF-8")) {
            StringBuilder stringBuilder = new StringBuilder(this.b);
            stringBuilder.append(this.c).append(".").append(this.g != null ? this.g : b.a.c).append("?");
            if (this.d != null) {
                stringBuilder.append("accessId=").append(this.d);
            } else if (this.e == null || this.f == null) {
                throw new IllegalStateException("Either accessId or both appId and appCode should be set.");
            } else {
                stringBuilder.append("app_id=").append(this.e).append(com.alipay.sdk.h.a.b).append("app_code=").append(this.f);
            }
            Map b = b();
            List<String> arrayList = new ArrayList(b.keySet());
            Collections.sort(arrayList);
            for (String str : arrayList) {
                try {
                    stringBuilder.append(com.alipay.sdk.h.a.b).append(str).append("=").append(URLEncoder.encode((String) b.get(str), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                }
            }
            return stringBuilder.toString();
        }
        throw new AssertionError();
    }

    protected Map<String, String> b() {
        Map hashMap = new HashMap();
        if (this.h != null) {
            hashMap.put("client", this.h);
        }
        if (this.i != null) {
            hashMap.put("lang", this.i.toLowerCase());
        }
        if (this.j != null) {
            hashMap.put("routing", this.j);
        }
        if (this.k != null) {
            hashMap.put("dbg", this.k.booleanValue() ? "2" : "0");
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public i a(String str) {
        this.h = str;
        return this;
    }

    public i b(String str) {
        this.i = str;
        return this;
    }

    public i a(c cVar) {
        this.j = cVar != null ? cVar.e : null;
        return this;
    }

    public i b(Boolean bool) {
        this.l = bool;
        return this;
    }

    public i a(d[] dVarArr) {
        int i = 0;
        if (dVarArr.length > 0) {
            int[] iArr = new int[16];
            Arrays.fill(iArr, 0);
            for (d ordinal : dVarArr) {
                int ordinal2 = ordinal.ordinal();
                if (ordinal2 < 16) {
                    iArr[ordinal2] = 1;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            int length = iArr.length;
            while (i < length) {
                stringBuilder.append(iArr[i]);
                i++;
            }
            this.m = stringBuilder.toString();
        }
        return this;
    }

    public boolean e() {
        return this.n != null;
    }

    public String f() {
        return this.n;
    }

    public String g() {
        return this.b;
    }

    public String h() {
        return this.h;
    }

    public String i() {
        return this.i;
    }

    public Boolean j() {
        return this.l;
    }

    public Boolean k() {
        return this.k;
    }

    public c l() {
        return c.a(this.j);
    }

    protected String m() {
        return this.m;
    }

    public String toString() {
        return d();
    }

    private static String c(String str) {
        if (!str.startsWith("http://") && !str.startsWith("https://")) {
            throw new IllegalArgumentException("Host should contain a schema, i.e. start with \"http://\": " + str);
        } else if (str.endsWith(dji.pilot.usercenter.protocol.d.t)) {
            return str;
        } else {
            return str + dji.pilot.usercenter.protocol.d.t;
        }
    }
}
