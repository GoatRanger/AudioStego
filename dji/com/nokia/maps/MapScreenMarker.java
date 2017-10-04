package com.nokia.maps;

import android.graphics.PointF;
import com.here.android.mpa.common.Image;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class MapScreenMarker extends MapMarkerBase {
    private native void createNative();

    private native void createNative(float f, float f2, ImageImpl imageImpl);

    private native void setScreenCoordinateNative(float f, float f2);

    public native PointF getScreenCoordinate();

    @OnlineNative
    private MapScreenMarker(int i) {
        super(i);
    }

    public MapScreenMarker() {
        createNative();
    }

    public MapScreenMarker(PointF pointF, Image image) throws IllegalArgumentException {
        super(image);
        if (pointF == null || image == null || !image.isValid()) {
            createNative();
            if (pointF == null) {
                try {
                    throw new NullPointerException("PointF provided is null.");
                } catch (NullPointerException e) {
                    throw e;
                }
            } else if (image == null) {
                throw new NullPointerException("Image provided is null.");
            } else if (!image.isValid()) {
                throw new IllegalArgumentException("Image provided is invalid.");
            }
        }
        createNative(pointF.x, pointF.y, ImageImpl.a(image));
    }

    public void a(Image image) {
        super.a(image);
        o();
    }

    public void b(PointF pointF) {
        if (pointF == null) {
            throw new NullPointerException("PointF provided is null.");
        }
        setScreenCoordinateNative(pointF.x, pointF.y);
        o();
    }

    public void a(PointF pointF) {
        super.a(pointF);
        o();
    }

    public boolean a(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Transparency value supplied is invalid.");
        }
        float b = b();
        boolean b2 = b(f);
        if (b2 && b != b()) {
            o();
        }
        return b2;
    }
}
