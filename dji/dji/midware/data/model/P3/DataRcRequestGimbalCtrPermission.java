package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcRequestGimbalCtrPermission extends n implements e {
    private static DataRcRequestGimbalCtrPermission instance = null;

    public enum RcGimbalError {
        Refused(1),
        TimeOut(2),
        Getted(3),
        OTHER(100);
        
        private int data;

        private RcGimbalError(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static RcGimbalError find(int i) {
            RcGimbalError rcGimbalError = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return rcGimbalError;
        }
    }

    public static synchronized DataRcRequestGimbalCtrPermission getInstance() {
        DataRcRequestGimbalCtrPermission dataRcRequestGimbalCtrPermission;
        synchronized (DataRcRequestGimbalCtrPermission.class) {
            if (instance == null) {
                instance = new DataRcRequestGimbalCtrPermission();
            }
            dataRcRequestGimbalCtrPermission = instance;
        }
        return dataRcRequestGimbalCtrPermission;
    }

    public RcGimbalError getError(a aVar) {
        return RcGimbalError.find(aVar.b());
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.RequestGimbalCtrPermission.b();
        cVar.p = getSendData();
        cVar.v = 10000;
        start(cVar, dVar);
    }
}
