package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataOsdSetSdrAssitantRead.SdrCpuType;
import dji.midware.data.model.P3.DataOsdSetSdrAssitantRead.SdrDataType;
import dji.midware.data.model.P3.DataOsdSetSdrAssitantRead.SdrDeviceType;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataOsdSetSdrAssitantWrite extends n implements e {
    private static DataOsdSetSdrAssitantWrite a = null;
    private SdrDeviceType b = SdrDeviceType.a;
    private SdrCpuType c = SdrCpuType.a;
    private SdrDataType d = SdrDataType.a;
    private int e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    public static synchronized DataOsdSetSdrAssitantWrite getInstance() {
        DataOsdSetSdrAssitantWrite dataOsdSetSdrAssitantWrite;
        synchronized (DataOsdSetSdrAssitantWrite.class) {
            if (a == null) {
                a = new DataOsdSetSdrAssitantWrite();
            }
            dataOsdSetSdrAssitantWrite = a;
        }
        return dataOsdSetSdrAssitantWrite;
    }

    public DataOsdSetSdrAssitantWrite a(SdrDeviceType sdrDeviceType) {
        this.b = sdrDeviceType;
        return this;
    }

    public DataOsdSetSdrAssitantWrite a(SdrCpuType sdrCpuType) {
        this.c = sdrCpuType;
        return this;
    }

    public DataOsdSetSdrAssitantWrite a(SdrDataType sdrDataType) {
        this.d = sdrDataType;
        return this;
    }

    public DataOsdSetSdrAssitantWrite a(int i) {
        this.e = i;
        return this;
    }

    public DataOsdSetSdrAssitantWrite b(int i) {
        this.f = i;
        return this;
    }

    public DataOsdSetSdrAssitantWrite a() {
        a(SdrDeviceType.a).a(SdrCpuType.a).a(SdrDataType.c).a(-65464).b(2);
        return this;
    }

    public DataOsdSetSdrAssitantWrite b() {
        this.b = SdrDeviceType.a;
        this.g = true;
        return this;
    }

    public DataOsdSetSdrAssitantWrite c() {
        this.b = SdrDeviceType.a;
        this.h = true;
        return this;
    }

    public int d() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    protected void doPack() {
        if (this.g) {
            this._sendData = new byte[]{(byte) 0, (byte) 1, (byte) 70, (byte) 0, (byte) -1, (byte) -1, (byte) -94, (byte) 3, (byte) 0, (byte) 0};
        } else if (this.h) {
            this._sendData = new byte[]{(byte) 0, (byte) 1, (byte) 70, (byte) 0, (byte) -1, (byte) -1, (byte) -110, (byte) 4, (byte) 0, (byte) 0};
        } else {
            this._sendData = new byte[11];
            this._sendData[0] = (byte) this.c.a();
            this._sendData[1] = (byte) this.d.a();
            System.arraycopy(c.a(this.e), 0, this._sendData, 2, 4);
            System.arraycopy(c.a(this.f), 0, this._sendData, 6, 4);
        }
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        if (this.b == SdrDeviceType.a) {
            cVar.h = DeviceType.OFDM.value();
        } else {
            cVar.h = DeviceType.OSD.value();
        }
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.OSD.a();
        cVar.n = i.a.B.a();
        start(cVar, dVar);
        e();
    }

    private void e() {
        this.g = false;
        this.h = false;
    }
}
