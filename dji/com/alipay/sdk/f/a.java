package com.alipay.sdk.f;

import android.text.TextUtils;
import com.alipay.sdk.app.a.c;
import com.alipay.sdk.j.i;

public final class a {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split(com.alipay.sdk.h.a.b);
        if (split.length == 0) {
            return "";
        }
        Object obj = null;
        Object obj2 = null;
        Object obj3 = null;
        Object obj4 = null;
        for (String str2 : split) {
            if (TextUtils.isEmpty(obj4)) {
                obj4 = !str2.contains("biz_type") ? null : e(str2);
            }
            if (TextUtils.isEmpty(obj3)) {
                obj3 = !str2.contains("biz_no") ? null : e(str2);
            }
            if (TextUtils.isEmpty(obj2)) {
                obj2 = (!str2.contains(c.r) || str2.startsWith(c.q)) ? null : e(str2);
            }
            if (TextUtils.isEmpty(obj)) {
                if (str2.contains("app_userid")) {
                    obj = e(str2);
                } else {
                    obj = null;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(obj4)) {
            stringBuilder.append("biz_type=" + obj4 + i.b);
        }
        if (!TextUtils.isEmpty(obj3)) {
            stringBuilder.append("biz_no=" + obj3 + i.b);
        }
        if (!TextUtils.isEmpty(obj2)) {
            stringBuilder.append("trade_no=" + obj2 + i.b);
        }
        if (!TextUtils.isEmpty(obj)) {
            stringBuilder.append("app_userid=" + obj + i.b);
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.endsWith(i.b)) {
            return stringBuilder2.substring(0, stringBuilder2.length() - 1);
        }
        return stringBuilder2;
    }

    private static String b(String str) {
        if (str.contains("biz_type")) {
            return e(str);
        }
        return null;
    }

    private static String c(String str) {
        if (str.contains("biz_no")) {
            return e(str);
        }
        return null;
    }

    private static String d(String str) {
        if (!str.contains(c.r) || str.startsWith(c.q)) {
            return null;
        }
        return e(str);
    }

    private static String e(String str) {
        String[] split = str.split("=");
        if (split.length <= 1) {
            return null;
        }
        String str2 = split[1];
        if (str2.contains("\"")) {
            return str2.replaceAll("\"", "");
        }
        return str2;
    }

    private static String f(String str) {
        if (str.contains("app_userid")) {
            return e(str);
        }
        return null;
    }
}
