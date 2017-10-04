package dji.pilot.publics.objects;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import dji.thirdparty.a.c;

public class DJINetWorkReceiver extends BroadcastReceiver {

    public enum a {
        CONNECT_OK,
        CONNECT_OK_WIFI,
        CONNECT_LOSE,
        CONNECT_LOSE_WIFI
    }

    public void onReceive(Context context, Intent intent) {
        int i;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        int i2 = (networkInfo2 == null || !networkInfo2.isConnected()) ? 0 : 1;
        if (networkInfo == null || !networkInfo.isConnected()) {
            i = 0;
        } else {
            i = 1;
        }
        if (i == 0 && i2 == 0) {
            Log.i("onReceive", "unconnect");
            c.a().e(a.CONNECT_LOSE);
        } else {
            Log.i("onReceive", "connect");
            c.a().e(a.CONNECT_OK);
        }
        if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
            i = intent.getIntExtra("wifi_state", 1);
            if (i == 1) {
                c.a().e(a.CONNECT_LOSE_WIFI);
            } else if (i == 3) {
                c.a().e(a.CONNECT_OK_WIFI);
            }
        }
    }

    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        if ((networkInfo == null || !networkInfo.isConnected()) && (networkInfo2 == null || !networkInfo2.isConnected())) {
            return false;
        }
        return true;
    }

    public static boolean b(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        if (networkInfo == null || !networkInfo.isConnected()) {
            Log.i("getWifiStatus", "unconnect");
            return false;
        }
        Log.i("getWifiStatus", "connect");
        return true;
    }

    public static boolean c(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        Class cls = connectivityManager.getClass();
        Boolean.valueOf(false);
        try {
            if (((Boolean) cls.getMethod("getMobileDataEnabled", null).invoke(connectivityManager, null)).booleanValue()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
