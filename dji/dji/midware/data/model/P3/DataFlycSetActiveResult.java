package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataFlycSetActiveResult extends n implements e {
    private static DataFlycSetActiveResult a = null;
    private DJIActivationState b;
    private int c;
    private int d;
    private String e;

    public enum DJIActivationState {
        Success(0),
        NoNetwork(1),
        InvalidId(2),
        FailedForNet(3),
        OTHER(100);
        
        private int f;

        private DJIActivationState(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static DJIActivationState find(int i) {
            DJIActivationState dJIActivationState = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return dJIActivationState;
        }
    }

    public static synchronized DataFlycSetActiveResult getInstance() {
        DataFlycSetActiveResult dataFlycSetActiveResult;
        synchronized (DataFlycSetActiveResult.class) {
            if (a == null) {
                a = new DataFlycSetActiveResult();
            }
            dataFlycSetActiveResult = a;
        }
        return dataFlycSetActiveResult;
    }

    public DataFlycSetActiveResult a(DJIActivationState dJIActivationState) {
        this.b = dJIActivationState;
        return this;
    }

    public DataFlycSetActiveResult a(int i) {
        this.c = i;
        return this;
    }

    public DataFlycSetActiveResult b(int i) {
        this.d = i;
        return this;
    }

    public DataFlycSetActiveResult a(String str) {
        this.e = str;
        return this;
    }

    protected void doPack() {
        int i = 32;
        this._sendData = new byte[44];
        System.arraycopy(c.a(this.b.a()), 0, this._sendData, 0, 4);
        System.arraycopy(c.a(this.c), 0, this._sendData, 4, 4);
        System.arraycopy(c.a(this.d), 0, this._sendData, 8, 4);
        if (this.e.length() > 0) {
            Object c = c.c(this.e);
            Object obj = this._sendData;
            if (c.length < 32) {
                i = c.length;
            }
            System.arraycopy(c, 0, obj, 12, i);
        }
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.R.a();
        start(cVar, dVar);
    }
}
