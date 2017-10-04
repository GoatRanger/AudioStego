package com.alipay.sdk.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.sdk.b.a;
import com.alipay.sdk.h.b;
import com.alipay.sdk.j.i;
import com.alipay.sdk.j.k;
import com.alipay.sdk.j.l;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.util.HashMap;
import java.util.Random;

public final class c {
    private static final String d = "virtualImeiAndImsi";
    private static final String e = "virtual_imei";
    private static final String f = "virtual_imsi";
    private static c g;
    public String a;
    public String b = "sdk-and-lite";
    public String c;

    private String c() {
        return this.c;
    }

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (g == null) {
                g = new c();
            }
            cVar = g;
        }
        return cVar;
    }

    public final synchronized void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            PreferenceManager.getDefaultSharedPreferences(b.a().a).edit().putString(com.alipay.sdk.b.b.i, str).commit();
            a.b = str;
        }
    }

    private static String d() {
        return "1";
    }

    private static String a(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    private static String e() {
        return "-1;-1";
    }

    private String a(com.alipay.sdk.i.b bVar) {
        String d;
        String a;
        Context context = b.a().a;
        com.alipay.sdk.j.b a2 = com.alipay.sdk.j.b.a(context);
        if (TextUtils.isEmpty(this.a)) {
            String a3 = l.a();
            String b = l.b();
            d = l.d(context);
            a = k.a(context);
            this.a = "Msp/15.1.3" + " (" + a3 + i.b + b + i.b + d + i.b + a.substring(0, a.indexOf("://")) + i.b + l.e(context) + i.b + Float.toString(new TextView(context).getTextSize());
        }
        d = com.alipay.sdk.j.b.b(context).p;
        a = "-1;-1";
        String str = "1";
        String a4 = a2.a();
        String b2 = a2.b();
        Context context2 = b.a().a;
        SharedPreferences sharedPreferences = context2.getSharedPreferences(d, 0);
        CharSequence string = sharedPreferences.getString(f, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(com.alipay.sdk.i.b.a().a)) {
                Object c = b.a().c();
                string = TextUtils.isEmpty(c) ? b() : c.substring(3, 18);
            } else {
                string = com.alipay.sdk.j.b.a(context2).a();
            }
            sharedPreferences.edit().putString(f, string).commit();
        }
        CharSequence charSequence = string;
        Context context3 = b.a().a;
        SharedPreferences sharedPreferences2 = context3.getSharedPreferences(d, 0);
        string = sharedPreferences2.getString(e, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(com.alipay.sdk.i.b.a().a)) {
                string = b();
            } else {
                string = com.alipay.sdk.j.b.a(context3).b();
            }
            sharedPreferences2.edit().putString(e, string).commit();
        }
        CharSequence charSequence2 = string;
        if (bVar != null) {
            this.c = bVar.b;
        }
        String replace = Build.MANUFACTURER.replace(i.b, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        String replace2 = Build.MODEL.replace(i.b, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        boolean b3 = b.b();
        String str2 = a2.a;
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        String ssid = connectionInfo != null ? connectionInfo.getSSID() : WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        String bssid = connectionInfo != null ? connectionInfo.getBSSID() : "00";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a).append(i.b).append(d).append(i.b).append(a).append(i.b).append(str).append(i.b).append(a4).append(i.b).append(b2).append(i.b).append(this.c).append(i.b).append(replace).append(i.b).append(replace2).append(i.b).append(b3).append(i.b).append(str2).append(";-1;-1;").append(this.b).append(i.b).append(charSequence).append(i.b).append(charSequence2).append(i.b).append(ssid).append(i.b).append(bssid);
        if (bVar != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(com.alipay.sdk.b.b.c, bVar.a);
            hashMap.put(com.alipay.sdk.b.b.g, b.a().c());
            c = a(context, hashMap);
            if (!TextUtils.isEmpty(c)) {
                stringBuilder.append(i.b).append(c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private static String f() {
        Context context = b.a().a;
        SharedPreferences sharedPreferences = context.getSharedPreferences(d, 0);
        String string = sharedPreferences.getString(e, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(com.alipay.sdk.i.b.a().a)) {
                string = b();
            } else {
                string = com.alipay.sdk.j.b.a(context).b();
            }
            sharedPreferences.edit().putString(e, string).commit();
        }
        return string;
    }

    private static String g() {
        Context context = b.a().a;
        SharedPreferences sharedPreferences = context.getSharedPreferences(d, 0);
        String string = sharedPreferences.getString(f, null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(com.alipay.sdk.i.b.a().a)) {
                Object c = b.a().c();
                if (TextUtils.isEmpty(c)) {
                    string = b();
                } else {
                    string = c.substring(3, 18);
                }
            } else {
                string = com.alipay.sdk.j.b.a(context).a();
            }
            sharedPreferences.edit().putString(f, string).commit();
        }
        return string;
    }

    public static String b() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(9000) + 1000);
    }

    private static String b(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo != null) {
            return connectionInfo.getSSID();
        }
        return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
    }

    private static String c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo != null) {
            return connectionInfo.getBSSID();
        }
        return "00";
    }

    public static String a(Context context, HashMap<String, String> hashMap) {
        String str = "";
        try {
            str = com.alipay.d.a.a.a(context, hashMap);
        } catch (Throwable th) {
        }
        return str;
    }
}
