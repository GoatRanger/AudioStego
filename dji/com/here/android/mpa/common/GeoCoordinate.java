package com.here.android.mpa.common;

import com.nokia.maps.GeoCoordinateImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.dy;
import com.nokia.maps.k;

@Online
public final class GeoCoordinate {
    public static final int UNKNOWN_ALTITUDE = 1073741824;
    GeoCoordinateImpl a;

    public GeoCoordinate(double d, double d2) {
        this(new GeoCoordinateImpl(d, d2));
    }

    public GeoCoordinate(double d, double d2, double d3) {
        this(new GeoCoordinateImpl(d, d2, d3));
    }

    public GeoCoordinate(GeoCoordinate geoCoordinate) {
        dy.a((Object) geoCoordinate, "Cannot initialize coordinate with null");
        this.a = new GeoCoordinateImpl(geoCoordinate.a);
    }

    @OnlineNative
    GeoCoordinate(GeoCoordinateImpl geoCoordinateImpl) {
        this.a = geoCoordinateImpl;
    }

    public double getLatitude() {
        return this.a.a();
    }

    public void setLatitude(double d) {
        this.a.a(d);
    }

    public double getLongitude() {
        return this.a.b();
    }

    public void setLongitude(double d) {
        this.a.b(d);
    }

    public double getAltitude() {
        return this.a.c();
    }

    public void setAltitude(double d) {
        this.a.c(d);
    }

    public double distanceTo(GeoCoordinate geoCoordinate) {
        return this.a.a(geoCoordinate.a);
    }

    public boolean isValid() {
        return this.a.d();
    }

    public double getHeading(GeoCoordinate geoCoordinate) {
        return this.a.b(geoCoordinate.a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (GeoCoordinate.class.isInstance(obj)) {
            return this.a.equals(obj);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + 527;
    }

    public String toString() {
        return "Lat: " + getLatitude() + ", Long: " + getLongitude() + ", Alt: " + getAltitude();
    }

    static {
        GeoCoordinateImpl.a(new k<GeoCoordinate, GeoCoordinateImpl>() {
            public GeoCoordinateImpl a(GeoCoordinate geoCoordinate) {
                return geoCoordinate.a;
            }
        }, new am<GeoCoordinate, GeoCoordinateImpl>() {
            public GeoCoordinate a(GeoCoordinateImpl geoCoordinateImpl) {
                if (geoCoordinateImpl != null) {
                    return new GeoCoordinate(geoCoordinateImpl);
                }
                return null;
            }
        });
    }
}
