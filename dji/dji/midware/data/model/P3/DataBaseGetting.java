package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataBaseGetting extends n implements e {
    protected int cmdId;
    protected p cmdSet;
    protected DeviceType receiver;
    protected int value;

    public DataBaseGetting setCmdId(int i) {
        this.cmdId = i;
        return this;
    }

    public DataBaseGetting setCmdSet(p pVar) {
        this.cmdSet = pVar;
        return this;
    }

    public DataBaseGetting setReceiver(DeviceType deviceType) {
        this.receiver = deviceType;
        return this;
    }

    public int getValue() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getValue(int i, int i2) {
        if (this._recData == null || i + i2 > this._recData.length) {
            return -1;
        }
        return ((Integer) get(i, i2, Integer.class)).intValue();
    }

    public byte[] getData() {
        return this._recData;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.receiver.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = this.cmdSet.a();
        cVar.n = this.cmdId;
        start(cVar, dVar);
    }
}
