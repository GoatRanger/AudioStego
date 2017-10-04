package com.nokia.maps;

import com.here.android.mpa.guidance.TrafficNotification;
import com.here.android.mpa.guidance.TrafficNotificationInfo;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.List;

@HybridPlus
public class TrafficNotificationImpl extends BaseNativeObject {
    private static k<TrafficNotification, TrafficNotificationImpl> a = null;
    private static am<TrafficNotification, TrafficNotificationImpl> b;
    private List<TrafficNotificationInfo> c;

    private final native void destroyTrafficNotificationNative();

    private final native List<TrafficNotificationInfoImpl> getInfoNative();

    static {
        ce.a(TrafficNotification.class);
    }

    public static void a(k<TrafficNotification, TrafficNotificationImpl> kVar, am<TrafficNotification, TrafficNotificationImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    static TrafficNotificationImpl a(TrafficNotification trafficNotification) {
        if (a != null) {
            return (TrafficNotificationImpl) a.a(trafficNotification);
        }
        return null;
    }

    static TrafficNotification a(TrafficNotificationImpl trafficNotificationImpl) {
        if (trafficNotificationImpl != null) {
            return (TrafficNotification) b.a(trafficNotificationImpl);
        }
        return null;
    }

    @HybridPlusNative
    TrafficNotificationImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyTrafficNotificationNative();
    }

    public List<TrafficNotificationInfo> a() {
        if (this.c == null) {
            this.c = TrafficNotificationInfoImpl.a(getInfoNative());
        }
        return this.c;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        List a = a();
        for (int i = 0; i < a.size(); i++) {
            stringBuilder.append("TrafficNotificationInfo: ");
            stringBuilder.append(Integer.toString(i));
            stringBuilder.append("\n");
            stringBuilder.append(((TrafficNotificationInfo) a.get(i)).toString());
            if (i < a.size() - 1) {
                stringBuilder.append("\n======================\n");
            }
        }
        return stringBuilder.toString();
    }
}
