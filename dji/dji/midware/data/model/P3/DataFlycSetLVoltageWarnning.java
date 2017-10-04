package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycGetVoltageWarnning.WarnningLevel;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycSetLVoltageWarnning extends n implements e {
    private static DataFlycSetLVoltageWarnning a = null;
    private WarnningLevel b;
    private int c;
    private boolean d;

    public static synchronized DataFlycSetLVoltageWarnning getInstance() {
        DataFlycSetLVoltageWarnning dataFlycSetLVoltageWarnning;
        synchronized (DataFlycSetLVoltageWarnning.class) {
            if (a == null) {
                a = new DataFlycSetLVoltageWarnning();
            }
            dataFlycSetLVoltageWarnning = a;
        }
        return dataFlycSetLVoltageWarnning;
    }

    public void a(WarnningLevel warnningLevel) {
        this.b = warnningLevel;
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void b(boolean z) {
        this.d = z;
    }

    protected void doPack() {
        int i = 1;
        this._sendData = new byte[3];
        this._sendData[0] = (byte) this.b.value();
        this._sendData[1] = (byte) this.c;
        byte[] bArr = this._sendData;
        if (!this.d) {
            i = 0;
        }
        bArr[2] = (byte) i;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.r.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
