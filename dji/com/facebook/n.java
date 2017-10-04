package com.facebook;

import com.alipay.sdk.j.i;
import com.facebook.internal.ah;
import com.facebook.internal.l;
import java.net.HttpURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

public final class n {
    public static final int a = -1;
    public static final int b = -1;
    static final b c = new b(200, 299);
    private static final String d = "code";
    private static final String e = "body";
    private static final String f = "error";
    private static final String g = "type";
    private static final String h = "code";
    private static final String i = "message";
    private static final String j = "error_code";
    private static final String k = "error_subcode";
    private static final String l = "error_msg";
    private static final String m = "error_reason";
    private static final String n = "error_user_title";
    private static final String o = "error_user_msg";
    private static final String p = "is_transient";
    private final JSONObject A;
    private final Object B;
    private final HttpURLConnection C;
    private final k D;
    private final a q;
    private final int r;
    private final int s;
    private final int t;
    private final String u;
    private final String v;
    private final String w;
    private final String x;
    private final String y;
    private final JSONObject z;

    public enum a {
        LOGIN_RECOVERABLE,
        OTHER,
        TRANSIENT
    }

    private static class b {
        private final int a;
        private final int b;

        private b(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        boolean a(int i) {
            return this.a <= i && i <= this.b;
        }
    }

    private n(int i, int i2, int i3, String str, String str2, String str3, String str4, boolean z, JSONObject jSONObject, JSONObject jSONObject2, Object obj, HttpURLConnection httpURLConnection, k kVar) {
        this.r = i;
        this.s = i2;
        this.t = i3;
        this.u = str;
        this.v = str2;
        this.A = jSONObject;
        this.z = jSONObject2;
        this.B = obj;
        this.C = httpURLConnection;
        this.w = str3;
        this.x = str4;
        Object obj2 = null;
        if (kVar != null) {
            this.D = kVar;
            obj2 = 1;
        } else {
            this.D = new s(this, str2);
        }
        l o = o();
        this.q = obj2 != null ? a.OTHER : o.a(i2, i3, z);
        this.y = o.a(this.q);
    }

    n(HttpURLConnection httpURLConnection, Exception exception) {
        k kVar;
        if (exception instanceof k) {
            kVar = (k) exception;
        } else {
            kVar = new k((Throwable) exception);
        }
        this(-1, -1, -1, null, null, null, null, false, null, null, null, httpURLConnection, kVar);
    }

    public n(int i, String str, String str2) {
        this(-1, i, -1, str, str2, null, null, false, null, null, null, null, null);
    }

    public a a() {
        return this.q;
    }

    public int b() {
        return this.r;
    }

    public int c() {
        return this.s;
    }

    public int d() {
        return this.t;
    }

    public String e() {
        return this.u;
    }

    public String f() {
        if (this.v != null) {
            return this.v;
        }
        return this.D.getLocalizedMessage();
    }

    public String g() {
        return this.y;
    }

    public String h() {
        return this.x;
    }

    public String i() {
        return this.w;
    }

    public JSONObject j() {
        return this.A;
    }

    public JSONObject k() {
        return this.z;
    }

    public Object l() {
        return this.B;
    }

    public HttpURLConnection m() {
        return this.C;
    }

    public k n() {
        return this.D;
    }

    public String toString() {
        return "{HttpStatus: " + this.r + ", errorCode: " + this.s + ", errorType: " + this.u + ", errorMessage: " + f() + i.d;
    }

    static n a(JSONObject jSONObject, Object obj, HttpURLConnection httpURLConnection) {
        try {
            if (jSONObject.has("code")) {
                JSONObject jSONObject2;
                int i = jSONObject.getInt("code");
                Object a = ah.a(jSONObject, e, v.a);
                if (a != null && (a instanceof JSONObject)) {
                    jSONObject2 = (JSONObject) a;
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    boolean z = false;
                    int i2 = -1;
                    int i3 = -1;
                    Object obj2 = null;
                    if (jSONObject2.has("error")) {
                        JSONObject jSONObject3 = (JSONObject) ah.a(jSONObject2, "error", null);
                        str = jSONObject3.optString("type", null);
                        str2 = jSONObject3.optString("message", null);
                        i2 = jSONObject3.optInt("code", -1);
                        i3 = jSONObject3.optInt("error_subcode", -1);
                        str3 = jSONObject3.optString(o, null);
                        str4 = jSONObject3.optString(n, null);
                        z = jSONObject3.optBoolean(p, false);
                        obj2 = 1;
                    } else if (jSONObject2.has("error_code") || jSONObject2.has(l) || jSONObject2.has(m)) {
                        str = jSONObject2.optString(m, null);
                        str2 = jSONObject2.optString(l, null);
                        i2 = jSONObject2.optInt("error_code", -1);
                        i3 = jSONObject2.optInt("error_subcode", -1);
                        obj2 = 1;
                    }
                    if (obj2 != null) {
                        return new n(i, i2, i3, str, str2, str4, str3, z, jSONObject2, jSONObject, obj, httpURLConnection, null);
                    }
                }
                if (!c.a(i)) {
                    if (jSONObject.has(e)) {
                        jSONObject2 = (JSONObject) ah.a(jSONObject, e, v.a);
                    } else {
                        jSONObject2 = null;
                    }
                    return new n(i, -1, -1, null, null, null, null, false, jSONObject2, jSONObject, obj, httpURLConnection, null);
                }
            }
        } catch (JSONException e) {
        }
        return null;
    }

    static synchronized l o() {
        l d;
        synchronized (n.class) {
            com.facebook.internal.ah.b e = ah.e(o.k());
            if (e == null) {
                d = l.d();
            } else {
                d = e.e();
            }
        }
        return d;
    }
}
