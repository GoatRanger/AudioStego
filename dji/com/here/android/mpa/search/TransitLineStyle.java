package com.here.android.mpa.search;

import com.nokia.maps.PlacesTransitLineStyle;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public class TransitLineStyle {
    protected PlacesTransitLineStyle a;

    TransitLineStyle(PlacesTransitLineStyle placesTransitLineStyle) {
        this.a = placesTransitLineStyle;
    }

    public String getColor() {
        return this.a.a();
    }

    public String getTextColor() {
        return this.a.b();
    }

    public String getOutlineColor() {
        return this.a.c();
    }

    public String getIconShape() {
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
        PlacesTransitLineStyle.a(new k<TransitLineStyle, PlacesTransitLineStyle>() {
            public PlacesTransitLineStyle a(TransitLineStyle transitLineStyle) {
                return transitLineStyle != null ? transitLineStyle.a : null;
            }
        }, new am<TransitLineStyle, PlacesTransitLineStyle>() {
            public TransitLineStyle a(PlacesTransitLineStyle placesTransitLineStyle) {
                return placesTransitLineStyle != null ? new TransitLineStyle(placesTransitLineStyle) : null;
            }
        });
    }
}
