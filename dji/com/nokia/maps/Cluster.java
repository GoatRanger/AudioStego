package com.nokia.maps;

import com.here.android.mpa.mapping.MapMarker;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@HybridPlus
final class Cluster extends BaseNativeObject {
    private static final String a = Cluster.class.getSimpleName();
    private Map<Integer, MapMarker> b;

    public native GeoBoundingBoxImpl getBoundBox();

    public native int[] getMarkersIds();

    public native boolean representedBy(int i);

    @HybridPlusNative
    private Cluster(int i) {
        this.nativeptr = i;
    }

    public Collection<MapMarker> a() {
        int i = 0;
        int[] markersIds = getMarkersIds();
        Collection<MapMarker> arrayList = new ArrayList(markersIds.length);
        bj.e(a, "@@@@ lenght " + markersIds.length, new Object[0]);
        while (i < markersIds.length) {
            arrayList.add(this.b.get(Integer.valueOf(markersIds[i])));
            i++;
        }
        return arrayList;
    }

    void a(Map<Integer, MapMarker> map) {
        this.b = map;
    }

    public int hashCode() {
        return this.nativeptr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.nativeptr != ((Cluster) obj).nativeptr) {
            return false;
        }
        return true;
    }

    public String toString() {
        return getClass().getSimpleName() + "#" + hashCode();
    }
}
