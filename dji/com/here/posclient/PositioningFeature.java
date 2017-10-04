package com.here.posclient;

import java.util.HashSet;
import java.util.Set;

public enum PositioningFeature {
    None(0),
    Offline(1),
    Online(2),
    Cache(4),
    HighAccuracy(8),
    Learning(16),
    HighAccuracyCustom(32),
    Collector(256),
    ActiveStorage(512),
    RadioMapDownload(1024),
    RadioMapDownloadApi(2048),
    All(UpdateOptions.SOURCE_ANY);
    
    public final long value;

    public static Set<PositioningFeature> fromMask(long j) {
        Set<PositioningFeature> hashSet = new HashSet();
        for (PositioningFeature positioningFeature : values()) {
            if ((((long) ((int) positioningFeature.value)) & j) != 0) {
                hashSet.add(positioningFeature);
            }
        }
        return hashSet;
    }

    public static boolean isFeatureSet(long j, PositioningFeature positioningFeature) {
        return (positioningFeature.value & j) == positioningFeature.value;
    }

    private PositioningFeature(long j) {
        this.value = j;
    }
}
