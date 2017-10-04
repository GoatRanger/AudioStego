package com.amap.api.mapcore.util;

class g implements Runnable {
    final /* synthetic */ c a;

    g(c cVar) {
        this.a = cVar;
    }

    public void run() {
        if (this.a.aj != null) {
            this.a.aS = true;
            if (this.a.al != null) {
                this.a.al.setVisible(false);
            }
        }
    }
}
