package com.here.android.mpa.routing;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public enum RoutingError {
    NONE(0),
    UNKNOWN(1),
    OUT_OF_MEMORY(2),
    INVALID_PARAMETERS(3),
    INVALID_OPERATION(4),
    GRAPH_DISCONNECTED(5),
    GRAPH_DISCONNECTED_CHECK_OPTIONS(6),
    NO_START_POINT(7),
    NO_END_POINT(8),
    NO_END_POINT_CHECK_OPTIONS(9),
    CANNOT_DO_PEDESTRIAN(10),
    ROUTING_CANCELLED(11),
    VIOLATES_OPTIONS(12),
    ROUTE_CORRUPTED(13),
    INVALID_CREDENTIALS(14),
    REQUEST_TIMEOUT(15),
    OPERATION_NOT_ALLOWED(17),
    NO_CONNECTIVITY(18),
    INSUFFICIENT_MAP_DATA(19);
    
    private int a;

    private RoutingError(int i) {
        this.a = i;
    }

    public int value() {
        return this.a;
    }
}
