package dji.midware.data.model.P3;

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

public class DataFlycSmartAck extends n implements e {
    private static DataFlycSmartAck mInstance = null;
    private byte mAck = (byte) 0;

    public static synchronized DataFlycSmartAck getInstance() {
        DataFlycSmartAck dataFlycSmartAck;
        synchronized (DataFlycSmartAck.class) {
            if (mInstance == null) {
                mInstance = new DataFlycSmartAck();
            }
            dataFlycSmartAck = mInstance;
        }
        return dataFlycSmartAck;
    }

    public DataFlycSmartAck setAck(byte b) {
        this.mAck = b;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = this.mAck;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.M.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
