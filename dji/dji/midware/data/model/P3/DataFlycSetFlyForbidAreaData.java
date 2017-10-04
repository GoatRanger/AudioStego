package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.forbid.DJISetFlyForbidAreaModel;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;
import java.util.ArrayList;

public class DataFlycSetFlyForbidAreaData extends n implements e {
    private static DataFlycSetFlyForbidAreaData a = null;
    private ArrayList<DJISetFlyForbidAreaModel> b = new ArrayList();
    private int c = 0;

    public static synchronized DataFlycSetFlyForbidAreaData getInstance() {
        DataFlycSetFlyForbidAreaData dataFlycSetFlyForbidAreaData;
        synchronized (DataFlycSetFlyForbidAreaData.class) {
            if (a == null) {
                a = new DataFlycSetFlyForbidAreaData();
            }
            dataFlycSetFlyForbidAreaData = a;
        }
        return dataFlycSetFlyForbidAreaData;
    }

    public DataFlycSetFlyForbidAreaData a(ArrayList<DJISetFlyForbidAreaModel> arrayList) {
        if (arrayList == null) {
            this.b.clear();
        } else {
            this.b.clear();
            this.b.addAll(arrayList);
        }
        return this;
    }

    public DataFlycSetFlyForbidAreaData a(int i) {
        this.c = i;
        return this;
    }

    protected void doPack() {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            if (this.b.get(size) == null) {
                this.b.remove(size);
            }
        }
        int size2 = this.b.size();
        this._sendData = new byte[((size2 * 17) + 5)];
        this._sendData[0] = c.c(size2);
        this._sendData[1] = c.c(this.c);
        this._sendData[2] = (byte) 0;
        this._sendData[3] = (byte) 0;
        this._sendData[4] = (byte) 0;
        for (int i = 0; i < size2; i++) {
            c.a(c.a(((DJISetFlyForbidAreaModel) this.b.get(i)).latitude), this._sendData, (i * 17) + 5);
            c.a(c.a(((DJISetFlyForbidAreaModel) this.b.get(i)).longitude), this._sendData, ((i * 17) + 5) + 4);
            c.a(c.b(((DJISetFlyForbidAreaModel) this.b.get(i)).radius), this._sendData, ((i * 17) + 5) + 8);
            c.a(c.b(((DJISetFlyForbidAreaModel) this.b.get(i)).contryCode), this._sendData, ((i * 17) + 5) + 10);
            c.a(new byte[]{c.c(((DJISetFlyForbidAreaModel) this.b.get(i)).type)}, this._sendData, ((i * 17) + 5) + 12);
            c.a(c.a(((DJISetFlyForbidAreaModel) this.b.get(i)).id), this._sendData, ((i * 17) + 5) + 13);
        }
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.D.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
