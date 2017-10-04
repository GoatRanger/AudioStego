package dji.pilot.publics.e;

import android.location.Location;
import dji.common.product.Model;
import dji.logic.c.b;
import dji.logic.f.d;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.sdksharedlib.b.h;
import java.util.Arrays;
import java.util.regex.Pattern;

public class a {
    private static final float[] a = new float[2];
    private static final float b = 100000.0f;

    public static String a(String str) {
        return a(str, i.getInstance().c());
    }

    public static String a(String str, ProductType productType) {
        if (productType == ProductType.Orange || productType == ProductType.BigBanana || productType == ProductType.OrangeRAW || productType == ProductType.OrangeCV600 || productType == ProductType.None || productType == ProductType.OTHER) {
            return str;
        }
        if (productType != null) {
            return productType.toString() + str;
        }
        return ProductType.OTHER._name();
    }

    public static boolean a() {
        return b(i.getInstance().c());
    }

    public static boolean a(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return b.getInstance().a(productType);
    }

    public static boolean b(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.litchiC || productType == ProductType.litchiS || productType == ProductType.litchiX || productType == ProductType.P34K || productType == ProductType.Tomato || productType == ProductType.Pomato || c(productType);
    }

    public static boolean b() {
        ProductType c = i.getInstance().c();
        return d(c) || c(c);
    }

    public static boolean a(Model model) {
        Model model2;
        if (model == null) {
            model2 = (Model) dji.sdksharedlib.a.a.a(h.c);
        } else {
            model2 = model;
        }
        if (model2 == null) {
            return false;
        }
        boolean z = model2 == Model.Phantom_3_4K || model2 == Model.Phantom_3_Advanced || model2 == Model.Phantom_3_Professional || model2 == Model.Phantom_3_Standard || model2 == Model.Phantom_4 || model2 == Model.Phantom4_Pro;
        return z;
    }

    public static boolean c(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.KumquatX || productType == ProductType.KumquatS;
    }

    public static boolean d(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.PM820 || productType == ProductType.PM820PRO;
    }

    public static boolean e(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return d(productType);
    }

