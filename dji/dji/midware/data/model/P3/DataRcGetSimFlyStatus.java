package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcGetSimFlyStatus extends n implements e {
    private static DataRcGetSimFlyStatus instance = null;

    public enum FlySimStatus {
        NORMAL(0),
        FLY_SIM(1);
        
        private int mData;

        private FlySimStatus(int i) {
            this.mData = 0;
            this.mData = i;
        }

        public int value() {
            return this.mData;
        }

        private boolean belongsTo(int i) {
            return this.mData == i;
        }

        public static FlySimStatus ofData(int i) {
            for (FlySimStatus flySimStatus : values()) {
                if (flySimStatus.belongsTo(i)) {
                    return flySimStatus;
                }
            }
            return NORMAL;
        }
    }

    public static synchronized DataRcGetSimFlyStatus getInstance() {
        DataRcGetSimFlyStatus dataRcGetSimFlyStatus;
        synchronized (DataRcGetSimFlyStatus.class) {
            if (instance == null) {
                instance = new DataRcGetSimFlyStatus();
            }
            dataRcGetSimFlyStatus = instance;
        }
        return dataRcGetSimFlyStatus;
    }

    public FlySimStatus getFlySimStatus() {
        int i = 0;
        if (this._recData != null && this._recData.length > 0) {
            i = ((Integer) get(0, 1, Integer.class)).intValue();
        }
        return FlySimStatus.ofData(i);
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.GetSimFlyStatus.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
