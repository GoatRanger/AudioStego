package com.amap.api.mapcore.util;

import java.util.concurrent.Callable;

class fl implements Callable<Void> {
    final /* synthetic */ fk a;

    fl(fk fkVar) {
        this.a = fkVar;
    }

    public /* synthetic */ Object call() throws Exception {
        return a();
    }

    public Void a() throws Exception {
        synchronized (this.a) {
            if (this.a.k == null) {
            } else {
                this.a.j();
                if (this.a.h()) {
                    this.a.g();
                    this.a.m = 0;
                }
            }
        }
        return null;
    }
}
