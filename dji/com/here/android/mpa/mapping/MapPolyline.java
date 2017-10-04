package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoPolyline;
import com.nokia.maps.MapPolylineImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.k;

@Online
public final class MapPolyline extends MapObject {
    private MapPolylineImpl a;

    @Online
    public enum CapStyle {
        BUTT(0),
        ROUND(2);
        
        private int a;

        public static CapStyle toCapStyle(int i) {
            CapStyle capStyle = BUTT;
            switch (i) {
                case 0:
                    return BUTT;
                case 2:
                    return ROUND;
                default:
                    return capStyle;
            }
        }

        private CapStyle(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    public MapPolyline() {
        this(new MapPolylineImpl());
    }

    public MapPolyline(GeoPolyline geoPolyline) {
        this(new MapPolylineImpl(geoPolyline));
    }

    @OnlineNative
    protected MapPolyline(MapPolylineImpl mapPolylineImpl) {
        super(mapPolylineImpl);
        this.a = mapPolylineImpl;
    }

    public MapPolyline setGeoPolyline(GeoPolyline geoPolyline) {
        this.a.a(geoPolyline);
        return this;
    }

    public MapPolyline setLineColor(int i) {
        this.a.a(i);
        return this;
    }

    public int getLineColor() {
        return this.a.a();
    }

    public MapPolyline setLineWidth(int i) {
        this.a.c(i);
        return this;
    }

    public int getLineWidth() {
        return this.a.getLineWidth();
    }

    public boolean isDashEnabled() {
        return this.a.isDashEnabled();
    }

    public MapPolyline setDashEnabled(boolean z) {
        this.a.setDashEnabled(z);
        return this;
    }

    public int getDashPrimaryLength() {
        return this.a.getDashPrimaryLength();
    }

    public MapPolyline setDashPrimaryLength(int i) {
        this.a.g(i);
        return this;
    }

    public int getDashSecondaryLength() {
        return this.a.getDashSecondaryLength();
    }

    public MapPolyline setDashSecondaryLength(int i) {
        this.a.h(i);
        return this;
    }

    public boolean isGeodesicEnabled() {
        return this.a.c();
    }

    public MapPolyline setGeodesicEnabled(boolean z) {
        this.a.d(z);
        return this;
    }

    public MapPolyline setCapStyle(CapStyle capStyle) {
        this.a.b(capStyle.value());
        return this;
    }

    public CapStyle getCapStyle() {
        return CapStyle.toCapStyle(this.a.b());
    }

    public void setDepthTestEnabled(boolean z) {
        this.a.a(z);
    }

    public boolean getDepthTestEnabled() {
        return this.a.getDepthTestEnabled();
    }

    static {
        MapPolylineImpl.b(new k<MapPolyline, MapPolylineImpl>() {
            public MapPolylineImpl a(MapPolyline mapPolyline) {
                return mapPolyline.a;
            }
        });
    }
}
