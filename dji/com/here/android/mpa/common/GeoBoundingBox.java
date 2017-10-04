package com.here.android.mpa.common;

import android.annotation.SuppressLint;
import com.nokia.maps.GeoBoundingBoxImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.List;

@Online
public final class GeoBoundingBox {
    private GeoBoundingBoxImpl a;

    private GeoBoundingBox(GeoBoundingBoxImpl geoBoundingBoxImpl) {
        this.a = geoBoundingBoxImpl;
    }

    public GeoBoundingBox(GeoCoordinate geoCoordinate, GeoCoordinate geoCoordinate2) {
        this.a = new GeoBoundingBoxImpl(geoCoordinate.a, geoCoordinate2.a);
    }

    public GeoBoundingBox(GeoCoordinate geoCoordinate, float f, float f2) {
        this.a = new GeoBoundingBoxImpl(geoCoordinate, f, f2);
    }

    public GeoCoordinate getTopLeft() {
        return new GeoCoordinate(this.a.a());
    }

    public boolean setTopLeft(GeoCoordinate geoCoordinate) {
        return this.a.a(geoCoordinate);
    }

    public GeoCoordinate getBottomRight() {
        return new GeoCoordinate(this.a.b());
    }

    public boolean setBottomRight(GeoCoordinate geoCoordinate) {
        return this.a.b(geoCoordinate);
    }

    public boolean setCoordinates(GeoCoordinate geoCoordinate, GeoCoordinate geoCoordinate2) {
        return this.a.a(geoCoordinate, geoCoordinate2);
    }

    public double getWidth() {
        return this.a.c();
    }

    public double getHeight() {
        return this.a.d();
    }

    public GeoBoundingBox merge(GeoBoundingBox geoBoundingBox) {
        return new GeoBoundingBox(GeoBoundingBoxImpl.a(this.a, geoBoundingBox.a));
    }

    public static GeoBoundingBox mergeBoxes(List<GeoBoundingBox> list) {
        return GeoBoundingBoxImpl.a((List) list);
    }

    public boolean contains(GeoBoundingBox geoBoundingBox) {
        return this.a.a(geoBoundingBox.a);
    }

    public static GeoBoundingBox getBoundingBoxContainingGeoCoordinates(List<GeoCoordinate> list) {
        return GeoBoundingBoxImpl.b((List) list);
    }

    public boolean contains(GeoCoordinate geoCoordinate) {
        return this.a.c(geoCoordinate.a);
    }

    public boolean intersects(GeoBoundingBox geoBoundingBox) {
        return this.a.b(geoBoundingBox.a);
    }

    public boolean isEmpty() {
        return this.a.e();
    }

    public GeoCoordinate getCenter() {
        return new GeoCoordinate(this.a.getCenter());
    }

    public void resizeToCenter(GeoCoordinate geoCoordinate) {
        this.a.d(geoCoordinate.a);
    }

    public void expand(float f, float f2) {
        this.a.a(f, f2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (GeoBoundingBox.class.isInstance(obj)) {
            return this.a.a((GeoBoundingBox) obj);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + 527;
    }

    static {
        GeoBoundingBoxImpl.a(new k<GeoBoundingBox, GeoBoundingBoxImpl>() {
            public GeoBoundingBoxImpl a(GeoBoundingBox geoBoundingBox) {
                return geoBoundingBox.a;
            }
        }, new am<GeoBoundingBox, GeoBoundingBoxImpl>() {
            public GeoBoundingBox a(GeoBoundingBoxImpl geoBoundingBoxImpl) {
                if (geoBoundingBoxImpl != null) {
                    return new GeoBoundingBox(geoBoundingBoxImpl);
                }
                return null;
            }
        });
    }

    @SuppressLint({"DefaultLocale"})
    public String toString() {
        return String.format("%f,%f;%f,%f", new Object[]{Double.valueOf(getTopLeft().getLatitude()), Double.valueOf(getTopLeft().getLongitude()), Double.valueOf(getBottomRight().getLatitude()), Double.valueOf(getBottomRight().getLongitude())});
    }
}
