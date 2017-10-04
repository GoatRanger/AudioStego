package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.h;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataGimbalRoninSetUserParams extends n implements e {
    private static DataGimbalRoninSetUserParams a = null;
    private ParamInfo b;
    private Number c;
    private String[] d = null;
    private Number[] e = null;

    public static synchronized DataGimbalRoninSetUserParams getInstance() {
        DataGimbalRoninSetUserParams dataGimbalRoninSetUserParams;
        synchronized (DataGimbalRoninSetUserParams.class) {
            if (a == null) {
                a = new DataGimbalRoninSetUserParams();
            }
            dataGimbalRoninSetUserParams = a;
        }
        return dataGimbalRoninSetUserParams;
    }

    public DataGimbalRoninSetUserParams a(String... strArr) {
        this.d = strArr;
        return this;
    }

    public DataGimbalRoninSetUserParams a(Number... numberArr) {
        this.e = numberArr;
        return this;
    }

    public DataGimbalRoninSetUserParams a(String str, Number number) {
        this.b = dji.midware.data.manager.P3.e.read(str);
        this.c = number;
        return this;
    }

    public DataGimbalRoninSetUserParams a(String str) {
        this.b = dji.midware.data.manager.P3.e.read(str);
        this.c = this.b.setvalue;
        return this;
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
        dji.midware.data.manager.P3.e.write(this.b.name, this.c);
    }

    protected void doPack() {
        if (this.d != null) {
            int i;
            int i2 = 0;
            for (String read : this.d) {
                this.b = dji.midware.data.manager.P3.e.read(read);
                i2 += this.b.size + 2;
            }
            this._sendData = new byte[i2];
            i2 = 0;
            for (i = 0; i < this.d.length; i++) {
                Object a;
                this.b = dji.midware.data.manager.P3.e.read(this.d[i]);
                c.a(c.b(this.b.index), this._sendData, i2);
                int i3 = i2 + 1;
                this._sendData[i2] = (byte) this.b.index;
                int i4 = i3 + 1;
                this._sendData[i3] = (byte) this.b.size;
                this.c = this.e[i];
                switch (this.b.typeId) {
                    case INT08S:
                    case INT16S:
                    case INT32S:
                    case INT08U:
                    case INT16U:
                        a = c.a(this.c.intValue());
                        break;
                    case INT64S:
                    case INT32U:
                    case INT64U:
                        a = c.a(this.c.longValue());
                        break;
                    case BYTE:
                        a = c.b((short) this.c.byteValue());
                        break;
                    case DOUBLE:
                        a = c.a(this.c.doubleValue());
                        break;
                    default:
                        a = c.a(this.c.floatValue());
                        break;
                }
                System.arraycopy(a, 0, this._sendData, i4, this.b.size);
                i2 = this.b.size + i4;
            }
            this.d = null;
        } else if (this.b != null) {
            Object a2;
            this._sendData = new byte[(this.b.size + 2)];
            this._sendData[0] = (byte) this.b.index;
            this._sendData[1] = (byte) this.b.size;
            switch (this.b.typeId) {
                case INT08S:
                case INT16S:
                case INT32S:
                case INT08U:
                case INT16U:
                    a2 = c.a(this.c.intValue());
                    break;
                case INT64S:
                case INT32U:
                case INT64U:
                    a2 = c.a(this.c.longValue());
                    break;
                case BYTE:
                    a2 = c.b((short) this.c.byteValue());
                    break;
                case DOUBLE:
                    a2 = c.a(this.c.doubleValue());
                    break;
                default:
                    a2 = c.a(this.c.floatValue());
                    break;
            }
            System.arraycopy(a2, 0, this._sendData, 2, this.b.size);
        }
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.RobinSetParams.a();
        start(cVar, dVar);
    }
}
