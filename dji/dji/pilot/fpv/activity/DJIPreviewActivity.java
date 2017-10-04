package dji.pilot.fpv.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.PointF;
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
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.facebook.login.widget.ToolTipPopup;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.common.camera.CameraTapZoomTargetPoint;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.forbid.NFZLogUtil;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataBatteryGetPushCheckStatus;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetMeteringArea;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraSetAELock;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetMeteringArea;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCameraTauParamThermometricEnable.ThermometricType;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetPushAvoid;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery.SmartGoHomeStatus;
import dji.midware.data.model.P3.DataFlycSmartAck;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause;
import dji.midware.data.model.P3.DataOsdGetPushCommon.TRIPOD_STATUS;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetGimbalControlMode;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.midware.data.model.P3.DataRcSetGimbalControlMode;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.midware.data.model.P3.DataSmartBatteryGetPushReArrangement;
import dji.midware.data.model.P3.DataSmartBatteryGetPushReArrangement.ReArrangement;
import dji.midware.data.model.P3.DataSmartBatteryGetPushReArrangement.ReArrangementOption;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.e.d;
import dji.midware.e.h;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.natives.FPVController;
import dji.pilot.R;
import dji.pilot.fpv.b.a;
import dji.pilot.fpv.camera.focus.DJIFocusAreaView;
import dji.pilot.fpv.camera.focus.DJIFocusDistanceView;
import dji.pilot.fpv.camera.focus.DJIFocusRingView;
import dji.pilot.fpv.camera.focus.DJIMFDemarcateView;
import dji.pilot.fpv.camera.focus.DJIZoomFocusView;
import dji.pilot.fpv.camera.newfn.DJICameraFnView;
import dji.pilot.fpv.camera.newfn.DJICameraTauSceneView;
import dji.pilot.fpv.camera.osd.DJICameraOsdX5RView;
import dji.pilot.fpv.camera.ref.DJICameraLineRefView;
import dji.pilot.fpv.camera.ref.DJICameraPointRefView;
import dji.pilot.fpv.camera.roi.DJIAreaMeasureView;
import dji.pilot.fpv.camera.roi.DJIRoiView;
import dji.pilot.fpv.camera.setting.DJICameraSettingBaseView;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIRedundancySysController;
import dji.pilot.fpv.control.b;
import dji.pilot.fpv.control.e;
import dji.pilot.fpv.control.j;
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
import java.util.Timer;

