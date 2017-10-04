package dji.midware.data.model.P3;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.m;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.thirdparty.afinal.c.c;

public class DataCenterGetBatteryHistory extends n implements e {
    private static DataCenterGetBatteryHistory mInstance = null;
    private final int[] mHistories = new int[31];

    public static synchronized DataCenterGetBatteryHistory getInstance() {
        DataCenterGetBatteryHistory dataCenterGetBatteryHistory;
        synchronized (DataCenterGetBatteryHistory.class) {
            if (mInstance == null) {
                mInstance = new DataCenterGetBatteryHistory();
            }
            dataCenterGetBatteryHistory = mInstance;
        }
        return dataCenterGetBatteryHistory;
    }

    private DataCenterGetBatteryHistory() {
        c.b(this.mHistories, 0);
    }

    public int[] getHistory() {
        c.b(this.mHistories, 0);
        DJILogHelper.getInstance().LOGD("History", "data[" + dji.midware.util.c.i(this._recData), false, true);
        if (this._recData != null && this._recData.length > 0) {
            int i = 0;
            int i2 = 0;
            while (i2 < this.mHistories.length && i + 4 < this._recData.length) {
                this.mHistories[i2] = ((Integer) get(i, 4, Integer.class)).intValue();
                i2++;
                i += 4;
            }
        }
        return this.mHistories;
    }

    public long[] getHistoryLong() {
        long[] jArr = new long[16];
        DJILogHelper.getInstance().LOGD("History", "data[" + dji.midware.util.c.i(this._recData), false, true);
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = ((Long) get(((i * 8) + 2) + 2, 6, Long.class)).longValue();
        }
        return jArr;
    }

    public void resetHistory() {
        c.b(this.mHistories, 0);
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        ProductType c = i.getInstance().c();
        if (c == ProductType.litchiC || c == ProductType.litchiS || c == ProductType.litchiX || c == ProductType.P34K || c == ProductType.Tomato || c == ProductType.Pomato || c == ProductType.KumquatX || c == ProductType.KumquatS) {
            cVar.h = DeviceType.BATTERY.value();
        } else {
            cVar.h = DeviceType.CENTER.value();
        }
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        if (c == ProductType.KumquatX || c == ProductType.KumquatS) {
            cVar.m = p.n.a();
            cVar.n = m.a.GetHistory.a();
        } else {
            cVar.m = p.f.a();
            cVar.n = dji.midware.data.config.P3.c.a.GetBatteryHistory.a();
        }
        cVar.p = getSendData();
        cVar.v = 5000;
        cVar.w = 2;
        start(cVar, dVar);
    }
}
