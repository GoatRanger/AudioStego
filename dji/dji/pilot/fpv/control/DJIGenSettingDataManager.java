package dji.pilot.fpv.control;

import android.content.Context;
import dji.a.a;
import dji.apppublic.reflect.AppPublicReflect;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraFormatSDCard;
import dji.midware.data.model.P3.DataCameraFormatSSD;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetQuickPlayBack;
import dji.midware.data.model.P3.DataCameraGetVideoCaption;
import dji.midware.data.model.P3.DataCameraLoadParams;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.midware.data.model.P3.DataCameraSetPushChart;
import dji.midware.data.model.P3.DataCameraSetVideoCaption;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.data.model.P3.DataGimbalAutoCalibration;
import dji.midware.data.model.P3.DataGimbalRollFinetune;
import dji.midware.e.d;
import dji.midware.media.h.a.f;
import dji.pilot.publics.R;
import dji.pilot.publics.e.e;
import dji.pilot.publics.objects.g;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class DJIGenSettingDataManager {
    public static final int A = 3;
    public static final int B = 0;
    public static final int C = 1;
    public static final float[] D = new float[]{0.2f, 3.0f};
    private static final int E = 4096;
    private static final int F = 4097;
    private static final String G = "key_new_grid";
    private static final String H = "key_roll_finetune";
    private static final String I = "key_show_route";
    private static final String J = "key_coordinate";
    private static final String K = "key_use_amap";
    private static final String L = "key_wifi_setting_type";
    private static final String M = "key_open_video_buffer";
    private static final String N = "key_limit_video_buffer_space";
    private static final String O = "key_video_cache_size_index";
    private static final String P = "key_over_exposure_warner";
    private static final String Q = "key_temperature_unit";
    private static final String R = "key_open_bg_download";
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = 10;
    public static final int l = 11;
    public static final int m = 12;
    public static final int n = 13;
    public static final int o = 14;
    public static final int p = 15;
    public static final int q = 0;
    public static final int r = 1;
    public static final int s = 2;
    public static final int t = 3;
    public static final int u = 0;
    public static final int v = 1;
    public static final int w = 2;
    public static final int x = 0;
    public static final int y = 1;
    public static final int z = 2;
    private int S;
    private int T;
    private int U;
    private int V;
    private boolean W;
    private boolean X;
    private boolean Y;
    private boolean Z;
    private d aA;
    private d aB;
    private final a aC;
    private Context aD;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private int ae;
    private boolean af;
    private boolean ag;
    private boolean ah;
    private final float[] ai;
    private boolean aj;
    private final ArrayList<c> ak;
    private final DataCameraLoadParams al;
    private final DataCameraFormatSDCard am;
    private final DataCameraFormatSSD an;
    private final DataCameraGetQuickPlayBack ao;
    private final DataGimbalAutoCalibration ap;
    private final DataGimbalRollFinetune aq;
    private final DataCameraGetVideoCaption ar;
    private final DataCameraSetVideoCaption as;
    private final DataCameraSetPushChart at;
    private d au;
    private d av;
    private d aw;
    private d ax;
    private d ay;
    private d az;

    public static DJIGenSettingDataManager getInstance() {
        return d.a;
    }

    public void a(Context context) {
        this.aD = context;
        if (!this.aj) {
            boolean z;
            this.U = g.b(context, "key_new_grid", 0);
            int b = g.b(context, e.d, 3);
            if (!(b == 0 || b == 1)) {
                b = b == 2 ? 2 : Locale.US.getCountry().equals(context.getResources().getConfiguration().locale.getCountry()) ? 0 : 1;
            }
            this.S = b;
            if (this.S == 0) {
                z = true;
            } else {
                z = false;
            }
            AppPublicReflect.dji_gs_Config_setUnitFT(z);
            if (this.S == 1 || this.S == 2) {
                this.ai[0] = D[0];
                this.ai[1] = D[1];
            } else {
                this.ai[0] = b(D[0]);
                this.ai[1] = b(D[1]);
            }
            this.Z = g.b(context, "key_roll_finetune", this.Z);
            this.aa = g.b(context, "key_show_route", this.aa);
            this.ab = g.b(context, "key_coordinate", this.ab);
            AppPublicReflect.dji_gs_utils_GpsUtils_OPEN(this.ab);
            this.ac = g.b(context, "key_use_amap", this.ac);
            i(g.b(context, R, true));
            this.ad = g.b(context, "key_open_video_buffer", true);
            this.ae = g.b(context, O, 1);
            dji.midware.media.j.g.a(((long) (this.ae + 1)) * dji.midware.media.j.g.a);
            this.af = g.b(context, "key_limit_video_buffer_space", false);
            l(g.b(context, P, false));
            this.T = g.b(context, Q, 1);
            this.aj = true;
        }
    }

    public void a() {
    }

    public boolean a(c cVar) {
        if (cVar == null || this.ak.contains(cVar)) {
            return false;
        }
        this.ak.add(cVar);
        return true;
    }

    public boolean b(c cVar) {
        if (cVar != null) {
            return this.ak.remove(cVar);
        }
        return false;
    }

    public void b() {
        c();
    }

    public void c() {
        d();
        e();
    }

    public void d() {
        a(11, true);
        this.ar.start(this.aA);
    }

    public void e() {
        boolean isHistogramEnable = DataCameraGetPushStateInfo.getInstance().isHistogramEnable();
        if (this.X != isHistogramEnable) {
            this.X = isHistogramEnable;
            e(13);
        }
    }

    public void f() {
        this.aC.removeMessages(4096);
        this.aC.removeMessages(4097);
        f(0);
    }

    public void g() {
        x();
        a(1, false);
        this.al.setMode(USER.DEFAULT);
        this.al.start(this.ax);
    }

    public void h() {
        a(2, false);
        this.am.start(this.az);
    }

    public void i() {
        a(3, false);
        this.an.start(this.ay);
    }

    public boolean j() {
        return this.W;
    }

    public void a(boolean z) {
        if (this.W != z) {
            this.W = z;
            if (z) {
                dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_Switcher_VideoCaption_ON");
            } else {
                dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_Switcher_VideoCaption_OFF");
            }
            a(11, false);
            this.as.a().a(z).start(this.aB);
        }
    }

    public int k() {
        if (3 == this.U) {
            return 0;
        }
        return this.U;
    }

    public void a(int i) {
        if (i != this.U) {
            this.U = i;
            switch (i) {
                case 0:
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_PullDownView_ShowGrid_OFF");
                    break;
                case 1:
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_PullDownView_ShowGrid_GridLines");
                    break;
                case 2:
                    dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Camera_PullDownView_ShowGrid_Grid+Diagnoals");
                    break;
            }
            g.a(this.aD, "key_new_grid", i);
            AppPublicReflect.notifyConfigChange("key_new_grid");
            e(4);
        }
    }

    public boolean l() {
        return this.X;
    }

    public void b(boolean z) {
        if (this.X != z) {
            a(13, false);
            this.at.a(z).start(this.au);
        }
    }

    public void c(boolean z) {
        this.Y = z;
    }

    public boolean m() {
        return this.Y;
    }

    public int n() {
        return this.V;
    }

    public boolean o() {
        return this.Z;
    }

    public void d(boolean z) {
        if (this.Z != z) {
            this.Z = z;
            g.a(this.aD, "key_roll_finetune", z);
            AppPublicReflect.notifyConfigChange("key_roll_finetune");
            e(5);
        }
    }

    public void p() {
        a(7, false);
        this.ap.start(this.av);
    }

    public void a(byte b) {
        a(6, false);
        this.aq.setFineTuneValue(b).start(this.aw);
    }

    public boolean q() {
        return this.ac;
    }

    public boolean r() {
        return !a.getInstance().d() || this.ac;
    }

    public void e(boolean z) {
        if (this.ac != z) {
            this.ac = z;
            g.a(this.aD, "key_use_amap", z);
            AppPublicReflect.notifyConfigChange("key_use_amap");
            e(12);
        }
    }

    public boolean s() {
        return this.ab;
    }

    public void f(boolean z) {
        if (this.ab != z) {
            this.ab = z;
            if (z) {
                dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Map_Switcher_CalibrateMapCoordinates(ForChinaMainland)_ON");
            } else {
                dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Map_Switcher_CalibrateMapCoordinates(ForChinaMainland)_OFF");
            }
            g.a(this.aD, "key_coordinate", z);
            AppPublicReflect.notifyConfigChange("key_coordinate");
            e(10);
        }
    }

    public boolean t() {
        return this.aa;
    }

    public void g(boolean z) {
        if (this.aa != z) {
            this.aa = z;
            if (z) {
                dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Map_Switcher_ShowFlightRoute_ON");
            } else {
                dji.pilot.fpv.d.e.a("FPV_GeneralSettings_Map_Switcher_ShowFlightRoute_OFF");
            }
            g.a(this.aD, "key_show_route", z);
            AppPublicReflect.notifyConfigChange("key_show_route");
            e(8);
        }
    }

    public void h(boolean z) {
        if (this.ah != z) {
            this.ah = z;
            g.a(this.aD, R, z);
            AppPublicReflect.notifyConfigChange(R);
            e(15);
        }
    }

    public void u() {
        a(9, false, 0, null);
    }

    public int v() {
        return this.S;
    }

    public void b(int i) {
        if (3 > i && i >= 0 && i != this.S) {
            g.a(this.aD, e.d, i);
            this.S = i;
            AppPublicReflect.dji_gs_Config_setUnitFT(this.S == 0);
            A();
            if (i == 1) {
                this.ai[0] = D[0];
                this.ai[1] = D[1];
            } else {
                this.ai[0] = b(D[0]);
                this.ai[1] = b(D[1]);
            }
            e(0);
            c.a().e(b.a);
        }
    }

    public int w() {
        return this.T;
    }

    public void c(int i) {
        if (this.T != i) {
            g.a(this.aD, Q, i);
            this.T = i;
            c.a().e(b.b);
        }
    }

    public void x() {
        a(0);
        m(false);
    }

    public void handleConfigChanged(String str) {
        if ("key_new_grid".equals(str)) {
            a(g.b(this.aD, "key_new_grid", 0));
        } else if (e.d.equals(str)) {
            b(g.b(this.aD, e.d, 3));
        } else if ("key_roll_finetune".equals(str)) {
            d(g.b(this.aD, "key_roll_finetune", this.Z));
        } else if ("key_show_route".equals(str)) {
            g(g.b(this.aD, "key_show_route", this.aa));
        } else if ("key_coordinate".equals(str)) {
            f(g.b(this.aD, "key_coordinate", this.ab));
        } else if ("key_use_amap".equals(str)) {
            e(g.b(this.aD, "key_use_amap", this.ac));
        }
    }

    public float[] y() {
        return this.ai;
    }

    public boolean z() {
        if (this.S == 0) {
            return false;
        }
        return true;
    }

    public float a(float f) {
        if (this.S == 0) {
            return e.h(f);
        }
        if (this.S == 2) {
            return e.a(f);
        }
        return f;
    }

    public float b(float f) {
        if (this.S == 0) {
            return e.f(f);
        }
        return f;
    }

    public float c(float f) {
        if (this.S == 0) {
            return e.f(f);
        }
        if (2 == this.S) {
            return e.a(f);
        }
        return f;
    }

    public float d(float f) {
        if (this.S == 0) {
            return e.g(f);
        }
        return f;
    }

    public void A() {
        int i = 1;
        if (ServiceManager.getInstance().isRemoteOK()) {
            DataDm368SetGParams dataDm368SetGParams = new DataDm368SetGParams();
            CmdId cmdId = CmdId.g;
            if (this.S != 1) {
                i = 0;
            }
            dataDm368SetGParams.a(cmdId, i).start(null);
        }
    }

    private void a(int i, int i2, Object obj) {
        if (i2 == 0 && 11 == i) {
            this.W = this.ar.isGenerateVideoCaptionEnable();
        }
        a(i, true, i2, obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) obj : null);
    }

    private void b(int i, int i2, Object obj) {
        if (i2 == 0) {
            if (11 != i && 13 == i) {
            }
        } else if (11 == i) {
            this.W = !this.W;
        }
        a(i, false, i2, obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) obj : null);
    }

    private DJIGenSettingDataManager() {
        this.S = 1;
        this.T = 1;
        this.U = 0;
        this.V = 0;
        this.W = false;
        this.X = false;
        this.Y = false;
        this.Z = false;
        this.aa = true;
        this.ab = true;
        this.ac = false;
        this.ad = true;
        this.ae = 1;
        this.af = false;
        this.ag = false;
        this.ah = true;
        this.ai = new float[2];
        this.aj = false;
        this.au = null;
        this.av = null;
        this.aw = null;
        this.ax = null;
        this.ay = null;
        this.az = null;
        this.aA = null;
        this.aB = null;
        this.ak = new ArrayList(3);
        this.al = DataCameraLoadParams.getInstance();
        this.am = DataCameraFormatSDCard.getInstance();
        this.an = DataCameraFormatSSD.getInstance();
        this.ao = DataCameraGetQuickPlayBack.getInstance();
        this.ap = DataGimbalAutoCalibration.getInstance();
        this.aq = DataGimbalRollFinetune.getInstance();
        this.ar = DataCameraGetVideoCaption.getInstance();
        this.as = DataCameraSetVideoCaption.getInstance();
        this.at = DataCameraSetPushChart.getInstance();
        this.aC = new a(this);
        G();
        H();
        I();
    }

    private void G() {
        this.aA = new 1(this);
    }

    private void H() {
        this.aB = new 2(this);
        this.au = new 3(this);
    }

    private void I() {
        this.ax = new 4(this);
        this.az = new 5(this);
        this.ay = new 6(this);
        this.av = new 7(this);
        this.aw = new 8(this);
    }

    private void e(int i) {
        Iterator it = this.ak.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(i);
        }
    }

    private void f(int i) {
        Iterator it = this.ak.iterator();
        while (it.hasNext()) {
            ((c) it.next()).b(i);
        }
    }

    private void a(int i, boolean z) {
        Iterator it = this.ak.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(i, z);
        }
    }

    private void a(int i, boolean z, int i2, dji.midware.data.config.P3.a aVar) {
        Iterator it = this.ak.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(i, z, i2, aVar);
        }
    }

    public boolean B() {
        return this.ah;
    }

    public void i(boolean z) {
        if (this.ah != z) {
            this.ah = z;
        }
    }

    public boolean C() {
        return this.ad;
    }

    public void j(boolean z) {
        if (this.ad != z) {
            this.ad = z;
            g.a(this.aD, "key_open_video_buffer", z);
        }
    }

    public int D() {
        return this.ae;
    }

    public int d(int i) {
        if (this.ae != i) {
            this.ae = i;
            g.a(this.aD, O, i);
            dji.midware.media.j.g.a(((long) (i + 1)) * dji.midware.media.j.g.a);
        }
        return (int) (dji.midware.media.j.g.d() / dji.midware.media.j.g.b);
    }

    public boolean E() {
        return this.af;
    }

    public void k(boolean z) {
        if (this.af != z) {
            this.af = z;
            g.a(this.aD, "key_limit_video_buffer_space", z);
        }
    }

    public boolean F() {
        return this.ag;
    }

    public void l(boolean z) {
        this.ag = z;
        c.a().e(new f.a(this.ag, R.raw.overexposure));
    }

    public void m(boolean z) {
        if (this.ag != z) {
            l(z);
            g.a(this.aD, P, z);
            AppPublicReflect.notifyConfigChange(P);
            e(14);
        }
    }
}
