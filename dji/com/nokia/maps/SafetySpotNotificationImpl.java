package com.nokia.maps;

import com.here.android.mpa.guidance.SafetySpotNotification;
import com.here.android.mpa.guidance.SafetySpotNotificationInfo;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.List;

@HybridPlus
public class SafetySpotNotificationImpl extends BaseNativeObject {
    private static am<SafetySpotNotification, SafetySpotNotificationImpl> a;

    private native void destroyNative();

    private native List<SafetySpotNotificationInfoImpl> getSafetySpotNotificationInfos();

    public native double getSpeedMS();

    static {
        ce.a(SafetySpotNotification.class);
    }

    public static void a(am<SafetySpotNotification, SafetySpotNotificationImpl> amVar) {
        a = amVar;
    }

    public static SafetySpotNotification a(SafetySpotNotificationImpl safetySpotNotificationImpl) {
        if (safetySpotNotificationImpl != null) {
            return (SafetySpotNotification) a.a(safetySpotNotificationImpl);
        }
        return null;
    }

    @HybridPlusNative
    SafetySpotNotificationImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyNative();
    }

    public List<SafetySpotNotificationInfo> a() {
        return SafetySpotNotificationInfoImpl.a(getSafetySpotNotificationInfos());
    }
}
