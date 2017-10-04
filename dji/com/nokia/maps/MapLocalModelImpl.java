package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.LocalMesh;
import com.here.android.mpa.mapping.Mesh;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class MapLocalModelImpl extends MapModelObjectImpl {
    private native void createNative();

    private native GeoCoordinateImpl getAnchorNative();

    private native LocalMeshImpl getMeshNative();

    private native ImageImpl getTextureNative();

    private native void setPitch(float f);

    private native void setRoll(float f);

    private native void setScale(float f);

    private native void setTextureNative(ImageImpl imageImpl);

    private native void setYaw(float f);

    public native float getPitch();

    public native float getRoll();

    public native float getScale();

    public native float getYaw();

    public native boolean isDynamicScalingEnabled();

    public native void setAnchorNative(GeoCoordinateImpl geoCoordinateImpl);

    native void setDynamicScalingEnabled(boolean z);

    public native void setMeshNative(LocalMeshImpl localMeshImpl);

    public MapLocalModelImpl() {
        createNative();
    }

    @OnlineNative
    private MapLocalModelImpl(int i) {
        super(i);
    }

    public void a(LocalMesh localMesh) {
        LocalMeshImpl localMeshImpl = (LocalMeshImpl) MeshImpl.a((Mesh) localMesh);
        if (localMeshImpl == null || !localMeshImpl.d()) {
            throw new IllegalArgumentException("LocalMesh provided is invalid.");
        }
        setMeshNative((LocalMeshImpl) MeshImpl.a((Mesh) localMesh));
        o();
    }

    public LocalMesh a() {
        return LocalMeshImpl.a(getMeshNative());
    }

    public void a(Image image) {
        if (image.isValid()) {
            setTextureNative(ImageImpl.a(image));
            o();
            return;
        }
        throw new IllegalArgumentException("Image provided is invalid.");
    }

    public Image b() {
        return ImageImpl.a(getTextureNative());
    }

    public GeoCoordinate c() {
        return GeoCoordinateImpl.create(getAnchorNative());
    }

    public void a(GeoCoordinate geoCoordinate) {
        if (geoCoordinate.isValid()) {
            setAnchorNative(GeoCoordinateImpl.get(geoCoordinate));
            o();
            return;
        }
        throw new IllegalArgumentException("GeoCoordinate provided is invalid.");
    }

    public void a(float f) {
        setScale(f);
        o();
    }

    public void a(boolean z) {
        setDynamicScalingEnabled(z);
        o();
    }

    public void b(float f) {
        setPitch(f);
        o();
    }

    public void c(float f) {
        setYaw(f);
        o();
    }

    public void d(float f) {
        setRoll(f);
        o();
    }
}
