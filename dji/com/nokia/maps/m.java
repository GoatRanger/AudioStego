package com.nokia.maps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.DetailedState;
import android.os.Build.VERSION;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

class m {
    static String a() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).format(new Date());
    }

    static String a(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).format(date);
    }

    static String b() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).format(new Date(0));
    }

    static Date a(String str) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).parse(str);
        } catch (ParseException e) {
        }
        return date;
    }

    static long a(Date date, Date date2, TimeUnit timeUnit) {
        return timeUnit.convert(date2.getTime() - date.getTime(), TimeUnit.MILLISECONDS);
    }

    static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().getDetailedState() == DetailedState.CONNECTED;
    }

    static boolean b(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isRoaming();
    }

    static String c() {
        return VERSION.RELEASE;
    }

    static String d() {
        return "Android";
    }

    static String e() {
        return ApplicationContext.b().getAppId();
    }

    static String f() {
        return ApplicationContext.b().getAppToken();
    }

    static String g() {
        return ApplicationContext.b().d();
    }

    static String h() {
        return "Premium";
    }

    static String i() {
        return Version.a();
    }

    static String j() {
        return ConnectionInfoImpl.getDeviceId();
    }

    static boolean k() {
        return MapsEngine.isInternalBuild();
    }
}
