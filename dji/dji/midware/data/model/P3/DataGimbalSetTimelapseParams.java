package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import java.util.ArrayList;
import java.util.Iterator;

public class DataGimbalSetTimelapseParams extends n implements e {
    public static final int a = 5;
    private static final String c = "DataGimbalSetTimelapseParams";
    ArrayList<TimelapseRoadObject> b = new ArrayList();
    private byte d;

    private class TimelapseRoadObject {
        public long a = 0;
        public int b = 0;
        public int c = 0;
        public int d = 0;
        final /* synthetic */ DataGimbalSetTimelapseParams e;

        public TimelapseRoadObject(DataGimbalSetTimelapseParams dataGimbalSetTimelapseParams, long j, int i, int i2, int i3) {
            this.e = dataGimbalSetTimelapseParams;
            this.a = j;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }
    }

    public DataGimbalSetTimelapseParams a(int i) {
        this.d = (byte) (this.d | (i & 1));
        return this;
    }

    public DataGimbalSetTimelapseParams b(int i) {
        this.d = (byte) (this.d | ((i & 1) << 1));
        return this;
    }

    public DataGimbalSetTimelapseParams c(int i) {
        this.d = (byte) (this.d | ((i & 1) << 2));
        return this;
    }

    public boolean a(long j, int i, int i2, int i3) {
        if (this.b.size() >= 5) {
            return false;
        }
        this.b.add(new TimelapseRoadObject(this, j, i, i2, i3));
        return true;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.SetTimelapseParams.a();
        cVar.w = 5;
        cVar.v = 200;
        start(cVar, dVar);
    }

    protected void doPack() {
        int i;
        if ((this.d & 1) == 1) {
            i = 2;
        } else {
            i = (this.b.size() * 10) + 2;
        }
        this._sendData = new byte[i];
        this._sendData[0] = this.d;
        this._sendData[1] = (byte) this.b.size();
        if ((this.d & 1) == 0) {
            Iterator it = this.b.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                TimelapseRoadObject timelapseRoadObject = (TimelapseRoadObject) it.next();
                System.arraycopy(dji.midware.util.c.a(timelapseRoadObject.a), 0, this._sendData, (i2 * 10) + 2, 4);
                System.arraycopy(dji.midware.util.c.a(timelapseRoadObject.b), 0, this._sendData, (i2 * 10) + 6, 2);
                System.arraycopy(dji.midware.util.c.a(timelapseRoadObject.c), 0, this._sendData, (i2 * 10) + 8, 2);
                System.arraycopy(dji.midware.util.c.a(timelapseRoadObject.d), 0, this._sendData, (i2 * 10) + 10, 2);
                i2++;
            }
        }
        this.b.clear();
        this.d = (byte) 0;
    }
}
