package dji.midware.c.a;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.e;
import dji.midware.f.b;
import dji.midware.util.m;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.a.c;

public class a {
    private static final String a = a.class.getSimpleName();
    private static a c = null;
    private static boolean d = false;
    private DataCommonGetVersion b;

    public static a getInstance() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    public static boolean a() {
        return d;
    }

    public void b() {
        c.a().a((Object) this);
        if (dji.midware.f.a.getInstance().d() == b.NON) {
            onEventBackgroundThread(p.ConnectLose);
        } else {
            onEventBackgroundThread(p.ConnectOK);
        }
    }

    public DataCommonGetVersion c() {
        return this.b;
    }

    public ProductType d() {
        if (this.b == null) {
            return null;
        }
        return a(this.b);
    }

    public void onEventBackgroundThread(p pVar) {
        DJILogHelper.getInstance().LOGD("DJIComponentManager", "DataEvent = " + pVar, false, false);
        if (pVar == p.ConnectLose) {
            if (this.b != null) {
                this.b = null;
                d = false;
                c.a().e(this);
            }
        } else if (this.b == null) {
            e dataCommonGetVersion = new DataCommonGetVersion();
            dataCommonGetVersion.setClearCameraLose(false);
            dataCommonGetVersion.setDeviceType(DeviceType.OSD);
            new m(dataCommonGetVersion, 6, 500, new 1(this, dataCommonGetVersion)).a();
        }
    }

    public static ProductType a(DataCommonGetVersion dataCommonGetVersion) {
        if (dataCommonGetVersion == null || dataCommonGetVersion.getRecData() == null) {
            return null;
        }
        int intValue = ((Integer) dataCommonGetVersion.get(19, 1, Integer.class)).intValue();
        int intValue2 = ((Integer) dataCommonGetVersion.get(18, 1, Integer.class)).intValue();
        c byOsdData = c.getByOsdData(dataCommonGetVersion.getRecData());
        if (byOsdData != null && ProductType.isValidType(byOsdData.n)) {
            return byOsdData.n;
        }
        if (b(dataCommonGetVersion)) {
            return ProductType.KumquatX;
        }
        DJILogHelper.getInstance().LOGD("Test", "1765 Loader[" + dji.midware.util.c.i(dataCommonGetVersion.getRecData()) + d.H, false, true);
        DataCommonGetVersion a;
        if (intValue == 0 && 1 == intValue2) {
            ProductType productType = ProductType.Grape2;
            a = new b().a();
            if (a != null && ((Integer) a.get(19, 1, Integer.class)).intValue() == 0) {
                d = true;
            }
            return productType;
        }
        intValue2 = ((Integer) dataCommonGetVersion.get(24, 1, Integer.class)).intValue();
        DJILogHelper.getInstance().LOGD("", "1765 firmware type=" + intValue2, true, true);
        if (intValue2 >= 4) {
            intValue2 = ((Integer) dataCommonGetVersion.get(20, 1, Integer.class)).intValue() % 9;
            DJILogHelper.getInstance().LOGD("", "1765 loader version=" + intValue2, true, true);
            if (intValue2 == 1) {
                return ProductType.Orange;
            }
            if (intValue2 == 2) {
                return ProductType.litchiX;
            }
            if (intValue2 == 3) {
                return ProductType.litchiX;
            }
            if (intValue2 == 4) {
                return ProductType.Orange;
            }
            if (intValue2 == 0) {
                if (dji.midware.f.a.getInstance().d().equals(b.WIFI)) {
                    return ProductType.litchiC;
                }
            } else if (intValue2 == 5) {
                return ProductType.P34K;
            }
            return null;
        }
        a = new b().a();
        if (a == null) {
            return ProductType.litchiX;
        }
        intValue2 = ((Integer) a.get(24, 1, Integer.class)).intValue();
        if (intValue2 == 1) {
            return ProductType.Orange;
        }
        if (intValue2 == 2) {
            return ProductType.litchiX;
        }
        return ProductType.Orange;
    }

    private static boolean b(DataCommonGetVersion dataCommonGetVersion) {
        if (dataCommonGetVersion == null) {
            return false;
        }
        return ((Integer) dataCommonGetVersion.get(19, 1, Integer.class)).intValue() == 6;
    }
}
