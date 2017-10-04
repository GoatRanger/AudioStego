package dji.midware.data.model.P3;

import dji.log.DJILogHelper;
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

public class DataGimbalSetUserParams extends n implements e {
    private static DataGimbalSetUserParams a = null;
    private ParamInfo b;
    private Number c;
    private int d = -1;
    private int e = -1;

    public static synchronized DataGimbalSetUserParams getInstance() {
        DataGimbalSetUserParams dataGimbalSetUserParams;
        synchronized (DataGimbalSetUserParams.class) {
            if (a == null) {
                a = new DataGimbalSetUserParams();
            }
            dataGimbalSetUserParams = a;
        }
        return dataGimbalSetUserParams;
    }

    public DataGimbalSetUserParams a(int i) {
        this.d = i;
        return this;
    }

    public void b(int i) {
        this.e = i;
    }

    public DataGimbalSetUserParams a(String str, Number number) {
        this.b = dji.midware.data.manager.P3.e.read(str);
        this.c = number;
        return this;
    }

    public DataGimbalSetUserParams a(String str) {
        this.b = dji.midware.data.manager.P3.e.read(str);
        this.c = this.b.setvalue;
        return this;
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
        dji.midware.data.manager.P3.e.write(this.b.name, this.c);
    }

    protected void doPack() {
        if (this.b != null) {
            Object a;
            this._sendData = new byte[(this.b.size + 2)];
            this._sendData[0] = (byte) this.b.index;
            this._sendData[1] = (byte) this.b.size;
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
            System.arraycopy(a, 0, this._sendData, 2, this.b.size);
            DJILogHelper.getInstance().LOGE("DJIPackManager", "send =" + c.i(this._sendData), false, true);
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
        cVar.n = h.a.SetUserParams.a();
        if (this.d > 0) {
            cVar.v = this.d;
        }
        if (this.e > 0) {
            cVar.w = this.e;
        }
        start(cVar, dVar);
    }
}
