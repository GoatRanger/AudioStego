package com.amap.api.mapcore.util;

class h implements Runnable {
    final /* synthetic */ c a;

    h(c cVar) {
        this.a = cVar;
    }

    public void run() {
        if (this.a.aj != null) {
            this.a.aS = false;
            this.a.aj.setVisibility(8);
        }
    }
}
