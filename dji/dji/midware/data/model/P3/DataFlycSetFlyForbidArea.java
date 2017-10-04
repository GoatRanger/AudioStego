package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.forbid.DJISetFlightLimitAreaModel;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;
import java.util.ArrayList;

public class DataFlycSetFlyForbidArea extends n implements e {
    private static DataFlycSetFlyForbidArea a = null;
    private ArrayList<DJISetFlightLimitAreaModel> b = new ArrayList();

    public static synchronized DataFlycSetFlyForbidArea getInstance() {
        DataFlycSetFlyForbidArea dataFlycSetFlyForbidArea;
        synchronized (DataFlycSetFlyForbidArea.class) {
            if (a == null) {
                a = new DataFlycSetFlyForbidArea();
            }
            dataFlycSetFlyForbidArea = a;
        }
        return dataFlycSetFlyForbidArea;
    }

    public DataFlycSetFlyForbidArea a(ArrayList<DJISetFlightLimitAreaModel> arrayList) {
        this.b.clear();
        this.b.addAll(arrayList);
        return this;
    }

    protected void doPack() {
        int size = this.b.size();
        this._sendData = new byte[((size * 17) + 5)];
        this._sendData[0] = c.c(size);
        c.a(c.a(0), this._sendData, 1);
        for (int i = 0; i < size; i++) {
            c.a(c.a(((DJISetFlightLimitAreaModel) this.b.get(i)).latitude), this._sendData, (i * 17) + 5);
            c.a(c.a(((DJISetFlightLimitAreaModel) this.b.get(i)).longitude), this._sendData, ((i * 17) + 5) + 4);
            c.a(c.b(((DJISetFlightLimitAreaModel) this.b.get(i)).radius), this._sendData, ((i * 17) + 5) + 8);
            c.a(c.b(((DJISetFlightLimitAreaModel) this.b.get(i)).contryCode), this._sendData, ((i * 17) + 5) + 10);
            c.a(new byte[]{c.c(((DJISetFlightLimitAreaModel) this.b.get(i)).type)}, this._sendData, ((i * 17) + 5) + 12);
            c.a(c.a(((DJISetFlightLimitAreaModel) this.b.get(i)).revers), this._sendData, ((i * 17) + 5) + 13);
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
        cVar.n = g.a.e.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
