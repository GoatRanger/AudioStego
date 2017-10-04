package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.guidance.TrafficNotificationInfo;
import com.here.android.mpa.guidance.TrafficNotificationInfo$Type;
import com.here.android.mpa.mapping.TrafficEvent;
import com.here.android.mpa.mapping.TrafficEvent.Severity;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public class TrafficNotificationInfoImpl extends BaseNativeObject {
    private static final String a = TrafficNotificationInfoImpl.class.getName();
    private static k<TrafficNotificationInfo, TrafficNotificationInfoImpl> b = null;
    private static am<TrafficNotificationInfo, TrafficNotificationInfoImpl> c;
    private Object d = new Object();
    private volatile TrafficEvent e;

    private final native void destroyTrafficNotificationInfoNative();

    private native GeoBoundingBoxImpl getAffectedAreaNative();

    private final native TrafficEventImpl getEvent();

    private final native int getSeverityNative();

    private final native int getTypeNative();

    public final native long getAffectedLength();

    public final native long getDistanceInMeters();

    public final native long getTravelTime();

    public final native long getTravelTimeWithTraffic();

    public final native boolean isValid();

    static {
        ce.a(TrafficNotificationInfo.class);
    }

    public static void a(k<TrafficNotificationInfo, TrafficNotificationInfoImpl> kVar, am<TrafficNotificationInfo, TrafficNotificationInfoImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static TrafficNotificationInfo a(TrafficNotificationInfoImpl trafficNotificationInfoImpl) {
        if (trafficNotificationInfoImpl != null) {
            return (TrafficNotificationInfo) c.a(trafficNotificationInfoImpl);
        }
        return null;
    }

    static List<TrafficNotificationInfo> a(List<TrafficNotificationInfoImpl> list) {
        List<TrafficNotificationInfo> arrayList = new ArrayList();
        for (TrafficNotificationInfoImpl a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    @HybridPlusNative
    TrafficNotificationInfoImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyTrafficNotificationInfoNative();
    }

    public TrafficNotificationInfo$Type a() {
        return TrafficNotificationInfo$Type.values()[getTypeNative()];
    }

    public final TrafficEvent b() {
        if (this.e == null) {
            synchronized (this.d) {
                if (this.e == null) {
                    this.e = TrafficEventImpl.a(getEvent());
                }
            }
        }
        return this.e;
    }

    public final GeoBoundingBox c() {
        return GeoBoundingBoxImpl.create(getAffectedAreaNative());
    }

    public final Severity d() {
        return TrafficEventImpl.a(getSeverityNative());
    }

    public String toString() {
        return b().toString() + "\nValid: " + Boolean.toString(isValid()) + "\nType: " + a().toString() + "\nDistance(m): " + getDistanceInMeters() + "\nAffected Length(m):" + getAffectedLength();
    }
}
