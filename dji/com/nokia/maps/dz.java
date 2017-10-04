package com.nokia.maps;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Locale;

public class dz {
    public static void a(Context context) {
        ApplicationContext b = ApplicationContext.b();
        String appId = b.getAppId();
        String appToken = b.getAppToken();
        if (appId.length() > 0 && appToken.length() > 0) {
            ConnectionInfoImpl.setApplicationId(appId);
            ConnectionInfoImpl.setApplicationCode(appToken);
        }
        ConnectionInfoImpl.setPlatformName(a());
        ConnectionInfoImpl.setPlatformVersion(VERSION.RELEASE);
        ConnectionInfoImpl.setDeviceName(b());
        ConnectionInfoImpl.setClientSDKName(MapsEngine.isInternalBuild() ? "Android Internal SDK" : "Android External SDK");
        ConnectionInfoImpl.setClientSDKVersion(Version.a());
        ConnectionInfoImpl.setApplicationVersion(c(context));
        ConnectionInfoImpl.setUserAgent(b.f());
    }

    public static String[] b(Context context) {
        String[] strArr = new String[8];
        strArr[0] = "T9XPjJPi2igV2fyclOXA";
        strArr[1] = "nDrdUX3FSSEJYK1VElm1aw";
        strArr[2] = a();
        strArr[3] = VERSION.RELEASE;
        strArr[4] = b();
        strArr[5] = MapsEngine.isInternalBuild() ? "Android Internal SDK" : "Android External SDK";
        strArr[6] = Version.a();
        strArr[7] = c(context);
        return strArr;
    }

    static String a() {
        return "Android Premium SDK";
    }

    private static String b() {
        return Build.MANUFACTURER + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Build.MODEL + "; " + Locale.getDefault();
    }

    static String c(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str == null) {
                return "";
            }
            return str;
        } catch (NameNotFoundException e) {
            return "";
        }
    }
}
