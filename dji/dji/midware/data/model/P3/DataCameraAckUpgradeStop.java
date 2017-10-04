package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.d;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.manager.P3.n;
import dji.midware.e.b;

public class DataCameraAckUpgradeStop extends n implements b {
    private static DataCameraAckUpgradeStop instance = null;

    public static synchronized DataCameraAckUpgradeStop getInstance() {
        DataCameraAckUpgradeStop dataCameraAckUpgradeStop;
        synchronized (DataCameraAckUpgradeStop.class) {
            if (instance == null) {
                instance = new DataCameraAckUpgradeStop();
            }
            dataCameraAckUpgradeStop = instance;
        }
        return dataCameraAckUpgradeStop;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) 0;
    }

    public void start() {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.b.a();
        cVar.k = q.c.b.a();
        cVar.l = q.b.a.a();
        cVar.m = p.a.a();
        cVar.n = d.a.w.a();
        cVar.i = this.pack.i;
        start(cVar);
    }
}
