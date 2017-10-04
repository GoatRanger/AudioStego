package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataOsdSetSDRImageTransmMode extends n implements e {
    private static DataOsdSetSDRImageTransmMode a = null;
    private SDRImageTransmMode b = SDRImageTransmMode.SMOOTH;

    public enum SDRImageTransmMode {
        SMOOTH(0),
        HD(1),
        NONE(10);
        
        private int d;

        private SDRImageTransmMode(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public static SDRImageTransmMode find(int i) {
            SDRImageTransmMode[] values = values();
            int length = values.length;
            for (int i2 = 0; i2 != length; i2++) {
                if (i == values[i2].a()) {
                    return values[i2];
                }
            }
            return NONE;
        }
    }

    public static synchronized DataOsdSetSDRImageTransmMode getInstance() {
        DataOsdSetSDRImageTransmMode dataOsdSetSDRImageTransmMode;
        synchronized (DataOsdSetSDRImageTransmMode.class) {
            if (a == null) {
                a = new DataOsdSetSDRImageTransmMode();
            }
            dataOsdSetSDRImageTransmMode = a;
        }
        return dataOsdSetSDRImageTransmMode;
    }

    public DataOsdSetSDRImageTransmMode a(SDRImageTransmMode sDRImageTransmMode) {
        this.b = sDRImageTransmMode;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OFDM.value();
        cVar.g = 0;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.SetSDRImageTransmissionMode.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b.a();
    }
}
