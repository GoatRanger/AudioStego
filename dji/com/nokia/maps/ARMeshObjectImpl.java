package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Vector3f;
import com.here.android.mpa.mapping.LocalMesh;
import com.here.android.mpa.mapping.Mesh;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class ARMeshObjectImpl extends ARModelObjectImpl {
    private native void createLocalNative(Vector3f vector3f);

    private native void createNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void createWaypointerNative(Vector3f vector3f, GeoCoordinateImpl geoCoordinateImpl);

    private native void destroyNative();

    private native GeoCoordinateImpl getGeoPositionNative();

    private native LocalMeshImpl getMeshNative();

    private native GeoCoordinateImpl getWaypointNative();

    private native void setGeoPositionNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void setMeshNative(LocalMeshImpl localMeshImpl);

    private native void setWaypointNative(GeoCoordinateImpl geoCoordinateImpl);

    public native Vector3f getLocalPosition();

    public native void setLocalPosition(Vector3f vector3f);

    public ARMeshObjectImpl(Vector3f vector3f) {
        createLocalNative(vector3f);
    }

    public ARMeshObjectImpl(GeoCoordinate geoCoordinate) {
        createNative(GeoCoordinateImpl.get(geoCoordinate));
    }

    public ARMeshObjectImpl(Vector3f vector3f, GeoCoordinate geoCoordinate) {
        createWaypointerNative(vector3f, GeoCoordinateImpl.get(geoCoordinate));
    }

    public void a(GeoCoordinate geoCoordinate) {
        setGeoPositionNative(GeoCoordinateImpl.get(geoCoordinate));
    }

    public GeoCoordinate a() {
        return GeoCoordinateImpl.create(getGeoPositionNative());
    }

    public void b(GeoCoordinate geoCoordinate) {
        setWaypointNative(GeoCoordinateImpl.get(geoCoordinate));
    }

    public GeoCoordinate b() {
        return GeoCoordinateImpl.create(getWaypointNative());
    }

    public void a(LocalMesh localMesh) {
        LocalMeshImpl localMeshImpl = (LocalMeshImpl) MeshImpl.a((Mesh) localMesh);
        if (localMeshImpl == null || !localMeshImpl.d()) {
            throw new IllegalArgumentException("LocalMesh provided is invalid.");
        }
        setMeshNative(localMeshImpl);
    }

    public LocalMesh c() {
        return LocalMeshImpl.a(getMeshNative());
    }

    protected void finalize() {
        destroyNative();
    }
}
