package com.alipay.sdk.h;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    public static final String a = "\"&";
    public static final String b = "&";
    public static final String c = "bizcontext=\"";
    public static final String d = "bizcontext=";
    public static final String e = "\"";
    public static final String f = "appkey";
    public static final String g = "ty";
    public static final String h = "sv";
    public static final String i = "an";
    public static final String j = "av";
    public static final String k = "sdk_start_time";
    public static final String l = "UTF-8";
    private String m = "";
    private String n = "";

    public a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.m = packageInfo.versionName;
            this.n = packageInfo.packageName;
        } catch (Exception e) {
        }
    }

    public final String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        if ((!str.contains(a) ? 1 : null) != null) {
            return c(str);
        }
        return d(str);
    }

    private static boolean b(String str) {
        return !str.contains(a);
    }

    private String c(String str) {
        try {
            String a = a(str, b, d);
            if (TextUtils.isEmpty(a)) {
                return str + b + b(d, "");
            }
            int indexOf = str.indexOf(a);
            String substring = str.substring(0, indexOf);
            return substring + b(a, d, "") + str.substring(indexOf + a.length());
        } catch (Throwable th) {
            return str;
        }
    }

    private String d(String str) {
        try {
            String a = a(str, a, c);
            if (TextUtils.isEmpty(a)) {
                return str + b + b(c, "\"");
            }
            if (!a.endsWith("\"")) {
                a = a + "\"";
            }
            int indexOf = str.indexOf(a);
            String substring = str.substring(0, indexOf);
            return substring + b(a, c, "\"") + str.substring(indexOf + a.length());
        } catch (Throwable th) {
            return str;
        }
    }

    private static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str4;
        String[] split = str.split(str2);
        int i = 0;
        while (i < split.length) {
            if (!TextUtils.isEmpty(split[i]) && split[i].startsWith(str3)) {
                str4 = split[i];
                break;
            }
            i++;
        }
        str4 = null;
        return str4;
    }

    private String b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        return str + a("", "") + str2;
    }

    public final String a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", com.alipay.sdk.b.a.c);
            jSONObject.put(g, "and_lite");
            jSONObject.put(h, com.alipay.sdk.b.a.f);
            jSONObject.put(i, this.n);
            jSONObject.put(j, this.m);
            jSONObject.put(k, System.currentTimeMillis());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    private String b(String str, String str2, String str3) throws JSONException, UnsupportedEncodingException {
        String substring = str.substring(str2.length());
        JSONObject jSONObject = new JSONObject(substring.substring(0, substring.length() - str3.length()));
        if (!jSONObject.has("appkey")) {
            jSONObject.put("appkey", com.alipay.sdk.b.a.c);
        }
        if (!jSONObject.has(g)) {
            jSONObject.put(g, "and_lite");
        }
        if (!jSONObject.has(h)) {
            jSONObject.put(h, com.alipay.sdk.b.a.f);
        }
        if (!jSONObject.has(i)) {
            jSONObject.put(i, this.n);
        }
        if (!jSONObject.has(j)) {
            jSONObject.put(j, this.m);
        }
        if (!jSONObject.has(k)) {
            jSONObject.put(k, System.currentTimeMillis());
        }
        return str2 + jSONObject.toString() + str3;
    }
}
