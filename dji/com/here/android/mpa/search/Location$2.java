package com.here.android.mpa.search;

import com.nokia.maps.PlacesLocation;
import com.nokia.maps.am;

class Location$2 implements am<Location, PlacesLocation> {
    Location$2() {
    }

    public Location a(PlacesLocation placesLocation) {
        return placesLocation != null ? new Location(placesLocation, null) : null;
    }
}
