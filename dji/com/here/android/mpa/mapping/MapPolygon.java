package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoPolygon;
import com.nokia.maps.MapPolygonImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.k;

@Online
public final class MapPolygon extends MapObject {
    private MapPolygonImpl a;

    public MapPolygon() {
        this(new MapPolygonImpl());
    }

    public MapPolygon(GeoPolygon geoPolygon) {
        this(new MapPolygonImpl(geoPolygon, null));
    }

    @OnlineNative
    protected MapPolygon(MapPolygonImpl mapPolygonImpl) {
        super(mapPolygonImpl);
        this.a = mapPolygonImpl;
    }

    public MapPolygon setGeoPolygon(GeoPolygon geoPolygon) {
        this.a.a(geoPolygon);
        return this;
    }

    public MapPolygon setFillColor(int i) {
        this.a.a(i);
        return this;
    }

    public int getFillColor() {
        return this.a.a();
    }

    public MapPolygon setLineColor(int i) {
        this.a.b(i);
        return this;
    }

    public int getLineColor() {
        return this.a.b();
    }

    public MapPolygon setLineWidth(int i) {
        this.a.c(i);
        return this;
    }

    public int getLineWidth() {
        return this.a.getLineWidth();
    }

    public boolean isGeodesicEnabled() {
        return this.a.c();
    }

    public MapPolygon setGeodesicEnabled(boolean z) {
        this.a.a(z);
        return this;
    }

    public void setDepthTestEnabled(boolean z) {
        this.a.d(z);
    }

    public boolean getDepthTestEnabled() {
        return this.a.getDepthTestEnabled();
    }

    static {
        MapPolygonImpl.b(new k<MapPolygon, MapPolygonImpl>() {
            public MapPolygonImpl a(MapPolygon mapPolygon) {
                return mapPolygon.a;
            }
        });
    }
}
