package com.here.android.mpa.ar;

import android.annotation.TargetApi;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.Vector3f;
import com.here.android.mpa.mapping.LocalMesh;
import com.nokia.maps.ARMeshObjectImpl;
import com.nokia.maps.annotation.HybridPlus;

@TargetApi(14)
@HybridPlus
public class ARMeshObject extends ARModelObject {
    protected ARMeshObjectImpl a;

    public ARMeshObject(GeoCoordinate geoCoordinate) {
        this(new ARMeshObjectImpl(geoCoordinate));
    }

    public ARMeshObject(Vector3f vector3f) {
        this(new ARMeshObjectImpl(vector3f));
    }

    public ARMeshObject(Vector3f vector3f, GeoCoordinate geoCoordinate) {
        this(new ARMeshObjectImpl(vector3f, geoCoordinate));
    }

    public ARMeshObject(GeoCoordinate geoCoordinate, LocalMesh localMesh, Image image) {
        this(geoCoordinate);
        setMesh(localMesh);
        setTexture(image);
    }

    public ARMeshObject(Vector3f vector3f, LocalMesh localMesh, Image image) {
        this(vector3f);
        setMesh(localMesh);
        setTexture(image);
    }

    public ARMeshObject(Vector3f vector3f, GeoCoordinate geoCoordinate, LocalMesh localMesh, Image image) {
        this(vector3f, geoCoordinate);
        setMesh(localMesh);
        setTexture(image);
    }

    private ARMeshObject(ARMeshObjectImpl aRMeshObjectImpl) {
        super(aRMeshObjectImpl);
        this.a = aRMeshObjectImpl;
    }

    public void setGeoPosition(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
    }

    public GeoCoordinate getGeoPosition() {
        return this.a.a();
    }

    public void setLocalPosition(Vector3f vector3f) {
        this.a.setLocalPosition(vector3f);
    }

    public Vector3f getLocalPosition() {
        return this.a.getLocalPosition();
    }

    public void setGeoDirection(GeoCoordinate geoCoordinate) {
        this.a.b(geoCoordinate);
    }

    public GeoCoordinate getGeoDirection() {
        return this.a.b();
    }

    public void setMesh(LocalMesh localMesh) {
        this.a.a(localMesh);
    }

    public LocalMesh getMesh() {
        return this.a.c();
    }
}
