package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.manager.P3.n;
import dji.midware.e.b;

public class DataNotifyDisconnect extends n implements b {
    private static DataNotifyDisconnect a = null;

    public static synchronized DataNotifyDisconnect getInstance() {
        DataNotifyDisconnect dataNotifyDisconnect;
        synchronized (DataNotifyDisconnect.class) {
            if (a == null) {
                a = new DataNotifyDisconnect();
            }
            dataNotifyDisconnect = a;
        }
        return dataNotifyDisconnect;
    }

    public RebootReason a() {
        return RebootReason.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public int b() {
        return ((Integer) get(1, 2, Integer.class)).intValue();
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) a.SUCCEED.a();
    }

    public void start() {
        c cVar = new c();
        if (this.pack != null) {
            cVar.f = DeviceType.APP.value();
            cVar.h = this.pack.f;
            cVar.g = this.pack.e;
            cVar.j = q.a.ACK.a();
            cVar.k = q.c.NO.a();
            cVar.l = q.b.NO.a();
            cVar.m = p.COMMON.a();
            cVar.n = this.pack.n;
            start(cVar);
        }
    }
}
