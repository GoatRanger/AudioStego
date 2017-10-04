package com.nokia.maps;

import com.here.android.mpa.mapping.Map$OnSchemeChangedListener;
import java.util.Iterator;

class MapImpl$9 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ MapImpl b;

    MapImpl$9(MapImpl mapImpl, String str) {
        this.b = mapImpl;
        this.a = str;
    }

    public void run() {
        Iterator it = MapImpl.l(this.b).iterator();
        while (it.hasNext()) {
            try {
                ((Map$OnSchemeChangedListener) it.next()).onMapSchemeChanged(this.a);
            } catch (Exception e) {
            }
        }
    }
}
