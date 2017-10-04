package com.alipay.sdk.j;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.alipay.sdk.app.a.a;
import com.alipay.sdk.app.a.c;
import com.alipay.sdk.d.e;

public final class j {
    private static boolean a(Context context, String str) {
        boolean z = false;
        try {
            z = PreferenceManager.getDefaultSharedPreferences(context).contains(str);
        } catch (Throwable th) {
        }
        return z;
    }

    private static void b(Context context, String str) {
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).commit();
        } catch (Throwable th) {
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            Object a = e.a("1234567812345678A1F20E7=", str2);
            if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(a)) {
                a.a(c.c, c.l, String.format("%s,%s", new Object[]{str, str2}));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, a).commit();
        } catch (Throwable th) {
        }
    }

    public static String b(Context context, String str, String str2) {
        String str3 = null;
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
            if (!TextUtils.isEmpty(string)) {
                str3 = e.b("1234567812345678A1F20E7=", string);
            }
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(r0)) {
                a.a(c.c, c.k, String.format("%s,%s", new Object[]{str, string}));
            }
        } catch (Exception e) {
        }
        return str3;
    }

    private static String a() {
        return "1234567812345678A1F20E7=";
    }
}
