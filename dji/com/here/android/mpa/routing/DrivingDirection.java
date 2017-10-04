package com.here.android.mpa.routing;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum DrivingDirection {
    DIR_BOTH(0),
    DIR_FORWARD(1),
    DIR_BACKWARD(2);
    
    private int a;

    private DrivingDirection(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
