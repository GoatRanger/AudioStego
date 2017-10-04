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

public class DataFlycGetParams extends n implements e {
    private static DataFlycGetParams instance = null;
    private String[] indexs = null;

    public static synchronized DataFlycGetParams getInstance() {
        DataFlycGetParams dataFlycGetParams;
        synchronized (DataFlycGetParams.class) {
            if (instance == null) {
                instance = new DataFlycGetParams();
            }
            dataFlycGetParams = instance;
        }
        return dataFlycGetParams;
    }

    public DataFlycGetParams setInfos(String[] strArr) {
        this.indexs = strArr;
        return this;
    }

    public void setRecData(byte[] bArr) {
        int i;
        super.setRecData(bArr);
        if (d.isNew()) {
            i = 5;
        } else {
            i = 3;
        }
        int i2 = i;
        for (i = 0; i < this.indexs.length; i++) {
            ParamInfo read = d.read(this.indexs[i]);
            if (read != null) {
                long j = 0;
                try {
                    j = c.g(this._recData, i2 - 4);
                } catch (Exception e) {
                }
                if (j == read.hash) {
                    d.write(this.indexs[i], get(i2, read.size, read.type));
                    if (d.isNew()) {
                        i2 += read.size + 4;
                    } else {
                        i2 += read.size + 2;
                    }
                }
            }
        }
    }

    protected void doPack() {
        int i = 0;
        if (d.isNew()) {
            this._sendData = new byte[(this.indexs.length * 4)];
            while (i < this.indexs.length) {
                c.a(c.b(d.read(this.indexs[i]).hash), this._sendData, i * 4);
                i++;
            }
            return;
        }
        this._sendData = new byte[(this.indexs.length * 2)];
        while (i < this.indexs.length) {
            c.a(c.b(d.read(this.indexs[i]).index), this._sendData, i * 2);
            i++;
        }
    }

    public void start(dji.midware.e.d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.FLYC.a();
        if (d.isNew()) {
            cVar.n = g.a.GetParamsByHash.a();
        } else {
            cVar.n = g.a.GetParamsByIndex.a();
        }
        start(cVar, dVar);
    }
}
