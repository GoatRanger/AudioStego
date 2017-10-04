package dji.pilot2.utils;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Build.VERSION;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class r {
    WifiLock a;
    Context b;
    private WifiManager c;
    private WifiInfo d = this.c.getConnectionInfo();
    private List<ScanResult> e;
    private List<WifiConfiguration> f;

    public r(Context context) {
        this.b = context;
        this.c = (WifiManager) context.getSystemService("wifi");
    }

    public void a() {
        if (!this.c.isWifiEnabled()) {
            this.c.setWifiEnabled(true);
        }
    }

    public boolean b() {
        if (this.c != null) {
            return this.c.isWifiEnabled();
        }
        return false;
    }

    public void c() {
        if (this.c.isWifiEnabled()) {
            this.c.setWifiEnabled(false);
        }
    }

    public int d() {
        return this.c.getWifiState();
    }

    public void e() {
        this.a.acquire();
    }

    public void f() {
        if (this.a.isHeld()) {
            this.a.acquire();
        }
    }

    public void g() {
        this.a = this.c.createWifiLock("Test");
    }

    public List<WifiConfiguration> h() {
        return this.f;
    }

    public void a(int i) {
        if (i <= this.f.size()) {
            this.c.enableNetwork(((WifiConfiguration) this.f.get(i)).networkId, true);
        }
    }

    public void a(WifiConfiguration wifiConfiguration) {
        this.c.enableNetwork(wifiConfiguration.networkId, true);
    }

    public void b(int i) {
        this.c.enableNetwork(i, true);
    }

    public void i() {
        this.c.startScan();
        this.e = this.c.getScanResults();
        this.f = this.c.getConfiguredNetworks();
    }

    public HashMap<String, WifiConfiguration> j() {
        this.e = this.c.getScanResults();
        this.f = this.c.getConfiguredNetworks();
        HashMap<String, WifiConfiguration> hashMap = new HashMap();
        HashSet hashSet = new HashSet(128);
        if (this.e == null || this.f == null) {
            return hashMap;
        }
        for (ScanResult scanResult : this.e) {
            hashSet.add(scanResult.SSID);
        }
        for (WifiConfiguration wifiConfiguration : this.f) {
            Object obj = wifiConfiguration.SSID;
            if (VERSION.SDK_INT >= 17 && obj.startsWith("\"") && obj.endsWith("\"")) {
                obj = obj.substring(1, obj.length() - 1);
            }
            if (hashSet.contains(obj)) {
                hashMap.put(obj, wifiConfiguration);
            }
        }
        return hashMap;
    }

    public List<ScanResult> k() {
        return this.e;
    }

    public StringBuilder l() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.e.size(); i++) {
            stringBuilder.append("Index_" + new Integer(i + 1).toString() + ":");
            stringBuilder.append(((ScanResult) this.e.get(i)).toString());
            stringBuilder.append("/n");
        }
        return stringBuilder;
    }

    public String m() {
        return this.d == null ? "NULL" : this.d.getMacAddress();
    }

    public String n() {
        return this.d == null ? "NULL" : this.d.getSSID();
    }

    public String o() {
        return this.d == null ? "NULL" : this.d.getBSSID();
    }

    public int p() {
        return this.d == null ? 0 : this.d.getIpAddress();
    }

    public int q() {
        return this.d == null ? 0 : this.d.getNetworkId();
    }

    public String r() {
        return this.d == null ? "NULL" : this.d.toString();
    }

    public boolean b(WifiConfiguration wifiConfiguration) {
        int addNetwork = this.c.addNetwork(wifiConfiguration);
        if (addNetwork != -1 && this.c.enableNetwork(addNetwork, true)) {
            return true;
        }
        return false;
    }

    public void c(int i) {
        this.c.disableNetwork(i);
        this.c.disconnect();
    }

    public WifiConfiguration a(String str, String str2, int i) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        WifiConfiguration a = a(str);
        if (a != null) {
            this.c.removeNetwork(a.networkId);
        }
        if (i == 1) {
            wifiConfiguration.wepKeys[0] = "";
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        }
        if (i == 2) {
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.wepKeys[0] = "\"" + str2 + "\"";
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedGroupCiphers.set(0);
            wifiConfiguration.allowedGroupCiphers.set(1);
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        }
        if (i == 3) {
            wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.status = 2;
        }
        return wifiConfiguration;
    }

    public WifiConfiguration a(String str) {
        for (WifiConfiguration wifiConfiguration : this.c.getConfiguredNetworks()) {
            if (wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    public boolean b(String str) {
        i();
        for (ScanResult scanResult : this.e) {
            if (scanResult != null && str.equals(scanResult.SSID)) {
                return true;
            }
        }
        return false;
    }
}
