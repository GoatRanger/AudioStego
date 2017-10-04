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

public class DataFlycResetParams extends n implements e {
    private static DataFlycResetParams instance = null;
    private String[] indexs = null;
    private ParamInfo paramInfo;

    public static synchronized DataFlycResetParams getInstance() {
        DataFlycResetParams dataFlycResetParams;
        synchronized (DataFlycResetParams.class) {
            if (instance == null) {
                instance = new DataFlycResetParams();
            }
            dataFlycResetParams = instance;
        }
        return dataFlycResetParams;
    }

    public DataFlycResetParams setIndexs(String... strArr) {
        this.indexs = strArr;
        return this;
    }

    protected void doPack() {
        int i = 0;
        if (this.indexs != null) {
            int i2 = 0;
            for (String read : this.indexs) {
                this.paramInfo = d.read(read);
                if (d.isNew()) {
                    i2 += 4;
                } else {
                    i2 += 2;
                }
            }
            this._sendData = new byte[i2];
            int i3 = 0;
            while (i < this.indexs.length) {
                this.paramInfo = d.read(this.indexs[i]);
                if (d.isNew()) {
                    c.a(c.b(this.paramInfo.hash), this._sendData, i3);
                    i3 += 4;
                } else {
                    c.a(c.b(this.paramInfo.index), this._sendData, i3);
                    i3 += 2;
                }
                i++;
            }
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
        if (d.isNew()) {
            cVar.n = g.a.aU.a();
        } else {
            cVar.n = g.a.aP.a();
        }
        start(cVar, dVar);
    }
}
