package dji.midware.data.model.P3;

import android.util.SparseArray;
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

public class DataRcGetSlaveList extends n implements e {
    private static DataRcGetSlaveList instance = null;

    public static class RcModel {
        public int id;
        public boolean isOpen;
        public String name;
        public int password;
        public boolean pitch;
        public boolean playback;
        public int quality;
        public boolean record;
        public boolean roll;
        public boolean takephoto;
        public boolean yaw;
    }

    public static synchronized DataRcGetSlaveList getInstance() {
        DataRcGetSlaveList dataRcGetSlaveList;
        synchronized (DataRcGetSlaveList.class) {
            if (instance == null) {
                instance = new DataRcGetSlaveList();
            }
            dataRcGetSlaveList = instance;
        }
        return dataRcGetSlaveList;
    }

    public SparseArray<RcModel> getList() {
        SparseArray<RcModel> sparseArray = new SparseArray();
        int length = this._recData.length / 12;
        for (int i = 0; i < length; i++) {
            boolean z;
            RcModel rcModel = new RcModel();
            rcModel.id = ((Integer) get(i * 11, 4, Integer.class)).intValue();
            rcModel.name = getUTF8((i * 11) + 4, 6);
            rcModel.quality = ((Integer) get((i * 11) + 10, 1, Integer.class)).intValue();
            if (((((Integer) get((i * 11) + 11, 1, Integer.class)).intValue() >> 7) & 1) == 1) {
                z = true;
            } else {
                z = false;
            }
            rcModel.takephoto = z;
            if (((((Integer) get((i * 11) + 11, 1, Integer.class)).intValue() >> 6) & 1) == 1) {
                z = true;
            } else {
                z = false;
            }
            rcModel.record = z;
            if (((((Integer) get((i * 11) + 11, 1, Integer.class)).intValue() >> 5) & 1) == 1) {
                z = true;
            } else {
                z = false;
            }
            rcModel.playback = z;
            if (((((Integer) get((i * 11) + 11, 1, Integer.class)).intValue() >> 4) & 1) == 1) {
                z = true;
            } else {
                z = false;
            }
            rcModel.pitch = z;
            if (((((Integer) get((i * 11) + 11, 1, Integer.class)).intValue() >> 3) & 1) == 1) {
                z = true;
            } else {
                z = false;
            }
            rcModel.roll = z;
            if (((((Integer) get((i * 11) + 11, 1, Integer.class)).intValue() >> 2) & 1) == 1) {
                z = true;
            } else {
                z = false;
            }
            rcModel.yaw = z;
            sparseArray.put(i, rcModel);
        }
        return sparseArray;
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
        cVar.n = k.a.GetSlaveList.b();
        start(cVar, dVar);
    }
}
