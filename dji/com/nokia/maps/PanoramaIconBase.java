package com.nokia.maps;

import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.streetlevel.StreetLevelIconPlacement;
import com.here.android.mpa.streetlevel.StreetLevelIconPlacement.HorizontalPlacement;
import com.here.android.mpa.streetlevel.StreetLevelIconPlacement.VerticalPlacement;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class PanoramaIconBase extends cx {
    private static String a = PanoramaIconBase.class.getSimpleName();
    private cq b = new cq(PanoramaIconBase.class.getSimpleName());
    private Identifier c;

    private native boolean attachNative(PanoramaBuilding panoramaBuilding);

    private native long getAttachmentId();

    private native float[] getPlacementNative();

    private native GeoCoordinateImpl getPosition();

    private native boolean setAnchorPointNative(float f, float f2);

    private native boolean setAttachmentId(long j);

    private native boolean setImageNative(ImageImpl imageImpl);

    private native boolean setPlacementNative(int i, int i2, float f);

    private native boolean setPositionNative(GeoCoordinateImpl geoCoordinateImpl);

    private native boolean setTextureCoordinatesNative(float f, float f2, float f3, float f4);

    private native boolean setTransparencyNative(float f);

    public native PointF getAnchorPoint();

    public native PointF getBottomRightTextureCoordinate();

    public native PointF getTopLeftTextureCoordinate();

    public native float getTransparency();

    protected PanoramaIconBase() {
    }

    protected PanoramaIconBase(int i) {
        super(i);
    }

    public void a(Image image) {
        dy.a((Object) image, "image argument is null");
        dy.a(image.isValid(), "image is invalid");
        if (setImageNative(ImageImpl.a(image))) {
            h();
        }
    }

    public void a(GeoCoordinate geoCoordinate) {
        dy.a((Object) geoCoordinate, "coordinate argument is null");
        dy.a(geoCoordinate.isValid(), "coordinate is invalid");
        if (setPositionNative(GeoCoordinateImpl.get(geoCoordinate))) {
            h();
        }
    }

    public Identifier e() {
        return this.c;
    }

    public GeoCoordinate f() {
        return GeoCoordinateImpl.create(getPosition());
    }

    public void a(Identifier identifier) {
        long parseLong;
        dy.a((Object) identifier, "identifier argument is null");
        try {
            parseLong = Long.parseLong(identifier.toString());
        } catch (NumberFormatException e) {
            parseLong = 0;
        }
        if (parseLong > 0) {
            boolean attachmentId = setAttachmentId(parseLong);
            this.c = identifier;
            if (attachmentId) {
                h();
            }
        }
    }

    public void c(float f) {
        boolean z = f >= 0.0f && f <= 1.0f;
        dy.a(z, "position is invalid");
        if (setTransparencyNative(f)) {
            h();
        }
    }

    public void a(StreetLevelIconPlacement streetLevelIconPlacement) {
        dy.a((Object) streetLevelIconPlacement, "placement argument is null");
        try {
            if (setPlacementNative(cv.a(streetLevelIconPlacement.getHorizontalPlacement()), cv.a(streetLevelIconPlacement.getVerticalPlacement()), streetLevelIconPlacement.getVerticalPlacementHeight())) {
                h();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("placement is not supported");
        }
    }

    public StreetLevelIconPlacement g() {
        HorizontalPlacement horizontalPlacement = HorizontalPlacement.DEFAULT;
        VerticalPlacement verticalPlacement = VerticalPlacement.DEFAULT;
        float f = 0.0f;
        float[] placementNative = getPlacementNative();
        if (placementNative != null && placementNative.length == 3) {
            try {
                horizontalPlacement = cv.a((int) placementNative[0]);
                verticalPlacement = cv.b((int) placementNative[1]);
                f = placementNative[2];
            } catch (Throwable e) {
                bj.c(a, "Exception \n%s", new Object[]{bj.a(e)});
                horizontalPlacement = HorizontalPlacement.DEFAULT;
                verticalPlacement = VerticalPlacement.DEFAULT;
            }
        }
        return cv.a(new cv(horizontalPlacement, verticalPlacement, f));
    }

    public boolean a(PointF pointF) {
        dy.a((Object) pointF, "anchor argument is null");
        boolean anchorPointNative = setAnchorPointNative(pointF.x, pointF.y);
        if (anchorPointNative) {
            h();
        }
        return anchorPointNative;
    }

    public void a(PointF pointF, PointF pointF2) {
        dy.a((Object) pointF, "topLeft argument is null");
        dy.a((Object) pointF, "bottomRight argument is null");
        if (setTextureCoordinatesNative(pointF.x, pointF.y, pointF2.x, pointF2.y)) {
            h();
        }
    }
}
