package com.nokia.maps;

import com.here.android.mpa.mapping.Location;
import com.here.android.mpa.mapping.MapProxyObject.Type;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class MapCartoMarkerImpl extends MapProxyObjectImpl {
    private native LocationImpl getLocationNative();

    @OnlineNative
    private MapCartoMarkerImpl(int i) {
        super(i);
    }

    private MapCartoMarkerImpl() {
    }

    public Type a() {
        return Type.MAP_CARTO_MARKER;
    }

    public Location b() {
        return LocationImpl.a(getLocationNative());
    }
}
