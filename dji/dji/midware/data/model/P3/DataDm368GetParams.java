package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataDm368SetParams.DM368CmdId;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataDm368GetParams extends n implements e {
    private static DataDm368GetParams instance = null;
    private DM368CmdId cmdId;
    private int mPercent = 0;

    public static synchronized DataDm368GetParams getInstance() {
        DataDm368GetParams dataDm368GetParams;
        synchronized (DataDm368GetParams.class) {
            if (instance == null) {
                instance = new DataDm368GetParams();
            }
            dataDm368GetParams = instance;
        }
        return dataDm368GetParams;
    }

    public DataDm368GetParams set(DM368CmdId dM368CmdId) {
        this.cmdId = dM368CmdId;
        return this;
    }

    public int getResult() {
        if (this.cmdId == DM368CmdId.BandwidthPercentage) {
            this.mPercent = ((Integer) get(2, 1, Integer.class)).intValue();
        }
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int getBandWidthPercent() {
        return this.mPercent;
    }

    public int getBandWidthPercentInstant() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int getCmdId() {
        return this.cmdId.a();
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.cmdId.a();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.g = 1;
        cVar.h = DeviceType.DM368.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.i.a();
        cVar.n = dji.midware.data.config.P3.e.a.GetParams.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
