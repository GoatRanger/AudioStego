package com.here.android.mpa.guidance;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum NavigationManager$TrafficAvoidanceMode {
    DYNAMIC(0),
    MANUAL(1),
    DISABLE(2);
    
    private int a;

    private NavigationManager$TrafficAvoidanceMode(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
