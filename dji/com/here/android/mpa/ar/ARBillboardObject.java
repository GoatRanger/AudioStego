package com.here.android.mpa.ar;

import android.annotation.TargetApi;
import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.Vector3f;
import com.nokia.maps.ARBillboardObjectImpl;
import com.nokia.maps.annotation.HybridPlus;

@TargetApi(14)
@HybridPlus
public class ARBillboardObject extends ARModelObject {
    protected ARBillboardObjectImpl a;

    @HybridPlus
    public enum Orientation {
        FIXED,
        BILLBOARD
    }

    public ARBillboardObject(Vector3f vector3f) {
        this(new ARBillboardObjectImpl(vector3f));
    }

    public ARBillboardObject(GeoCoordinate geoCoordinate) {
        this(new ARBillboardObjectImpl(geoCoordinate));
    }

    public ARBillboardObject(Vector3f vector3f, Image image) {
        this(new ARBillboardObjectImpl(vector3f));
        setTexture(image);
    }

    public ARBillboardObject(GeoCoordinate geoCoordinate, Image image) {
        this(new ARBillboardObjectImpl(geoCoordinate));
        setTexture(image);
    }

    private ARBillboardObject(ARBillboardObjectImpl aRBillboardObjectImpl) {
        super(aRBillboardObjectImpl);
        this.a = aRBillboardObjectImpl;
    }

    public void setSurfaceNormal(Vector3f vector3f) {
        this.a.setSurfaceNormal(vector3f);
    }

    public Vector3f getSurfaceNormal() {
        return this.a.getSurfaceNormal();
    }

    public void setUpDirection(Vector3f vector3f) {
        this.a.setUpDirection(vector3f);
    }

    public Vector3f getUpDirection() {
        return this.a.getUpDirection();
    }

    public void setSize(PointF pointF) {
        this.a.setSize(pointF);
    }

    public PointF getSize() {
        return this.a.getSize();
    }

    public void setOrientation(Orientation orientation) {
        this.a.a(orientation);
    }

    public Orientation getOrientation() {
        return this.a.a();
    }

    public void setLocalPosition(Vector3f vector3f) {
        this.a.setLocalPosition(vector3f);
    }

    public Vector3f getLocalPosition() {
        return this.a.getLocalPosition();
    }

    public void setGeoPosition(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
    }

    public GeoCoordinate getGeoPosition() {
        return this.a.b();
    }
}
