package dji.midware.data.model.P3;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.manager.P3.n;
import dji.midware.e.b;
import dji.midware.util.c;

public class DataRcAckGimbalCtrPermission extends n implements b {
    private static DataRcAckGimbalCtrPermission instance = null;
    private boolean isAgree;

    public static synchronized DataRcAckGimbalCtrPermission getInstance() {
        DataRcAckGimbalCtrPermission dataRcAckGimbalCtrPermission;
        synchronized (DataRcAckGimbalCtrPermission.class) {
            if (instance == null) {
                instance = new DataRcAckGimbalCtrPermission();
            }
            dataRcAckGimbalCtrPermission = instance;
        }
        return dataRcAckGimbalCtrPermission;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    public DataRcAckGimbalCtrPermission setIsAgree(boolean z) {
        this.isAgree = z;
        return this;
    }

    public String getName() {
        return getUTF8(1, 6);
    }

    protected void doPack() {
        int i;
        this._sendData = new byte[2];
        byte[] bArr = this._sendData;
        if (this.isAgree) {
            i = 0;
        } else {
            i = 1;
        }
        bArr[0] = (byte) i;
        this._sendData[1] = this._recData[0];
        DJILogHelper.getInstance().LOGD("", "ack =" + c.i(this._sendData));
    }

    public void start() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.b.a();
        cVar.k = q.c.b.a();
        cVar.l = q.b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.AckGimbalCtrPermission.b();
        cVar.i = this.pack.i;
        start(cVar);
        this._recData = null;
    }
}
