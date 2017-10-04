package com.nokia.maps;

import android.graphics.Color;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.Online;

@Online
public class MapCircleImpl extends MapObjectImpl {
    private native void createNative();

    private native int getAlphaNative();

    private native int getBlueNative();

    private native GeoCoordinateImpl getCenterNative();

    private native int getFillAlphaNative();

    private native int getFillBlueNative();

    private native int getFillGreenNative();

    private native int getFillRedNative();

    private native int getGreenNative();

    private native double getRadiusNative();

    private native int getRedNative();

    private native void setCenterNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void setDepthTestEnabledNative(boolean z);

    private native void setFillColorNative(int i, int i2, int i3, int i4);

    private native void setLineColorNative(int i, int i2, int i3, int i4);

    private native void setLineWidthNative(int i);

    private native void setRadiusNative(double d);

    public native boolean getDepthTestEnabled();

    public native int getLineWidth();

    public MapCircleImpl() {
        createNative();
    }

    public MapCircleImpl(double d, GeoCoordinate geoCoordinate) {
        this();
        a(geoCoordinate);
        a(d);
    }

    public void a(GeoCoordinate geoCoordinate) {
        dy.a((Object) geoCoordinate, "center is null");
        dy.a(geoCoordinate.isValid(), "GeoCoordinate provided is invalid.");
        setCenterNative(GeoCoordinateImpl.get(geoCoordinate));
        o();
    }

    public GeoCoordinate a() {
        return GeoCoordinateImpl.create(getCenterNative());
    }

    public void a(double d) {
        if (d <= 0.0d) {
            throw new IllegalArgumentException("Radius provided is invalid.");
        }
        setRadiusNative(d);
        o();
    }

    public double b() {
        return getRadiusNative();
    }

    public void a(int i) {
        setFillColorNative(Color.red(i), Color.green(i), Color.blue(i), Color.alpha(i));
        o();
    }

    public int c() {
        return Color.argb(getFillAlphaNative(), getFillRedNative(), getFillGreenNative(), getFillBlueNative());
    }

    public void b(int i) {
        setLineColorNative(Color.red(i), Color.green(i), Color.blue(i), Color.alpha(i));
        o();
    }

    public int d() {
        return Color.argb(getAlphaNative(), getRedNative(), getGreenNative(), getBlueNative());
    }

    public void c(int i) {
        if (i < 0 || i > 100) {
            throw new IllegalArgumentException("Line width is not within the supported range [0..100].");
        }
        setLineWidthNative(i);
        o();
    }

    public void a(boolean z) {
        setDepthTestEnabledNative(z);
        o();
    }
}
