package dji.midware.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.dji.frame.b.c;
import java.net.UnknownHostException;

public class n {
    private static boolean a = false;

    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager != null) {
            a = connectivityManager.getNetworkInfo(1).getState() == State.CONNECTED;
        } else {
            a = false;
        }
        return a;
    }

    public static String b(Context context) {
        String str = "";
        if (!a(context)) {
            return str;
        }
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo == null) {
            return str;
        }
        return connectionInfo.getSSID().replaceAll("\"", "");
    }

    public static void a(final boolean z, final c cVar) {
        new Thread(new Runnable() {
            public void run() {
                cVar.a(Integer.valueOf(n.a("192.168.1.1", z)));
            }
        }).start();
    }

    public static void b(final boolean z, final c cVar) {
        new Thread(new Runnable() {
            public void run() {
                cVar.a(Integer.valueOf(n.a("192.168.1.2", z)));
            }
        }).start();
    }

    public static int a(String str, boolean z) {
        int i;
        int i2;
        int i3 = 300;
        long j = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < 3; i5++) {
            i = -1;
            long currentTimeMillis = System.currentTimeMillis();
            try {
                if (l.a(str, 300)) {
                    i = 0;
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (i != 0) {
                i = 0;
                break;
            }
            j += System.currentTimeMillis() - currentTimeMillis;
            i4++;
        }
        i = i4;
        if (i == 3) {
            i3 = ((int) j) / 3;
            if (i3 < 100) {
                i = 4;
                i2 = 0;
            } else if (i3 < 250) {
                i = 3;
                i2 = i3;
            } else if (i3 < 500) {
                i = 2;
                i2 = i3;
            } else if (i3 < 1000) {
                i = 1;
                i2 = i3;
            }
            if (z) {
                return i;
            }
            i = (1000 - i2) / 10;
            if (i < 0) {
                return i;
            }
            return 0;
        }
        i = 0;
        i2 = i3;
        if (z) {
            return i;
        }
        i = (1000 - i2) / 10;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public static boolean a(String str, int i) {
        boolean z = false;
        try {
            z = l.a(str, i);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z;
    }
}
