package com.here.android.mpa.search;

import com.nokia.maps.PlacesTransitLine;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public class TransitLine {
    protected PlacesTransitLine a;

    TransitLine(PlacesTransitLine placesTransitLine) {
        this.a = placesTransitLine;
    }

    public String getName() {
        return this.a.a();
    }

    public String getDestination() {
        return this.a.b();
    }

    public TransitLineCategory getLineCategory() {
        return this.a.c();
    }

    public TransitLineStyle getStyle() {
        return this.a.d();
    }

    public String getOperator() {
        return this.a.e();
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
        PlacesTransitLine.a(new k<TransitLine, PlacesTransitLine>() {
            public PlacesTransitLine a(TransitLine transitLine) {
                return transitLine != null ? transitLine.a : null;
            }
        }, new am<TransitLine, PlacesTransitLine>() {
            public TransitLine a(PlacesTransitLine placesTransitLine) {
                return placesTransitLine != null ? new TransitLine(placesTransitLine) : null;
            }
        });
    }
}
