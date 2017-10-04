package com.nokia.maps;

import com.here.android.mpa.mapping.Map$Animation;

class MapImpl$21 implements Runnable {
    final /* synthetic */ GeoBoundingBoxImpl a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ Map$Animation d;
    final /* synthetic */ float e;
    final /* synthetic */ MapImpl f;

    MapImpl$21(MapImpl mapImpl, GeoBoundingBoxImpl geoBoundingBoxImpl, int i, int i2, Map$Animation map$Animation, float f) {
        this.f = mapImpl;
        this.a = geoBoundingBoxImpl;
        this.b = i;
        this.c = i2;
        this.d = map$Animation;
        this.e = f;
    }

    public void run() {
        MapImpl.d(this.f);
        MapImpl.e(this.f);
        MapImpl.a(this.f, this.a, this.b, this.c, MapImpl.a(this.d), this.e);
    }
}
