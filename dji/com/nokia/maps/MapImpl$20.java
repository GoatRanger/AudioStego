package com.nokia.maps;

import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.Map$Animation;

class MapImpl$20 implements Runnable {
    final /* synthetic */ GeoBoundingBoxImpl a;
    final /* synthetic */ ViewRect b;
    final /* synthetic */ Map$Animation c;
    final /* synthetic */ float d;
    final /* synthetic */ MapImpl e;

    MapImpl$20(MapImpl mapImpl, GeoBoundingBoxImpl geoBoundingBoxImpl, ViewRect viewRect, Map$Animation map$Animation, float f) {
        this.e = mapImpl;
        this.a = geoBoundingBoxImpl;
        this.b = viewRect;
        this.c = map$Animation;
        this.d = f;
    }

    public void run() {
        MapImpl.d(this.e);
        MapImpl.e(this.e);
        MapImpl.a(this.e, this.a, this.b.getX(), this.b.getY(), this.b.getWidth(), this.b.getHeight(), MapImpl.a(this.c), this.d);
    }
}
