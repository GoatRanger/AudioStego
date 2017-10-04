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

public class DataSimulatorCtrlMotor extends n implements e {
    private static DataSimulatorCtrlMotor a;
    private int[] b = new int[8];

    public static synchronized DataSimulatorCtrlMotor getInstance() {
        DataSimulatorCtrlMotor dataSimulatorCtrlMotor;
        synchronized (DataSimulatorCtrlMotor.class) {
            if (a == null) {
                a = new DataSimulatorCtrlMotor();
            }
            dataSimulatorCtrlMotor = a;
        }
        return dataSimulatorCtrlMotor;
    }

    public DataSimulatorCtrlMotor a(int[] iArr) {
        this.b = iArr;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.l.a();
        cVar.n = l.a.CtrlMotor.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[8];
        for (int i = 0; i < 8; i++) {
            this._sendData[i] = (byte) this.b[i];
        }
    }
}
