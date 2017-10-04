package com.here.android.mpa.search;

import com.nokia.maps.PlacesTransitOperatorSupplier;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public class TransitOperatorSupplier {
    protected PlacesTransitOperatorSupplier a;

    TransitOperatorSupplier(PlacesTransitOperatorSupplier placesTransitOperatorSupplier) {
        this.a = placesTransitOperatorSupplier;
    }

    public String getTitle() {
        return this.a.a();
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
        PlacesTransitOperatorSupplier.a(new k<TransitOperatorSupplier, PlacesTransitOperatorSupplier>() {
            public PlacesTransitOperatorSupplier a(TransitOperatorSupplier transitOperatorSupplier) {
                return transitOperatorSupplier != null ? transitOperatorSupplier.a : null;
            }
        }, new am<TransitOperatorSupplier, PlacesTransitOperatorSupplier>() {
            public TransitOperatorSupplier a(PlacesTransitOperatorSupplier placesTransitOperatorSupplier) {
                return placesTransitOperatorSupplier != null ? new TransitOperatorSupplier(placesTransitOperatorSupplier) : null;
            }
        });
    }
}
