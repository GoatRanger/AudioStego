package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.nokia.maps.MapBuildingObjectImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public final class MapBuildingObject extends MapProxyObject {
    private MapBuildingObjectImpl b;

    private MapBuildingObject() {
        super(null);
    }

    @OnlineNative
    private MapBuildingObject(MapBuildingObjectImpl mapBuildingObjectImpl) {
        super(mapBuildingObjectImpl);
        this.b = mapBuildingObjectImpl;
    }

    public Identifier getIdentifier() {
        return this.b.b();
    }

    public GeoCoordinate getPosition() {
        return this.b.c();
    }

    public String getPlaceName() {
        return this.b.getPlaceName();
    }

    public float getHeight() {
        return this.b.getHeight();
    }

    static {
        MapBuildingObjectImpl.a(new am<MapBuildingObject, MapBuildingObjectImpl>() {
            public MapBuildingObject a(MapBuildingObjectImpl mapBuildingObjectImpl) {
                if (mapBuildingObjectImpl != null) {
                    return new MapBuildingObject(mapBuildingObjectImpl);
                }
                return null;
            }
        });
    }
}
