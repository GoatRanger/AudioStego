package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.MapCircleImpl;
import com.nokia.maps.annotation.Online;

@Online
public final class MapCircle extends MapObject {
    private MapCircleImpl a;

    public MapCircle() {
        this(new MapCircleImpl());
    }

    public MapCircle(double d, GeoCoordinate geoCoordinate) {
        this(new MapCircleImpl(d, geoCoordinate));
    }

    private MapCircle(MapCircleImpl mapCircleImpl) {
        super(mapCircleImpl);
        this.a = mapCircleImpl;
    }

    public MapCircle setCenter(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
        return this;
    }

    public GeoCoordinate getCenter() {
        return this.a.a();
    }

    public MapCircle setRadius(double d) {
        this.a.a(d);
        return this;
    }

    public double getRadius() {
        return this.a.b();
    }

    public MapCircle setFillColor(int i) {
        this.a.a(i);
        return this;
    }

    public int getFillColor() {
        return this.a.c();
    }

    public MapCircle setLineColor(int i) {
        this.a.b(i);
        return this;
    }

    public int getLineColor() {
        return this.a.d();
    }

    public MapCircle setLineWidth(int i) {
        this.a.c(i);
        return this;
    }

    public int getLineWidth() {
        return this.a.getLineWidth();
    }

    public void setDepthTestEnabled(boolean z) {
        this.a.a(z);
    }

    public boolean getDepthTestEnabled() {
        return this.a.getDepthTestEnabled();
    }
}
