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

public class DataCommonGetCfgFile extends n implements e {
    private static DataCommonGetCfgFile instance = null;
    private long length = 0;
    private int mReceiveId = 0;
    private DeviceType mReceiveType = DeviceType.DM368;
    private long offset = 0;
    private DJIUpgradeFileType type;

    public enum DJIUpgradeFileType {
        CFG(1),
        LOG(2),
        OTHER(100);
        
        private int data;

        private DJIUpgradeFileType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static DJIUpgradeFileType find(int i) {
            DJIUpgradeFileType dJIUpgradeFileType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return dJIUpgradeFileType;
        }
    }

    public static synchronized DataCommonGetCfgFile getInstance() {
        DataCommonGetCfgFile dataCommonGetCfgFile;
        synchronized (DataCommonGetCfgFile.class) {
            if (instance == null) {
                instance = new DataCommonGetCfgFile();
            }
            dataCommonGetCfgFile = instance;
        }
        return dataCommonGetCfgFile;
    }

    public DataCommonGetCfgFile setReceiveType(DeviceType deviceType) {
        this.mReceiveType = deviceType;
        return this;
    }

    public DataCommonGetCfgFile setReceiveId(int i) {
        this.mReceiveId = i;
        return this;
    }

    public DataCommonGetCfgFile setType(DJIUpgradeFileType dJIUpgradeFileType) {
        this.type = dJIUpgradeFileType;
        return this;
    }

    public DataCommonGetCfgFile setOffset(long j) {
        this.offset = j;
        return this;
    }

    public DataCommonGetCfgFile setLength(long j) {
        this.length = j;
        return this;
    }

    public long getRelLength() {
        return ((Long) get(0, 4, Long.class)).longValue();
    }

    public long getRemainLength() {
        return ((Long) get(4, 4, Long.class)).longValue();
    }

    public int getBuffer(byte[] bArr) {
        if (this._recData == null) {
            return 0;
        }
        int length = this._recData.length - 8;
        System.arraycopy(this._recData, 8, bArr, 0, length);
        return length;
    }

    protected void doPack() {
        this._sendData = new byte[9];
        this._sendData[0] = (byte) this.type.value();
        System.arraycopy(c.b(this.offset), 0, this._sendData, 1, 4);
        System.arraycopy(c.b(this.length), 0, this._sendData, 5, 4);
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
        cVar.n = dji.midware.data.config.P3.d.a.B.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        cVar.w = 2;
        start(cVar, dVar);
    }
}
