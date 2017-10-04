package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetOpticsZoomMode extends n implements e {
    private static final String a = "DataCameraSetOpticsZoomMode";
    private static DataCameraSetOpticsZoomMode b = null;
    private OpticsZommMode c = OpticsZommMode.OTHER;
    private ZoomSpeed d = ZoomSpeed.OTHER;
    private int e = -1;
    private int f = -1;

    public enum OpticsZommMode {
        CONTINUOUS(1),
        SETZOOM(2),
        STOPZOOM(255),
        OTHER(100);
        
        private int e;

        private OpticsZommMode(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static OpticsZommMode find(int i) {
            OpticsZommMode opticsZommMode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return opticsZommMode;
        }
    }

    public enum ZoomSpeed {
        SLOWEST(72),
        SLOW(73),
        MIDSLOW(74),
        MID(75),
        MIDFAST(76),
        FAST(77),
        FASTEST(78),
        OTHER(100);
        
        private int i;

        private ZoomSpeed(int i) {
            this.i = i;
        }

        public int a() {
            return this.i;
        }

        public boolean a(int i) {
            return this.i == i;
        }

        public static ZoomSpeed find(int i) {
            ZoomSpeed zoomSpeed = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return zoomSpeed;
        }
    }

    public static synchronized DataCameraSetOpticsZoomMode getInstance() {
        DataCameraSetOpticsZoomMode dataCameraSetOpticsZoomMode;
        synchronized (DataCameraSetOpticsZoomMode.class) {
            if (b == null) {
                b = new DataCameraSetOpticsZoomMode();
            }
            dataCameraSetOpticsZoomMode = b;
        }
        return dataCameraSetOpticsZoomMode;
    }

    public DataCameraSetOpticsZoomMode a(OpticsZommMode opticsZommMode, ZoomSpeed zoomSpeed, int i, int i2) {
        this.c = opticsZommMode;
        this.d = zoomSpeed;
        this.e = i;
        this.f = i2;
        return this;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int b() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int c() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int d() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.CAMERA.a();
        cVar.n = dji.midware.data.config.P3.b.a.SetOpticsZoom.a();
        cVar.w = 1;
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[4];
        this._sendData[0] = (byte) this.c.a();
        this._sendData[1] = (byte) this.d.a();
        this._sendData[2] = (byte) this.e;
        this._sendData[3] = (byte) this.f;
    }
}
