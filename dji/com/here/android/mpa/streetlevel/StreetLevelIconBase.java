package com.here.android.mpa.streetlevel;

import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.Image;
import com.nokia.maps.PanoramaIconBase;

public abstract class StreetLevelIconBase extends StreetLevelObject {
    private PanoramaIconBase a;

    StreetLevelIconBase(PanoramaIconBase panoramaIconBase) {
        super(panoramaIconBase);
        this.a = panoramaIconBase;
    }

    public void setImage(Image image) {
        this.a.a(image);
    }

    public void setPosition(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
    }

    public GeoCoordinate getPosition() {
        return this.a.f();
    }

    public StreetLevelIconBase setAttachmentIdentifier(Identifier identifier) {
        this.a.a(identifier);
        return this;
    }

    public Identifier getAttachmentIdentifier() {
        return this.a.e();
    }

    public void setAnchorPoint(PointF pointF) {
        this.a.a(pointF);
    }

    public PointF getAnchorPoint() {
        return this.a.getAnchorPoint();
    }

    public void setTransparency(float f) {
        this.a.c(f);
    }

    public float getTransparency() {
        return this.a.getTransparency();
    }

    public void setPlacementMode(StreetLevelIconPlacement streetLevelIconPlacement) {
        this.a.a(streetLevelIconPlacement);
    }

    public StreetLevelIconPlacement getPlacementMode() {
        return this.a.g();
    }

    public void setTextureCoordinates(PointF pointF, PointF pointF2) {
        this.a.a(pointF, pointF2);
    }

    public PointF getTopLeftTextureCoordinate() {
        return this.a.getTopLeftTextureCoordinate();
    }

    public PointF getBottomRightTextureCoordinate() {
        return this.a.getBottomRightTextureCoordinate();
    }
}
