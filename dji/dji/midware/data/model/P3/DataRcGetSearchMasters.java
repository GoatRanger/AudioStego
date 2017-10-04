package dji.midware.data.model.P3;

import android.util.SparseArray;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataRcGetSlaveList.RcModel;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataRcGetSearchMasters extends n implements e {
    private static DataRcGetSearchMasters instance = null;

    public static synchronized DataRcGetSearchMasters getInstance() {
        DataRcGetSearchMasters dataRcGetSearchMasters;
        synchronized (DataRcGetSearchMasters.class) {
            if (instance == null) {
                instance = new DataRcGetSearchMasters();
            }
            dataRcGetSearchMasters = instance;
        }
        return dataRcGetSearchMasters;
    }

    public SparseArray<RcModel> getList() {
        SparseArray<RcModel> sparseArray = new SparseArray();
        int length = this._recData.length / 11;
        for (int i = 0; i < length; i++) {
            RcModel rcModel = new RcModel();
            rcModel.id = ((Integer) get(i * 11, 4, Integer.class)).intValue();
            rcModel.name = c.g(c.e(this._recData, (i * 11) + 4, 6));
            rcModel.quality = ((Integer) get((i * 11) + 10, 1, Integer.class)).intValue();
            sparseArray.put(i, rcModel);
        }
        return sparseArray;
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
        cVar.m = p.g.a();
        cVar.n = k.a.GetSearchMasters.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
