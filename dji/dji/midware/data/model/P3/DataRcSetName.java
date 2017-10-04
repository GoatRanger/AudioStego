package dji.midware.data.model.P3;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataRcSetName extends n implements e {
    private static DataRcSetName a = null;
    private String b;

    public static synchronized DataRcSetName getInstance() {
        DataRcSetName dataRcSetName;
        synchronized (DataRcSetName.class) {
            if (a == null) {
                a = new DataRcSetName();
            }
            dataRcSetName = a;
        }
        return dataRcSetName;
    }

    public DataRcSetName a(String str) {
        this.b = str;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[6];
        Object b = c.b(this.b);
        System.arraycopy(b, 0, this._sendData, 0, b.length);
        DJILogHelper.getInstance().LOGD("", "setrcname=" + c.i(this._sendData), false, true);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetName.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
