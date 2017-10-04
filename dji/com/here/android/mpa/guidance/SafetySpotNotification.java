package com.here.android.mpa.guidance;

import com.nokia.maps.SafetySpotNotificationImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.List;

@HybridPlus
public final class SafetySpotNotification {
    private final SafetySpotNotificationImpl a;

    public List<SafetySpotNotificationInfo> getSafetySpotNotificationInfos() {
        return this.a.a();
    }

    public double getSpeed() {
        return this.a.getSpeedMS();
    }

    private SafetySpotNotification(SafetySpotNotificationImpl safetySpotNotificationImpl) {
        this.a = safetySpotNotificationImpl;
    }

    static {
        SafetySpotNotificationImpl.a(new am<SafetySpotNotification, SafetySpotNotificationImpl>() {
            public SafetySpotNotification a(SafetySpotNotificationImpl safetySpotNotificationImpl) {
                return new SafetySpotNotification(safetySpotNotificationImpl);
            }
        });
    }
}
