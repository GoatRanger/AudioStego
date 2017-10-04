package com.e;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import android.text.TextUtils;
import com.e.cv.a.b;
import com.e.cv.a.c;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.here.odnp.config.OdnpConfigStatic;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import org.json.JSONObject;

public class bo {
    private static boolean A = false;
    private static int B = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
    private static int C = 0;
    private static boolean D = true;
    private static long E = com.alipay.e.a.a.c.a.a.b;
    private static Context a;
    private static String b = "提示信息";
    private static String c = "确认";
    private static String d = "取消";
    private static String e = "";
    private static String f = "";
    private static String g = "";
    private static boolean h = false;
    private static long i = 0;
    private static long j = 0;
    private static long k = OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL;
    private static boolean l = false;
    private static int m = 0;
    private static boolean n = false;
    private static int o = 0;
    private static boolean p = false;
    private static boolean q = true;
    private static boolean r = true;
    private static int s = -1;
    private static long t = 0;
    private static boolean u = true;
    private static int v = -1;
    private static long w = 0;
    private static String x;
    private static String y;
    private static boolean z = false;

    static class a {
        boolean a = false;
        String b = "0";
        boolean c = false;
        int d = 5;

        a() {
        }
    }

    private static a a(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (optJSONObject != null) {
                a aVar = new a();
                aVar.a = cv.a(optJSONObject.optString("b", "0"), false);
                aVar.b = optJSONObject.optString("t", "0");
                aVar.c = cv.a(optJSONObject.optString("st", null), false);
                aVar.d = optJSONObject.optInt("i", 5);
                return aVar;
            }
        }
        return null;
    }

    private static void a(Context context, long j) {
        try {
            Editor edit = context.getSharedPreferences("pref", 0).edit();
            edit.putLong("ngpsTime", j);
            edit.putInt("ngpsCount", 0);
            bq.a(edit);
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "resetPrefsNGPS");
        }
    }

    private static void a(Context context, boolean z) {
        bq.a(context, "pref", "exception", z);
    }

    private static void a(com.e.cv.a aVar) {
        try {
            JSONObject jSONObject = aVar.g;
            if (jSONObject != null) {
                q = cv.a(jSONObject.optString("opflag", ""), true);
            }
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "loadConfigData_offlineLoc");
        }
    }

    public static synchronized void a(boolean z) {
        synchronized (bo.class) {
            z = z;
        }
    }

    public static boolean a() {
        return l;
    }

    public static boolean a(long j) {
        long b = br.b();
        return h && b - j <= i && b - j >= k;
    }

    public static synchronized boolean a(Context context) {
        boolean z;
        synchronized (bo.class) {
            a = context;
            z = false;
            try {
                com.e.cv.a a = cv.a(context, bc.a("2.5.0"), bc.c(context), null);
                if (a != null) {
                    z = k(a);
                }
            } catch (Throwable th) {
                bc.a(th, "AuthUtil", "getConfig");
            }
        }
        return z;
    }

    public static int b() {
        return m;
    }

    public static void b(Context context) {
        try {
            boolean b = bq.b(context, "pref", "exception", false);
            dc a = bc.a("2.5.0");
            a.a(b);
            dj.a(context, a);
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "isUploadException");
        }
    }

    private static void b(com.e.cv.a aVar) {
        try {
            JSONObject jSONObject = aVar.c;
            if (jSONObject != null) {
                r = cv.a(jSONObject.optString("callamapflag", null), true);
                s = jSONObject.optInt(ParamKey.COUNT, -1);
                t = jSONObject.optLong("sysTime", 0);
                if (s != -1 && t != 0) {
                    if (!br.a(t, bq.b(a, "pref", "nowtime", 0))) {
                        c(a);
                    }
                }
            }
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "loadConfigData_callAMapSer");
        }
    }

    public static boolean b(long j) {
        if (!A) {
            return false;
        }
        long a = br.a();
        if (a - j < ((long) B)) {
            return false;
        }
        if (C == -1) {
            return true;
        }
        if (br.b(a, bq.b(a, "pref", "ngpsTime", 0))) {
            int b = bq.b(a, "pref", "ngpsCount", 0);
            if (b >= C) {
                return false;
            }
            bq.a(a, "pref", "ngpsCount", b + 1);
            return true;
        }
        a(a, a);
        bq.a(a, "pref", "ngpsCount", 1);
        return true;
    }

    private static void c(Context context) {
        Editor edit;
        try {
            edit = context.getSharedPreferences("pref", 0).edit();
            if (t == 0) {
                edit.remove("nowtime");
            } else {
                edit.putLong("nowtime", t);
            }
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "resetPrefsBind");
            return;
        }
        if (s == -1) {
            edit.remove(ParamKey.COUNT);
        } else {
            edit.putInt(ParamKey.COUNT, 0);
        }
        bq.a(edit);
    }

    private static void c(com.e.cv.a aVar) {
        try {
            JSONObject jSONObject = aVar.d;
            if (jSONObject != null) {
                u = cv.a(jSONObject.optString("amappushflag", null), false);
                v = jSONObject.optInt(ParamKey.COUNT, -1);
                w = jSONObject.optLong("sysTime", 0);
                if (v != -1 && w != 0) {
                    if (!br.a(w, bq.b(a, "pref", "pushSerTime", 0))) {
                        d(a);
                    }
                }
            }
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "loadConfigData_callAMapPush");
        }
    }

    public static boolean c() {
        return n;
    }

    public static int d() {
        return o;
    }

    private static void d(Context context) {
        Editor edit;
        try {
            edit = context.getSharedPreferences("pref", 0).edit();
            if (w == 0) {
                edit.remove("pushSerTime");
            } else {
                edit.putLong("pushSerTime", w);
            }
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "resetPrefsBind");
            return;
        }
        if (v == -1) {
            edit.remove("pushCount");
        } else {
            edit.putInt("pushCount", 0);
        }
        bq.a(edit);
    }

    private static void d(com.e.cv.a aVar) {
        try {
            JSONObject jSONObject = aVar.e;
            if (jSONObject != null) {
                z = cv.a(jSONObject.optString("f", null), false);
                if (z) {
                    long b = bq.b(a, "abcd", "abc", 0);
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (elapsedRealtime - b > 3600000) {
                        bq.a(a, "abcd", "abc", elapsedRealtime);
                    }
                    if (elapsedRealtime > b && elapsedRealtime - b < 3600000) {
                        z = false;
                    }
                    if (elapsedRealtime < b) {
                        z = false;
                        bq.a(a, "abcd", "abc", elapsedRealtime);
                    }
                }
                b = jSONObject.optString("a", "提示信息");
                c = jSONObject.optString("o", "确认");
                d = jSONObject.optString("c", "取消");
                e = jSONObject.optString("i", "");
                f = jSONObject.optString("u", "");
                g = jSONObject.optString("t", "");
                if (!TextUtils.isEmpty(e) && !"null".equals(e)) {
                    return;
                }
                if (TextUtils.isEmpty(f) || "null".equals(f)) {
                    z = false;
                }
            }
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "loadConfigData_openAMap");
        }
    }

    private static void e(com.e.cv.a aVar) {
        try {
            dc a = bc.a("2.5.0");
            c cVar = aVar.m;
            if (cVar != null) {
                Object obj = cVar.b;
                Object obj2 = cVar.a;
                Object obj3 = cVar.c;
                if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2) || TextUtils.isEmpty(obj3)) {
                    z.a(a, null, a);
                    return;
                }
                z.a(a, new u(obj2, obj, obj3), a);
                return;
            }
            z.a(a, null, a);
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "loadConfigData_sdkUpdate");
        }
    }

    public static boolean e() {
        return p;
    }

    private static void f(com.e.cv.a aVar) {
        try {
            b bVar = aVar.n;
            if (bVar != null) {
                x = bVar.a;
                y = bVar.b;
                if (!TextUtils.isEmpty(x) && !TextUtils.isEmpty(y)) {
                    new db(a, "loc", x, y).a();
                }
            }
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "loadConfigData_groupOffset");
        }
    }

    public static boolean f() {
        at.a = q;
        return q;
    }

    public static String g() {
        return b;
    }

    private static void g(com.e.cv.a aVar) {
        try {
            com.e.cv.a.a aVar2 = aVar.l;
            if (aVar2 != null) {
                boolean z = aVar2.a;
                dc a = bc.a("2.5.0");
                a.a(z);
                dj.a(a, a);
                a(a, z);
            }
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "loadConfigData_uploadException");
        }
    }

    public static String h() {
        return c;
    }

    private static void h(com.e.cv.a aVar) {
        try {
            JSONObject jSONObject = aVar.f;
            if (jSONObject != null && cv.a(jSONObject.optString("able", null), false)) {
                a a = a(jSONObject, "fs");
                if (a != null) {
                    l = a.c;
                    m = Integer.parseInt(a.b);
                }
                a = a(jSONObject, "us");
                if (a != null) {
                    n = a.c;
                    p = a.a;
                    try {
                        o = Integer.parseInt(a.b);
                    } catch (Throwable th) {
                        bc.a(th, "AuthUtil", "loadconfig part1");
                    }
                    if (o < 2) {
                        n = false;
                    }
                }
                a = a(jSONObject, "rs");
                if (a != null) {
                    h = a.c;
                    if (h) {
                        j = br.b();
                        k = (long) (a.d * 1000);
                    }
                    try {
                        i = (long) (Integer.parseInt(a.b) * 1000);
                    } catch (Throwable th2) {
                        bc.a(th2, "AuthUtil", "loadconfig part");
                    }
                }
            }
        } catch (Throwable th22) {
            bc.a(th22, "AuthUtil", "loadConfigData_locate");
        }
    }

    public static String i() {
        return d;
    }

    private static void i(com.e.cv.a aVar) {
        try {
            JSONObject jSONObject = aVar.h;
            if (jSONObject != null) {
                A = cv.a(jSONObject.optString("able"), false);
                int optInt = jSONObject.optInt("c", 3);
                if (optInt == 0) {
                    B = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
                } else {
                    B = optInt * 1000;
                }
                C = jSONObject.optInt("t", 0) / 2;
            }
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "loadConfigData_ngps");
        }
    }

    public static String j() {
        return e;
    }

    private static void j(com.e.cv.a aVar) {
        try {
            JSONObject jSONObject = aVar.i;
            if (jSONObject != null) {
                D = cv.a(jSONObject.optString("able", ""), true);
                E = (long) (jSONObject.optInt("c", 0) * 1000);
            }
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "loadConfigData_cacheAble");
        }
    }

    public static String k() {
        return f;
    }

    private static boolean k(com.e.cv.a aVar) {
        try {
            a(aVar);
            b(aVar);
            c(aVar);
            d(aVar);
            e(aVar);
            f(aVar);
            g(aVar);
            h(aVar);
            i(aVar);
            j(aVar);
            return true;
        } catch (Throwable th) {
            bc.a(th, "AuthUtil", "loadconfig");
            return false;
        }
    }

    public static String l() {
        return g;
    }

    public static boolean m() {
        if (!r) {
            return false;
        }
        if (s == -1 || t == 0) {
            return true;
        }
        if (br.a(t, bq.b(a, "pref", "nowtime", 0))) {
            int b = bq.b(a, "pref", ParamKey.COUNT, 0);
            if (b >= s) {
                return false;
            }
            bq.a(a, "pref", ParamKey.COUNT, b + 1);
            return true;
        }
        c(a);
        bq.a(a, "pref", ParamKey.COUNT, 1);
        return true;
    }

    public static boolean n() {
        if (!u) {
            return false;
        }
        if (v == -1 || w == 0) {
            return true;
        }
        if (br.a(w, bq.b(a, "pref", "pushSerTime", 0))) {
            int b = bq.b(a, "pref", "pushCount", 0);
            if (b >= v) {
                return false;
            }
            bq.a(a, "pref", "pushCount", b + 1);
            return true;
        }
        d(a);
        bq.a(a, "pref", "pushCount", 1);
        return true;
    }

    public static synchronized boolean o() {
        boolean z;
        synchronized (bo.class) {
            z = z;
        }
        return z;
    }

    public static boolean p() {
        return D;
    }

    public static long q() {
        return E;
    }
}
