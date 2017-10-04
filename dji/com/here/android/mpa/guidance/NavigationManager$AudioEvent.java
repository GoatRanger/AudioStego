package com.here.android.mpa.guidance;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum NavigationManager$AudioEvent {
    MANEUVER(1),
    ROUTE(2),
    GPS(4),
    SPEED_LIMIT(8),
    SAFETY_SPOT(32),
    VIBRATION(64);
    
    private final int a;

    private NavigationManager$AudioEvent(int i) {
        this.a = i;
    }

    private int a() {
        return this.a;
    }
}
