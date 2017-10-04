package dji.pilot.fpv.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.login.widget.ToolTipPopup;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.common.camera.CameraLensFocusTargetPoint;
import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.forbid.NFZLogUtil;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetMeteringArea;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetAELock;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetMeteringArea;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataDm368GetPushStatus;
import dji.midware.data.model.P3.DataDm368SetParams;
import dji.midware.data.model.P3.DataDm368SetParams.DM368CmdId;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetPushAvoid;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery.SmartGoHomeStatus;
import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionInfo;
import dji.midware.data.model.P3.DataFlycSmartAck;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause;
import dji.midware.data.model.P3.DataOsdGetPushCommon.TRIPOD_STATUS;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetGimbalControlMode;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.midware.data.model.P3.DataRcSetGimbalControlMode;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.e.h;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.natives.FPVController;
import dji.pilot.R;
import dji.pilot.fpv.b.a;
import dji.pilot.fpv.b.b;
import dji.pilot.fpv.camera.control.DJICameraControlView;
import dji.pilot.fpv.camera.focus.DJIFocusAreaView;
import dji.pilot.fpv.camera.focus.DJIFocusDistanceView;
import dji.pilot.fpv.camera.focus.DJIFocusRingView;
import dji.pilot.fpv.camera.focus.DJIMFDemarcateView;
import dji.pilot.fpv.camera.newfn.DJICameraMenuView;
import dji.pilot.fpv.camera.newfn.DJICameraTauSceneView;
import dji.pilot.fpv.camera.ref.DJICameraPointRefView;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.d;
import dji.pilot.fpv.control.e;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.control.m;
import dji.pilot.fpv.control.n;
import dji.pilot.fpv.control.q;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.flightmode.FlightModeWifiView;
import dji.pilot.fpv.leftmenu.DJILeftBar;
import dji.pilot.fpv.rightbar.DJIFMSettingView;
import dji.pilot.fpv.rightbar.DJISwitchModeView;
import dji.pilot.fpv.view.DJICameraAnimView;
import dji.pilot.fpv.view.DJICameraChartView;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIFpvGimbalPitchView;
import dji.pilot.fpv.view.DJIGimbalRollFineTuneView;
import dji.pilot.fpv.view.DJIGridLine;
import dji.pilot.fpv.view.DJIPlayBackView;
import dji.pilot.fpv.view.DJITerrainTrackingHint;
import dji.pilot.fpv.view.GrayView;
import dji.pilot.fpv.view.GroupAttitudeView;
import dji.pilot.fpv.view.SimpleAttitudeView;
import dji.pilot.joystick.DJIJoyStickView;
import dji.pilot.liveshare.LiveShareFpvTopView;
import dji.pilot.main.activity.DJIHubActivity;
import dji.pilot.newfpv.DJIAbsFpvActivity;
import dji.pilot.newfpv.c;
import dji.pilot.newfpv.f.l;
import dji.pilot.newfpv.topbar.DJIFpvTopBarBaseView;
import dji.pilot.newfpv.topbar.DJIFpvTopBarKumquatView;
import dji.pilot.newfpv.view.FpvShortcutView;
import dji.pilot.newfpv.view.WholeAttitudeView;
import dji.pilot.playback.litchi.DJIPlayBackActivity;
import dji.pilot.publics.c.g;
import dji.pilot.publics.c.i;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.publics.widget.f;
import dji.pilot.sdr.debug.DJISdrDebugView;
import dji.pilot.visual.beginner.DJIVisualBeginnerView;
import dji.pilot.visual.radar.DJIVisionRadarView;
import dji.pilot.visual.stage.DJIVisualPointSpeedView;
import dji.pilot.visual.view.VisualScreenTouchView;
import dji.pilot2.multimoment.view.HorizonalSegmentView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.publics.widget.FpvPopWarnView;
import dji.sdksharedlib.DJISDKCache;
import dji.setting.ui.flyc.SdModeView;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public abstract class DJIBaseNewPreviewActivity extends DJIAbsFpvActivity<c> implements SurfaceTextureListener, OnClickListener, h, VideoSurfaceView$a, a, s {
    private static final int Y = 4096;
    private static final int aa = 8192;
    private static final int ab = 12288;
    private static final int ac = 16384;
    private static final int ad = 20480;
    private static final int ae = 24576;
    private static final int af = 28672;
    private static final int ag = 32768;
    private static final int ah = 36864;
    private static final int ai = 36865;
    private static final int aj = 36866;
    private static final int ak = 36867;
    private static final int al = 36868;
    private static final int am = 36869;
    private static final int an = 36870;
    private static final int ao = 0;
    private static final int ap = 1;
    private static final int aq = 2;
    private static final int ar = 4;
    private static final int as = 8;
    private static final int at = 16;
    private static final int au = 32;
    private static final int av = 64;
    private static final int aw = 127;
    private static final long ay = 200;
    private static final long az = 100;
    private static final int bA = 8193;
    private static final int bB = 8194;
    private static final boolean bi = false;
    private static final int bz = 8192;
    private static boolean cc = false;
    protected static final float o = 1.0f;
    protected static final float p = 0.5f;
    protected b A = null;
    protected dji.midware.media.h.b.b B = null;
    protected i C = null;
    protected int D = 0;
    protected int E = 0;
    protected int F = 0;
    protected int G = 0;
    protected SimpleAttitudeView H;
    protected WholeAttitudeView I;
    protected FlightModeWifiView J;
    protected GroupAttitudeView K;
    protected Boolean L = Boolean.valueOf(false);
    protected boolean M = false;
    protected float N = 0.0f;
    protected dji.midware.util.a O;
    int P = -1;
    protected boolean Q = false;
    protected RatioType R = RatioType.R_4_3;
    protected RatioType S = RatioType.OTHER;
    public boolean T = false;
    protected boolean U = false;
    @dji.thirdparty.afinal.a.b.c(a = 2131362848)
    protected TextureView a;
    @dji.thirdparty.afinal.a.b.c(a = 2131362846)
    private DJIRelativeLayout aA;
    @dji.thirdparty.afinal.a.b.c(a = 2131362859)
    private ViewStub aB;
    @dji.thirdparty.afinal.a.b.c(a = 2131362870)
    private DJITextView aC;
    @dji.thirdparty.afinal.a.b.c(a = 2131362861)
    private DJIErrorPopView aD;
    @dji.thirdparty.afinal.a.b.c(a = 2131362862)
    private ViewStub aE;
    @dji.thirdparty.afinal.a.b.c(a = 2131362847)
    private DJIRelativeLayout aF;
    @dji.thirdparty.afinal.a.b.c(a = 2131362852)
    private DJIImageView aG;
    @dji.thirdparty.afinal.a.b.c(a = 2131362853)
    private DJIImageView aH;
    @dji.thirdparty.afinal.a.b.c(a = 2131362905)
    private DJITextView aI;
    @dji.thirdparty.afinal.a.b.c(a = 2131367297)
    private VisualScreenTouchView aJ;
    @dji.thirdparty.afinal.a.b.c(a = 2131362865)
    private DJIFMSettingView aK;
    private DJIPlayBackView aL = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362869)
    private DJILeftBar aM;
    @dji.thirdparty.afinal.a.b.c(a = 2131362916)
    private DJICameraMenuView aN;
    @dji.thirdparty.afinal.a.b.c(a = 2131362909)
    private DJICameraControlView aO = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362877)
    private DJICameraChartView aP;
    @dji.thirdparty.afinal.a.b.c(a = 2131362151)
    private DJIFocusDistanceView aQ;
    private DJICameraTauSceneView aR = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362904)
    private DJICameraPointRefView aS;
    @dji.thirdparty.afinal.a.b.c(a = 2131363211)
    private DJIVisualPointSpeedView aT;
    private DJIGimbalRollFineTuneView aU = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362902)
    private DJIImageView aV = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362857)
    private DJIFpvGimbalPitchView aW;
    @dji.thirdparty.afinal.a.b.c(a = 2131363083)
    private LiveShareFpvTopView aX;
    @dji.thirdparty.afinal.a.b.c(a = 2131362910)
    private DJIVisionRadarView aY;
    private a aZ = null;
    private volatile int ax = 0;
    @dji.thirdparty.afinal.a.b.c(a = 2131362924)
    protected VideoSurfaceView b;
    private dji.pilot.fpv.leftmenu.b bC = null;
    private int bD = 8192;
    private d bE = null;
    private int[] bF = new int[]{0, 0};
    private int[] bG = new int[]{0, 0};
    private m bH;
    private g bI;
    private RecordReceiver bJ = null;
    private dji.midware.e.d bK = new 12(this);
    private Runnable bL = new 30(this);
    private GestureDetector bM;
    private boolean bN = false;
    private dji.pilot.fpv.leftmenu.b bO = null;
    private dji.pilot.fpv.leftmenu.b bP;
    private dji.pilot.publics.widget.b bQ;
    private boolean bR = false;
    private dji.pilot.publics.widget.b bS;
    private boolean bT = false;
    private Runnable bU = new 14(this);
    private Runnable bV = new 15(this);
    private dji.pilot.fpv.leftmenu.b bW;
    private int bX = 5;
    private boolean bY = false;
    private DataDm368SetParams bZ = new DataDm368SetParams();
    private dji.pilot.fpv.control.a ba = null;
    private DJIPlayBackView.b bb = null;
    private DJIGenSettingDataManager.c bc = null;
    private k bd = new k(this);
    private Animation be = null;
    private Animation bf = null;
    private int bg = 0;
    private Timer bh;
    private boolean bj = false;
    private TRIPOD_STATUS bk = TRIPOD_STATUS.UNKNOWN;
    private long bl = 0;
    private FLIGHT_ACTION bm = null;
    private MotorStartFailedCause bn = MotorStartFailedCause.None;
    private boolean bo = false;
    private FLYC_STATE bp = FLYC_STATE.OTHER;
    private MODE bq;
    private CameraType br = CameraType.OTHER;
    private boolean bs = false;
    private boolean bt = false;
    private int bu = -1;
    private boolean bv = false;
    private Animation bw = null;
    private f bx = null;
    private FLYC_STATE by = FLYC_STATE.OTHER;
    protected View c;
    private DJITerrainTrackingHint cA;
    private int ca;
    private dji.pilot.fpv.leftmenu.b cb;
    private volatile int cd = -1;
    private volatile ExposureMode ce = ExposureMode.OTHER;
    private volatile int cf = -1;
    private int cg = 0;
    private n ch = null;
    private int ci = 0;
    private dji.pilot.publics.widget.b cj;
    private SdModeView ck;
    private DataBaseCameraSetting cl = new DataBaseCameraSetting();
    private boolean cm = false;
    private OnGestureListener cn = new 28(this);
    private MotionEvent co = null;
    private long cp = 0;
    private long cq = 0;
    private long cr = 0;
    private f cs = null;
    private i ct = null;
    private DJIVisualBeginnerView cu = null;
    private final Rect cv = new Rect();
    private View cw = null;
    private boolean cx = false;
    private boolean cy = false;
    private boolean cz = false;
    @dji.thirdparty.afinal.a.b.c(a = 2131362849)
    protected GrayView d;
    @dji.thirdparty.afinal.a.b.c(a = 2131362858)
    protected DJIFpvTopBarKumquatView e;
    @dji.thirdparty.afinal.a.b.c(a = 2131362850)
    protected DJIGridLine f;
    @dji.thirdparty.afinal.a.b.c(a = 2131362625)
    protected DJISwitchModeView g;
    @dji.thirdparty.afinal.a.b.c(a = 2131362866)
    protected DJIFocusRingView h;
    @dji.thirdparty.afinal.a.b.c(a = 2131362854)
    protected DJIFocusAreaView i;
    @dji.thirdparty.afinal.a.b.c(a = 2131362915)
    protected DJIMFDemarcateView j;
    @dji.thirdparty.afinal.a.b.c(a = 2131362920)
    protected DJISdrDebugView k;
    @dji.thirdparty.afinal.a.b.c(a = 2131362921)
    protected ImageView l;
    @dji.thirdparty.afinal.a.b.c(a = 2131362908)
    protected DJIJoyStickView m;
    @dji.thirdparty.afinal.a.b.c(a = 2131362923)
    protected FpvShortcutView n;
    protected dji.pilot.publics.c.f q = null;
    protected q r;
    protected boolean s = false;
    protected int t;
    protected int u;
    protected int v = 0;
    protected int w = 12;
    protected int x = 8;
    protected int[] y = new int[]{0, 0};
    protected int[] z = new int[]{0, 0};

    public void a() {
        setContentView(R.layout.fpv_kumquat);
        this.H = (SimpleAttitudeView) findViewById(R.id.a3o);
        this.I = (WholeAttitudeView) findViewById(R.id.a2r);
        this.J = (FlightModeWifiView) findViewById(R.id.a3t);
        this.K = (GroupAttitudeView) findViewById(R.id.a3n);
    }

    public Map<dji.pilot.newfpv.view.b.a, dji.pilot.newfpv.d> b() {
        Map hashMap = new HashMap();
        hashMap.put(this.H.getViewId(), this.H.getViewInfo());
        hashMap.put(this.I.getViewId(), this.I.getViewInfo());
        hashMap.put(this.J.getViewId(), this.J.getViewInfo());
        hashMap.put(this.K.getViewId(), this.K.getViewInfo());
        hashMap.put(this.aN.getViewId(), this.aN.getViewInfo());
        hashMap.put(this.aO.getViewId(), this.aO.getViewInfo());
        hashMap.put(this.e.getViewId(), this.e.getViewInfo());
        return hashMap;
    }

    protected boolean c() {
        return this.M;
    }

    protected void d() {
        if (this.r.c()) {
            boolean z;
            if (this.M) {
                z = false;
            } else {
                z = true;
            }
            this.M = z;
            if (this.M) {
                a(false);
            } else {
                b(false);
            }
        }
    }

    public boolean a(dji.pilot.newfpv.d dVar, int i) {
        if (c()) {
            return false;
        }
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
        if (dji.pilot.newfpv.view.b.a.d == dVar.b || dji.pilot.newfpv.view.b.a.e == dVar.b || dji.pilot.newfpv.view.b.a.f == dVar.b) {
            if (instance == null || instance.q()) {
                return false;
            }
            if (this.aL == null || !this.aL.isShown()) {
                return true;
            }
            return false;
        } else if (dji.pilot.newfpv.view.b.a.c == dVar.b) {
            if ((this.aL == null || !this.aL.isShown()) && this.aN.getVisibility() != 0) {
                return true;
            }
            return false;
        } else if (dji.pilot.newfpv.view.b.a.h == dVar.b) {
            if (g()) {
                return false;
            }
            return true;
        } else if (dji.pilot.newfpv.view.b.a.i != dVar.b) {
            return true;
        } else {
            if (g()) {
                return false;
            }
            return true;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(this.TAG, "onCreate");
        this.N = getResources().getDimension(R.dimen.h4);
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(this);
        instance.a(this.bd);
        instance.a(this.r);
        dji.pilot.dji_groundstation.controller.a.getInstance(this);
        if (this.L.booleanValue()) {
            d.i();
        }
        this.b = (VideoSurfaceView) findViewById(R.id.a3y);
        this.a = (TextureView) findViewById(R.id.a1w);
        this.c = K();
        getWindow().addFlags(128);
        this.aA = (DJIRelativeLayout) findViewById(R.id.a1u);
        this.bd.a(bundle, this.aA);
        dji.gs.utils.a.a = DJIGenSettingDataManager.getInstance().s();
        this.bH = new m(this.aA);
        O();
        P();
        an();
        if (!this.L.booleanValue()) {
            if (this.b == null) {
                this.a.setSurfaceTextureListener(this);
            } else {
                this.b.setRenderer();
                this.b.setRenderListener(this);
            }
        }
        M();
        this.v = getResources().getDimensionPixelSize(R.dimen.pv);
        this.u = screenWidth / this.w;
        this.t = screenHeight / this.x;
        if (!getIntent().getBooleanExtra(dji.pilot.c.b.a, true)) {
            this.r.b();
        }
        e(true);
        this.bI = new g(this);
        dji.midware.data.manager.P3.g.getInstance().a((int) HorizonalSegmentView.N);
        dji.pilot.c.a.n = true;
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        }
        if (DataCameraGetPushShotParams.getInstance().isGetted()) {
            onEventBackgroundThread(DataCameraGetPushShotParams.getInstance());
        }
        this.C = new i(this);
        this.C.b();
        L();
        dji.pilot.flyunlimit.b.getInstance(this);
        this.O = new dji.midware.util.a();
        this.O.a(new 1(this));
    }

    protected dji.midware.util.a.b.b e() {
        int i = DJIVideoDecoder.width;
        int i2 = DJIVideoDecoder.height;
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if (c == ProductType.KumquatS || c == ProductType.Pomato) {
            float f = (((float) i) * 1.0f) / ((float) i2);
            if (Math.abs(f - dji.midware.util.a.b.a) < Math.abs(f - dji.midware.util.a.b.c)) {
                return dji.midware.util.a.b.b.a;
            }
            if (Math.abs(f - dji.midware.util.a.b.c) < Math.abs(f - dji.midware.util.a.b.b)) {
                return dji.midware.util.a.b.b.b;
            }
            return dji.midware.util.a.b.b.c;
        } else if (this.S == RatioType.R_4_3 && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
            return dji.midware.util.a.b.b.d;
        } else {
            if (this.S == RatioType.R_3_2 && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
                return dji.midware.util.a.b.b.e;
            }
            return dji.midware.util.a.b.b.a;
        }
    }

    private int H() {
        CameraOrientation cameraOrientation = (CameraOrientation) dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.bW);
        if (cameraOrientation == null || !cameraOrientation.equals(CameraOrientation.Portrait)) {
            this.Q = false;
            return 0;
        }
        this.Q = true;
        return 270;
    }

    private int I() {
        return this.r.c() ? screenWidth : q.a;
    }

    private int J() {
        return this.r.c() ? screenHeight : q.b;
    }

    public void f() {
        dji.midware.util.a.b a = this.O.a();
        a.a(e());
        int H = H();
        a.a(H != 0);
        if (this.b != null) {
            this.b.setRotateDegree(H);
        }
        a.a(I(), J());
        this.O.b();
    }

    public void a(int i) {
        dji.midware.util.a.b a = this.O.a();
        a.a(e());
        a.a(i != 0);
        if (this.b != null) {
            this.b.setRotateDegree(i);
        }
        a.a(I(), J());
        this.O.b();
    }

    private View K() {
        return this.b == null ? this.a : this.b;
    }

    private void L() {
        this.bJ = new RecordReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.record");
        registerReceiver(this.bJ, intentFilter);
    }

    public boolean g() {
        return this.n.isShown() || c() || ((this.e != null && this.e.hasDlgShowing()) || (!(this.r == null || this.r.c()) || (this.aL != null && this.aL.isShown())));
    }

    private void e(boolean z) {
        if (!ServiceManager.getInstance().isRemoteOK()) {
            this.e.hideCheckListDlg();
            if (!z) {
            }
        } else if (d.h() && !this.mGuideShowing && !DataOsdGetPushCommon.getInstance().isMotorUp()) {
            if (this.aL == null || !this.aL.isShown()) {
                this.e.showCheckListDlg();
                d.i();
            }
        }
    }

    private void M() {
        if (ServiceManager.getInstance().isConnected()) {
            int metering = DataCameraGetPushShotParams.getInstance().getMetering();
            DJILogHelper.getInstance().LOGD(this.TAG, "测光=" + metering);
            if (metering == 2) {
                au();
            } else if (metering == 0) {
                this.aZ.sendEmptyMessage(af);
                av();
            }
        }
    }

    private void N() {
        this.aC.show();
        this.bh = new Timer();
        this.bh.schedule(new 23(this), 0, 1000);
    }

    public void onEventMainThread(DJIFpvTopBarBaseView.c cVar) {
        if (cVar.a == DJIFpvTopBarBaseView.c.a.a) {
            finishThis();
        } else if (cVar.a == DJIFpvTopBarBaseView.c.a.b) {
            a(false);
            dji.thirdparty.a.c.a().e(dji.pilot.groundStation.a.a.c.h);
        } else if (cVar.a != DJIFpvTopBarBaseView.c.a.c) {
        } else {
            if (this.aL == null || !this.aL.isShown()) {
                b(false);
                dji.thirdparty.a.c.a().e(dji.pilot.groundStation.a.a.c.i);
                if (this.bN) {
                    this.bN = false;
                    dji.pilot.flyforbid.a.getInstance(this).a((Context) this);
                }
            }
        }
    }

    private void O() {
        this.bl = System.currentTimeMillis();
        this.ba = new dji.pilot.fpv.control.a(this, this.aA);
        this.ba.a();
        this.bc = new 31(this);
        this.aZ = new a(this);
        this.be = AnimationUtils.loadAnimation(this, R.anim.bt);
        this.bf = AnimationUtils.loadAnimation(this, R.anim.bu);
        DJIGenSettingDataManager.getInstance().a(this.bc);
        this.bE = new d(this);
        this.A = new e(this, screenHeight);
        this.ch = new n(this);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void P() {
        this.l.setOnClickListener(this);
        int[] rules = ((LayoutParams) findViewById(R.id.a2i).getLayoutParams()).getRules();
        if (rules != null && rules.length >= 11) {
            this.bv = rules[11] == -1;
        }
        this.K.setGsOnRight(this.bv);
        this.aD.dispatchOnCreate();
        this.aW.dispatchOnCreate();
        this.h.dispatchOnCreate();
        this.aQ.dispatchOnCreate();
        this.aM.dispatchOnCreate();
        this.aM.setMutexView(this.aD);
        this.aO.bindAnimView((DJICameraAnimView) findViewById(R.id.a1z));
        ag();
        ai();
        ah();
        this.r = new q(this, this.aA, this.bv);
        this.r.a(this.bd);
        this.r.a(new 32(this));
        this.bM = new GestureDetector(this, this.cn);
        this.bM.setIsLongpressEnabled(false);
        this.aF.setOnTouchListener(new 33(this));
        this.aJ.setOnLongPressListener(new 34(this));
        this.aP.dispatchOnCreate();
        this.aH.setOnClickListener(this);
        this.m.setOnJoystickVisibilityChangedListenner(new 35(this));
    }

    protected void a(MotionEvent motionEvent) {
        if (!DataCameraGetPushStateInfo.getInstance().beInTrackingMode()) {
            if (dji.pilot.fpv.d.b.r()) {
                this.A.a(motionEvent);
            } else if (dji.pilot.fpv.d.b.s()) {
                FpvPopWarnView.getInstance(this).pop(R.drawable.gs_warning_icon, R.string.unsupport_dzoom_4k, FpvPopWarnView.a.a);
            }
        }
    }

    public void h() {
        if (DataCameraGetPushStateInfo.getInstance().isGetted() && dji.pilot.fpv.d.b.e(DataCameraGetPushStateInfo.getInstance().getCameraType()) && dji.pilot.fpv.camera.more.a.getInstance().f() && this.aN.getVisibility() != 0 && ap() && dji.pilot.fpv.flightmode.c.getInstance().a() != dji.pilot.fpv.flightmode.c.b.g) {
            onEventMainThread(DJIMFDemarcateView.a.a);
        }
    }

    protected void i() {
        float f;
        float f2 = 0.0f;
        this.i.hideView();
        float f3 = (float) DJIBaseActivity.screenWidth;
        float f4 = (float) DJIBaseActivity.screenHeight;
        int i = DJIVideoDecoder.width;
        int i2 = DJIVideoDecoder.height;
        float f5 = (float) i;
        float f6 = (float) i2;
        DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
        boolean z = MODE.TAKEPHOTO == instance.getMode() && !dji.pilot.fpv.camera.a.a.b(instance.getCameraType());
        if (z) {
            f5 = ((float) i2) / DataCameraGetPushShotParams.getInstance().getImageRatio().getRatioNumber();
            f = ((((float) i) - f5) / ((float) i)) / 2.0f;
        }
        if (((float) DJIBaseActivity.screenWidth) * f6 > ((float) DJIBaseActivity.screenHeight) * f5) {
            f3 = ((((float) DJIBaseActivity.screenHeight) * 1.0f) / f6) * f5;
            f = (((float) DJIBaseActivity.screenWidth) - f3) / 2.0f;
        } else {
            f4 = (float) ((int) (((((float) DJIBaseActivity.screenWidth) * 1.0f) / f5) * f6));
            f = 0.0f;
            f2 = (((float) DJIBaseActivity.screenHeight) - f4) / 2.0f;
        }
        dji.pilot.fpv.camera.a.a.a(null, "video-" + i2 + com.alipay.sdk.j.i.b + i + com.alipay.sdk.j.i.b + f4 + com.alipay.sdk.j.i.b + f3 + com.alipay.sdk.j.i.b + f5 + com.alipay.sdk.j.i.b + f6 + com.alipay.sdk.j.i.b + f + com.alipay.sdk.j.i.b + f2);
        f5 = f4 / dji.gs.e.b.a;
        f4 = f3 / dji.gs.e.b.a;
        f4 = ((f4 * ((float) this.F)) / 2.0f) + ((((float) this.D) * f4) + f);
        f5 = ((f5 * ((float) this.G)) / 2.0f) + ((((float) this.E) * f5) + f2);
        DJILogHelper.getInstance().LOGD("", "对焦位置：" + this.D + ", " + this.E + " 宽度：" + this.F + ", " + this.G, false, true);
        this.i.setPosition(f4, f5);
        q();
    }

    private void Q() {
        float f;
        float f2 = 0.0f;
        this.i.hideView();
        DJIDeviceType deviceType = DJIOriLayout.getDeviceType();
        int i;
        int i2;
        if (DJIDeviceType.Phone != deviceType && DJIDeviceType.DJI5_5 != deviceType) {
            if (DJIDeviceType.Pad == deviceType) {
                if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD || DataCameraGetPushShotParams.getInstance().getImageRatio() == RatioType.R_16_9) {
                    i = (int) (((((double) screenWidth) / 16.0d) * 9.0d) / 16.0d);
                    i2 = (int) (((double) screenWidth) / 16.0d);
                    f = (((float) (i2 * this.F)) / 2.0f) + ((float) (this.D * i2));
                    f2 = (((float) (i * this.G)) / 2.0f) + ((float) (((screenHeight / 2) - ((screenWidth * 9) / 32)) + (this.E * i)));
                } else if (DataCameraGetPushShotParams.getInstance().getImageRatio() == RatioType.R_4_3) {
                    i = (int) (((double) screenHeight) / 16.0d);
                    i2 = (int) (((double) screenWidth) / 16.0d);
                    f = (((float) (i2 * this.F)) / 2.0f) + ((float) (this.D * i2));
                    f2 = (((float) (i * this.G)) / 2.0f) + ((float) (this.E * i));
                } else if (DataCameraGetPushShotParams.getInstance().getImageRatio() == RatioType.R_3_2) {
                    i = (int) (((double) screenHeight) / 16.0d);
                    i2 = (int) (((((double) screenHeight) * 3.0d) / 2.0d) / 16.0d);
                    f = (((float) (i2 * this.F)) / 2.0f) + ((float) (((screenWidth / 2) - ((screenHeight * 3) / 4)) + (this.D * i2)));
                    f2 = (((float) (i * this.G)) / 2.0f) + ((float) (this.E * i));
                }
            }
            f = 0.0f;
        } else if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD || DataCameraGetPushShotParams.getInstance().getImageRatio() == RatioType.R_16_9) {
            i = (int) (((double) screenHeight) / 16.0d);
            i2 = (int) (((double) screenWidth) / 16.0d);
            f = (((float) (i2 * this.F)) / 2.0f) + ((float) (this.D * i2));
            f2 = (((float) (i * this.G)) / 2.0f) + ((float) (this.E * i));
        } else if (DataCameraGetPushShotParams.getInstance().getImageRatio() == RatioType.R_4_3) {
            i = (int) (((double) screenHeight) / 16.0d);
            i2 = (int) (((((double) screenHeight) * dji.pilot2.multimoment.videolib.c.c) / 3.0d) / 16.0d);
            f = (((float) (i2 * this.F)) / 2.0f) + ((float) (((screenWidth / 2) - ((screenHeight * 2) / 3)) + (this.D * i2)));
            f2 = (((float) (i * this.G)) / 2.0f) + ((float) (this.E * i));
        } else {
            if (DataCameraGetPushShotParams.getInstance().getImageRatio() == RatioType.R_3_2) {
                i = (int) (((double) screenHeight) / 16.0d);
                i2 = (int) (((((double) screenHeight) * 3.0d) / 2.0d) / 16.0d);
                f = (((float) (i2 * this.F)) / 2.0f) + ((float) (((screenWidth / 2) - ((screenHeight * 3) / 4)) + (this.D * i2)));
                f2 = (((float) (i * this.G)) / 2.0f) + ((float) (this.E * i));
            }
            f = 0.0f;
        }
        DJILogHelper.getInstance().LOGD("", "对焦位置：" + this.D + ", " + this.E + " 宽度：" + this.F + ", " + this.G, false, true);
        this.i.setPosition(f, f2);
        q();
    }

    public void onEventMainThread(DJIMFDemarcateView.a aVar) {
        if (aVar == DJIMFDemarcateView.a.c) {
            j();
            A();
            k();
        } else if (aVar == DJIMFDemarcateView.a.b) {
            d(true);
            if (this.aR != null) {
                this.aR.go();
            }
            this.aN.hideView();
            this.aQ.hideView();
            this.h.hideView();
            this.g.switchMode(DJISwitchModeView.a.b);
        } else if (aVar != DJIMFDemarcateView.a.a || !DataCameraGetPushStateInfo.getInstance().isGetted() || !dji.pilot.fpv.d.b.e(DataCameraGetPushStateInfo.getInstance().getCameraType()) || this.n.isShown() || c() || !this.r.c()) {
        } else {
            if (this.aL == null || (this.aL.getVisibility() != 0 && ap())) {
                this.j.showView();
                this.aN.hideView();
                this.aQ.hideView();
                this.h.hideView();
                d(true);
            }
        }
    }

    protected void j() {
        if (!DataCameraGetPushStateInfo.getInstance().isGetted() || !dji.pilot.fpv.d.b.c(DataCameraGetPushStateInfo.getInstance().getCameraType()) || !this.r.c()) {
            return;
        }
        if ((this.aL == null || this.aL.getVisibility() != 0) && this.aN.getVisibility() != 0 && this.j.getVisibility() != 0 && ap() && !c()) {
            this.h.showView();
        }
    }

    public void onEventMainThread(DJIFocusRingView.a aVar) {
        if (aVar == DJIFocusRingView.a.b) {
            if (this.aR != null) {
                this.aR.go();
            }
            this.aN.hideView();
            a(DJISwitchModeView.a);
        } else if (aVar == DJIFocusRingView.a.a) {
            j();
        }
    }

    protected void k() {
        if (!DataCameraGetPushStateInfo.getInstance().isGetted() || !dji.pilot.fpv.d.b.a(DataCameraGetPushStateInfo.getInstance().getCameraType(), DataCameraGetPushShotInfo.getInstance().getZoomFocusType()) || !this.r.c()) {
            return;
        }
        if ((this.aL == null || this.aL.getVisibility() != 0) && ap() && this.j.getVisibility() != 0 && !c()) {
            this.aQ.showView();
        }
    }

    public void onEventMainThread(DJIFocusDistanceView.a aVar) {
        if (aVar == DJIFocusDistanceView.a.a) {
            k();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a21:
                R();
                return;
            case R.id.a3v:
                S();
                return;
            default:
                return;
        }
    }

    private void R() {
        this.aZ.sendMessage(this.aZ.obtainMessage(32768, 0, 0));
        au();
    }

    private void S() {
        if (this.l.getAlpha() == 1.0f) {
            this.l.setAlpha(0.5f);
            dji.thirdparty.a.c.a().e(dji.pilot.fpv.model.n.a.LEFTMENU_JS_CLICK_STOP);
            return;
        }
        this.l.setAlpha(1.0f);
        dji.thirdparty.a.c.a().e(dji.pilot.fpv.model.n.a.LEFTMENU_JS_CLICK_START);
    }

    public void onClickBackground(View view) {
    }

    private void T() {
        TRIPOD_STATUS deformStatus = DataFlycGetPushDeformStatus.getInstance().getDeformStatus();
        if (deformStatus != TRIPOD_STATUS.UNKNOWN && this.bk != deformStatus && dji.pilot.fpv.d.b.g(null)) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.bk != TRIPOD_STATUS.UNKNOWN && currentTimeMillis - this.bl >= 8000) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.b = dji.pilot.fpv.d.b.a(deformStatus);
                bVar.a = DJIErrorPopView.d.NOTIFY;
                dji.thirdparty.a.c.a().e(bVar);
            }
            this.bk = deformStatus;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void U() {
        /*
        r8 = this;
        r7 = 1;
        r6 = 8;
        r5 = 0;
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.isSwaveWork();
        r1 = r8.s;
        if (r1 == r0) goto L_0x0012;
    L_0x0010:
        r8.s = r0;
    L_0x0012:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.getFlightAction();
        r1 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r1 = r1.getFlycState();
        r2 = r8.bp;
        if (r2 == r1) goto L_0x0054;
    L_0x0026:
        r8.bp = r1;
        if (r0 == 0) goto L_0x0050;
    L_0x002a:
        r2 = dji.pilot.fpv.d.b.b(r1);
        if (r2 == 0) goto L_0x0050;
    L_0x0030:
        r2 = dji.pilot.fpv.d.b.a(r0);
        r3 = r2[r5];
        if (r3 == 0) goto L_0x0050;
    L_0x0038:
        r3 = new dji.pilot.fpv.view.DJIErrorPopView$b;
        r3.<init>();
        r4 = r2[r5];
        r3.b = r4;
        r2 = r2[r7];
        if (r2 != 0) goto L_0x0188;
    L_0x0045:
        r2 = dji.pilot.fpv.view.DJIErrorPopView.d.NOTIFY;
        r3.a = r2;
    L_0x0049:
        r2 = dji.thirdparty.a.c.a();
        r2.e(r3);
    L_0x0050:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE.GoHome;
        if (r1 != r2) goto L_0x0054;
    L_0x0054:
        r1 = r8.bm;
        if (r1 == r0) goto L_0x007e;
    L_0x0058:
        if (r0 == 0) goto L_0x007e;
    L_0x005a:
        r8.bm = r0;
        r0 = r8.bm;
        r1 = dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION.AIRPORT_AVOID_LANDING;
        if (r0 != r1) goto L_0x007e;
    L_0x0062:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.groundOrSky();
        r1 = 2;
        if (r0 != r1) goto L_0x007e;
    L_0x006d:
        r0 = new dji.pilot.fpv.view.DJIErrorPopView$b;
        r0.<init>();
        r1 = 2131298317; // 0x7f09080d float:1.8214604E38 double:1.0530012795E-314;
        r0.b = r1;
        r1 = dji.thirdparty.a.c.a();
        r1.e(r0);
    L_0x007e:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.getMotorFailedCause();
        r1 = r8.bn;
        if (r1 == r0) goto L_0x017d;
    L_0x008a:
        r8.bn = r0;
        r1 = dji.pilot.publics.control.a.getInstance();
        r1 = r1.l();
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.DeviceLocked;
        if (r0 != r2) goto L_0x009a;
    L_0x0098:
        if (r1 != 0) goto L_0x00b2;
    L_0x009a:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.IMUNeedCalibration;
        if (r0 == r2) goto L_0x00b2;
    L_0x009e:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.IMUWarning;
        if (r0 == r2) goto L_0x00b2;
    L_0x00a2:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.CompassBig;
        if (r0 == r2) goto L_0x00b2;
    L_0x00a6:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.CompassError;
        if (r0 == r2) goto L_0x00b2;
    L_0x00aa:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.CompassFailed;
        if (r0 == r2) goto L_0x00b2;
    L_0x00ae:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.CompassNoiseBig;
        if (r0 != r2) goto L_0x00d3;
    L_0x00b2:
        r0 = r8.e;
        if (r0 == 0) goto L_0x00d3;
    L_0x00b6:
        r0 = r8.e;
        r0 = r0.hasDlgShowing();
        if (r0 != 0) goto L_0x00d3;
    L_0x00be:
        r0 = r8.isVisible;
        if (r0 == 0) goto L_0x00d3;
    L_0x00c2:
        r0 = r8.aL;
        if (r0 == 0) goto L_0x00ce;
    L_0x00c6:
        r0 = r8.aL;
        r0 = r0.isShown();
        if (r0 != 0) goto L_0x00d3;
    L_0x00ce:
        r0 = r8.e;
        r0.showCheckListDlg();
    L_0x00d3:
        r0 = dji.pilot.publics.control.a.getInstance();
        r0 = r0.j;
        if (r0 == 0) goto L_0x017d;
    L_0x00db:
        r0 = r8.bn;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.DeviceLocked;
        if (r0 != r2) goto L_0x0104;
    L_0x00e1:
        if (r1 != 0) goto L_0x0104;
    L_0x00e3:
        r0 = dji.midware.data.manager.P3.i.getInstance();
        r0 = r0.c();
        r2 = dji.midware.data.config.P3.ProductType.Tomato;
        if (r0 == r2) goto L_0x00fb;
    L_0x00ef:
        r0 = dji.midware.data.manager.P3.i.getInstance();
        r0 = r0.c();
        r2 = dji.midware.data.config.P3.ProductType.Pomato;
        if (r0 != r2) goto L_0x018e;
    L_0x00fb:
        r0 = dji.thirdparty.a.c.a();
        r2 = dji.pilot.publics.control.a.e.NO;
        r0.e(r2);
    L_0x0104:
        r0 = r8.bn;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.DeviceLocked;
        if (r0 == r2) goto L_0x0110;
    L_0x010a:
        r0 = r8.bn;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.NoviceProtected;
        if (r0 != r2) goto L_0x017d;
    L_0x0110:
        if (r1 == 0) goto L_0x017d;
    L_0x0112:
        r0 = r8.bP;
        if (r0 != 0) goto L_0x0178;
    L_0x0116:
        r0 = new dji.pilot.fpv.leftmenu.b;
        r0.<init>(r8);
        r8.bP = r0;
        r0 = r8.bP;
        r0.a(r7);
        r0 = r8.bP;
        r1 = new dji.pilot.fpv.activity.DJIBaseNewPreviewActivity$3;
        r1.<init>(r8);
        r0.a(r1);
        r0 = r8.bP;
        r0.d(r6);
        r0 = r8.bP;
        r0 = r0.a(r6, r5);
        r0.e(r6);
        r0 = r8.bP;
        r1 = "";
        r0.a(r6, r1);
        r0 = r8.bP;
        r1 = 2131296822; // 0x7f090236 float:1.8211572E38 double:1.053000541E-314;
        r1 = r8.getString(r1);
        r0.a(r1);
        r0 = dji.midware.data.manager.P3.i.getInstance();
        r0 = r0.c();
        r0 = r0.isFromWifi();
        if (r0 == 0) goto L_0x01cf;
    L_0x015b:
        r0 = r8.bP;
        r1 = 2131296821; // 0x7f090235 float:1.821157E38 double:1.0530005403E-314;
        r1 = r8.getString(r1);
        r0.b(r1);
    L_0x0167:
        r0 = r8.bP;
        r1 = r8.getResources();
        r2 = 2131427846; // 0x7f0b0206 float:1.847732E38 double:1.0530652753E-314;
        r1 = r1.getDimension(r2);
        r1 = (int) r1;
        r0.c(r1);
    L_0x0178:
        r0 = r8.bP;
        r0.show();
    L_0x017d:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.getFlycVersion();
        r8.bu = r0;
        return;
    L_0x0188:
        r2 = dji.pilot.fpv.view.DJIErrorPopView.d.WARNING;
        r3.a = r2;
        goto L_0x0049;
    L_0x018e:
        r0 = r8.bQ;
        if (r0 != 0) goto L_0x01c0;
    L_0x0192:
        r0 = new dji.pilot.publics.widget.b;
        r0.<init>(r8, r5);
        r8.bQ = r0;
        r0 = r8.bQ;
        r0.f();
        r0 = r8.bQ;
        r2 = 2131296512; // 0x7f090100 float:1.8210943E38 double:1.0530003877E-314;
        r0.a(r2);
        r0 = r8.bQ;
        r2 = 2131300423; // 0x7f091047 float:1.8218875E38 double:1.05300232E-314;
        r0.b(r2);
        r0 = r8.bQ;
        r2 = 2131296493; // 0x7f0900ed float:1.8210904E38 double:1.0530003783E-314;
        r0.d(r2);
        r0 = r8.bQ;
        r2 = new dji.pilot.fpv.activity.DJIBaseNewPreviewActivity$2;
        r2.<init>(r8);
        r0.a(r2);
    L_0x01c0:
        r0 = r8.bQ;
        r0 = r0.isShowing();
        if (r0 != 0) goto L_0x0104;
    L_0x01c8:
        r0 = r8.bQ;
        r0.show();
        goto L_0x0104;
    L_0x01cf:
        r0 = dji.midware.data.manager.P3.i.getInstance();
        r0 = r0.c();
        r1 = dji.midware.data.config.P3.ProductType.Tomato;
        if (r0 != r1) goto L_0x01e8;
    L_0x01db:
        r0 = r8.bP;
        r1 = 2131300658; // 0x7f091132 float:1.8219352E38 double:1.053002436E-314;
        r1 = r8.getString(r1);
        r0.b(r1);
        goto L_0x0167;
    L_0x01e8:
        r0 = r8.bP;
        r1 = 2131296820; // 0x7f090234 float:1.8211567E38 double:1.05300054E-314;
        r1 = r8.getString(r1);
        r0.b(r1);
        goto L_0x0167;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.fpv.activity.DJIBaseNewPreviewActivity.U():void");
    }

    private void V() {
        if (this.bO == null) {
            this.bO = new dji.pilot.fpv.leftmenu.b(this);
            this.bO.a(1);
            this.bO.a(getString(R.string.fpv_cancel_gohome_title));
            this.bO.b(R.drawable.leftmenu_dlg_gohome);
            this.bO.b(getString(R.string.fpv_cancel_gohome_desc));
            this.bO.a(8, 0);
            this.bO.e(8);
            this.bO.d(8);
            this.bO.c(getString(R.string.fpv_before_cancel_gohome));
            this.bO.a(new 4(this));
            this.bO.o();
        }
        if (!this.bO.isShowing()) {
            this.bO.show();
            W();
        }
    }

    private void W() {
        if (this.bO != null && this.bO.isShowing()) {
            int[] partVoltages = DataCenterGetPushBatteryCommon.getInstance().getPartVoltages();
            int C = dji.pilot.battery.a.a.C();
            int i = 0;
            int i2 = 0;
            while (i < partVoltages.length && i < C) {
                if (i == 0) {
                    i2 = partVoltages[i];
                } else if (i2 > partVoltages[i]) {
                    i2 = partVoltages[i];
                }
                i++;
            }
            this.bO.a(0, getString(R.string.fpv_cancel_gohome_littletitle, new Object[]{Integer.valueOf(DataFlycGetPushSmartBattery.getInstance().getBattery()), Float.valueOf((((float) i2) * 1.0f) / 1000.0f)}));
        }
    }

    private void X() {
        if (this.bO != null && this.bO.isShowing()) {
            this.bO.dismiss();
            this.bO = null;
        }
    }

    private void Y() {
        SmartGoHomeStatus goHomeStatus = DataFlycGetPushSmartBattery.getInstance().getGoHomeStatus();
        if (goHomeStatus == SmartGoHomeStatus.NON_GOHOME && DataOsdGetPushCommon.getInstance().isMotorUp()) {
            if (this.bC == null || !this.bC.isShowing()) {
                if (DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown() > 0) {
                    c(8193);
                }
            } else if (this.bC.isShowing() && 8193 == this.bD) {
                a(false, DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown());
            }
        } else if ((goHomeStatus == SmartGoHomeStatus.GOHOME_ALREADY || goHomeStatus == SmartGoHomeStatus.GOHOME) && 8193 == this.bD) {
            ad();
        }
        if (!cc) {
            int status = DataFlycGetPushSmartBattery.getInstance().getStatus();
            if (status != this.ca) {
                this.ca = status;
                if ((this.ca & 2048) == 2048) {
                    if (this.cb == null) {
                        this.cb = new dji.pilot.fpv.leftmenu.b(this);
                        this.cb.a(1);
                        this.cb.a(new 5(this));
                        this.cb.d(8);
                        this.cb.a(8, 0).e(8);
                        this.cb.a(8, "");
                        this.cb.a(false);
                        this.cb.b(getString(R.string.battery_first_charge_not_full));
                    }
                    this.cb.show();
                    cc = true;
                }
            }
        }
    }

    private void Z() {
        boolean isBeginnerMode = DataOsdGetPushHome.getInstance().isBeginnerMode();
        if (this.bo != isBeginnerMode) {
            this.bo = isBeginnerMode;
            if (!this.bo) {
                dji.pilot.fpv.d.b.a(2, true);
            } else if (dji.pilot.fpv.d.b.a(2)) {
                dji.pilot.fpv.d.b.a(2, false);
                this.e.hideDialog();
                c(8194);
            }
        }
        isBeginnerMode = DataOsdGetPushHome.getInstance().isReatchLimitHeight();
        boolean isReatchLimitDistance = DataOsdGetPushHome.getInstance().isReatchLimitDistance();
        if (this.bu >= 3) {
            DJIErrorPopView.b bVar;
            if (this.bs != isBeginnerMode) {
                this.bs = isBeginnerMode;
                if (this.bs) {
                    bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.fpv_errorpop_altitude_limit;
                    dji.thirdparty.a.c.a().e(bVar);
                    dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.y);
                }
            }
            if (this.bt != isReatchLimitDistance) {
                this.bt = isReatchLimitDistance;
                if (this.bt) {
                    bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.fpv_errorpop_distance_limit;
                    dji.thirdparty.a.c.a().e(bVar);
                    dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.x);
                }
            }
        }
    }

    private void aa() {
        int i = R.string.fpv_height_imperial;
        if (this.bx != null && this.bx.isShowing() && this.by != FLYC_STATE.GoHome) {
            float height;
            float b;
            if (this.by == FLYC_STATE.AutoLanding) {
                height = (float) ((int) (((float) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1f));
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    b = (float) ((int) DJIGenSettingDataManager.getInstance().b(height));
                } else {
                    i = R.string.fpv_height_metric;
                    b = height;
                }
                this.bx.a(getString(i, new Object[]{Float.valueOf(b)}));
            } else if (this.by == FLYC_STATE.AutoTakeoff) {
                height = (float) ((int) (((float) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1f));
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    b = (float) ((int) DJIGenSettingDataManager.getInstance().b(height));
                } else {
                    i = R.string.fpv_height_metric;
                    b = height;
                }
                this.bx.a(getString(i, new Object[]{Float.valueOf(b)}));
            }
        }
    }

    private void ab() {
        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.DropGohome).start(new 6(this));
    }

    private void c(int i) {
        if (this.bC == null) {
            this.bC = new dji.pilot.fpv.leftmenu.b(this);
            this.bC.a(new 7(this));
            this.bC.setOnDismissListener(new 8(this));
        }
        if (!dji.pilot.publics.control.a.getInstance().l() && this.bC != null && !this.bC.isShowing()) {
            this.bD = i;
            if (i == 8193) {
                a(true, DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown());
            } else if (i == 8194) {
                ac();
            }
            this.bC.show();
        }
    }

    private void a(boolean z, int i) {
        this.bC.a(getString(R.string.fpv_before_gohome_title, new Object[]{Integer.valueOf(i)}));
        if (z) {
            this.bC.a(1);
            this.bC.b(R.drawable.leftmenu_dlg_gohome);
            this.bC.a(8, 0);
            this.bC.e(8);
            this.bC.d(0);
            this.bC.b(getString(R.string.fpv_before_gohome_above_desc));
            this.bC.c(getString(R.string.app_cancel));
        }
    }

    private void ac() {
        this.bC.a(4);
        this.bC.a(8, 0);
        this.bC.e(8);
        this.bC.d(8);
        this.bC.a(getString(R.string.fpv_novice_takeoff_title));
        this.bC.b(getString(R.string.fpv_novice_takeoff_desc));
        this.bC.c(getString(R.string.app_isee));
    }

    private void d(int i) {
        if (this.bD == 8193) {
            DataFlycSmartAck.getInstance().setAck((byte) 1).start(null);
        }
    }

    private void e(int i) {
        if (this.bD == 8193) {
            DataFlycSmartAck.getInstance().setAck((byte) 2).start(null);
        } else if (this.bD == 8194) {
            ad();
        }
    }

    private void ad() {
        if (this.bC != null && this.bC.isShowing()) {
            this.bC.dismiss();
        }
    }

    private void ae() {
        boolean z;
        boolean z2 = false;
        boolean z3 = true;
        if ((this.ax & 64) != 0) {
            this.ax &= -65;
            z = true;
        } else {
            z = false;
        }
        if ((this.ax & 32) != 0) {
            T();
            this.ax &= -33;
        }
        if ((this.ax & 1) != 0) {
            U();
            e(true);
            this.W.d();
            this.ax &= -2;
            z2 = true;
        }
        if ((this.ax & 16) != 0) {
            this.ax &= -17;
            z2 = true;
        }
        if ((this.ax & 4) != 0) {
            this.ax &= -5;
        }
        if ((this.ax & 2) != 0) {
            if (this.by == FLYC_STATE.GoHome) {
                aa();
            }
            Z();
            this.bE.a(DataOsdGetPushHome.getInstance());
            this.W.e();
            this.ax &= -3;
        }
        if ((this.ax & 8) != 0) {
            Y();
            this.ax &= -9;
        } else {
            z3 = z;
        }
        if (this.bp != FLYC_STATE.GoHome || r1) {
            if (!z2) {
            }
        } else if (z2) {
        }
    }

    public void l() {
        this.aN.hideView();
        this.f.go();
        this.aP.hideChart();
        this.j.hideView();
        this.h.hideView();
        this.aS.go();
        this.aP.hideChart();
        this.i.hideView();
        af();
        dji.pilot.battery.a.a.getInstance().d();
        dji.pilot.battery.a.a.getInstance().f();
        DJIGenSettingDataManager.getInstance().f();
        this.ch.b();
        super.l();
    }

    private void af() {
        this.bm = null;
        this.bk = TRIPOD_STATUS.UNKNOWN;
        this.s = false;
        this.by = FLYC_STATE.OTHER;
        this.bn = MotorStartFailedCause.None;
        this.bp = FLYC_STATE.OTHER;
        this.cd = -1;
        this.ce = ExposureMode.OTHER;
        this.br = CameraType.OTHER;
    }

    private void b(boolean z, int i) {
        if (!z) {
        }
    }

    public void onEventMainThread(dji.pilot.fpv.model.n.b bVar) {
        if (bVar == dji.pilot.fpv.model.n.b.a) {
            if (this.e.getVisibility() == 0) {
                a(false, true);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.b) {
            if (this.e.getVisibility() == 0) {
                b(false, true);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.e) {
            if (this.e.getVisibility() == 0) {
                a(false, false);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.f) {
            b(false, false);
        } else if (bVar == dji.pilot.fpv.model.n.b.d && this.e.getVisibility() == 0) {
            this.W.h();
        }
    }

    protected void a(boolean z, boolean z2) {
        if (z) {
            this.f.go();
            this.aV.go();
            this.r.a(false);
            this.aW.setPlayBackViewVisible(true);
            this.e.hideDialog();
            this.aS.go();
        } else {
            this.r.a(true);
        }
        if (!z2) {
            this.e.go();
            this.e.startAnimation(this.bf);
        }
        this.aY.hideByOuter();
        this.aP.hideChart();
        this.aM.hideMenu(true);
        this.aW.go();
        this.aT.go();
        d(true);
        this.h.hideView();
        if (z) {
            this.i.hideView();
            this.aG.hide();
            this.aI.hide();
            this.aH.hide();
        }
        this.j.hideView();
        dji.thirdparty.a.c.a().e(dji.pilot.newfpv.f.d.d);
    }

    protected void a(boolean z) {
        a(z, false);
    }

    protected void m() {
        this.aM.hideMenu(true);
        this.r.a(true);
        DJIGenSettingDataManager.getInstance().b(false);
        dji.thirdparty.a.c.a().e(dji.pilot.newfpv.f.d.j);
    }

    protected void n() {
        if (!c()) {
            this.aM.showMenu();
        }
        dji.thirdparty.a.c.a().e(dji.pilot.newfpv.f.d.k);
    }

    protected void o() {
        this.aO.hide();
        this.aW.go();
        this.h.hideView();
        DJIGenSettingDataManager.getInstance().b(false);
        dji.thirdparty.a.c.a().e(dji.pilot.newfpv.f.d.l);
    }

    protected void p() {
        if (!c()) {
            this.aO.show();
            this.aW.show();
        }
        j();
        dji.thirdparty.a.c.a().e(dji.pilot.newfpv.f.d.m);
    }

    protected void b(boolean z, boolean z2) {
        if (!c()) {
            dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
            if (z) {
                this.aW.setPlayBackViewVisible(false);
                this.r.f();
                ag();
                ah();
            } else if (instance == null || !instance.q()) {
                this.aZ.postDelayed(new 9(this), 50);
            }
            if (instance == null || !instance.q()) {
                this.aM.showMenu();
                if (this.r.c()) {
                    this.aP.showChart();
                }
            }
            if (!z2) {
                this.e.show();
                this.e.startAnimation(this.be);
            }
            a(DJISwitchModeView.a);
            if (this.r.c()) {
                h();
                j();
                k();
                A();
                if (instance == null || !instance.p()) {
                    this.aW.show();
                }
                z();
                this.aY.showByOuter();
            }
            if (dji.pilot.c.d.f == 1) {
                this.W.g();
            }
            dji.thirdparty.a.c.a().e(dji.pilot.newfpv.f.d.c);
        }
    }

    @CallSuper
    protected void q() {
        if (DJISwitchModeView.a == DJISwitchModeView.a.b && !dji.pilot.visual.a.c.getInstance().l()) {
            this.i.showView();
        }
    }

    protected void b(boolean z) {
        b(z, false);
    }

    public void r() {
        DJILogHelper.getInstance().LOGD("", "======handleEnterPlayBackMode======", false, true);
        if (this.B != null) {
            this.B.a(false, 0);
        }
        switch (29.a[dji.midware.data.manager.P3.i.getInstance().c().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                com.dji.frame.c.b.a((Context) this, DJIPlayBackActivity.class);
                return;
            case 7:
            case 8:
                if (this.aL == null) {
                    a(true);
                    this.aF.show();
                    this.bb = new 10(this);
                    this.aL = (DJIPlayBackView) this.aB.inflate();
                    this.aL.setOnFullScreenListener(this.bb);
                    this.aL.setCenterHeight(screenHeight);
                    this.aL.show();
                    dji.thirdparty.a.c.a().e(dji.pilot.groundStation.a.a.c.h);
                    return;
                } else if (this.aL.getVisibility() != 0) {
                    a(true);
                    this.aF.show();
                    this.aL.show();
                    dji.thirdparty.a.c.a().e(dji.pilot.groundStation.a.a.c.h);
                    return;
                } else {
                    return;
                }
            default:
                DJILogHelper.getInstance().LOGD("playback", "cannot find type ", false, true);
                return;
        }
    }

    public void s() {
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if (!(c == ProductType.litchiS || c == ProductType.litchiC || c == ProductType.P34K || dji.pilot.publics.e.c.a(DataCameraGetPushStateInfo.getInstance().getCameraType()))) {
            b(true);
            e(false);
            if (this.aL != null) {
                this.aL.go();
            }
            dji.thirdparty.a.c.a().e(dji.pilot.groundStation.a.a.c.i);
        }
        if (this.B != null) {
            this.B.a(DJIGenSettingDataManager.getInstance().F(), R.raw.overexposure);
        }
    }

    public void t() {
        this.aN.handleMenuClick();
    }

    public void c(boolean z) {
        if (z) {
            this.j.hideView();
            this.h.hideView();
            this.aW.go();
            this.aT.go();
            d(false);
            dji.thirdparty.a.c.a().e(dji.pilot.newfpv.f.d.e);
        } else if (!c()) {
            this.aW.show();
            z();
            h();
            A();
            j();
            k();
            dji.thirdparty.a.c.a().e(dji.pilot.newfpv.f.d.f);
        }
        this.aO.handleMenuEvent(z);
    }

    private void ag() {
        int k = DJIGenSettingDataManager.getInstance().k();
        if (k == 0) {
            this.f.go();
            return;
        }
        if (k == 1) {
            this.f.setType(1);
        } else if (k == 2) {
            this.f.setType(2);
        } else if (k == 3) {
            this.f.setType(4);
        }
        this.f.show();
    }

    public void onEventMainThread(dji.pilot.fpv.camera.more.a.a aVar) {
        if (aVar == dji.pilot.fpv.camera.more.a.a.e) {
            ah();
        }
        if (aVar == dji.pilot.fpv.camera.more.a.a.d) {
            onEventMainThread(DJIMFDemarcateView.a.a);
        }
    }

    private void ah() {
        if (dji.pilot.fpv.camera.more.a.getInstance().s() == 0) {
            this.aS.go();
        } else {
            this.aS.show();
        }
    }

    private void ai() {
        if (this.aU == null) {
            this.aU = (DJIGimbalRollFineTuneView) this.aE.inflate();
        }
        if (DJIGenSettingDataManager.getInstance().o()) {
            this.aU.show();
        } else {
            this.aU.go();
        }
    }

    private void aj() {
        this.bd.h();
    }

    private void ak() {
        this.bd.i();
    }

    public void finishThis() {
        if (dji.pilot.liveshare.b.getInstance().isRunning() || dji.pilot.liveshare.b.getInstance().isLaunch() || this.bR) {
            v();
            return;
        }
        DJILogHelper.getInstance().LOGD(this.TAG, "on finishThis() start", false, false);
        if (this.aL == null || this.aL.getVisibility() != 0) {
            this.aZ.removeMessages(8192);
            this.aZ.removeMessages(4096);
            this.aZ.removeMessages(16384);
            this.aZ.removeMessages(24576);
            this.ba.b();
            dji.thirdparty.a.c.a().e(DJIHubActivity.a.b);
            al();
            dji.pilot.c.a.n = false;
            am();
            this.bR = true;
            finish();
            overridePendingTransition(0, 0);
            DJILogHelper.getInstance().LOGD(this.TAG, "on finishThis() end", false, false);
            return;
        }
        DJILogHelper.getInstance().LOGD(this.TAG, "out playback " + ServiceManager.getInstance().isConnected(), false, true);
        if (ServiceManager.getInstance().isConnected()) {
            DataSpecialControl.getInstance().setPlayBackType(false).start(20);
        } else {
            s();
        }
    }

    private void al() {
        if (this.q != null) {
            this.q.a(null);
            this.q.b();
            this.q = null;
        }
        if (this.B != null) {
            this.B.c();
            this.B = null;
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.bd.a(bundle);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.bd.d();
    }

    private void am() {
        unregisterReceiver(this.bJ);
        if (this.bI != null) {
            if (this.aL != null && this.aL.getVisibility() == 0) {
                this.aL.go();
            }
            this.bI.a();
            ao();
            DJIGenSettingDataManager.getInstance().b(this.bc);
            this.r.a();
            this.aD.dispatchOnDestroy();
            this.aW.dispatchOnDestroy();
            this.aM.dispatchOnDestroy();
            this.aP.dispatchOnDestroy();
            this.C.a();
            this.j.hideView();
            this.h.dispatchOnDestroy();
            this.aQ.dispatchOnDestroy();
            FpvPopWarnView.dispatchOnDestroy();
            this.bI = null;
        }
    }

    protected void onDestroy() {
        if (!this.bR) {
            finishThis();
        }
        super.onDestroy();
        this.bd.e();
        ay();
        Log.d(this.TAG, "onDestroy start");
        Log.d(this.TAG, "onDestroy" + this);
    }

    protected void onResume() {
        super.onResume();
        if (this.b != null) {
            this.b.onResume();
        }
        DJILogHelper.getInstance().LOGD("onresume", "Preview onResume" + this, false, true);
        this.bd.b();
    }

    protected Class<c> u() {
        return c.class;
    }

    protected void onPause() {
        super.onPause();
        if (this.b != null) {
            this.b.onPause();
        }
        this.bd.c();
    }

    protected void onStart() {
        super.onStart();
        this.bj = true;
        if (this.ax != 0) {
            this.aZ.sendEmptyMessageDelayed(4096, 200);
        }
        dji.pilot.fpv.d.e.b((Context) this);
        ServiceManager.getInstance().pauseService(false);
        dji.pilot.groundStation.a.a.a(this);
        dji.pilot.groundStation.a.a.getInstance(this).a(this.bd);
        dji.pilot.groundStation.a.a.getInstance(this).a(this.r);
        dji.pilot.groundStation.a.a.getInstance(this).c(true);
        dji.pilot.dji_groundstation.controller.a.getInstance(this);
        dji.pilot.flyunlimit.b.getInstance(this).a(this.bd);
        dji.pilot.flyforbid.a.getInstance(this).c((Context) this);
    }

    protected void onStop() {
        this.bj = false;
        super.onStop();
        dji.pilot.groundStation.a.a.getInstance(this).c(false);
        dji.pilot.groundStation.a.a.getInstance(this).b();
        dji.pilot.fpv.d.e.c((Context) this);
        dji.pilot.flyunlimit.b.getInstance(this).a(null);
        dji.pilot.flyforbid.a.getInstance(this).f();
    }

    public void onBackPressed() {
        if (!aA()) {
            finishThis();
        }
    }

    public void v() {
        if (this.bS == null) {
            this.bS = new dji.pilot.publics.widget.b(this);
            this.bS.a(R.string.app_warning).b(R.string.liveshare_base_exit).d(R.string.app_enter).e(R.string.app_cancel).a(new 13(this)).b(new 11(this));
        }
        this.bS.show();
    }

    public void oneFrameComeIn() {
        this.bg++;
    }

    public void resetVideoSurface(int i, int i2) {
        this.aZ.sendEmptyMessage(12288);
    }

    protected void a(int[] iArr, int[] iArr2) {
        System.arraycopy(iArr, 0, this.bF, 0, 2);
        System.arraycopy(iArr2, 0, this.bG, 0, 2);
        int i = this.v * 2;
        int i2 = iArr[1] - iArr[0];
        int i3 = iArr2[1] - iArr2[0];
        if (i2 == this.O.a().d()) {
            i2 -= i;
        }
        if (i3 == this.O.a().e()) {
            i3 -= i;
        }
        ViewGroup.LayoutParams layoutParams = this.aV.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i3;
        this.aV.setLayoutParams(layoutParams);
        this.i.setHVLimits(iArr, iArr2);
    }

    protected void a(int[] iArr, int[] iArr2, dji.midware.util.a.b.b bVar, boolean z) {
        dji.pilot.visual.a.c.getInstance().a(iArr, iArr2);
        RatioType ratioType = RatioType.R_16_9;
        if (bVar == dji.midware.util.a.b.b.e || bVar == dji.midware.util.a.b.b.b) {
            ratioType = RatioType.R_3_2;
        } else if (bVar == dji.midware.util.a.b.b.d || bVar == dji.midware.util.a.b.b.c) {
            ratioType = RatioType.R_4_3;
        } else {
            ratioType = RatioType.R_16_9;
        }
        dji.pilot.visual.a.c.getInstance().a(ratioType, ratioType, z);
        LayoutParams layoutParams = (LayoutParams) this.aJ.getLayoutParams();
        layoutParams.width = dji.pilot.visual.a.c.getInstance().d;
        layoutParams.height = dji.pilot.visual.a.c.getInstance().e;
        if (layoutParams.width == 0) {
            layoutParams.width = DJIBaseActivity.screenWidth;
        }
        if (layoutParams.height == 0) {
            layoutParams.height = DJIBaseActivity.screenHeight;
        }
        this.aJ.setLayoutParams(layoutParams);
        DJILogHelper.getInstance().LOGD(this.TAG, "width= " + layoutParams.width + " height= " + layoutParams.height, false, true);
    }

    protected void w() {
        this.u = (this.y[1] - this.y[0]) / this.w;
        this.t = (this.z[1] - this.z[0]) / this.x;
    }

    public void x() {
        f();
    }

    public dji.pilot.publics.c.f a(dji.midware.media.h.b.b bVar) {
        System.out.println("xxxgbac glsurface");
        this.B = bVar;
        if (this.q == null) {
            b(bVar);
        } else {
            this.q.a(bVar);
        }
        return this.q;
    }

    public void y() {
        if (this.q != null) {
            this.q.a(null);
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.B = dji.midware.media.h.e.a(getClass());
        this.B.a(surfaceTexture, i, i2);
        this.B.a(DJIGenSettingDataManager.getInstance().F(), R.raw.overexposure);
        if (this.q == null) {
            b(this.B);
        } else {
            this.q.a(this.B);
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.B != null) {
            this.B.a(i, i2);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.q != null) {
            this.q.a(null);
        }
        if (this.B != null) {
            this.B.c();
            this.B = null;
        }
        return false;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    protected void b(dji.midware.media.h.b.b bVar) {
        this.q = new dji.pilot.publics.c.f(this, bVar);
        this.q.a(this);
        FPVController.native_setDecodeMode(dji.pilot.publics.objects.g.b((Context) this, "DecodeMode", false));
    }

    private void an() {
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
    }

    private void ao() {
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
    }

    private boolean ap() {
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
        return instance == null || !instance.p();
    }

    private boolean aq() {
        return this.aJ.isNonVisionDlg();
    }

    protected void z() {
        if (dji.pilot.visual.a.c.getInstance().e() && F() && aq() && this.r.c() && !c()) {
            this.aT.show();
        }
    }

    protected void A() {
        Object obj = ((this.aL == null || this.aL.getVisibility() != 0) && this.aN.getVisibility() != 0 && ap() && dji.pilot.fpv.flightmode.c.getInstance().a() != dji.pilot.fpv.flightmode.c.b.g && aq()) ? 1 : null;
        if (obj != null) {
            this.aK.showCheck();
        }
    }

    protected void d(boolean z) {
        Object obj = (this.e.getVisibility() == 0 && this.aN.getVisibility() != 0 && ap() && aq()) ? 1 : null;
        if (z || obj == null) {
            this.aK.go();
        }
    }

    protected void B() {
        if (this.g.needShow() && !DataCameraGetPushStateInfo.getInstance().beInTrackingMode() && !dji.pilot.visual.a.c.getInstance().l() && this.r.c()) {
            if (this.aL == null || this.aL.getVisibility() != 0) {
                this.g.show();
            }
        }
    }

    public void onEventMainThread(DJISwitchModeView.b bVar) {
        if (bVar != DJISwitchModeView.b.b && bVar == DJISwitchModeView.b.a) {
            B();
        }
    }

    private void a(DJISwitchModeView.a aVar) {
        if (DJISwitchModeView.a.a == aVar) {
            this.i.hideView();
            if (this.ce == ExposureMode.OTHER && DataCameraGetPushShotParams.getInstance().isGetted()) {
                this.ce = DataCameraGetPushShotParams.getInstance().getExposureMode();
                this.cd = DataCameraGetPushShotParams.getInstance().isAELock() ? 1 : 0;
            }
            if (this.ce != ExposureMode.OTHER && ExposureMode.M != this.ce && this.cd == 0 && !dji.pilot.visual.a.c.getInstance().l()) {
                int metering = DataCameraGetPushShotParams.getInstance().getMetering();
                this.aG.show();
                this.aI.show();
                if (metering == 2) {
                    this.aH.show();
                    return;
                }
                this.aH.hide();
                this.aZ.sendMessageDelayed(this.aZ.obtainMessage(32768, 0, 0), 2000);
            }
        } else if (DJISwitchModeView.a.b == aVar) {
            this.aG.hide();
            this.aI.hide();
            this.aH.hide();
            q();
        }
    }

    public void onEventMainThread(DJISwitchModeView.a aVar) {
        a(aVar);
    }

    public void onEventMainThread(dji.pilot.visual.a.g.e eVar) {
        if (dji.pilot.visual.a.c.getInstance().e() && this.r.c() && !c()) {
            z();
        } else {
            this.aT.go();
        }
    }

    public void onEventMainThread(dji.pilot.visual.a.g.f fVar) {
        if (dji.pilot.visual.a.c.getInstance().l()) {
            if (dji.pilot.visual.a.c.getInstance().e() && this.r.c() && !c()) {
                z();
            }
            this.aG.hide();
            this.aI.hide();
            this.aH.hide();
            this.i.hideView();
            this.g.go();
            return;
        }
        this.aT.go();
        a(DJISwitchModeView.a);
        B();
    }

    public void onEventMainThread(dji.pilot.visual.a.g.d dVar) {
        if (dVar == dji.pilot.visual.a.g.d.a) {
            aB();
        }
    }

    public void onEventMainThread(DJIGlobalService.a aVar) {
        if (aVar == DJIGlobalService.a.C2LongPress) {
            this.f.setType(1);
            this.f.show();
        } else if (aVar == DJIGlobalService.a.C2PressUp) {
            ag();
        }
    }

    public void onEventMainThread(DJIPlayBackActivity.a aVar) {
        switch (29.b[aVar.ordinal()]) {
            case 1:
                if (this.q != null) {
                    DJILogHelper.getInstance().LOGD(this.TAG, "mVideoDecoder resetToManager", false, true);
                    this.q.a();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(ProductType productType) {
        switch (29.a[productType.ordinal()]) {
            case 4:
            case 5:
                return;
            default:
                finishThis();
                return;
        }
    }

    private void ar() {
        DJILogHelper.getInstance().LOGD(this.TAG, "showNoVideoGoHomeDialog isRemoteOK=" + ServiceManager.getInstance().isRemoteOK(), false, true);
        if (ServiceManager.getInstance().isRemoteOK()) {
        }
        if (this.bW == null) {
            this.bW = new dji.pilot.fpv.leftmenu.b(this);
            this.bW.a(1);
            this.bW.a(new 16(this));
            this.bW.d(getString(R.string.disconnect_gohome_alert_gohome_button));
            this.bW.d(0);
            this.bW.a(8, 0).e(8);
            this.bW.a(0, getString(R.string.disconnect_gohome_alert_title2));
        }
        this.bX = 5;
        this.bW.c();
        this.bW.show();
        DJILogHelper.getInstance().LOGD(this.TAG, "showNoVideoGoHomeDialog show", false, true);
        dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.v);
    }

    private void as() {
        if (this.bW != null && this.bW.isShowing()) {
            if (this.bX == 0) {
                this.bW.dismiss();
                at();
                return;
            }
            this.bW.b(getString(R.string.disconnect_gohome_alert_title, new Object[]{Integer.valueOf(this.bX)}));
            this.aZ.sendEmptyMessageDelayed(ak, 1000);
            this.bX--;
        }
    }

    private void at() {
        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.GOHOME).start(new 17(this));
    }

    public void onEventBackgroundThread(dji.midware.data.manager.P3.m mVar) {
        switch (29.c[mVar.ordinal()]) {
            case 1:
                this.aZ.post(this.bU);
                this.aZ.removeMessages(aj);
                this.aZ.removeMessages(ak);
                if (this.bW != null && this.bW.isShowing()) {
                    this.bW.dismiss();
                    return;
                }
                return;
            case 2:
                if (this.isVisible) {
                    this.aZ.post(this.bV);
                    if (ServiceManager.getInstance().isRemoteOK() && DataOsdGetPushCommon.getInstance().groundOrSky() == 2 && DataCameraGetPushStateInfo.getInstance().getMode() != MODE.PLAYBACK && DataCameraGetPushStateInfo.getInstance().getMode() != MODE.DOWNLOAD) {
                        this.aZ.sendEmptyMessageDelayed(aj, ToolTipPopup.a);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(dji.midware.media.h.a.f.a aVar) {
        if (this.B != null) {
            this.B.a(aVar.a, aVar.b);
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (29.d[oVar.ordinal()]) {
            case 1:
                M();
                this.bl = System.currentTimeMillis();
                this.aZ.removeMessages(16384);
                this.aZ.removeMessages(4096);
                this.ax = 127;
                this.aZ.sendEmptyMessageDelayed(4096, 200);
                this.aZ.removeMessages(8192);
                this.aZ.sendEmptyMessageDelayed(8192, az);
                this.aZ.sendEmptyMessage(24576);
                return;
            case 2:
                this.bq = null;
                if (this.aL != null) {
                    this.aL.setIsBackPBack(true);
                }
                this.ax = 0;
                this.aZ.removeMessages(4096);
                this.aZ.removeMessages(8192);
                this.aZ.sendEmptyMessageDelayed(16384, 200);
                return;
            default:
                return;
        }
    }

    private void f(int i) {
        if (this.bj && (this.ax & i) == 0) {
            this.ax |= i;
            if (!this.aZ.hasMessages(4096)) {
                this.aZ.sendEmptyMessageDelayed(4096, 200);
            }
        }
    }

    public void onEventBackgroundThread(DataDm368GetPushStatus dataDm368GetPushStatus) {
        if (dataDm368GetPushStatus.isDisableLiveview() && !this.bY) {
            this.bY = true;
            this.bZ.a(DM368CmdId.a, 0).start(new 18(this));
        }
    }

    protected void onBackgroundThreadOver(DataOsdGetPushCommon dataOsdGetPushCommon) {
        f(1);
        if (this.bj && !this.aZ.hasMessages(8192)) {
            this.aZ.sendEmptyMessageDelayed(8192, az);
        }
        this.T = true;
        this.aZ.removeMessages(al);
        this.aZ.sendEmptyMessageDelayed(al, 1000);
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        f(2);
    }

    public void onEventBackgroundThread(DataRcGetPushGpsInfo dataRcGetPushGpsInfo) {
        f(4);
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        f(16);
    }

    public void onEventBackgroundThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        f(8);
    }

    public void onEventBackgroundThread(DataFlycGetPushDeformStatus dataFlycGetPushDeformStatus) {
        f(32);
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        MODE mode = dataCameraGetPushStateInfo.getMode();
        if (mode != this.bq) {
            this.bq = mode;
            this.aZ.sendEmptyMessage(12288);
            if (this.bq == MODE.PLAYBACK) {
                this.aZ.sendMessage(this.aZ.obtainMessage(32768, 0, 0));
            }
            DJILogHelper.getInstance().LOGD(this.TAG, "cameramode=" + this.bq, false, true);
            if (this.bq == MODE.DOWNLOAD && (this.aL == null || this.aL.isBackPBack())) {
                DJILogHelper.getInstance().LOGD(this.TAG, "!mPlayBackView.isDownload()" + ServiceManager.getInstance().isConnected(), false, true);
                DataCameraSetMode.getInstance().a(MODE.PLAYBACK).start(new 19(this));
            }
            if (this.aL != null) {
                this.aL.setIsBackPBack(false);
            }
            if (this.bq == MODE.NEW_PLAYBACK) {
                DataCameraSetMode.getInstance().a(dji.pilot.c.d.a).start(null);
            }
        }
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (cameraType != this.br) {
            this.br = cameraType;
            this.aZ.post(new 20(this));
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        if (dji.pilot.fpv.d.b.c(DataCameraGetPushStateInfo.getInstance().getCameraType()) && !dji.pilot.visual.a.c.getInstance().l()) {
            int mFFocusStatus = dataCameraGetPushShotInfo.getMFFocusStatus();
            if (mFFocusStatus != this.cf) {
                this.cf = mFFocusStatus;
                if (mFFocusStatus == 1) {
                    this.i.hideView();
                    this.aG.hide();
                    this.aI.hide();
                    this.aH.hide();
                } else if (this.aL == null || this.aL.getVisibility() != 0) {
                    a(DJISwitchModeView.a);
                }
            }
            FuselageFocusMode fuselageFocusMode = dataCameraGetPushShotInfo.getFuselageFocusMode();
            if (fuselageFocusMode == FuselageFocusMode.OneAuto || fuselageFocusMode == FuselageFocusMode.ContinuousAuto) {
                mFFocusStatus = dataCameraGetPushShotInfo.getFocusStatus();
                if (this.cg != mFFocusStatus) {
                    this.cg = mFFocusStatus;
                    if (mFFocusStatus == 2) {
                        dji.pilot.fpv.camera.more.a.getInstance().C();
                    }
                }
            } else {
                this.cg = 0;
            }
            mFFocusStatus = dataCameraGetPushShotInfo.getFocusWindowStartX();
            int focusWindowStartY = dataCameraGetPushShotInfo.getFocusWindowStartY();
            int focusWindowRealNumX = dataCameraGetPushShotInfo.getFocusWindowRealNumX();
            int focusWindowRealNumY = dataCameraGetPushShotInfo.getFocusWindowRealNumY();
            if (!this.i.a) {
                return;
            }
            if (this.D != mFFocusStatus || this.E != focusWindowStartY || this.F != focusWindowRealNumX || this.G != focusWindowRealNumY) {
                this.D = dataCameraGetPushShotInfo.getFocusWindowStartX();
                this.E = dataCameraGetPushShotInfo.getFocusWindowStartY();
                this.F = dataCameraGetPushShotInfo.getFocusWindowRealNumX();
                this.G = dataCameraGetPushShotInfo.getFocusWindowRealNumY();
                if (this.aZ.hasMessages(an)) {
                    this.aZ.removeMessages(an);
                }
                this.i.a = false;
                this.aZ.sendEmptyMessage(an);
            }
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int i = dataCameraGetPushShotParams.isAELock() ? 1 : 0;
        ExposureMode exposureMode = dataCameraGetPushShotParams.getExposureMode();
        if (!(this.cd == i && exposureMode == this.ce)) {
            this.cd = i;
            this.ce = exposureMode;
            if (i == 1 || exposureMode == ExposureMode.M) {
                this.aZ.sendMessage(this.aZ.obtainMessage(32768, 0, 0));
            } else if (DataCameraGetPushShotParams.getInstance().getMetering() == 2 && !dji.pilot.visual.a.c.getInstance().l()) {
                this.aZ.sendMessage(this.aZ.obtainMessage(32768, 1, 0));
            }
        }
        RatioType imageRatio = DataCameraGetPushShotParams.getInstance().getImageRatio();
        if (imageRatio != this.S) {
            this.S = imageRatio;
            this.aZ.sendEmptyMessage(12288);
        }
        this.A.a(dataCameraGetPushShotParams);
    }

    public void onEventBackgroundThread(l lVar) {
        switch (29.e[lVar.ordinal()]) {
            case 1:
                if (this.w == 0) {
                    DataCameraGetMeteringArea.getInstance().start(this.bK);
                    return;
                }
                return;
            case 2:
                au();
                return;
            default:
                return;
        }
    }

    public void b(int i) {
        if (i == 0) {
            this.ck.a = SdModeView.a.c;
        } else {
            this.ck.a = SdModeView.a.a;
        }
        DJILogHelper.getInstance().LOGD("", "lose_osd blackMode=" + i, false, true);
        onEventMainThread(this.ck);
    }

    public void onEventMainThread(DataFlycGetPushAvoid dataFlycGetPushAvoid) {
        if (this.ch != null && dji.pilot.fpv.d.b.j(null)) {
            this.ch.onEventMainThread(dataFlycGetPushAvoid);
        }
    }

    public void onEventMainThread(SdModeView sdModeView) {
        this.ck = sdModeView;
        SdModeView.a aVar = sdModeView.a;
        if (this.cj == null) {
            this.cj = new dji.pilot.publics.widget.b(this, false);
            this.cj.a(R.string.app_tip);
            this.cj.d(R.string.app_enter);
            this.cj.a(new 21(this));
        }
        switch (29.f[aVar.ordinal()]) {
            case 1:
                this.cj.b(R.string.mc_switch_sd_mode_success);
                this.cj.show();
                sdModeView.setBlackStatus(false);
                dji.thirdparty.a.c.a().e(aVar);
                return;
            case 2:
                this.ci = 0;
                this.aZ.sendEmptyMessageDelayed(am, 1000);
                return;
            default:
                this.cj.b(R.string.mc_switch_sd_mode_error);
                this.cj.show();
                sdModeView.setBlackStatus(false);
                return;
        }
    }

    public void onEventMainThread(dji.midware.media.j.g.c cVar) {
        if (dji.pilot.c.a.p) {
            Log.i("DJIPreviewActivity", "received a bus event for bitmap");
            dji.midware.media.j.g.a(D(), cVar.a());
        }
    }

    public void onEventMainThread(DJICustomType dJICustomType) {
        boolean z = true;
        if (!this.n.isShown()) {
            dji.pilot.groundStation.a.a instance;
            switch (29.g[dJICustomType.ordinal()]) {
                case 1:
                    if (!aA() && !this.mGuideShowing && this.r.h() && !c()) {
                        this.r.b();
                        return;
                    }
                    return;
                case 2:
                    instance = dji.pilot.groundStation.a.a.getInstance(null);
                    if ((instance == null || !instance.c() || !instance.p()) && !aA() && !c()) {
                        if ((this.aL == null || !this.aL.isShown()) && !this.mGuideShowing && this.r.c() && !this.e.hasDlgShowing()) {
                            this.aN.handleMenuClick();
                            return;
                        }
                        return;
                    }
                    return;
                case 3:
                    instance = dji.pilot.groundStation.a.a.getInstance(null);
                    if ((instance == null || !instance.c() || !instance.p()) && !aA() && !c()) {
                        if ((this.aL == null || !this.aL.isShown()) && !this.mGuideShowing) {
                            this.e.handleBatteryClickPush();
                            return;
                        }
                        return;
                    }
                    return;
                case 4:
                    DataSpecialControl.getInstance().resetGimbal().start(20);
                    return;
                case 5:
                    this.aM.switchGimbalMode();
                    return;
                case 6:
                    DataRcSetGimbalControlMode.MODE mode;
                    if (DataRcGetGimbalControlMode.getInstance().getMode() == DataRcSetGimbalControlMode.MODE.a) {
                        mode = DataRcSetGimbalControlMode.MODE.c;
                    } else {
                        mode = DataRcSetGimbalControlMode.MODE.a;
                    }
                    DataRcSetGimbalControlMode.getInstance().a(mode).start(new 22(this, mode));
                    return;
                case 7:
                    if (this.aW.getVisibility() == 0) {
                        this.aW.go();
                    }
                    this.aT.go();
                    this.j.hideView();
                    this.h.hideView();
                    this.aK.setCanShow(aq());
                    d(false);
                    dji.pilot.groundStation.a.a instance2 = dji.pilot.groundStation.a.a.getInstance(null);
                    if (instance2 == null || !instance2.q()) {
                        this.r.f();
                        this.aM.showMenu();
                        this.aP.showChart();
                        return;
                    }
                    this.r.a(true);
                    this.aM.hideMenu(true);
                    this.aP.hideChart();
                    return;
                case 8:
                    this.aK.setCanShow(aq());
                    if (this.aL == null || !this.aL.isShown()) {
                        if (this.r.c()) {
                            this.aW.show();
                            h();
                            j();
                            k();
                            z();
                            this.aP.showChart();
                        }
                        A();
                        this.r.f();
                        this.aM.showMenu();
                        return;
                    }
                    return;
                case 9:
                    if (!aA() && !c()) {
                        if (this.r.h()) {
                            this.r.b();
                            return;
                        }
                        this.r.g();
                        this.r.b();
                        this.r.a(true);
                        return;
                    }
                    return;
                case 10:
                    if ((this.aL == null || !this.aL.isShown()) && !this.mGuideShowing) {
                        au();
                        return;
                    }
                    return;
                case 11:
                    boolean isAELock = DataCameraGetPushShotParams.getInstance().isAELock();
                    DataCameraSetAELock instance3 = DataCameraSetAELock.getInstance();
                    if (isAELock) {
                        z = false;
                    }
                    instance3.a(z).start(null);
                    return;
                case 12:
                    G();
                    return;
                case 13:
                    if (!aA() && !c() && dji.pilot.dji_groundstation.controller.d.getInstance().b() != dji.pilot.dji_groundstation.a.d.c.Tripod) {
                        dji.pilot.dji_groundstation.controller.e.a("gs://flightmode/main", this);
                        return;
                    }
                    return;
                case 14:
                    DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.S), new CameraLensFocusTargetPoint(0.5f, 0.5f), null);
                    return;
                default:
                    return;
            }
        }
    }

    private void f(boolean z) {
        this.ba.a(z, DataOsdGetPushCommon.getInstance().getVoltageWarning(), DataCenterGetPushBatteryCommon.getInstance().getRelativeCapacity());
    }

    private void au() {
        if (ServiceManager.getInstance().isConnected()) {
            this.aZ.sendEmptyMessage(af);
            this.cl.a(dji.midware.data.config.P3.b.a.SetMetering).a(0).start(new 24(this));
        }
    }

    private void av() {
        this.aZ.sendMessage(this.aZ.obtainMessage(32768, 1, 1));
        this.aZ.sendMessageDelayed(this.aZ.obtainMessage(32768, 0, 0), 2000);
    }

    public void onEventMainThread(dji.pilot.fpv.model.a.a aVar) {
        if (dji.pilot.fpv.control.b.a.c == aVar.a) {
            if ((this.aL == null || !this.aL.isShown()) && !this.mGuideShowing) {
                new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.SetMetering).a(0).start(null);
            }
        } else if (dji.pilot.fpv.control.b.a.a == aVar.a && this.w != 0) {
            a(aVar.b.x, aVar.b.y);
        }
    }

    private boolean a(float f, float f2) {
        DJIErrorPopView.b bVar;
        if (DataCameraGetPushShotParams.getInstance().getExposureMode() == ExposureMode.M) {
            bVar = new DJIErrorPopView.b();
            bVar.b = R.string.fpv_cant_metering_mmode;
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.g = DJIErrorPopView.f.INSERT;
            dji.thirdparty.a.c.a().e(bVar);
        } else if (DataCameraGetPushShotParams.getInstance().isAELock()) {
            bVar = new DJIErrorPopView.b();
            bVar.b = R.string.fpv_cant_metering_ae;
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.g = DJIErrorPopView.f.INSERT;
            dji.thirdparty.a.c.a().e(bVar);
        } else {
            int metering = DataCameraGetPushShotParams.getInstance().getMetering();
            DJILogHelper.getInstance().LOGD(this.TAG, "click 测光=" + metering);
            if (metering == 0) {
                this.cl.a(dji.midware.data.config.P3.b.a.SetMetering).a(2).start(new 25(this, f, f2));
                dji.pilot.fpv.d.e.c(s.dn);
            } else if (metering == 2) {
                b(f, f2);
            }
        }
        return false;
    }

    private void b(float f, float f2) {
        if (this.u != 0) {
            int i = ((int) ((f - ((float) this.y[0])) / ((float) this.u))) + (((int) ((f2 - ((float) this.z[0])) / ((float) this.t))) * this.w);
            this.aZ.removeMessages(32768);
            this.aZ.sendMessage(this.aZ.obtainMessage(20480, new PointF(f, f2)));
            DJILogHelper.getInstance().LOGD(this.TAG, "meteringarea index=" + i, false, false);
            DataCameraSetMeteringArea.getInstance().a(i).start(new 26(this));
        }
    }

    private void aw() {
        if (this.bw == null) {
            this.bw = new AlphaAnimation(0.4f, 1.0f);
            this.bw.setDuration(az);
            this.bw.setRepeatCount(3);
            this.bw.setRepeatMode(2);
            this.bw.setAnimationListener(new 27(this));
        }
        this.aV.show();
        this.aV.startAnimation(this.bw);
    }

    private void ax() {
        ay();
    }

    private void ay() {
        this.aZ.removeMessages(ah);
        this.bH.a();
    }

    public void C() {
        if (DJIFocusAreaView.canLongPressFocus(this)) {
            this.i.handleMotion4LongPress(this.co);
            return;
        }
        this.bH.a(this.co);
        dji.pilot.fpv.d.e.c(s.dp);
    }

    public Bitmap D() {
        if (this.b == null) {
            return this.a.getBitmap(Bitmap.createBitmap(this.a.getWidth() / 2, this.a.getHeight() / 2, Config.RGB_565));
        }
        return this.b.getBitmap();
    }

    public boolean E() {
        return this.e.getVisibility() == 0;
    }

    public void onEventMainThread(dji.pilot.flyforbid.a.b bVar) {
        switch (29.h[bVar.ordinal()]) {
            case 1:
                this.bd.a(null);
                return;
            case 2:
                this.bd.a(dji.pilot.flyforbid.a.getInstance(this).a());
                return;
            case 3:
                dji.pilot.flyforbid.a.getInstance(this).b();
                return;
            case 4:
                if (this.e.hasDlgShowing()) {
                    this.bN = true;
                    return;
                } else {
                    dji.pilot.flyforbid.a.getInstance(this).a((Context) this);
                    return;
                }
            case 5:
                NFZLogUtil.savedLOG("nfz log 6 b d limits" + dji.pilot.flyforbid.a.getInstance(this).c().b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + dji.pilot.flyforbid.a.getInstance(this).c().c);
                this.bd.b(dji.pilot.flyforbid.a.getInstance(this).c());
                NFZLogUtil.savedLOG("nfz log 6 a d limits");
                return;
            case 6:
                if (!this.e.hasDlgShowing()) {
                    dji.pilot.flyforbid.a.getInstance(this).b((Context) this);
                    return;
                }
                return;
            case 7:
                dji.pilot.flyforbid.a.getInstance(this).a((Context) this, -1, -1, "");
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(p pVar) {
        super.onEventMainThread(pVar);
        if (pVar == p.ConnectOK) {
            az();
        }
    }

    private void az() {
        if (dji.logic.c.b.getInstance().a(null)) {
            this.l.setVisibility(0);
            this.l.setAlpha(1.0f);
            this.r.a(true);
            return;
        }
        this.l.setVisibility(8);
        this.r.f();
    }

    public boolean F() {
        return this.e.getVisibility() == 0;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 2 && motionEvent.getAction() == 261) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.cp < 800) {
                try {
                    Class.forName("com.dji.tools.base.InnerToolsDialog").getMethod("showInnerTools", new Class[]{Context.class}).invoke(null, new Object[]{this});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.cp = currentTimeMillis;
        }
        if (aA() && b(motionEvent)) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean aA() {
        return this.cu != null && this.cu.getVisibility() == 0;
    }

    private void aB() {
        if (this.r.c()) {
            dji.pilot.visual.util.c.a("here to show Visual Tutorial");
            if (dji.pilot.visual.beginner.a.getInstance().i()) {
                if (this.cu == null) {
                    this.cu = (DJIVisualBeginnerView) View.inflate(this, R.layout.visual_beginner_view, null);
                    this.aA.addView(this.cu, -1, -1);
                }
                this.cu.show();
            }
        }
    }

    private void a(View view, MotionEvent motionEvent, Rect rect) {
        motionEvent.offsetLocation((float) (-rect.left), (float) (-rect.top));
        view.dispatchTouchEvent(motionEvent);
        motionEvent.offsetLocation((float) rect.left, (float) rect.top);
    }

    private boolean b(MotionEvent motionEvent) {
        this.cv.setEmpty();
        int action = motionEvent.getAction();
        if (action == 0) {
            View handleEventView = this.cu.getHandleEventView();
            if (handleEventView != null && handleEventView.getGlobalVisibleRect(this.cv) && this.cv.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                a(handleEventView, motionEvent, this.cv);
                this.cw = handleEventView;
            } else {
                int[] h = dji.pilot.visual.beginner.a.getInstance().h();
                if (h != null && h.length > 0) {
                    int length = h.length;
                    for (action = 0; action < length; action++) {
                        if (h[action] != 0) {
                            View findViewById = findViewById(h[action]);
                            if (findViewById != null && findViewById.getGlobalVisibleRect(this.cv) && this.cv.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                                a(findViewById, motionEvent, this.cv);
                                this.cw = findViewById;
                                break;
                            }
                        }
                    }
                }
            }
        } else if (this.cw != null) {
            this.cw.getGlobalVisibleRect(this.cv);
            a(this.cw, motionEvent, this.cv);
            if (action == 3 || action == 1) {
                this.cw = null;
            }
        }
        return true;
    }

    public void onEventMainThread(DataFlycGetPushWayPointMissionInfo dataFlycGetPushWayPointMissionInfo) {
        if (dataFlycGetPushWayPointMissionInfo.getMissionType() == 5 && DataOsdGetPushCommon.getInstance().getFlycState().equals(FLYC_STATE.TERRAIN_TRACKING)) {
            if (!dataFlycGetPushWayPointMissionInfo.isPositionValid() && !this.cx) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.b = R.string.gs_terrain_tracking_invalid_warning_title;
                bVar.d = R.string.gs_terrain_tracking_invalid_warning_desc;
                bVar.a = DJIErrorPopView.d.DANGEROUS;
                bVar.f = DJIErrorPopView.c.STATIC;
                dji.thirdparty.a.c.a().e(bVar);
                this.cx = true;
            } else if (dataFlycGetPushWayPointMissionInfo.isPositionValid()) {
                this.cx = false;
            }
            if (dataFlycGetPushWayPointMissionInfo.getCurrentHeight() - dataFlycGetPushWayPointMissionInfo.getLimitedHeight() > 1000 && !this.cy) {
                ViewGroup.LayoutParams layoutParams = new LayoutParams((int) getResources().getDimension(R.dimen.y0), (int) getResources().getDimension(R.dimen.y0));
                layoutParams.addRule(13);
                this.cA = (DJITerrainTrackingHint) View.inflate(this, R.layout.terrain_tracking_hint, null);
                this.cA.setImageViewResIndex(R.drawable.gs_terrain_tracking_down).setTextViewResIndex(R.string.gs_terrain_tracking_current_height_is_too_high);
                if (this.aA != null) {
                    this.aA.addView(this.cA, layoutParams);
                }
                this.cy = true;
                return;
            } else if (this.cy && dataFlycGetPushWayPointMissionInfo.getCurrentHeight() - dataFlycGetPushWayPointMissionInfo.getLimitedHeight() < 950) {
                this.cy = false;
                if (this.aA != null) {
                    this.aA.removeView(this.cA);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        this.cz = false;
        this.cy = false;
        this.cx = false;
        if (this.aA != null && this.cA != null) {
            this.aA.removeView(this.cA);
            this.cA = null;
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (!dataOsdGetPushCommon.getFlycState().equals(FLYC_STATE.TERRAIN_TRACKING)) {
            this.cz = false;
            this.cy = false;
            this.cx = false;
            if (this.aA != null && this.cA != null) {
                this.aA.removeView(this.cA);
                this.cA = null;
            }
        }
    }

    public void onEventMainThread(dji.pilot.fpv.model.n.a aVar) {
        if (aVar == dji.pilot.fpv.model.n.a.SHOW_PRECISE_LANDING) {
            new dji.pilot.fpv.view.a(this).show();
        }
    }
}
