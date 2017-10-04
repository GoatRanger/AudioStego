package com.amap.api.mapcore.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

class w implements Runnable {
    final /* synthetic */ v a;

    w(v vVar) {
        this.a = vVar;
    }

    public synchronized void run() {
        try {
            synchronized (this.a) {
                Collection arrayList = new ArrayList(this.a.d);
                Collections.sort(arrayList, this.a.b);
                this.a.d = new CopyOnWriteArrayList(arrayList);
            }
        } catch (Throwable th) {
            ee.a(th, "MapOverlayImageView", "changeOverlayIndex");
        }
    }
}
