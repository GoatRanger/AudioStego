package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesLink;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dy;
import java.util.List;

public class PlaceLink extends DiscoveryResult {
    PlaceLink(PlacesLink placesLink) {
        super(placesLink);
    }

    @Online
    public GeoCoordinate getPosition() {
        return this.a.i();
    }

    @Online
    public GeoBoundingBox getBoundingBox() {
        return this.a.n();
    }

    @Online
    public double getDistance() {
        return (double) this.a.j();
    }

    @Online
    public double getAverageRating() {
        return this.a.k();
    }

    @Online
    public Category getCategory() {
        return this.a.l();
    }

    @Online
    public PlaceRequest getDetailsRequest() {
        return this.a.h();
    }

    @Online
    public boolean isSponsored() {
        return this.a.o();
    }

    @Online
    public String getReference(String str) {
        dy.a((Object) str, "Name argument is null");
        dy.a(!str.isEmpty(), "Name argument is empty");
        return this.a.a(str);
    }

    @Online
    public List<String> getAlternativeReferenceIds(String str) {
        dy.a((Object) str, "Name argument is null");
        dy.a(!str.isEmpty(), "Name argument is empty");
        return this.a.b(str);
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
}
