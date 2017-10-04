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
import dji.midware.data.model.P3.DataRcSetSlavePermission.RcSlavePermission;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcGetSlavePermission extends n implements e {
    private static DataRcGetSlavePermission instance = null;
    private SparseArray<RcSlavePermission> result = new SparseArray();

    public static synchronized DataRcGetSlavePermission getInstance() {
        DataRcGetSlavePermission dataRcGetSlavePermission;
        synchronized (DataRcGetSlavePermission.class) {
            if (instance == null) {
                instance = new DataRcGetSlavePermission();
            }
            dataRcGetSlavePermission = instance;
        }
        return dataRcGetSlavePermission;
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
        if (this._recData != null) {
            int length = this._recData.length / 5;
            for (int i = 0; i < length; i++) {
                RcSlavePermission rcSlavePermission;
                boolean z;
                int intValue = ((Integer) get(i * 5, 4, Integer.class)).intValue();
                RcSlavePermission rcSlavePermission2 = (RcSlavePermission) this.result.get(intValue);
                if (rcSlavePermission2 == null) {
                    rcSlavePermission = new RcSlavePermission();
                } else {
                    rcSlavePermission = rcSlavePermission2;
                }
                int intValue2 = ((Integer) get((i * 5) + 4, 1, Integer.class)).intValue();
                if (((intValue2 >> 7) & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                rcSlavePermission.b = z;
                if (((intValue2 >> 6) & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                rcSlavePermission.c = z;
                if (((intValue2 >> 5) & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                rcSlavePermission.d = z;
                if (((intValue2 >> 4) & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                rcSlavePermission.e = z;
                if (((intValue2 >> 3) & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                rcSlavePermission.f = z;
                if (((intValue2 >> 2) & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                rcSlavePermission.g = z;
                this.result.put(intValue, rcSlavePermission);
            }
        }
    }

    public RcSlavePermission getPermission(int i) {
        if (this.result == null) {
            return null;
        }
        return (RcSlavePermission) this.result.get(i);
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
        cVar.n = k.a.GetSlavePermission.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
