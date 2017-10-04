package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.i;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataOsdGetPushConfig extends n implements e {
    private static DataOsdGetPushConfig instance = null;

    public static synchronized DataOsdGetPushConfig getInstance() {
        DataOsdGetPushConfig dataOsdGetPushConfig;
        synchronized (DataOsdGetPushConfig.class) {
            if (instance == null) {
                instance = new DataOsdGetPushConfig();
            }
            dataOsdGetPushConfig = instance;
        }
        return dataOsdGetPushConfig;
    }

    protected void setPushRecData(byte[] bArr) {
        if (this.pack.j == 0) {
            super.setPushRecData(c.h(bArr, 1));
        } else {
            super.setPushRecData(bArr);
        }
    }

    public int getChannel() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public boolean getIsAuto() {
        return ((Integer) get(2, 1, Integer.class)).intValue() != 0;
    }

    public boolean getIsMaster() {
        return ((Integer) get(3, 1, Integer.class)).intValue() == 1;
    }

    public int getMcs() {
        return ((Integer) get(9, 1, Integer.class)).intValue();
    }

    public boolean getSingleOrDouble() {
        return ((Integer) get(10, 1, Integer.class)).intValue() == 2;
    }

    public int getBandWidthPercent() {
        return ((Integer) get(11, 1, Integer.class)).intValue();
    }

    public int getWorkingFreq() {
        if (this._recData.length >= 16) {
            return ((Integer) get(15, 1, Integer.class)).intValue();
        }
        return 0;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.j.a();
        cVar.n = i.a.GetPushConfig.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
