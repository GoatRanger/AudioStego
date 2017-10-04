package com.nokia.maps;

import com.here.android.mpa.mapping.Map$OnTransformListener;
import com.here.android.mpa.mapping.MapState;
import java.util.Iterator;

class MapImpl$8 implements Runnable {
    final /* synthetic */ MapState a;
    final /* synthetic */ MapImpl b;

    MapImpl$8(MapImpl mapImpl, MapState mapState) {
        this.b = mapImpl;
        this.a = mapState;
    }

    public void run() {
        Iterator it = MapImpl.k(this.b).iterator();
        while (it.hasNext()) {
            ((Map$OnTransformListener) it.next()).onMapTransformEnd(this.a);
        }
        MapImpl.a(this.b, this.b.getZoomLevel());
    }
}
