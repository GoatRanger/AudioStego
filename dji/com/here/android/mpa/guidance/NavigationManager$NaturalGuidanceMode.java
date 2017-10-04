package com.here.android.mpa.guidance;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum NavigationManager$NaturalGuidanceMode {
    TRAFFIC_LIGHT(1),
    STOP_SIGN(2),
    JUNCTION(4);
    
    private int a;

    private NavigationManager$NaturalGuidanceMode(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
