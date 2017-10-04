package com.here.android.mpa.guidance;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum TrafficNotificationInfo$Type {
    UNDEFINED(0),
    ON_ROUTE(1),
    ON_HIGHWAY(2),
    NEAR_START(3),
    NEAR_STOPOVER(4),
    NEAR_DESTINATION(5);
    
    private int a;

    private TrafficNotificationInfo$Type(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
