package com.here.odnp.wifi;

import com.here.posclient.WifiMeasurement;
import java.util.List;

public class WifiFilterRx extends WifiFilterBase {
    private static final String TAG = "odnp.wifi.WifiFilterRx";

    static class CacheItemRx extends CacheItem {
        CacheItemRx(WifiMeasurement wifiMeasurement, boolean z) {
            super(wifiMeasurement, z);
        }

        public boolean onUpdate(WifiMeasurement wifiMeasurement) {
            return wifiMeasurement.rxLevel != this.mWifiMeasurement.rxLevel;
        }
    }

    public /* bridge */ /* synthetic */ WifiMeasurement[] getFilteredMeasurements() {
        return super.getFilteredMeasurements();
    }

    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    public /* bridge */ /* synthetic */ void setInitialMeasurements(List list) {
        super.setInitialMeasurements(list);
    }

    public /* bridge */ /* synthetic */ void updateMeasurements(List list) {
        super.updateMeasurements(list);
    }

    protected CacheItem createCacheItem(WifiMeasurement wifiMeasurement, boolean z) {
        return new CacheItemRx(wifiMeasurement, z);
    }
}
