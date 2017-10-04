package com.here.android.mpa.mapping;

import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.nokia.maps.MapMarkerImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public final class MapMarker extends MapObject {
    private MapMarkerImpl a;

    public MapMarker() {
        this(new MapMarkerImpl());
    }

    public MapMarker(float f) {
        this(new MapMarkerImpl(f));
    }

    public MapMarker(GeoCoordinate geoCoordinate, Image image) {
        this(new MapMarkerImpl(geoCoordinate, image));
    }

    @OnlineNative
    private MapMarker(MapMarkerImpl mapMarkerImpl) {
        super(mapMarkerImpl);
        this.a = mapMarkerImpl;
    }

    public MapMarker setIcon(Image image) {
        this.a.a(image);
        return this;
    }

    public Image getIcon() {
        return this.a.a();
    }

    public MapMarker setCoordinate(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
        return this;
    }

    public GeoCoordinate getCoordinate() {
        return this.a.c();
    }

    public MapMarker setAnchorPoint(PointF pointF) {
        this.a.a(pointF);
        return this;
    }

    public PointF getAnchorPoint() {
        return this.a.getAnchorPoint();
    }

    public boolean isInfoBubbleVisible() {
        return this.a.i();
    }

    public MapMarker setDraggable(boolean z) {
        this.a.a(this, z);
        return this;
    }

    public boolean isDraggable() {
        return this.a.d();
    }

    public boolean setTransparency(float f) {
        return this.a.d(f);
    }

    public float getTransparency() {
        return this.a.b();
    }

    public MapMarker setDeclutteringEnabled(boolean z) {
        this.a.a(z);
        return this;
    }

    public boolean isDeclutteringEnabled() {
        return this.a.isDeclutteringEnabled();
    }

    public boolean setSvgIconScaling(float f) {
        return this.a.setSVGScaleFactor(f);
    }

    public float getSvgIconScaling() {
        return this.a.getSVGScaleFactor();
    }

    public MapMarker setTitle(String str) {
        this.a.b(str);
        return this;
    }

    public String getTitle() {
        return this.a.e();
    }

    public MapMarker setDescription(String str) {
        this.a.c(str);
        return this;
    }

    public String getDescription() {
        return this.a.f();
    }

    @Deprecated
    public void showInfoBubble() {
        this.a.g();
    }

    @Deprecated
    public void hideInfoBubble() {
        this.a.h();
    }

    public int getInfoBubbleHashCode() {
        return this.a.j();
    }

    static {
        MapMarkerImpl.b(new 1());
    }
}
