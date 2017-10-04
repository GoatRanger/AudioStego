package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.n;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataFlycSetPushParams extends n implements e {
    private static DataFlycSetPushParams a = null;
    private int b;
    private int c;
    private int d;
    private int e;
    private ParamInfo[] f;

    public static synchronized DataFlycSetPushParams getInstance() {
        DataFlycSetPushParams dataFlycSetPushParams;
        synchronized (DataFlycSetPushParams.class) {
            if (a == null) {
                a = new DataFlycSetPushParams();
            }
            dataFlycSetPushParams = a;
        }
        return dataFlycSetPushParams;
    }

    public DataFlycSetPushParams a(int i) {
        this.b = i;
        return this;
    }

    public DataFlycSetPushParams b(int i) {
        this.c = i;
        return this;
    }

    public DataFlycSetPushParams c(int i) {
        this.d = i;
        return this;
    }

    public DataFlycSetPushParams a(String[] strArr) {
        int i = 0;
        if (strArr == null) {
            this.e = 0;
        } else {
            this.e = strArr.length;
            this.f = new ParamInfo[this.e];
            while (i < this.e) {
                this.f[i] = d.read(strArr[i]);
                i++;
            }
        }
        return this;
    }

    protected void doPack() {
        int i = 0;
        this._sendData = new byte[((this.e * 4) + 4)];
        this._sendData[0] = (byte) this.b;
        this._sendData[1] = (byte) this.c;
        this._sendData[2] = (byte) this.d;
        this._sendData[3] = (byte) this.e;
        while (i < this.e) {
            c.a(c.b(this.f[i].hash), this._sendData, (i * 4) + 4);
            i++;
        }
    }

    public void start(dji.midware.e.d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aW.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }
}