    public static boolean b(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean f(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return c(productType) && !b.getInstance().a(productType);
    }

    public static boolean c(String str) {
        String str2 = "[A-Z0-9a-z-_ ]{1,32}";
        return Pattern.compile("[A-Z0-9a-z-_ ]{1,32}").matcher(str).matches();
    }

    public static boolean d(String str) {
        String str2 = "[A-Z0-9a-z]{8,63}";
        return Pattern.compile("[A-Z0-9a-z]{8,63}").matcher(str).matches();
    }

    public static boolean g(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.Orange || productType == ProductType.N1 || productType == ProductType.BigBanana || productType == ProductType.Olives || productType == ProductType.OrangeRAW || productType == ProductType.OrangeCV600;
    }

    public static boolean b(Model model) {
        Model model2;
        if (model == null) {
            model2 = (Model) dji.sdksharedlib.a.a.a(h.c);
        } else {
            model2 = model;
        }
        if (model2 == null) {
            return false;
        }
        boolean z = model2 == Model.Inspire_1 || model2 == Model.Inspire_1_Pro || model2 == Model.Inspire_1_Raw || model2 == Model.Matrice_100 || model2 == Model.ZenmuseZ3;
        return z;
    }

    public static boolean h(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.Orange || productType == ProductType.BigBanana || productType == ProductType.OrangeRAW || productType == ProductType.Olives || productType == ProductType.OrangeCV600;
    }

    public static boolean i(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return d.a(productType);
    }

    public static boolean a(ProductType productType, long j) {
        if (productType == null) {
            productType = i.getInstance().a();
        }
        if (!j(productType)) {
            return false;
        }
        if (ProductType.Grape2 == productType) {
            if (67895552 <= j) {
                return true;
            }
            return false;
        } else if (67764230 > j) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean j(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.Orange || productType == ProductType.N1 || productType == ProductType.Grape2 || productType == ProductType.A2 || productType == ProductType.BigBanana || productType == ProductType.OrangeRAW || productType == ProductType.Olives || productType == ProductType.OrangeCV600 || productType == ProductType.A3 || d(productType) || productType == ProductType.N3;
    }

    public static boolean k(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.Tomato || productType == ProductType.Pomato || c(productType);
    }

    public static dji.pilot.fpv.model.o.a l(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (ProductType.Tomato == productType || productType == ProductType.Pomato || c(productType)) {
            return dji.pilot.fpv.model.o.a.b;
        }
        return dji.pilot.fpv.model.o.a.a;
    }

    public static dji.pilot.fpv.model.o.a m(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (productType == ProductType.Pomato) {
            return dji.pilot.fpv.model.o.a.b;
        }
        return dji.pilot.fpv.model.o.a.a;
    }

    public static dji.pilot.fpv.model.o.a n(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (ProductType.Pomato == productType) {
            return dji.pilot.fpv.model.o.a.c;
        }
        return dji.pilot.fpv.model.o.a.a;
    }

    public static dji.pilot.fpv.model.o.a o(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (ProductType.Pomato == productType) {
            return dji.pilot.fpv.model.o.a.c;
        }
        return dji.pilot.fpv.model.o.a.a;
    }

    public static dji.pilot.fpv.model.o.a p(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (productType == ProductType.litchiX || productType == ProductType.litchiS || productType == ProductType.P34K || productType == ProductType.Orange || productType == ProductType.OrangeRAW || productType == ProductType.BigBanana || productType == ProductType.Olives || productType == ProductType.Tomato || productType == ProductType.Pomato || productType == ProductType.OrangeCV600) {
            return dji.pilot.fpv.model.o.a.b;
        }
        return dji.pilot.fpv.model.o.a.a;
    }

    public static dji.pilot.fpv.model.o.a q(ProductType productType) {
        if (productType == null) {
            i.getInstance().c();
        }
        return dji.pilot.fpv.model.o.a.a;
    }

    public static boolean r(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.N1;
    }

    public static boolean a(double d) {
        double abs = Math.abs(d);
        return 1.0E-6d < abs && abs <= 90.0d;
    }

    public static float[] a(double d, double d2, double d3, double d4) {
        float[] fArr = new float[2];
        float b = b(d, d2, d3, d4);
        if (b <= 0.0f) {
            fArr[0] = 0.0f;
        } else {
            double toDegrees = Math.toDegrees(Math.asin((double) (b(d, d4, d3, d4) / b)));
            if (d3 > d) {
                if (d4 <= d2) {
                    toDegrees = 180.0d - toDegrees;
                }
            } else if (d4 > d2) {
                toDegrees = 360.0d - toDegrees;
            } else {
                toDegrees += 180.0d;
            }
            if (Double.isNaN(toDegrees)) {
                toDegrees = 0.0d;
            }
            fArr[0] = (float) toDegrees;
        }
        fArr[1] = b;
        return fArr;
    }

    public static boolean c() {
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        if (!instance.isGetted()) {
            return false;
        }
        if (d() || instance.getFlycVersion() < 6) {
            return a(instance.getGpsNum());
        }
        if (instance.getGpsLevel() >= 4) {
            return true;
        }
        return false;
    }

    public static boolean a(int i) {
        if (i.getInstance().c() == ProductType.litchiC) {
            if (i < 6 || i >= 50) {
                return false;
            }
            return true;
        } else if (i < 8 || i >= 50) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean d() {
        return a(DataOsdGetPushCommon.getInstance().getDroneType());
    }

    public static boolean a(DroneType droneType) {
        return droneType == DroneType.A2 || droneType == DroneType.WKM || droneType == DroneType.NAZA;
    }

    public static float b(double d, double d2, double d3, double d4) {
        float[] fArr = new float[2];
        Location.distanceBetween(d, d2, d3, d4, fArr);
        if (fArr[0] <= 0.0f || fArr[0] > b) {
            fArr[0] = 0.0f;
        }
        return fArr[0];
    }

    public static float c(double d, double d2, double d3, double d4) {
        Arrays.fill(a, 0.0f);
        Location.distanceBetween(d, d2, d3, d4, a);
        if (a[0] <= 0.0f) {
            a[0] = 0.0f;
        }
        return a[0];
    }

    public static boolean b(double d) {
        double abs = Math.abs(d);
        return 1.0E-6d < abs && abs <= 180.0d;
    }

    public static boolean a(double d, double d2) {
        return a(d) && b(d2);
    }

    public static boolean e() {
        return a(DataOsdGetPushCommon.getInstance());
    }

    public static boolean a(DataOsdGetPushCommon dataOsdGetPushCommon) {
        return dataOsdGetPushCommon.getFlycVersion() >= 7;
    }

    public static boolean f() {
        ProductType c = i.getInstance().c();
        return c == ProductType.Tomato || c == ProductType.Pomato || g() || c(c);
    }

    public static int s(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (productType == ProductType.Tomato || productType == ProductType.Pomato || c(productType)) {
            return 2;
        }
        if (g()) {
            return 3;
        }
        return 1;
    }

    public static int t(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (productType == ProductType.Tomato || productType == ProductType.Pomato || c(productType) || productType == ProductType.N3) {
            return 2;
        }
        if (!g() || productType == ProductType.N3) {
            return 1;
        }
        return 3;
    }

    public static boolean g() {
        ProductType c = i.getInstance().c();
        return c == ProductType.A3 || d(c) || c == ProductType.N3;
    }

    public static boolean h() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 16) {
            return false;
        }
        ProductType c = i.getInstance().c();
        if (c == ProductType.A3 || c == ProductType.N3) {
            return true;
        }
        return false;
    }

    public static boolean i() {
        DroneType droneType = DataOsdGetPushCommon.getInstance().getDroneType();
        return DroneType.A3 == droneType || DroneType.PM820 == droneType || droneType == DroneType.N3;
    }

    public static boolean j() {
        return i.getInstance().c() == ProductType.PM820 || i.getInstance().c() == ProductType.PM820PRO;
    }

    public static boolean u(ProductType productType) {
        return !(productType == ProductType.litchiC || i(productType) || i.getInstance().a() == ProductType.Grape2 || productType == ProductType.A2 || b.getInstance().a(null)) || d(productType) || productType == ProductType.A3 || productType == ProductType.N3;
    }

    public static boolean a(ProductType productType, int i) {
        return (b(null) && i >= 6) || ((g(null) && i >= 13) || (d(productType) && i >= 23));
    }

    public static DJICustomType[] a(Boolean bool) {
        DJICustomType[] dJICustomTypeArr;
        boolean isGetted = DataCameraGetPushStateInfo.getInstance().isGetted();
        CameraType cameraType = isGetted ? DataCameraGetPushStateInfo.getInstance().getCameraType() : CameraType.OTHER;
        int flycVersion = DataOsdGetPushCommon.getInstance().getFlycVersion();
        if (bool.booleanValue()) {
            DJICustomType[] dJICustomTypeArr2;
            ProductType c = i.getInstance().c();
            if (a(cameraType)) {
                dJICustomTypeArr2 = a(c, flycVersion) ? new DJICustomType[]{DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.GimbalDirec, DJICustomType.ForeArm, DJICustomType.OTHER} : new DJICustomType[]{DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.GimbalDirec, DJICustomType.OTHER};
            } else if (c == ProductType.Tomato) {
                dJICustomTypeArr2 = new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.CenterMetering, DJICustomType.AeLock, DJICustomType.ForeArm, DJICustomType.OTHER};
            } else if (c == ProductType.Pomato) {
                dJICustomTypeArr2 = new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.CenterMetering, DJICustomType.AeLock, DJICustomType.ForeArm, DJICustomType.Vision1, DJICustomType.Vision2, DJICustomType.OTHER};
            } else if (g(c)) {
                dJICustomTypeArr2 = a(c, flycVersion) ? new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.GimbalDirec, DJICustomType.CenterMetering, DJICustomType.ForeArm, DJICustomType.OTHER} : new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.GimbalDirec, DJICustomType.CenterMetering, DJICustomType.OTHER};
            } else if (i.getInstance().b() == CameraType.DJICameraTypeGD600) {
                dJICustomTypeArr2 = new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.GimbalDirec, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.AeLock, DJICustomType.CenterMetering, DJICustomType.OTHER};
            } else if (d(c)) {
                DJIGimbalType dJIGimbalType = DJIGimbalType.OTHER;
                if (DataGimbalGetPushType.getInstance().isGetted()) {
                    dJIGimbalType = DataGimbalGetPushType.getInstance().getType();
                }
                dJICustomTypeArr2 = dJIGimbalType == DJIGimbalType.Z15 ? new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.GimbalDirec, DJICustomType.CenterMetering, DJICustomType.OTHER} : new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.GimbalDirec, DJICustomType.CenterMetering, DJICustomType.OTHER};
            } else {
                dJICustomTypeArr2 = a(c, flycVersion) ? c(c) ? new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.CenterMetering, DJICustomType.ForeArm, DJICustomType.Navigation, DJICustomType.PlayBack, DJICustomType.AeLock, DJICustomType.CenterFocus, DJICustomType.OTHER} : new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.CenterMetering, DJICustomType.ForeArm, DJICustomType.OTHER} : (c == ProductType.A3 || c == ProductType.N3) ? new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.GimbalDirec, DJICustomType.CenterMetering, DJICustomType.OTHER} : new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.Bettery, DJICustomType.CenterMetering, DJICustomType.OTHER};
            }
            dJICustomTypeArr = dJICustomTypeArr2;
        } else {
            dJICustomTypeArr = a(cameraType) ? new DJICustomType[]{DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.OTHER} : new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.GimbalCenter, DJICustomType.SwitchGimbalMode, DJICustomType.MapSwitch, DJICustomType.ClearRote, DJICustomType.OTHER};
        }
        if (isGetted || dJICustomTypeArr == null || dJICustomTypeArr.length <= 0) {
            return dJICustomTypeArr;
        }
        DJICustomType[] dJICustomTypeArr3 = new DJICustomType[]{DJICustomType.CameraSetting, DJICustomType.CenterMetering};
        int length = dJICustomTypeArr.length;
        for (DJICustomType dJICustomType : dJICustomTypeArr) {
            for (DJICustomType dJICustomType2 : dJICustomTypeArr3) {
                if (dJICustomType2 == dJICustomType) {
                    length--;
                    break;
                }
            }
        }
        DJICustomType[] dJICustomTypeArr4 = new DJICustomType[length];
        int i = 0;
        int i2 = 0;
        while (i < r8) {
            for (DJICustomType dJICustomType3 : dJICustomTypeArr3) {
                if (dJICustomType3 == dJICustomTypeArr[i]) {
                    length = 1;
                    break;
                }
            }
            length = 0;
            if (length == 0) {
                length = i2 + 1;
                dJICustomTypeArr4[i2] = dJICustomTypeArr[i];
            } else {
                length = i2;
            }
            i++;
            i2 = length;
        }
        return dJICustomTypeArr4;
    }

    public static boolean a(CameraType cameraType) {
        return cameraType == CameraType.DJICameraTypeTau336 || cameraType == CameraType.DJICameraTypeTau640;
    }

    public static boolean k() {
        return (DataSmartBatteryGetPushDynamicData.getInstance().getStatus() & 17732923532771328L) != 0;
    }

    public static boolean v(ProductType productType) {
        if (ServiceManager.getInstance().isRemoteOK()) {
            if (productType == ProductType.Grape2 || productType == ProductType.A2) {
                return true;
            }
            return false;
        } else if (i.getInstance().a() == ProductType.Grape2 || productType == ProductType.A2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean w(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.Tomato || productType == ProductType.Pomato || c(productType) || (l() && (productType == ProductType.N3 || productType == ProductType.A3));
    }

    public static boolean l() {
        return DataOsdGetPushCommon.getInstance().getFlycVersion() >= 16;
    }

    public static long e(String str) {
        try {
            String[] split = str.split("\\.");
            int i = 0;
            long j = 0;
            while (i < split.length) {
                i++;
                j = ((long) Integer.parseInt(split[i], 10)) + (j * 256);
            }
            return j;
        } catch (Exception e) {
            return 0;
        }
    }
}
