package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.mapping.MapBuildingObject;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.List;

@Online
public class MapBuildingObjectImpl extends MapProxyObjectImpl {
    private static am<MapBuildingObject, MapBuildingObjectImpl> a;

    private native IdentifierImpl getIdentifierNative();

    private native GeoCoordinateImpl getPositionNative();

    public native float getHeight();

    public native String getLROId();

    public native String getPlaceName();

    public static void a(am<MapBuildingObject, MapBuildingObjectImpl> amVar) {
        a = amVar;
    }

    static MapBuildingObject a(MapBuildingObjectImpl mapBuildingObjectImpl) {
        if (mapBuildingObjectImpl != null) {
            return (MapBuildingObject) a.a(mapBuildingObjectImpl);
        }
        return null;
    }

    static List<MapBuildingObject> a(MapBuildingObjectImpl[] mapBuildingObjectImplArr) {
        List arrayList = new ArrayList(mapBuildingObjectImplArr.length);
        for (MapBuildingObjectImpl a : mapBuildingObjectImplArr) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    static {
        ce.a(MapBuildingObject.class);
    }

    private MapBuildingObjectImpl() {
    }

    @OnlineNative
    private MapBuildingObjectImpl(int i) {
        super(i);
    }

    public Identifier b() {
        return IdentifierImpl.a(getIdentifierNative());
    }

    public GeoCoordinate c() {
        return GeoCoordinateImpl.create(getPositionNative());
    }
}
