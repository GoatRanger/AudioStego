package com.here.android.mpa.routing;

import android.util.SparseArray;
import com.nokia.maps.annotation.Online;

@Online
public enum RouteOptions$TransportMode {
    CAR(0),
    PEDESTRIAN(1),
    PUBLIC_TRANSPORT(2),
    TRACK(3),
    TRUCK(5),
    BICYCLE(4),
    UNDEFINED(6);
    
    private static SparseArray<RouteOptions$TransportMode> b;
    private int a;

    static {
        b = new SparseArray();
        RouteOptions$TransportMode[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            RouteOptions$TransportMode routeOptions$TransportMode = values[i];
            b.put(routeOptions$TransportMode.a, routeOptions$TransportMode);
            i++;
        }
    }

    private RouteOptions$TransportMode(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }

    public static RouteOptions$TransportMode getMode(int i) {
        return (RouteOptions$TransportMode) b.get(i);
    }
}
