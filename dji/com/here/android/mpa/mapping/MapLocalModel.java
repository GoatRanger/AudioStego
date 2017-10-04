package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.nokia.maps.MapLocalModelImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public final class MapLocalModel extends MapModelObject {
    private MapLocalModelImpl a;

    public MapLocalModel() {
        this(new MapLocalModelImpl());
    }

    @OnlineNative
    private MapLocalModel(MapLocalModelImpl mapLocalModelImpl) {
        super(mapLocalModelImpl);
        this.a = mapLocalModelImpl;
    }

    public MapLocalModel setMesh(LocalMesh localMesh) {
        this.a.a(localMesh);
        return this;
    }

    public LocalMesh getMesh() {
        return this.a.a();
    }

    public MapLocalModel setTexture(Image image) {
        this.a.a(image);
        return this;
    }

    public Image getTexture() {
        return this.a.b();
    }

    public GeoCoordinate getAnchor() {
        return this.a.c();
    }

    public MapLocalModel setAnchor(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
        return this;
    }

    public MapLocalModel setScale(float f) {
        this.a.a(f);
        return this;
    }

    public float getScale() {
        return this.a.getScale();
    }

    public MapLocalModel setDynamicScalingEnabled(boolean z) {
        this.a.a(z);
        return this;
    }

    public boolean isDynamicScalingEnabled() {
        return this.a.isDynamicScalingEnabled();
    }

    public MapLocalModel setPitch(float f) {
        this.a.b(f);
        return this;
    }

    public MapLocalModel setYaw(float f) {
        this.a.c(f);
        return this;
    }

    public MapLocalModel setRoll(float f) {
        this.a.d(f);
        return this;
    }

    public float getPitch() {
        return this.a.getPitch();
    }

    public float getYaw() {
        return this.a.getYaw();
    }

    public float getRoll() {
        return this.a.getRoll();
    }
}
