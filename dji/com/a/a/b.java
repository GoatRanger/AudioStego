package com.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.a.a.a.b.d;
import dji.pilot.usercenter.mode.n;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class b extends p {
    private static String a;

    public static class a extends p {
        public /* synthetic */ p b(String str, Object obj) {
            return a(str, obj);
        }

        private a() {
        }

        public a a(String str, Object obj) {
            super.b(str, obj);
            return this;
        }

        void a(String str, boolean z) {
            c("advertisingId", str);
            c("adTrackingEnabled", Boolean.valueOf(z));
        }
    }

    public /* synthetic */ p b(String str, Object obj) {
        return a(str, obj);
    }

    static synchronized b a(Context context, o oVar) {
        b bVar;
        synchronized (b.class) {
            bVar = new b(new d());
            bVar.b(context);
            bVar.c(context);
            bVar.c(dji.pilot2.publics.b.a.k, Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry());
            bVar.d(context);
            bVar.f();
            bVar.e(context);
            a(bVar, "userAgent", System.getProperty("http.agent"));
            a(bVar, "timezone", TimeZone.getDefault().getID());
            bVar.a(oVar);
        }
        return bVar;
    }

    static void a(Map<String, Object> map, String str, CharSequence charSequence) {
        if (com.a.a.a.b.a(charSequence)) {
            map.put(str, dji.pilot2.mine.e.a.a);
        } else {
            map.put(str, charSequence);
        }
    }

    b(Map<String, Object> map) {
        super(map);
    }

    void a(Context context) {
        if (com.a.a.a.b.a("com.google.android.gms.analytics.GoogleAnalytics")) {
            new f(this).execute(new Context[]{context});
        }
    }

    public b a(String str, Object obj) {
        super.b(str, obj);
        return this;
    }

    public b a() {
        return new b(Collections.unmodifiableMap(new LinkedHashMap(this)));
    }

    public void a(o oVar) {
        c("traits", oVar.b());
    }

    public o b() {
        return (o) a("traits", o.class);
    }

    void b(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            Map a = com.a.a.a.b.a();
            a(a, "name", packageInfo.applicationInfo.loadLabel(packageManager));
            a(a, "version", packageInfo.versionName);
            a(a, "namespace", packageInfo.packageName);
            a.put("build", Integer.valueOf(packageInfo.versionCode));
            c("app", a);
        } catch (NameNotFoundException e) {
        }
    }

    void c(Context context) {
        a aVar = new a();
        aVar.c("manufacturer", Build.MANUFACTURER);
        aVar.c(n.E, Build.MODEL);
        aVar.c("name", Build.DEVICE);
        c("device", aVar);
    }

    public a c() {
        return (a) a("device", a.class);
    }

    void a(String str) {
        a = str;
        e();
    }

    String d() {
        return a;
    }

    void e() {
        Map a = com.a.a.a.b.a();
        a.put("name", "analytics-android");
        a.put("segment-version", "3.1.7.21");
        a.put("hac-version", d());
        c("library", a);
    }

    void d(Context context) {
        boolean z = true;
        Map a = com.a.a.a.b.a();
        if (com.a.a.a.b.a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            ConnectivityManager connectivityManager = (ConnectivityManager) com.a.a.a.b.b(context, "connectivity");
            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                String str = "wifi";
                boolean z2 = networkInfo != null && networkInfo.isConnected();
                a.put(str, Boolean.valueOf(z2));
                networkInfo = connectivityManager.getNetworkInfo(7);
                str = "bluetooth";
                if (networkInfo == null || !networkInfo.isConnected()) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                a.put(str, Boolean.valueOf(z2));
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                String str2 = "cellular";
                if (networkInfo2 == null || !networkInfo2.isConnected()) {
                    z = false;
                }
                a.put(str2, Boolean.valueOf(z));
            }
        }
        TelephonyManager telephonyManager = (TelephonyManager) com.a.a.a.b.b(context, "phone");
        if (telephonyManager != null) {
            a.put("carrier", telephonyManager.getNetworkOperatorName());
        } else {
            a.put("carrier", "unknown");
        }
        c("network", a);
    }

    void f() {
        Map a = com.a.a.a.b.a();
        a.put("name", "Android");
        a.put("version", VERSION.RELEASE);
        c("os", a);
    }

    void e(Context context) {
        Map a = com.a.a.a.b.a();
        Display defaultDisplay = ((WindowManager) com.a.a.a.b.b(context, "window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        a.put("density", Float.valueOf(displayMetrics.density));
        a.put("height", Integer.valueOf(displayMetrics.heightPixels));
        a.put("width", Integer.valueOf(displayMetrics.widthPixels));
        c("screen", a);
    }
}
