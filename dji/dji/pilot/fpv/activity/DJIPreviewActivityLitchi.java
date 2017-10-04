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
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.facebook.login.widget.ToolTipPopup;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.forbid.NFZLogUtil;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetMeteringArea;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
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
import dji.midware.usbhost.P3.NativeRcController;
import dji.pilot.R;
import dji.pilot.fpv.b.a;
import dji.pilot.fpv.camera.focus.DJIFocusAreaView;
import dji.pilot.fpv.camera.focus.DJIFocusDistanceView;
import dji.pilot.fpv.camera.focus.DJIFocusRingView;
import dji.pilot.fpv.camera.focus.DJIMFDemarcateView;
import dji.pilot.fpv.camera.newfn.DJICameraFnView;
import dji.pilot.fpv.camera.newfn.DJICameraTauSceneView;
import dji.pilot.fpv.camera.ref.DJICameraPointRefView;
import dji.pilot.fpv.camera.setting.DJICameraSettingBaseView;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.b;
import dji.pilot.fpv.control.d;
import dji.pilot.fpv.control.e;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.control.m;
import dji.pilot.fpv.control.n;
import dji.pilot.fpv.control.q;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.leftmenu.DJILeftBar;
import dji.pilot.fpv.rightbar.DJIFMSettingView;
import dji.pilot.fpv.rightbar.DJISwitchModeView;
import dji.pilot.fpv.topbar.DJIFpvTopBaseView;
import dji.pilot.fpv.view.DJIAttitudeView;
import dji.pilot.fpv.view.DJICameraAnimView;
import dji.pilot.fpv.view.DJICameraChartView;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIFpvGimbalPitchView;
import dji.pilot.fpv.view.DJIGimbalRollFineTuneView;
import dji.pilot.fpv.view.DJIGridLine;
import dji.pilot.fpv.view.DJIPlayBackView;
import dji.pilot.joystick.DJIJoyStickView;
import dji.pilot.liveshare.LiveShareFpvTopView;
import dji.pilot.main.activity.DJIHubActivity;
import dji.pilot.playback.litchi.DJIPlayBackActivity;
import dji.pilot.publics.c.f;
import dji.pilot.publics.c.g;
import dji.pilot.publics.c.i;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.sdr.debug.DJISdrDebugView;
import dji.pilot.visual.beginner.DJIVisualBeginnerView;
import dji.pilot.visual.radar.DJIVisionRadarView;
import dji.pilot.visual.stage.DJIVisualPointSpeedView;
import dji.pilot2.multimoment.view.HorizonalSegmentView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.publics.widget.FpvPopWarnView;
import dji.setting.ui.flyc.SdModeView;
import dji.thirdparty.afinal.a.b.c;
import java.io.InputStream;
import java.util.Timer;

