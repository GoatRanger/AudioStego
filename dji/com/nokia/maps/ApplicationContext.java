package com.nokia.maps;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.here.android.mpa.common.OnEngineInitListener.Error;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.HashMap;

@Online
public class ApplicationContext extends BaseNativeObject {
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static ApplicationContext f;
    private static Context g = null;
    private static String j = null;
    private static String k = null;
    private HashMap<String, String> a = new HashMap();
    private String e;
    private int h = 2;
    private Error i = Error.OPERATION_NOT_ALLOWED;

    private static native void initNative(Context context);

    private native boolean setPermissionString(String str, int i);

    public native String a();

    public native void check(int i, c cVar);

    public static ApplicationContext a(Context context) {
        if (f == null) {
            ApplicationContext applicationContext = new ApplicationContext();
            g = context.getApplicationContext();
            applicationContext.h();
            f = applicationContext;
        }
        return f;
    }

    public static ApplicationContext b() {
        if (f != null) {
            return f;
        }
        throw new NullPointerException("ApplicationContext has not been initialized");
    }

    private ApplicationContext() {
    }

    @OnlineNative
    public String getAppId() {
        return b;
    }

    @OnlineNative
    public String getAppToken() {
        return c;
    }

    static String c() {
        return k;
    }

    String d() {
        return dz.c(g);
    }

    public String e() {
        if (b == null || b.length() == 0 || c == null || c.length() == 0) {
            return null;
        }
        return String.format("?app_id=%s&app_code=%s", new Object[]{b, c});
    }

    public String f() {
        return this.e;
    }

    private void h() {
        Bundle c = c(g);
        this.e = g.getApplicationInfo().packageName;
        if (c == null) {
            return;
        }
        if (!a(c)) {
            this.i = aq.a(Error.MISSING_APP_CREDENTIAL, "Missing app id or app token in the AndroidManifest.xml file. Please refer to the user guide for details about proper project setup.");
        } else if (b(c)) {
            this.i = Error.NONE;
        } else {
            this.i = aq.a(Error.OPERATION_NOT_ALLOWED, "Missing license key in the AndroidManifest.xml file. Please refer to the user guide for details about proper project setup.");
        }
    }

    Error g() {
        return this.i;
    }

    private boolean a(Bundle bundle) {
        if (b == null || c == null || b.length() == 0 || c.length() == 0) {
            b = bundle.getString("com.here.android.maps.appid");
            c = bundle.getString("com.here.android.maps.apptoken");
            if (b == null || c == null) {
                b = "";
                c = "";
                return false;
            }
        }
        return true;
    }

    private Bundle c(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            return null;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean b(Bundle bundle) {
        String str;
        if (j != null) {
            str = j;
            j = null;
        } else {
            str = bundle.getString("com.here.android.maps.license.key");
        }
        if (str != null) {
            bj.e(getClass().getName(), "Found license key: " + str + " length = %d", Integer.valueOf(str.length()));
            d = str;
        }
        return setPermissionString(str, this.h);
    }

    @OnlineNative
    private Context getAppContext() {
        return g;
    }

    public static void b(Context context) {
        dy.a(context, "Cannot initialize with a null context");
        initNative(context.getApplicationContext());
    }
}
