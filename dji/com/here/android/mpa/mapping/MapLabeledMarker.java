package com.here.android.mpa.mapping;

import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.IconCategory;
import com.here.android.mpa.common.Image;
import com.nokia.maps.MapLabeledMarkerImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

public final class MapLabeledMarker extends MapObject {
    private MapLabeledMarkerImpl a;

    @Online
    public MapLabeledMarker(GeoCoordinate geoCoordinate) {
        this(new MapLabeledMarkerImpl(geoCoordinate));
    }

    @Online
    public MapLabeledMarker(GeoCoordinate geoCoordinate, Image image) {
        this(new MapLabeledMarkerImpl(geoCoordinate, image));
    }

    @OnlineNative
    private MapLabeledMarker(MapLabeledMarkerImpl mapLabeledMarkerImpl) {
        super(mapLabeledMarkerImpl);
        this.a = mapLabeledMarkerImpl;
    }

    @Online
    public MapLabeledMarker setIcon(IconCategory iconCategory) {
        this.a.a(iconCategory);
        return this;
    }

    @Online
    public MapLabeledMarker setIcon(Image image) {
        this.a.a(image);
        return this;
    }

    @Online
    public MapLabeledMarker setCoordinate(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
        return this;
    }

    @Online
    public GeoCoordinate getCoordinate() {
        return this.a.c();
    }

    @Online
    public MapLabeledMarker setAnchorPoint(PointF pointF) {
        this.a.a(pointF);
        return this;
    }

    @Online
    public PointF getAnchorPoint() {
        return this.a.getAnchorPoint();
    }

    @Online
    public MapLabeledMarker setTransparency(float f) {
        this.a.d(f);
        return this;
    }

    @Online
    public float getTransparency() {
        return this.a.b();
    }

    @Online
    public MapLabeledMarker setDeclutteringEnabled(boolean z) {
        this.a.a(z);
        return this;
    }

    @Online
    public boolean isDeclutteringEnabled() {
        return this.a.isDeclutteringEnabled();
    }

    @Online
    public MapLabeledMarker setOverlappingEnabled(boolean z) {
        this.a.setOverlappingEnabled(z);
        return this;
    }

    @Online
    public boolean isOverlappingEnabled() {
        return this.a.isOverlappingEnabled();
    }

    @Online
    public MapLabeledMarker setFadingAnimationEnabled(boolean z) {
        this.a.setFadingAnimationEnabled(z);
        return this;
    }

    @Online
    public boolean isFadingAnimationEnabled() {
        return this.a.isFadingAnimationEnabled();
    }

    @Online
    public MapLabeledMarker setLabelText(String str, String str2) {
        this.a.a(str, str2);
        return this;
    }

    @Online
    public String getLabelText(String str) {
        return this.a.a(str);
    }

    @Online
    public MapLabeledMarker setFontScalingFactor(float f) {
        this.a.a(f);
        return this;
    }

    @Online
    public float getFontScalingFactor() {
        return this.a.getFontScalingFactor();
    }

    static {
        MapLabeledMarkerImpl.a(new am<MapLabeledMarker, MapLabeledMarkerImpl>() {
            public MapLabeledMarker a(MapLabeledMarkerImpl mapLabeledMarkerImpl) {
                if (mapLabeledMarkerImpl != null) {
                    return new MapLabeledMarker(mapLabeledMarkerImpl);
                }
                return null;
            }
        });
    }
}
