package com.flurry.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.flurry.sdk.jr.a;

public class hw implements a {
    private static hw a;
    private static final String b = hw.class.getSimpleName();
    private String c;
    private String d;

    public static synchronized hw a() {
        hw hwVar;
        synchronized (hw.class) {
            if (a == null) {
                a = new hw();
            }
            hwVar = a;
        }
        return hwVar;
    }

    public static void b() {
        if (a != null) {
            jq.a().b("VersionName", a);
        }
        a = null;
    }

    private hw() {
        jr a = jq.a();
        this.c = (String) a.a("VersionName");
        a.a("VersionName", (a) this);
        in.a(4, b, "initSettings, VersionName = " + this.c);
    }

    public String c() {
        return VERSION.RELEASE;
    }

    public String d() {
        return Build.DEVICE;
    }

    public synchronized String e() {
        String str;
        if (!TextUtils.isEmpty(this.c)) {
            str = this.c;
        } else if (TextUtils.isEmpty(this.d)) {
            this.d = f();
            str = this.d;
        } else {
            str = this.d;
        }
        return str;
    }

    private String f() {
        try {
            Context c = hz.a().c();
            PackageInfo packageInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
            if (packageInfo.versionName != null) {
                return packageInfo.versionName;
            }
            if (packageInfo.versionCode != 0) {
                return Integer.toString(packageInfo.versionCode);
            }
            return "Unknown";
        } catch (Throwable th) {
            in.a(6, b, "", th);
        }
    }

    public void a(String str, Object obj) {
        if (str.equals("VersionName")) {
            this.c = (String) obj;
            in.a(4, b, "onSettingUpdate, VersionName = " + this.c);
            return;
        }
        in.a(6, b, "onSettingUpdate internal error!");
    }
}
