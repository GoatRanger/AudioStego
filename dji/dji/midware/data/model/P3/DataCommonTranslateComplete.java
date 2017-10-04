package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCommonTranslateComplete extends n implements e {
    private static DataCommonTranslateComplete instance = null;
    private int mEncrypt = 0;
    private byte[] mMd5 = null;
    private int mReceiveId = 0;
    private DeviceType mReceiveType = DeviceType.RC;
    private int mTimeOut = -1;

    public static synchronized DataCommonTranslateComplete getInstance() {
        DataCommonTranslateComplete dataCommonTranslateComplete;
        synchronized (DataCommonTranslateComplete.class) {
            if (instance == null) {
                instance = new DataCommonTranslateComplete();
            }
            dataCommonTranslateComplete = instance;
        }
        return dataCommonTranslateComplete;
    }

    public DataCommonTranslateComplete setReceiveType(DeviceType deviceType) {
        this.mReceiveType = deviceType;
        return this;
    }

    public DataCommonTranslateComplete setReceiveId(int i) {
        this.mReceiveId = i;
        return this;
    }

    public DataCommonTranslateComplete setMD5(byte[] bArr) {
        this.mMd5 = bArr;
        return this;
    }

    public DataCommonTranslateComplete setTimeOut(int i) {
        this.mTimeOut = i;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[(this.mMd5.length + 1)];
        this._sendData[0] = (byte) this.mEncrypt;
        System.arraycopy(this.mMd5, 0, this._sendData, 1, this.mMd5.length);
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.mReceiveType.value();
        cVar.g = this.mReceiveId;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.f.a();
        cVar.w = 1;
        if (this.mTimeOut > 0) {
            cVar.v = this.mTimeOut;
        } else if (this.mReceiveType == DeviceType.DM368_G || this.mReceiveType == DeviceType.TRANSFORM_G) {
            cVar.v = 30000;
        } else {
            cVar.v = 10000;
        }
        start(cVar, dVar);
    }
}
