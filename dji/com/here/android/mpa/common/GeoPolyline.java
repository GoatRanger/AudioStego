package com.here.android.mpa.common;

import com.nokia.maps.GeoPolylineImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.List;

@Online
public class GeoPolyline {
    GeoPolylineImpl a;

    public GeoPolyline() {
        this(new GeoPolylineImpl());
    }

    public GeoPolyline(List<GeoCoordinate> list) {
        this(new GeoPolylineImpl((List) list));
    }

    private GeoPolyline(GeoPolylineImpl geoPolylineImpl) {
        this.a = geoPolylineImpl;
    }

    public final int getNumberOfPoints() {
        return this.a.getNumberOfPoints();
    }

    public final GeoCoordinate getPoint(int i) {
        return this.a.a(i);
    }

    public void add(List<GeoCoordinate> list) {
        this.a.a((List) list);
    }

    @Online
    public void add(GeoCoordinate geoCoordinate) {
        this.a.b(geoCoordinate);
    }

    @Online
    public void insert(GeoCoordinate geoCoordinate, int i) {
        this.a.a(geoCoordinate, i);
    }

    @Online
    public void remove(int i) {
        this.a.remove(i);
    }

    @Online
    public void clear() {
        this.a.clear();
    }

    @Online
    public double length() {
        return this.a.length();
    }

    @Online
    public int getNearestIndex(GeoCoordinate geoCoordinate) {
        return this.a.c(geoCoordinate);
    }

    @Online
    public GeoCoordinate getNearest(GeoCoordinate geoCoordinate) {
        return this.a.d(geoCoordinate);
    }

    @Online
    public boolean contains(GeoCoordinate geoCoordinate) {
        return this.a.a(geoCoordinate);
    }

    @Online
    public GeoBoundingBox getBoundingBox() {
        return this.a.a();
    }

    @Online
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (GeoPolyline.class.isInstance(obj)) {
            return this.a.equals(obj);
        }
        return false;
    }

    @Online
    public int hashCode() {
        return this.a.hashCode() + 217;
    }

    static {
        GeoPolylineImpl.b(new k<GeoPolyline, GeoPolylineImpl>() {
            public GeoPolylineImpl a(GeoPolyline geoPolyline) {
                return geoPolyline.a;
            }
        }, new am<GeoPolyline, GeoPolylineImpl>() {
            public GeoPolyline a(GeoPolylineImpl geoPolylineImpl) {
                return new GeoPolyline(geoPolylineImpl);
            }
        });
    }
}
