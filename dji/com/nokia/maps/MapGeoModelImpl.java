package com.nokia.maps;

import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.GeoMesh;
import com.here.android.mpa.mapping.Mesh;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class MapGeoModelImpl extends MapModelObjectImpl {
    private native void createNative();

    private native GeoMeshImpl getMeshNative();

    private native ImageImpl getTextureNative();

    private native void setMeshNative(GeoMeshImpl geoMeshImpl);

    private native void setTextureNative(ImageImpl imageImpl);

    public MapGeoModelImpl() {
        createNative();
    }

    @OnlineNative
    private MapGeoModelImpl(int i) {
        super(i);
    }

    public void a(GeoMesh geoMesh) {
        GeoMeshImpl geoMeshImpl = (GeoMeshImpl) MeshImpl.a((Mesh) geoMesh);
        if (geoMeshImpl == null || !geoMeshImpl.d()) {
            throw new IllegalArgumentException("GeoMesh provided is invalid.");
        }
        setMeshNative(geoMeshImpl);
    }

    public GeoMesh a() {
        return GeoMeshImpl.a(getMeshNative());
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
}
