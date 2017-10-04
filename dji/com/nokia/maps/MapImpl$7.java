package com.nokia.maps;

import com.here.android.mpa.mapping.Map$OnTransformListener;
import java.util.Iterator;

class MapImpl$7 implements Runnable {
    final /* synthetic */ MapImpl a;

    MapImpl$7(MapImpl mapImpl) {
        this.a = mapImpl;
    }

    public void run() {
        Iterator it = MapImpl.k(this.a).iterator();
        while (it.hasNext()) {
            ((Map$OnTransformListener) it.next()).onMapTransformStart();
        }
    }
}
