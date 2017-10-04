package com.autonavi.amap.mapcore;

class a implements Runnable {
    public BaseMapLoader a = null;

    public a(BaseMapLoader baseMapLoader) {
        this.a = baseMapLoader;
    }

    public void run() {
        try {
            this.a.doRequest();
        } catch (Throwable th) {
        }
    }
}
