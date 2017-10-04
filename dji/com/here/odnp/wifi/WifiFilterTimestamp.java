package com.here.odnp.wifi;

import android.annotation.TargetApi;
import android.os.SystemClock;
import com.here.posclient.WifiMeasurement;
import java.util.List;
import java.util.concurrent.TimeUnit;

@TargetApi(17)
public class WifiFilterTimestamp extends WifiFilterBase {
    private static final String TAG = "odnp.wifi.WifiFilterTimestamp";

    static class CacheItemTimestamp extends CacheItem {
        CacheItemTimestamp(WifiMeasurement wifiMeasurement, boolean z) {
            super(wifiMeasurement, z);
        }

        public boolean onUpdate(WifiMeasurement wifiMeasurement) {
            if (wifiMeasurement.tsf == this.mWifiMeasurement.tsf) {
                return false;
            }
            this.mWifiMeasurement.tsf = wifiMeasurement.tsf;
            return true;
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
        return new CacheItemTimestamp(wifiMeasurement, z);
    }

    public static boolean updateTimestamps(List<WifiMeasurement> list) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        for (WifiMeasurement wifiMeasurement : list) {
            long toMillis = TimeUnit.MICROSECONDS.toMillis(wifiMeasurement.tsf);
            if (toMillis < 0 || toMillis > elapsedRealtime) {
                return false;
            }
            wifiMeasurement.elapsedRealtimeTimeStamp = toMillis;
            wifiMeasurement.timeStamp = (toMillis + (System.currentTimeMillis() - elapsedRealtime)) / 1000;
        }
        return true;
    }
}
