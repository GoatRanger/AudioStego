package com.here.android.mpa.venues3d;

import com.here.android.mpa.venues3d.IRouteSection.RouteSectionType;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public class VenueRoute extends BaseNativeObject implements IRouteSection {
    private List<VenueManeuver> a = null;

    @HybridPlusNative
    private native List<VenueManeuver> getManuversNative();

    @HybridPlusNative
    private VenueRoute(int i) {
        this.nativeptr = i;
    }

    public RouteSectionType getRouteSectionType() {
        return RouteSectionType.VENUE;
    }

    public List<VenueManeuver> getVenueManeuvers() {
        if (this.a == null) {
            this.a = getManuversNative();
        }
        return this.a != null ? this.a : new ArrayList();
    }
}
