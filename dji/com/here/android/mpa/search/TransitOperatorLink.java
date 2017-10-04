package com.here.android.mpa.search;

import com.nokia.maps.PlacesTransitOperatorLink;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public class TransitOperatorLink {
    protected PlacesTransitOperatorLink a;

    TransitOperatorLink(PlacesTransitOperatorLink placesTransitOperatorLink) {
        this.a = placesTransitOperatorLink;
    }

    public String getText() {
        return this.a.a();
    }

    public Link getUrl() {
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
        PlacesTransitOperatorLink.a(new k<TransitOperatorLink, PlacesTransitOperatorLink>() {
            public PlacesTransitOperatorLink a(TransitOperatorLink transitOperatorLink) {
                return transitOperatorLink != null ? transitOperatorLink.a : null;
            }
        }, new am<TransitOperatorLink, PlacesTransitOperatorLink>() {
            public TransitOperatorLink a(PlacesTransitOperatorLink placesTransitOperatorLink) {
                return placesTransitOperatorLink != null ? new TransitOperatorLink(placesTransitOperatorLink) : null;
            }
        });
    }
}
