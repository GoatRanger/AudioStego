package com.amap.api.mapcore.util;

import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import java.util.concurrent.CopyOnWriteArrayList;

class ad {
    c a;
    private CopyOnWriteArrayList<CameraUpdateFactoryDelegate> b = new CopyOnWriteArrayList();
    private CopyOnWriteArrayList<ac> c = new CopyOnWriteArrayList();

    public ad(c cVar) {
        this.a = cVar;
    }

    public synchronized void a(ac acVar) {
        this.a.setRunLowFrame(false);
        this.c.add(acVar);
        this.a.setRunLowFrame(false);
    }

    public ac a() {
        if (b() == 0) {
            return null;
        }
        ac acVar = (ac) this.c.get(0);
        this.c.remove(acVar);
        return acVar;
    }

    public synchronized int b() {
        return this.c.size();
    }

    public void a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        this.a.setRunLowFrame(false);
        this.b.add(cameraUpdateFactoryDelegate);
        this.a.setRunLowFrame(false);
    }

    public CameraUpdateFactoryDelegate c() {
        if (d() == 0) {
            return null;
        }
        CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate = (CameraUpdateFactoryDelegate) this.b.get(0);
        this.b.remove(cameraUpdateFactoryDelegate);
        this.a.setRunLowFrame(false);
        return cameraUpdateFactoryDelegate;
    }

    public int d() {
        return this.b.size();
    }
}
