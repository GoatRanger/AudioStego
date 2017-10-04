package com.here.services.playback.internal.wifi;

import com.here.odnp.wifi.IWifiManager;
import com.here.odnp.wifi.IWifiManager.IWifiListener;
import com.here.posclient.WifiMeasurement;
import java.util.ArrayList;
import java.util.List;

public class NullWifiManager implements IWifiManager {
    private static final String TAG = "services.playback.internal.wifi.NullWifiManager";

    public void open(IWifiListener iWifiListener) {
    }

    public void close() {
    }

    public List<WifiMeasurement> getLastScanResult() {
        return new ArrayList();
    }

    public boolean startWifiScan() {
        return false;
    }

    public void cancelWifiScan() {
    }

    public boolean isWifiSupported() {
        return true;
    }
}
