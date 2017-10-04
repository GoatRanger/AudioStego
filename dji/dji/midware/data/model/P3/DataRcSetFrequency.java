package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcSetFrequency extends n implements e {
    private static DataRcSetFrequency a = null;
    private FreqMode b;

    public enum FreqCcode {
        Idle(0),
        Progress(1),
        Finish(2),
        OTHER(6);
        
        private int e;

        private FreqCcode(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static FreqCcode find(int i) {
            FreqCcode freqCcode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return freqCcode;
        }
    }

    public enum FreqMode {
        Current(0),
        Enter(1),
        Cancel(2),
        OTHER(6);
        
        private int e;

        private FreqMode(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static FreqMode find(int i) {
            FreqMode freqMode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return freqMode;
        }
    }

    public static synchronized DataRcSetFrequency getInstance() {
        DataRcSetFrequency dataRcSetFrequency;
        synchronized (DataRcSetFrequency.class) {
            if (a == null) {
                a = new DataRcSetFrequency();
            }
            dataRcSetFrequency = a;
        }
        return dataRcSetFrequency;
    }

    public DataRcSetFrequency a(FreqMode freqMode) {
        this.b = freqMode;
        return this;
    }

    public FreqCcode a() {
        return FreqCcode.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b.a();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetFrequency.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
