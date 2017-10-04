package com.here.android.mpa.common;

import com.nokia.maps.GeoPolygonImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.List;

@Online
public final class GeoPolygon extends GeoPolyline {
    public GeoPolygon() {
        this(new GeoPolygonImpl());
    }

    public GeoPolygon(List<GeoCoordinate> list) {
        this(new GeoPolygonImpl((List) list));
    }

    private GeoPolygon(GeoPolygonImpl geoPolygonImpl) {
        this.a = geoPolygonImpl;
    }

    public int hashCode() {
        return this.a.hashCode() + 217;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (GeoPolygon.class.isInstance(obj)) {
            return this.a.equals(obj);
        }
        return false;
    }

    static {
        GeoPolygonImpl.a(new k<GeoPolygon, GeoPolygonImpl>() {
            public GeoPolygonImpl a(GeoPolygon geoPolygon) {
                return (GeoPolygonImpl) geoPolygon.a;
            }
        }, new am<GeoPolygon, GeoPolygonImpl>() {
            public GeoPolygon a(GeoPolygonImpl geoPolygonImpl) {
                if (geoPolygonImpl != null) {
                    return new GeoPolygon(geoPolygonImpl);
                }
                return null;
            }
        });
    }
}
