package dji.midware.util;

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
        CONNECT_LOSE
    }

    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        if ((networkInfo == null || !networkInfo.isConnected()) && (networkInfo2 == null || !networkInfo2.isConnected())) {
            Log.i("onReceive", "unconnect");
            c.a().e(a.CONNECT_LOSE);
            return;
        }
        Log.i("onReceive", "connect");
        c.a().e(a.CONNECT_OK);
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
}
