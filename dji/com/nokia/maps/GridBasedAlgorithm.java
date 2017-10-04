package com.nokia.maps;

import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.nokia.maps.annotation.Online;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Online
class GridBasedAlgorithm extends BaseNativeObject implements af {
    private static final String a = GridBasedAlgorithm.class.getSimpleName();

    private native Cluster[] cluster(Object[] objArr, double d, boolean z);

    private native void createNative();

    private native void deleteNative();

    private native void setActiveZoomRangeNative(double d, double d2);

    private native void setGridSizeNative(int i);

    GridBasedAlgorithm() {
        createNative();
    }

    public Set<Cluster> a(Collection<MapMarker> collection, double d, boolean z) {
        bj.e(a, "clustering for zoom level " + d + "...", new Object[0]);
        MapObjectImpl[] mapObjectImplArr = new MapObjectImpl[collection.size()];
        int i = 0;
        for (MapObject d2 : collection) {
            mapObjectImplArr[i] = MapObjectImpl.d(d2);
            i++;
        }
        Cluster[] cluster = cluster(mapObjectImplArr, d, z);
        bj.e(a, cluster.length + " clusters created for zoom level " + d + "; #markers=" + mapObjectImplArr.length, new Object[0]);
        return new HashSet(Arrays.asList(cluster));
    }

    public String toString() {
        return getClass().getSimpleName() + "#" + hashCode();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.nativeptr != 0) {
            deleteNative();
        }
    }
}
