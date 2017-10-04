package dji.midware.data.model.P3;

import com.google.android.gms.common.api.CommonStatusCodes;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCommonRestartDevice extends n implements e {
    private static DataCommonRestartDevice instance = null;
    private int mDelay = 0;
    private int mEncrypt = 0;
    private boolean mForceSetReceiveId = false;
    private int mReceiveId = 0;
    private DeviceType mReceiveType = DeviceType.RC;
    private int mRestartType = 0;

    public static synchronized DataCommonRestartDevice getInstance() {
        DataCommonRestartDevice dataCommonRestartDevice;
        synchronized (DataCommonRestartDevice.class) {
            if (instance == null) {
                instance = new DataCommonRestartDevice();
            }
            dataCommonRestartDevice = instance;
        }
        return dataCommonRestartDevice;
    }

    public DataCommonRestartDevice setReceiveType(DeviceType deviceType) {
        this.mReceiveType = deviceType;
        return this;
    }

    public DataCommonRestartDevice setRestartType(int i) {
        this.mRestartType = i;
        return this;
    }

    public DataCommonRestartDevice setReceiveId(int i) {
        this.mReceiveId = i;
        return this;
    }

    public DataCommonRestartDevice setReceiveIdForce(int i) {
        this.mReceiveId = i;
        this.mForceSetReceiveId = true;
        return this;
    }

    public DataCommonRestartDevice setDelay(int i) {
        this.mDelay = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[14];
        this._sendData[0] = (byte) this.mEncrypt;
        this._sendData[1] = (byte) this.mRestartType;
        System.arraycopy(c.a(this.mDelay), 0, this._sendData, 2, 4);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.mReceiveType.value();
        cVar.g = this.mForceSetReceiveId ? this.mReceiveId : 0;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.g.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        start(cVar, dVar);
    }
}
