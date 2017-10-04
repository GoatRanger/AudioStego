package com.nokia.maps;

class MapImpl$22 implements Runnable {
    final /* synthetic */ float a;
    final /* synthetic */ float b;
    final /* synthetic */ float c;
    final /* synthetic */ float d;
    final /* synthetic */ float e;
    final /* synthetic */ float f;
    final /* synthetic */ MapImpl g;

    MapImpl$22(MapImpl mapImpl, float f, float f2, float f3, float f4, float f5, float f6) {
        this.g = mapImpl;
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = f5;
        this.f = f6;
    }

    public void run() {
        MapImpl.a(this.g, (int) this.a, (int) this.b, (int) this.c, (int) this.d, this.e, this.f);
        MapImpl.a(this.g, null);
    }
}
