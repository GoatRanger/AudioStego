package com.here.android.mpa.mapping;

import android.graphics.PointF;
import android.view.View;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.bt;
import com.nokia.maps.k;

@HybridPlus
public class MapOverlay {
    private bt a;

    public MapOverlay(View view, GeoCoordinate geoCoordinate) {
        this.a = new bt(view, geoCoordinate);
    }

    public PointF getAnchorPoint() {
        return this.a.a();
    }

    public MapOverlay setAnchorPoint(PointF pointF) {
        this.a.a(pointF);
        return this;
    }

    public View getView() {
        return this.a.b();
    }

    public GeoCoordinate getCoordinate() {
        return this.a.c();
    }

    public MapOverlay setCoordinate(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
        return this;
    }

    static {
        bt.a(new k<MapOverlay, bt>() {
            public bt a(MapOverlay mapOverlay) {
                return mapOverlay != null ? mapOverlay.a : null;
            }
        });
    }
}
