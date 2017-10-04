package com.nokia.maps;

import com.here.android.mpa.mapping.MapState;

class MapImpl$b implements Runnable {
    final /* synthetic */ MapImpl a;
    private MapState b = null;

    public MapImpl$b(MapImpl mapImpl) {
        this.a = mapImpl;
    }

    public void run() {
        MapState mapState = this.a.getMapState();
        boolean z = (this.b != null && MapImpl.a(this.a, this.b, mapState)) || MapImpl.a(this.a);
        if (z) {
            MapImpl.a(this.a, mapState);
            MapImpl.f(this.a).set(false);
            this.b = null;
            return;
        }
        this.b = mapState;
        this.a.a(MapImpl.h(this.a), 200);
    }
}
