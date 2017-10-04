package com.nokia.maps;

class MapImpl$1 implements Runnable {
    final /* synthetic */ MapImpl a;

    MapImpl$1(MapImpl mapImpl) {
        this.a = mapImpl;
    }

    public void run() {
        if (MapImpl.a(this.a)) {
            MapImpl.b(this.a);
            this.a.a(MapImpl.c(this.a));
        }
    }
}
