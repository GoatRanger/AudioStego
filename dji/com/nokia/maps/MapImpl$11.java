package com.nokia.maps;

class MapImpl$11 implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ MapImpl b;

    MapImpl$11(MapImpl mapImpl, boolean z) {
        this.b = mapImpl;
        this.a = z;
    }

    public void run() {
        this.b.setFadingAnimations(this.a);
    }
}
