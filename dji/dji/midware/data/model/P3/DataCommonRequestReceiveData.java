package dji.midware.data.model.P3;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCommonRequestUpgrade.DJIUpgradeFileMethod;
import dji.midware.data.model.P3.DataCommonRequestUpgrade.DJIUpgradeTranMethod;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;

public class DataCommonRequestReceiveData extends n implements e {
    private static DataCommonRequestReceiveData instance = null;
    private DJIUpgradeFileMethod fileMethod;
    private long mDataLength = 0;
    private int mEncrypt = 0;
    private int mReceiveId = 0;
    private DeviceType mReceiveType = DeviceType.RC;
    private DJIUpgradeTranMethod tranMethod;

    public static synchronized DataCommonRequestReceiveData getInstance() {
        DataCommonRequestReceiveData dataCommonRequestReceiveData;
        synchronized (DataCommonRequestReceiveData.class) {
            if (instance == null) {
                instance = new DataCommonRequestReceiveData();
            }
            dataCommonRequestReceiveData = instance;
        }
        return dataCommonRequestReceiveData;
    }

    public DataCommonRequestReceiveData setDataLength(long j) {
        this.mDataLength = j;
        return this;
    }

    public DataCommonRequestReceiveData setReceiveType(DeviceType deviceType) {
        this.mReceiveType = deviceType;
        return this;
    }

    public DataCommonRequestReceiveData setReceiveId(int i) {
        this.mReceiveId = i;
        return this;
    }

    public DataCommonRequestReceiveData setTranMethod(DJIUpgradeTranMethod dJIUpgradeTranMethod) {
        this.tranMethod = dJIUpgradeTranMethod;
        return this;
    }

    public DataCommonRequestReceiveData setFileMethod(DJIUpgradeFileMethod dJIUpgradeFileMethod) {
        this.fileMethod = dJIUpgradeFileMethod;
        return this;
    }

    public int getReceiveDataLength() {
        if (this._recData == null || this._recData.length < 2) {
            return 300;
        }
        return ((Integer) get(0, 2, Integer.class)).intValue();
    }

    protected void doPack() {
        this._sendData = new byte[13];
        this._sendData[0] = (byte) this.mEncrypt;
        System.arraycopy(c.b(this.mDataLength), 0, this._sendData, 1, 4);
        if (this.tranMethod != null) {
            this._sendData[11] = this.tranMethod.getBuffer();
        }
        if (this.fileMethod != null) {
            this._sendData[12] = this.fileMethod.getBuffer();
        }
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
        cVar.n = dji.midware.data.config.P3.d.a.d.a();
        cVar.v = 10000;
        cVar.w = 1;
        start(cVar, dVar);
    }

    protected void LogPack(String str) {
        DJILogHelper.getInstance().LOGE("", "rrr =" + str);
    }
}
