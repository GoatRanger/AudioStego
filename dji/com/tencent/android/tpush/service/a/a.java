package com.tencent.android.tpush.service.a;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.d.e;
import com.tencent.android.tpush.service.l;
import org.json.JSONObject;

public class a {
    public static long a = a(l.e());
    public static int b = a("recTo", 30000);
    public static int c = a("hbIntvl", 299980);
    public static int d = a("httpHbIntvl", 600000);
    public static int e = a("stIntvl", 54000000);
    public static int f = a("cnMsgExp", 60000);
    public static int g = a("fqcSuc", 10);
    public static int h = a("fqcFal", 100);
    public static int i = a("rptIntvl", 1200);
    public static int j = a("rptMaxCnt", 5);
    public static int k = a("httpRtCnt", 3);
    public static int l = a("ackMaxCnt", 3);
    public static int m = a("ackDuration", 180000);
    public static int n = a("loadIpIntvl", 72000000);
    public static int o = a("redirectConnectTime", 30000);
    public static int p = a("redirectSoTime", 20000);
    public static int q = a("strategyExpiredTime", 1440);
    public static int r;
    public static int s = a("logFileSizeLimit", 262144);
    public static int t = a("errCount", 5);
    public static String u = a("logUploadDomain", "183.61.46.193");
    public static int v = a("rptLive", 0);
    public static int w = a("rptLiveIntvl", 3600);
    public static String x = null;
    public static int y = 1;
    public static int z = 1;

    public static void a(String str) {
        int i = 30000;
        int i2 = 3600;
        int i3 = 5;
        int i4 = 3;
        try {
            JSONObject jSONObject = new JSONObject(str);
            a = (long) a("confVer", jSONObject);
            a = a == 0 ? 1 : a;
            b = a("recTo", jSONObject) * 1000;
            b = b == 0 ? 30000 : b;
            c = (a("hbIntvl", jSONObject) * 60) * 1000;
            c = c == 0 ? 299980 : c;
            d = (a("httpHbIntvl", jSONObject) * 60) * 1000;
            d = d == 0 ? 600000 : d;
            e = (a("stIntvl", jSONObject) * 60) * 1000;
            e = e == 0 ? 54000000 : e;
            f = a("cnMsgExp", jSONObject) * 1000;
            f = f == 0 ? 60000 : f;
            g = a("fqcSuc", jSONObject);
            g = g == 0 ? 10 : g;
            h = a("fqcFal", jSONObject);
            h = h == 0 ? 100 : h;
            i = a("rptIntvl", jSONObject);
            i = i == 0 ? 1200 : i;
            j = a("rptMaxCnt", jSONObject);
            j = j == 0 ? 5 : j;
            k = a("httpRtCnt", jSONObject);
            k = k == 0 ? 3 : k;
            l = a("ackMaxCnt", jSONObject);
            if (l != 0) {
                i4 = l;
            }
            l = i4;
            m = a("ackDuration", jSONObject) * 1000;
            m = m == 0 ? 180000 : m;
            n = ((a("loadIpIntvl", jSONObject) * 60) * 60) * 1000;
            n = n == 0 ? 72000000 : n;
            o = a("redirectConnectTime", jSONObject);
            if (o != 0) {
                i = o;
            }
            o = i;
            p = a("redirectSoTime", jSONObject);
            p = p == 0 ? 20000 : p;
            q = a("strategyExpiredTime", jSONObject);
            q = q == 0 ? 1440 : q;
            v = a("rptLive", jSONObject);
            v = v == 0 ? 0 : v;
            w = a("rptLiveIntvl", jSONObject);
            if (w != 3600) {
                i2 = w;
            }
            w = i2;
            r = a("logLevel", jSONObject);
            s = a("logFileSizeLimit", jSONObject) * 1024;
            s = s == 0 ? 262144 : s;
            t = a("errCount", jSONObject);
            if (t != 0) {
                i3 = t;
            }
            t = i3;
            u = b("logUploadDomain", jSONObject);
            u = TextUtils.isEmpty(u) ? "183.61.46.193" : u;
            y = jSONObject.optInt("enableWd", 1);
            z = jSONObject.optInt("report", 1);
            x = jSONObject.optString("stopXG", null);
            if (l.e() != null) {
                e.b(l.e(), b("confVer"), a);
                e.a(l.e(), b("recTo"), b);
                e.a(l.e(), b("hbIntvl"), c);
                e.a(l.e(), b("httpHbIntvl"), d);
                e.a(l.e(), b("stIntvl"), e);
                e.a(l.e(), b("cnMsgExp"), f);
                e.a(l.e(), b("fqcSuc"), g);
                e.a(l.e(), b("fqcFal"), h);
                e.a(l.e(), b("rptIntvl"), i);
                e.a(l.e(), b("rptMaxCnt"), j);
                e.a(l.e(), b("httpRtCnt"), k);
                e.a(l.e(), b("ackMaxCnt"), l);
                e.a(l.e(), b("ackDuration"), m);
                e.a(l.e(), b("loadIpIntvl"), n);
                e.a(l.e(), b("redirectConnectTime"), o);
                e.a(l.e(), b("redirectSoTime"), p);
                e.a(l.e(), b("strategyExpiredTime"), q);
                e.a(l.e(), b("rptLive"), v);
                e.a(l.e(), b("rptLiveIntvl"), w);
                e.a(l.e(), b("logLevel"), r);
                e.a(l.e(), b("logFileSizeLimit"), s);
                e.a(l.e(), b("errCount"), t);
                if (!e.a(x)) {
                    e.a(l.e(), b("stopXG"), Rijndael.encrypt(x), true);
                }
                e.a(l.e(), b("enableWd"), y);
                e.a(l.e(), b("report"), z);
            }
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "parseValue failed.", e);
        }
    }

    public static long a(Context context) {
        if (context != null) {
            return e.c(context, b("confVer"), 1);
        }
        return 1;
    }

    public static boolean a(Context context, long j) {
        if (context == null || a(context) == j) {
            return false;
        }
        return e.b(context, b("confVer"), j);
    }

    public static int a(String str, int i) {
        if (l.e() != null) {
            return e.b(l.e(), b(str), i);
        }
        return i;
    }

    public static String a(String str, String str2) {
        if (l.e() == null) {
            return str2;
        }
        CharSequence a = e.a(l.e(), b(str), false);
        if (TextUtils.isEmpty(a)) {
            return str2;
        }
        return a;
    }

    public static int a(String str, JSONObject jSONObject) {
        if (!(jSONObject == null || e.a(str))) {
            try {
                return jSONObject.getInt(str);
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "getJsonInt", e);
            }
        }
        return 0;
    }

    public static String b(String str, JSONObject jSONObject) {
        if (!(jSONObject == null || e.a(str))) {
            try {
                return jSONObject.getString(str);
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c(Constants.ServiceLogTag, "getJsonStr", e);
            }
        }
        return "";
    }

    public static String b(String str) {
        return "com.tencent.tpus." + str;
    }
}
