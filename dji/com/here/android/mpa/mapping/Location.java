package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.LocationImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;

@Online
public final class Location {
    private LocationImpl a;

    private Location(LocationImpl locationImpl) {
        this.a = locationImpl;
    }

    public GeoCoordinate getCoordinate() {
        return this.a.b();
    }

    public GeoBoundingBox getBoundingBox() {
        return this.a.c();
    }

    public LocationInfo getInfo() {
        return this.a.d();
    }

    static {
        LocationImpl.a(new am<Location, LocationImpl>() {
            public Location a(LocationImpl locationImpl) {
                if (locationImpl == null || !locationImpl.isValid()) {
                    return null;
                }
                return new Location(locationImpl);
            }
        });
    }
}
