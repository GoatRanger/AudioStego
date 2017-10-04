package com.amap.api.mapcore.util;

class ag implements Runnable {
    final /* synthetic */ ae a;

    ag(ae aeVar) {
        this.a = aeVar;
    }

    public void run() {
        try {
            this.a.a.redrawInfoWindow();
        } catch (Throwable th) {
            ee.a(th, "MapOverlayImageView", "redrawInfoWindow post");
            th.printStackTrace();
        }
    }
}
