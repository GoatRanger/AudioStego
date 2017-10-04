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

public class DataFlycResetParamNew extends n implements e {
    private String[] indexs = null;

    public DataFlycResetParamNew setInfos(String[] strArr) {
        this.indexs = strArr;
        return this;
    }

    protected void doPack() {
        int i = 0;
        ParamInfo read;
        if (d.isNew()) {
            this._sendData = new byte[(this.indexs.length * 4)];
            while (i < this.indexs.length) {
                read = d.read(this.indexs[i]);
                if (read != null) {
                    c.a(c.b(read.hash), this._sendData, i * 4);
                }
                i++;
            }
            return;
        }
        this._sendData = new byte[(this.indexs.length * 2)];
        while (i < this.indexs.length) {
            read = d.read(this.indexs[i]);
            if (read != null) {
                c.a(c.b(read.index), this._sendData, i * 2);
            }
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
        if (d.isNew()) {
            cVar.n = g.a.aU.a();
        } else {
            cVar.n = g.a.aP.a();
        }
        start(cVar, dVar);
    }
}
