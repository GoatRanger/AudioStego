package com.here.android.mpa.mapping;

import com.here.android.mpa.common.ViewObject;
import com.nokia.maps.MapObjectImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.k;
import java.util.BitSet;

@OnlineNative
public abstract class MapObject extends ViewObject {
    MapObjectImpl b;

    @OnlineNative
    public enum Type {
        UNKNOWN,
        MARKER,
        SCREEN_MARKER,
        POLYGON,
        POLYLINE,
        ROUTE,
        CONTAINER,
        CIRCLE,
        LOCAL_MODEL,
        GEO_MODEL,
        LABELED_MARKER
    }

    MapObject(MapObjectImpl mapObjectImpl) {
        super(mapObjectImpl);
        this.b = mapObjectImpl;
    }

    @Online
    public Type getType() {
        return this.b.l();
    }

    @Online
    public MapContainer getParent() {
        return this.b.m();
    }

    @Online
    public boolean isVisible() {
        return this.b.isVisible();
    }

    @Online
    public MapObject setVisible(boolean z) {
        this.b.b(z);
        return this;
    }

    @Online
    public MapObject setVisibleMask(int i) {
        this.b.d(i);
        return this;
    }

    @Online
    public MapObject unsetVisibleMask(int i) {
        this.b.e(i);
        return this;
    }

    @Online
    public MapObject setVisibleMask(int i, int i2) {
        this.b.b(i, i2);
        return this;
    }

    @Online
    public MapObject unsetVisibleMask(int i, int i2) {
        this.b.c(i, i2);
        return this;
    }

    @Online
    public MapObject resetVisibleMask(boolean z) {
        this.b.c(z);
        return this;
    }

    @Online
    public BitSet getVisibleMask() {
        return this.b.n();
    }

    @Online
    public MapOverlayType getOverlayType() {
        return this.b.q();
    }

    @Online
    public MapObject setOverlayType(MapOverlayType mapOverlayType) {
        this.b.a(mapOverlayType);
        return this;
    }

    @Online
    public MapOverlayType getReserveOverlayType() {
        return this.b.r();
    }

    @Online
    public MapObject setReserveOverlayType(MapOverlayType mapOverlayType) {
        this.b.b(mapOverlayType);
        return this;
    }

    @Online
    public int getZIndex() {
        return this.b.getZIndex();
    }

    @Online
    public MapObject setZIndex(int i) {
        this.b.f(i);
        return this;
    }

    static {
        MapObjectImpl.a(new k<MapObject, MapObjectImpl>() {
            public MapObjectImpl a(MapObject mapObject) {
                if (mapObject != null) {
                    return mapObject.b;
                }
                return null;
            }
        });
    }
}
