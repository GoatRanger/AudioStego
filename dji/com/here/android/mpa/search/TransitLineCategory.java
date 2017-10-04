package com.here.android.mpa.search;

import com.nokia.maps.PlacesTransitLineCategory;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public class TransitLineCategory {
    protected PlacesTransitLineCategory a;

    TransitLineCategory(PlacesTransitLineCategory placesTransitLineCategory) {
        this.a = placesTransitLineCategory;
    }

    public String getId() {
        return this.a.a();
    }

    public String getTitle() {
        return this.a.b();
    }

    public String getLocalName() {
        return this.a.c();
    }

    public String getIcon() {
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
        PlacesTransitLineCategory.a(new k<TransitLineCategory, PlacesTransitLineCategory>() {
            public PlacesTransitLineCategory a(TransitLineCategory transitLineCategory) {
                return transitLineCategory != null ? transitLineCategory.a : null;
            }
        }, new am<TransitLineCategory, PlacesTransitLineCategory>() {
            public TransitLineCategory a(PlacesTransitLineCategory placesTransitLineCategory) {
                return placesTransitLineCategory != null ? new TransitLineCategory(placesTransitLineCategory) : null;
            }
        });
    }
}
