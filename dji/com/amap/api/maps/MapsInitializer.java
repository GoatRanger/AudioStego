package com.amap.api.maps;

import android.content.Context;
import android.os.RemoteException;
import com.amap.api.mapcore.util.ab;
import com.amap.api.mapcore.util.dm;

public final class MapsInitializer {
    public static String KEY = null;
    private static boolean a = true;
    public static String sdcardDir = "";

    public static void initialize(Context context) throws RemoteException {
        ab.a = context.getApplicationContext();
    }

    public static void setNetWorkEnable(boolean z) {
        a = z;
    }

    public static boolean getNetWorkEnable() {
        return a;
    }

    public static void setApiKey(String str) {
        if (str != null && str.trim().length() > 0) {
            KEY = str;
            dm.a(str);
        }
    }

    public static String getVersion() {
        return "3.3.2";
    }
}
