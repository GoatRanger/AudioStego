package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.m;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import org.msgpack.core.MessagePack.Code;
import org.xeustechnologies.jtar.TarHeader;

public class DataSmartBatterySartUp extends n implements e {
    private static DataSmartBatterySartUp a = null;
    private int b = 0;

    public static DataSmartBatterySartUp getInstance() {
        if (a == null) {
            a = new DataSmartBatterySartUp();
        }
        return a;
    }

    public DataSmartBatterySartUp a(int i) {
        this.b = i;
        return this;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int b() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.BATTERY.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.n.a();
        cVar.n = m.a.StartUp.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[9];
        this._sendData[0] = (byte) this.b;
        this._sendData[1] = (byte) -17;
        this._sendData[2] = (byte) -66;
        this._sendData[3] = (byte) -83;
        this._sendData[4] = Code.MAP16;
        this._sendData[5] = (byte) 70;
        this._sendData[6] = TarHeader.LF_CONTIG;
        this._sendData[7] = (byte) 40;
        this._sendData[8] = (byte) 25;
    }
}
