package dji.midware.data.model.P3;

import android.util.SparseArray;
import dji.midware.data.a.a.a;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.q;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.a.b;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCommonGetVersion extends b implements e {
    private static SparseArray<dji.midware.data.a.a.b> versionList = new SparseArray();
    private DeviceType deviceType;
    private boolean isClearCameraLose = true;
    private int modelId;

    public static class DJIVersionInfo {
        public boolean isProduction = true;
        public boolean isSupportSafeUpgrade = true;
    }

    public void setClearCameraLose(boolean z) {
        this.isClearCameraLose = z;
    }

    public void onEventBackgroundThread(p pVar) {
        if (pVar == p.ConnectLose) {
            clear();
        }
    }

    public void clear() {
        if (this.isClearCameraLose) {
            super.clear();
            synchronized (versionList) {
                versionList.clear();
            }
        }
    }

    public DeviceType getWhoamI() {
        a aVar = (a) versionList.get(getKey());
        if (aVar == null) {
            return DeviceType.OTHER;
        }
        return DeviceType.find(aVar.f);
    }

    public DeviceType getDeviceType() {
        return this.deviceType;
    }

    public int getModelId() {
        return this.modelId;
    }

    public DataCommonGetVersion setDeviceModel(int i) {
        this.modelId = i;
        return this;
    }

    public DataCommonGetVersion setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
        this.modelId = 0;
        return this;
    }

    public String getHardwareVer() {
        byte[] bArr = versionList.get(getKey()) != null ? ((dji.midware.data.a.a.b) versionList.get(getKey())).p : null;
        if (bArr != null) {
            this._recData = bArr;
        }
        return get(1, 16);
    }

    public int getLoaderByte(int i) {
        byte[] bArr = versionList.get(getKey()) != null ? ((dji.midware.data.a.a.b) versionList.get(getKey())).p : null;
        if (bArr != null) {
            this._recData = bArr;
        }
        return ((Integer) get(21 - i, 1, Integer.class)).intValue();
    }

    public int getFirmByte(int i) {
        byte[] bArr = versionList.get(getKey()) != null ? ((dji.midware.data.a.a.b) versionList.get(getKey())).p : null;
        if (bArr != null) {
            this._recData = bArr;
        }
        return ((Integer) get(25 - i, 1, Integer.class)).intValue();
    }

    public String getLoader(String str) {
        byte[] bArr = versionList.get(getKey()) != null ? ((dji.midware.data.a.a.b) versionList.get(getKey())).p : null;
        if (bArr != null) {
            this._recData = bArr;
        }
        if (this.deviceType == DeviceType.CAMERA) {
            int intValue = ((Integer) get(17, 2, Integer.class)).intValue();
            return String.format("%02d" + str + "%02d" + str + "%02d" + str + "%02d", new Object[]{get(20, 1, Integer.class), get(19, 1, Integer.class), Integer.valueOf(intValue / 100), Integer.valueOf(intValue % 100)});
        }
        return String.format("%02d" + str + "%02d" + str + "%02d" + str + "%02d", new Object[]{get(20, 1, Integer.class), get(19, 1, Integer.class), get(18, 1, Integer.class), get(17, 1, Integer.class)});
    }

    public String getFirmVer(String str) {
        byte[] bArr = versionList.get(getKey()) != null ? ((dji.midware.data.a.a.b) versionList.get(getKey())).p : null;
        if (bArr != null) {
            this._recData = bArr;
        }
        if (this.deviceType != DeviceType.CAMERA) {
            return String.format("%02d" + str + "%02d" + str + "%02d" + str + "%02d", new Object[]{get(24, 1, Integer.class), get(23, 1, Integer.class), get(22, 1, Integer.class), get(21, 1, Integer.class)});
        } else if (this.modelId == 6) {
            return String.format("%02d" + str + "%02d" + str + "%02d" + str + "%02d", new Object[]{get(24, 1, Integer.class), get(23, 1, Integer.class), get(22, 1, Integer.class), get(21, 1, Integer.class)});
        } else {
            int intValue = ((Integer) get(21, 2, Integer.class)).intValue();
            return String.format("%02d" + str + "%02d" + str + "%02d" + str + "%02d", new Object[]{get(24, 1, Integer.class), get(23, 1, Integer.class), Integer.valueOf(intValue / 100), Integer.valueOf(intValue % 100)});
        }
    }

    public DJIVersionInfo getInfo() {
        boolean z;
        boolean z2 = true;
        dji.midware.data.a.a.b bVar = (dji.midware.data.a.a.b) versionList.get(getKey());
        byte[] bArr = bVar != null ? bVar.p : null;
        if (bArr != null) {
            this._recData = bArr;
        }
        DJIVersionInfo dJIVersionInfo = new DJIVersionInfo();
        if ((((Integer) get(25, 4, Integer.class)).intValue() >> 31) == 1) {
            z = true;
        } else {
            z = false;
        }
        dJIVersionInfo.isProduction = z;
        if (((((Integer) get(25, 4, Integer.class)).intValue() >> 30) & 1) != 1) {
            z2 = false;
        }
        dJIVersionInfo.isSupportSafeUpgrade = z2;
        return dJIVersionInfo;
    }

    public String getLoaderSimple(String str) {
        dji.midware.data.a.a.b bVar = (dji.midware.data.a.a.b) versionList.get(getKey());
        byte[] bArr = bVar != null ? bVar.p : null;
        if (bArr != null) {
            this._recData = bArr;
        }
        return String.format("%02d" + str + "%02d", new Object[]{get(20, 1, Integer.class), get(19, 1, Integer.class)});
    }

    public String getFirmVerSimple(String str) {
        byte[] bArr = versionList.get(getKey()) != null ? ((dji.midware.data.a.a.b) versionList.get(getKey())).p : null;
        if (bArr != null) {
            this._recData = bArr;
        }
        if (this.deviceType == DeviceType.CAMERA) {
            return String.format("%02d" + str + "%02d", new Object[]{get(24, 1, Integer.class), get(23, 1, Integer.class)});
        }
        return String.format("%02d" + str + "%02d", new Object[]{get(24, 1, Integer.class), get(23, 1, Integer.class)});
    }

    public void setRecPack(dji.midware.data.a.a.b bVar) {
        super.setRecPack(bVar);
        dji.midware.data.a.a.b bVar2 = (dji.midware.data.a.a.b) versionList.get(getKey());
        boolean z = bVar.p != null;
        if (z && this.deviceType == DeviceType.FPGA_G) {
            z = ((Integer) get(17, 4, Integer.class)).intValue() != 0;
        }
        if (!z) {
            return;
        }
        if (this.deviceType == DeviceType.FPGA_G) {
            synchronized (versionList) {
                versionList.put(getKey(), bVar);
            }
        } else if (bVar2 == null) {
            synchronized (versionList) {
                versionList.put(getKey(), bVar);
            }
            if (this.deviceType == DeviceType.OSD && !i.getInstance().f()) {
                ProductType a = dji.midware.c.a.a.a(this);
                if (a != null) {
                    i.getInstance().b(true);
                    i.getInstance().b(a);
                    if (!i.getInstance().e()) {
                        i.getInstance().a(a);
                        if (ProductType.Grape2 == a) {
                            i.getInstance().a(true);
                        } else if (ProductType.P34K == a && ServiceManager.getInstance().isRemoteOK()) {
                            i.getInstance().a(true);
                        }
                    }
                }
            }
        }
    }

    protected void doPack() {
    }

    private int getKey() {
        return (this.deviceType.value() * 100) + this.modelId;
    }

    public void start(d dVar) {
        start(dVar, 500, 1);
    }

    public void startForce(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.g = this.modelId;
        cVar.h = this.deviceType.value();
        cVar.j = q.a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = q.b.NO.a();
        cVar.m = dji.midware.data.config.P3.p.COMMON.a();
        cVar.n = dji.midware.data.config.P3.d.a.GetVersion.a();
        cVar.v = 500;
        cVar.w = 1;
        start(cVar, dVar);
    }

    public void startForce(d dVar, int i, int i2, boolean z) {
        if (!(z || versionList.get(getKey()) == null)) {
            byte[] bArr = ((dji.midware.data.a.a.b) versionList.get(getKey())).p;
            if (bArr != null) {
                this._recData = bArr;
                dVar.onSuccess(this);
                return;
            }
        }
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.g = this.modelId;
        cVar.h = this.deviceType.value();
        cVar.j = q.a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = q.b.NO.a();
        cVar.m = dji.midware.data.config.P3.p.COMMON.a();
        cVar.n = dji.midware.data.config.P3.d.a.GetVersion.a();
        cVar.v = i;
        cVar.w = i2;
        start(cVar, dVar);
    }

    public void start(d dVar, int i, int i2) {
        if (versionList.get(getKey()) != null) {
            byte[] bArr = ((dji.midware.data.a.a.b) versionList.get(getKey())).p;
            if (bArr != null) {
                this._recData = bArr;
                dVar.onSuccess(this);
                return;
            }
        }
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.g = this.modelId;
        cVar.h = this.deviceType.value();
        cVar.j = q.a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = q.b.NO.a();
        cVar.m = dji.midware.data.config.P3.p.COMMON.a();
        cVar.n = dji.midware.data.config.P3.d.a.GetVersion.a();
        cVar.v = i;
        cVar.w = i2;
        start(cVar, dVar);
    }
}
