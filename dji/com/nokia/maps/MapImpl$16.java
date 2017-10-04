package com.nokia.maps;

import com.nokia.maps.MapGestureHandler.MapUserInteractionListener;

class MapImpl$16 implements MapUserInteractionListener {
    final /* synthetic */ MapImpl a;

    MapImpl$16(MapImpl mapImpl) {
        this.a = mapImpl;
    }

    public void a(boolean z) {
        if (z) {
            MapImpl.p(this.a).incrementAndGet();
        } else {
            MapImpl.p(this.a).decrementAndGet();
        }
    }
}
