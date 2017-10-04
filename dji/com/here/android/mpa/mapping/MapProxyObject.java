package com.here.android.mpa.mapping;

import com.here.android.mpa.common.ViewObject;
import com.nokia.maps.MapProxyObjectImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.k;

public abstract class MapProxyObject extends ViewObject {
    MapProxyObjectImpl a;

    @OnlineNative
    public enum Type {
        UNKNOWN,
        SAFETY_SPOT,
        TRAFFIC_EVENT,
        TRANSIT_ACCESS,
        TRANSIT_LINE,
        TRANSIT_LINE_SEGMENT,
        TRANSIT_STOP,
        EXTRUDED_BUILDING,
        MAP_CARTO_MARKER,
        CLUSTER_MARKER
    }

    protected MapProxyObject(MapProxyObjectImpl mapProxyObjectImpl) {
        super(mapProxyObjectImpl);
        this.a = mapProxyObjectImpl;
    }

    static {
        MapProxyObjectImpl.a(new k<MapProxyObject, MapProxyObjectImpl>() {
            public MapProxyObjectImpl a(MapProxyObject mapProxyObject) {
                return mapProxyObject.a;
            }
        });
    }

    @Online
    public Type getType() {
        return this.a.a();
    }
}
