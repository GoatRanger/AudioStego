package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
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

public class DataGimbalRoninGetUserParams extends n implements e {
    private static DataGimbalRoninGetUserParams instance = null;
    private String[] indexs = null;

    public static synchronized DataGimbalRoninGetUserParams getInstance() {
        DataGimbalRoninGetUserParams dataGimbalRoninGetUserParams;
        synchronized (DataGimbalRoninGetUserParams.class) {
            if (instance == null) {
                instance = new DataGimbalRoninGetUserParams();
            }
            dataGimbalRoninGetUserParams = instance;
        }
        return dataGimbalRoninGetUserParams;
    }

    public DataGimbalRoninGetUserParams setInfos(String[] strArr) {
        this.indexs = strArr;
        return this;
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
        int i = 0;
        for (int i2 = 0; i2 < this.indexs.length; i2++) {
            ParamInfo read = dji.midware.data.manager.P3.e.read(this.indexs[i2]);
            if (read != null && read.index == ((Integer) get(i, 1, Integer.class)).intValue()) {
                int i3 = i + 2;
                dji.midware.data.manager.P3.e.write(this.indexs[i2], get(i3, read.size, read.type));
                i = read.size + i3;
            }
        }
    }

    protected void doPack() {
        this._sendData = new byte[this.indexs.length];
        for (int i = 0; i < this.indexs.length; i++) {
            this._sendData[i] = (byte) dji.midware.data.manager.P3.e.read(this.indexs[i]).index;
        }
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.GIMBAL.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.e.a();
        cVar.n = h.a.RobinGetParams.a();
        start(cVar, dVar);
    }
}
