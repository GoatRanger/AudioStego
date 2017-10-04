package com.here.odnp.wifi;

import com.here.odnp.util.TimeManager;
import com.here.posclient.WifiMeasurement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

abstract class WifiFilterBase implements IWifiFilter {
    private static final String TAG = "odnp.wifi.WifiFilterBase";
    private final Map<String, CacheItem> mCache = new HashMap();
    private WifiMeasurement[] mFilteredMeasurements = new WifiMeasurement[0];

    static abstract class CacheItem {
        public final WifiMeasurement mWifiMeasurement;

        protected abstract boolean onUpdate(WifiMeasurement wifiMeasurement);

        protected CacheItem(WifiMeasurement wifiMeasurement, boolean z) {
            this.mWifiMeasurement = wifiMeasurement;
            if (z) {
                this.mWifiMeasurement.timeStamp = 0;
                this.mWifiMeasurement.elapsedRealtimeTimeStamp = 0;
                return;
            }
            updateRealtimeAge();
            updateTimestamp();
        }

        private void updateRealtimeAge() {
            this.mWifiMeasurement.elapsedRealtimeTimeStamp = TimeManager.timeSinceBoot();
        }

        private void updateTimestamp() {
            long max = Math.max(0, TimeManager.timeSinceBoot() - this.mWifiMeasurement.elapsedRealtimeTimeStamp);
            this.mWifiMeasurement.timeStamp = TimeUnit.MILLISECONDS.toSeconds(Math.max(0, TimeManager.currentTimeMillis() - max));
        }

        public boolean update(WifiMeasurement wifiMeasurement) {
            boolean onUpdate = onUpdate(wifiMeasurement);
            if (onUpdate) {
                updateRealtimeAge();
            }
            updateTimestamp();
            this.mWifiMeasurement.rxLevel = wifiMeasurement.rxLevel;
            return onUpdate;
        }
    }

    protected abstract CacheItem createCacheItem(WifiMeasurement wifiMeasurement, boolean z);

    protected WifiFilterBase() {
    }

    public void setInitialMeasurements(List<WifiMeasurement> list) {
        reset();
        if (list != null) {
            filterDuplicates(list);
            for (WifiMeasurement wifiMeasurement : list) {
                this.mCache.put(wifiMeasurement.bssid, createCacheItem(wifiMeasurement, true));
            }
            updateFilteredMeasurements();
        }
    }

    public synchronized void updateMeasurements(List<WifiMeasurement> list) {
        if (list != null) {
            int i;
            int i2;
            filterDuplicates(list);
            Set<String> hashSet = new HashSet(this.mCache.keySet());
            int i3 = 0;
            int i4 = 0;
            for (WifiMeasurement wifiMeasurement : list) {
                CacheItem cacheItem = (CacheItem) this.mCache.get(wifiMeasurement.bssid);
                hashSet.remove(wifiMeasurement.bssid);
                if (cacheItem == null) {
                    this.mCache.put(wifiMeasurement.bssid, createCacheItem(wifiMeasurement, false));
                    i = i4 + 1;
                    i2 = i3;
                } else if (cacheItem.update(wifiMeasurement)) {
                    i2 = i3 + 1;
                    i = i4;
                } else {
                    i2 = i3;
                    i = i4;
                }
                i3 = i2;
                i4 = i;
            }
            if (hashSet.isEmpty()) {
                i2 = 0;
            } else {
                i = hashSet.size();
                for (String remove : hashSet) {
                    this.mCache.remove(remove);
                }
                i2 = i;
            }
            if (i4 > 0 || r0 > 0) {
                updateFilteredMeasurements();
            }
        }
    }

    public synchronized WifiMeasurement[] getFilteredMeasurements() {
        return this.mFilteredMeasurements;
    }

    public synchronized void reset() {
        this.mCache.clear();
        this.mFilteredMeasurements = new WifiMeasurement[0];
    }

    public static void filterDuplicates(List<WifiMeasurement> list) {
        if (list != null) {
            int size = list.size();
            Map hashMap = new HashMap(list.size());
            for (WifiMeasurement wifiMeasurement : list) {
                if (wifiMeasurement.bssid != null) {
                    hashMap.put(wifiMeasurement.bssid, wifiMeasurement);
                }
            }
            if (size != hashMap.size()) {
                list.clear();
                list.addAll(hashMap.values());
            }
        }
    }

    private void updateFilteredMeasurements() {
        this.mFilteredMeasurements = new WifiMeasurement[this.mCache.size()];
        int i = 0;
        for (CacheItem cacheItem : this.mCache.values()) {
            int i2 = i + 1;
            this.mFilteredMeasurements[i] = cacheItem.mWifiMeasurement;
            i = i2;
        }
    }
}
