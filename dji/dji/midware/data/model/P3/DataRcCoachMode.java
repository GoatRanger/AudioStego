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

public class DataRcCoachMode extends n implements e {
    private static DataRcCoachMode a = null;
    private OptMode b = OptMode.GET;
    private CoachMode c = CoachMode.DISABLE;
    private byte[] d = null;

    public enum CoachMode {
        UNACTIVE(0),
        DISABLE(1),
        ENABLE(2);
        
        private int d;

        private CoachMode(int i) {
            this.d = 0;
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        private boolean a(int i) {
            return this.d == i;
        }

        public static CoachMode find(int i) {
            for (CoachMode coachMode : values()) {
                if (coachMode.a(i)) {
                    return coachMode;
                }
            }
            return DISABLE;
        }
    }

    public enum OptMode {
        GET(0),
        SET(1),
        ACTIVE(2);
        
        private int d;

        private OptMode(int i) {
            this.d = 0;
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        private boolean a(int i) {
            return this.d == i;
        }

        public static OptMode find(int i) {
            for (OptMode optMode : values()) {
                if (optMode.a(i)) {
                    return optMode;
                }
            }
            return GET;
        }
    }

    public static synchronized DataRcCoachMode getInstance() {
        DataRcCoachMode dataRcCoachMode;
        synchronized (DataRcCoachMode.class) {
            if (a == null) {
                a = new DataRcCoachMode();
            }
            dataRcCoachMode = a;
        }
        return dataRcCoachMode;
    }

    public DataRcCoachMode a(OptMode optMode) {
        this.b = optMode;
        return this;
    }

    public DataRcCoachMode a(CoachMode coachMode) {
        this.c = coachMode;
        return this;
    }

    public DataRcCoachMode a(byte[] bArr) {
        this.d = bArr;
        return this;
    }

    public CoachMode a() {
        return CoachMode.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    protected void doPack() {
        int i = 8;
        this._sendData = new byte[10];
        this._sendData[0] = (byte) this.b.a();
        if (this.b == OptMode.SET) {
            this._sendData[1] = (byte) this.c.a();
        } else if (this.b == OptMode.ACTIVE && this.d != null && this.d.length > 0) {
            Object obj = this.d;
            Object obj2 = this._sendData;
            if (this.d.length <= 8) {
                i = this.d.length;
            }
            System.arraycopy(obj, 0, obj2, 2, i);
        }
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.CoachMode.b();
        start(cVar, dVar);
    }
}
