package dji.midware.data.model.P3;

import dji.midware.data.a.a.b;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.n;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataFlycSetParams extends n implements e {
    private String[] a = null;
    private Number[] b = null;
    private ParamInfo c;
    private Number d;

    public void a(String... strArr) {
        this.a = strArr;
    }

    public void a(Number... numberArr) {
        this.b = numberArr;
    }

    public DataFlycSetParams a(String str, Number number) {
        this.c = d.read(str);
        this.d = number;
        return this;
    }

    public DataFlycSetParams a(String str) {
        this.c = d.read(str);
        this.d = this.c.setvalue;
        return this;
    }

    public void setRecPack(b bVar) {
        super.setRecPack(bVar);
        if (bVar != null && bVar.o == a.OK.a()) {
            if (this.a != null) {
                for (int i = 0; i < this.a.length; i++) {
                    d.write(this.a[i], this.b[i]);
                }
                return;
            }
            d.write(this.c.name, this.d);
        }
    }

    public void setRecData(byte[] bArr) {
    }

    protected void doPack() {
        int i;
        if (this.a != null) {
            int i2 = 0;
            for (String read : this.a) {
                this.c = d.read(read);
                if (d.isNew()) {
                    i2 += this.c.size + 4;
                } else {
                    i2 += this.c.size + 2;
                }
            }
            this._sendData = new byte[i2];
            i2 = 0;
            for (i = 0; i < this.a.length; i++) {
                Object a;
                this.c = d.read(this.a[i]);
                if (d.isNew()) {
                    c.a(c.b(this.c.hash), this._sendData, i2);
                    i2 += 4;
                } else {
                    c.a(c.b(this.c.index), this._sendData, i2);
                    i2 += 2;
                }
                this.d = this.b[i];
                switch (1.a[this.c.typeId.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        a = c.a(this.d.intValue());
                        break;
                    case 6:
                    case 7:
                    case 8:
                        a = c.a(this.d.longValue());
                        break;
                    case 9:
                        a = c.b((short) this.d.byteValue());
                        break;
                    case 10:
                        a = c.a(this.d.doubleValue());
                        break;
                    default:
                        a = c.a(this.d.floatValue());
                        break;
                }
                System.arraycopy(a, 0, this._sendData, i2, this.c.size);
                i2 += this.c.size;
            }
            return;
        }
        Object a2;
        if (d.isNew()) {
            this._sendData = new byte[(this.c.size + 4)];
            c.a(c.b(this.c.hash), this._sendData, 0);
            i = 4;
        } else {
            this._sendData = new byte[(this.c.size + 2)];
            c.a(c.b(this.c.index), this._sendData, 0);
            i = 2;
        }
        switch (1.a[this.c.typeId.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                a2 = c.a(this.d.intValue());
                break;
            case 6:
            case 7:
            case 8:
                a2 = c.a(this.d.longValue());
                break;
            case 9:
                a2 = c.b((short) this.d.byteValue());
                break;
            case 10:
                a2 = c.a(this.d.doubleValue());
                break;
            default:
                a2 = c.a(this.d.floatValue());
                break;
        }
        System.arraycopy(a2, 0, this._sendData, i, this.c.size);
        i += this.c.size;
    }

    public void start(dji.midware.e.d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = q.a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = q.b.NO.a();
        cVar.m = p.FLYC.a();
        if (d.isNew()) {
            cVar.n = g.a.SetParamsByHash.a();
        } else {
            cVar.n = g.a.SetParamsByIndex.a();
        }
        start(cVar, dVar);
    }

    protected void LogPack(String str) {
        super.LogPack(str);
    }
}
