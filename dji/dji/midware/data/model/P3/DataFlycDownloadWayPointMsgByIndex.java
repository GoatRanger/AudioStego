package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycUploadWayPointMsgByIndex.ACTION;
import dji.midware.data.model.P3.DataFlycUploadWayPointMsgByIndex.TURNMODE;
import dji.midware.e.d;
import dji.midware.e.e;
import java.util.ArrayList;

public class DataFlycDownloadWayPointMsgByIndex extends n implements e {
    private static DataFlycDownloadWayPointMsgByIndex a = null;
    private int b;

    public static synchronized DataFlycDownloadWayPointMsgByIndex getInstance() {
        DataFlycDownloadWayPointMsgByIndex dataFlycDownloadWayPointMsgByIndex;
        synchronized (DataFlycDownloadWayPointMsgByIndex.class) {
            if (a == null) {
                a = new DataFlycDownloadWayPointMsgByIndex();
            }
            dataFlycDownloadWayPointMsgByIndex = a;
        }
        return dataFlycDownloadWayPointMsgByIndex;
    }

    public DataFlycDownloadWayPointMsgByIndex a(int i) {
        this.b = i;
        return this;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int b() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public double c() {
        return ((Double) get(2, 8, Double.class)).doubleValue();
    }

    public double d() {
        return ((Double) get(10, 8, Double.class)).doubleValue();
    }

    public float e() {
        return ((Float) get(18, 4, Float.class)).floatValue();
    }

    public float f() {
        return ((Float) get(22, 4, Float.class)).floatValue();
    }

    public short g() {
        return ((Short) get(26, 2, Short.class)).shortValue();
    }

    public short h() {
        return ((Short) get(28, 2, Short.class)).shortValue();
    }

    public TURNMODE i() {
        return TURNMODE.find(((Short) get(30, 1, Short.class)).shortValue());
    }

    public boolean j() {
        return ((Integer) get(39, 1, Integer.class)).intValue() == 1;
    }

    public short k() {
        return ((Short) get(40, 2, Short.class)).shortValue();
    }

    public int l() {
        int intValue = (((Integer) get(42, 1, Integer.class)).intValue() << 28) >> 28;
        return 0;
    }

    public int m() {
        return ((Integer) get(42, 1, Integer.class)).intValue() >> 4;
    }

    public ArrayList<ACTION> n() {
        ArrayList<ACTION> arrayList = new ArrayList();
        for (int i = 0; i < 16; i++) {
            arrayList.add(ACTION.find(((Integer) get(i + 43, 1, Integer.class)).intValue()));
        }
        return arrayList;
    }

    public ArrayList<Integer> o() {
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 0; i < 16; i++) {
            arrayList.add(Integer.valueOf(((Short) get((i * 2) + 59, 2, Short.class)).shortValue()));
        }
        return arrayList;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.ae.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = dji.midware.util.c.c(this.b);
    }
}
