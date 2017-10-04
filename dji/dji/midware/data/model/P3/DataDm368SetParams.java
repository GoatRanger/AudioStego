package dji.midware.data.model.P3;

import dji.log.DJILogHelper;
import dji.midware.data.a.a.a;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataDm368SetParams extends n implements e {
    private DM368CmdId a;
    private int b;

    public enum DM368CmdId {
        DisableLiveStream(1),
        SetEncodeFormat(2),
        SetTransmissionMode(3),
        d(6),
        BandwidthPercentage(7),
        RevertImage(9),
        OTHER(100);
        
        private int h;

        private DM368CmdId(int i) {
            this.h = i;
        }

        public int a() {
            return this.h;
        }

        public boolean a(int i) {
            return this.h == i;
        }

        public static DM368CmdId find(int i) {
            DM368CmdId dM368CmdId = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return dM368CmdId;
        }
    }

    public DataDm368SetParams a(DM368CmdId dM368CmdId, int i) {
        this.a = dM368CmdId;
        this.b = i;
        return this;
    }

    protected void setPushRecPack(a aVar) {
        DJILogHelper.getInstance().LOGD("", aVar.h + "", false, true);
        super.setPushRecPack(aVar);
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
        cVar.g = 1;
        cVar.h = DeviceType.DM368.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.i.a();
        cVar.n = dji.midware.data.config.P3.e.a.SetParams.a();
        start(cVar, dVar);
    }
}
