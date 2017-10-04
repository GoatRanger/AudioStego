package dji.midware.data.model.P3;

import com.google.android.gms.common.api.CommonStatusCodes;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycStopIoc extends n implements e {
    private static DataFlycStopIoc instance = null;

    public static synchronized DataFlycStopIoc getInstance() {
        DataFlycStopIoc dataFlycStopIoc;
        synchronized (DataFlycStopIoc.class) {
            if (instance == null) {
                instance = new DataFlycStopIoc();
            }
            dataFlycStopIoc = instance;
        }
        return dataFlycStopIoc;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
        this._sendData = null;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.az.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        start(cVar, dVar);
    }
}
