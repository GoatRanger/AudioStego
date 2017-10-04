package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesLocation;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dy;
import java.util.List;

public class Location {
    private PlacesLocation a;

    @Online
    public Location(GeoCoordinate geoCoordinate) {
        this.a = new PlacesLocation(geoCoordinate);
    }

    private Location(PlacesLocation placesLocation) {
        this.a = placesLocation;
    }

    @Online
    public Address getAddress() {
        return this.a.a();
    }

    @Online
    public String getId() {
        return this.a.d();
    }

    @Online
    public GeoCoordinate getCoordinate() {
        return this.a.b();
    }

    @Online
    public GeoBoundingBox getBoundingBox() {
        return this.a.c();
    }

    @Online
    public String getReference(String str) {
        dy.a(str, "Name argument is null");
        dy.a(!str.isEmpty(), "Name argument is empty");
        return this.a.b(str);
    }

    @Online
    @Deprecated
    public NavigationPosition getNavigationPosition() {
        return this.a.f();
    }

    @Online
    public List<NavigationPosition> getAccessPoints() {
        return this.a.e();
    }

    @Online
    public String toString() {
        return "Address: \n" + getAddress().toString() + "\nCoordinate: " + getCoordinate();
    }

    @Online
    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    @Online
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(obj);
    }

    static {
        PlacesLocation.a(new 1(), new 2());
    }
}
