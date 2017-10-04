package com.here.android.mpa.guidance;

import com.here.android.mpa.routing.Route;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public abstract class NavigationManager$TrafficRerouteListener {

    @HybridPlus
    public enum TrafficEnabledRoutingState {
        OFF,
        ON,
        ONGOING_REQUEST,
        NOT_AVAILABLE
    }

    public void onTrafficRerouted(Route route) {
    }

    public void onTrafficRerouteFailed(TrafficNotification trafficNotification) {
    }

    public void onTrafficRerouteBegin(TrafficNotification trafficNotification) {
    }

    public void onTrafficRerouteState(TrafficEnabledRoutingState trafficEnabledRoutingState) {
    }
}
