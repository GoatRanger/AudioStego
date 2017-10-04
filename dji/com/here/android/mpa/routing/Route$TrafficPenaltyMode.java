package com.here.android.mpa.routing;

import com.nokia.maps.annotation.Online;

@Online
public enum Route$TrafficPenaltyMode {
    DISABLED(0),
    OPTIMAL(1),
    AVOID_CONGESTION(2),
    AVOID_LONG_TERM_CLOSURES(3);
    
    int a;

    private Route$TrafficPenaltyMode(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
