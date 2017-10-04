package com.here.android.mpa.guidance;

import com.here.android.mpa.mapping.SafetySpotInfo;
import com.nokia.maps.SafetySpotNotificationInfoImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class SafetySpotNotificationInfo {
    private final SafetySpotNotificationInfoImpl a;

    public SafetySpotInfo getSafetySpot() {
        return this.a.a();
    }

    public long getDistance() {
        return this.a.getDistanceInMetres();
    }

    private SafetySpotNotificationInfo(SafetySpotNotificationInfoImpl safetySpotNotificationInfoImpl) {
        this.a = safetySpotNotificationInfoImpl;
    }

    public String toString() {
        return this.a.toString();
    }

    static {
        SafetySpotNotificationInfoImpl.a(new am<SafetySpotNotificationInfo, SafetySpotNotificationInfoImpl>() {
            public SafetySpotNotificationInfo a(SafetySpotNotificationInfoImpl safetySpotNotificationInfoImpl) {
                return new SafetySpotNotificationInfo(safetySpotNotificationInfoImpl);
            }
        });
    }
}
