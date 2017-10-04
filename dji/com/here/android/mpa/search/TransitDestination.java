package com.here.android.mpa.search;

import com.nokia.maps.PlacesTransitDestination;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public class TransitDestination {
    protected PlacesTransitDestination a;

    TransitDestination(PlacesTransitDestination placesTransitDestination) {
        this.a = placesTransitDestination;
    }

    public String getDestination() {
        return this.a.a();
    }

    public String getLine() {
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
        PlacesTransitDestination.a(new k<TransitDestination, PlacesTransitDestination>() {
            public PlacesTransitDestination a(TransitDestination transitDestination) {
                return transitDestination != null ? transitDestination.a : null;
            }
        }, new am<TransitDestination, PlacesTransitDestination>() {
            public TransitDestination a(PlacesTransitDestination placesTransitDestination) {
                return placesTransitDestination != null ? new TransitDestination(placesTransitDestination) : null;
            }
        });
    }
}
