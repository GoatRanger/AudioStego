package com.nokia.maps;

import com.here.android.mpa.guidance.SafetySpotNotificationInfo;
import com.here.android.mpa.mapping.SafetySpotInfo;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import dji.pilot.fpv.d.c.g;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public class SafetySpotNotificationInfoImpl extends BaseNativeObject {
    private static am<SafetySpotNotificationInfo, SafetySpotNotificationInfoImpl> a;

    private native void destroyNative();

    private native SafetySpotInfoImpl getSafetySpotNative();

    public native long getDistanceInMetres();

    static {
        ce.a(SafetySpotNotificationInfo.class);
    }

    public static void a(am<SafetySpotNotificationInfo, SafetySpotNotificationInfoImpl> amVar) {
        a = amVar;
    }

    public static SafetySpotNotificationInfo a(SafetySpotNotificationInfoImpl safetySpotNotificationInfoImpl) {
        if (safetySpotNotificationInfoImpl != null) {
            return (SafetySpotNotificationInfo) a.a(safetySpotNotificationInfoImpl);
        }
        return null;
    }

    public static List<SafetySpotNotificationInfo> a(List<SafetySpotNotificationInfoImpl> list) {
        List<SafetySpotNotificationInfo> arrayList = new ArrayList();
        for (SafetySpotNotificationInfoImpl a : list) {
            SafetySpotNotificationInfo a2 = a(a);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    @HybridPlusNative
    private SafetySpotNotificationInfoImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyNative();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ce.a(stringBuilder, "SafetySpot", a().toString(), false);
        ce.a(stringBuilder, g.w, Long.toString(getDistanceInMetres()), true);
        return stringBuilder.toString();
    }

    public SafetySpotInfo a() {
        return SafetySpotInfoImpl.a(getSafetySpotNative());
    }
}
