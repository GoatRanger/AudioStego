package dji.midware.data.model.P3;

import android.util.Log;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.util.c;

public class DataRcSetFollowFocusInfo extends n {
    private static DataRcSetFollowFocusInfo b = null;
    private final String a = DataRcSetFollowFocusInfo.class.getSimpleName();
    private int c = -1;
    private int d = -1;

    public static synchronized DataRcSetFollowFocusInfo getInstance() {
        DataRcSetFollowFocusInfo dataRcSetFollowFocusInfo;
        synchronized (DataRcSetFollowFocusInfo.class) {
            if (b == null) {
                b = new DataRcSetFollowFocusInfo();
            }
            dataRcSetFollowFocusInfo = b;
        }
        return dataRcSetFollowFocusInfo;
    }

    public DataRcSetFollowFocusInfo a(int i) {
        this.c = i;
        return this;
    }

    public DataRcSetFollowFocusInfo b(int i) {
        this.d = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) ((this.c & 3) | ((this.d << 2) & 4));
        Log.d(this.a, "doPack: " + c.i(this._sendData));
    }

    public void a(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.RC.value();
        cVar.g = 1;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.SetFollowFocusInfo.b();
        start(cVar, dVar);
    }
}
