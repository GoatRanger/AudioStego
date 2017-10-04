package com.alipay.e.a.a.c;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Base64;
import com.alipay.e.a.a.c.b.a;
import dji.pilot.usercenter.mode.n;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class e {
    private Context a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private volatile int l = 0;

    private e() {
    }

    public static e a(Context context) {
        TelephonyManager telephonyManager;
        String str;
        String str2;
        String str3;
        String str4;
        WifiManager wifiManager;
        e eVar = new e();
        eVar.a = context;
        try {
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
            str = "";
            str2 = "";
            str3 = "";
            str4 = "";
            if ((telephonyManager.getPhoneType() == 2 ? 2 : 1) == 2) {
                String networkOperator;
                String valueOf;
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
                if (cdmaCellLocation != null) {
                    str4 = String.valueOf(cdmaCellLocation.getNetworkId());
                    networkOperator = telephonyManager.getNetworkOperator();
                    if (!(networkOperator == null || networkOperator.equals(""))) {
                        str = networkOperator.substring(0, 3);
                    }
                    str2 = String.valueOf(cdmaCellLocation.getSystemId());
                    valueOf = String.valueOf(cdmaCellLocation.getBaseStationId());
                    str3 = str;
                    networkOperator = str4;
                    str4 = str2;
                } else {
                    networkOperator = str4;
                    valueOf = str3;
                    str4 = str2;
                    str3 = str;
                }
                str2 = str4;
                str = str3;
                str4 = networkOperator;
                str3 = valueOf;
            } else {
                try {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                    if (gsmCellLocation != null) {
                        String networkOperator2 = telephonyManager.getNetworkOperator();
                        if (!(networkOperator2 == null || networkOperator2.equals(""))) {
                            str = telephonyManager.getNetworkOperator().substring(0, 3);
                            str2 = telephonyManager.getNetworkOperator().substring(3, 5);
                        }
                        str3 = String.valueOf(gsmCellLocation.getCid());
                        str4 = String.valueOf(gsmCellLocation.getLac());
                    }
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.fillInStackTrace();
        } catch (Throwable th) {
        }
        eVar.g(str);
        eVar.h(str2);
        eVar.i(str3);
        eVar.j(str4);
        try {
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                telephonyManager.listen(new f(eVar, telephonyManager), 256);
            }
        } catch (Throwable th2) {
        }
        try {
            Object obj;
            CdmaCellLocation cdmaCellLocation2;
            LocationManager locationManager = (LocationManager) context.getSystemService(n.C);
            if (locationManager.isProviderEnabled("network")) {
                LocationListener aVar = new a();
                locationManager.requestLocationUpdates("network", com.alipay.e.a.a.c.a.a.b, 0.0f, aVar, Looper.getMainLooper());
                locationManager.removeUpdates(aVar);
                Location lastKnownLocation = locationManager.getLastKnownLocation("network");
                if (lastKnownLocation != null) {
                    eVar.b(lastKnownLocation.getLatitude());
                    eVar.a(lastKnownLocation.getLongitude());
                    obj = 1;
                    telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (obj == null && telephonyManager.getPhoneType() == 2) {
                        cdmaCellLocation2 = (CdmaCellLocation) telephonyManager.getCellLocation();
                        if (cdmaCellLocation2 != null && com.alipay.e.a.a.b.a.a(eVar.g()) && com.alipay.e.a.a.b.a.a(eVar.f())) {
                            eVar.b((((double) cdmaCellLocation2.getBaseStationLatitude()) / 14400.0d));
                            eVar.a((((double) cdmaCellLocation2.getBaseStationLongitude()) / 14400.0d));
                        }
                    }
                    eVar.e(((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected());
                    wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                        eVar.c(connectionInfo.getBSSID());
                        eVar.d(Base64.encodeToString(connectionInfo.getSSID().getBytes(), 8));
                        eVar.f(connectionInfo.getRssi());
                    }
                    return eVar;
                }
            }
            obj = null;
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
            cdmaCellLocation2 = (CdmaCellLocation) telephonyManager.getCellLocation();
            eVar.b((((double) cdmaCellLocation2.getBaseStationLatitude()) / 14400.0d));
            eVar.a((((double) cdmaCellLocation2.getBaseStationLongitude()) / 14400.0d));
        } catch (Exception e22) {
            e22.fillInStackTrace();
        }
        try {
            eVar.e(((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected());
            wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                WifiInfo connectionInfo2 = wifiManager.getConnectionInfo();
                eVar.c(connectionInfo2.getBSSID());
                eVar.d(Base64.encodeToString(connectionInfo2.getSSID().getBytes(), 8));
                eVar.f(connectionInfo2.getRssi());
            }
        } catch (Exception e222) {
            e222.fillInStackTrace();
        }
        return eVar;
    }

    public void a(int i) {
        this.l = i;
    }

    public void a(String str) {
        this.b = str;
    }

    public boolean a() {
        return this.l != 0;
    }

    public double b() {
        return (double) this.l;
    }

    public void b(String str) {
        this.c = str;
    }

    public List<Map<String, String>> c() {
        List<Map<String, String>> arrayList = new ArrayList();
        if (this.a == null) {
            return arrayList;
        }
        WifiManager wifiManager = (WifiManager) this.a.getSystemService("wifi");
        if (wifiManager == null) {
            return arrayList;
        }
        List<ScanResult> scanResults = wifiManager.getScanResults();
        if (scanResults == null) {
            return arrayList;
        }
        for (ScanResult scanResult : scanResults) {
            Map hashMap = new HashMap();
            hashMap.put("wifiMac", scanResult.BSSID == null ? "" : scanResult.BSSID);
            hashMap.put("ssid", scanResult.SSID);
            hashMap.put("rssi", scanResult.level);
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public void c(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.e = str;
    }

    public boolean d() {
        if (this.a == null) {
            return false;
        }
        LocationManager locationManager = (LocationManager) this.a.getSystemService(n.C);
        return locationManager == null ? false : locationManager.isProviderEnabled("gps");
    }

    public void e(String str) {
        this.f = str;
    }

    public boolean e() {
        if (this.a == null) {
            return false;
        }
        WifiManager wifiManager = (WifiManager) this.a.getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return false;
        }
        WifiConfiguration wifiConfiguration;
        Context context = this.a;
        String ssid = connectionInfo.getSSID();
        if (context == null || ssid == null) {
            wifiConfiguration = null;
        } else {
            wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
                if (configuredNetworks != null) {
                    for (WifiConfiguration wifiConfiguration2 : configuredNetworks) {
                        if (wifiConfiguration2.SSID.equals(ssid)) {
                            break;
                        }
                    }
                }
            }
            wifiConfiguration2 = null;
        }
        if (wifiConfiguration2 == null) {
            return false;
        }
        int i = wifiConfiguration2.allowedKeyManagement.get(1) ? 2 : (wifiConfiguration2.allowedKeyManagement.get(2) || wifiConfiguration2.allowedKeyManagement.get(3)) ? 3 : wifiConfiguration2.wepKeys[0] != null ? 1 : 0;
        return i != 0;
    }

    public String f() {
        return this.b;
    }

    public void f(String str) {
        this.g = str;
    }

    public String g() {
        return this.c;
    }

    public void g(String str) {
        this.h = str;
    }

    public String h() {
        return this.d;
    }

    public void h(String str) {
        this.i = str;
    }

    public String i() {
        return this.e;
    }

    public void i(String str) {
        this.j = str;
    }

    public String j() {
        return this.f;
    }

    public void j(String str) {
        this.k = str;
    }

    public String k() {
        return this.g;
    }

    public String l() {
        return this.h;
    }

    public String m() {
        return this.i;
    }

    public String n() {
        return this.j;
    }

    public String o() {
        return this.k;
    }
}
