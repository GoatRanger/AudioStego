package com.dji.a.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.dji.a.b;

public class c {
    private static volatile a a;
    private static BroadcastReceiver b = new d();

    public enum a {
        WIFI_CONN,
        MOBILE_CONN,
        LOST
    }

    public static void a(Context context) {
        if (context != null) {
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                context.registerReceiver(b, intentFilter);
            } catch (Exception e) {
                com.dji.a.a.c.c(com.dji.a.a.a, "error in register receiver " + e);
            }
        }
    }

    public static void b(Context context) {
        if (context != null) {
            a aVar;
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                aVar = a.LOST;
            } else if (activeNetworkInfo.getType() == 1) {
                aVar = a.WIFI_CONN;
            } else {
                aVar = a.MOBILE_CONN;
            }
            a = aVar;
            if (com.dji.a.a.b) {
                com.dji.a.a.c.a(com.dji.a.a.a, "ProbeNetworkState NetState is " + a);
            }
        }
    }

    public static void a() {
        if (a == a.WIFI_CONN || a == a.MOBILE_CONN) {
            e.a().b();
        }
    }

    public static boolean a(b bVar) {
        if (bVar == null) {
            return false;
        }
        if (a == a.LOST) {
            if (!com.dji.a.a.b) {
                return false;
            }
            com.dji.a.a.c.a(com.dji.a.a.a, "NetState is lost");
            return false;
        } else if (a != a.MOBILE_CONN) {
            return true;
        } else {
            if (com.dji.a.a.b) {
                com.dji.a.a.c.a(com.dji.a.a.a, "NetState is mobile_conn");
            }
            return bVar.a;
        }
    }
}
