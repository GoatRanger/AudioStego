package com.alipay.e.a.a.d;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.util.Map;

public class c {
    public static void a(Context context, String str, String str2, String str3) {
        if (str3 != null) {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            if (edit != null) {
                edit.clear();
                edit.putString(str2, str3);
                edit.commit();
            }
        }
    }

    public static void a(Context context, String str, Map<String, String> map) {
        if (map != null) {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            if (edit != null) {
                edit.clear();
                for (String str2 : map.keySet()) {
                    edit.putString(str2, (String) map.get(str2));
                }
                edit.commit();
            }
        }
    }

    public static String b(Context context, String str, String str2, String str3) {
        return context.getSharedPreferences(str, 0).getString(str2, str3);
    }
}
