package dji.midware.c;

import android.content.Context;
import android.os.Handler;
import dji.log.DJILog;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushPowerStatus;
import dji.midware.util.i;

public class a {
    private static final String b = "DJIComponentManager";
    private static final boolean c = false;
    private static a d = null;
    public String a = "";
    private c e = c.None;
    private c f = c.None;
    private d g = d.None;
    private d h = d.None;
    private a i = a.None;
    private a j = a.None;
    private b k = b.None;
    private b l = b.None;
    private Handler m = null;
    private Context n;
    private int o = 0;
    private boolean p = false;
    private Runnable q = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a(a.b, "updateRunnableFirst ", true);
            this.a.o();
            this.a.Q();
            this.a.m.postDelayed(this.a.r, 2500);
        }
    };
    private Runnable r = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a(a.b, "updateRunnableSecond ", true);
            this.a.o();
            this.a.Q();
            this.a.m.postDelayed(this.a.s, 8000);
        }
    };
    private Runnable s = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a(a.b, "updateRunnableThird ", true);
            this.a.o();
            this.a.Q();
        }
    };

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[CameraType.values().length];

        static {
            try {
                a[CameraType.DJICameraTypeFC260.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[CameraType.DJICameraTypeFC300S.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[CameraType.DJICameraTypeFC300X.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[CameraType.DJICameraTypeFC300XW.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[CameraType.DJICameraTypeFC330X.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[CameraType.DJICameraTypeFC350.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[CameraType.DJICameraTypeFC550.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[CameraType.DJICameraTypeFC550Raw.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[CameraType.DJICameraTypeTau336.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[CameraType.DJICameraTypeTau640.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[CameraType.DJICameraTypeFC220.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[CameraType.DJICameraTypeFC220S.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[CameraType.DJICameraTypeFC6310.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[CameraType.DJICameraTypeCV600.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[CameraType.DJICameraTypeGD600.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    public enum a {
        None,
        P3x,
        P3s,
        P3c,
        P3w,
        P4,
        X3,
        X5,
        X5R,
        TAU336,
        TAU640,
        FoldingDroneX,
        FoldingDroneS,
        Z3,
        Pomato,
        GD600,
        Unknow
    }

    public enum b {
        None,
        Ronin,
        Unknow
    }

    public enum c {
        None,
        P3x,
        P3s,
        P3c,
        P3w,
        Inspire,
        M100,
        OSMO,
        OSMOMobile,
        P4,
        M600,
        Inspire2,
        FoldingDrone,
        Pomato,
        Unknow
    }

    public enum d {
        None,
        P3P4,
        P3c,
        P3w,
        Inspire,
        LB2,
        P4,
        FoldingDrone,
        Inspire2,
        Unknow
    }

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (d == null) {
                d = new a();
            }
            aVar = d;
        }
        return aVar;
    }

    private a() {
    }

    public void a(Context context) {
        dji.midware.c.a.a.getInstance().b();
        this.n = context;
        this.f = k();
        this.h = l();
        this.j = m();
        this.m = new Handler(dji.midware.util.b.b());
        o();
        dji.thirdparty.a.c.a().a(this);
        dji.logic.a.a.getInstance().a(context);
    }

    private c k() {
        return c.values()[i.b(this.n, "DJIComponentManager_platform", 0)];
    }

    private d l() {
        return d.values()[i.b(this.n, "DJIComponentManager_rc", 0)];
    }

    private a m() {
        return a.values()[i.b(this.n, "DJIComponentManager_camera", 0)];
    }

    private b n() {
        return b.values()[i.b(this.n, "DJIComponentManager_gimbal", 0)];
    }

    private void b(c cVar) {
        i.a(this.n, "DJIComponentManager_platform", cVar.ordinal());
    }

    private void a(d dVar) {
        i.b(this.n, "DJIComponentManager_rc", dVar.ordinal());
    }

    private void a(a aVar) {
        i.b(this.n, "DJIComponentManager_camera", aVar.ordinal());
    }

    private void a(b bVar) {
        i.b(this.n, "DJIComponentManager_gimbal", bVar.ordinal());
    }

    public c a() {
        return this.e;
    }

    public void a(c cVar) {
        this.e = cVar;
    }

    public c b() {
        return this.f;
    }

    public d c() {
        return this.g;
    }

    public d d() {
        return this.h;
    }

    public a e() {
        return this.i;
    }

    public a f() {
        return this.j;
    }

    public b g() {
        return this.k;
    }

    public b h() {
        return this.l;
    }

    public boolean i() {
        return this.e != c.None;
    }

    public boolean j() {
        return this.g != d.None;
    }

    private void o() {
        a(b, "updateValue", true);
        boolean p = p();
        boolean q = q();
        boolean s = s();
        boolean t = t();
        if (p) {
            dji.thirdparty.a.c.a().e(this.e);
            if (this.e == null || this.e == c.None) {
                this.p = false;
            }
        }
        if (q) {
            dji.thirdparty.a.c.a().e(this.g);
        }
        if (s) {
            dji.thirdparty.a.c.a().e(this.i);
            if (this.i == null || this.i == a.None) {
                this.p = false;
            }
        }
        if (t) {
            dji.thirdparty.a.c.a().e(this.k);
        }
        if (p || q || s || t) {
            DJILog.i(b, "====================", true, true);
            DJILog.i(b, "PlaformType : " + this.e, true, true);
            DJILog.i(b, "RcType : " + this.g, true, true);
            DJILog.i(b, "CameraType : " + this.i, true, true);
            DJILog.i(b, "GimbalType : " + this.k, true, true);
            DJILog.i(b, "LastPlatformType : " + this.f, true, true);
            DJILog.i(b, "LastRcType : " + this.h, true, true);
            DJILog.i(b, "LastCameraType : " + this.j, true, true);
            DJILog.i(b, "LastGimbalType : " + this.l, true, true);
            DJILog.i(b, "Petyr count : " + this.o, true, true);
            DJILog.i(b, "====================", true, true);
            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.append("\r\n ========================");
            stringBuilder.append("\r\n PlaformType : " + this.e);
            stringBuilder.append("\r\n RcType : " + this.g);
            stringBuilder.append("\r\n CameraType : " + this.i);
            stringBuilder.append("\r\n GimbalType:" + this.k);
            stringBuilder.append("\r\n LastPlatformType : " + this.f);
            stringBuilder.append("\r\n LastRcComponentType : " + this.h);
            stringBuilder.append("\r\n LastCameraComponentType : " + this.j);
            stringBuilder.append("\r\n LastGimbalComponentType : " + this.l);
            stringBuilder.append("\r\n count : " + this.o);
            stringBuilder.append("\r\n ========================");
            stringBuilder.append("\r\n " + this.a);
            this.a = stringBuilder.toString();
            dji.thirdparty.a.c.a().e(this);
            this.o++;
        }
    }

    private boolean p() {
        c cVar = c.None;
        if (u() && c(cVar)) {
            cVar = c.P3x;
        }
        if (v() && c(r0)) {
            cVar = c.P3s;
        }
        if (w() && c(r0)) {
            cVar = c.P3c;
        }
        if (x() && c(r0)) {
            cVar = c.P3w;
        }
        if (y() && c(r0)) {
            cVar = c.Inspire;
        }
        if (z() && c(r0)) {
            cVar = c.M100;
        }
        if (A() && c(r0)) {
            cVar = c.OSMO;
        }
        if (B() && c(r0)) {
            cVar = c.OSMOMobile;
        }
        if (D() && c(r0)) {
            cVar = c.P4;
        }
        if (F() && c(r0)) {
            cVar = c.M600;
        }
        if (H() && c(r0)) {
            cVar = c.Inspire2;
        }
        if (G() && c(r0)) {
            cVar = c.FoldingDrone;
        }
        if (E() && c(r0)) {
            cVar = c.Pomato;
        }
        if (cVar == c.None && I() && c(cVar)) {
            cVar = c.Unknow;
        }
        if (this.e == cVar) {
            return false;
        }
        if (this.e != c.None) {
            this.f = this.e;
            b(this.f);
        }
        this.e = cVar;
        return true;
    }

    private boolean c(c cVar) {
        if (r() || cVar == c.None) {
            return true;
        }
        throw new RuntimeException("reconige logic is wrong: " + cVar);
    }

    private boolean q() {
        d dVar = d.None;
        if (J() && b(dVar)) {
            dVar = d.P3P4;
        }
        if (K() && b(r0)) {
            dVar = d.P3c;
        }
        if (L() && b(r0)) {
            dVar = d.P3w;
        }
        if (M() && b(r0)) {
            dVar = d.Inspire;
        }
        if (N() && b(r0)) {
            dVar = d.LB2;
        }
        if (O() && b(r0)) {
            dVar = d.FoldingDrone;
        }
        if (this.g == dVar) {
            return false;
        }
        if (this.g != d.None) {
            this.h = this.g;
            a(this.h);
        }
        this.g = dVar;
        return true;
    }

    private boolean b(d dVar) {
        if (!(r() || dVar == d.None)) {
        }
        return true;
    }

    private boolean r() {
        return false;
    }

    private boolean s() {
        a aVar = a.None;
        if (ServiceManager.getInstance().isRemoteOK()) {
            DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
            if (!(instance == null || !instance.isGetted() || instance.getCameraType() == null)) {
                CameraType cameraType = instance.getCameraType();
                switch (AnonymousClass4.a[cameraType.ordinal()]) {
                    case 1:
                        aVar = a.P3c;
                        break;
                    case 2:
                        aVar = a.P3s;
                        break;
                    case 3:
                        aVar = a.P3x;
                        break;
                    case 4:
                        aVar = a.P3w;
                        break;
                    case 5:
                        aVar = a.P4;
                        break;
                    case 6:
                        aVar = a.X3;
                        break;
                    case 7:
                        aVar = a.X5;
                        break;
                    case 8:
                        aVar = a.X5R;
                        break;
                    case 9:
                        aVar = a.TAU336;
                        break;
                    case 10:
                        aVar = a.TAU640;
                        break;
                    case 11:
                        aVar = a.FoldingDroneX;
                        break;
                    case 12:
                        aVar = a.FoldingDroneS;
                        break;
                    case 13:
                        aVar = a.Pomato;
                        break;
                    case 14:
                        aVar = a.Z3;
                        break;
                    case 15:
                        aVar = a.GD600;
                        break;
                }
                if (a.None == aVar && CameraType.OTHER != cameraType) {
                    aVar = a.Unknow;
                }
            }
        }
        if (this.i == aVar) {
            return false;
        }
        if (this.i != a.None) {
            this.j = this.i;
            a(this.j);
        }
        this.i = aVar;
        return true;
    }

    private boolean t() {
        b bVar = b.None;
        if (P() && b(bVar)) {
            bVar = b.Ronin;
        }
        if (this.k == bVar) {
            return false;
        }
        if (this.k != b.None) {
            this.l = this.k;
            a(this.l);
        }
        this.k = bVar;
        return true;
    }

    private boolean b(b bVar) {
        if (bVar == b.None) {
            return true;
        }
        throw new RuntimeException("the logic of gimbal recognition has error, need fix : " + bVar);
    }

    private boolean u() {
        a(b, "=====isPlatformP3x=====", false);
        a(b, "isRemoteOK : " + ServiceManager.getInstance().isRemoteOK(), false);
        a(b, "isGetted : " + DataCameraGetPushStateInfo.getInstance().isGetted(), false);
        a(b, "CameraType : " + DataCameraGetPushStateInfo.getInstance().getCameraType(), false);
        a(b, "OSD is get : " + DataOsdGetPushCommon.getInstance().getDroneType(), false);
        a(b, "OSD type : " + DataOsdGetPushCommon.getInstance().isGetted(), false);
        a(b, "========================\r\n", false);
        if (ServiceManager.getInstance().isRemoteOK() && DataCameraGetPushStateInfo.getInstance().isGetted() && DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC300X) {
            return true;
        }
        return false;
    }

    private boolean v() {
        if (ServiceManager.getInstance().isRemoteOK() && DataCameraGetPushStateInfo.getInstance().isGetted() && DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC300S) {
            return true;
        }
        return false;
    }

    private boolean w() {
        if (ServiceManager.getInstance().isRemoteOK() && DataCameraGetPushStateInfo.getInstance().isGetted() && DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC260) {
            return true;
        }
        return false;
    }

    private boolean x() {
        if (ServiceManager.getInstance().isRemoteOK() && DataCameraGetPushStateInfo.getInstance().isGetted() && DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC300XW) {
            return true;
        }
        return false;
    }

    private boolean y() {
        if (ServiceManager.getInstance().isRemoteOK() && DataCameraGetPushStateInfo.getInstance().isGetted() && !((dji.midware.f.a.getInstance().d() != dji.midware.f.b.d && dji.midware.f.a.getInstance().d() != dji.midware.f.b.a) || DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.OpenFrame || DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.PM820 || DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.PM820PRO)) {
            CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
            if (cameraType == CameraType.DJICameraTypeFC350 || cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw || cameraType == CameraType.DJICameraTypeTau336 || cameraType == CameraType.DJICameraTypeTau640) {
                a(b, "=====isPlatformInspire=====", false);
                return true;
            }
        }
        if (ServiceManager.getInstance().isRemoteOK() && DataOsdGetPushCommon.getInstance().isGetted() && DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.Inspire) {
            return true;
        }
        return false;
    }

    private boolean z() {
        if (!ServiceManager.getInstance().isRemoteOK() || !DataOsdGetPushCommon.getInstance().isGetted() || DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.OpenFrame) {
            return false;
        }
        a(b, "=====isPlatformM100=====", false);
        return true;
    }

    private boolean A() {
        if (ServiceManager.getInstance().isRemoteOK() && DataCameraGetPushStateInfo.getInstance().isGetted() && dji.midware.f.a.getInstance().d() == dji.midware.f.b.e) {
            CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
            if (cameraType == CameraType.DJICameraTypeFC350 || cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw) {
                return true;
            }
        }
        DataOsdGetPushPowerStatus instance = DataOsdGetPushPowerStatus.getInstance();
        if (instance.isGetted() && instance.getPowerStatus() == 1) {
            return true;
        }
        return false;
    }

    private boolean B() {
        a(b, "=====isPlatformOSMOMobile=====", false);
        a(b, "isRemoteOK: " + ServiceManager.getInstance().isRemoteOK(), false);
        if (!ServiceManager.getInstance().isRemoteOK() || dji.midware.f.a.getInstance().d() != dji.midware.f.b.g) {
            return false;
        }
        DJILog.d(b, "isPlatformOSMOMobile: true");
        return true;
    }

    private boolean C() {
        if (!N()) {
            return false;
        }
        a(b, "=====isPlatformLB2=====", false);
        if (!DataOsdGetPushCommon.getInstance().isGetted() || DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.PM820 || DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.PM820PRO) {
            return false;
        }
        return true;
    }

    private boolean D() {
        if (ServiceManager.getInstance().isRemoteOK() && DataCameraGetPushStateInfo.getInstance().isGetted() && DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC330X) {
            return true;
        }
        return false;
    }

    private boolean E() {
        if (ServiceManager.getInstance().isRemoteOK() && DataCameraGetPushStateInfo.getInstance().isGetted() && DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC6310) {
            return true;
        }
        return false;
    }

    private boolean F() {
        if (!ServiceManager.getInstance().isRemoteOK() || DataOsdGetPushCommon.getInstance().isPushLosed()) {
            return false;
        }
        if (DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.PM820 || DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.PM820PRO) {
            return true;
        }
        return false;
    }

    private boolean G() {
        if (ServiceManager.getInstance().isRemoteOK() && DataOsdGetPushCommon.getInstance().isGetted()) {
            return DataOsdGetPushCommon.getInstance().getDroneType().equals(DroneType.wm220);
        }
        return false;
    }

    private boolean H() {
        if (ServiceManager.getInstance().isRemoteOK() && !DataOsdGetPushCommon.getInstance().isPushLosed() && DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.Orange2) {
            return true;
        }
        return false;
    }

    private boolean I() {
        return ServiceManager.getInstance().isRemoteOK();
    }

    private boolean J() {
        if (v() || u() || D() || E()) {
            return true;
        }
        a(b, "==========isRcP3x=========", false);
        if (!ServiceManager.getInstance().isOK()) {
            return false;
        }
        ProductType d = dji.midware.c.a.a.getInstance().d();
        if (d == ProductType.litchiX || d == ProductType.litchiS || d == ProductType.Tomato) {
            return true;
        }
        return false;
    }

    private boolean K() {
        if (w()) {
            return true;
        }
        if (!ServiceManager.getInstance().isOK()) {
            return false;
        }
        if (dji.midware.c.a.a.getInstance().d() != ProductType.litchiC) {
            return false;
        }
        return true;
    }

    private boolean L() {
        if (x()) {
            return true;
        }
        if (!ServiceManager.getInstance().isOK()) {
            return false;
        }
        if (dji.midware.c.a.a.getInstance().d() != ProductType.P34K) {
            return false;
        }
        return true;
    }

    private boolean M() {
        if (J()) {
            return false;
        }
        if (y()) {
            return true;
        }
        if (ServiceManager.getInstance().isOK() && !N() && dji.midware.c.a.a.getInstance().d() == ProductType.Orange) {
            return true;
        }
        return false;
    }

    private boolean N() {
        if (ServiceManager.getInstance().isOK() && dji.midware.c.a.a.getInstance().d() == ProductType.Grape2) {
            return true;
        }
        return false;
    }

    private boolean O() {
        if (!ServiceManager.getInstance().isOK()) {
            return false;
        }
        ProductType d = dji.midware.c.a.a.getInstance().d();
        if (d == ProductType.KumquatS || d == ProductType.KumquatX) {
            return true;
        }
        return false;
    }

    private boolean P() {
        if (DataGimbalGetPushType.getInstance().isGetted() && DataGimbalGetPushType.getInstance().getType() == DJIGimbalType.Ronin) {
            return true;
        }
        return false;
    }

    public void onEventBackgroundThread(dji.midware.f.b bVar) {
        a(b, "DJILinkType linkType", false);
        R();
    }

    public void onEventBackgroundThread(p pVar) {
        a(b, "DataEvent event", false);
        R();
    }

    public void onEventBackgroundThread(o oVar) {
        a(b, "DataCameraEvent event", false);
        R();
    }

    public void onEventBackgroundThread(dji.midware.c.a.a aVar) {
        a(b, "DJIRcDetectHelper event", false);
        R();
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        a(b, "DataCameraGetPushStateInfo event", false);
        if (!this.p) {
            this.p = true;
            R();
        }
    }

    public void onEventBackgroundThread(dji.midware.b.a.c cVar) {
        a(b, "BLEEvent event", false);
        if (cVar == dji.midware.b.a.c.BLE_DEVICE_CONNECTED || cVar == dji.midware.b.a.c.BLE_DEVICE_DISCONNECTED) {
            R();
        }
    }

    private void Q() {
        this.m.removeCallbacks(this.q);
        this.m.removeCallbacks(this.r);
        this.m.removeCallbacks(this.s);
    }

    private void R() {
        a(b, "updateValueDelay isUpgradeDelay ", true);
        Q();
        this.m.postDelayed(this.q, 1000);
    }

    private void a(String str, String str2, boolean z) {
        if (z) {
            DJILogHelper.getInstance().LOGD(str, str2, false, z);
        }
    }
}
