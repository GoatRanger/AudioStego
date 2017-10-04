package com.here.android.mpa.routing;

import com.nokia.maps.annotation.Online;

@Online
public enum RouteOptions$Type {
    FASTEST(0),
    SHORTEST(1),
    ECONOMIC(2),
    BALANCED(3);
    
    private int a;

    private RouteOptions$Type(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
