package com.nokia.maps;

import com.here.android.mpa.mapping.Map$Animation;

class MapImpl$18 implements Runnable {
    final /* synthetic */ GeoBoundingBoxImpl a;
    final /* synthetic */ Map$Animation b;
    final /* synthetic */ float c;
    final /* synthetic */ MapImpl d;

    MapImpl$18(MapImpl mapImpl, GeoBoundingBoxImpl geoBoundingBoxImpl, Map$Animation map$Animation, float f) {
        this.d = mapImpl;
        this.a = geoBoundingBoxImpl;
        this.b = map$Animation;
        this.c = f;
    }

    public void run() {
        MapImpl.d(this.d);
        MapImpl.e(this.d);
        MapImpl.a(this.d, this.a, MapImpl.a(this.b), this.c);
    }
}
