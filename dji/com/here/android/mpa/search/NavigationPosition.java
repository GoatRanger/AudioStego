package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesNavigationPosition;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public class NavigationPosition {
    private PlacesNavigationPosition a;

    private NavigationPosition(PlacesNavigationPosition placesNavigationPosition) {
        this.a = placesNavigationPosition;
    }

    public GeoCoordinate getCoordinate() {
        return this.a.a();
    }

    public String getAccessType() {
        return this.a.b();
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

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
        PlacesNavigationPosition.a(new k<NavigationPosition, PlacesNavigationPosition>() {
            public PlacesNavigationPosition a(NavigationPosition navigationPosition) {
                return navigationPosition != null ? navigationPosition.a : null;
            }
        }, new am<NavigationPosition, PlacesNavigationPosition>() {
            public NavigationPosition a(PlacesNavigationPosition placesNavigationPosition) {
                return placesNavigationPosition != null ? new NavigationPosition(placesNavigationPosition) : null;
            }
        });
    }
}
