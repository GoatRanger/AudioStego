package com.here.android.mpa.mapping;

import com.here.android.mpa.common.Identifier;
import com.nokia.maps.MapBuildingGroupImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.EnumSet;
import java.util.List;

@Online
public final class MapBuildingGroup {
    private MapBuildingGroupImpl a;

    @Online
    public enum BuildingFace {
        ROOF(1),
        WALLTOP(2),
        WALLBOTTOM(4),
        OUTLINE(8),
        LANDMARKS(16);
        
        private int a;

        private BuildingFace(int i) {
            this.a = i;
        }

        public int mask() {
            return this.a;
        }
    }

    private MapBuildingGroup() {
    }

    private MapBuildingGroup(MapBuildingGroupImpl mapBuildingGroupImpl) {
        this.a = mapBuildingGroupImpl;
    }

    public int getBuildingCount() {
        return this.a.b();
    }

    public boolean addBuilding(Identifier identifier) {
        return this.a.a(identifier);
    }

    public boolean addBuildings(List<Identifier> list) {
        return this.a.a((List) list);
    }

    public boolean removeBuilding(Identifier identifier) {
        return this.a.b(identifier);
    }

    public boolean removeBuildings(List<Identifier> list) {
        return this.a.b((List) list);
    }

    public boolean removeAllBuildings() {
        return this.a.c();
    }

    public void setColor(int i, EnumSet<BuildingFace> enumSet) {
        this.a.a(i, (EnumSet) enumSet);
    }

    public int getColor(BuildingFace buildingFace) {
        return this.a.a(buildingFace);
    }

    public void setVerticalScale(float f) {
        this.a.a(f);
    }

    public float getVerticalScale() {
        return this.a.getVerticalScale();
    }

    public Identifier convertStringToIdentifier(String str) {
        return this.a.c(str);
    }

    static {
        MapBuildingGroupImpl.a(new k<MapBuildingGroup, MapBuildingGroupImpl>() {
            public MapBuildingGroupImpl a(MapBuildingGroup mapBuildingGroup) {
                return mapBuildingGroup.a;
            }
        }, new am<MapBuildingGroup, MapBuildingGroupImpl>() {
            public MapBuildingGroup a(MapBuildingGroupImpl mapBuildingGroupImpl) {
                if (mapBuildingGroupImpl != null) {
                    return new MapBuildingGroup(mapBuildingGroupImpl);
                }
                return null;
            }
        });
    }
}
