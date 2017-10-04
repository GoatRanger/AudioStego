package com.nokia.maps;

class MapImpl$23 implements Runnable {
    final /* synthetic */ MapImpl a;

    MapImpl$23(MapImpl mapImpl) {
        this.a = mapImpl;
    }

    public void run() {
        if (MapImpl.f(this.a).get() && !MapImpl.a(this.a)) {
            this.a.redraw();
            this.a.a(MapImpl.g(this.a), (long) as.a);
        }
    }
}
