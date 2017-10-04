package com.here.android.mpa.guidance;

import com.here.android.mpa.routing.Route;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public abstract class NavigationManager$RerouteListener {
    public void onRerouteBegin() {
    }

    public void onRerouteEnd(Route route) {
    }

    public void onRerouteFailed() {
    }
}
