package com.here.android.mpa.guidance;

import com.here.android.mpa.routing.Route;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public abstract class NavigationManager$NavigationManagerEventListener {
    public void onRunningStateChanged() {
    }

    public void onNavigationModeChanged() {
    }

    public void onEnded(NavigationManager$NavigationMode navigationManager$NavigationMode) {
    }

    public void onMapUpdateModeChanged(NavigationManager$MapUpdateMode navigationManager$MapUpdateMode) {
    }

    public void onRouteUpdated(Route route) {
    }

    public void onCountryInfo(String str, String str2) {
    }
}
