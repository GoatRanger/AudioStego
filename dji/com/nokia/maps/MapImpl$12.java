package com.nokia.maps;

import com.here.android.mpa.mapping.Map$Animation;

class MapImpl$12 implements Runnable {
    final /* synthetic */ GeoCoordinateImpl a;
    final /* synthetic */ Map$Animation b;
    final /* synthetic */ double c;
    final /* synthetic */ float d;
    final /* synthetic */ float e;
    final /* synthetic */ MapImpl f;

    MapImpl$12(MapImpl mapImpl, GeoCoordinateImpl geoCoordinateImpl, Map$Animation map$Animation, double d, float f, float f2) {
        this.f = mapImpl;
        this.a = geoCoordinateImpl;
        this.b = map$Animation;
        this.c = d;
        this.d = f;
        this.e = f2;
    }

    public void run() {
        MapImpl.d(this.f);
        MapImpl.e(this.f);
        MapImpl.a(this.f, this.a.a(), this.a.b(), MapImpl.a(this.b), this.c, this.d, this.e);
    }
}
