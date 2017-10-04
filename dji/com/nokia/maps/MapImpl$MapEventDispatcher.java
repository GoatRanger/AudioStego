package com.nokia.maps;

import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.lang.ref.WeakReference;

@Online
class MapImpl$MapEventDispatcher extends Thread {
    WeakReference<MapImpl> a;
    @OnlineNative
    int nativeptr;

    private native void getEventNative(MapImpl mapImpl);

    private native void killEventNative();

    private native void runEventNative();

    MapImpl$MapEventDispatcher(MapImpl mapImpl) {
        this.a = new WeakReference(mapImpl);
        this.nativeptr = mapImpl.nativeptr;
        setName("MapEventDispatcher");
        setPriority(1);
        runEventNative();
    }

    public void a() {
        killEventNative();
        try {
            join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean b() {
        return this.a.get() != null;
    }

    public void run() {
        while (b()) {
            runEventNative();
            if (b()) {
                getEventNative((MapImpl) this.a.get());
            }
        }
    }
}
