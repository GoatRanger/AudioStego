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

public class DataDm385SetParams extends n implements e {
    private DM385CmdId a;
    private int b;

    public enum DM385CmdId {
        SetTransmissionMode(3),
        OTHER(100);
        
        private int c;

        private DM385CmdId(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }

        public boolean a(int i) {
            return this.c == i;
        }

        public static DM385CmdId find(int i) {
            DM385CmdId dM385CmdId = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return dM385CmdId;
        }
    }

    public DataDm385SetParams a(DM385CmdId dM385CmdId, int i) {
        this.a = dM385CmdId;
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
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
