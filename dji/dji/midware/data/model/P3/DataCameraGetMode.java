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

public class DataCameraGetMode extends n implements e {
    private static DataCameraGetMode instance = null;

    public enum MODE {
        TAKEPHOTO(0),
        RECORD(1),
        PLAYBACK(2),
        TRANSCODE(3),
        TUNING(4),
        SAVEPOWER(5),
        DOWNLOAD(6),
        NEW_PLAYBACK(7),
        OTHER(100);
        
        private int data;

        private MODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static MODE find(int i) {
            MODE mode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return mode;
        }
    }

    public static synchronized DataCameraGetMode getInstance() {
        DataCameraGetMode dataCameraGetMode;
        synchronized (DataCameraGetMode.class) {
            if (instance == null) {
                instance = new DataCameraGetMode();
            }
            dataCameraGetMode = instance;
        }
        return dataCameraGetMode;
    }

    public MODE getMode() {
        return MODE.find(this._recData[0]);
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.CAMERA.a();
        cVar.n = dji.midware.data.config.P3.b.a.GetMode.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
