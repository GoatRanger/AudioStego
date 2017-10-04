package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.l;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataSimulatorConnectHeartPacket extends n implements e {
    private static DataSimulatorConnectHeartPacket a = null;
    private int b;

    public static synchronized DataSimulatorConnectHeartPacket getInstance() {
        DataSimulatorConnectHeartPacket dataSimulatorConnectHeartPacket;
        synchronized (DataSimulatorConnectHeartPacket.class) {
            if (a == null) {
                a = new DataSimulatorConnectHeartPacket();
            }
            dataSimulatorConnectHeartPacket = a;
        }
        return dataSimulatorConnectHeartPacket;
    }

    public DataSimulatorConnectHeartPacket a(int i) {
        this.b = i;
        return this;
    }

    public int a() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.l.a();
        cVar.n = l.a.ConnectHeartPacket.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[4];
        this._sendData[0] = (byte) 0;
        this._sendData[1] = (byte) this.b;
        this._sendData[2] = (byte) 0;
        this._sendData[3] = (byte) 0;
    }
}
