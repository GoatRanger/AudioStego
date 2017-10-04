package com.nokia.maps;

import com.here.android.mpa.mapping.MapOverlay;
import java.util.Iterator;

class MapImpl$10 implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ MapOverlay b;
    final /* synthetic */ MapImpl c;

    MapImpl$10(MapImpl mapImpl, boolean z, MapOverlay mapOverlay) {
        this.c = mapImpl;
        this.a = z;
        this.b = mapOverlay;
    }

    public void run() {
        Iterator it = MapImpl.m(this.c).iterator();
        while (it.hasNext()) {
            MapImpl$h mapImpl$h = (MapImpl$h) it.next();
            try {
                if (this.a) {
                    mapImpl$h.a(this.b);
                } else {
                    mapImpl$h.b(this.b);
                }
            } catch (Exception e) {
            }
        }
    }
}
