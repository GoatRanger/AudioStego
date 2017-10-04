package com.nokia.maps;

import com.here.android.mpa.common.GeoPosition;
import com.nokia.maps.annotation.Online;

@Online
public class LocationContext extends BaseNativeObject {
    private static final String a = LocationContext.class.getName();

    private native void createNative();

    private native void destroyNative();

    private final native GeoBoundingBoxImpl getMapViewNative();

    private final native GeoPositionImpl getUserPositionNative();

    private native boolean setMapviewNative(GeoBoundingBoxImpl geoBoundingBoxImpl);

    private native boolean setUserPositionNative(GeoPositionImpl geoPositionImpl);

    public LocationContext() {
        createNative();
        a();
    }

    public boolean a(GeoPosition geoPosition) {
        dy.a((Object) geoPosition, "userPosition is null");
        return setUserPositionNative(GeoPositionImpl.a(geoPosition));
    }

    private void a() {
        try {
            PositioningManagerImpl a = PositioningManagerImpl.a();
            if (a != null) {
                setUserPositionNative(GeoPositionImpl.a(a.e()));
            }
        } catch (Exception e) {
            bj.b(a, e.getMessage(), new Object[0]);
        }
    }

    protected void finalize() {
        destroyNative();
    }
}
