package dji.midware.data.model.P3;

import android.util.SparseIntArray;
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

public class DataRcGetHardwareParams extends n implements e {
    private static DataRcGetHardwareParams instance = null;

    public static synchronized DataRcGetHardwareParams getInstance() {
        DataRcGetHardwareParams dataRcGetHardwareParams;
        synchronized (DataRcGetHardwareParams.class) {
            if (instance == null) {
                instance = new DataRcGetHardwareParams();
            }
            dataRcGetHardwareParams = instance;
        }
        return dataRcGetHardwareParams;
    }

    public SparseIntArray getList() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        if (this._recData == null) {
            return sparseIntArray;
        }
        int length = this._recData.length / 2;
        for (int i = 0; i < length; i++) {
            sparseIntArray.put(i, ((Integer) get(i * 2, 2, Integer.class)).intValue());
        }
        return sparseIntArray;
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
        cVar.n = k.a.GetHardwareParams.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
