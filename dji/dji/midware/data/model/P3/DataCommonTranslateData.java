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

public class DataCommonTranslateData extends n implements e {
    private static DataCommonTranslateData instance = null;
    private byte[] mDatas = null;
    private int mEncrypt = 0;
    private int mReceiveId = 0;
    private DeviceType mReceiveType = DeviceType.RC;
    private int mSequence = 0;
    private int mSize = 0;

    public static synchronized DataCommonTranslateData getInstance() {
        DataCommonTranslateData dataCommonTranslateData;
        synchronized (DataCommonTranslateData.class) {
            if (instance == null) {
                instance = new DataCommonTranslateData();
            }
            dataCommonTranslateData = instance;
        }
        return dataCommonTranslateData;
    }

    public DataCommonTranslateData setReceiveType(DeviceType deviceType) {
        this.mReceiveType = deviceType;
        return this;
    }

    public DataCommonTranslateData setReceiveId(int i) {
        this.mReceiveId = i;
        return this;
    }

    public DataCommonTranslateData setSequence(int i) {
        this.mSequence = i;
        return this;
    }

    public DataCommonTranslateData setData(byte[] bArr) {
        this.mDatas = bArr;
        return this;
    }

    public DataCommonTranslateData setData(byte[] bArr, int i) {
        this.mDatas = bArr;
        this.mSize = i;
        return this;
    }

    public int getSequence() {
        int i = this.mSequence;
        if (this._recData == null) {
        }
        if (this._recData == null || this._recData.length < 4) {
            return i;
        }
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public int getFailSequence() {
        if (this._recData == null || this._recData.length < 4) {
            return 0;
        }
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
        if (this._recData != null) {
        }
    }

    protected void doPack() {
        this.mSize = this.mSize == 0 ? this.mDatas.length : this.mSize;
        this._sendData = new byte[(this.mSize + 7)];
        this._sendData[0] = (byte) this.mEncrypt;
        System.arraycopy(c.a(this.mSequence), 0, this._sendData, 1, 4);
        System.arraycopy(c.b(this.mSize), 0, this._sendData, 5, 2);
        System.arraycopy(this.mDatas, 0, this._sendData, 7, this.mSize);
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.mReceiveType.value();
        cVar.g = this.mReceiveId;
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.e.a();
        cVar.w = 1;
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        start(cVar, dVar);
    }

    public void start() {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = this.mReceiveType.value();
        cVar.g = this.mReceiveId;
        cVar.j = a.a.a();
        cVar.k = q.c.b.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.e.a();
        start(cVar);
    }

    protected void LogPack(String str) {
        super.LogPack(str);
    }
}
