package com.here.android.mpa.guidance;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum NavigationManager$UnitSystem {
    METRIC(0),
    IMPERIAL(1),
    IMPERIAL_US(2);
    
    private int a;

    private NavigationManager$UnitSystem(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
