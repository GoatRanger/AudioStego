package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.nokia.maps.MapBuildingLayerImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.HashMap;
import java.util.List;

@Online
public final class MapBuildingLayer {
    private MapBuildingLayerImpl a;

    @Online
    public static final class BuildingSearchResult {
        private ErrorCode a;
        private List<MapBuildingObject> b;

        @Online
        public enum ErrorCode {
            NONE,
            NEEDS_DATA,
            AREA_TOO_LARGE,
            UNKNOWN
        }

        public ErrorCode getResultCode() {
            return this.a;
        }

        public List<MapBuildingObject> getResults() {
            return this.b;
        }

        BuildingSearchResult(ErrorCode errorCode, List<MapBuildingObject> list) {
            this.b = list;
            this.a = errorCode;
        }
    }

    @Online
    public enum DefaultBuildingColor {
        SELECTED,
        HIGHLIGHT,
        HIGHLIGHT2,
        HIGHLIGHT3,
        HIGHLIGHT4,
        HIGHLIGHT5
    }

    @Online
    public enum DefaultBuildingGroups {
        NORMAL_BUILDINGS,
        IMPORTANT_BUILDINGS
    }

    private MapBuildingLayer() {
    }

    private MapBuildingLayer(MapBuildingLayerImpl mapBuildingLayerImpl) {
        this.a = mapBuildingLayerImpl;
    }

    public MapBuildingObject getBuilding(Identifier identifier, GeoCoordinate geoCoordinate) {
        return this.a.a(identifier, geoCoordinate);
    }

    public MapBuildingObject getBuilding(Identifier identifier) {
        return this.a.a(identifier);
    }

    public BuildingSearchResult getBuildingsInside(GeoBoundingBox geoBoundingBox) {
        return new BuildingSearchResult(ErrorCode.values()[this.a.a()], this.a.a(geoBoundingBox));
    }

    public MapBuildingGroup getDefaultBuildingGroup(DefaultBuildingGroups defaultBuildingGroups) {
        return this.a.a(defaultBuildingGroups);
    }

    public MapBuildingGroup createNewBuildingGroup() {
        return this.a.b();
    }

    public MapBuildingGroup createNewBuildingGroup(DefaultBuildingColor defaultBuildingColor) {
        return this.a.a(defaultBuildingColor);
    }

    public void releaseBuildingGroup(MapBuildingGroup mapBuildingGroup) {
        this.a.a(mapBuildingGroup);
    }

    public HashMap<Identifier, Float> getTransparency(List<Identifier> list) {
        HashMap<Identifier, Float> hashMap = new HashMap();
        float[] a = this.a.a((List) list);
        if (a != null) {
            for (int i = 0; i < list.size(); i++) {
                hashMap.put(list.get(i), Float.valueOf(a[i]));
            }
        }
        return hashMap;
    }

    public HashMap<Identifier, Float> getVerticalScale(List<Identifier> list) {
        HashMap<Identifier, Float> hashMap = new HashMap();
        float[] b = this.a.b(list);
        if (b != null) {
            for (int i = 0; i < list.size(); i++) {
                hashMap.put(list.get(i), Float.valueOf(b[i]));
            }
        }
        return hashMap;
    }

    static {
        MapBuildingLayerImpl.a(new k<MapBuildingLayer, MapBuildingLayerImpl>() {
            public MapBuildingLayerImpl a(MapBuildingLayer mapBuildingLayer) {
                return mapBuildingLayer.a;
            }
        }, new am<MapBuildingLayer, MapBuildingLayerImpl>() {
            public MapBuildingLayer a(MapBuildingLayerImpl mapBuildingLayerImpl) {
                if (mapBuildingLayerImpl != null) {
                    return new MapBuildingLayer(mapBuildingLayerImpl);
                }
                return null;
            }
        });
    }
}
