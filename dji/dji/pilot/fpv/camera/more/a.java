package dji.pilot.fpv.camera.more;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetIso.TYPE;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraGetShotInfo;
import dji.midware.data.model.P3.DataCameraSetAperture;
import dji.midware.data.model.P3.DataCameraSetExposureMode;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetFocusWindow;
import dji.midware.data.model.P3.DataCameraSetIso;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCameraSetPhoto;
import dji.midware.data.model.P3.DataCameraSetVideoEncode.VideoEncodeType;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.d.b;
import dji.pilot.fpv.camera.more.d.d;
import dji.pilot.fpv.camera.more.d.e;
import dji.pilot.fpv.camera.more.d.f;
import dji.pilot.fpv.camera.more.d.g;
import dji.thirdparty.a.c;
import java.util.Locale;

public class a implements d, b, d, e, f, g {
    private static final String ae = "_value";
    private static final int af = 0;
    private static final int am = 4096;
    private static final int an = 4097;
    private static final int ao = 8192;
    private static final int ap = 8193;
    private static final int aq = 8194;
    private static final int ar = 8195;
    private static final long as = 300;
    private static final long at = 100;
    private static final int au = 8;
    private volatile boolean C = false;
    private Context D = null;
    private String[] E = null;
    private int[] F = null;
    private String[] G = null;
    private int[] H = null;
    private String[] I = null;
    private int[] J = null;
    private String[] K = null;
    private String[] L = null;
    private int[] M = null;
    private String[] N = null;
    private String[] O = null;
    private String[] P = null;
    private String[] Q = null;
    private int[] R = null;
    private String[] S = null;
    private String[] T = null;
    private int[] U = null;
    private int[] V = null;
    private SoundPool W = null;
    private int X = 0;
    private int Y = 0;
    private int Z = 0;
    public b a = null;
    private String[] aA = null;
    private int[] aB = null;
    private String[] aC = null;
    private int[] aD = null;
    private String[] aE = null;
    private int[] aF = null;
    private String[] aG = null;
    private int[] aH = null;
    private String[] aI = null;
    private int[] aJ = null;
    private String[] aK = null;
    private int[] aL = null;
    private String[] aM = null;
    private int[] aN = null;
    private String[] aO = null;
    private String[] aP = null;
    private String[] aQ = null;
    private int[] aR = null;
    private String[] aS = null;
    private int[] aT = null;
    private String[] aU = null;
    private String[] aV = null;
    private int[] aW = null;
    private String[] aX = null;
    private String[] aY = null;
    private int[] aZ = null;
    private int aa = 0;
    private dji.pilot.fpv.camera.more.d.a ab = dji.pilot.fpv.camera.more.d.a.a;
    private int ac = 0;
    private dji.pilot.fpv.camera.more.d.a ad = dji.pilot.fpv.camera.more.d.a.a;
    private volatile boolean ag = false;
    private volatile boolean ah = true;
    private volatile boolean ai = false;
    private CameraType aj = CameraType.OTHER;
    private volatile int ak = 0;
    private dji.pilot.fpv.camera.more.a.b al = null;
    private Handler av = new 1(this);
    private String[] aw = null;
    private int[] ax = null;
    private String[] ay = null;
    private int[] az = null;
    protected dji.pilot.fpv.camera.more.a.a b = null;
    private String[] ba = null;
    private int[] bb = null;
    private String[] bc = null;
    private int[] bd = null;
    private String[] be = null;
    private int[] bf = null;
    private c bg = null;
    private boolean bh = false;

    public static final void a(String str) {
        dji.pilot.fpv.camera.a.a.a(null, str);
    }

    public void a() {
        a("****had[" + this.ah + "," + this.ag + dji.pilot.usercenter.protocol.d.H);
        if (!this.ah) {
            this.ah = true;
            aI();
        }
    }

