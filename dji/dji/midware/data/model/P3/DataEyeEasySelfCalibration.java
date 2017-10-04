package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.f;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataEyeEasySelfCalibration extends n implements e {
    private static DataEyeEasySelfCalibration a = null;
    private SelfRequest b = SelfRequest.ByUser;

    public enum SelfRequest {
        None(0),
        ByUser(1),
        Cancel(-1),
        OTHER(100);
        
        private final int e;

        private SelfRequest(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static SelfRequest find(int i) {
            SelfRequest selfRequest = None;
            for (SelfRequest selfRequest2 : values()) {
                if (selfRequest2.a(i)) {
                    return selfRequest2;
                }
            }
            return selfRequest;
        }
    }

    public static synchronized DataEyeEasySelfCalibration getInstance() {
        DataEyeEasySelfCalibration dataEyeEasySelfCalibration;
        synchronized (DataEyeEasySelfCalibration.class) {
            if (a == null) {
                a = new DataEyeEasySelfCalibration();
            }
            dataEyeEasySelfCalibration = a;
        }
        return dataEyeEasySelfCalibration;
    }

    public DataEyeEasySelfCalibration a(SelfRequest selfRequest) {
        this.b = selfRequest;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.b.a();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.DOUBLE.value();
        cVar.g = 0;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.k.a();
        cVar.n = f.a.EasySelfCal.a();
        cVar.v = 2000;
        cVar.w = 1;
        start(cVar, dVar);
    }
}