public class DJIPreviewActivityLitchi extends DJIPreviewActivityBaseForMC implements SurfaceTextureListener, OnClickListener, h, a, s {
    private static final int A = 36865;
    private static final int B = 36866;
    private static final int C = 36867;
    private static final int D = 36868;
    private static final int E = 36869;
    private static final int F = 36870;
    private static final int G = 0;
    private static final int H = 1;
    private static final int I = 2;
    private static final int J = 4;
    private static final int K = 8;
    private static final int L = 16;
    private static final int M = 32;
    private static final int N = 64;
    private static final int O = 127;
    private static final long Q = 200;
    private static final long R = 100;
    private static final boolean aS = false;
    private static boolean bO = false;
    private static final int bk = 8192;
    private static final int bl = 8193;
    private static final int bm = 8194;
    private static final int r = 4096;
    private static final int s = 8192;
    private static final int t = 12288;
    private static final int u = 16384;
    private static final int v = 20480;
    private static final int w = 24576;
    private static final int x = 28672;
    private static final int y = 32768;
    private static final int z = 36864;
    private volatile int P = 0;
    @c(a = 2131362846)
    private DJIRelativeLayout S;
    @c(a = 2131362848)
    private TextureView T;
    @c(a = 2131362849)
    private DJIImageView U;
    @c(a = 2131362858)
    private DJIFpvTopBaseView Y;
    @c(a = 2131362920)
    protected DJISdrDebugView a;
    @c(a = 2131363211)
    private DJIVisualPointSpeedView aA;
    private DJIGimbalRollFineTuneView aB = null;
    @c(a = 2131362902)
    private DJIImageView aC = null;
    @c(a = 2131362857)
    private DJIFpvGimbalPitchView aD;
    @c(a = 2131363083)
    private LiveShareFpvTopView aE;
    @c(a = 2131362910)
    private DJIVisionRadarView aF;
    private f aG = null;
    private a aH = null;
    private b aI;
    private dji.pilot.fpv.control.a aJ = null;
    private DJIPlayBackView.b aK = null;
    private DJIGenSettingDataManager.c aL = null;
    private DJIFpvTopBaseView.b aM = null;
    private k aN = new k(this);
    private Animation aO = null;
    private Animation aP = null;
    private int aQ = 0;
    private Timer aR;
    private q aT;
    private boolean aU = false;
    private boolean aV = false;
    private dji.pilot.publics.widget.b aW;
    private TRIPOD_STATUS aX = TRIPOD_STATUS.UNKNOWN;
    private long aY = 0;
    private FLIGHT_ACTION aZ = null;
    @c(a = 2131362864)
    private DJIAttitudeView aa;
    @c(a = 2131362856)
    private DJIRelativeLayout ab;
    @c(a = 2131362859)
    private ViewStub ac;
    @c(a = 2131362870)
    private DJITextView ad;
    @c(a = 2131362850)
    private DJIGridLine ae;
    @c(a = 2131362861)
    private DJIErrorPopView af;
    @c(a = 2131362862)
    private ViewStub ag;
    @c(a = 2131362847)
    private DJIRelativeLayout ah;
    @c(a = 2131362852)
    private DJIImageView ai;
    @c(a = 2131362853)
    private DJIImageView aj;
    @c(a = 2131362905)
    private DJITextView ak;
    @c(a = 2131362625)
    private DJISwitchModeView al;
    @c(a = 2131367297)
    private DJIRelativeLayout am;
    @c(a = 2131363208)
    private DJILinearLayout an;
    @c(a = 2131362865)
    private DJIFMSettingView ao;
    private DJIPlayBackView ap = null;
    @c(a = 2131362869)
    private DJILeftBar aq;
    @c(a = 2131362867)
    private DJICameraSettingBaseView ar;
    @c(a = 2131362868)
    private DJICameraFnView as;
    @c(a = 2131362877)
    private DJICameraChartView at;
    @c(a = 2131362866)
    private DJIFocusRingView au;
    @c(a = 2131362854)
    private DJIFocusAreaView av;
    @c(a = 2131362151)
    private DJIFocusDistanceView aw;
    @c(a = 2131362915)
    private DJIMFDemarcateView ax;
    private DJICameraTauSceneView ay = null;
    @c(a = 2131362904)
    private DJICameraPointRefView az;
    @c(a = 2131362921)
    protected DJIImageView b;
    private GestureDetector bA;
    private boolean bB = false;
    private dji.pilot.fpv.leftmenu.b bC = null;
    private dji.pilot.fpv.leftmenu.b bD;
    private dji.pilot.publics.widget.b bE;
    private boolean bF = false;
    private Runnable bG = new 18(this);
    private Runnable bH = new 19(this);
    private dji.pilot.fpv.leftmenu.b bI;
    private int bJ = 5;
    private boolean bK = false;
    private DataDm368SetParams bL = new DataDm368SetParams();
    private int bM;
    private dji.pilot.fpv.leftmenu.b bN;
    private MODE bP;
    private RatioType bQ = RatioType.R_4_3;
    private RatioType bR = RatioType.R_4_3;
    private RatioType bS = RatioType.OTHER;
    private volatile int bT = -1;
    private volatile ExposureMode bU = ExposureMode.OTHER;
    private volatile int bV = -1;
    private int bW = 0;
    private n bX = null;
    private int bY = 0;
    private dji.pilot.publics.widget.b bZ;
    private MotorStartFailedCause ba = MotorStartFailedCause.None;
    private boolean bb = false;
    private FLYC_STATE bc = FLYC_STATE.OTHER;
    private boolean bd = false;
    private boolean be = false;
    private int bf = -1;
    private boolean bg = false;
    private Animation bh = null;
    private dji.pilot.publics.widget.f bi = null;
    private FLYC_STATE bj = FLYC_STATE.OTHER;
    private dji.pilot.fpv.leftmenu.b bn = null;
    private int bo = 8192;
    private d bp = null;
    private int[] bq = new int[]{0, 0};
    private int[] br = new int[]{0, 0};
    private int[] bs = new int[]{0, 0};
    private int[] bt = new int[]{0, 0};
    private m bu;
    private g bv;
    private dji.midware.media.h.b.b bw = null;
    private RecordReceiver bx = null;
    private dji.midware.e.d by = new 1(this);
    private Runnable bz = new 23(this);
    @c(a = 2131362908)
    protected DJIJoyStickView c;
    private SdModeView ca;
    private DataBaseCameraSetting cb = new DataBaseCameraSetting();
    private boolean cc = false;
    private OnGestureListener cd = new 31(this);
    private MotionEvent ce = null;
    private long cf = 0;
    private long cg = 0;
    private long ch = 0;
    private f ci = null;
    private i cj = null;
    private DJIVisualBeginnerView ck = null;
    private final Rect cl = new Rect();
    private View cm = null;
    protected int d;
    protected int e;
    protected int f = 0;
    protected int g = 12;
    protected int h = 8;
    protected dji.pilot.fpv.b.b i = null;
    protected i j = null;
    protected int k = 0;
    protected int l = 0;
    protected int m = 0;
    protected int n = 0;
    protected Boolean o = Boolean.valueOf(false);
    int p = -1;
    public boolean q = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(this);
        instance.a(this.aN);
        instance.a(this.aT);
        dji.pilot.dji_groundstation.controller.a.getInstance(this);
        setContentView(R.layout.fpv_litchi);
        if (this.o.booleanValue()) {
            p();
            d.i();
            this.aF.hideByOuter();
        }
        getWindow().addFlags(128);
        this.S = (DJIRelativeLayout) findViewById(R.id.a1u);
        this.aN.a(bundle, this.S);
        dji.gs.utils.a.a = DJIGenSettingDataManager.getInstance().s();
        this.bu = new m(this.S);
        u();
        v();
        ac();
        s();
        this.f = getResources().getDimensionPixelSize(R.dimen.pv);
        this.e = screenWidth / this.g;
        this.d = screenHeight / this.h;
        if (!getIntent().getBooleanExtra(dji.pilot.c.b.a, true)) {
            this.aT.b();
        }
        c(true);
        this.bv = new g(this);
        dji.midware.data.manager.P3.g.getInstance().a((int) HorizonalSegmentView.N);
        dji.pilot.c.a.n = true;
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        }
        this.bX = new n(this);
        this.j = new i(this);
        this.j.b();
        q();
        this.b.setOnClickListener(this);
        dji.pilot.flyunlimit.b.getInstance(this);
        showG04RecommendDialog();
    }

    private void p() {
        this.S = (DJIRelativeLayout) findViewById(R.id.a1u);
        this.T = (TextureView) findViewById(R.id.a1w);
        this.U = (DJIImageView) findViewById(R.id.a1x);
        this.Y = (DJIFpvTopBaseView) findViewById(R.id.a26);
        this.aa = (DJIAttitudeView) findViewById(R.id.a2b);
        this.ab = (DJIRelativeLayout) findViewById(R.id.a24);
        this.ac = (ViewStub) findViewById(R.id.a27);
        this.ad = (DJITextView) findViewById(R.id.a2h);
        this.ae = (DJIGridLine) findViewById(R.id.a1y);
        this.af = (DJIErrorPopView) findViewById(R.id.a29);
        this.ag = (ViewStub) findViewById(R.id.a2_);
        this.ah = (DJIRelativeLayout) findViewById(R.id.a1v);
        this.ai = (DJIImageView) findViewById(R.id.a20);
        this.aj = (DJIImageView) findViewById(R.id.a21);
        this.ak = (DJITextView) findViewById(R.id.a3f);
        this.aq = (DJILeftBar) findViewById(R.id.a2g);
        this.ar = (DJICameraSettingBaseView) findViewById(R.id.a2e);
        this.as = (DJICameraFnView) findViewById(R.id.a2f);
        this.at = (DJICameraChartView) findViewById(R.id.a2o);
        this.aD = (DJIFpvGimbalPitchView) findViewById(R.id.a25);
        this.aE = (LiveShareFpvTopView) findViewById(R.id.a89);
        this.al = (DJISwitchModeView) findViewById(R.id.vv);
        this.aA = (DJIVisualPointSpeedView) findViewById(R.id.aap);
        this.an = (DJILinearLayout) findViewById(R.id.aam);
        this.ao = (DJIFMSettingView) findViewById(R.id.a2c);
        this.aF = (DJIVisionRadarView) findViewById(R.id.a3k);
        this.az = (DJICameraPointRefView) findViewById(R.id.a3e);
    }

    private void q() {
        this.bx = new RecordReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.record");
        registerReceiver(this.bx, intentFilter);
    }

    private void r() {
        if (!dji.pilot.fpv.model.b.e(this) && !ServiceManager.getInstance().isConnected()) {
            Toast.makeText(this, getString(R.string.str_usd_unplugged), 1).show();
        }
    }

    public boolean a() {
        return (this.Y != null && this.Y.hasDlgShowing()) || (!(this.aT == null || this.aT.c()) || (this.ap != null && this.ap.isShown()));
    }

    private void c(boolean z) {
        if (!ServiceManager.getInstance().isRemoteOK()) {
            this.Y.hideCheckListDlg();
            if (!z) {
            }
        } else if (d.h() && !this.mGuideShowing && !DataOsdGetPushCommon.getInstance().isMotorUp()) {
            if (this.ap == null || !this.ap.isShown()) {
                this.Y.showCheckListDlg();
                d.i();
            }
        }
    }

    private void s() {
        if (ServiceManager.getInstance().isConnected()) {
            int metering = DataCameraGetPushShotParams.getInstance().getMetering();
            DJILogHelper.getInstance().LOGD(this.TAG, "测光=" + metering);
            if (metering == 2) {
                ak();
            } else if (metering == 0) {
                this.aH.sendEmptyMessage(x);
                al();
            }
        }
    }

    private void t() {
        this.ad.show();
        this.aR = new Timer();
        this.aR.schedule(new 12(this), 0, 1000);
    }

    private void u() {
        this.aY = System.currentTimeMillis();
        this.aJ = new dji.pilot.fpv.control.a(this, this.S);
        this.aJ.a();
        this.aM = new 33(this);
        this.aL = new 34(this);
        this.aH = new a(this);
        this.aO = AnimationUtils.loadAnimation(this, R.anim.bt);
        this.aP = AnimationUtils.loadAnimation(this, R.anim.bu);
        DJIGenSettingDataManager.getInstance().a(this.aL);
        this.bp = new d(this);
        this.i = new e(this, screenHeight);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void v() {
        if (!this.o.booleanValue()) {
            this.T.setSurfaceTextureListener(this);
        }
        int[] rules = ((LayoutParams) findViewById(R.id.a2i).getLayoutParams()).getRules();
        if (rules != null && rules.length >= 11) {
            this.bg = rules[11] == -1;
        }
        this.aa.dispatchOnCreate();
        this.aa.setGsOnRight(this.bg);
        this.Y.dispatchOnCreate();
        this.Y.setOnEventListener(this.aM);
        this.af.dispatchOnCreate();
        this.aD.dispatchOnCreate();
        this.au.dispatchOnCreate();
        this.aw.dispatchOnCreate();
        this.aq.dispatchOnCreate();
        this.aq.setMutexView(this.af);
        this.aI = new b(this.ab, (DJICameraAnimView) findViewById(R.id.a1z), new 35(this));
        this.aI.a(dji.pilot.fpv.camera.more.a.getInstance());
        S();
        U();
        T();
        this.aT = new q(this, this.S, this.bg);
        this.aT.a(this.aN);
        this.aT.a(new 36(this));
        this.bA = new GestureDetector(this, this.cd);
        this.bA.setIsLongpressEnabled(false);
        this.ah.setOnTouchListener(new 37(this));
        this.ar.setOnVisibilityChangeListener(new 38(this));
        this.as.setOnVisibilityChangeListener(new 2(this));
        this.at.dispatchOnCreate();
        this.aj.setOnClickListener(this);
        this.c.setOnJoystickVisibilityChangedListenner(new 3(this));
        this.b.go();
        onEventMainThread(dji.pilot.fpv.flightmode.c.getInstance().a());
    }

    @Deprecated
    public void onEventMainThread(dji.pilot.fpv.flightmode.c.b bVar) {
        if (bVar == dji.pilot.fpv.flightmode.c.b.g) {
            this.c.show();
            this.c.showJoyStick();
            f(true);
            this.au.hideView();
            this.av.hideView();
            this.ax.hideView();
            this.aT.e();
            this.a.hide();
            return;
        }
        this.c.hide();
        this.c.hideJoyStick();
        if (this.aT.c()) {
            b();
            x();
            y();
            af();
        }
        this.aT.d();
        this.a.show();
    }

    public void onEventMainThread(dji.pilot.fpv.model.n.a aVar) {
        super.onEventMainThread(aVar);
        if (aVar == dji.pilot.fpv.model.n.a.LEFTMENU_JS_CLICK_START) {
            this.c.show();
            this.c.showJoyStick();
            f(true);
            this.au.hideView();
            this.av.hideView();
            this.ax.hideView();
            this.aT.e();
            this.a.hide();
        } else if (aVar == dji.pilot.fpv.model.n.a.LEFTMENU_JS_CLICK_STOP) {
            this.c.hide();
            this.c.hideJoyStick();
            if (this.aT.c()) {
                b();
                x();
                y();
                af();
            }
            this.aT.d();
            this.a.show();
        }
    }

    public void b() {
        if (DataCameraGetPushStateInfo.getInstance().isGetted() && dji.pilot.fpv.d.b.e(DataCameraGetPushStateInfo.getInstance().getCameraType()) && dji.pilot.fpv.camera.more.a.getInstance().f() && this.as.getVisibility() != 0 && this.ar.getVisibility() != 0 && ae() && dji.pilot.fpv.flightmode.c.getInstance().a() != dji.pilot.fpv.flightmode.c.b.g) {
            onEventMainThread(DJIMFDemarcateView.a.a);
        }
    }

    public void onEventMainThread(dji.pilot.fpv.camera.more.a.a aVar) {
        if (aVar == dji.pilot.fpv.camera.more.a.a.d) {
            onEventMainThread(DJIMFDemarcateView.a.a);
        } else if (aVar == dji.pilot.fpv.camera.more.a.a.e) {
            T();
        }
    }

    private void w() {
        float f;
        float f2 = 0.0f;
        this.av.hideView();
        int i;
        int i2;
        if (DJIOriLayout.getDeviceType() != DJIDeviceType.Phone) {
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
                if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD || DataCameraGetPushShotParams.getInstance().getImageRatio() == RatioType.R_16_9) {
                    i = (int) (((((double) screenWidth) / 16.0d) * 9.0d) / 16.0d);
                    i2 = (int) (((double) screenWidth) / 16.0d);
                    f = (((float) (i2 * this.m)) / 2.0f) + ((float) (this.k * i2));
                    f2 = (((float) (i * this.n)) / 2.0f) + ((float) (((screenHeight / 2) - ((screenWidth * 9) / 32)) + (this.l * i)));
                } else if (DataCameraGetPushShotParams.getInstance().getImageRatio() == RatioType.R_4_3) {
                    i = (int) (((double) screenHeight) / 16.0d);
                    i2 = (int) (((double) screenWidth) / 16.0d);
                    f = (((float) (i2 * this.m)) / 2.0f) + ((float) (this.k * i2));
                    f2 = (((float) (i * this.n)) / 2.0f) + ((float) (this.l * i));
                }
            }
            f = 0.0f;
        } else if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD || DataCameraGetPushShotParams.getInstance().getImageRatio() == RatioType.R_16_9) {
            i = (int) (((double) screenHeight) / 16.0d);
            i2 = (int) (((double) screenWidth) / 16.0d);
            f = (((float) (i2 * this.m)) / 2.0f) + ((float) (this.k * i2));
            f2 = (((float) (i * this.n)) / 2.0f) + ((float) (this.l * i));
        } else {
            if (DataCameraGetPushShotParams.getInstance().getImageRatio() == RatioType.R_4_3) {
                i = (int) (((double) screenHeight) / 16.0d);
                i2 = (int) (((((double) screenHeight) * dji.pilot2.multimoment.videolib.c.c) / 3.0d) / 16.0d);
                f = (((float) (i2 * this.m)) / 2.0f) + ((float) (((screenWidth / 2) - ((screenHeight * 2) / 3)) + (this.k * i2)));
                f2 = (((float) (i * this.n)) / 2.0f) + ((float) (this.l * i));
            }
            f = 0.0f;
        }
        DJILogHelper.getInstance().LOGD("", "对焦位置：" + this.k + ", " + this.l + " 宽度：" + this.m + ", " + this.n, false, true);
        this.av.setPosition(f, f2);
        this.av.showView();
    }

    public void onEventMainThread(DJIMFDemarcateView.a aVar) {
        if (aVar == DJIMFDemarcateView.a.c) {
            x();
            af();
            y();
        } else if (aVar == DJIMFDemarcateView.a.b) {
            f(true);
            if (this.ay != null) {
                this.ay.go();
            }
            this.as.hideView();
            this.ar.hideView();
            this.aw.hideView();
            this.au.hideView();
            this.al.switchMode(DJISwitchModeView.a.b);
        } else if (aVar != DJIMFDemarcateView.a.a || !DataCameraGetPushStateInfo.getInstance().isGetted() || !dji.pilot.fpv.d.b.e(DataCameraGetPushStateInfo.getInstance().getCameraType()) || !this.aT.c()) {
        } else {
            if (this.ap == null || (this.ap.getVisibility() != 0 && ae())) {
                this.ax.showView();
                this.as.hideView();
                this.ar.hideView();
                this.aw.hideView();
                this.au.hideView();
                f(true);
            }
        }
    }

    private void x() {
        if (!DataCameraGetPushStateInfo.getInstance().isGetted() || !dji.pilot.fpv.d.b.c(DataCameraGetPushStateInfo.getInstance().getCameraType()) || !this.aT.c()) {
            return;
        }
        if ((this.ap == null || this.ap.getVisibility() != 0) && this.as.getVisibility() != 0 && this.ar.getVisibility() != 0 && this.ax.getVisibility() != 0 && ae() && dji.pilot.fpv.flightmode.c.getInstance().a() != dji.pilot.fpv.flightmode.c.b.g) {
            this.au.showView();
        }
    }

    public void onEventMainThread(DJIFocusRingView.a aVar) {
        if (aVar == DJIFocusRingView.a.b) {
            if (this.ay != null) {
                this.ay.go();
            }
            this.as.hideView();
            this.ar.hideView();
            a(DJISwitchModeView.a);
        } else if (aVar == DJIFocusRingView.a.a) {
            x();
        }
    }

    private void y() {
        if (!DataCameraGetPushStateInfo.getInstance().isGetted() || !dji.pilot.fpv.d.b.a(DataCameraGetPushStateInfo.getInstance().getCameraType(), DataCameraGetPushShotInfo.getInstance().getZoomFocusType()) || !this.aT.c()) {
            return;
        }
        if ((this.ap == null || this.ap.getVisibility() != 0) && ae() && this.ax.getVisibility() != 0 && dji.pilot.fpv.flightmode.c.getInstance().a() != dji.pilot.fpv.flightmode.c.b.g) {
            this.aw.showView();
        }
    }

    public void onEventMainThread(DJIFocusDistanceView.a aVar) {
        if (aVar == DJIFocusDistanceView.a.a) {
            y();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a21:
                z();
                return;
            case R.id.a3v:
                if (dji.logic.c.b.getInstance().a(dji.midware.data.manager.P3.i.getInstance().c())) {
                    o();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void z() {
        this.aH.sendMessage(this.aH.obtainMessage(32768, 0, 0));
        ak();
    }

    public void onClickBackground(View view) {
    }

    private void A() {
        TRIPOD_STATUS deformStatus = DataFlycGetPushDeformStatus.getInstance().getDeformStatus();
        if (deformStatus != TRIPOD_STATUS.UNKNOWN && this.aX != deformStatus && dji.pilot.fpv.d.b.g(null)) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.aX != TRIPOD_STATUS.UNKNOWN && currentTimeMillis - this.aY >= 8000) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.b = dji.pilot.fpv.d.b.a(deformStatus);
                bVar.a = DJIErrorPopView.d.NOTIFY;
                dji.thirdparty.a.c.a().e(bVar);
            }
            this.aX = deformStatus;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void B() {
        /*
        r8 = this;
        r7 = 1;
        r6 = 8;
        r5 = 0;
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.isSwaveWork();
        r1 = r8.aV;
        if (r1 == r0) goto L_0x0014;
    L_0x0010:
        r8.aV = r0;
        if (r0 == 0) goto L_0x0014;
    L_0x0014:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.getFlightAction();
        r1 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r1 = r1.getFlycState();
        r2 = r8.bc;
        if (r2 == r1) goto L_0x0056;
    L_0x0028:
        r8.bc = r1;
        if (r0 == 0) goto L_0x0052;
    L_0x002c:
        r2 = dji.pilot.fpv.d.b.b(r1);
        if (r2 == 0) goto L_0x0052;
    L_0x0032:
        r2 = dji.pilot.fpv.d.b.a(r0);
        r3 = r2[r5];
        if (r3 == 0) goto L_0x0052;
    L_0x003a:
        r3 = new dji.pilot.fpv.view.DJIErrorPopView$b;
        r3.<init>();
        r4 = r2[r5];
        r3.b = r4;
        r2 = r2[r7];
        if (r2 != 0) goto L_0x018a;
    L_0x0047:
        r2 = dji.pilot.fpv.view.DJIErrorPopView.d.NOTIFY;
        r3.a = r2;
    L_0x004b:
        r2 = dji.thirdparty.a.c.a();
        r2.e(r3);
    L_0x0052:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE.GoHome;
        if (r1 != r2) goto L_0x0056;
    L_0x0056:
        r1 = r8.aZ;
        if (r1 == r0) goto L_0x0080;
    L_0x005a:
        if (r0 == 0) goto L_0x0080;
    L_0x005c:
        r8.aZ = r0;
        r0 = r8.aZ;
        r1 = dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION.AIRPORT_AVOID_LANDING;
        if (r0 != r1) goto L_0x0080;
    L_0x0064:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.groundOrSky();
        r1 = 2;
        if (r0 != r1) goto L_0x0080;
    L_0x006f:
        r0 = new dji.pilot.fpv.view.DJIErrorPopView$b;
        r0.<init>();
        r1 = 2131298317; // 0x7f09080d float:1.8214604E38 double:1.0530012795E-314;
        r0.b = r1;
        r1 = dji.thirdparty.a.c.a();
        r1.e(r0);
    L_0x0080:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.getMotorFailedCause();
        r1 = r8.ba;
        if (r1 == r0) goto L_0x017f;
    L_0x008c:
        r8.ba = r0;
        r1 = dji.pilot.publics.control.a.getInstance();
        r1 = r1.l();
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.DeviceLocked;
        if (r0 != r2) goto L_0x009c;
    L_0x009a:
        if (r1 != 0) goto L_0x00b4;
    L_0x009c:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.IMUNeedCalibration;
        if (r0 == r2) goto L_0x00b4;
    L_0x00a0:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.IMUWarning;
        if (r0 == r2) goto L_0x00b4;
    L_0x00a4:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.CompassBig;
        if (r0 == r2) goto L_0x00b4;
    L_0x00a8:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.CompassError;
        if (r0 == r2) goto L_0x00b4;
    L_0x00ac:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.CompassFailed;
        if (r0 == r2) goto L_0x00b4;
    L_0x00b0:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.CompassNoiseBig;
        if (r0 != r2) goto L_0x00d5;
    L_0x00b4:
        r0 = r8.Y;
        if (r0 == 0) goto L_0x00d5;
    L_0x00b8:
        r0 = r8.Y;
        r0 = r0.hasDlgShowing();
        if (r0 != 0) goto L_0x00d5;
    L_0x00c0:
        r0 = r8.isVisible;
        if (r0 == 0) goto L_0x00d5;
    L_0x00c4:
        r0 = r8.ap;
        if (r0 == 0) goto L_0x00d0;
    L_0x00c8:
        r0 = r8.ap;
        r0 = r0.isShown();
        if (r0 != 0) goto L_0x00d5;
    L_0x00d0:
        r0 = r8.Y;
        r0.showCheckListDlg();
    L_0x00d5:
        r0 = dji.pilot.publics.control.a.getInstance();
        r0 = r0.j;
        if (r0 == 0) goto L_0x017f;
    L_0x00dd:
        r0 = r8.ba;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.DeviceLocked;
        if (r0 != r2) goto L_0x0106;
    L_0x00e3:
        if (r1 != 0) goto L_0x0106;
    L_0x00e5:
        r0 = dji.midware.data.manager.P3.i.getInstance();
        r0 = r0.c();
        r2 = dji.midware.data.config.P3.ProductType.Tomato;
        if (r0 == r2) goto L_0x00fd;
    L_0x00f1:
        r0 = dji.midware.data.manager.P3.i.getInstance();
        r0 = r0.c();
        r2 = dji.midware.data.config.P3.ProductType.Pomato;
        if (r0 != r2) goto L_0x0190;
    L_0x00fd:
        r0 = dji.thirdparty.a.c.a();
        r2 = dji.pilot.publics.control.a.e.NO;
        r0.e(r2);
    L_0x0106:
        r0 = r8.ba;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.DeviceLocked;
        if (r0 == r2) goto L_0x0112;
    L_0x010c:
        r0 = r8.ba;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.NoviceProtected;
        if (r0 != r2) goto L_0x017f;
    L_0x0112:
        if (r1 == 0) goto L_0x017f;
    L_0x0114:
        r0 = r8.bD;
        if (r0 != 0) goto L_0x017a;
    L_0x0118:
        r0 = new dji.pilot.fpv.leftmenu.b;
        r0.<init>(r8);
        r8.bD = r0;
        r0 = r8.bD;
        r0.a(r7);
        r0 = r8.bD;
        r1 = new dji.pilot.fpv.activity.DJIPreviewActivityLitchi$5;
        r1.<init>(r8);
        r0.a(r1);
        r0 = r8.bD;
        r0.d(r6);
        r0 = r8.bD;
        r0 = r0.a(r6, r5);
        r0.e(r6);
        r0 = r8.bD;
        r1 = "";
        r0.a(r6, r1);
        r0 = r8.bD;
        r1 = 2131296822; // 0x7f090236 float:1.8211572E38 double:1.053000541E-314;
        r1 = r8.getString(r1);
        r0.a(r1);
        r0 = dji.midware.data.manager.P3.i.getInstance();
        r0 = r0.c();
        r0 = r0.isFromWifi();
        if (r0 == 0) goto L_0x01d1;
    L_0x015d:
        r0 = r8.bD;
        r1 = 2131296821; // 0x7f090235 float:1.821157E38 double:1.0530005403E-314;
        r1 = r8.getString(r1);
        r0.b(r1);
    L_0x0169:
        r0 = r8.bD;
        r1 = r8.getResources();
        r2 = 2131427846; // 0x7f0b0206 float:1.847732E38 double:1.0530652753E-314;
        r1 = r1.getDimension(r2);
        r1 = (int) r1;
        r0.c(r1);
    L_0x017a:
        r0 = r8.bD;
        r0.show();
    L_0x017f:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.getFlycVersion();
        r8.bf = r0;
        return;
    L_0x018a:
        r2 = dji.pilot.fpv.view.DJIErrorPopView.d.WARNING;
        r3.a = r2;
        goto L_0x004b;
    L_0x0190:
        r0 = r8.bE;
        if (r0 != 0) goto L_0x01c2;
    L_0x0194:
        r0 = new dji.pilot.publics.widget.b;
        r0.<init>(r8, r5);
        r8.bE = r0;
        r0 = r8.bE;
        r0.f();
        r0 = r8.bE;
        r2 = 2131296512; // 0x7f090100 float:1.8210943E38 double:1.0530003877E-314;
        r0.a(r2);
        r0 = r8.bE;
        r2 = 2131300423; // 0x7f091047 float:1.8218875E38 double:1.05300232E-314;
        r0.b(r2);
        r0 = r8.bE;
        r2 = 2131296493; // 0x7f0900ed float:1.8210904E38 double:1.0530003783E-314;
        r0.d(r2);
        r0 = r8.bE;
        r2 = new dji.pilot.fpv.activity.DJIPreviewActivityLitchi$4;
        r2.<init>(r8);
        r0.a(r2);
    L_0x01c2:
        r0 = r8.bE;
        r0 = r0.isShowing();
        if (r0 != 0) goto L_0x0106;
    L_0x01ca:
        r0 = r8.bE;
        r0.show();
        goto L_0x0106;
    L_0x01d1:
        r0 = dji.midware.data.manager.P3.i.getInstance();
        r0 = r0.c();
        r1 = dji.midware.data.config.P3.ProductType.Tomato;
        if (r0 != r1) goto L_0x01ea;
    L_0x01dd:
        r0 = r8.bD;
        r1 = 2131300658; // 0x7f091132 float:1.8219352E38 double:1.053002436E-314;
        r1 = r8.getString(r1);
        r0.b(r1);
        goto L_0x0169;
    L_0x01ea:
        r0 = r8.bD;
        r1 = 2131296820; // 0x7f090234 float:1.8211567E38 double:1.05300054E-314;
        r1 = r8.getString(r1);
        r0.b(r1);
        goto L_0x0169;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.fpv.activity.DJIPreviewActivityLitchi.B():void");
    }

    private void C() {
        if (this.bC == null) {
            this.bC = new dji.pilot.fpv.leftmenu.b(this);
            this.bC.a(1);
            this.bC.a(getString(R.string.fpv_cancel_gohome_title));
            this.bC.b(R.drawable.leftmenu_dlg_gohome);
            this.bC.b(getString(R.string.fpv_cancel_gohome_desc));
            this.bC.a(8, 0);
            this.bC.e(8);
            this.bC.d(8);
            this.bC.c(getString(R.string.fpv_before_cancel_gohome));
            this.bC.a(new 6(this));
            this.bC.o();
        }
        if (!this.bC.isShowing()) {
            this.bC.show();
            E();
        }
    }

    private void E() {
        if (this.bC != null && this.bC.isShowing()) {
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
            this.bC.a(0, getString(R.string.fpv_cancel_gohome_littletitle, new Object[]{Integer.valueOf(DataFlycGetPushSmartBattery.getInstance().getBattery()), Float.valueOf((((float) i2) * 1.0f) / 1000.0f)}));
        }
    }

    private void F() {
        if (this.bC != null && this.bC.isShowing()) {
            this.bC.dismiss();
            this.bC = null;
        }
    }

    private void H() {
        SmartGoHomeStatus goHomeStatus = DataFlycGetPushSmartBattery.getInstance().getGoHomeStatus();
        if (goHomeStatus == SmartGoHomeStatus.NON_GOHOME && DataOsdGetPushCommon.getInstance().isMotorUp()) {
            if (this.bn == null || !this.bn.isShowing()) {
                if (DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown() > 0) {
                    b(8193);
                }
            } else if (this.bn.isShowing() && 8193 == this.bo) {
                a(false, DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown());
            }
        } else if ((goHomeStatus == SmartGoHomeStatus.GOHOME_ALREADY || goHomeStatus == SmartGoHomeStatus.GOHOME) && 8193 == this.bo) {
            M();
        }
        if (!bO) {
            int status = DataFlycGetPushSmartBattery.getInstance().getStatus();
            if (status != this.bM) {
                this.bM = status;
                if ((this.bM & 2048) == 2048) {
                    if (this.bN == null) {
                        this.bN = new dji.pilot.fpv.leftmenu.b(this);
                        this.bN.a(1);
                        this.bN.a(new 7(this));
                        this.bN.d(8);
                        this.bN.a(8, 0).e(8);
                        this.bN.a(8, "");
                        this.bN.a(false);
                        this.bN.b(getString(R.string.battery_first_charge_not_full));
                    }
                    this.bN.show();
                    bO = true;
                }
            }
        }
    }

    private void I() {
        boolean isBeginnerMode = DataOsdGetPushHome.getInstance().isBeginnerMode();
        if (this.bb != isBeginnerMode) {
            this.bb = isBeginnerMode;
            if (!this.bb) {
                dji.pilot.fpv.d.b.a(2, true);
            } else if (dji.pilot.fpv.d.b.a(2)) {
                dji.pilot.fpv.d.b.a(2, false);
                this.Y.hideDialog();
                b(8194);
            }
        }
        isBeginnerMode = DataOsdGetPushHome.getInstance().isReatchLimitHeight();
        boolean isReatchLimitDistance = DataOsdGetPushHome.getInstance().isReatchLimitDistance();
        if (this.bf >= 3) {
            DJIErrorPopView.b bVar;
            if (this.bd != isBeginnerMode) {
                this.bd = isBeginnerMode;
                if (this.bd) {
                    bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.fpv_errorpop_altitude_limit;
                    dji.thirdparty.a.c.a().e(bVar);
                    dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.y);
                }
            }
            if (this.be != isReatchLimitDistance) {
                this.be = isReatchLimitDistance;
                if (this.be) {
                    bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.fpv_errorpop_distance_limit;
                    dji.thirdparty.a.c.a().e(bVar);
                    dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.x);
                }
            }
        }
    }

    private void J() {
        int i = R.string.fpv_height_imperial;
        if (this.bi != null && this.bi.isShowing() && this.bj != FLYC_STATE.GoHome) {
            float height;
            float b;
            if (this.bj == FLYC_STATE.AutoLanding) {
                height = (float) ((int) (((float) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1f));
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    b = (float) ((int) DJIGenSettingDataManager.getInstance().b(height));
                } else {
                    i = R.string.fpv_height_metric;
                    b = height;
                }
                this.bi.a(getString(i, new Object[]{Float.valueOf(b)}));
            } else if (this.bj == FLYC_STATE.AutoTakeoff) {
                height = (float) ((int) (((float) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1f));
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    b = (float) ((int) DJIGenSettingDataManager.getInstance().b(height));
                } else {
                    i = R.string.fpv_height_metric;
                    b = height;
                }
                this.bi.a(getString(i, new Object[]{Float.valueOf(b)}));
            }
        }
    }

    private void K() {
        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.DropGohome).start(new 8(this));
    }

    private void b(int i) {
        if (this.bn == null) {
            this.bn = new dji.pilot.fpv.leftmenu.b(this);
            this.bn.a(new 9(this));
            this.bn.setOnDismissListener(new 10(this));
        }
        if (!dji.pilot.publics.control.a.getInstance().l() && this.bn != null && !this.bn.isShowing()) {
            this.bo = i;
            if (i == 8193) {
                a(true, DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown());
            } else if (i == 8194) {
                L();
            }
            this.bn.show();
        }
    }

    private void a(boolean z, int i) {
        this.bn.a(getString(R.string.fpv_before_gohome_title, new Object[]{Integer.valueOf(i)}));
        if (z) {
            this.bn.a(1);
            this.bn.b(R.drawable.leftmenu_dlg_gohome);
            this.bn.a(8, 0);
            this.bn.e(8);
            this.bn.d(0);
            this.bn.b(getString(R.string.fpv_before_gohome_above_desc));
            this.bn.c(getString(R.string.app_cancel));
        }
    }

    private void L() {
        this.bn.a(4);
        this.bn.a(8, 0);
        this.bn.e(8);
        this.bn.d(8);
        this.bn.a(getString(R.string.fpv_novice_takeoff_title));
        this.bn.b(getString(R.string.fpv_novice_takeoff_desc));
        this.bn.c(getString(R.string.app_isee));
    }

    private void c(int i) {
        if (this.bo == 8193) {
            DataFlycSmartAck.getInstance().setAck((byte) 1).start(null);
        }
    }

    private void d(int i) {
        if (this.bo == 8193) {
            DataFlycSmartAck.getInstance().setAck((byte) 2).start(null);
        } else if (this.bo == 8194) {
            M();
        }
    }

    private void M() {
        if (this.bn != null && this.bn.isShowing()) {
            this.bn.dismiss();
        }
    }

    private void N() {
        dji.pilot.publics.widget.b.a(this, R.string.app_tip, R.string.fpv_adb_debug_tip, R.string.app_cancel, new 11(this), R.string.app_setting, new 13(this)).show();
    }

    private void O() {
        boolean z;
        boolean z2;
        boolean z3 = false;
        boolean z4 = true;
        if ((this.P & 64) != 0) {
            this.P &= -65;
            z = true;
        } else {
            z = false;
        }
        if ((this.P & 32) != 0) {
            A();
            this.P &= -33;
        }
        if ((this.P & 1) != 0) {
            B();
            c(true);
            this.W.d();
            this.P &= -2;
            z2 = true;
        } else {
            z2 = false;
        }
        if ((this.P & 16) != 0) {
            this.P &= -17;
            z2 = true;
        }
        if ((this.P & 4) != 0) {
            this.P &= -5;
        }
        if ((this.P & 2) != 0) {
            if (this.bj == FLYC_STATE.GoHome) {
                J();
            }
            I();
            this.bp.a(DataOsdGetPushHome.getInstance());
            this.W.e();
            this.P &= -3;
            z3 = true;
        }
        if ((this.P & 8) != 0) {
            H();
            this.P &= -9;
        } else {
            z4 = z;
        }
        if (this.bc != FLYC_STATE.GoHome || r1) {
            if (z2) {
                this.aa.update(z3);
            }
        } else if (z2) {
            this.aa.update(z3);
        }
    }

    public void l() {
        this.aa.disconnect();
        this.as.hideView();
        this.ar.hideView();
        this.ae.go();
        this.at.hideChart();
        this.ax.hideView();
        this.au.hideView();
        this.az.go();
        this.at.hideChart();
        this.av.hideView();
        P();
        dji.pilot.battery.a.a.getInstance().d();
        dji.pilot.battery.a.a.getInstance().f();
        DJIGenSettingDataManager.getInstance().f();
        this.bX.b();
        super.l();
    }

    private void P() {
        this.aZ = null;
        this.aX = TRIPOD_STATUS.UNKNOWN;
        this.aV = false;
        this.bj = FLYC_STATE.OTHER;
        this.ba = MotorStartFailedCause.None;
        this.bc = FLYC_STATE.OTHER;
        this.bT = -1;
        this.bU = ExposureMode.OTHER;
    }

    private void b(boolean z, int i) {
        if (!z) {
        }
    }

    public void onEventMainThread(dji.pilot.fpv.model.n.b bVar) {
        if (bVar == dji.pilot.fpv.model.n.b.a) {
            if (this.Y.getVisibility() == 0) {
                a(false, true);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.b) {
            if (this.Y.getVisibility() == 0) {
                b(false, true);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.e) {
            if (this.Y.getVisibility() == 0) {
                a(false, false);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.f) {
            b(false, false);
        } else if (bVar == dji.pilot.fpv.model.n.b.d && this.Y.getVisibility() == 0) {
            this.W.h();
        }
    }

    protected void a(boolean z, boolean z2) {
        if (z) {
            this.ae.go();
            this.aC.go();
            this.aT.a(false);
            this.aD.setPlayBackViewVisible(true);
            this.Y.hideDialog();
            this.az.go();
        } else {
            this.aT.a(true);
        }
        if (!z2) {
            this.Y.go();
            this.Y.startAnimation(this.aP);
        }
        this.aF.hideByOuter();
        this.aI.c();
        this.as.hideView();
        this.ar.hideView();
        this.at.hideChart();
        this.aq.hideMenu(true);
        this.aD.go();
        this.aa.go();
        this.aA.go();
        f(true);
        this.au.hideView();
        this.av.hideView();
        this.ax.hideView();
    }

    protected void a(boolean z) {
        a(z, false);
    }

    protected void c() {
        this.ae.go();
        this.aq.hideMenu(true);
        this.aT.a(true);
    }

    protected void d() {
        this.aq.showMenu();
        this.aT.f();
    }

    protected void e() {
        this.aI.c();
        this.aD.go();
        this.as.hideView();
        this.ar.hideView();
        this.at.hideChart();
    }

    protected void f() {
        this.aI.b();
        this.aD.show();
    }

    protected void b(boolean z, boolean z2) {
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
        if (z) {
            this.aD.setPlayBackViewVisible(false);
            this.aT.f();
            S();
            T();
        } else if (instance == null || !instance.q()) {
            this.aH.postDelayed(new 14(this), 50);
        }
        if (instance == null || !instance.q()) {
            this.aq.showMenu();
            this.aa.show();
            if (this.aT.c()) {
                this.at.showChart();
            }
        }
        if (!z2) {
            this.Y.show();
            this.Y.startAnimation(this.aO);
        }
        if (DJISwitchModeView.a == DJISwitchModeView.a.b) {
            this.av.showView();
        }
        if (this.aT.c()) {
            b();
            x();
            y();
            af();
            if (instance == null || !instance.p()) {
                this.aI.b();
                this.aD.show();
            }
            if (dji.pilot.visual.a.c.getInstance().e() && n()) {
                this.aA.show();
            }
            g();
        }
        if (dji.pilot.c.d.f == 1) {
            this.W.g();
        }
    }

    protected void g() {
        if (!this.o.booleanValue()) {
            this.aF.showByOuter();
        }
    }

    protected void b(boolean z) {
        b(z, false);
    }

    protected void h() {
        DJILogHelper.getInstance().LOGD("", "======handleEnterPlayBackMode======", false, true);
        if (this.bw != null) {
            this.bw.a(false, 0);
        }
        switch (32.a[dji.midware.data.manager.P3.i.getInstance().c().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                com.dji.frame.c.b.a((Context) this, DJIPlayBackActivity.class);
                return;
            case 8:
            case 9:
                if (this.ap == null) {
                    a(true);
                    this.ah.show();
                    this.aK = new 15(this);
                    this.ap = (DJIPlayBackView) this.ac.inflate();
                    this.ap.setOnFullScreenListener(this.aK);
                    this.ap.setCenterHeight(screenHeight);
                    this.ap.show();
                    dji.thirdparty.a.c.a().e(dji.pilot.groundStation.a.a.c.h);
                    return;
                } else if (this.ap.getVisibility() != 0) {
                    a(true);
                    this.ah.show();
                    this.ap.show();
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

    private void Q() {
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if (c != ProductType.litchiS && c != ProductType.litchiC && c != ProductType.P34K && !dji.pilot.publics.e.c.a(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            b(true);
            c(false);
            if (this.ap != null) {
                this.ap.go();
            }
            dji.thirdparty.a.c.a().e(dji.pilot.groundStation.a.a.c.i);
        }
    }

    private void R() {
        this.ar.handleCameraSettingClick();
    }

    private void d(boolean z) {
        this.aI.a(z);
        if (z) {
            this.as.hideView();
            this.ax.hideView();
            this.au.hideView();
            this.aD.go();
            this.aA.go();
            this.aa.hideSpeedLy();
            f(false);
        } else {
            this.aD.show();
            if (dji.pilot.visual.a.c.getInstance().e() && n()) {
                this.aA.show();
            }
            this.aa.showSpeedLy();
            b();
            af();
            x();
            y();
        }
        this.Y.handleCameraWidgetVisibility(z, false);
    }

    private void e(boolean z) {
        this.aI.b(z);
        if (z) {
            this.ar.hideView();
            this.ax.hideView();
            this.au.hideView();
            this.aD.go();
            this.aA.go();
            f(false);
            String a = dji.pilot.fpv.model.b.a();
            if (!("large".equals(a) || "xlarge".equals(a))) {
                this.aa.hideSpeedLy();
            }
        } else {
            af();
            b();
            x();
            y();
            this.aD.show();
            if (dji.pilot.visual.a.c.getInstance().e() && n()) {
                this.aA.show();
            }
            this.aa.showSpeedLy();
        }
        this.Y.handleCameraWidgetVisibility(z, false);
    }

    private void S() {
        int k = DJIGenSettingDataManager.getInstance().k();
        if (k == 0) {
            this.ae.go();
            return;
        }
        if (k == 1) {
            this.ae.setType(1);
        } else if (k == 2) {
            this.ae.setType(2);
        } else if (k == 3) {
            this.ae.setType(4);
        }
        this.ae.show();
    }

    private void T() {
        if (dji.pilot.fpv.camera.more.a.getInstance().s() == 0) {
            this.az.go();
        } else {
            this.az.show();
        }
    }

    private void U() {
        if (this.aB == null) {
            this.aB = (DJIGimbalRollFineTuneView) this.ag.inflate();
        }
        if (DJIGenSettingDataManager.getInstance().o()) {
            this.aB.show();
        } else {
            this.aB.go();
        }
    }

    private void V() {
    }

    private void W() {
        this.aN.h();
    }

    private void X() {
        this.aN.i();
    }

    public void i() {
        if (this.aW == null) {
            this.aW = new dji.pilot.publics.widget.b(this);
            this.aW.a(R.string.app_warning).b(R.string.liveshare_base_exit).d(R.string.app_enter).e(R.string.app_cancel).a(new 17(this)).b(new 16(this));
        }
        this.aW.show();
    }

    public void finishThis() {
        DJILogHelper.getInstance().LOGD(this.TAG, "on finishThis() start", false, false);
        if (this.ap == null || this.ap.getVisibility() != 0) {
            this.aH.removeMessages(8192);
            this.aH.removeMessages(4096);
            this.aH.removeMessages(16384);
            this.aH.removeMessages(24576);
            this.aJ.b();
            dji.thirdparty.a.c.a().e(DJIHubActivity.a.b);
            Y();
            dji.pilot.c.a.n = false;
            this.aI.a();
            Z();
            finish();
            overridePendingTransition(0, 0);
            DJILogHelper.getInstance().LOGD(this.TAG, "on finishThis() end", false, false);
            return;
        }
        DJILogHelper.getInstance().LOGD(this.TAG, "out playback " + ServiceManager.getInstance().isConnected(), false, true);
        if (ServiceManager.getInstance().isConnected()) {
            DataSpecialControl.getInstance().setPlayBackType(false).start(20);
        } else {
            Q();
        }
    }

    private void Y() {
        if (this.aG != null) {
            this.aG.a(null);
            this.aG.b();
            this.aG = null;
        }
        if (this.bw != null) {
            this.bw.c();
            this.bw = null;
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.aN.a(bundle);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.aN.d();
    }

    private void Z() {
        if (this.bv != null) {
            if (this.ap != null && this.ap.getVisibility() == 0) {
                this.ap.go();
            }
            this.bv.a();
            ad();
            DJIGenSettingDataManager.getInstance().b(this.aL);
            this.aT.a();
            this.aa.dispatchOnDestroy();
            this.Y.dispatchOnDestroy();
            this.af.dispatchOnDestroy();
            this.aD.dispatchOnDestroy();
            this.aq.dispatchOnDestroy();
            this.as.hideView();
            this.at.dispatchOnDestroy();
            this.j.a();
            this.ax.hideView();
            this.au.dispatchOnDestroy();
            this.aw.dispatchOnDestroy();
            FpvPopWarnView.dispatchOnDestroy();
            this.bv = null;
            unregisterReceiver(this.bx);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.aN.e();
        ao();
        Log.d(this.TAG, "onDestroy start");
        Log.d(this.TAG, "onDestroy" + this);
    }

    protected void onResume() {
        super.onResume();
        DJILogHelper.getInstance().LOGD("onresume", "Preview onResume" + this, false, true);
        this.aN.b();
    }

    protected void onPause() {
        super.onPause();
        this.aN.c();
    }

    protected void onStart() {
        super.onStart();
        this.aU = true;
        if (this.P != 0) {
            this.aH.sendEmptyMessageDelayed(4096, 200);
        }
        dji.pilot.fpv.d.e.b((Context) this);
        ServiceManager.getInstance().pauseService(false);
        dji.pilot.groundStation.a.a.a(this);
        dji.pilot.groundStation.a.a.getInstance(this).a(this.aN);
        dji.pilot.groundStation.a.a.getInstance(this).a(this.aT);
        dji.pilot.groundStation.a.a.getInstance(this).c(true);
        dji.pilot.dji_groundstation.controller.a.getInstance(this);
        dji.pilot.flyunlimit.b.getInstance(this).a(this.aN);
        dji.pilot.flyforbid.a.getInstance(this).c((Context) this);
    }

    protected void onStop() {
        this.aU = false;
        super.onStop();
        dji.pilot.groundStation.a.a.getInstance(this).c(false);
        dji.pilot.groundStation.a.a.getInstance(this).b();
        dji.pilot.fpv.d.e.c((Context) this);
        dji.pilot.flyunlimit.b.getInstance(this).a(null);
        dji.pilot.flyforbid.a.getInstance(this).f();
    }

    public void onBackPressed() {
        if (!ap()) {
            if (dji.pilot.liveshare.b.getInstance().isRunning() || dji.pilot.liveshare.b.getInstance().isLaunch()) {
                i();
            } else {
                finishThis();
            }
        }
    }

    public void oneFrameComeIn() {
        this.aQ++;
    }

    public void resetVideoSurface(int i, int i2) {
        this.aH.sendMessage(this.aH.obtainMessage(12288, i, i2));
    }

    private void a(int i, int i2) {
        aa();
    }

    private void a(int[] iArr, int[] iArr2, int i, int i2, int i3, float f, float f2, boolean z) {
        if (z) {
            int i4 = this.f * 2;
            if (i3 == 0) {
                i -= i4;
                i2 = (int) (((float) i) / f);
            } else if (1 == i3) {
                i2 -= i4;
                i = (int) (((float) i2) * f2);
            } else if (2 == i3) {
                i = (int) (((float) (i - i4)) / f);
                i2 = (int) (((float) (i2 - i4)) / f2);
            }
            i4 = (DJIBaseActivity.screenWidth - i) / 2;
            int i5 = (DJIBaseActivity.screenHeight - i2) / 2;
            this.bs[0] = i4;
            this.bs[1] = DJIBaseActivity.screenWidth - i4;
            this.bt[0] = i5;
            this.bt[1] = DJIBaseActivity.screenHeight - i5;
        } else {
            i = iArr[1] - iArr[0];
            i2 = iArr2[1] - iArr2[0];
            System.arraycopy(iArr, 0, this.bs, 0, 2);
            System.arraycopy(iArr2, 0, this.bt, 0, 2);
        }
        ViewGroup.LayoutParams layoutParams = this.aC.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        this.aC.setLayoutParams(layoutParams);
        this.av.setHVLimits(iArr, iArr2);
    }

    private void aa() {
        if (!this.o.booleanValue()) {
            float f;
            int i = DJIVideoDecoder.width;
            int i2 = DJIVideoDecoder.height;
            DJILogHelper.getInstance().LOGD("Test", "Video[" + i + com.alipay.sdk.j.i.b + i2 + dji.pilot.usercenter.protocol.d.H, false, true);
            boolean z = true;
            if (Math.abs(DJIBaseActivity.screenRatio - dji.midware.util.a.b.a) < Math.abs(DJIBaseActivity.screenRatio - dji.midware.util.a.b.b)) {
                this.bQ = RatioType.R_16_9;
            } else {
                this.bQ = RatioType.R_4_3;
            }
            if (this.bS == RatioType.OTHER) {
                this.bS = DataCameraGetPushShotParams.getInstance().isGetted() ? DataCameraGetPushShotParams.getInstance().getImageRatio() : RatioType.R_4_3;
            }
            ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
            if (c == ProductType.litchiC || c == ProductType.litchiS || c == ProductType.P34K || c == ProductType.KumquatS || c == ProductType.Pomato) {
                f = (((float) i) * 1.0f) / ((float) i2);
                if (Math.abs(f - dji.midware.util.a.b.a) < Math.abs(f - dji.midware.util.a.b.c)) {
                    this.bR = RatioType.R_16_9;
                } else if (Math.abs(f - dji.midware.util.a.b.c) < Math.abs(f - dji.midware.util.a.b.b)) {
                    this.bR = RatioType.R_3_2;
                } else {
                    this.bR = RatioType.R_4_3;
                }
                z = false;
            } else if (this.bS == RatioType.R_4_3 && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
                this.bR = RatioType.R_4_3;
            } else if (this.bS == RatioType.R_3_2 && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
                this.bR = RatioType.R_3_2;
            } else {
                this.bR = RatioType.R_16_9;
            }
            DJILogHelper.getInstance().LOGD(this.TAG, "isLong=" + z + " productType=" + c, false, true);
            DJILogHelper.getInstance().LOGD(this.TAG, "ratioType=" + this.bS, false, true);
            DJILogHelper.getInstance().LOGD(this.TAG, "screenRatioType=" + this.bQ, false, true);
            DJILogHelper.getInstance().LOGD(this.TAG, "videoRatioType=" + this.bR, false, true);
            LayoutParams layoutParams = (LayoutParams) this.T.getLayoutParams();
            int i3;
            if (z) {
                if (this.bQ == RatioType.R_16_9) {
                    layoutParams.width = DJIBaseActivity.screenWidth;
                    layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                } else {
                    layoutParams.width = DJIBaseActivity.screenWidth;
                    layoutParams.height = (int) (((1.0f * ((float) i2)) / ((float) i)) * ((float) DJIBaseActivity.screenWidth));
                }
                this.T.setLayoutParams(layoutParams);
                this.U.setLayoutParams(layoutParams);
                if (this.bQ == RatioType.R_4_3) {
                    float f2;
                    if (this.bR == RatioType.R_4_3) {
                        f2 = dji.midware.util.a.b.b;
                        this.T.setScaleX(dji.midware.util.a.b.b);
                        this.U.setScaleX(dji.midware.util.a.b.b);
                        layoutParams.width = DJIBaseActivity.screenWidth;
                        layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.b);
                    } else if (RatioType.R_3_2 == this.bR) {
                        f2 = ((((float) i) * 1.0f) / ((float) i2)) * 0.6666667f;
                        this.T.setScaleX(f2);
                        this.U.setScaleX(f2);
                        layoutParams.width = DJIBaseActivity.screenWidth;
                        layoutParams.height = (int) ((((float) DJIBaseActivity.screenWidth) * 8.0f) / 9.0f);
                    } else {
                        f2 = dji.midware.util.a.b.a;
                        this.T.setScaleX(1.0f);
                        this.U.setScaleX(1.0f);
                        layoutParams.width = DJIBaseActivity.screenWidth;
                        layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                    }
                    this.bq[0] = 0;
                    this.bq[1] = layoutParams.width;
                    i3 = (DJIBaseActivity.screenHeight - layoutParams.height) / 2;
                    this.br[0] = i3;
                    this.br[1] = DJIBaseActivity.screenHeight - i3;
                    DJILogHelper.getInstance().LOGD(this.TAG, "resetVisualTouchArea 1" + this.bq[0], false, true);
                    a(this.bq, this.br, this.bR, this.bR, true);
                    a(this.bq, this.br, layoutParams.width, layoutParams.height, 0, f2, 1.0f, true);
                    ab();
                    this.ae.setLayoutParams(layoutParams);
                    return;
                }
                ViewGroup.LayoutParams layoutParams2 = new LayoutParams(0, 0);
                layoutParams2.addRule(13, -1);
                if (this.bR == RatioType.R_4_3) {
                    layoutParams2.width = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.b);
                    layoutParams2.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                    i3 = (DJIBaseActivity.screenWidth - layoutParams2.width) / 2;
                    this.bq[0] = i3;
                    this.bq[1] = DJIBaseActivity.screenWidth - i3;
                    i3 = (DJIBaseActivity.screenHeight - layoutParams2.height) / 2;
                    this.br[0] = i3;
                    this.br[1] = DJIBaseActivity.screenHeight - i3;
                    DJILogHelper.getInstance().LOGD(this.TAG, "resetVisualTouchArea 2" + this.bq[0], false, true);
                    a(this.bq, this.br, RatioType.R_16_9, this.bR, true);
                    a(this.bq, this.br, DJIBaseActivity.screenWidth, DJIBaseActivity.screenWidth, 2, dji.midware.util.a.b.b, dji.midware.util.a.b.a, false);
                    ab();
                } else if (this.bR == RatioType.R_3_2) {
                    layoutParams2.width = (int) ((((float) DJIBaseActivity.screenWidth) * dji.midware.util.a.b.c) / dji.midware.util.a.b.a);
                    layoutParams2.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                    i3 = (DJIBaseActivity.screenWidth - layoutParams2.width) / 2;
                    this.bq[0] = i3;
                    this.bq[1] = DJIBaseActivity.screenWidth - i3;
                    i3 = (DJIBaseActivity.screenHeight - layoutParams2.height) / 2;
                    this.br[0] = i3;
                    this.br[1] = DJIBaseActivity.screenHeight - i3;
                    DJILogHelper.getInstance().LOGD(this.TAG, "resetVisualTouchArea 2" + this.bq[0], false, true);
                    a(this.bq, this.br, RatioType.R_16_9, this.bR, true);
                    a(this.bq, this.br, DJIBaseActivity.screenWidth, DJIBaseActivity.screenWidth, 2, dji.midware.util.a.b.c, dji.midware.util.a.b.a, false);
                    ab();
                } else {
                    layoutParams2.width = DJIBaseActivity.screenWidth;
                    layoutParams2.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                    this.bq[0] = 0;
                    this.bq[1] = layoutParams2.width;
                    i3 = (DJIBaseActivity.screenHeight - layoutParams2.height) / 2;
                    this.br[0] = i3;
                    this.br[1] = DJIBaseActivity.screenHeight - i3;
                    DJILogHelper.getInstance().LOGD(this.TAG, "resetVisualTouchArea 3" + this.bq[0], false, true);
                    a(this.bq, this.br, RatioType.R_16_9, RatioType.R_16_9, true);
                    a(this.bq, this.br, layoutParams.width, layoutParams.height, 0, dji.midware.util.a.b.a, 1.0f, true);
                    ab();
                }
                this.ae.setLayoutParams(layoutParams2);
                return;
            }
            if (this.bR == RatioType.R_16_9) {
                layoutParams.width = DJIBaseActivity.screenWidth;
                layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                this.bq[0] = 0;
                this.bq[1] = layoutParams.width;
                i3 = (DJIBaseActivity.screenHeight - layoutParams.height) / 2;
                this.br[0] = i3;
                this.br[1] = DJIBaseActivity.screenHeight - i3;
                a(this.bq, this.br, layoutParams.width, layoutParams.height, 0, dji.midware.util.a.b.a, 1.0f, true);
                a(this.bq, this.br, this.bR, this.bR, false);
                ab();
            } else if (RatioType.R_3_2 == this.bR) {
                int i4;
                i3 = 0;
                layoutParams.width = DJIBaseActivity.screenWidth;
                layoutParams.height = DJIBaseActivity.screenHeight;
                if (DJIBaseActivity.screenWidth * i2 > DJIBaseActivity.screenHeight * i) {
                    f = (((float) DJIBaseActivity.screenHeight) * 1.0f) / ((float) i2);
                    layoutParams.width = (int) (((float) i) * f);
                    i4 = (int) ((((float) DJIBaseActivity.screenWidth) - (f * ((float) i))) / 2.0f);
                } else {
                    float f3 = (((float) DJIBaseActivity.screenWidth) * 1.0f) / ((float) i);
                    layoutParams.height = (int) (((float) i2) * f3);
                    i3 = (int) ((((float) DJIBaseActivity.screenHeight) - (f3 * ((float) i2))) / 2.0f);
                    i4 = 0;
                }
                DJILogHelper.getInstance().LOGD("Preview", "Surface[" + layoutParams.width + com.alipay.sdk.j.i.b + layoutParams.height + dji.pilot.usercenter.protocol.d.H, false, true);
                this.bq[0] = i4;
                this.bq[1] = DJIBaseActivity.screenWidth - i4;
                this.br[0] = i3;
                this.br[1] = DJIBaseActivity.screenHeight - i3;
                a(this.bq, this.br, layoutParams.width, layoutParams.height, 0, dji.midware.util.a.b.c, 1.0f, i4 == 0);
                a(this.bq, this.br, this.bR, this.bR, false);
                ab();
            } else {
                layoutParams.width = (int) (((float) DJIBaseActivity.screenHeight) * dji.midware.util.a.b.b);
                layoutParams.height = DJIBaseActivity.screenHeight;
                i3 = (DJIBaseActivity.screenWidth - layoutParams.width) / 2;
                this.bq[0] = i3;
                this.bq[1] = DJIBaseActivity.screenWidth - i3;
                this.br[0] = 0;
                this.br[1] = layoutParams.height;
                a(this.bq, this.br, DJIBaseActivity.screenHeight, DJIBaseActivity.screenHeight, 1, dji.midware.util.a.b.b, dji.midware.util.a.b.b, this.bQ == RatioType.R_4_3);
                a(this.bq, this.br, this.bR, this.bR, false);
                ab();
            }
            this.T.setLayoutParams(layoutParams);
            this.U.setLayoutParams(layoutParams);
            this.ae.setLayoutParams(layoutParams);
        }
    }

    private void a(int[] iArr, int[] iArr2, RatioType ratioType, RatioType ratioType2, boolean z) {
        dji.pilot.visual.a.c.getInstance().a(iArr, iArr2);
        dji.pilot.visual.a.c.getInstance().a(ratioType, ratioType2, z);
        LayoutParams layoutParams = (LayoutParams) this.am.getLayoutParams();
        layoutParams.width = dji.pilot.visual.a.c.getInstance().d;
        layoutParams.height = dji.pilot.visual.a.c.getInstance().e;
        if (layoutParams.width == 0) {
            layoutParams.width = DJIBaseActivity.screenWidth;
        }
        if (layoutParams.height == 0) {
            layoutParams.height = DJIBaseActivity.screenHeight;
        }
        this.am.setLayoutParams(layoutParams);
        DJILogHelper.getInstance().LOGD(this.TAG, "width= " + layoutParams.width + " height= " + layoutParams.height, false, true);
    }

    private void ab() {
        this.e = (this.bq[1] - this.bq[0]) / this.g;
        this.d = (this.br[1] - this.br[0]) / this.h;
    }

    void j() {
        try {
            InputStream openRawResource = getResources().openRawResource(DJIVideoDecoder.getIframeRawId(dji.midware.data.manager.P3.i.getInstance().c(), 1280, 720));
            int available = openRawResource.available();
            byte[] bArr = new byte[available];
            openRawResource.read(bArr);
            NativeRcController.b(bArr, available);
            openRawResource.close();
        } catch (Exception e) {
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.bw = dji.midware.media.h.e.a(getClass());
        this.bw.a(surfaceTexture, i, i2);
        this.bw.a(DJIGenSettingDataManager.getInstance().F(), R.raw.overexposure);
        if (this.aG == null) {
            a(this.bw);
        } else {
            this.aG.a(this.bw);
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.bw != null) {
            this.bw.a(i, i2);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.aG != null) {
            this.aG.a(null);
        }
        if (this.bw != null) {
            this.bw.c();
            this.bw = null;
        }
        NativeRcController.d();
        return false;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    private void a(dji.midware.media.h.b.b bVar) {
        this.aG = new f(this, bVar);
        this.aG.a(this);
        FPVController.native_setDecodeMode(dji.pilot.publics.objects.g.b((Context) this, "DecodeMode", false));
    }

    private void ac() {
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
    }

    private void ad() {
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
    }

    private boolean ae() {
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
        return instance == null || !instance.p();
    }

    private void af() {
        Object obj = ((this.ap != null && this.ap.getVisibility() == 0) || this.as.getVisibility() == 0 || this.ar.getVisibility() == 0 || !ae() || dji.pilot.fpv.flightmode.c.getInstance().a() == dji.pilot.fpv.flightmode.c.b.g) ? null : 1;
        if (obj != null) {
            if (this.aT.c()) {
                this.an.show();
            }
            this.ao.showCheck();
        }
    }

    private void f(boolean z) {
        this.an.go();
        Object obj = (this.Y.getVisibility() != 0 || this.as.getVisibility() == 0 || this.ar.getVisibility() == 0 || !ae()) ? null : 1;
        if (z || obj == null) {
            this.ao.go();
        }
    }

    private void ag() {
        if (!this.al.needShow() || dji.pilot.visual.a.c.getInstance().l() || !this.aT.c()) {
            return;
        }
        if ((this.ap == null || this.ap.getVisibility() != 0) && this.as.getVisibility() != 0 && this.ar.getVisibility() != 0 && this.ax.getVisibility() != 0 && ae()) {
            this.al.show();
        }
    }

    public void onEventMainThread(DJISwitchModeView.b bVar) {
        if (bVar != DJISwitchModeView.b.b && bVar == DJISwitchModeView.b.a) {
            ag();
        }
    }

    private void a(DJISwitchModeView.a aVar) {
        if (DJISwitchModeView.a.a == aVar) {
            this.av.hideView();
            if (this.bU == ExposureMode.OTHER && DataCameraGetPushShotParams.getInstance().isGetted()) {
                this.bU = DataCameraGetPushShotParams.getInstance().getExposureMode();
                this.bT = DataCameraGetPushShotParams.getInstance().isAELock() ? 1 : 0;
            }
            if (this.bU != ExposureMode.OTHER && ExposureMode.M != this.bU && this.bT == 0) {
                int metering = DataCameraGetPushShotParams.getInstance().getMetering();
                this.ai.show();
                this.ak.show();
                if (metering == 2) {
                    this.aj.show();
                    return;
                }
                this.aj.hide();
                this.aH.sendMessageDelayed(this.aH.obtainMessage(32768, 0, 0), 2000);
            }
        } else if (DJISwitchModeView.a.b == aVar) {
            this.ai.hide();
            this.ak.hide();
            this.aj.hide();
            this.av.showView();
        }
    }

    public void onEventMainThread(DJISwitchModeView.a aVar) {
        a(aVar);
    }

    public void onEventMainThread(dji.pilot.visual.a.g.e eVar) {
        if (dji.pilot.visual.a.c.getInstance().e() && this.aT.c()) {
            this.aA.show();
        } else {
            this.aA.go();
        }
    }

    public void onEventMainThread(dji.pilot.visual.a.g.f fVar) {
        if (!dji.pilot.visual.a.c.getInstance().l()) {
            this.aA.go();
        } else if (dji.pilot.visual.a.c.getInstance().e() && this.aT.c()) {
            this.aA.show();
        }
    }

    public void onEventMainThread(dji.pilot.visual.a.g.d dVar) {
        if (dVar == dji.pilot.visual.a.g.d.a) {
            this.ai.hide();
            this.ak.hide();
            this.aj.hide();
            aq();
        }
    }

    public void onEventMainThread(DJIGlobalService.a aVar) {
        if (aVar == DJIGlobalService.a.C2LongPress) {
            this.ae.setType(1);
            this.ae.show();
        } else if (aVar == DJIGlobalService.a.C2PressUp) {
            S();
        }
    }

    public void onEventMainThread(DJIPlayBackActivity.a aVar) {
        switch (32.b[aVar.ordinal()]) {
            case 1:
                if (this.aG != null) {
                    DJILogHelper.getInstance().LOGD(this.TAG, "mVideoDecoder resetToManager", false, true);
                    this.aG.a();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(ProductType productType) {
        switch (32.a[productType.ordinal()]) {
            case 1:
            case 3:
            case 8:
                return;
            case 2:
                if (this.aG == null) {
                    return;
                }
                return;
            default:
                if (dji.pilot.liveshare.b.getInstance().isRunning() || dji.pilot.liveshare.b.getInstance().isLaunch()) {
                    i();
                    return;
                } else {
                    finishThis();
                    return;
                }
        }
    }

    private void ah() {
        DJILogHelper.getInstance().LOGD(this.TAG, "showNoVideoGoHomeDialog isRemoteOK=" + ServiceManager.getInstance().isRemoteOK(), false, true);
        if (ServiceManager.getInstance().isRemoteOK()) {
        }
        if (this.bI == null) {
            this.bI = new dji.pilot.fpv.leftmenu.b(this);
            this.bI.a(1);
            this.bI.a(new 20(this));
            this.bI.d(getString(R.string.disconnect_gohome_alert_gohome_button));
            this.bI.d(0);
            this.bI.a(8, 0).e(8);
            this.bI.a(0, getString(R.string.disconnect_gohome_alert_title2));
        }
        this.bJ = 5;
        this.bI.c();
        this.bI.show();
        DJILogHelper.getInstance().LOGD(this.TAG, "showNoVideoGoHomeDialog show", false, true);
        dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.v);
    }

    private void ai() {
        if (this.bI != null && this.bI.isShowing()) {
            if (this.bJ == 0) {
                this.bI.dismiss();
                aj();
                return;
            }
            this.bI.b(getString(R.string.disconnect_gohome_alert_title, new Object[]{Integer.valueOf(this.bJ)}));
            this.aH.sendEmptyMessageDelayed(C, 1000);
            this.bJ--;
        }
    }

    private void aj() {
        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.GOHOME).start(new 21(this));
    }

    public void onEventBackgroundThread(dji.midware.data.manager.P3.m mVar) {
        switch (32.c[mVar.ordinal()]) {
            case 1:
                this.aH.post(this.bG);
                this.aH.removeMessages(B);
                this.aH.removeMessages(C);
                if (this.bI != null && this.bI.isShowing()) {
                    this.bI.dismiss();
                    return;
                }
                return;
            case 2:
                if (this.isVisible) {
                    this.aH.post(this.bH);
                    if (ServiceManager.getInstance().isRemoteOK() && DataOsdGetPushCommon.getInstance().groundOrSky() == 2 && DataCameraGetPushStateInfo.getInstance().getMode() != MODE.PLAYBACK && DataCameraGetPushStateInfo.getInstance().getMode() != MODE.DOWNLOAD) {
                        this.aH.sendEmptyMessageDelayed(B, ToolTipPopup.a);
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
        if (this.bw != null) {
            this.bw.a(aVar.a, aVar.b);
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (32.d[oVar.ordinal()]) {
            case 1:
                s();
                this.aY = System.currentTimeMillis();
                this.aH.removeMessages(16384);
                this.aH.removeMessages(4096);
                this.P = 127;
                this.aH.sendEmptyMessageDelayed(4096, 200);
                this.aH.removeMessages(8192);
                this.aH.sendEmptyMessageDelayed(8192, R);
                this.aH.sendEmptyMessage(24576);
                return;
            case 2:
                this.bP = null;
                if (this.ap != null) {
                    this.ap.setIsBackPBack(true);
                }
                this.P = 0;
                this.aH.removeMessages(4096);
                this.aH.removeMessages(8192);
                this.aH.sendEmptyMessageDelayed(16384, 200);
                return;
            default:
                return;
        }
    }

    private void e(int i) {
        if (this.aU && (this.P & i) == 0) {
            this.P |= i;
            if (!this.aH.hasMessages(4096)) {
                this.aH.sendEmptyMessageDelayed(4096, 200);
            }
        }
    }

    public void onEventBackgroundThread(DataDm368GetPushStatus dataDm368GetPushStatus) {
        if (dataDm368GetPushStatus.isDisableLiveview() && !this.bK) {
            this.bK = true;
            this.bL.a(DM368CmdId.a, 0).start(new 22(this));
        }
    }

    protected void onBackgroundThreadOver(DataOsdGetPushCommon dataOsdGetPushCommon) {
        e(1);
        if (this.aU && !this.aH.hasMessages(8192)) {
            this.aH.sendEmptyMessageDelayed(8192, R);
        }
        this.q = true;
        this.aH.removeMessages(D);
        this.aH.sendEmptyMessageDelayed(D, 1000);
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        e(2);
    }

    public void onEventBackgroundThread(DataRcGetPushGpsInfo dataRcGetPushGpsInfo) {
        e(4);
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        e(16);
    }

    public void onEventBackgroundThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        e(8);
    }

    public void onEventBackgroundThread(DataFlycGetPushDeformStatus dataFlycGetPushDeformStatus) {
        e(32);
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        MODE mode = dataCameraGetPushStateInfo.getMode();
        if (mode != this.bP) {
            this.bP = mode;
            this.aH.sendEmptyMessage(12288);
            if (this.bP == MODE.PLAYBACK) {
                this.aH.sendMessage(this.aH.obtainMessage(32768, 0, 0));
            }
            DJILogHelper.getInstance().LOGD(this.TAG, "cameramode=" + this.bP, false, true);
            if (this.bP == MODE.DOWNLOAD && (this.ap == null || this.ap.isBackPBack())) {
                DJILogHelper.getInstance().LOGD(this.TAG, "!mPlayBackView.isDownload()" + ServiceManager.getInstance().isConnected(), false, true);
                DataCameraSetMode.getInstance().a(MODE.PLAYBACK).start(new 24(this));
            }
            if (this.ap != null) {
                this.ap.setIsBackPBack(false);
            }
            if (this.bP == MODE.NEW_PLAYBACK) {
                DataCameraSetMode.getInstance().a(dji.pilot.c.d.a).start(null);
            }
            if ((this.bP == MODE.TAKEPHOTO || this.bP == MODE.RECORD) && this.bw != null) {
                this.bw.a(DJIGenSettingDataManager.getInstance().F(), R.raw.overexposure);
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        if (dji.pilot.fpv.d.b.c(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            int mFFocusStatus = dataCameraGetPushShotInfo.getMFFocusStatus();
            if (mFFocusStatus != this.bV) {
                this.bV = mFFocusStatus;
                if (mFFocusStatus == 1) {
                    this.av.hideView();
                    this.ai.hide();
                    this.ak.hide();
                    this.aj.hide();
                } else if (this.ap == null || this.ap.getVisibility() != 0) {
                    a(DJISwitchModeView.a);
                }
            }
            FuselageFocusMode fuselageFocusMode = dataCameraGetPushShotInfo.getFuselageFocusMode();
            if (fuselageFocusMode == FuselageFocusMode.OneAuto || fuselageFocusMode == FuselageFocusMode.ContinuousAuto) {
                mFFocusStatus = dataCameraGetPushShotInfo.getFocusStatus();
                if (this.bW != mFFocusStatus) {
                    this.bW = mFFocusStatus;
                    if (mFFocusStatus == 2) {
                        dji.pilot.fpv.camera.more.a.getInstance().C();
                    }
                }
            } else {
                this.bW = 0;
            }
            mFFocusStatus = dataCameraGetPushShotInfo.getFocusWindowStartX();
            int focusWindowStartY = dataCameraGetPushShotInfo.getFocusWindowStartY();
            int focusWindowRealNumX = dataCameraGetPushShotInfo.getFocusWindowRealNumX();
            int focusWindowRealNumY = dataCameraGetPushShotInfo.getFocusWindowRealNumY();
            if (!this.av.a) {
                return;
            }
            if (this.k != mFFocusStatus || this.l != focusWindowStartY || this.m != focusWindowRealNumX || this.n != focusWindowRealNumY) {
                this.k = dataCameraGetPushShotInfo.getFocusWindowStartX();
                this.l = dataCameraGetPushShotInfo.getFocusWindowStartY();
                this.m = dataCameraGetPushShotInfo.getFocusWindowRealNumX();
                this.n = dataCameraGetPushShotInfo.getFocusWindowRealNumY();
                if (this.aH.hasMessages(F)) {
                    this.aH.removeMessages(F);
                }
                this.av.a = false;
                this.aH.sendEmptyMessage(F);
            }
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int i = dataCameraGetPushShotParams.isAELock() ? 1 : 0;
        ExposureMode exposureMode = dataCameraGetPushShotParams.getExposureMode();
        if (!(this.bT == i && exposureMode == this.bU)) {
            this.bT = i;
            this.bU = exposureMode;
            if (i == 1 || exposureMode == ExposureMode.M) {
                this.aH.sendMessage(this.aH.obtainMessage(32768, 0, 0));
            } else if (DataCameraGetPushShotParams.getInstance().getMetering() == 2) {
                this.aH.sendMessage(this.aH.obtainMessage(32768, 1, 0));
            }
        }
        RatioType imageRatio = DataCameraGetPushShotParams.getInstance().getImageRatio();
        if (imageRatio != this.bS) {
            this.bS = imageRatio;
            this.aH.sendEmptyMessage(12288);
        }
        this.i.a(dataCameraGetPushShotParams);
    }

    public void onEventBackgroundThread(b.a aVar) {
        switch (32.e[aVar.ordinal()]) {
            case 1:
                if (this.g == 0) {
                    DataCameraGetMeteringArea.getInstance().start(this.by);
                    return;
                }
                return;
            case 2:
                ak();
                return;
            default:
                return;
        }
    }

    public void a(int i) {
        if (i == 0) {
            this.ca.a = SdModeView.a.c;
        } else {
            this.ca.a = SdModeView.a.a;
        }
        DJILogHelper.getInstance().LOGD("", "lose_osd blackMode=" + i, false, true);
        onEventMainThread(this.ca);
    }

    public void onEventMainThread(DataFlycGetPushAvoid dataFlycGetPushAvoid) {
        if (dji.pilot.fpv.d.b.j(null)) {
            this.bX.onEventMainThread(dataFlycGetPushAvoid);
        }
    }

    public void onEventMainThread(SdModeView sdModeView) {
        this.ca = sdModeView;
        SdModeView.a aVar = sdModeView.a;
        if (this.bZ == null) {
            this.bZ = new dji.pilot.publics.widget.b(this, false);
            this.bZ.a(R.string.app_tip);
            this.bZ.d(R.string.app_enter);
            this.bZ.a(new 25(this));
        }
        switch (32.f[aVar.ordinal()]) {
            case 1:
                this.bZ.b(R.string.mc_switch_sd_mode_success);
                this.bZ.show();
                sdModeView.setBlackStatus(false);
                dji.thirdparty.a.c.a().e(aVar);
                return;
            case 2:
                this.bY = 0;
                this.aH.sendEmptyMessageDelayed(E, 1000);
                return;
            default:
                this.bZ.b(R.string.mc_switch_sd_mode_error);
                this.bZ.show();
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
        dji.pilot.groundStation.a.a instance;
        switch (32.g[dJICustomType.ordinal()]) {
            case 1:
                if (!ap() && !this.mGuideShowing && this.aT.h()) {
                    this.aT.b();
                    return;
                }
                return;
            case 2:
                instance = dji.pilot.groundStation.a.a.getInstance(null);
                if ((instance == null || !instance.c() || !instance.p()) && !ap()) {
                    if ((this.ap == null || !this.ap.isShown()) && !this.mGuideShowing && this.aT.c() && !this.Y.hasDlgShowing()) {
                        this.ar.handleCameraSettingClick();
                        return;
                    }
                    return;
                }
                return;
            case 3:
                instance = dji.pilot.groundStation.a.a.getInstance(null);
                if ((instance == null || !instance.c() || !instance.p()) && !ap()) {
                    if ((this.ap == null || !this.ap.isShown()) && !this.mGuideShowing) {
                        this.Y.handleBatteryClickPush();
                        return;
                    }
                    return;
                }
                return;
            case 4:
                DataSpecialControl.getInstance().resetGimbal().start(20);
                return;
            case 5:
                this.aq.switchGimbalMode();
                return;
            case 6:
                DataRcSetGimbalControlMode.MODE mode;
                if (DataRcGetGimbalControlMode.getInstance().getMode() == DataRcSetGimbalControlMode.MODE.a) {
                    mode = DataRcSetGimbalControlMode.MODE.c;
                } else {
                    mode = DataRcSetGimbalControlMode.MODE.a;
                }
                DataRcSetGimbalControlMode.getInstance().a(mode).start(new 26(this, mode));
                return;
            case 7:
                this.aI.c();
                if (this.ar.getVisibility() == 0) {
                    this.ar.hideView();
                }
                if (this.as.getVisibility() == 0) {
                    this.as.hideView();
                }
                if (this.aD.getVisibility() == 0) {
                    this.aD.go();
                }
                this.aA.go();
                this.ax.hideView();
                this.au.hideView();
                f(false);
                dji.pilot.groundStation.a.a instance2 = dji.pilot.groundStation.a.a.getInstance(null);
                if (instance2 == null || !instance2.q()) {
                    this.aT.f();
                    this.aa.show();
                    this.aq.showMenu();
                    this.at.showChart();
                    return;
                }
                this.aT.a(true);
                this.aa.go();
                this.aq.hideMenu(true);
                this.at.hideChart();
                return;
            case 8:
                if (this.ap == null || !this.ap.isShown()) {
                    if (this.aT.c()) {
                        this.aI.b();
                        this.aD.show();
                        b();
                        x();
                        y();
                        if (dji.pilot.visual.a.c.getInstance().e() && n()) {
                            this.aA.show();
                        }
                        this.at.showChart();
                    }
                    af();
                    this.aT.f();
                    this.aa.show();
                    this.aq.showMenu();
                    return;
                }
                return;
            case 9:
                if (!ap()) {
                    if (this.aT.h()) {
                        this.aT.b();
                        return;
                    }
                    this.aT.g();
                    this.aT.b();
                    this.aT.a(true);
                    return;
                }
                return;
            case 10:
                if ((this.ap == null || !this.ap.isShown()) && !this.mGuideShowing) {
                    ak();
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
            default:
                return;
        }
    }

    private void g(boolean z) {
        this.aJ.a(z, DataOsdGetPushCommon.getInstance().getVoltageWarning(), DataCenterGetPushBatteryCommon.getInstance().getRelativeCapacity());
    }

    private void ak() {
        if (ServiceManager.getInstance().isConnected()) {
            this.aH.sendEmptyMessage(x);
            this.cb.a(dji.midware.data.config.P3.b.a.SetMetering).a(0).start(new 27(this));
        }
    }

    private void al() {
        this.aH.sendMessage(this.aH.obtainMessage(32768, 1, 1));
        this.aH.sendMessageDelayed(this.aH.obtainMessage(32768, 0, 0), 2000);
    }

    public void onEventMainThread(dji.pilot.fpv.model.a.a aVar) {
        if (b.a.c == aVar.a) {
            if ((this.ap == null || !this.ap.isShown()) && !this.mGuideShowing) {
                new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.SetMetering).a(0).start(null);
            }
        } else if (b.a.a == aVar.a && this.g != 0) {
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
                this.cb.a(dji.midware.data.config.P3.b.a.SetMetering).a(2).start(new 28(this, f, f2));
                dji.pilot.fpv.d.e.c(s.dn);
            } else if (metering == 2) {
                b(f, f2);
            }
        }
        return false;
    }

    private void b(float f, float f2) {
        if (this.e != 0) {
            int i = ((int) ((f - ((float) this.bq[0])) / ((float) this.e))) + (((int) ((f2 - ((float) this.br[0])) / ((float) this.d))) * this.g);
            this.aH.removeMessages(32768);
            this.aH.sendMessage(this.aH.obtainMessage(20480, new PointF(f, f2)));
            DJILogHelper.getInstance().LOGD(this.TAG, "meteringarea index=" + i, false, false);
            DataCameraSetMeteringArea.getInstance().a(i).start(new 29(this));
        }
    }

    private void am() {
        if (this.bh == null) {
            this.bh = new AlphaAnimation(0.4f, 1.0f);
            this.bh.setDuration(R);
            this.bh.setRepeatCount(3);
            this.bh.setRepeatMode(2);
            this.bh.setAnimationListener(new 30(this));
        }
        this.aC.show();
        this.aC.startAnimation(this.bh);
    }

    private void an() {
        ao();
    }

    private void ao() {
        this.aH.removeMessages(z);
        this.bu.a();
    }

    public void k() {
        dji.pilot.fpv.d.e.a("FPV_LongPressGesture_ControlGimbal");
        dji.pilot.fpv.d.e.c(s.dp);
        this.bu.a(this.ce);
    }

    public Bitmap D() {
        return this.T.getBitmap(Bitmap.createBitmap(this.T.getWidth() / 2, this.T.getHeight() / 2, Config.RGB_565));
    }

    public boolean m() {
        return this.Y.getVisibility() == 0;
    }

    public void onEventMainThread(dji.pilot.flyforbid.a.b bVar) {
        switch (32.h[bVar.ordinal()]) {
            case 1:
                this.aN.a(null);
                return;
            case 2:
                this.aN.a(dji.pilot.flyforbid.a.getInstance(this).a());
                return;
            case 3:
                dji.pilot.flyforbid.a.getInstance(this).b();
                return;
            case 4:
                if (this.Y.hasDlgShowing()) {
                    this.bB = true;
                    return;
                } else {
                    dji.pilot.flyforbid.a.getInstance(this).a((Context) this);
                    return;
                }
            case 5:
                NFZLogUtil.savedLOG("nfz log 6 b d limits" + dji.pilot.flyforbid.a.getInstance(this).c().b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + dji.pilot.flyforbid.a.getInstance(this).c().c);
                this.aN.b(dji.pilot.flyforbid.a.getInstance(this).c());
                NFZLogUtil.savedLOG("nfz log 6 a d limits");
                return;
            case 6:
                if (!this.Y.hasDlgShowing()) {
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

    public boolean n() {
        return this.Y.getVisibility() == 0;
    }

    public void o() {
        if (this.c.getVisibility() == 0) {
            this.c.hide();
            this.c.hideJoyStick();
            return;
        }
        this.c.show();
        this.c.showJoyStick();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 2 && motionEvent.getAction() == 261) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.cf < 800) {
                try {
                    Class.forName("com.dji.tools.base.InnerToolsDialog").getMethod("showInnerTools", new Class[]{Context.class}).invoke(null, new Object[]{this});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.cf = currentTimeMillis;
        }
        if (ap() && a(motionEvent)) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean ap() {
        return this.ck != null && this.ck.getVisibility() == 0;
    }

    private void aq() {
        if (this.aT.c()) {
            dji.pilot.visual.util.c.a("here to show Visual Tutorial");
            if (dji.pilot.visual.beginner.a.getInstance().i()) {
                if (this.ck == null) {
                    this.ck = (DJIVisualBeginnerView) View.inflate(this, R.layout.visual_beginner_view, null);
                    this.S.addView(this.ck, -1, -1);
                }
                this.ck.show();
            }
        }
    }

    private void a(View view, MotionEvent motionEvent, Rect rect) {
        motionEvent.offsetLocation((float) (-rect.left), (float) (-rect.top));
        view.dispatchTouchEvent(motionEvent);
        motionEvent.offsetLocation((float) rect.left, (float) rect.top);
    }

    private boolean a(MotionEvent motionEvent) {
        this.cl.setEmpty();
        int action = motionEvent.getAction();
        if (action == 0) {
            View handleEventView = this.ck.getHandleEventView();
            if (handleEventView != null && handleEventView.getGlobalVisibleRect(this.cl) && this.cl.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                a(handleEventView, motionEvent, this.cl);
                this.cm = handleEventView;
            } else {
                int[] h = dji.pilot.visual.beginner.a.getInstance().h();
                if (h != null && h.length > 0) {
                    int length = h.length;
                    for (action = 0; action < length; action++) {
                        if (h[action] != 0) {
                            View findViewById = findViewById(h[action]);
                            if (findViewById != null && findViewById.getGlobalVisibleRect(this.cl) && this.cl.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                                a(findViewById, motionEvent, this.cl);
                                this.cm = findViewById;
                                break;
                            }
                        }
                    }
                }
            }
        } else if (this.cm != null) {
            this.cm.getGlobalVisibleRect(this.cl);
            a(this.cm, motionEvent, this.cl);
            if (action == 3 || action == 1) {
                this.cm = null;
            }
        }
        return true;
    }
}