public class DJIPreviewActivity extends DJIPreviewActivityBaseForMC implements SurfaceTextureListener, OnClickListener, h, a, s {
    private static final int A = 32768;
    private static final int B = 36864;
    private static final int C = 36865;
    private static final int D = 36866;
    private static final int E = 36867;
    private static final int F = 36868;
    private static final int G = 36869;
    private static final int H = 36870;
    private static final int I = 36872;
    private static final int J = 36873;
    private static final int K = 36874;
    private static final int L = 0;
    private static final int M = 1;
    private static final int N = 2;
    private static final int O = 4;
    private static final int P = 8;
    private static final int Q = 16;
    private static final int R = 32;
    private static final int S = 64;
    private static final int T = 127;
    private static final long Y = 200;
    private static final long aa = 100;
    private static DJIPreviewActivity bG = null;
    private static final int bV = 4500;
    private static final boolean bc = false;
    private static final int bu = 8192;
    private static final int bv = 8193;
    private static final int bw = 8194;
    private static boolean cb = false;
    private static dji.pilot.publics.widget.h cu = null;
    private static final int t = 4096;
    private static final int u = 8192;
    private static final int v = 12288;
    private static final int w = 16384;
    private static final int x = 20480;
    private static final int y = 24576;
    private static final int z = 28672;
    private volatile int U = 0;
    @c(a = 2131362920)
    protected DJISdrDebugView a;
    @c(a = 2131363208)
    private DJILinearLayout aA;
    @c(a = 2131362865)
    private DJIFMSettingView aB;
    @c(a = 2131362906)
    private DJIRoiView aC;
    private DJIGimbalRollFineTuneView aD = null;
    @c(a = 2131362902)
    private DJIImageView aE = null;
    @c(a = 2131362915)
    private DJIMFDemarcateView aF;
    @c(a = 2131362904)
    private DJICameraPointRefView aG;
    @c(a = 2131362903)
    private DJICameraLineRefView aH;
    @c(a = 2131362917)
    private ViewStub aI;
    private DJICameraTauSceneView aJ = null;
    @c(a = 2131362907)
    private DJIAreaMeasureView aK = null;
    @c(a = 2131362857)
    private DJIFpvGimbalPitchView aL;
    @c(a = 2131363083)
    private LiveShareFpvTopView aM;
    private DJICameraOsdX5RView aN;
    private ImageView aO;
    private Animation aP = null;
    private f aQ = null;
    private a aR = null;
    private b aS;
    private dji.pilot.fpv.control.a aT = null;
    private DJIPlayBackView.b aU = null;
    private DJIGenSettingDataManager.c aV = null;
    private DJIFpvTopBaseView.b aW = null;
    private k aX = new k(this);
    private Animation aY = null;
    private Animation aZ = null;
    @c(a = 2131362846)
    private DJIRelativeLayout ab;
    @c(a = 2131362848)
    private TextureView ac;
    @c(a = 2131362849)
    private DJIImageView ad;
    @c(a = 2131362858)
    private DJIFpvTopBaseView ae;
    @c(a = 2131362864)
    private DJIAttitudeView af;
    @c(a = 2131362856)
    private DJIRelativeLayout ag;
    @c(a = 2131362859)
    private ViewStub ah;
    @c(a = 2131362870)
    private DJITextView ai;
    @c(a = 2131362850)
    private DJIGridLine aj;
    @c(a = 2131362861)
    private DJIErrorPopView ak;
    @c(a = 2131362862)
    private ViewStub al;
    @c(a = 2131362847)
    private DJIRelativeLayout am;
    @c(a = 2131362852)
    private DJIImageView an;
    @c(a = 2131362853)
    private DJIImageView ao;
    @c(a = 2131362905)
    private DJITextView ap;
    private DJIPlayBackView aq = null;
    @c(a = 2131362869)
    private DJILeftBar ar;
    @c(a = 2131362867)
    private DJICameraSettingBaseView as;
    @c(a = 2131362868)
    private DJICameraFnView at;
    @c(a = 2131362877)
    private DJICameraChartView au;
    @c(a = 2131362866)
    private DJIFocusRingView av;
    @c(a = 2131362854)
    private DJIFocusAreaView aw;
    @c(a = 2131362151)
    private DJIFocusDistanceView ax;
    @c(a = 2131363210)
    private DJIZoomFocusView ay;
    @c(a = 2131362625)
    private DJISwitchModeView az;
    @c(a = 2131362921)
    protected DJIImageView b;
    private int[] bA = new int[]{0, 0};
    private int[] bB = new int[]{0, 0};
    private int[] bC = new int[]{0, 0};
    private int[] bD = new int[]{0, 0};
    private m bE;
    private g bF;
    private dji.midware.media.h.b.b bH = null;
    private RecordReceiver bI = null;
    private j bJ = null;
    private d bK = new 1(this);
    private Runnable bL = new 23(this);
    private GestureDetector bM;
    private dji.pilot.fpv.leftmenu.b bN = null;
    private dji.pilot.fpv.leftmenu.b bO;
    private ThermometricType bP = ThermometricType.d;
    private boolean bQ = false;
    private Runnable bR = new 18(this);
    private Runnable bS = new 19(this);
    private dji.pilot.fpv.leftmenu.b bT;
    private int bU = 5;
    private int bW = 0;
    private boolean bX = false;
    private boolean bY = false;
    private int bZ;
    private int ba = 0;
    private Timer bb;
    private q bd;
    private boolean be = false;
    private boolean bf = false;
    private TRIPOD_STATUS bg = TRIPOD_STATUS.UNKNOWN;
    private long bh = 0;
    private FLIGHT_ACTION bi = null;
    private MotorStartFailedCause bj = MotorStartFailedCause.None;
    private boolean bk = false;
    private FLYC_STATE bl = FLYC_STATE.OTHER;
    private boolean bm = false;
    private boolean bn = false;
    private int bo = -1;
    private boolean bp = false;
    private Animation bq = null;
    private dji.pilot.publics.widget.b br;
    private dji.pilot.publics.widget.f bs = null;
    private FLYC_STATE bt = FLYC_STATE.OTHER;
    private dji.pilot.fpv.leftmenu.b bx = null;
    private int by = 8192;
    private dji.pilot.fpv.control.d bz = null;
    @c(a = 2131362908)
    protected DJIJoyStickView c;
    private i cA = null;
    private j cB = null;
    private boolean cC = false;
    private dji.pilot.fpv.leftmenu.b ca;
    private MODE cc;
    private volatile CameraType cd = CameraType.OTHER;
    private RatioType ce = RatioType.R_4_3;
    private RatioType cf = RatioType.R_4_3;
    private RatioType cg = RatioType.R_4_3;
    private volatile int ch = -1;
    private volatile ExposureMode ci = ExposureMode.OTHER;
    private boolean cj = true;
    private volatile int ck = -1;
    private int cl = 0;
    private n cm = null;
    private int cn = 0;
    private dji.pilot.publics.widget.b co;
    private SdModeView cp;
    private DataBaseCameraSetting cq = new DataBaseCameraSetting();
    private boolean cr = false;
    private OnGestureListener cs = new 30(this);
    private MotionEvent ct = null;
    private boolean cv = false;
    private long cw = 0;
    private long cx = 0;
    private long cy = 0;
    private f cz = null;
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
    int[] o;
    int[] p;
    int q = -1;
    public boolean r = false;
    AnimationListener s;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(this.TAG, "onCreate");
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(this);
        instance.a(this.aX);
        instance.a(this.bd);
        dji.pilot.dji_groundstation.controller.a.getInstance(this);
        setContentView(R.layout.fpv_litchi);
        getWindow().addFlags(128);
        this.aX.a(bundle, this.ab);
        dji.gs.utils.a.a = DJIGenSettingDataManager.getInstance().s();
        this.bE = new m(this.ab);
        q();
        r();
        ag();
        o();
        this.f = getResources().getDimensionPixelSize(R.dimen.pv);
        this.e = screenWidth / this.g;
        this.d = screenHeight / this.h;
        if (!getIntent().getBooleanExtra(dji.pilot.c.b.a, true)) {
            this.bd.b();
        }
        b(true);
        this.bF = new g(this);
        bG = this;
        dji.midware.data.manager.P3.g.getInstance().a((int) HorizonalSegmentView.N);
        dji.pilot.c.a.n = true;
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        }
        this.j = new i(this);
        this.j.b();
        m();
        b();
        this.b.setOnClickListener(this);
        dji.pilot.flyunlimit.b.getInstance(this);
        DJIRedundancySysController.getInstance(getApplicationContext());
        if (dji.pilot.fpv.d.b.n()) {
            this.aR.sendEmptyMessageDelayed(K, ToolTipPopup.a);
        }
    }

    private void m() {
        this.bI = new RecordReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.record");
        try {
            registerReceiver(this.bI, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void n() {
        if (!dji.pilot.fpv.model.b.e(this) && !ServiceManager.getInstance().isConnected()) {
            Toast.makeText(this, getString(R.string.str_usd_unplugged), 1).show();
        }
    }

    public boolean a() {
        return (this.ae != null && this.ae.hasDlgShowing()) || (!(this.bd == null || this.bd.c()) || (this.aq != null && this.aq.isShown()));
    }

    private void b(boolean z) {
        if (!ServiceManager.getInstance().isRemoteOK()) {
            this.ae.hideCheckListDlg();
            if (!z) {
            }
        } else if (d.h() && !this.mGuideShowing && !DataOsdGetPushCommon.getInstance().isMotorUp()) {
            if (this.aq == null || !this.aq.isShown()) {
                this.ae.showCheckListDlg();
                d.i();
            }
        }
    }

    private void o() {
        if (ServiceManager.getInstance().isConnected() && !dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            int metering = DataCameraGetPushShotParams.getInstance().getMetering();
            DJILogHelper.getInstance().LOGD(this.TAG, "测光=" + metering);
            if (metering == 2) {
                al();
            } else if (metering == 0) {
                this.aR.sendEmptyMessage(z);
                am();
            }
        }
    }

    private void p() {
        this.ai.show();
        this.bb = new Timer();
        this.bb.schedule(new 12(this), 0, 1000);
    }

    private void q() {
        this.bh = System.currentTimeMillis();
        this.aT = new dji.pilot.fpv.control.a(this, this.ab);
        this.aT.a();
        this.aW = new 34(this);
        this.aV = new 41(this);
        this.aR = new a(this);
        this.aY = AnimationUtils.loadAnimation(this, R.anim.bt);
        this.aZ = AnimationUtils.loadAnimation(this, R.anim.bu);
        DJIGenSettingDataManager.getInstance().a(this.aV);
        this.bz = new dji.pilot.fpv.control.d(this);
        this.cm = new n(this);
        this.bJ = new j(this);
        this.i = new e(this, screenHeight);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void r() {
        this.ac.setSurfaceTextureListener(this);
        int[] rules = ((LayoutParams) findViewById(R.id.a2i).getLayoutParams()).getRules();
        if (rules != null && rules.length >= 11) {
            this.bp = rules[11] == -1;
        }
        this.ak.dispatchOnCreate();
        this.af.dispatchOnCreate();
        this.af.setGsOnRight(this.bp);
        this.ae.dispatchOnCreate();
        this.ae.setOnEventListener(this.aW);
        this.aL.dispatchOnCreate();
        this.av.dispatchOnCreate();
        this.ax.dispatchOnCreate();
        this.ar.dispatchOnCreate();
        this.ar.setMutexView(this.ak);
        this.aS = new dji.pilot.fpv.control.c(this.ag, (DJICameraAnimView) findViewById(R.id.a1z), new 42(this));
        this.aS.a(dji.pilot.fpv.camera.more.a.getInstance());
        V();
        Y();
        W();
        X();
        this.bd = new q(this, this.ab, this.bp);
        this.bd.a(this.aX);
        this.bd.a(new 43(this));
        this.bM = new GestureDetector(this, this.cs);
        this.bM.setIsLongpressEnabled(true);
        this.am.setOnTouchListener(new 44(this));
        this.as.setOnVisibilityChangeListener(new 45(this));
        this.at.setOnVisibilityChangeListener(new 2(this));
        this.au.dispatchOnCreate();
        this.ao.setOnClickListener(this);
        if (!aq()) {
            this.aS.c();
            this.aL.hide();
        }
        CameraType cameraType = CameraType.OTHER;
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (cameraType == CameraType.DJICameraTypeFC550Raw && this.aN == null) {
            this.aN = (DJICameraOsdX5RView) ((ViewStub) findViewById(R.id.a3l)).inflate();
        }
    }

    protected void a(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 2) {
            if (dji.pilot.fpv.d.b.r()) {
                this.i.a(motionEvent);
            } else {
                a(getApplicationContext());
            }
        } else if (motionEvent.getPointerCount() == 1 && this.bE.c) {
            this.bE.b(motionEvent);
            this.bE.a(motionEvent.getX() - this.ct.getX(), motionEvent.getY() - this.ct.getY());
        }
    }

    public static void a(Context context) {
        if (DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC350 && DataCameraGetPushStateInfo.getInstance().getVerstion() >= 4 && dji.pilot.fpv.camera.a.a.a()) {
            DJIErrorPopView.b bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.b = R.string.unsupport_dzoom_4k;
            dji.thirdparty.a.c.a().e(bVar);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a21:
                s();
                return;
            case R.id.a3v:
                i();
                return;
            default:
                return;
        }
    }

    private void s() {
        this.aR.sendMessage(this.aR.obtainMessage(32768, 0, 0));
        al();
    }

    public void onClickBackground(View view) {
    }

    private void t() {
        TRIPOD_STATUS deformStatus = DataFlycGetPushDeformStatus.getInstance().getDeformStatus();
        if (deformStatus != TRIPOD_STATUS.UNKNOWN && this.bg != deformStatus && dji.pilot.fpv.d.b.g(null)) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.bg != TRIPOD_STATUS.UNKNOWN && currentTimeMillis - this.bh >= 8000) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.b = dji.pilot.fpv.d.b.a(deformStatus);
                bVar.a = DJIErrorPopView.d.NOTIFY;
                dji.thirdparty.a.c.a().e(bVar);
            }
            this.bg = deformStatus;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void u() {
        /*
        r8 = this;
        r7 = 1;
        r6 = 8;
        r5 = 0;
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.isSwaveWork();
        r1 = r8.bf;
        if (r1 == r0) goto L_0x0014;
    L_0x0010:
        r8.bf = r0;
        if (r0 == 0) goto L_0x0014;
    L_0x0014:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.getFlightAction();
        r1 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r1 = r1.getFlycState();
        r2 = r8.bl;
        if (r2 == r1) goto L_0x0056;
    L_0x0028:
        r8.bl = r1;
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
        if (r2 != 0) goto L_0x0192;
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
        r1 = r8.bi;
        if (r1 == r0) goto L_0x0080;
    L_0x005a:
        if (r0 == 0) goto L_0x0080;
    L_0x005c:
        r8.bi = r0;
        r0 = r8.bi;
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
        r1 = r8.bj;
        if (r1 == r0) goto L_0x0187;
    L_0x008c:
        r8.bj = r0;
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
        r0 = r8.ae;
        if (r0 == 0) goto L_0x00d5;
    L_0x00b8:
        r0 = r8.ae;
        r0 = r0.hasDlgShowing();
        if (r0 != 0) goto L_0x00d5;
    L_0x00c0:
        r0 = r8.isVisible;
        if (r0 == 0) goto L_0x00d5;
    L_0x00c4:
        r0 = r8.aq;
        if (r0 == 0) goto L_0x00d0;
    L_0x00c8:
        r0 = r8.aq;
        r0 = r0.isShown();
        if (r0 != 0) goto L_0x00d5;
    L_0x00d0:
        r0 = r8.ae;
        r0.showCheckListDlg();
    L_0x00d5:
        r0 = r8.bj;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.DeviceLocked;
        if (r0 != r2) goto L_0x011c;
    L_0x00db:
        if (r1 != 0) goto L_0x011c;
    L_0x00dd:
        r0 = r8.unlockDialog;
        if (r0 != 0) goto L_0x010f;
    L_0x00e1:
        r0 = new dji.pilot.publics.widget.b;
        r0.<init>(r8, r5);
        r8.unlockDialog = r0;
        r0 = r8.unlockDialog;
        r0.f();
        r0 = r8.unlockDialog;
        r2 = 2131296512; // 0x7f090100 float:1.8210943E38 double:1.0530003877E-314;
        r0.a(r2);
        r0 = r8.unlockDialog;
        r2 = 2131300423; // 0x7f091047 float:1.8218875E38 double:1.05300232E-314;
        r0.b(r2);
        r0 = r8.unlockDialog;
        r2 = 2131296493; // 0x7f0900ed float:1.8210904E38 double:1.0530003783E-314;
        r0.d(r2);
        r0 = r8.unlockDialog;
        r2 = new dji.pilot.fpv.activity.DJIPreviewActivity$3;
        r2.<init>(r8);
        r0.a(r2);
    L_0x010f:
        r0 = r8.unlockDialog;
        r0 = r0.isShowing();
        if (r0 != 0) goto L_0x011c;
    L_0x0117:
        r0 = r8.unlockDialog;
        r0.show();
    L_0x011c:
        r0 = r8.bj;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.DeviceLocked;
        if (r0 == r2) goto L_0x0128;
    L_0x0122:
        r0 = r8.bj;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.NoviceProtected;
        if (r0 != r2) goto L_0x0187;
    L_0x0128:
        if (r1 == 0) goto L_0x0187;
    L_0x012a:
        r0 = r8.bO;
        if (r0 != 0) goto L_0x0182;
    L_0x012e:
        r0 = new dji.pilot.fpv.leftmenu.b;
        r0.<init>(r8);
        r8.bO = r0;
        r0 = r8.bO;
        r0.a(r7);
        r0 = r8.bO;
        r1 = new dji.pilot.fpv.activity.DJIPreviewActivity$4;
        r1.<init>(r8);
        r0.a(r1);
        r0 = r8.bO;
        r0.d(r6);
        r0 = r8.bO;
        r0 = r0.a(r6, r5);
        r0.e(r6);
        r0 = r8.bO;
        r1 = "";
        r0.a(r6, r1);
        r0 = r8.bO;
        r1 = 2131296822; // 0x7f090236 float:1.8211572E38 double:1.053000541E-314;
        r1 = r8.getString(r1);
        r0.a(r1);
        r0 = r8.bO;
        r1 = 2131296820; // 0x7f090234 float:1.8211567E38 double:1.05300054E-314;
        r1 = r8.getString(r1);
        r0.b(r1);
        r0 = r8.bO;
        r1 = r8.getResources();
        r2 = 2131427846; // 0x7f0b0206 float:1.847732E38 double:1.0530652753E-314;
        r1 = r1.getDimension(r2);
        r1 = (int) r1;
        r0.c(r1);
    L_0x0182:
        r0 = r8.bO;
        r0.show();
    L_0x0187:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.getFlycVersion();
        r8.bo = r0;
        return;
    L_0x0192:
        r2 = dji.pilot.fpv.view.DJIErrorPopView.d.WARNING;
        r3.a = r2;
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.fpv.activity.DJIPreviewActivity.u():void");
    }

    private void v() {
        if (this.bN == null) {
            this.bN = new dji.pilot.fpv.leftmenu.b(this);
            this.bN.a(1);
            this.bN.a(getString(R.string.fpv_cancel_gohome_title));
            this.bN.b(R.drawable.leftmenu_dlg_gohome);
            this.bN.b(getString(R.string.fpv_cancel_gohome_desc));
            this.bN.a(8, 0);
            this.bN.e(8);
            this.bN.d(8);
            this.bN.c(getString(R.string.fpv_before_cancel_gohome));
            this.bN.a(new 5(this));
        }
        if (!this.bN.isShowing()) {
            this.bN.show();
            w();
        }
    }

    private void w() {
        if (this.bN != null && this.bN.isShowing()) {
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
            this.bN.a(0, getString(R.string.fpv_cancel_gohome_littletitle, new Object[]{Integer.valueOf(DataFlycGetPushSmartBattery.getInstance().getBattery()), Float.valueOf((((float) i2) * 1.0f) / 1000.0f)}));
        }
    }

    private void x() {
        if (this.bN != null && this.bN.isShowing()) {
            this.bN.dismiss();
            this.bN = null;
        }
    }

    private void y() {
        SmartGoHomeStatus goHomeStatus = DataFlycGetPushSmartBattery.getInstance().getGoHomeStatus();
        if (goHomeStatus == SmartGoHomeStatus.NON_GOHOME && DataOsdGetPushCommon.getInstance().isMotorUp()) {
            if (this.bx == null || !this.bx.isShowing()) {
                if (DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown() > 0) {
                    b(8193);
                }
            } else if (this.bx.isShowing() && 8193 == this.by) {
                a(false, DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown());
            }
        } else if ((goHomeStatus == SmartGoHomeStatus.GOHOME_ALREADY || goHomeStatus == SmartGoHomeStatus.GOHOME) && 8193 == this.by) {
            E();
        }
        if (!cb) {
            int status = DataFlycGetPushSmartBattery.getInstance().getStatus();
            if (status != this.bZ) {
                this.bZ = status;
                if ((this.bZ & 2048) == 2048) {
                    if (this.ca == null) {
                        this.ca = new dji.pilot.fpv.leftmenu.b(this);
                        this.ca.a(1);
                        this.ca.a(new 6(this));
                        this.ca.d(8);
                        this.ca.a(8, 0).e(8);
                        this.ca.a(8, "");
                        this.ca.a(false);
                        this.ca.b(getString(R.string.battery_first_charge_not_full));
                    }
                    this.ca.show();
                    cb = true;
                }
            }
        }
    }

    private void z() {
        boolean isBeginnerMode = DataOsdGetPushHome.getInstance().isBeginnerMode();
        if (this.bk != isBeginnerMode) {
            this.bk = isBeginnerMode;
            if (!this.bk) {
                dji.pilot.fpv.d.b.a(2, true);
            } else if (dji.pilot.fpv.d.b.a(2)) {
                dji.pilot.fpv.d.b.a(2, false);
                this.ae.hideDialog();
                b(8194);
            }
        }
        isBeginnerMode = DataOsdGetPushHome.getInstance().isReatchLimitHeight();
        boolean isReatchLimitDistance = DataOsdGetPushHome.getInstance().isReatchLimitDistance();
        if (this.bo >= 3) {
            DJIErrorPopView.b bVar;
            if (this.bm != isBeginnerMode) {
                this.bm = isBeginnerMode;
                if (this.bm) {
                    bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.fpv_errorpop_altitude_limit;
                    dji.thirdparty.a.c.a().e(bVar);
                    dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.y);
                }
            }
            if (this.bn != isReatchLimitDistance) {
                this.bn = isReatchLimitDistance;
                if (this.bn) {
                    bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.fpv_errorpop_distance_limit;
                    dji.thirdparty.a.c.a().e(bVar);
                    dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.x);
                }
            }
        }
    }

    private void A() {
        int i = R.string.fpv_height_imperial;
        if (this.bs != null && this.bs.isShowing() && this.bt != FLYC_STATE.GoHome) {
            float height;
            float b;
            if (this.bt == FLYC_STATE.AutoLanding) {
                height = (float) ((int) (((float) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1f));
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    b = (float) ((int) DJIGenSettingDataManager.getInstance().b(height));
                } else {
                    i = R.string.fpv_height_metric;
                    b = height;
                }
                this.bs.a(getString(i, new Object[]{Float.valueOf(b)}));
            } else if (this.bt == FLYC_STATE.AutoTakeoff) {
                height = (float) ((int) (((float) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1f));
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    b = (float) ((int) DJIGenSettingDataManager.getInstance().b(height));
                } else {
                    i = R.string.fpv_height_metric;
                    b = height;
                }
                this.bs.a(getString(i, new Object[]{Float.valueOf(b)}));
            }
        }
    }

    private void B() {
        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.DropGohome).start(new 7(this));
    }

    private void b(int i) {
        if (this.bx == null) {
            this.bx = new dji.pilot.fpv.leftmenu.b(this);
            this.bx.a(new 8(this));
            this.bx.setOnDismissListener(new 9(this));
        }
        if (!dji.pilot.publics.control.a.getInstance().l() && this.bx != null && !this.bx.isShowing()) {
            this.by = i;
            if (i == 8193) {
                a(true, DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown());
            } else if (i == 8194) {
                C();
            }
            this.bx.show();
        }
    }

    private void a(boolean z, int i) {
        this.bx.a(getString(R.string.fpv_before_gohome_title, new Object[]{Integer.valueOf(i)}));
        if (z) {
            this.bx.a(1);
            this.bx.b(R.drawable.leftmenu_dlg_gohome);
            this.bx.a(8, 0);
            this.bx.e(8);
            this.bx.d(0);
            this.bx.b(getString(R.string.fpv_before_gohome_above_desc));
            this.bx.c(getString(R.string.app_cancel));
        }
    }

    private void C() {
        if (!dji.pilot.publics.e.a.d(null)) {
            this.bx.a(4);
            this.bx.a(8, 0);
            this.bx.e(8);
            this.bx.d(8);
            this.bx.a(getString(R.string.fpv_novice_takeoff_title));
            this.bx.b(getString(R.string.fpv_novice_takeoff_desc));
            this.bx.c(getString(R.string.app_isee));
        }
    }

    private void c(int i) {
        if (this.by == 8193) {
            DataFlycSmartAck.getInstance().setAck((byte) 1).start(null);
        }
    }

    private void d(int i) {
        if (this.by == 8193) {
            DataFlycSmartAck.getInstance().setAck((byte) 2).start(null);
        } else if (this.by == 8194) {
            E();
        }
    }

    private void E() {
        if (this.bx != null && this.bx.isShowing()) {
            this.bx.dismiss();
        }
    }

    private void F() {
        dji.pilot.publics.widget.b.a(this, R.string.app_tip, R.string.fpv_adb_debug_tip, R.string.app_cancel, new 10(this), R.string.app_setting, new 11(this)).show();
    }

    private void H() {
        boolean z;
        boolean z2;
        boolean z3 = false;
        boolean z4 = true;
        if ((this.U & 64) != 0) {
            this.U &= -65;
            z = true;
        } else {
            z = false;
        }
        if ((this.U & 32) != 0) {
            t();
            this.U &= -33;
        }
        if ((this.U & 1) != 0) {
            u();
            b(true);
            this.W.d();
            this.U &= -2;
            z2 = true;
        } else {
            z2 = false;
        }
        if ((this.U & 16) != 0) {
            this.U &= -17;
            z2 = true;
        }
        if ((this.U & 4) != 0) {
            this.U &= -5;
        }
        if ((this.U & 2) != 0) {
            if (this.bt == FLYC_STATE.GoHome) {
                A();
            }
            z();
            this.bz.a(DataOsdGetPushHome.getInstance());
            this.W.e();
            this.U &= -3;
            z3 = true;
        }
        if ((this.U & 8) != 0) {
            y();
            this.U &= -9;
        } else {
            z4 = z;
        }
        if (this.bl != FLYC_STATE.GoHome || r1) {
            if (z2) {
                this.af.update(z3);
            }
        } else if (z2) {
            this.af.update(z3);
        }
    }

    public void l() {
        this.af.disconnect();
        if (this.aJ != null) {
            this.aJ.go();
        }
        this.at.hideView();
        this.aF.hideView();
        this.aC.handleThermmometricType(ThermometricType.d);
        this.aC.hideView();
        this.av.hideView();
        this.as.hideView();
        this.aj.go();
        this.aG.go();
        dji.pilot.publics.e.f.a(this.aH, 8);
        this.au.hideChart();
        this.aw.hideView();
        I();
        e(false);
        dji.pilot.battery.a.a.getInstance().d();
        dji.pilot.battery.a.a.getInstance().f();
        DJIGenSettingDataManager.getInstance().f();
        this.cm.b();
        this.bJ.b();
        super.l();
    }

    private void I() {
        this.bi = null;
        this.bg = TRIPOD_STATUS.UNKNOWN;
        this.bf = false;
        this.bt = FLYC_STATE.OTHER;
        this.bj = MotorStartFailedCause.None;
        this.bl = FLYC_STATE.OTHER;
        this.bY = false;
        this.cj = true;
        this.ch = -1;
        this.ci = ExposureMode.OTHER;
        this.bW = 0;
        this.bX = false;
        this.cd = CameraType.OTHER;
        this.bP = ThermometricType.d;
    }

    private void b(boolean z, int i) {
        if (!z) {
        }
    }

    public void onEventMainThread(dji.pilot.fpv.camera.more.c.a aVar) {
        if (dji.pilot.fpv.camera.more.c.a.c == aVar) {
            if (dji.pilot.publics.e.f.a(this.am, this.ac.getBitmap(Bitmap.createBitmap(this.ac.getWidth(), this.ac.getHeight(), Config.RGB_565)), dji.pilot.fpv.camera.more.a.getInstance().aH().a())) {
                Toast.makeText(this, getString(R.string.tau_save_screenshot_completed, new Object[]{dji.pilot.fpv.camera.more.a.getInstance().aH().a()}), 0).show();
            }
        }
    }

    public void onEventMainThread(dji.pilot.fpv.model.n.b bVar) {
        if (bVar == dji.pilot.fpv.model.n.b.a) {
            if (this.ae.getVisibility() == 0) {
                a(false, true);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.b) {
            if (this.ae.getVisibility() == 0) {
                b(false, true);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.e) {
            if (this.ae.getVisibility() == 0) {
                a(false, false);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.f) {
            b(false, false);
        } else if (bVar == dji.pilot.fpv.model.n.b.d && this.ae.getVisibility() == 0) {
            this.W.h();
        }
    }

    protected void a(boolean z, boolean z2) {
        if (z) {
            this.aj.go();
            this.aE.go();
            this.bd.a(false);
            this.aL.setPlayBackViewVisible(true);
            this.ae.hideDialog();
            this.aG.go();
            dji.pilot.publics.e.f.a(this.aH, 8);
        } else {
            this.bd.a(true);
        }
        if (!z2) {
            this.ae.go();
            this.ae.startAnimation(this.aZ);
        }
        if (this.aN != null) {
            this.aN.go();
            if (!z) {
                this.aN.startAnimation(this.aZ);
            }
        }
        this.aS.c();
        this.aF.hideView();
        if (this.aJ != null) {
            this.aJ.go();
        }
        this.at.hideView();
        this.as.hideView();
        e(true);
        this.av.hideView();
        this.ay.go();
        this.aw.hideView();
        this.aC.hideView();
        this.au.hideChart();
        this.ar.hideMenu(true);
        this.aL.go();
        this.af.go();
    }

    private void c(boolean z) {
        a(z, false);
    }

    protected void b(boolean z, boolean z2) {
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
        if (z) {
            this.aL.setPlayBackViewVisible(false);
            this.bd.f();
            V();
            W();
            X();
        } else if (instance == null || !instance.q()) {
            this.aR.postDelayed(new 13(this), 50);
        }
        if (instance == null || !instance.q()) {
            this.ar.showMenu();
            this.af.show();
            a(false);
        }
        if (DJISwitchModeView.a == DJISwitchModeView.a.b) {
            this.aw.showView();
        }
        if (this.bd.c()) {
            if (aq()) {
                this.aL.show();
                this.au.showChart();
            }
            b();
            U();
            P();
            O();
            R();
            Q();
        }
        if (!z2) {
            this.ae.show();
            this.ae.startAnimation(this.aY);
        }
        if (this.aN != null) {
            this.aN.show();
            this.aN.startAnimation(this.aY);
        }
        if (this.bd.c()) {
            if ((instance == null || !instance.p()) && aq()) {
                this.aS.b();
            }
            if (dji.pilot.c.d.f == 1) {
                this.W.g();
            }
        }
    }

    private void d(boolean z) {
        b(z, false);
    }

    private void J() {
        if (this.bH != null) {
            this.bH.a(false, 0);
        }
        if (dji.pilot.publics.e.c.a(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            com.dji.frame.c.b.a((Context) this, DJIPlayBackActivity.class);
        } else if (this.aq == null) {
            c(true);
            this.aU = new 14(this);
            this.aq = (DJIPlayBackView) this.ah.inflate();
            this.aq.setOnFullScreenListener(this.aU);
            this.aq.setCenterHeight(screenHeight);
            this.aq.show();
        } else if (this.aq.getVisibility() != 0) {
            c(true);
            this.aq.show();
        }
    }

    private void K() {
        if (!dji.pilot.publics.e.c.a(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            if (this.aq != null) {
                this.aq.go();
            }
            d(true);
            b(false);
        }
    }

    private void L() {
        this.as.handleCameraSettingClick();
    }

    private boolean M() {
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
        return instance == null || !instance.p();
    }

    public void onEventMainThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        ThermometricType thermometricType = dataCameraGetPushTauParam.getThermometricType();
        if (thermometricType != this.bP) {
            this.aC.handleThermmometricType(thermometricType);
            if (thermometricType == ThermometricType.b) {
                a(false);
            } else {
                this.aC.hideView();
            }
            this.bP = thermometricType;
        }
    }

    public void a(boolean z) {
        if (dji.pilot.fpv.d.b.j(this.cd) && DataCameraGetPushTauParam.getInstance().getThermometricType() == ThermometricType.b) {
            this.an.hide();
            this.ao.hide();
            this.aw.hide();
            if ((this.aq == null || this.aq.getVisibility() != 0) && this.bd.c()) {
                this.aC.showView();
            }
        } else if (z) {
            this.aC.hideView();
        }
    }

    public void onEventMainThread(DJICameraTauSceneView.b bVar) {
        if (!dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            return;
        }
        if (bVar == DJICameraTauSceneView.b.c) {
            if (this.aJ == null) {
                this.aJ = (DJICameraTauSceneView) this.aI.inflate();
                this.aJ.setVisibility(8);
            }
            if ((this.aq == null || this.aq.getVisibility() != 0) && M() && this.bd.c()) {
                this.aJ.show();
            }
        } else if (bVar == DJICameraTauSceneView.b.d) {
            if (this.aJ != null) {
                this.aJ.go();
            }
        } else if (bVar != DJICameraTauSceneView.b.a && bVar == DJICameraTauSceneView.b.b) {
        }
    }

    public void onEventMainThread(DJIGlobalService.a aVar) {
        if (aVar == DJIGlobalService.a.C2LongPress) {
            this.aj.setType(1);
            this.aj.show();
        } else if (aVar == DJIGlobalService.a.C2PressUp) {
            V();
        }
    }

    public void onEventMainThread(DJIMFDemarcateView.a aVar) {
        if (aVar == DJIMFDemarcateView.a.c) {
            U();
            Q();
            P();
            O();
        } else if (aVar == DJIMFDemarcateView.a.b) {
            e(true);
            if (this.aJ != null) {
                this.aJ.go();
            }
            this.at.hideView();
            this.as.hideView();
            this.ax.hideView();
            this.av.hideView();
            this.ay.go();
            if (dji.pilot.fpv.camera.a.a.c()) {
                this.az.switchMode(DJISwitchModeView.a.c);
            } else {
                this.az.switchMode(DJISwitchModeView.a.b);
            }
        } else if (aVar != DJIMFDemarcateView.a.a || !DataCameraGetPushStateInfo.getInstance().isGetted() || !dji.pilot.fpv.d.b.e(DataCameraGetPushStateInfo.getInstance().getCameraType()) || !this.bd.c()) {
        } else {
            if (this.aq == null || (this.aq.getVisibility() != 0 && M())) {
                this.aF.showView();
                this.at.hideView();
                this.as.hideView();
                this.ax.hideView();
                this.av.hideView();
                this.ay.go();
                e(true);
            }
        }
    }

    public void b() {
        if (DataCameraGetPushStateInfo.getInstance().isGetted() && dji.pilot.fpv.d.b.e(DataCameraGetPushStateInfo.getInstance().getCameraType()) && dji.pilot.fpv.camera.more.a.getInstance().f() && this.at.getVisibility() != 0 && this.as.getVisibility() != 0 && M()) {
            onEventMainThread(DJIMFDemarcateView.a.a);
        }
    }

    public void onEventMainThread(dji.pilot.fpv.camera.more.a.a aVar) {
        if (aVar == dji.pilot.fpv.camera.more.a.a.d) {
            onEventMainThread(DJIMFDemarcateView.a.a);
        } else if (aVar == dji.pilot.fpv.camera.more.a.a.e) {
            W();
        } else if (aVar == dji.pilot.fpv.camera.more.a.a.g) {
            X();
        }
    }

    private void N() {
        float f;
        float f2 = 0.0f;
        this.aw.hideView();
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
        this.aw.setPosition(f, f2);
        this.aw.showView();
    }

    public void onEventMainThread(dji.midware.media.h.a.f.a aVar) {
        if (this.bH != null) {
            this.bH.a(aVar.a, aVar.b);
        }
    }

    private void O() {
        if (!DataCameraGetPushStateInfo.getInstance().isGetted() || !dji.pilot.fpv.d.b.a(DataCameraGetPushShotInfo.getInstance().getZoomFocusType(), DataCameraGetPushStateInfo.getInstance().getCameraType()) || !this.bd.c()) {
            return;
        }
        if ((this.aq == null || this.aq.getVisibility() != 0) && M() && this.aF.getVisibility() != 0) {
            this.ay.show();
        }
    }

    public void onEventMainThread(DJIZoomFocusView.a aVar) {
        if (aVar == DJIZoomFocusView.a.a) {
            O();
        }
    }

    private void P() {
        if (!DataCameraGetPushStateInfo.getInstance().isGetted() || !dji.pilot.fpv.d.b.a(DataCameraGetPushStateInfo.getInstance().getCameraType(), DataCameraGetPushShotInfo.getInstance().getZoomFocusType()) || !this.bd.c()) {
            return;
        }
        if ((this.aq == null || this.aq.getVisibility() != 0) && M() && this.aF.getVisibility() != 0) {
            this.ax.showView();
        }
    }

    public void onEventMainThread(DJIFocusDistanceView.a aVar) {
        if (aVar == DJIFocusDistanceView.a.a) {
            P();
        }
    }

    private void Q() {
        Object obj = ((this.aq == null || this.aq.getVisibility() != 0) && this.at.getVisibility() != 0 && this.as.getVisibility() != 0 && M()) ? 1 : null;
        if (obj != null) {
            if (this.bd.c()) {
                this.aA.show();
            }
            this.aB.showCheck();
        }
    }

    private void e(boolean z) {
        this.aA.go();
        Object obj = (this.ae.getVisibility() != 0 || this.at.getVisibility() == 0 || this.as.getVisibility() == 0 || !M()) ? null : 1;
        if (z || obj == null) {
            this.aB.go();
        }
    }

    private void R() {
        if (this.az.needShow()) {
            this.az.show();
        }
    }

    public void onEventMainThread(DJISwitchModeView.b bVar) {
        if (bVar != DJISwitchModeView.b.b && bVar == DJISwitchModeView.b.a) {
            R();
        }
    }

    private void a(DJISwitchModeView.a aVar) {
        if (!dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            if (DJISwitchModeView.a.a == aVar) {
                this.aw.hideView();
                if (this.ci == ExposureMode.OTHER && DataCameraGetPushShotParams.getInstance().isGetted()) {
                    this.ci = DataCameraGetPushShotParams.getInstance().getExposureMode();
                    this.ch = DataCameraGetPushShotParams.getInstance().isAELock() ? 1 : 0;
                }
                if (this.ci != ExposureMode.OTHER && ExposureMode.M != this.ci && this.ch == 0) {
                    int metering = DataCameraGetPushShotParams.getInstance().getMetering();
                    this.an.show();
                    this.ap.show();
                    if (metering == 2) {
                        this.ao.show();
                        return;
                    }
                    this.ao.hide();
                    this.aR.sendMessageDelayed(this.aR.obtainMessage(32768, 0, 0), 2000);
                }
            } else if (DJISwitchModeView.a.b == aVar) {
                this.an.hide();
                this.ap.hide();
                this.ao.hide();
                this.aw.showView();
            } else if (DJISwitchModeView.a.c == aVar) {
                this.an.hide();
                this.ap.hide();
                this.ao.hide();
                this.aw.hideView();
                if (this.aO == null) {
                    T();
                    S();
                }
            }
        }
    }

    private void S() {
        dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.b.cc, Boolean.valueOf(true), new 15(this));
    }

    private void T() {
        this.aO = new ImageView(this);
        this.aO.setImageResource(R.drawable.fpv_tap_zoom);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.leftMargin = 0;
        layoutParams.topMargin = 0;
        this.aO.setLayoutParams(layoutParams);
        this.ab.addView(this.aO);
        this.aO.setVisibility(8);
    }

    public void onEventMainThread(DJISwitchModeView.a aVar) {
        a(aVar);
    }

    private void U() {
        if (!DataCameraGetPushStateInfo.getInstance().isGetted() || !dji.pilot.fpv.d.b.e(DataCameraGetPushStateInfo.getInstance().getCameraType()) || !this.bd.c()) {
            return;
        }
        if ((this.aq == null || this.aq.getVisibility() != 0) && this.at.getVisibility() != 0 && this.as.getVisibility() != 0 && this.aF.getVisibility() != 0 && M()) {
            this.av.showView();
        }
    }

    public void onEventMainThread(DJIFocusRingView.a aVar) {
        if (aVar == DJIFocusRingView.a.b) {
            if (this.aJ != null) {
                this.aJ.go();
            }
            this.at.hideView();
            this.as.hideView();
            a(DJISwitchModeView.a);
        } else if (aVar == DJIFocusRingView.a.a) {
            U();
        }
    }

    private void f(boolean z) {
        this.aS.a(z);
        if (z) {
            if (this.aJ != null) {
                this.aJ.go();
            }
            this.at.hideView();
            this.aL.go();
            this.ay.go();
            this.aF.hideView();
            this.av.hideView();
            e(false);
            String a = dji.pilot.fpv.model.b.a();
            if (!("large".equals(a) || "xlarge".equals(a))) {
                this.af.hideSpeedLy();
            }
        } else {
            if (aq()) {
                this.aL.show();
            }
            this.af.showSpeedLy();
            b();
            Q();
            U();
            P();
            O();
        }
        this.ae.handleCameraWidgetVisibility(z, false);
        if (this.aN != null) {
            this.aN.handleCameraWidgetVisibility(z, false);
        }
    }

    private void g(boolean z) {
        this.aS.b(z);
        if (z) {
            this.as.hideView();
            this.aF.hideView();
            this.av.hideView();
            this.aL.go();
            this.ay.go();
            e(false);
            String a = dji.pilot.fpv.model.b.a();
            if (!("large".equals(a) || "xlarge".equals(a))) {
                this.af.hideSpeedLy();
            }
        } else {
            if (aq()) {
                this.aL.show();
            }
            this.af.showSpeedLy();
            b();
            Q();
            U();
            P();
            O();
            if (this.aJ != null) {
                this.aJ.go();
            }
        }
        this.ae.handleCameraWidgetVisibility(z, false);
        if (this.aN != null) {
            this.aN.handleCameraWidgetVisibility(z, false);
        }
    }

    private void h(boolean z) {
        DJILogHelper.getInstance().LOGD("", "FastCamera visible[" + z + dji.pilot.usercenter.protocol.d.H, false, true);
        if (z) {
            this.bd.a(false);
            this.aL.go();
            this.ae.setVisibleAeLock(false);
            return;
        }
        this.bd.f();
        this.aL.show();
        this.ae.setVisibleAeLock(true);
    }

    private void V() {
        int k = DJIGenSettingDataManager.getInstance().k();
        if (k == 0) {
            this.aj.go();
            return;
        }
        if (k == 1) {
            this.aj.setType(1);
        } else if (k == 2) {
            this.aj.setType(2);
        } else if (k == 3) {
            this.aj.setType(4);
        }
        this.aj.show();
    }

    private void W() {
        if (!ServiceManager.getInstance().isRemoteOK()) {
            return;
        }
        if (dji.pilot.fpv.camera.more.a.getInstance().s() == 0) {
            this.aG.go();
        } else {
            this.aG.show();
        }
    }

    private void X() {
        if (!dji.pilot.fpv.d.b.i(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            return;
        }
        if (dji.pilot.fpv.camera.more.a.getInstance().u() == 0) {
            dji.pilot.publics.e.f.a(this.aH, 8);
        } else {
            dji.pilot.publics.e.f.a(this.aH, 0);
        }
    }

    private void Y() {
        if (this.aD == null) {
            this.aD = (DJIGimbalRollFineTuneView) this.al.inflate();
        }
        if (DJIGenSettingDataManager.getInstance().o()) {
            this.aD.show();
        } else {
            this.aD.go();
        }
    }

    private void Z() {
    }

    private void aa() {
        this.aX.h();
    }

    private void ab() {
        this.aX.i();
    }

    public void c() {
        if (this.br == null) {
            this.br = new dji.pilot.publics.widget.b(this);
            this.br.a(R.string.app_warning).b(R.string.liveshare_base_exit).d(R.string.app_enter).e(R.string.app_cancel).a(new 17(this)).b(new 16(this));
        }
        this.br.show();
    }

    public void finishThis() {
        if (this.aq == null || this.aq.getVisibility() != 0) {
            this.aR.removeMessages(8192);
            this.aR.removeMessages(4096);
            this.aR.removeMessages(16384);
            this.aR.removeMessages(24576);
            this.aT.b();
            dji.thirdparty.a.c.a().e(DJIHubActivity.a.b);
            ac();
            dji.pilot.c.a.n = false;
            this.aS.a();
            ad();
            finish();
            overridePendingTransition(0, 0);
        } else if (ServiceManager.getInstance().isConnected()) {
            DataSpecialControl.getInstance().setPlayBackType(false).start(20);
        } else {
            K();
        }
    }

    private void ac() {
        if (this.aQ != null) {
            this.aQ.a(null);
            this.aQ.b();
            this.aQ = null;
        }
        if (this.bH != null) {
            this.bH.c();
            this.bH = null;
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.aX.a(bundle);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.aX.d();
    }

    private void ad() {
        if (this.bF != null) {
            if (this.aq != null && this.aq.getVisibility() == 0) {
                this.aq.go();
            }
            if (this.a != null) {
                this.a.destroy();
            }
            this.bF.a();
            ah();
            DJIGenSettingDataManager.getInstance().b(this.aV);
            this.bd.a();
            this.af.dispatchOnDestroy();
            this.ae.dispatchOnDestroy();
            this.ak.dispatchOnDestroy();
            this.aL.dispatchOnDestroy();
            this.ar.dispatchOnDestroy();
            this.at.hideView();
            this.aF.hideView();
            this.av.dispatchOnDestroy();
            this.ax.dispatchOnDestroy();
            this.au.dispatchOnDestroy();
            if (this.aJ != null) {
                this.aJ.go();
            }
            this.aC.hideView();
            this.j.a();
            this.bF = null;
            unregisterReceiver(this.bI);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.aX.e();
        ap();
        FpvPopWarnView.dispatchOnDestroy();
        Log.d(this.TAG, "onDestroy");
    }

    protected void onResume() {
        super.onResume();
        this.aX.b();
    }

    protected void onPause() {
        super.onPause();
        this.aX.c();
    }

    protected void onStart() {
        super.onStart();
        this.be = true;
        if (this.U != 0) {
            this.aR.sendEmptyMessageDelayed(4096, 200);
        }
        Log.d(this.TAG, "onStart" + System.currentTimeMillis());
        dji.pilot.fpv.d.e.b((Context) this);
        ServiceManager.getInstance().pauseService(false);
        dji.pilot.groundStation.a.a.a(this);
        dji.pilot.groundStation.a.a.getInstance(this).a(this.aX);
        dji.pilot.groundStation.a.a.getInstance(this).a(this.bd);
        dji.pilot.groundStation.a.a.getInstance(this).c(true);
        dji.pilot.dji_groundstation.controller.a.getInstance(this);
        dji.pilot.flyunlimit.b.getInstance(this).a(this.aX);
        dji.pilot.flyforbid.a.getInstance(this).c((Context) this);
    }

    protected void onStop() {
        this.be = false;
        Log.d(this.TAG, "onStop");
        super.onStop();
        dji.pilot.groundStation.a.a.getInstance(this).c(false);
        dji.pilot.groundStation.a.a.getInstance(this).b();
        dji.pilot.fpv.d.e.c((Context) this);
        dji.pilot.flyunlimit.b.getInstance(this).a(null);
        dji.pilot.flyforbid.a.getInstance(this).f();
    }

    public void onBackPressed() {
        if (dji.pilot.liveshare.b.getInstance().isRunning() || dji.pilot.liveshare.b.getInstance().isLaunch()) {
            c();
        } else {
            finishThis();
        }
    }

    public void oneFrameComeIn() {
        this.ba++;
    }

    public void resetVideoSurface(int i, int i2) {
        this.aR.sendMessage(this.aR.obtainMessage(12288, i, i2));
    }

    private void a(int i, int i2) {
        DJILogHelper.getInstance().LOGD(this.TAG, "videoWidth=" + i + "  videoHeight=" + i2, false, true);
        ae();
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
            this.bC[0] = i4;
            this.bC[1] = DJIBaseActivity.screenWidth - i4;
            this.bD[0] = i5;
            this.bD[1] = DJIBaseActivity.screenHeight - i5;
        } else {
            i = iArr[1] - iArr[0];
            i2 = iArr2[1] - iArr2[0];
            System.arraycopy(iArr, 0, this.bC, 0, 2);
            System.arraycopy(iArr2, 0, this.bD, 0, 2);
        }
        ViewGroup.LayoutParams layoutParams = this.aK.getLayoutParams();
        layoutParams.width = iArr[1] - iArr[0];
        layoutParams.height = iArr2[1] - iArr2[0];
        this.aK.setLayoutParams(layoutParams);
        layoutParams = this.aE.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        this.aE.setLayoutParams(layoutParams);
        layoutParams = this.aH.getLayoutParams();
        layoutParams.width = iArr[1] - iArr[0];
        layoutParams.height = iArr2[1] - iArr2[0];
        this.aH.setLayoutParams(layoutParams);
        this.aw.setHVLimits(iArr, iArr2);
        this.aC.setHVLimits(iArr, iArr2);
        this.o = iArr;
        this.p = iArr2;
    }

    private void ae() {
        boolean z;
        int i = DJIVideoDecoder.width;
        int i2 = DJIVideoDecoder.height;
        if (Math.abs(DJIBaseActivity.screenRatio - dji.midware.util.a.b.a) < Math.abs(DJIBaseActivity.screenRatio - dji.midware.util.a.b.b)) {
            this.ce = RatioType.R_16_9;
        } else {
            this.ce = RatioType.R_4_3;
        }
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if (c == ProductType.litchiC || c == ProductType.litchiS || dji.pilot.fpv.camera.a.a.a(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            z = false;
        } else {
            z = true;
        }
        DJILogHelper.getInstance().LOGD(this.TAG, "isLong=" + z + " productType=" + c, false, true);
        DJILogHelper.getInstance().LOGD(this.TAG, "ratioType=" + this.cg, false, true);
        DJILogHelper.getInstance().LOGD(this.TAG, "screenRatioType=" + this.ce, false, true);
        if ((this.cg == RatioType.R_4_3 && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) || dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            this.cf = RatioType.R_4_3;
        } else {
            this.cf = RatioType.R_16_9;
        }
        DJILogHelper.getInstance().LOGD(this.TAG, "videoRatioType=" + this.cf, false, true);
        LayoutParams layoutParams = (LayoutParams) this.ac.getLayoutParams();
        int i3;
        if (z) {
            if (this.ce == RatioType.R_16_9) {
                layoutParams.width = DJIBaseActivity.screenWidth;
                layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
            } else {
                layoutParams.width = DJIBaseActivity.screenWidth;
                layoutParams.height = (int) (((1.0f * ((float) i2)) / ((float) i)) * ((float) DJIBaseActivity.screenWidth));
            }
            this.ac.setLayoutParams(layoutParams);
            this.ad.setLayoutParams(layoutParams);
            if (this.ce == RatioType.R_4_3) {
                float f;
                if (this.cf == RatioType.R_4_3) {
                    f = dji.midware.util.a.b.b;
                    this.ac.setScaleX(dji.midware.util.a.b.b);
                    this.ad.setScaleX(dji.midware.util.a.b.b);
                    layoutParams.width = DJIBaseActivity.screenWidth;
                    layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.b);
                } else {
                    f = dji.midware.util.a.b.a;
                    this.ac.setScaleX(1.0f);
                    this.ad.setScaleX(1.0f);
                    layoutParams.width = DJIBaseActivity.screenWidth;
                    layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                }
                this.bA[0] = 0;
                this.bA[1] = layoutParams.width;
                i3 = (DJIBaseActivity.screenHeight - layoutParams.height) / 2;
                this.bB[0] = i3;
                this.bB[1] = DJIBaseActivity.screenHeight - i3;
                a(this.bA, this.bB, layoutParams.width, layoutParams.height, 0, f, 1.0f, true);
                af();
                this.aj.setLayoutParams(layoutParams);
                return;
            }
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(0, 0);
            layoutParams2.addRule(13, -1);
            if (this.cf == RatioType.R_4_3) {
                layoutParams2.width = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.b);
                layoutParams2.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                i3 = (DJIBaseActivity.screenWidth - layoutParams2.width) / 2;
                this.bA[0] = i3;
                this.bA[1] = DJIBaseActivity.screenWidth - i3;
                i3 = (DJIBaseActivity.screenHeight - layoutParams2.height) / 2;
                this.bB[0] = i3;
                this.bB[1] = DJIBaseActivity.screenHeight - i3;
                a(this.bA, this.bB, DJIBaseActivity.screenWidth, DJIBaseActivity.screenWidth, 2, dji.midware.util.a.b.b, dji.midware.util.a.b.a, false);
                af();
            } else {
                layoutParams2.width = DJIBaseActivity.screenWidth;
                layoutParams2.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                this.bA[0] = 0;
                this.bA[1] = layoutParams2.width;
                i3 = (DJIBaseActivity.screenHeight - layoutParams2.height) / 2;
                this.bB[0] = i3;
                this.bB[1] = DJIBaseActivity.screenHeight - i3;
                a(this.bA, this.bB, layoutParams.width, layoutParams.height, 0, dji.midware.util.a.b.a, 1.0f, true);
                af();
            }
            this.aj.setLayoutParams(layoutParams2);
            return;
        }
        if (this.cf == RatioType.R_16_9) {
            layoutParams.width = DJIBaseActivity.screenWidth;
            layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
            this.bA[0] = 0;
            this.bA[1] = layoutParams.width;
            i3 = (DJIBaseActivity.screenHeight - layoutParams.height) / 2;
            this.bB[0] = i3;
            this.bB[1] = DJIBaseActivity.screenHeight - i3;
            a(this.bA, this.bB, layoutParams.width, layoutParams.height, 0, dji.midware.util.a.b.a, 1.0f, true);
            af();
        } else {
            layoutParams.width = (int) (((float) DJIBaseActivity.screenHeight) * dji.midware.util.a.b.b);
            layoutParams.height = DJIBaseActivity.screenHeight;
            i3 = (DJIBaseActivity.screenWidth - layoutParams.width) / 2;
            this.bA[0] = i3;
            this.bA[1] = DJIBaseActivity.screenWidth - i3;
            this.bB[0] = 0;
            this.bB[1] = layoutParams.height;
            a(this.bA, this.bB, DJIBaseActivity.screenHeight, DJIBaseActivity.screenHeight, 1, dji.midware.util.a.b.b, dji.midware.util.a.b.b, this.ce == RatioType.R_4_3);
            af();
        }
        this.ac.setLayoutParams(layoutParams);
        this.ad.setLayoutParams(layoutParams);
        this.aj.setLayoutParams(layoutParams);
    }

    private void af() {
        this.e = (this.bA[1] - this.bA[0]) / this.g;
        this.d = (this.bB[1] - this.bB[0]) / this.h;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        DJILogHelper.getInstance().LOGD(this.TAG, "onSurfaceTextureAvailable mVideoDecoder", false, true);
        this.bH = dji.midware.media.h.e.a(getClass());
        this.bH.a(surfaceTexture, i, i2);
        this.bH.a(DJIGenSettingDataManager.getInstance().F(), R.raw.overexposure);
        if (this.aQ == null) {
            a(this.bH);
        } else {
            this.aQ.a(this.bH);
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.bH != null) {
            this.bH.a(i, i2);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.aQ != null) {
            this.aQ.a(null);
        }
        if (this.bH != null) {
            this.bH.c();
            this.bH = null;
        }
        DJILogHelper.getInstance().LOGD(this.TAG, "onSurfaceTextureDestroyed");
        return false;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    private void a(dji.midware.media.h.b.b bVar) {
        this.aQ = new f(this, bVar);
        this.aQ.a(this);
        FPVController.native_setDecodeMode(dji.pilot.publics.objects.g.b((Context) this, "DecodeMode", false));
    }

    private void ag() {
        dji.thirdparty.a.c.a().a((Object) this);
    }

    private void ah() {
        dji.thirdparty.a.c.a().d((Object) this);
    }

    public void onEventMainThread(DJIPlayBackActivity.a aVar) {
        switch (40.a[aVar.ordinal()]) {
            case 1:
                if (this.aQ != null) {
                    DJILogHelper.getInstance().LOGD(this.TAG, "mVideoDecoder resetToManager", false, true);
                    this.aQ.a();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(ProductType productType) {
        CameraType cameraType = CameraType.OTHER;
        DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
        if (instance.isGetted()) {
            cameraType = instance.getCameraType();
        }
        if (cameraType == CameraType.DJICameraTypeFC550Raw) {
            if (this.aN == null) {
                this.aN = (DJICameraOsdX5RView) ((ViewStub) findViewById(R.id.a3l)).inflate();
            } else {
                this.aN.show();
            }
        } else if (this.aN != null) {
            this.aN.go();
        }
        if (productType != ProductType.Orange && productType != ProductType.N1 && productType != ProductType.BigBanana && productType != ProductType.Olives && productType != ProductType.OrangeRAW && !dji.pilot.publics.e.a.d(productType) && productType != ProductType.Grape2 && productType != ProductType.A3 && productType != ProductType.N3 && productType != ProductType.OrangeCV600) {
            if (dji.pilot.liveshare.b.getInstance().isLaunch() || dji.pilot.liveshare.b.getInstance().isRunning()) {
                c();
            } else {
                finishThis();
            }
        }
    }

    private void ai() {
        DJILogHelper.getInstance().LOGD(this.TAG, "showNoVideoGoHomeDialog isRemoteOK=" + ServiceManager.getInstance().isRemoteOK(), false, true);
        if (ServiceManager.getInstance().isRemoteOK()) {
        }
        if (this.bT == null) {
            this.bT = new dji.pilot.fpv.leftmenu.b(this);
            this.bT.a(1);
            this.bT.a(new 20(this));
            this.bT.d(getString(R.string.disconnect_gohome_alert_gohome_button));
            this.bT.d(0);
            this.bT.a(8, 0).e(8);
            this.bT.a(0, getString(R.string.disconnect_gohome_alert_title2));
        }
        this.bU = 5;
        this.bT.c();
        this.bT.show();
        DJILogHelper.getInstance().LOGD(this.TAG, "showNoVideoGoHomeDialog show", false, true);
        dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.v);
    }

    private void aj() {
        if (this.bT != null && this.bT.isShowing()) {
            if (this.bU == 0) {
                this.bT.dismiss();
                ak();
                return;
            }
            this.bT.b(getString(R.string.disconnect_gohome_alert_title, new Object[]{Integer.valueOf(this.bU)}));
            this.aR.sendEmptyMessageDelayed(E, 1000);
            this.bU--;
        }
    }

    private void ak() {
        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.GOHOME).start(new 21(this));
    }

    public void onEventBackgroundThread(dji.midware.data.manager.P3.m mVar) {
        switch (40.b[mVar.ordinal()]) {
            case 1:
                this.aR.post(this.bR);
                this.aR.removeMessages(D);
                this.aR.removeMessages(E);
                if (this.bT != null && this.bT.isShowing()) {
                    this.bT.dismiss();
                    return;
                }
                return;
            case 2:
                if (this.isVisible) {
                    this.aR.post(this.bS);
                    if (ServiceManager.getInstance().isRemoteOK() && DataOsdGetPushCommon.getInstance().groundOrSky() == 2 && DataCameraGetPushStateInfo.getInstance().getMode() != MODE.PLAYBACK && DataCameraGetPushStateInfo.getInstance().getMode() != MODE.DOWNLOAD) {
                        this.aR.sendEmptyMessageDelayed(D, ToolTipPopup.a);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (40.c[oVar.ordinal()]) {
            case 1:
                o();
                this.bh = System.currentTimeMillis();
                this.aR.removeMessages(16384);
                this.aR.removeMessages(4096);
                this.U = 127;
                this.aR.sendEmptyMessageDelayed(4096, 200);
                this.aR.removeMessages(8192);
                this.aR.sendEmptyMessageDelayed(8192, aa);
                this.aR.sendEmptyMessage(24576);
                this.aR.post(new 22(this));
                return;
            case 2:
                this.cC = false;
                this.cc = null;
                if (this.aq != null) {
                    this.aq.setIsBackPBack(true);
                }
                this.U = 0;
                this.aR.removeMessages(4096);
                this.aR.removeMessages(8192);
                this.aR.sendEmptyMessageDelayed(16384, 200);
                return;
            default:
                return;
        }
    }

    private void e(int i) {
        if (this.be && (this.U & i) == 0) {
            this.U |= i;
            if (!this.aR.hasMessages(4096)) {
                this.aR.sendEmptyMessageDelayed(4096, 200);
            }
        }
    }

    protected void onBackgroundThreadOver(DataOsdGetPushCommon dataOsdGetPushCommon) {
        e(1);
        if (this.be && !this.aR.hasMessages(8192)) {
            this.aR.sendEmptyMessageDelayed(8192, aa);
        }
        this.r = true;
        this.aR.removeMessages(F);
        this.aR.sendEmptyMessageDelayed(F, 1000);
    }

    public void onEventMainThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        if (dji.pilot.fpv.d.b.c(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            int fullCapacity = dataCenterGetPushBatteryCommon.getFullCapacity();
            if (this.bW != fullCapacity) {
                this.bW = fullCapacity;
            }
        }
    }

    public void onEventBackgroundThread(DataBatteryGetPushCheckStatus dataBatteryGetPushCheckStatus) {
        boolean z = dataBatteryGetPushCheckStatus.getFirstOverheatStatus() || dataBatteryGetPushCheckStatus.getSecondOverheatStatus();
        if (this.bX != z) {
            this.bX = z;
            if (z) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.b = R.string.fpv_battery_high_temp;
                bVar.a = DJIErrorPopView.d.NOTIFY;
                bVar.f = DJIErrorPopView.c.AUTODISAPPEAR;
                dji.thirdparty.a.c.a().e(bVar);
            }
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        e(2);
        boolean z = dataOsdGetPushHome.getHeight() >= 3000.0f;
        if (z != this.bY) {
            this.bY = z;
            if (z) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.b = R.string.fpv_attitude_height_tip;
                bVar.a = DJIErrorPopView.d.WARNING;
                bVar.f = DJIErrorPopView.c.AUTODISAPPEAR;
                dji.thirdparty.a.c.a().e(bVar);
            }
        }
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
        if (mode != this.cc) {
            this.cc = mode;
            this.aR.sendEmptyMessage(12288);
            if (this.cc == MODE.PLAYBACK) {
                this.aR.sendMessage(this.aR.obtainMessage(32768, 0, 0));
            }
            DJILogHelper.getInstance().LOGD(this.TAG, "cameramode=" + this.cc, false, true);
            if (this.cc == MODE.DOWNLOAD && (this.aq == null || this.aq.isBackPBack())) {
                DataCameraSetMode.getInstance().a(MODE.PLAYBACK).start(new 24(this));
            }
            if (this.aq != null) {
                this.aq.setIsBackPBack(false);
            }
            if (this.cc == MODE.NEW_PLAYBACK) {
                DataCameraSetMode.getInstance().a(dji.pilot.c.d.a).start(null);
            }
            if ((this.cc == MODE.TAKEPHOTO || this.cc == MODE.RECORD) && this.bH != null) {
                this.bH.a(DJIGenSettingDataManager.getInstance().F(), R.raw.overexposure);
            }
        }
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (this.cd != cameraType) {
            this.cd = cameraType;
            this.aR.sendEmptyMessageDelayed(I, 0);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int i = dataCameraGetPushShotParams.isAELock() ? 1 : 0;
        ExposureMode exposureMode = dataCameraGetPushShotParams.getExposureMode();
        if (!(this.ch == i && exposureMode == this.ci)) {
            this.ch = i;
            this.ci = exposureMode;
            if (i == 1 || exposureMode == ExposureMode.M) {
                this.aR.sendMessage(this.aR.obtainMessage(32768, 0, 0));
            } else {
                this.aR.sendMessage(this.aR.obtainMessage(32768, 1, 0));
            }
        }
        RatioType imageRatio = DataCameraGetPushShotParams.getInstance().getImageRatio();
        if (imageRatio != this.cg) {
            this.cg = imageRatio;
            this.aR.sendEmptyMessage(12288);
        }
        this.i.a(dataCameraGetPushShotParams);
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        if (dji.pilot.fpv.d.b.c(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            boolean isShotConnected = dataCameraGetPushShotInfo.isShotConnected();
            if (isShotConnected != this.cj) {
                this.cj = isShotConnected;
                if (isShotConnected) {
                    i(false);
                } else {
                    i(true);
                }
            }
            int mFFocusStatus = dataCameraGetPushShotInfo.getMFFocusStatus();
            if (mFFocusStatus != this.ck) {
                this.ck = mFFocusStatus;
                if (mFFocusStatus == 1) {
                    this.aw.hideView();
                    this.an.hide();
                    this.ap.hide();
                    this.ao.hide();
                } else if (this.aq == null || this.aq.getVisibility() != 0) {
                    a(DJISwitchModeView.a);
                }
            }
            FuselageFocusMode fuselageFocusMode = dataCameraGetPushShotInfo.getFuselageFocusMode();
            if (fuselageFocusMode == FuselageFocusMode.OneAuto || fuselageFocusMode == FuselageFocusMode.ContinuousAuto) {
                mFFocusStatus = dataCameraGetPushShotInfo.getFocusStatus();
                if (this.cl != mFFocusStatus) {
                    this.cl = mFFocusStatus;
                    if (mFFocusStatus == 2) {
                        dji.pilot.fpv.camera.more.a.getInstance().C();
                    }
                }
            } else {
                this.cl = 0;
            }
            mFFocusStatus = dataCameraGetPushShotInfo.getFocusWindowStartX();
            int focusWindowStartY = dataCameraGetPushShotInfo.getFocusWindowStartY();
            int focusWindowRealNumX = dataCameraGetPushShotInfo.getFocusWindowRealNumX();
            int focusWindowRealNumY = dataCameraGetPushShotInfo.getFocusWindowRealNumY();
            if (!this.aw.a) {
                return;
            }
            if (this.k != mFFocusStatus || this.l != focusWindowStartY || this.m != focusWindowRealNumX || this.n != focusWindowRealNumY) {
                this.k = dataCameraGetPushShotInfo.getFocusWindowStartX();
                this.l = dataCameraGetPushShotInfo.getFocusWindowStartY();
                this.m = dataCameraGetPushShotInfo.getFocusWindowRealNumX();
                this.n = dataCameraGetPushShotInfo.getFocusWindowRealNumY();
                if (this.aR.hasMessages(H)) {
                    this.aR.removeMessages(H);
                }
                this.aw.a = false;
                this.aR.sendEmptyMessage(H);
            }
        }
    }

    private void i(boolean z) {
        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
        bVar.b = R.string.fpv_shot_nonconnect_tip;
        bVar.a = DJIErrorPopView.d.WARNING;
        bVar.f = DJIErrorPopView.c.STATIC;
        bVar.g = z ? DJIErrorPopView.f.INSERT : DJIErrorPopView.f.REMOVE;
        dji.thirdparty.a.c.a().e(bVar);
    }

    public void onEventBackgroundThread(b.a aVar) {
        if (!dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            switch (40.d[aVar.ordinal()]) {
                case 1:
                    if (this.g == 0) {
                        DataCameraGetMeteringArea.getInstance().start(this.bK);
                        return;
                    }
                    return;
                case 2:
                    al();
                    return;
                default:
                    return;
            }
        }
    }

    public void a(int i) {
        if (i == 0) {
            this.cp.a = SdModeView.a.c;
        } else {
            this.cp.a = SdModeView.a.a;
        }
        DJILogHelper.getInstance().LOGD("", "lose_osd blackMode=" + i, false, true);
        onEventMainThread(this.cp);
    }

    public void onEventMainThread(DataFlycGetPushAvoid dataFlycGetPushAvoid) {
        if (dji.pilot.fpv.d.b.j(null)) {
            this.cm.onEventMainThread(dataFlycGetPushAvoid);
        }
    }

    public void onEventMainThread(DataRcGetPushFollowFocus dataRcGetPushFollowFocus) {
        if (dji.pilot.fpv.d.b.c(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            this.bJ.a(dataRcGetPushFollowFocus);
        }
    }

    public void onEventMainThread(SdModeView sdModeView) {
        this.cp = sdModeView;
        SdModeView.a aVar = sdModeView.a;
        if (this.co == null) {
            this.co = new dji.pilot.publics.widget.b(this, false);
            this.co.a(R.string.app_tip);
            this.co.d(R.string.app_enter);
            this.co.a(new 25(this));
        }
        switch (40.e[aVar.ordinal()]) {
            case 1:
                this.co.b(R.string.mc_switch_sd_mode_success);
                this.co.show();
                sdModeView.setBlackStatus(false);
                dji.thirdparty.a.c.a().e(aVar);
                return;
            case 2:
                this.cn = 0;
                this.aR.sendEmptyMessageDelayed(G, 1000);
                return;
            default:
                this.co.b(R.string.mc_switch_sd_mode_error);
                this.co.show();
                sdModeView.setBlackStatus(false);
                return;
        }
    }

    public void onEventMainThread(DJICustomType dJICustomType) {
        boolean z = true;
        dji.pilot.groundStation.a.a instance;
        switch (40.f[dJICustomType.ordinal()]) {
            case 1:
                if (!this.mGuideShowing && this.bd.h()) {
                    this.bd.b();
                    return;
                }
                return;
            case 2:
                instance = dji.pilot.groundStation.a.a.getInstance(null);
                if (instance == null || !instance.c() || !instance.p()) {
                    if ((this.aq == null || !this.aq.isShown()) && !this.mGuideShowing && this.bd.c() && !this.ae.hasDlgShowing()) {
                        this.as.handleCameraSettingClick();
                        return;
                    }
                    return;
                }
                return;
            case 3:
                instance = dji.pilot.groundStation.a.a.getInstance(null);
                if (instance == null || !instance.c() || !instance.p()) {
                    if ((this.aq == null || !this.aq.isShown()) && !this.mGuideShowing) {
                        this.ae.handleBatteryClickPush();
                        return;
                    }
                    return;
                }
                return;
            case 4:
                DataSpecialControl.getInstance().resetGimbal().start(20);
                return;
            case 5:
                this.ar.switchGimbalMode();
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
                this.aS.c();
                if (this.as.getVisibility() == 0) {
                    this.as.hideView();
                }
                if (this.at.getVisibility() == 0) {
                    this.at.hideView();
                }
                if (this.aJ != null) {
                    this.aJ.go();
                }
                if (this.aL.getVisibility() == 0) {
                    this.aL.go();
                }
                this.aF.hideView();
                this.at.hideView();
                this.as.hideView();
                this.av.hideView();
                this.ay.go();
                e(false);
                dji.pilot.groundStation.a.a instance2 = dji.pilot.groundStation.a.a.getInstance(null);
                if (instance2 == null || !instance2.q()) {
                    this.bd.f();
                    this.af.show();
                    this.ar.showMenu();
                    this.au.showChart();
                    return;
                }
                this.bd.a(true);
                this.af.go();
                this.ar.hideMenu(true);
                this.au.hideChart();
                return;
            case 8:
                if (this.aq == null || !this.aq.isShown()) {
                    if (this.bd.c()) {
                        if (aq()) {
                            this.aS.b();
                            this.aL.show();
                        }
                        b();
                        U();
                        P();
                        O();
                        this.au.showChart();
                    }
                    Q();
                    this.bd.f();
                    this.af.show();
                    this.ar.showMenu();
                    return;
                }
                return;
            case 9:
                if (this.bd.h()) {
                    this.bd.b();
                    return;
                }
                this.bd.g();
                this.bd.b();
                this.bd.a(true);
                return;
            case 10:
                if ((this.aq == null || !this.aq.isShown()) && !this.mGuideShowing && DJISwitchModeView.a == DJISwitchModeView.a.a) {
                    al();
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

    public void onEventMainThread(dji.midware.media.j.g.c cVar) {
        if (dji.pilot.c.a.p) {
            Log.i("DJIPreviewActivity", "received a bus event for bitmap");
            dji.midware.media.j.g.a(D(), cVar.a());
        }
    }

    private void j(boolean z) {
        this.aT.a(z, DataOsdGetPushCommon.getInstance().getVoltageWarning(), DataCenterGetPushBatteryCommon.getInstance().getRelativeCapacity());
    }

    private void al() {
        if (ServiceManager.getInstance().isConnected()) {
            this.aR.sendEmptyMessage(z);
            this.cq.a(dji.midware.data.config.P3.b.a.SetMetering).a(0).start(new 27(this));
        }
    }

    private void am() {
        this.aR.sendMessage(this.aR.obtainMessage(32768, 1, 1));
        this.aR.sendMessageDelayed(this.aR.obtainMessage(32768, 0, 0), 2000);
    }

    private void a(float f, float f2) {
        if (this.e != 0) {
            int i = ((int) ((f - ((float) this.bA[0])) / ((float) this.e))) + (((int) ((f2 - ((float) this.bB[0])) / ((float) this.d))) * this.g);
            this.aR.sendMessage(this.aR.obtainMessage(20480, new PointF(f, f2)));
            DJILogHelper.getInstance().LOGD(this.TAG, "meteringarea index=" + i, false, false);
            DataCameraSetMeteringArea.getInstance().a(i).start(new 28(this));
        }
    }

    private void an() {
        if (this.bq == null) {
            this.bq = new AlphaAnimation(0.4f, 1.0f);
            this.bq.setDuration(aa);
            this.bq.setRepeatCount(3);
            this.bq.setRepeatMode(2);
            this.bq.setAnimationListener(new 29(this));
        }
        this.aE.show();
        this.aE.startAnimation(this.bq);
    }

    private void b(float f, float f2) {
        if (this.aP == null) {
            this.aP = AnimationUtils.loadAnimation(this, R.anim.ae);
            this.aP.setAnimationListener(new 31(this));
        }
        LayoutParams layoutParams = (LayoutParams) this.aO.getLayoutParams();
        layoutParams.leftMargin = (int) f;
        layoutParams.topMargin = (int) f2;
        this.aO.setLayoutParams(layoutParams);
        this.aO.setVisibility(0);
        this.aO.startAnimation(this.aP);
        dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.b.cd, new CameraTapZoomTargetPoint(a(f, this.o), a(f2, this.p)), new 32(this));
    }

    private void c(float f, float f2) {
        float f3;
        if (this.s == null) {
            this.s = new 33(this);
        }
        this.aO.setVisibility(0);
        int measuredWidth = this.aO.getMeasuredWidth();
        int measuredHeight = this.aO.getMeasuredHeight();
        int a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.ce));
        float curScale = DJIZoomFocusView.getCurScale();
        if (((double) curScale) >= 29.8d) {
            f3 = 1.0f;
        } else if (30.0f / curScale > ((float) a)) {
            f3 = (float) a;
        } else {
            f3 = 30.0f / curScale;
        }
        if (f3 < 1.0f) {
            f3 = 1.0f;
        }
        Animation animationSet = new AnimationSet(false);
        animationSet.setAnimationListener(this.s);
        Animation scaleAnimation = new ScaleAnimation(1.0f, f3, 1.0f, f3, 1, dji.pilot.visual.a.d.c, 1, dji.pilot.visual.a.d.c);
        scaleAnimation.setDuration(600);
        Animation translateAnimation = new TranslateAnimation(f - ((float) (measuredWidth / 2)), (float) ((screenWidth / 2) - (measuredWidth / 2)), f2 - ((float) (measuredHeight / 2)), (float) ((screenHeight / 2) - (measuredHeight / 2)));
        translateAnimation.setDuration(600);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
        this.aO.startAnimation(animationSet);
        if (!dji.sdksharedlib.a.a.b(dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.cc))) {
            S();
        }
        dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.b.cd, new CameraTapZoomTargetPoint(a(f, this.o), a(f2, this.p)), new 35(this));
    }

    private float a(float f, int[] iArr) {
        if (iArr == null || iArr.length < 2) {
            return 0.0f;
        }
        return (f - ((float) iArr[0])) / ((float) (iArr[1] - iArr[0]));
    }

    private void ao() {
        if (this.bE.c) {
            ap();
        }
    }

    private void ap() {
        this.aR.removeMessages(B);
        if (this.bE != null) {
            this.bE.a();
        }
    }

    public void d() {
        dji.pilot.fpv.d.e.a("FPV_LongPressGesture_ControlGimbal");
        dji.pilot.fpv.d.e.c(s.dp);
        this.bE.a(this.ct);
    }

    public Bitmap D() {
        return this.ac.getBitmap(Bitmap.createBitmap(this.ac.getWidth() / 2, this.ac.getHeight() / 2, Config.RGB_565));
    }

    public static void e() {
        bG.runOnUiThread(new 36());
    }

    public static void f() {
        bG.runOnUiThread(new 37());
    }

    public boolean g() {
        return this.ae.getVisibility() == 0;
    }

    public void onEventMainThread(dji.pilot.flyforbid.a.b bVar) {
        switch (40.g[bVar.ordinal()]) {
            case 1:
                this.aX.a(null);
                return;
            case 2:
                this.aX.a(dji.pilot.flyforbid.a.getInstance(this).a());
                return;
            case 3:
                dji.pilot.flyforbid.a.getInstance(this).b();
                return;
            case 4:
                if (this.ae.hasDlgShowing()) {
                    this.cv = true;
                    return;
                } else {
                    dji.pilot.flyforbid.a.getInstance(this).a((Context) this);
                    return;
                }
            case 5:
                NFZLogUtil.savedLOG("nfz log 6 b d limits" + dji.pilot.flyforbid.a.getInstance(this).c().b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + dji.pilot.flyforbid.a.getInstance(this).c().c);
                this.aX.b(dji.pilot.flyforbid.a.getInstance(this).c());
                NFZLogUtil.savedLOG("nfz log 6 a d limits");
                return;
            case 6:
                if (!this.ae.hasDlgShowing()) {
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

    public boolean h() {
        return this.ae.getVisibility() == 0 && this.bd.c() && (this.aq == null || this.aq.getVisibility() != 0);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 2 && motionEvent.getAction() == 261) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.cw < 800) {
                try {
                    Class.forName("com.dji.tools.base.InnerToolsDialog").getMethod("showInnerTools", new Class[]{Context.class}).invoke(null, new Object[]{this});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.cw = currentTimeMillis;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onEventMainThread(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        DJIErrorPopView.b bVar;
        dataSmartBatteryGetPushDynamicData.setRequestPush(true);
        if ((dataSmartBatteryGetPushDynamicData.getStatus() & 281474976710656L) != 0) {
            bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.f = DJIErrorPopView.c.STATIC;
            bVar.g = DJIErrorPopView.f.INSERT;
            bVar.b = R.string.pm820_battery_error_5;
            dji.thirdparty.a.c.a().e(bVar);
        } else {
            bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.f = DJIErrorPopView.c.STATIC;
            bVar.g = DJIErrorPopView.f.REMOVE;
            bVar.b = R.string.pm820_battery_error_5;
            dji.thirdparty.a.c.a().e(bVar);
        }
        if ((dataSmartBatteryGetPushDynamicData.getStatus() & 562949953421312L) != 0) {
            bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.f = DJIErrorPopView.c.STATIC;
            bVar.g = DJIErrorPopView.f.INSERT;
            bVar.b = R.string.pm820_battery_error_4;
            dji.thirdparty.a.c.a().e(bVar);
        } else {
            bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.f = DJIErrorPopView.c.STATIC;
            bVar.g = DJIErrorPopView.f.REMOVE;
            bVar.b = R.string.pm820_battery_error_4;
            dji.thirdparty.a.c.a().e(bVar);
        }
        if ((dataSmartBatteryGetPushDynamicData.getStatus() & 2251799813685248L) != 0) {
            bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.f = DJIErrorPopView.c.STATIC;
            bVar.g = DJIErrorPopView.f.INSERT;
            bVar.b = R.string.pm820_battery_error_3;
            dji.thirdparty.a.c.a().e(bVar);
        } else {
            bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.f = DJIErrorPopView.c.STATIC;
            bVar.g = DJIErrorPopView.f.REMOVE;
            bVar.b = R.string.pm820_battery_error_3;
            dji.thirdparty.a.c.a().e(bVar);
        }
        if ((dataSmartBatteryGetPushDynamicData.getStatus() & 9007199254740992L) != 0) {
            bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.f = DJIErrorPopView.c.STATIC;
            bVar.g = DJIErrorPopView.f.INSERT;
            bVar.b = R.string.pm820_battery_error_1;
            dji.thirdparty.a.c.a().e(bVar);
        } else {
            bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.f = DJIErrorPopView.c.STATIC;
            bVar.g = DJIErrorPopView.f.REMOVE;
            bVar.b = R.string.pm820_battery_error_1;
            dji.thirdparty.a.c.a().e(bVar);
        }
        if ((dataSmartBatteryGetPushDynamicData.getStatus() & 18014398509481984L) != 0) {
            bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.f = DJIErrorPopView.c.STATIC;
            bVar.g = DJIErrorPopView.f.INSERT;
            bVar.b = R.string.pm820_battery_need_upgrate;
            dji.thirdparty.a.c.a().e(bVar);
        } else {
            bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.f = DJIErrorPopView.c.STATIC;
            bVar.g = DJIErrorPopView.f.REMOVE;
            bVar.b = R.string.pm820_battery_need_upgrate;
            dji.thirdparty.a.c.a().e(bVar);
        }
        if ((dataSmartBatteryGetPushDynamicData.getStatus() & 1125899906842624L) != 0) {
            bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.f = DJIErrorPopView.c.STATIC;
            bVar.g = DJIErrorPopView.f.INSERT;
            bVar.b = R.string.pm820_battery_error_2;
            dji.thirdparty.a.c.a().e(bVar);
            return;
        }
        bVar = new DJIErrorPopView.b();
        bVar.a = DJIErrorPopView.d.WARNING;
        bVar.f = DJIErrorPopView.c.STATIC;
        bVar.g = DJIErrorPopView.f.REMOVE;
        bVar.b = R.string.pm820_battery_error_2;
        dji.thirdparty.a.c.a().e(bVar);
    }

    public void onEventMainThread(DataSmartBatteryGetPushReArrangement dataSmartBatteryGetPushReArrangement) {
        Object obj = null;
        for (ReArrangement reArrangement : dataSmartBatteryGetPushReArrangement.getReArrangement()) {
            if (reArrangement.option == ReArrangementOption.Switch) {
                obj = 1;
                break;
            }
        }
        if (obj != null) {
            if (!this.cC && g()) {
                if (this.cB == null || !this.cB.isShowing()) {
                    this.cB = new j(this);
                    this.cB.a(new 38(this));
                    this.cB.show();
                }
            }
        } else if (this.cB != null && this.cB.isShowing()) {
            this.cB.dismiss();
            this.cB = null;
        }
    }

    public void onEventMainThread(j.a aVar) {
        if (aVar != j.a.a) {
            return;
        }
        if (this.cB == null || !this.cB.isShowing()) {
            this.cB = new j(this);
            this.cB.a(new 39(this));
            this.cB.show();
        }
    }

    private boolean aq() {
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if (c == ProductType.A3 || c == ProductType.N3) {
            if (!DataCameraGetPushStateInfo.getInstance().isGetted()) {
                return false;
            }
            CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
            DJIGimbalType type = DataGimbalGetPushType.getInstance().getType();
            if (cameraType == CameraType.OTHER || type == DJIGimbalType.Z15 || type == DJIGimbalType.D5 || type == DJIGimbalType.A7 || type == DJIGimbalType.GH4 || type == DJIGimbalType.BMPCC) {
                return false;
            }
        } else if (dji.pilot.fpv.d.b.r(null) && DataGimbalGetPushParams.getInstance().isPushLosed()) {
            return false;
        }
        return true;
    }

    public void i() {
        if (this.c.getVisibility() == 0) {
            this.c.hide();
            this.c.hideJoyStick();
            return;
        }
        this.c.show();
        this.c.showJoyStick();
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (!dji.pilot.fpv.d.b.r(null)) {
            return;
        }
        if (dataGimbalGetPushParams.isPushLosed()) {
            this.aS.c();
            if (this.aL.getVisibility() == 0) {
                this.aL.hide();
                return;
            }
            return;
        }
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
        if (instance != null && instance.o()) {
            return;
        }
        if ((this.aq == null || !this.aq.isShown()) && !this.mGuideShowing && this.bd.c() && !this.ae.hasDlgShowing() && aq()) {
            this.aS.b();
            if (this.aL.getVisibility() != 0) {
                this.aL.show();
            }
        }
    }
}
