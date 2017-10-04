package com.e;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.here.odnp.config.OdnpConfigStatic;
import java.util.List;
import org.json.JSONObject;

public class ba {
    long a = 0;
    private WifiManager b;
    private JSONObject c;
    private Context d;

    public ba(Context context, WifiManager wifiManager, JSONObject jSONObject) {
        this.b = wifiManager;
        this.c = jSONObject;
        this.d = context;
    }

    private boolean a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getBSSID()) || wifiInfo.getSSID() == null || wifiInfo.getBSSID().equals("00:00:00:00:00:00") || wifiInfo.getBSSID().contains(" :") || TextUtils.isEmpty(wifiInfo.getSSID())) ? false : true;
    }

    public List<ScanResult> a() {
        List<ScanResult> list = null;
        try {
            if (this.b != null) {
                list = this.b.getScanResults();
            }
        } catch (Throwable th) {
            bc.a(th, "WifiManagerWrapper", "getScanResults");
        }
        return list;
    }

    public void a(JSONObject jSONObject) {
        this.c = jSONObject;
    }

    public void a(boolean z) {
        Context context = this.d;
        if (this.b != null && context != null && z && br.c() > 17) {
            String str = "autoenablewifialwaysscan";
            if (br.a(this.c, str)) {
                try {
                    if ("0".equals(this.c.getString(str))) {
                        return;
                    }
                } catch (Throwable th) {
                    bc.a(th, "WifiManagerWrapper", "enableWifiAlwaysScan1");
                }
            }
            ContentResolver contentResolver = context.getContentResolver();
            String str2 = "android.provider.Settings$Global";
            try {
                if (((Integer) bp.a(str2, "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                    bp.a(str2, "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", Integer.valueOf(1)}, new Class[]{ContentResolver.class, String.class, Integer.TYPE});
                }
            } catch (Throwable th2) {
                bc.a(th2, "WifiManagerWrapper", "enableWifiAlwaysScan");
            }
        }
    }

    public boolean a(ConnectivityManager connectivityManager) {
        boolean z = true;
        WifiManager wifiManager = this.b;
        if (wifiManager == null || !f()) {
            return false;
        }
        try {
            if (!(bk.a(connectivityManager.getActiveNetworkInfo()) == 1 && a(wifiManager.getConnectionInfo()))) {
                z = false;
            }
            return z;
        } catch (Throwable th) {
            bc.a(th, "WifiManagerWrapper", "wifiAccess");
            return false;
        }
    }

    public WifiInfo b() {
        return this.b != null ? this.b.getConnectionInfo() : null;
    }

    public int c() {
        return this.b != null ? this.b.getWifiState() : 4;
    }

    public boolean d() {
        if (br.b() - this.a < OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME || this.b == null) {
            return false;
        }
        this.a = br.b();
        return this.b.startScan();
    }

    public boolean e() {
        try {
            return String.valueOf(bp.a(this.b, "startScanActive", new Object[0])).equals("true");
        } catch (Throwable th) {
            bc.a(th, "WifiManagerWrapper", "startScanActive");
            return false;
        }
    }

    public boolean f() {
        boolean z = false;
        WifiManager wifiManager = this.b;
        if (wifiManager != null) {
            try {
                z = wifiManager.isWifiEnabled();
            } catch (Throwable th) {
                bc.a(th, "WifiManagerWrapper", "wifiEnabled1");
            }
            if (!z && br.c() > 17) {
                try {
                    z = String.valueOf(bp.a(wifiManager, "isScanAlwaysAvailable", new Object[0])).equals("true");
                } catch (Throwable th2) {
                    bc.a(th2, "WifiManagerWrapper", "wifiEnabled");
                }
            }
        }
        return z;
    }
}
