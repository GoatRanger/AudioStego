package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.thirdparty.afinal.c.c;

public class DataCommonRequestUpgrade extends n implements e {
    private static DataCommonRequestUpgrade instance = null;
    private int mEncrypt = 0;
    private int mReceiveId = 0;
    private DeviceType mReceiveType = DeviceType.RC;
    private DJIUpgradeFileMethod upgradeFileMethod = new DJIUpgradeFileMethod();
    private DJIUpgradeTranMethod upgradeTranMethod = new DJIUpgradeTranMethod();

    public static class DJIUpgradeFileMethod implements Cloneable {
        public boolean isSupportBigPackage = false;
        public boolean isSupportMultiFile = false;
        public boolean isSupportSingalFileSerial = false;

        public void parse(byte b) {
            boolean z;
            boolean z2 = true;
            if ((b & 1) == 1) {
                z = true;
            } else {
                z = false;
            }
            this.isSupportSingalFileSerial = z;
            if (((b >> 1) & 1) == 1) {
                z = true;
            } else {
                z = false;
            }
            this.isSupportMultiFile = z;
            if (((b >> 2) & 1) != 1) {
                z2 = false;
            }
            this.isSupportBigPackage = z2;
        }

        public byte getBuffer() {
            int i = 1;
            int i2 = (this.isSupportSingalFileSerial ? 1 : 0) | ((this.isSupportMultiFile ? 1 : 0) << 1);
            if (!this.isSupportBigPackage) {
                i = 0;
            }
            return (byte) (i2 | (i << 2));
        }

        public void reset() {
            this.isSupportSingalFileSerial = false;
            this.isSupportMultiFile = false;
            this.isSupportBigPackage = false;
        }

        public Object clone() {
            Object obj = null;
            try {
                obj = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static class DJIUpgradeTranMethod implements Cloneable {
        public boolean isSupportFTP = false;
        public boolean isSupportV1 = false;

        public void parse(byte b) {
            boolean z;
            boolean z2 = true;
            if ((b & 1) == 1) {
                z = true;
            } else {
                z = false;
            }
            this.isSupportV1 = z;
            if (((b >> 1) & 1) != 1) {
                z2 = false;
            }
            this.isSupportFTP = z2;
        }

        public byte getBuffer() {
            int i = 1;
            int i2 = this.isSupportV1 ? 1 : 0;
            if (!this.isSupportFTP) {
                i = 0;
            }
            return (byte) (i2 | (i << 1));
        }

        public void reset() {
            this.isSupportV1 = false;
            this.isSupportFTP = false;
        }

        public Object clone() {
            Object obj = null;
            try {
                obj = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return obj;
        }
    }

    public static synchronized DataCommonRequestUpgrade getInstance() {
        DataCommonRequestUpgrade dataCommonRequestUpgrade;
        synchronized (DataCommonRequestUpgrade.class) {
            if (instance == null) {
                instance = new DataCommonRequestUpgrade();
            }
            dataCommonRequestUpgrade = instance;
        }
        return dataCommonRequestUpgrade;
    }

    public DataCommonRequestUpgrade setReceiveType(DeviceType deviceType) {
        this.mReceiveType = deviceType;
        return this;
    }

    public DataCommonRequestUpgrade setReceiveId(int i) {
        this.mReceiveId = i;
        return this;
    }

    public DJIUpgradeTranMethod getTranMethodEntry() {
        this.upgradeTranMethod.parse(this._recData[0]);
        return this.upgradeTranMethod;
    }

    public DJIUpgradeFileMethod getTranFileEntry() {
        this.upgradeFileMethod.parse(this._recData[1]);
        return this.upgradeFileMethod;
    }

    protected void doPack() {
        this._sendData = new byte[9];
        c.b(this._sendData, (byte) 0);
        this._sendData[0] = (byte) this.mEncrypt;
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
        cVar.n = dji.midware.data.config.P3.d.a.c.a();
        cVar.v = 15000;
        cVar.w = 1;
        start(cVar, dVar);
    }
}
