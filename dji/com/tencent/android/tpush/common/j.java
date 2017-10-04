package com.tencent.android.tpush.common;

public class j {
    public static String a(String str) {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{str});
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean a() {
        try {
            return (p.a(a("ro.miui.ui.version.code")) && p.a(a(a("ro.miui.ui.version.name"))) && p.a(a(a("ro.miui.internal.storage")))) ? false : true;
        } catch (Throwable th) {
            return false;
        }
    }
}
