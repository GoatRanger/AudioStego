package com.nokia.maps;

import android.content.Context;
import android.content.Intent;
import com.nokia.maps.MapsEngine.h;
import java.lang.ref.WeakReference;

class MapImpl$c implements h {
    private final WeakReference<MapImpl> a;

    public MapImpl$c(MapImpl mapImpl) {
        this.a = new WeakReference(mapImpl);
    }

    public void a(Context context, Intent intent) {
        MapImpl mapImpl = (MapImpl) this.a.get();
        if (mapImpl != null) {
            mapImpl.j();
        }
    }
}
