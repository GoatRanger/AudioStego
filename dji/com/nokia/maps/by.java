package com.nokia.maps;

import com.here.android.mpa.routing.Route;
import com.nokia.maps.a.ac;

public class by {
    public static MapRouteImpl a(Route route) {
        switch (RouteImpl.a(route).a()) {
            case ENHANCED_TRANSIT_ROUTE:
                return new ca();
            case URBAN_MOBILITY_ROUTE:
                return new ac();
            default:
                return new MapRouteImpl();
        }
    }
}
