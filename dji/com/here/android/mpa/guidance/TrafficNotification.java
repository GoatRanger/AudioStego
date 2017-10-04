package com.here.android.mpa.guidance;

import com.nokia.maps.TrafficNotificationImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.List;

@HybridPlus
public final class TrafficNotification {
    private TrafficNotificationImpl a;

    private TrafficNotification(TrafficNotificationImpl trafficNotificationImpl) {
        this.a = trafficNotificationImpl;
    }

    public List<TrafficNotificationInfo> getInfoList() {
        return this.a.a();
    }

    static {
        TrafficNotificationImpl.a(new k<TrafficNotification, TrafficNotificationImpl>() {
            public TrafficNotificationImpl a(TrafficNotification trafficNotification) {
                return trafficNotification.a;
            }
        }, new am<TrafficNotification, TrafficNotificationImpl>() {
            public TrafficNotification a(TrafficNotificationImpl trafficNotificationImpl) {
                return trafficNotificationImpl != null ? new TrafficNotification(trafficNotificationImpl) : null;
            }
        });
    }

    public String toString() {
        return this.a.toString();
    }
}
