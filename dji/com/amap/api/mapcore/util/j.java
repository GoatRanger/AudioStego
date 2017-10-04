package com.amap.api.mapcore.util;

class j implements Runnable {
    final /* synthetic */ c a;

    j(c cVar) {
        this.a = cVar;
    }

    public synchronized void run() {
        if (this.a.ba) {
            this.a.aZ = true;
            this.a.ba = false;
        }
    }
}
