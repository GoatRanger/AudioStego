package com.here.android.mpa.mapping;

import com.nokia.maps.MapContainerImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import java.util.List;

@Online
public final class MapContainer extends MapObject {
    MapContainerImpl a;

    public MapContainer() {
        this(new MapContainerImpl());
    }

    private MapContainer(MapContainerImpl mapContainerImpl) {
        super(mapContainerImpl);
        this.a = mapContainerImpl;
    }

    public boolean addMapObject(MapObject mapObject) {
        return this.a.a(mapObject);
    }

    public boolean removeMapObject(MapObject mapObject) {
        return this.a.b(mapObject);
    }

    public boolean removeAllMapObjects() {
        return this.a.a();
    }

    public List<MapObject> getAllMapObjects() {
        return this.a.b();
    }

    static {
        MapContainerImpl.a(new am<MapContainer, MapContainerImpl>() {
            public MapContainer a(MapContainerImpl mapContainerImpl) {
                return new MapContainer(mapContainerImpl);
            }
        });
    }
}
