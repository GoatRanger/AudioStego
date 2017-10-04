package com.alipay.e.a.a.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.alipay.e.a.a.b.a;
import com.facebook.internal.af;
import java.io.File;

public class d {
    private static d a = new d();

    private d() {
    }

    private static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
        } catch (Exception e) {
            return str2;
        }
    }

    public static d getInstance() {
        return a;
    }

    public String a() {
        return "android";
    }

    public boolean a(Context context) {
        try {
            if (Build.HARDWARE.contains("goldfish") || Build.PRODUCT.contains(af.o) || Build.FINGERPRINT.contains("generic")) {
                return true;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return (telephonyManager == null || !a.d(telephonyManager.getDeviceId())) ? a.a(Secure.getString(context.getContentResolver(), "android_id")) : true;
        } catch (Exception e) {
            return false;
        }
    }

    public String b(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager.getActiveNetworkInfo().isConnected()) {
                return a.b(connectivityManager.getActiveNetworkInfo().getTypeName(), "WIFI") ? "WIFI" : connectivityManager.getActiveNetworkInfo().getExtraInfo();
            }
        } catch (Exception e) {
        }
        return "";
    }

    public boolean b() {
        String[] strArr = new String[]{"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 5) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    return true;
                }
                i++;
            } catch (Exception e) {
            }
        }
        return false;
    }

    public String c() {
        return Build.BOARD;
    }

    public String d() {
        return Build.BRAND;
    }

    public String e() {
        return Build.DEVICE;
    }

    public String f() {
        return Build.DISPLAY;
    }

    public String g() {
        return VERSION.INCREMENTAL;
    }

    public String h() {
        return Build.MANUFACTURER;
    }

    public String i() {
        return Build.MODEL;
    }

    public String j() {
        return Build.PRODUCT;
    }

    public String k() {
        return VERSION.RELEASE;
    }

    public String l() {
        return VERSION.SDK;
    }

    public String m() {
        return Build.TAGS;
    }

    public String n() {
        return a("ro.kernel.qemu", "0");
    }

    public String o() {
        return a("gsm.sim.state", "");
    }

    public String p() {
        return a("gsm.sim.state.2", "");
    }

    public String q() {
        return a("wifi.interface", "");
    }

    public String r() {
        return a("sys.usb.state", "");
    }
}
