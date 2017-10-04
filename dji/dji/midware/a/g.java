package dji.midware.a;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiManager;
import java.util.ArrayList;
import java.util.List;

public class g {
    public static Context a() {
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean a(String str) {
        List installedPackages = a().getPackageManager().getInstalledPackages(0);
        List arrayList = new ArrayList();
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                arrayList.add(((PackageInfo) installedPackages.get(i)).packageName);
            }
        }
        return arrayList.contains(str);
    }

    public static String b() {
        WifiManager wifiManager = (WifiManager) a().getSystemService("wifi");
        if (wifiManager.isWifiEnabled()) {
            return a(wifiManager.getConnectionInfo().getIpAddress());
        }
        return null;
    }

    private static String a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }
}
