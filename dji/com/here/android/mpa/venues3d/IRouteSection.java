package com.here.android.mpa.venues3d;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public interface IRouteSection {

    @HybridPlus
    public enum RouteSectionType {
        VENUE,
        OUTDOOR,
        LINK
    }

    RouteSectionType getRouteSectionType();
}
