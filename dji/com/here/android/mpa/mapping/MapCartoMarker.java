package com.here.android.mpa.mapping;

import com.nokia.maps.MapCartoMarkerImpl;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public final class MapCartoMarker extends MapProxyObject {
    private MapCartoMarkerImpl b;

    @OnlineNative
    private MapCartoMarker(MapCartoMarkerImpl mapCartoMarkerImpl) {
        super(mapCartoMarkerImpl);
        this.b = mapCartoMarkerImpl;
    }

    public Location getLocation() {
        return this.b.b();
    }
}
