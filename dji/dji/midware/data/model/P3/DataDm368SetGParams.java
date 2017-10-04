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

public class DataDm368SetGParams extends n implements e {
    private CmdId a;
    private int b;

    public enum CmdId {
        ShowOsd(1),
        Set720PFps(3),
        SetOsdLeft(4),
        SetOsdRight(5),
        SetOsdTop(6),
        SetOsdBottom(7),
        ShowUnit(9),
        ShowDouble(10),
        SetOutputDevice(12),
        SetHDMIFormat(13),
        SetSDIFormat(15),
        SetOutputMode(16),
        SetOutputLoc(17),
        SetOutputEnable(18),
        OTHER(100);
        
        private int p;

        private CmdId(int i) {
            this.p = i;
        }

        public int a() {
            return this.p;
        }

        public boolean a(int i) {
            return this.p == i;
        }

        public static CmdId find(int i) {
            CmdId cmdId = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return cmdId;
        }
    }

    public DataDm368SetGParams a(CmdId cmdId, int i) {
        this.a = cmdId;
        this.b = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[3];
        this._sendData[0] = (byte) this.a.a();
        this._sendData[1] = (byte) 1;
        this._sendData[2] = (byte) this.b;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.DM368_G.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.i.a();
        cVar.n = dji.midware.data.config.P3.e.a.SetGParams.a();
        start(cVar, dVar);
    }
}
