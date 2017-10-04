package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.mapping.MapBuildingGroup;
import com.here.android.mpa.mapping.MapBuildingLayer;
import com.here.android.mpa.mapping.MapBuildingLayer.BuildingSearchResult.ErrorCode;
import com.here.android.mpa.mapping.MapBuildingLayer.DefaultBuildingColor;
import com.here.android.mpa.mapping.MapBuildingLayer.DefaultBuildingGroups;
import com.here.android.mpa.mapping.MapBuildingObject;
import com.nokia.maps.IdentifierImpl.a;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.List;

@Online
public class MapBuildingLayerImpl extends BaseNativeObject {
    private static k<MapBuildingLayer, MapBuildingLayerImpl> a;
    private static am<MapBuildingLayer, MapBuildingLayerImpl> b;
    private MapImpl c = null;
    @OnlineNative
    private int m_buildingSearchCode;

    private native void destroyNative();

    private native MapBuildingObjectImpl getBuilding2(String str, GeoCoordinateImpl geoCoordinateImpl);

    private native MapBuildingObjectImpl[] getBuildings(GeoBoundingBoxImpl geoBoundingBoxImpl);

    private native MapBuildingGroupImpl getDefaultBuildingGroupNative(int i);

    private native MapBuildingGroupImpl getNewBuildingGroupNative(int i);

    private native void releaseBuildingGroupNative(MapBuildingGroupImpl mapBuildingGroupImpl);

    public native MapBuildingObjectImpl getBuilding(String str);

    native float[] getBuildingScale(IdentifierImpl[] identifierImplArr);

    native float[] getBuildingTransparency(IdentifierImpl[] identifierImplArr);

    public static void a(k<MapBuildingLayer, MapBuildingLayerImpl> kVar, am<MapBuildingLayer, MapBuildingLayerImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    static MapBuildingLayer a(MapBuildingLayerImpl mapBuildingLayerImpl) {
        if (mapBuildingLayerImpl != null) {
            return (MapBuildingLayer) b.a(mapBuildingLayerImpl);
        }
        return null;
    }

    static {
        ce.a(MapBuildingLayer.class);
    }

    private MapBuildingLayerImpl() {
    }

    @OnlineNative
    private MapBuildingLayerImpl(int i, MapImpl mapImpl) {
        this.nativeptr = i;
        this.c = mapImpl;
    }

    protected void finalize() {
        destroyNative();
    }

    public MapBuildingObject a(Identifier identifier, GeoCoordinate geoCoordinate) {
        if (identifier == null && geoCoordinate == null) {
            throw new IllegalArgumentException("Both identifier and positionHint are null. One must be valid");
        }
        String str = null;
        if (identifier != null) {
            IdentifierImpl a = IdentifierImpl.a(identifier);
            if (a.a() == a.STRING_ID) {
                str = a.b();
            }
        }
        return MapBuildingObjectImpl.a(getBuilding2(str, GeoCoordinateImpl.get(geoCoordinate)));
    }

    public MapBuildingObject a(Identifier identifier) {
        MapBuildingObjectImpl mapBuildingObjectImpl = null;
        IdentifierImpl a = IdentifierImpl.a(identifier);
        if (a.a() == a.STRING_ID) {
            mapBuildingObjectImpl = getBuilding(a.b());
        }
        return MapBuildingObjectImpl.a(mapBuildingObjectImpl);
    }

    public MapBuildingGroup a(DefaultBuildingGroups defaultBuildingGroups) {
        MapBuildingGroupImpl defaultBuildingGroupNative = getDefaultBuildingGroupNative(defaultBuildingGroups.ordinal());
        if (defaultBuildingGroupNative != null) {
            defaultBuildingGroupNative.a(this.c);
        }
        return MapBuildingGroupImpl.a(defaultBuildingGroupNative);
    }

    public List<MapBuildingObject> a(GeoBoundingBox geoBoundingBox) {
        if (geoBoundingBox.isEmpty()) {
            throw new IllegalArgumentException("Invalid bounding box");
        }
        GeoBoundingBoxImpl geoBoundingBoxImpl = GeoBoundingBoxImpl.get(geoBoundingBox);
        this.m_buildingSearchCode = ErrorCode.UNKNOWN.ordinal();
        MapBuildingObjectImpl[] buildings = getBuildings(geoBoundingBoxImpl);
        if (buildings != null) {
            return MapBuildingObjectImpl.a(buildings);
        }
        return new ArrayList();
    }

    public int a() {
        return this.m_buildingSearchCode;
    }

    public MapBuildingGroup b() {
        return a(DefaultBuildingColor.SELECTED);
    }

    public MapBuildingGroup a(DefaultBuildingColor defaultBuildingColor) {
        MapBuildingGroupImpl newBuildingGroupNative = getNewBuildingGroupNative(defaultBuildingColor.ordinal());
        if (newBuildingGroupNative != null) {
            newBuildingGroupNative.a(this.c);
            newBuildingGroupNative.a(true);
        }
        return MapBuildingGroupImpl.a(newBuildingGroupNative);
    }

    public void a(MapBuildingGroup mapBuildingGroup) {
        if (mapBuildingGroup != null) {
            MapBuildingGroupImpl a = MapBuildingGroupImpl.a(mapBuildingGroup);
            if (a == null || !a.a()) {
                throw new IllegalArgumentException("Default MapBuildingGroups cannot be released.");
            }
            releaseBuildingGroupNative(a);
        }
    }

    public float[] a(List<Identifier> list) {
        return getBuildingTransparency(IdentifierImpl.a((List) list));
    }

    public float[] b(List<Identifier> list) {
        return getBuildingScale(IdentifierImpl.a((List) list));
    }
}
