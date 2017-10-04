package com.here.android.mpa.mapping;

import android.graphics.PointF;
import com.here.android.mpa.common.Image;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public final class MapScreenMarker extends MapObject {
    private com.nokia.maps.MapScreenMarker a;

    public MapScreenMarker() {
        this(new com.nokia.maps.MapScreenMarker());
    }

    public MapScreenMarker(PointF pointF, Image image) {
        this(new com.nokia.maps.MapScreenMarker(pointF, image));
    }

    @OnlineNative
    private MapScreenMarker(com.nokia.maps.MapScreenMarker mapScreenMarker) {
        super(mapScreenMarker);
        this.a = mapScreenMarker;
    }

    public MapScreenMarker setIcon(Image image) {
        this.a.a(image);
        return this;
    }

    public Image getIcon() {
        return this.a.a();
    }

    public MapScreenMarker setScreenCoordinate(PointF pointF) {
        this.a.b(pointF);
        return this;
    }

    public PointF getScreenCoordinate() {
        return this.a.getScreenCoordinate();
    }

    public MapScreenMarker setAnchorPoint(PointF pointF) {
        this.a.a(pointF);
        return this;
    }

    public PointF getAnchorPoint() {
        return this.a.getAnchorPoint();
    }

    public MapScreenMarker setTransparency(float f) {
        this.a.a(f);
        return this;
    }

    public float getTransparency() {
        return this.a.b();
    }
}
