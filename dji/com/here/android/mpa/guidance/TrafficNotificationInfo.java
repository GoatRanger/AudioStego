package com.here.android.mpa.guidance;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.mapping.TrafficEvent;
import com.here.android.mpa.mapping.TrafficEvent.Severity;
import com.nokia.maps.TrafficNotificationInfoImpl;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class TrafficNotificationInfo {
    private TrafficNotificationInfoImpl a;

    private TrafficNotificationInfo(TrafficNotificationInfoImpl trafficNotificationInfoImpl) {
        this.a = trafficNotificationInfoImpl;
    }

    public Type getType() {
        return this.a.a();
    }

    @Deprecated
    public final TrafficEvent getEvent() {
        return this.a.b();
    }

    public final long getDistanceInMeters() {
        return this.a.getDistanceInMeters();
    }

    public final long getAffectedLength() {
        return this.a.getAffectedLength();
    }

    public final GeoBoundingBox getAffectedArea() {
        return this.a.c();
    }

    public final Severity getSeverity() {
        return this.a.d();
    }

    public final long getTravelTime() {
        return this.a.getTravelTime();
    }

    public final long getTravelTimeWithTraffic() {
        return this.a.getTravelTimeWithTraffic();
    }

    static {
        TrafficNotificationInfoImpl.a(new 1(), new 2());
    }

    public String toString() {
        return this.a.toString();
    }
}
