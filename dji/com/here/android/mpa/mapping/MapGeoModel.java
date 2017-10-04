package com.here.android.mpa.mapping;

import com.here.android.mpa.common.Image;
import com.nokia.maps.MapGeoModelImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public final class MapGeoModel extends MapModelObject {
    private MapGeoModelImpl a;

    public MapGeoModel() {
        this(new MapGeoModelImpl());
    }

    @OnlineNative
    private MapGeoModel(MapGeoModelImpl mapGeoModelImpl) {
        super(mapGeoModelImpl);
        this.a = mapGeoModelImpl;
    }

    public MapGeoModel setMesh(GeoMesh geoMesh) {
        this.a.a(geoMesh);
        return this;
    }

    public GeoMesh getMesh() {
        return this.a.a();
    }

    public MapGeoModel setTexture(Image image) {
        this.a.a(image);
        return this;
    }

    public Image getTexture() {
        return this.a.b();
    }
}
