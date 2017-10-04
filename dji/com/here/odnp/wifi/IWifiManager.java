package com.here.odnp.wifi;

import com.here.posclient.WifiMeasurement;
import com.here.posclient.WifiStatus;
import java.util.List;

public interface IWifiManager {

    public interface IWifiListener {
        void onScanResultsAvailable(WifiScanResultContainer wifiScanResultContainer);

        void onWifiScanFailed();

        void onWifiStateChanged(WifiStatus wifiStatus);
    }

    public static class WifiScanResultContainer {
        public long measurementId;
        public List<WifiMeasurement> scanResultList;
        public boolean simulated;

        public WifiScanResultContainer(List<WifiMeasurement> list) {
            this.scanResultList = list;
        }

        public WifiScanResultContainer(long j, boolean z, List<WifiMeasurement> list) {
            this.measurementId = j;
            this.simulated = z;
            this.scanResultList = list;
        }
    }

    void cancelWifiScan();

    void close();

    List<WifiMeasurement> getLastScanResult();

    boolean isWifiSupported();

    void open(IWifiListener iWifiListener);

    boolean startWifiScan();
}