    private void aI() {
        if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD) {
            aJ();
        } else {
            DataCameraSetMode.getInstance().a(MODE.RECORD).start(new 2(this));
        }
    }

    private void aJ() {
        if (DataCameraGetPushShotParams.getInstance().getExposureMode() == ExposureMode.d) {
            aK();
        } else {
            new DataCameraSetExposureMode().a(ExposureMode.d.a()).start(new 3(this));
        }
    }

    private void aK() {
        if (170 == DataCameraGetPushShotParams.getInstance().getRealApertureSize()) {
            aL();
        } else {
            new DataCameraSetAperture().a((short) 170).start(new 4(this));
        }
    }

    private void aL() {
        int iso = DataCameraGetPushShotParams.getInstance().getISO();
        if (iso != 0 && iso != 1) {
            new DataCameraSetIso().a(true).a(TYPE.AUTO).start(new 5(this));
        }
    }

    private void aM() {
        DataCameraGetPushShotInfo.getInstance().getXAxisFocusWindowNum();
        DataCameraGetPushShotInfo.getInstance().getYAxisFocusWindowNum();
        DataCameraSetFocusWindow.getInstance().a(2).b(2).c(1).start(null);
    }

    public void b() {
        if (this.ag) {
            this.ai = true;
            c.a().e(a.d);
        }
    }

    public void c() {
        DataCameraSetFocusWindow.getInstance().a(1).b(1).c(0).start(null);
        this.ag = false;
        this.ai = false;
        this.ak = 0;
        new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.SetFocusMode).a(FuselageFocusMode.Manual.value()).start(null);
    }

    public void d() {
        DataCameraSetFocusWindow.getInstance().a(1).b(1).c(0).start(null);
        String format = String.format(Locale.US, "%d%d%d", new Object[]{Integer.valueOf(DataCameraGetShotInfo.getInstance().getMemberId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getModelId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getHardVersion())});
        this.ag = false;
        this.ai = false;
        this.ak = DataCameraGetPushShotInfo.getInstance().getShotFocusCurStroke();
        a("MF Demarcate Key[" + format + "]value[" + this.ak + dji.pilot.usercenter.protocol.d.H);
        dji.pilot.publics.objects.g.a(this.D, format, false);
        dji.pilot.publics.objects.g.a(this.D, format + ae, this.ak);
        new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.SetFocusMode).a(FuselageFocusMode.Manual.value()).start(null);
    }

    public void e() {
        String format = String.format(Locale.US, "%d%d%d", new Object[]{Integer.valueOf(DataCameraGetShotInfo.getInstance().getMemberId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getModelId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getHardVersion())});
        this.ag = true;
        this.ah = false;
        this.ai = true;
        this.ak = 0;
        dji.pilot.publics.objects.g.a(this.D, format, true);
        dji.pilot.publics.objects.g.a(this.D, format + ae, 0);
        new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.SetFocusMode).a(FuselageFocusMode.OneAuto.value()).start(null);
        c.a().e(a.d);
    }

    public boolean f() {
        return this.ai;
    }

    public boolean g() {
        return this.ag;
    }

    public int h() {
        if (this.ag) {
            return 0;
        }
        return this.ak;
    }

    public void onEventMainThread(dji.pilot.fpv.camera.focus.DJIMFDemarcateView.a aVar) {
        if (aVar == dji.pilot.fpv.camera.focus.DJIMFDemarcateView.a.b && this.ag) {
            a();
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        FuselageFocusMode fuselageFocusMode = DataCameraGetPushShotInfo.getInstance().getFuselageFocusMode();
        if (!this.ag) {
            return;
        }
        if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
            this.ai = true;
            c.a().e(a.d);
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.ConnectOK) {
            this.ag = false;
        } else if (oVar == o.ConnectLose) {
            this.aj = CameraType.OTHER;
            this.ag = false;
            this.ah = true;
            this.ai = false;
        }
        this.b.a(oVar);
        this.al.a(oVar);
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (this.aj != cameraType) {
            this.aj = cameraType;
            if (dji.pilot.fpv.d.b.c(cameraType)) {
                a(0);
            }
            if (dji.pilot.fpv.d.b.k(null)) {
                Resources resources = this.D.getResources();
                this.N = resources.getStringArray(R.array.b8);
                this.S = resources.getStringArray(R.array.ba);
                this.T = resources.getStringArray(R.array.j);
                this.U = resources.getIntArray(R.array.l);
                this.aw = resources.getStringArray(R.array.c);
                this.ax = resources.getIntArray(R.array.g);
            }
            this.b.a(cameraType);
            this.al.a(cameraType);
        }
    }

    public void a(int i) {
        if (i < 8 && !this.av.hasMessages(4096)) {
            DataCameraGetShotInfo.getInstance().start(new 6(this, i));
        }
    }

    public int[] i() {
        return this.b.a();
    }

    public int[] j() {
        return this.b.b();
    }

    public String[] k() {
        return this.b.c();
    }

    public int a(int i, int i2) {
        return this.b.a(i, i2);
    }

    public int[] b(int i) {
        return this.b.a(i);
    }

    public DataCameraSetPhoto.TYPE l() {
        return this.b.d();
    }

    public void m() {
        this.b.e();
    }

    public int n() {
        return this.b.f();
    }

    public int a(DataCameraSetPhoto.TYPE type, int i) {
        return this.b.a(type, i);
    }

    public boolean b(DataCameraSetPhoto.TYPE type, int i) {
        return this.b.b(type, i);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        this.b.a(dataCameraGetPushShotParams);
    }

    public int[] o() {
        return this.b.g();
    }

    public int[] p() {
        return this.b.h();
    }

    public String[] q() {
        return this.b.i();
    }

    public int[] c(int i) {
        return this.b.b(i);
    }

    public static a getInstance() {
        return c.a();
    }

    public synchronized void a(Context context) {
        if (!this.C) {
            this.C = true;
            this.D = context.getApplicationContext();
            this.b = new dji.pilot.fpv.camera.more.a.a(this.D);
            aN();
            this.bg = new c(context);
            this.al = new dji.pilot.fpv.camera.more.a.b(context);
            this.aa = dji.pilot.publics.objects.g.b(context, "key_camera_centerpoints", 0);
            this.ab = dji.pilot.fpv.camera.more.d.a.find(dji.pilot.publics.objects.g.b(context, "key_camera_cp_color", dji.pilot.fpv.camera.more.d.a.a.b()));
            this.ac = dji.pilot.publics.objects.g.b(context, "key_camera_lineref", 0);
            this.ad = dji.pilot.fpv.camera.more.d.a.find(dji.pilot.publics.objects.g.b(context, "key_camera_lineref_color", dji.pilot.fpv.camera.more.d.a.a.b()));
            aq_[4][0] = dji.pilot.publics.objects.g.b(context, "key_camera_lineref_cwidth", aq_[4][0]);
            aq_[4][1] = dji.pilot.publics.objects.g.b(context, "key_camera_lineref_cheight", aq_[4][1]);
            c.a().a((Object) this, 50);
        }
    }

    public synchronized void r() {
        if (this.C) {
            this.C = false;
            c.a().d((Object) this);
        }
    }

    public int s() {
        return this.aa;
    }

    public void d(int i) {
        if (i != this.aa) {
            this.aa = i;
            dji.pilot.publics.objects.g.a(this.D, "key_camera_centerpoints", i);
            c.a().e(a.e);
        }
    }

    public dji.pilot.fpv.camera.more.d.a t() {
        return this.ab;
    }

    public void a(dji.pilot.fpv.camera.more.d.a aVar) {
        if (aVar != this.ab) {
            this.ab = aVar;
            dji.pilot.publics.objects.g.a(this.D, "key_camera_cp_color", aVar.b());
            c.a().e(a.f);
        }
    }

    public int u() {
        return this.ac;
    }

    public void e(int i) {
        if (i != this.ac) {
            this.ac = i;
            dji.pilot.publics.objects.g.a(this.D, "key_camera_lineref", i);
            c.a().e(a.g);
        }
    }

    public dji.pilot.fpv.camera.more.d.a v() {
        return this.ad;
    }

    public void b(dji.pilot.fpv.camera.more.d.a aVar) {
        if (aVar != this.ad) {
            this.ad = aVar;
            dji.pilot.publics.objects.g.a(this.D, "key_camera_lineref_color", aVar.b());
            c.a().e(a.h);
        }
    }

    public float w() {
        return aq_[4][0];
    }

    public void a(float f) {
        if (Math.abs(f - aq_[4][0]) >= 0.005f) {
            aq_[4][0] = f;
            dji.pilot.publics.objects.g.a(this.D, "key_camera_lineref_cwidth", f);
            c.a().e(a.i);
        }
    }

    public float x() {
        return aq_[4][1];
    }

    public void b(float f) {
        if (Math.abs(f - aq_[4][1]) >= 0.005f) {
            aq_[4][1] = f;
            dji.pilot.publics.objects.g.a(this.D, "key_camera_lineref_cheight", f);
            c.a().e(a.i);
        }
    }

    public void y() {
        if (this.W == null) {
            this.W = new SoundPool(2, 3, 0);
            this.X = this.W.load(this.D, R.raw.camera_simple_click, 1);
            this.Y = this.W.load(this.D, R.raw.camera_ev_center, 1);
            this.Z = this.W.load(this.D, R.raw.camera_focus_beep, 1);
        }
    }

    public void z() {
        if (this.W != null) {
            this.W.unload(this.X);
            this.W.unload(this.Y);
            this.W.unload(this.Z);
            this.W.release();
            this.W = null;
        }
        this.X = 0;
        this.Y = 0;
        this.Z = 0;
    }

    public void A() {
        x(this.X);
    }

    public void B() {
        x(this.Y);
    }

    public void C() {
        x(this.Z);
    }

    private void x(int i) {
        float f = 0.3f;
        AudioManager audioManager = (AudioManager) this.D.getApplicationContext().getSystemService("audio");
        float streamVolume = ((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3));
        if (streamVolume >= 0.3f) {
            f = streamVolume;
        }
        this.W.play(i, f, f, 100, 0, 1.0f);
    }

    public int f(int i) {
        int[] iArr = E_;
        if (i < 0 || i >= iArr.length) {
            return iArr[0];
        }
        return iArr[i];
    }

    public String[] D() {
        return this.L;
    }

    public int g(int i) {
        return a(this.M, i, 0);
    }

    public int h(int i) {
        return this.M[i];
    }

    public int[] E() {
        return this.M;
    }

    public String[] F() {
        return this.N;
    }

    public String[] i(int i) {
        Resources resources = this.D.getResources();
        switch (i) {
            case 1:
                this.O = resources.getStringArray(R.array.b1);
                this.P = resources.getStringArray(R.array.av);
                break;
            case 2:
                this.O = resources.getStringArray(R.array.b2);
                this.P = resources.getStringArray(R.array.aw);
                break;
            case 3:
                this.O = resources.getStringArray(R.array.b3);
                this.P = resources.getStringArray(R.array.ax);
                break;
            case 4:
                this.O = resources.getStringArray(R.array.b4);
                this.P = resources.getStringArray(R.array.ay);
                break;
            case 5:
                this.O = resources.getStringArray(R.array.b5);
                this.P = resources.getStringArray(R.array.az);
                break;
            case 6:
                this.O = resources.getStringArray(R.array.b6);
                this.P = resources.getStringArray(R.array.b0);
                break;
            default:
                this.O = resources.getStringArray(R.array.b1);
                this.P = resources.getStringArray(R.array.av);
                break;
        }
        return this.O;
    }

    public String j(int i) {
        if (i > this.S.length - 1) {
            return this.S[this.S.length - 1];
        }
        return this.S[i];
    }

    public String k(int i) {
        if (i > this.P.length - 1) {
            return this.P[this.P.length - 1];
        }
        return this.P[i];
    }

    public int b(String str) {
        int length = this.S.length;
        for (int i = 0; i < length; i++) {
            if (this.S[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public int c(String str) {
        int length = this.P.length;
        for (int i = 0; i < length; i++) {
            if (this.P[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public String[] G() {
        return this.Q;
    }

    public int l(int i) {
        return this.R[i];
    }

    public int m(int i) {
        return a(this.R, i, 0);
    }

    public int[] H() {
        return this.R;
    }

    public String[] I() {
        return this.T;
    }

    public int[] J() {
        return this.U;
    }

    public int n(int i) {
        return a(this.U, i, -1);
    }

    public String[] K() {
        if (this.G == null) {
            this.G = this.D.getResources().getStringArray(R.array.ai);
        }
        return this.G;
    }

    public int[] L() {
        if (this.H == null) {
            this.H = this.D.getResources().getIntArray(R.array.an);
        }
        return this.H;
    }

    public String[] M() {
        if (dji.pilot.fpv.d.b.j(this.aj)) {
            return aO();
        }
        return this.E;
    }

    public int o(int i) {
        return a(N(), i, 0);
    }

    public int[] N() {
        if (dji.pilot.fpv.d.b.j(this.aj)) {
            return aP();
        }
        return this.F;
    }

    public int[] O() {
        if (dji.pilot.fpv.d.b.j(this.aj)) {
        }
        return n_;
    }

    public String[] P() {
        return this.I;
    }

    public int p(int i) {
        return a(this.J, i, 0);
    }

    public int[] Q() {
        return this.J;
    }

    public String[] R() {
        return this.K;
    }

    public String[] S() {
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        int verstion = DataCameraGetPushStateInfo.getInstance().getVerstion();
        if (CameraType.DJICameraTypeFC330X == cameraType && verstion >= 7) {
            return new String[]{this.D.getString(R.string.fpv_camera_filter_neutral), this.D.getString(R.string.fpv_camera_filter_film), this.D.getString(R.string.fpv_camera_filter_visionx), this.D.getString(R.string.fpv_camera_filter_djicolor), this.D.getString(R.string.fpv_camera_filter_art), this.D.getString(R.string.fpv_camera_filter_blackwhite), this.D.getString(R.string.fpv_camera_filter_colorful), this.D.getString(R.string.fpv_camera_filter_m31), this.D.getString(R.string.fpv_camera_filter_kdx), this.D.getString(R.string.fpv_camera_filter_prismo), this.D.getString(R.string.fpv_camera_filter_jugo)};
        } else if (cameraType == CameraType.DJICameraTypeFC220S) {
            return this.D.getResources().getStringArray(R.array.q);
        } else {
            if (cameraType == CameraType.DJICameraTypeGD600) {
                return this.D.getResources().getStringArray(R.array.r);
            }
            return new String[]{this.D.getString(R.string.fpv_camera_filter_neutral), this.D.getString(R.string.fpv_camera_filter_film), this.D.getString(R.string.fpv_camera_filter_none), this.D.getString(R.string.fpv_camera_filter_art), this.D.getString(R.string.fpv_camera_filter_blackwhite), this.D.getString(R.string.fpv_camera_filter_colorful), this.D.getString(R.string.fpv_camera_filter_m31), this.D.getString(R.string.fpv_camera_filter_kdx), this.D.getString(R.string.fpv_camera_filter_prismo), this.D.getString(R.string.fpv_camera_filter_jugo)};
        }
    }

    public int[] T() {
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        int verstion = DataCameraGetPushStateInfo.getInstance().getVerstion();
        if (CameraType.DJICameraTypeFC330X == cameraType && verstion >= 7) {
            return new int[]{23, 6, 22, 0, 1, 4, 5, 14, 16, 18, 19};
        }
        if (cameraType == CameraType.DJICameraTypeFC220S) {
            return this.D.getResources().getIntArray(R.array.t);
        }
        if (cameraType == CameraType.DJICameraTypeGD600) {
            return this.D.getResources().getIntArray(R.array.u);
        }
        return new int[]{23, 6, 0, 1, 4, 5, 14, 16, 18, 19};
    }

    public String[] U() {
        if (CameraType.DJICameraTypeFC6310 == DataCameraGetPushStateInfo.getInstance().getCameraType()) {
            return new String[]{this.D.getString(R.string.fpv_camera_picturesize_43), this.D.getString(R.string.fpv_camera_picturesize_169), this.D.getString(R.string.fpv_camera_picturesize_32)};
        }
        return new String[]{this.D.getString(R.string.fpv_camera_picturesize_43), this.D.getString(R.string.fpv_camera_picturesize_169)};
    }

    public int q(int i) {
        return a(V(), i, 0);
    }

    public int[] V() {
        if (CameraType.DJICameraTypeFC6310 == DataCameraGetPushStateInfo.getInstance().getCameraType()) {
            return new int[]{RatioType.R_4_3.value(), RatioType.R_16_9.value(), RatioType.R_3_2.value()};
        }
        return new int[]{RatioType.R_4_3.value(), RatioType.R_16_9.value()};
    }

    public int b(int i, int i2) {
        int r = r(i);
        int a = a(D_, i2, 0);
        if (a >= C_[r].length) {
            a = C_[r].length - 1;
        }
        return C_[r][a];
    }

    public int[] W() {
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        if (cameraType == CameraType.DJICameraTypeFC260) {
            return new int[]{R.drawable.advanced_more_videoformat_2704p, R.drawable.advanced_more_videoformat_1080p, R.drawable.advanced_more_videoformat_720p};
        }
        if (cameraType == CameraType.DJICameraTypeFC300S) {
            if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 2) {
                return new int[]{R.drawable.advanced_more_videoformat_2704p, R.drawable.advanced_more_videoformat_1080p, R.drawable.advanced_more_videoformat_720p};
            }
            return new int[]{R.drawable.advanced_more_videoformat_1080p, R.drawable.advanced_more_videoformat_720p};
        } else if (cameraType == CameraType.DJICameraTypeFC300X || cameraType == CameraType.DJICameraTypeFC300XW) {
            if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 2) {
                return new int[]{R.drawable.advanced_more_videoformat_4kfull, R.drawable.advanced_more_videoformat_4knotfull, R.drawable.advanced_more_videoformat_2704p, R.drawable.advanced_more_videoformat_1080p, R.drawable.advanced_more_videoformat_720p};
            }
            return new int[]{R.drawable.advanced_more_videoformat_4kfull, R.drawable.advanced_more_videoformat_4knotfull, R.drawable.advanced_more_videoformat_1080p, R.drawable.advanced_more_videoformat_720p};
        } else if (cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeCV600) {
            return new int[]{R.drawable.advanced_more_videoformat_4kfull, R.drawable.advanced_more_videoformat_4knotfull, R.drawable.advanced_more_videoformat_2704p, R.drawable.advanced_more_videoformat_1080p};
        } else {
            if (cameraType == CameraType.DJICameraTypeFC550Raw) {
                return new int[]{0, 0, 0, 0, 0, 0, 0};
            }
            if (cameraType == CameraType.DJICameraTypeFC350 && DataCameraGetPushStateInfo.getInstance().getVerstion() >= 2) {
                return new int[]{R.drawable.advanced_more_videoformat_4kfull, R.drawable.advanced_more_videoformat_4knotfull, R.drawable.advanced_more_videoformat_2704p, R.drawable.advanced_more_videoformat_1080p, R.drawable.advanced_more_videoformat_720p};
            }
            if ((cameraType == CameraType.DJICameraTypeFC330X && DataCameraGetPushStateInfo.getInstance().getVerstion() >= 5) || cameraType == CameraType.DJICameraTypeFC220 || CameraType.DJICameraTypeFC6310 == cameraType) {
                if (aQ()) {
                    return new int[]{R.drawable.advanced_more_videoformat_2704p, R.drawable.advanced_more_videoformat_1080p};
                }
                return new int[]{R.drawable.advanced_more_videoformat_4kfull, R.drawable.advanced_more_videoformat_4knotfull, R.drawable.advanced_more_videoformat_2704p, R.drawable.advanced_more_videoformat_1080p, R.drawable.advanced_more_videoformat_720p};
            } else if (cameraType == CameraType.DJICameraTypeFC220S) {
                return new int[]{R.drawable.advanced_more_videoformat_1080p, R.drawable.advanced_more_videoformat_720p};
            } else {
                return new int[]{R.drawable.advanced_more_videoformat_4kfull, R.drawable.advanced_more_videoformat_4knotfull, R.drawable.advanced_more_videoformat_1080p, R.drawable.advanced_more_videoformat_720p};
            }
        }
    }

    public int[] X() {
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        if (cameraType == CameraType.DJICameraTypeFC260) {
            return new int[]{24, 10, 4};
        }
        if (cameraType == CameraType.DJICameraTypeFC300S) {
            if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 2) {
                return new int[]{24, 10, 4};
            }
            return new int[]{10, 4};
        } else if (cameraType == CameraType.DJICameraTypeFC300X || cameraType == CameraType.DJICameraTypeFC300XW) {
            if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 2) {
                return new int[]{22, 16, 24, 10, 4};
            }
            return new int[]{22, 16, 10, 4};
        } else if (cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeCV600) {
            return new int[]{22, 16, 24, 10};
        } else {
            if (cameraType == CameraType.DJICameraTypeFC550Raw) {
                return new int[]{-1, 22, 16, -1, 24, -1, 10};
            }
            if (cameraType == CameraType.DJICameraTypeFC350 && DataCameraGetPushStateInfo.getInstance().getVerstion() >= 2) {
                return new int[]{22, 16, 24, 10, 4};
            }
            if (cameraType == CameraType.DJICameraTypeFC330X && DataCameraGetPushStateInfo.getInstance().getVerstion() >= 5) {
                return new int[]{22, 16, 24, 10, 4};
            }
            if (cameraType == CameraType.DJICameraTypeFC220 || CameraType.DJICameraTypeFC6310 == cameraType) {
                if (aQ()) {
                    return new int[]{31, 10};
                }
                return new int[]{22, 16, 31, 10, 4};
            } else if (cameraType == CameraType.DJICameraTypeFC220S) {
                return new int[]{10, 4};
            } else {
                if (cameraType != CameraType.DJICameraTypeGD600) {
                    return new int[]{22, 16, 10, 4};
                }
                return new int[]{10};
            }
        }
    }

    public String[] Y() {
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        if (cameraType == CameraType.DJICameraTypeFC260) {
            return new String[]{g.av_, g.aw_, g.ax_};
        } else if (cameraType == CameraType.DJICameraTypeFC300S) {
            if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 2) {
                return new String[]{g.av_, g.aw_, g.ax_};
            }
            return new String[]{g.aw_, g.ax_};
        } else if (cameraType == CameraType.DJICameraTypeFC300X || cameraType == CameraType.DJICameraTypeFC300XW) {
            if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 2) {
                return new String[]{g.ar_, g.at_, g.av_, g.aw_, g.ax_};
            }
            return new String[]{g.ar_, g.at_, g.aw_, g.ax_};
        } else if (cameraType == CameraType.DJICameraTypeFC550Raw) {
            return new String[]{g.ay_, g.ar_, g.at_, g.az_, g.av_, g.aA_, g.aw_};
        } else if (cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeCV600) {
            return new String[]{g.ar_, g.at_, g.av_, g.aw_};
        } else if (cameraType == CameraType.DJICameraTypeFC350 && DataCameraGetPushStateInfo.getInstance().getVerstion() >= 2) {
            return new String[]{g.ar_, g.at_, g.av_, g.aw_, g.ax_};
        } else if (cameraType == CameraType.DJICameraTypeFC330X && DataCameraGetPushStateInfo.getInstance().getVerstion() >= 5) {
            return new String[]{g.ar_, g.at_, g.av_, g.aw_, g.ax_};
        } else if (CameraType.DJICameraTypeFC6310 == cameraType) {
            return new String[]{g.ar_, g.at_, g.au_, g.aw_, g.ax_};
        } else if (cameraType == CameraType.DJICameraTypeFC220) {
            if (aQ()) {
                return new String[]{g.au_, g.aw_};
            }
            return new String[]{g.ar_, g.at_, g.au_, g.aw_, g.ax_};
        } else if (cameraType == CameraType.DJICameraTypeGD600) {
            return new String[]{g.aw_};
        } else if (cameraType == CameraType.DJICameraTypeFC220S) {
            return new String[]{g.aw_, g.ax_};
        } else {
            return new String[]{g.ar_, g.at_, g.aw_, g.ax_};
        }
    }

    public int r(int i) {
        if (i == 24) {
            return 4;
        }
        if (i == 16) {
            return 1;
        }
        if (i == 10) {
            return 2;
        }
        if (i == 4) {
            return 3;
        }
        if (i == 27) {
            return 6;
        }
        if (i == 28) {
            return 5;
        }
        if (i == 31) {
            return 7;
        }
        return 0;
    }

    public int[] c(int i, int i2) {
        CameraType b = i.getInstance().b();
        if (i == 3) {
            if (b == CameraType.DJICameraTypeFC220) {
                if (i2 == 1) {
                    return new int[]{1, 3, 4, 6, 7};
                }
                return new int[]{1, 2, 4, 5, 7};
            } else if (b == CameraType.DJICameraTypeFC220S) {
                if (i2 == 1) {
                    return new int[]{1, 3};
                }
                return new int[]{1, 2};
            } else if (i2 == 1) {
                return new int[]{1, 3, 4, 6};
            } else {
                return new int[]{1, 2, 4, 5};
            }
        } else if (i == 2) {
            if (b == CameraType.DJICameraTypeFC220) {
                if (aQ()) {
                    if (i2 == 1) {
                        return new int[]{1, 3, 4, 6};
                    }
                    return new int[]{1, 2, 4, 5};
                } else if (i2 == 1) {
                    return new int[]{1, 3, 4, 6, 11};
                } else {
                    return new int[]{1, 2, 4, 5, 11};
                }
            } else if (b == CameraType.DJICameraTypeFC260 || b == CameraType.DJICameraTypeFC220S) {
                if (i2 == 1) {
                    return new int[]{1, 3};
                }
                return new int[]{1, 2};
            } else if (b == CameraType.DJICameraTypeGD600) {
                if (i2 == 1) {
                    return new int[]{3};
                }
                return new int[]{2};
            } else if (b != CameraType.DJICameraTypeFC350 || DataCameraGetPushStateInfo.getInstance().getVerstion() < 3) {
                if (b == CameraType.DJICameraTypeFC330X) {
                    if (i2 == 1) {
                        return new int[]{1, 3, 4, 6, 7};
                    }
                    return new int[]{1, 2, 4, 5, 7};
                } else if (i2 == 1) {
                    return new int[]{1, 3, 4, 6};
                } else {
                    return new int[]{1, 2, 4, 5};
                }
            } else if (i2 == 1) {
                return new int[]{1, 3, 4, 6, 7};
            } else {
                return new int[]{1, 2, 4, 5, 7};
            }
        } else if (i == 7) {
            if (CameraType.DJICameraTypeFC6310 == b) {
                if (i2 == 1) {
                    return new int[]{1, 3, 4, 6};
                }
                return new int[]{1, 2, 4, 5};
            } else if (i2 == 1) {
                return new int[]{1, 3};
            } else {
                return new int[]{1, 2};
            }
        } else if (i == 4) {
            if (CameraType.DJICameraTypeFC6310 == b) {
                if (i2 == 1) {
                    return new int[]{1, 3, 4, 6};
                }
                return new int[]{1, 2, 4, 5};
            } else if (i2 == 1) {
                return new int[]{1, 3};
            } else {
                return new int[]{1, 2};
            }
        } else if (i == 1) {
            if (CameraType.DJICameraTypeFC6310 == b) {
                if (i2 == 1) {
                    if (VideoEncodeType.b == DataCameraGetPushShotParams.getInstance().getPrimaryVideoEncodeType()) {
                        return new int[]{1, 3};
                    }
                    return new int[]{1, 3, 4, 6};
                }
                if (VideoEncodeType.b == DataCameraGetPushShotParams.getInstance().getPrimaryVideoEncodeType()) {
                    return new int[]{1, 2};
                }
                return new int[]{1, 2, 4, 5};
            } else if (i2 == 1) {
                return new int[]{1, 3};
            } else {
                return new int[]{1, 2};
            }
        } else if (i == 0) {
            if (CameraType.DJICameraTypeFC6310 == b) {
                if (i2 == 1) {
                    if (VideoEncodeType.b == DataCameraGetPushShotParams.getInstance().getPrimaryVideoEncodeType()) {
                        return new int[]{1, 3};
                    }
                    return new int[]{1, 3, 4, 6};
                }
                if (VideoEncodeType.b == DataCameraGetPushShotParams.getInstance().getPrimaryVideoEncodeType()) {
                    return new int[]{1, 2};
                }
                return new int[]{1, 2, 4, 5};
            } else if (i2 == 1) {
                return new int[]{1};
            } else if (i.getInstance().c() != ProductType.KumquatX) {
                return new int[]{1, 2};
            } else {
                return new int[]{1};
            }
        } else if (i == 6) {
            if (i2 == 1) {
                return new int[]{1, 3};
            }
            return new int[]{1, 2};
        } else if (i != 5) {
            return null;
        } else {
            if (i2 != 1) {
                return new int[]{1, 2};
            }
            return new int[]{1};
        }
    }

    public String[] d(int i, int i2) {
        CameraType b = i.getInstance().b();
        if (i == 3) {
            if (b == CameraType.DJICameraTypeFC220) {
                if (i2 == 1) {
                    return new String[]{d.c, d.e, d.f, d.h, d.i};
                }
                return new String[]{d.c, d.d, d.f, d.g, d.i};
            } else if (b == CameraType.DJICameraTypeFC220S) {
                if (i2 == 1) {
                    return new String[]{d.c, d.e, d.h};
                }
                return new String[]{d.c, d.d};
            } else if (i2 == 1) {
                return new String[]{d.c, d.e, d.f, d.h};
            } else {
                return new String[]{d.c, d.d, d.f, d.g};
            }
        } else if (i == 2) {
            if (b == CameraType.DJICameraTypeFC220) {
                if (i2 == 1) {
                    return new String[]{d.c, d.e, d.f, d.h, d.j};
                }
                return new String[]{d.c, d.d, d.f, d.g, d.j};
            } else if (b == CameraType.DJICameraTypeFC260 || b == CameraType.DJICameraTypeFC220S) {
                if (i2 == 1) {
                    return new String[]{d.c, d.e};
                }
                return new String[]{d.c, d.d};
            } else if (b != CameraType.DJICameraTypeFC350 || DataCameraGetPushStateInfo.getInstance().getVerstion() < 3) {
                if (b == CameraType.DJICameraTypeFC330X) {
                    if (i2 == 1) {
                        return new String[]{d.c, d.e, d.f, d.h, d.i};
                    }
                    return new String[]{d.c, d.d, d.f, d.g, d.i};
                } else if (i2 == 1) {
                    return new String[]{d.c, d.e, d.f, d.h};
                } else {
                    return new String[]{d.c, d.d, d.f, d.g};
                }
            } else if (i2 == 1) {
                return new String[]{d.c, d.e, d.f, d.h, d.i};
            } else {
                return new String[]{d.c, d.d, d.f, d.g, d.i};
            }
        } else if (i == 7) {
            if (CameraType.DJICameraTypeFC6310 == b) {
                if (i2 == 1) {
                    return new String[]{d.c, d.e, d.f, d.h};
                }
                return new String[]{d.c, d.d, d.f, d.g};
            } else if (i2 == 1) {
                return new String[]{d.c, d.e};
            } else {
                return new String[]{d.c, d.d};
            }
        } else if (i == 4) {
            if (CameraType.DJICameraTypeFC6310 == b) {
                if (i2 == 1) {
                    return new String[]{d.c, d.e, d.f, d.h};
                }
                return new String[]{d.c, d.d, d.f, d.g};
            } else if (i2 == 1) {
                return new String[]{d.c, d.e};
            } else {
                return new String[]{d.c, d.d};
            }
        } else if (i == 1) {
            if (CameraType.DJICameraTypeFC6310 == b) {
                if (i2 == 1) {
                    if (VideoEncodeType.b == DataCameraGetPushShotParams.getInstance().getPrimaryVideoEncodeType()) {
                        return new String[]{d.c, d.e};
                    }
                    return new String[]{d.c, d.e, d.f, d.h};
                }
                if (VideoEncodeType.b == DataCameraGetPushShotParams.getInstance().getPrimaryVideoEncodeType()) {
                    return new String[]{d.c, d.d};
                }
                return new String[]{d.c, d.d, d.f, d.g};
            } else if (i2 == 1) {
                return new String[]{d.c, d.e};
            } else {
                return new String[]{d.c, d.d};
            }
        } else if (i == 0) {
            if (CameraType.DJICameraTypeFC6310 == b) {
                if (i2 == 1) {
                    if (VideoEncodeType.b == DataCameraGetPushShotParams.getInstance().getPrimaryVideoEncodeType()) {
                        return new String[]{d.c, d.e};
                    }
                    return new String[]{d.c, d.e, d.f, d.h};
                }
                if (VideoEncodeType.b == DataCameraGetPushShotParams.getInstance().getPrimaryVideoEncodeType()) {
                    return new String[]{d.c, d.d};
                }
                return new String[]{d.c, d.d, d.f, d.g};
            } else if (i2 == 1) {
                return new String[]{d.c};
            } else {
                return new String[]{d.c, d.d};
            }
        } else if (i == 6) {
            if (i2 == 1) {
                return new String[]{d.c, d.e};
            }
            return new String[]{d.c, d.d};
        } else if (i != 5) {
            return null;
        } else {
            if (i2 == 1) {
                return new String[]{d.c};
            }
            return new String[]{d.c, d.d};
        }
    }

    public static int a(int[] iArr, int i, int i2) {
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i == iArr[i3]) {
                return i3;
            }
        }
        return i2;
    }

    private void aN() {
        Resources resources = this.D.getResources();
        this.E = resources.getStringArray(R.array.ag);
        this.F = resources.getIntArray(R.array.al);
        this.I = resources.getStringArray(R.array.c2);
        this.J = resources.getIntArray(R.array.c5);
        this.Q = resources.getStringArray(R.array.a0);
        this.R = resources.getIntArray(R.array.a1);
        this.K = resources.getStringArray(R.array.ar);
        this.L = resources.getStringArray(R.array.ac);
        this.M = resources.getIntArray(R.array.ad);
        this.N = resources.getStringArray(R.array.b7);
        this.S = resources.getStringArray(R.array.b_);
        this.T = resources.getStringArray(R.array.i);
        this.U = resources.getIntArray(R.array.k);
        Z();
    }

    public void Z() {
        if (this.ay == null) {
            Resources resources = this.D.getResources();
            this.ay = resources.getStringArray(R.array.bk);
            this.az = resources.getIntArray(R.array.bn);
            this.aw = resources.getStringArray(R.array.a);
            this.ax = resources.getIntArray(R.array.e);
            this.aA = resources.getStringArray(R.array.cj);
            this.aB = resources.getIntArray(R.array.ck);
            this.aC = resources.getStringArray(R.array.ci);
            this.aD = new int[]{0, 1, 2};
            this.aE = resources.getStringArray(R.array.a6);
            this.aF = resources.getIntArray(R.array.a7);
            this.a = new b(this);
            this.a.a(this.D);
        }
    }

    public String[] aa() {
        return this.aC;
    }

    public int[] ab() {
        return this.aD;
    }

    public int s(int i) {
        return i;
    }

    public String[] ac() {
        int i = 0;
        String string;
        if (dji.pilot.fpv.d.b.k(null)) {
            this.aG = new String[B_.length];
            string = this.D.getString(R.string.app_second);
            while (i < B_.length && i < 6) {
                if (i == 0) {
                    this.aG[i] = this.D.getString(R.string.app_off_lower);
                } else {
                    this.aG[i] = String.valueOf(i) + string;
                }
                i++;
            }
        } else {
            this.aG = new String[B_.length];
            string = this.D.getString(R.string.app_second);
            while (i < B_.length) {
                if (i == 0) {
                    this.aG[i] = this.D.getString(R.string.app_off_lower);
                } else {
                    this.aG[i] = String.valueOf(i) + string;
                }
                i++;
            }
        }
        return this.aG;
    }

    public int[] ad() {
        if (dji.pilot.fpv.d.b.k(null)) {
            this.aH = new int[]{0, 1, 2, 3, 4, 5};
        } else {
            this.aH = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        }
        return this.aH;
    }

    public String[] ae() {
        return this.aA;
    }

    public int t(int i) {
        return a(this.aB, i, 0);
    }

    public int[] af() {
        return this.aB;
    }

    public String[] ag() {
        return this.aE;
    }

    public int[] ah() {
        return this.aF;
    }

    public int u(int i) {
        return a(this.aF, i, 0);
    }

    public String[] ai() {
        return this.ay;
    }

    public int v(int i) {
        return a(this.az, i, 0);
    }

    public int[] aj() {
        return this.az;
    }

    public String[] ak() {
        return this.aw;
    }

    public int w(int i) {
        return a(this.ax, i, 0);
    }

    public int[] al() {
        return this.ax;
    }

    public String[] am() {
        return new String[]{this.D.getString(R.string.app_off_lower), this.D.getString(R.string.camera_gamma_1), this.D.getString(R.string.camera_gamma_2), this.D.getString(R.string.camera_gamma_3), this.D.getString(R.string.camera_gamma_4)};
    }

    public int[] an() {
        return new int[]{0, 1, 2, 3, 4};
    }

    public String[] ao() {
        return new String[]{this.D.getString(R.string.camera_encode_h264), this.D.getString(R.string.camera_encode_h265)};
    }

    public int[] ap() {
        return new int[]{0, 1};
    }

    public dji.pilot.fpv.camera.more.a.b aq() {
        return this.al;
    }

    public String[] ar() {
        if (this.bc == null) {
            this.bc = this.D.getResources().getStringArray(R.array.e5);
        }
        return this.bc;
    }

    public int[] as() {
        if (this.bd == null) {
            this.bd = this.D.getResources().getIntArray(R.array.e6);
        }
        return this.bd;
    }

    private String[] aO() {
        int verstion = DataCameraGetPushStateInfo.getInstance().getVerstion();
        boolean supportSpotThermometric = DataCameraGetPushTauParam.getInstance().supportSpotThermometric();
        if (verstion >= 4) {
            return new String[]{this.D.getString(R.string.tau_imgformat_rjpeg), this.D.getString(R.string.tau_imgformat_tiff14), this.D.getString(R.string.tau_imgformat_jpeg)};
        } else if (verstion >= 3) {
            if (supportSpotThermometric) {
                return new String[]{this.D.getString(R.string.tau_imgformat_jpeg), this.D.getString(R.string.tau_imgformat_rjpeg), this.D.getString(R.string.tau_imgformat_tiff_low14), this.D.getString(R.string.tau_imgformat_tiff_high14)};
            }
            return new String[]{this.D.getString(R.string.tau_imgformat_jpeg), this.D.getString(R.string.tau_imgformat_rjpeg)};
        } else if (supportSpotThermometric) {
            if (this.aK == null) {
                this.aK = this.D.getResources().getStringArray(R.array.e8);
            }
            return this.aK;
        } else {
            if (this.aI == null) {
                this.aI = this.D.getResources().getStringArray(R.array.e7);
            }
            return this.aI;
        }
    }

    private int[] aP() {
        int verstion = DataCameraGetPushStateInfo.getInstance().getVerstion();
        boolean supportSpotThermometric = DataCameraGetPushTauParam.getInstance().supportSpotThermometric();
        if (verstion >= 4) {
            return new int[]{7, 4, 1};
        }
        if (verstion >= 3) {
            if (supportSpotThermometric) {
                return new int[]{1, 7, 5, 6};
            }
            return new int[]{1, 7};
        } else if (supportSpotThermometric) {
            if (this.aL == null) {
                this.aL = this.D.getResources().getIntArray(R.array.e9);
            }
            return this.aL;
        } else {
            if (this.aJ == null) {
                this.aJ = this.D.getResources().getIntArray(R.array.e_);
            }
            return this.aJ;
        }
    }

    public String[] at() {
        if (this.aM == null) {
            this.aM = this.D.getResources().getStringArray(R.array.ef);
        }
        return this.aM;
    }

    public String[] au() {
        if (this.aO == null) {
            this.aO = this.D.getResources().getStringArray(R.array.eg);
        }
        return this.aO;
    }

    public int[] av() {
        if (this.aN == null) {
            this.aN = this.D.getResources().getIntArray(R.array.eh);
        }
        return this.aN;
    }

    public String[] aw() {
        if (DataCameraGetPushTauParam.getInstance().isIsothermEnable()) {
            if (this.aQ == null) {
                this.aQ = this.D.getResources().getStringArray(R.array.ed);
            }
            return this.aQ;
        }
        if (this.aP == null) {
            this.aP = this.D.getResources().getStringArray(R.array.ec);
        }
        return this.aP;
    }

    public int[] ax() {
        if (this.aR == null) {
            this.aR = this.D.getResources().getIntArray(R.array.ee);
        }
        return this.aR;
    }

    public String[] ay() {
        if (this.aS == null) {
            this.aS = this.D.getResources().getStringArray(R.array.ei);
        }
        return this.aS;
    }

    public int[] az() {
        if (this.aT == null) {
            this.aT = this.D.getResources().getIntArray(R.array.ej);
        }
        return this.aT;
    }

    public String[] aA() {
        if (CameraType.DJICameraTypeTau336 == this.aj) {
            if (this.aX == null) {
                this.aX = this.D.getResources().getStringArray(R.array.dx);
            }
            return this.aX;
        } else if (CameraType.DJICameraTypeTau640 == this.aj) {
            if (this.aU == null) {
                this.aU = this.D.getResources().getStringArray(R.array.e0);
            }
            return this.aU;
        } else {
            if (this.aX == null) {
                this.aX = this.D.getResources().getStringArray(R.array.dx);
            }
            return this.aX;
        }
    }

    public String[] aB() {
        if (CameraType.DJICameraTypeTau336 == this.aj) {
            if (this.aY == null) {
                this.aY = this.D.getResources().getStringArray(R.array.dy);
            }
            return this.aY;
        } else if (CameraType.DJICameraTypeTau640 == this.aj) {
            if (this.aV == null) {
                this.aV = this.D.getResources().getStringArray(R.array.e1);
            }
            return this.aV;
        } else {
            if (this.aY == null) {
                this.aY = this.D.getResources().getStringArray(R.array.dy);
            }
            return this.aY;
        }
    }

    public int[] aC() {
        if (CameraType.DJICameraTypeTau336 == this.aj) {
            if (this.aZ == null) {
                this.aZ = this.D.getResources().getIntArray(R.array.dz);
            }
            return this.aZ;
        } else if (CameraType.DJICameraTypeTau640 == this.aj) {
            if (this.aW == null) {
                this.aW = this.D.getResources().getIntArray(R.array.e2);
            }
            return this.aW;
        } else {
            if (this.aZ == null) {
                this.aZ = this.D.getResources().getIntArray(R.array.dz);
            }
            return this.aZ;
        }
    }

    public String[] aD() {
        if (this.ba == null) {
            this.ba = this.D.getResources().getStringArray(R.array.ea);
        }
        this.ba[1] = dji.pilot.fpv.d.b.b(this.D);
        return this.ba;
    }

    public int[] aE() {
        if (this.bb == null) {
            this.bb = this.D.getResources().getIntArray(R.array.eb);
        }
        return this.bb;
    }

    public String[] aF() {
        if (this.be == null) {
            this.be = this.D.getResources().getStringArray(R.array.e3);
        }
        return this.be;
    }

    public int[] aG() {
        if (this.bf == null) {
            this.bf = this.D.getResources().getIntArray(R.array.e4);
        }
        return this.bf;
    }

    public c aH() {
        return this.bg;
    }

    public void a(boolean z) {
        this.bh = z;
    }

    private boolean aQ() {
        return this.bh;
    }
}
