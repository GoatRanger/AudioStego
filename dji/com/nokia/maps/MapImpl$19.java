package com.nokia.maps;

import com.here.android.mpa.mapping.Map$Animation;

class MapImpl$19 implements Runnable {
    final /* synthetic */ GeoBoundingBoxImpl a;
    final /* synthetic */ Map$Animation b;
    final /* synthetic */ float c;
    final /* synthetic */ float d;
    final /* synthetic */ MapImpl e;

    MapImpl$19(MapImpl mapImpl, GeoBoundingBoxImpl geoBoundingBoxImpl, Map$Animation map$Animation, float f, float f2) {
        this.e = mapImpl;
        this.a = geoBoundingBoxImpl;
        this.b = map$Animation;
        this.c = f;
        this.d = f2;
    }

    public void run() {
        MapImpl.d(this.e);
        MapImpl.e(this.e);
        MapImpl.a(this.e, this.a, MapImpl.a(this.b), this.c, this.d);
    }
}
