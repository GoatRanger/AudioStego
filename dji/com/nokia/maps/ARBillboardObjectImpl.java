package com.nokia.maps;

import android.graphics.PointF;
import com.here.android.mpa.ar.ARBillboardObject.Orientation;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Vector3f;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class ARBillboardObjectImpl extends ARModelObjectImpl {
    private native void createLocalNative(Vector3f vector3f);

    private native void createNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void destroyNative();

    private native GeoCoordinateImpl getGeoPositionNative();

    private native int getOrientationNative();

    private native void setGeoPositionNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void setOrientationNative(int i);

    public native Vector3f getLocalPosition();

    public native PointF getSize();

    public native Vector3f getSurfaceNormal();

    public native Vector3f getUpDirection();

    public native void setLocalPosition(Vector3f vector3f);

    public native void setSize(PointF pointF);

    public native void setSurfaceNormal(Vector3f vector3f);

    public native void setUpDirection(Vector3f vector3f);

    public ARBillboardObjectImpl(Vector3f vector3f) {
        createLocalNative(vector3f);
    }

    public ARBillboardObjectImpl(GeoCoordinate geoCoordinate) {
        createNative(GeoCoordinateImpl.get(geoCoordinate));
    }

    public void a(Orientation orientation) {
        setOrientationNative(orientation.ordinal());
    }

    public Orientation a() {
        switch (getOrientationNative()) {
            case 0:
                return Orientation.FIXED;
            case 1:
                return Orientation.BILLBOARD;
            default:
                return Orientation.FIXED;
        }
    }

    public void a(GeoCoordinate geoCoordinate) {
        setGeoPositionNative(GeoCoordinateImpl.get(geoCoordinate));
    }

    public GeoCoordinate b() {
        return GeoCoordinateImpl.create(getGeoPositionNative());
    }

    protected void finalize() {
        destroyNative();
    }
}
