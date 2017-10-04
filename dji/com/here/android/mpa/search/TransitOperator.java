package com.here.android.mpa.search;

import com.nokia.maps.PlacesTransitOperator;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.Map;

@Online
public class TransitOperator {
    protected PlacesTransitOperator a;

    TransitOperator(PlacesTransitOperator placesTransitOperator) {
        this.a = placesTransitOperator;
    }

    public String getId() {
        return this.a.a();
    }

    public String getTitle() {
        return this.a.b();
    }

    public TransitOperatorSupplier getSupplier() {
        return this.a.c();
    }

    public Map<String, TransitOperatorLink> getLinks() {
        return this.a.d();
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
        PlacesTransitOperator.a(new k<TransitOperator, PlacesTransitOperator>() {
            public PlacesTransitOperator a(TransitOperator transitOperator) {
                return transitOperator != null ? transitOperator.a : null;
            }
        }, new am<TransitOperator, PlacesTransitOperator>() {
            public TransitOperator a(PlacesTransitOperator placesTransitOperator) {
                return placesTransitOperator != null ? new TransitOperator(placesTransitOperator) : null;
            }
        });
    }
}
