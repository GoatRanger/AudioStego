package com.here.android.mpa.venues3d;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@HybridPlus
public final class Level extends BaseNativeObject {
    Hashtable<String, List<Space>> a = new Hashtable();
    private List<OuterArea> b = null;
    private List<Space> c = null;
    private List<Space> d = null;

    private native List<OuterArea> getOuterAreasNative();

    private native List<Space> getSortedSpacesByCategoryNative(String str);

    private native List<Space> getSortedSpacesNative(boolean z);

    public native GeoCoordinate getCenter();

    public native int getFloorNumber();

    public native String getFloorSynonym();

    @HybridPlusNative
    private Level(int i) {
        this.nativeptr = i;
    }

    public List<OuterArea> getOuterAreas() {
        if (this.b == null) {
            this.b = getOuterAreasNative();
        }
        return this.b != null ? this.b : new ArrayList();
    }

    public List<Space> getSortedSpacesWithFacilities() {
        if (this.d == null) {
            this.d = getSortedSpacesNative(true);
        }
        return this.d != null ? this.d : new ArrayList();
    }

    public List<Space> getSortedSpaces() {
        if (this.c == null) {
            this.c = getSortedSpacesNative(false);
        }
        return this.c != null ? this.c : new ArrayList();
    }

    public List<Space> getSortedSpacesByCategory(String str) {
        List<Space> list = null;
        if (str != null) {
            if (this.a.containsKey(str)) {
                list = (List) this.a.get(str);
            } else {
                list = getSortedSpacesByCategoryNative(str);
                if (list != null) {
                    this.a.put(str, list);
                }
            }
        }
        if (list != null) {
            return list;
        }
        return new ArrayList();
    }
}
