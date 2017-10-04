package dji.pilot.fpv.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
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
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetAELock;
import dji.midware.data.model.P3.DataCameraSetMeteringArea;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetPushAvoid;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery.SmartGoHomeStatus;
import dji.midware.data.model.P3.DataFlycSmartAck;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause;
import dji.midware.data.model.P3.DataOsdGetPushCommon.TRIPOD_STATUS;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetGimbalControlMode;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.midware.data.model.P3.DataRcSetGimbalControlMode;
import dji.midware.data.model.P3.DataRcSetMaster;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.e.h;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.natives.FPVController;
import dji.pilot.R;
import dji.pilot.fpv.b.a;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.d;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.control.m;
import dji.pilot.fpv.control.n;
import dji.pilot.fpv.control.q;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.leftmenu.DJILeftBar;
import dji.pilot.fpv.leftmenu.b;
import dji.pilot.fpv.topbar.DJIFpvTopBaseView;
import dji.pilot.fpv.topbar.DJIFpvTopGrapeView;
import dji.pilot.fpv.view.DJIAttitudeView;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIGridLine;
import dji.pilot.fpv.view.DJIPlayBackView;
import dji.pilot.liveshare.LiveShareFpvTopView;
import dji.pilot.main.activity.DJIHubActivity;
import dji.pilot.publics.c.g;
import dji.pilot.publics.c.i;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.f;
import dji.pilot2.multimoment.view.HorizonalSegmentView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.setting.ui.flyc.SdModeView;
import dji.thirdparty.afinal.a.b.c;
import java.util.Timer;

public class DJIPreviewActivityGrape extends DJIPreviewActivityBaseForMC implements SurfaceTextureListener, OnClickListener, h, a, s {
    private static final int A = 8;
    private static final int B = 16;
    private static final int C = 32;
    private static final int D = 64;
    private static final int E = 127;
    private static final long G = 200;
    private static final long H = 100;
    private static final int aE = 8192;
    private static final int aF = 8193;
    private static final int aG = 8194;
    private static DJIPreviewActivityGrape aP = null;
    private static final boolean ap = false;
    private static boolean bh = false;
    private static dji.pilot.publics.widget.h bu = null;
    private static final int h = 4096;
    private static final int i = 8192;
    private static final int j = 12288;
    private static final int k = 16384;
    private static final int l = 20480;
    private static final int m = 24576;
    private static final int n = 28672;
    private static final int o = 32768;
    private static final int p = 36864;
    private static final int q = 36865;
    private static final int r = 36866;
    private static final int s = 36867;
    private static final int t = 36868;
    private static final int u = 36869;
    private static final int v = 36870;
    private static final int w = 0;
    private static final int x = 1;
    private static final int y = 2;
    private static final int z = 4;
    private volatile int F = 0;
    @c(a = 2131362846)
    private DJIRelativeLayout I;
    @c(a = 2131362848)
    private TextureView J;
    @c(a = 2131362849)
    private DJIImageView K;
    @c(a = 2131362858)
    private DJIFpvTopGrapeView L;
    @c(a = 2131362864)
    private DJIAttitudeView M;
    @c(a = 2131362855)
    private DJIRelativeLayout N;
    @c(a = 2131362871)
    private DJIRelativeLayout O;
    @c(a = 2131362876)
    private DJIStateImageView P;
    @c(a = 2131362856)
    private DJIRelativeLayout Q;
    @c(a = 2131362859)
    private ViewStub R;
    @c(a = 2131362870)
    private DJITextView S;
    @c(a = 2131362850)
    private DJIGridLine T;
    @c(a = 2131362861)
    private DJIErrorPopView U;
    @c(a = 2131362862)
    private ViewStub Y;
    protected int a;
    private boolean aA = false;
    private int aB = -1;
    private f aC = null;
    private FLYC_STATE aD = FLYC_STATE.OTHER;
    private b aH = null;
    private int aI = 8192;
    private d aJ = null;
    private dji.pilot.publics.widget.b aK = null;
    private int[] aL = new int[]{0, 0};
    private int[] aM = new int[]{0, 0};
    private m aN;
    private g aO;
    private dji.midware.media.h.b.b aQ = null;
    private RecordReceiver aR = null;
    private final int aS = -1;
    private int aT = -1;
    private dji.midware.e.d aU = new 1(this);
    private Runnable aV = new 23(this);
    private GestureDetector aW;
    private b aX = null;
    private b aY;
    private boolean aZ = false;
    @c(a = 2131362847)
    private DJIRelativeLayout aa;
    private DJIPlayBackView ab = null;
    @c(a = 2131362869)
    private DJILeftBar ac;
    @c(a = 2131363083)
    private LiveShareFpvTopView ad;
    private dji.pilot.publics.c.f ae = null;
    private a af = null;
    private dji.pilot.fpv.control.a ag = null;
    private DJIPlayBackView.b ah = null;
    private DJIGenSettingDataManager.c ai = null;
    private DJIFpvTopBaseView.b aj = null;
    private k ak = new k(this);
    private Animation al = null;
    private Animation am = null;
    private int an = 0;
    private Timer ao;
    private q aq;
    private boolean ar = false;
    private boolean as = false;
    private TRIPOD_STATUS at = TRIPOD_STATUS.UNKNOWN;
    private long au = 0;
    private FLIGHT_ACTION av = null;
    private MotorStartFailedCause aw = MotorStartFailedCause.None;
    private boolean ax = false;
    private FLYC_STATE ay = FLYC_STATE.OTHER;
    private boolean az = false;
    protected int b;
    private i bA = null;
    private Runnable ba = new 10(this);
    private Runnable bb = new 11(this);
    private b bc;
    private int bd = 5;
    private boolean be = false;
    private int bf;
    private b bg;
    private MODE bi;
    private RatioType bj = RatioType.R_4_3;
    private RatioType bk = RatioType.R_4_3;
    private RatioType bl = RatioType.R_4_3;
    private n bm = null;
    private int bn = 0;
    private dji.pilot.publics.widget.b bo;
    private SdModeView bp;
    private DataBaseCameraSetting bq = new DataBaseCameraSetting();
    private boolean br = false;
    private OnGestureListener bs = new 24(this);
    private MotionEvent bt = null;
    private boolean bv = false;
    private long bw = 0;
    private long bx = 0;
    private long by = 0;
    private f bz = null;
    protected int c = 12;
    protected int d = 8;
    protected i e = null;
    int f = -1;
    public boolean g = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(this.TAG, "onCreate");
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(this);
        instance.a(this.ak);
        instance.a(this.aq);
        dji.pilot.dji_groundstation.controller.a.getInstance(this);
        setContentView(R.layout.fpv_grape);
        getWindow().addFlags(128);
        this.ak.a(bundle, this.I);
        dji.gs.utils.a.a = DJIGenSettingDataManager.getInstance().s();
        this.aN = new m(this.I);
        n();
        o();
        O();
        k();
        this.b = screenWidth / this.c;
        this.a = screenHeight / this.d;
        if (!getIntent().getBooleanExtra(dji.pilot.c.b.a, true)) {
            this.aq.b();
        }
        b(true);
        this.aO = new g(this);
        aP = this;
        dji.midware.data.manager.P3.g.getInstance().a((int) HorizonalSegmentView.N);
        dji.pilot.c.a.n = true;
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        }
        this.bm = new n(this);
        this.e = new i(this);
        this.e.b();
        i();
    }

    private void i() {
        this.aR = new RecordReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.record");
        registerReceiver(this.aR, intentFilter);
    }

    private void j() {
        if (!dji.pilot.fpv.model.b.e(this) && !ServiceManager.getInstance().isConnected()) {
            Toast.makeText(this, getString(R.string.str_usd_unplugged), 1).show();
        }
    }

    private void a(boolean z) {
        this.L.toggleTipView(z);
        if (z) {
            this.M.show();
            if (!this.M.attiViewIsShown()) {
                this.O.show();
                this.P.show();
                this.N.show();
                return;
            }
            return;
        }
        this.M.go();
        this.O.go();
        this.P.go();
        this.N.go();
    }

    public boolean a() {
        return (this.L != null && this.L.hasDlgShowing()) || (!(this.aq == null || this.aq.c()) || (this.ab != null && this.ab.isShown()));
    }

    private void b(boolean z) {
        if (!ServiceManager.getInstance().isRemoteOK()) {
            this.L.hideCheckListDlg();
            if (!z) {
            }
        } else if (d.h() && !this.mGuideShowing && !DataOsdGetPushCommon.getInstance().isMotorUp()) {
            if (this.ab == null || !this.ab.isShown()) {
                this.L.showCheckListDlg();
                d.i();
            }
        }
    }

    private void k() {
        if (ServiceManager.getInstance().isConnected()) {
            int metering = DataCameraGetPushShotParams.getInstance().getMetering();
            DJILogHelper.getInstance().LOGD(this.TAG, "测光=" + metering);
            if (metering == 2) {
                U();
            } else if (metering == 0) {
                this.af.sendEmptyMessage(n);
                V();
            }
        }
    }

    private void m() {
        this.S.show();
        this.ao = new Timer();
        this.ao.schedule(new 12(this), 0, 1000);
    }

    private void n() {
        this.au = System.currentTimeMillis();
        this.ag = new dji.pilot.fpv.control.a(this, this.I);
        this.ag.a();
        this.aj = new 28(this);
        this.ai = new 29(this);
        this.af = new a(this);
        this.al = AnimationUtils.loadAnimation(this, R.anim.bt);
        this.am = AnimationUtils.loadAnimation(this, R.anim.bu);
        DJIGenSettingDataManager.getInstance().a(this.ai);
        this.aJ = new d(this);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void o() {
        this.J.setSurfaceTextureListener(this);
        this.M.dispatchOnCreate();
        this.M.setGsOnRight(false);
        this.L.dispatchOnCreate();
        this.L.changeGrapLayout();
        this.L.setOnEventListener(this.aj);
        this.U.dispatchOnCreate();
        this.ac.dispatchOnCreate();
        this.ac.setMutexView(this.U);
        F();
        this.aq = new q(this, this.I, false);
        this.aq.a(this.ak);
        this.aq.a(new 30(this));
        this.aW = new GestureDetector(this, this.bs);
        this.aW.setIsLongpressEnabled(false);
        this.aa.setOnTouchListener(new 31(this));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a21:
                p();
                return;
            default:
                return;
        }
    }

    private void p() {
        this.af.sendMessage(this.af.obtainMessage(32768, 0, 0));
        U();
    }

    public void onClickBackground(View view) {
    }

    private void q() {
        TRIPOD_STATUS deformStatus = DataFlycGetPushDeformStatus.getInstance().getDeformStatus();
        if (deformStatus != TRIPOD_STATUS.UNKNOWN && this.at != deformStatus && dji.pilot.fpv.d.b.g(null)) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.at != TRIPOD_STATUS.UNKNOWN && currentTimeMillis - this.au >= 8000) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.b = dji.pilot.fpv.d.b.a(deformStatus);
                bVar.a = DJIErrorPopView.d.NOTIFY;
                dji.thirdparty.a.c.a().e(bVar);
            }
            this.at = deformStatus;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void r() {
        /*
        r8 = this;
        r7 = 1;
        r6 = 8;
        r5 = 0;
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.isSwaveWork();
        r1 = r8.as;
        if (r1 == r0) goto L_0x0014;
    L_0x0010:
        r8.as = r0;
        if (r0 == 0) goto L_0x0014;
    L_0x0014:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.getFlightAction();
        r1 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r1 = r1.getFlycState();
        r2 = r8.ay;
        if (r2 == r1) goto L_0x0056;
    L_0x0028:
        r8.ay = r1;
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
        r1 = r8.av;
        if (r1 == r0) goto L_0x0080;
    L_0x005a:
        if (r0 == 0) goto L_0x0080;
    L_0x005c:
        r8.av = r0;
        r0 = r8.av;
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
        r1 = r8.aw;
        if (r1 == r0) goto L_0x0187;
    L_0x008c:
        r8.aw = r0;
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
        r0 = r8.L;
        if (r0 == 0) goto L_0x00d5;
    L_0x00b8:
        r0 = r8.L;
        r0 = r0.hasDlgShowing();
        if (r0 != 0) goto L_0x00d5;
    L_0x00c0:
        r0 = r8.isVisible;
        if (r0 == 0) goto L_0x00d5;
    L_0x00c4:
        r0 = r8.ab;
        if (r0 == 0) goto L_0x00d0;
    L_0x00c8:
        r0 = r8.ab;
        r0 = r0.isShown();
        if (r0 != 0) goto L_0x00d5;
    L_0x00d0:
        r0 = r8.L;
        r0.showCheckListDlg();
    L_0x00d5:
        r0 = r8.aw;
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
        r2 = 2131296758; // 0x7f0901f6 float:1.8211442E38 double:1.053000509E-314;
        r0.b(r2);
        r0 = r8.unlockDialog;
        r2 = 2131296493; // 0x7f0900ed float:1.8210904E38 double:1.0530003783E-314;
        r0.d(r2);
        r0 = r8.unlockDialog;
        r2 = new dji.pilot.fpv.activity.DJIPreviewActivityGrape$32;
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
        r0 = r8.aw;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.DeviceLocked;
        if (r0 == r2) goto L_0x0128;
    L_0x0122:
        r0 = r8.aw;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause.NoviceProtected;
        if (r0 != r2) goto L_0x0187;
    L_0x0128:
        if (r1 == 0) goto L_0x0187;
    L_0x012a:
        r0 = r8.aY;
        if (r0 != 0) goto L_0x0182;
    L_0x012e:
        r0 = new dji.pilot.fpv.leftmenu.b;
        r0.<init>(r8);
        r8.aY = r0;
        r0 = r8.aY;
        r0.a(r7);
        r0 = r8.aY;
        r1 = new dji.pilot.fpv.activity.DJIPreviewActivityGrape$33;
        r1.<init>(r8);
        r0.a(r1);
        r0 = r8.aY;
        r0.d(r6);
        r0 = r8.aY;
        r0 = r0.a(r6, r5);
        r0.e(r6);
        r0 = r8.aY;
        r1 = "";
        r0.a(r6, r1);
        r0 = r8.aY;
        r1 = 2131296822; // 0x7f090236 float:1.8211572E38 double:1.053000541E-314;
        r1 = r8.getString(r1);
        r0.a(r1);
        r0 = r8.aY;
        r1 = 2131296820; // 0x7f090234 float:1.8211567E38 double:1.05300054E-314;
        r1 = r8.getString(r1);
        r0.b(r1);
        r0 = r8.aY;
        r1 = r8.getResources();
        r2 = 2131427846; // 0x7f0b0206 float:1.847732E38 double:1.0530652753E-314;
        r1 = r1.getDimension(r2);
        r1 = (int) r1;
        r0.c(r1);
    L_0x0182:
        r0 = r8.aY;
        r0.show();
    L_0x0187:
        r0 = dji.midware.data.model.P3.DataOsdGetPushCommon.getInstance();
        r0 = r0.getFlycVersion();
        r8.aB = r0;
        return;
    L_0x0192:
        r2 = dji.pilot.fpv.view.DJIErrorPopView.d.WARNING;
        r3.a = r2;
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.fpv.activity.DJIPreviewActivityGrape.r():void");
    }

    private void s() {
        if (this.aX == null) {
            this.aX = new b(this);
            this.aX.a(1);
            this.aX.a(getString(R.string.fpv_cancel_gohome_title));
            this.aX.b(R.drawable.leftmenu_dlg_gohome);
            this.aX.b(getString(R.string.fpv_cancel_gohome_desc));
            this.aX.a(8, 0);
            this.aX.e(8);
            this.aX.d(8);
            this.aX.c(getString(R.string.fpv_before_cancel_gohome));
            this.aX.a(new 2(this));
        }
        if (!this.aX.isShowing()) {
            this.aX.show();
            t();
        }
    }

    private void t() {
        if (this.aX != null && this.aX.isShowing()) {
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
            this.aX.a(0, getString(R.string.fpv_cancel_gohome_littletitle, new Object[]{Integer.valueOf(DataFlycGetPushSmartBattery.getInstance().getBattery()), Float.valueOf((((float) i2) * 1.0f) / 1000.0f)}));
        }
    }

    private void u() {
        if (this.aX != null && this.aX.isShowing()) {
            this.aX.dismiss();
            this.aX = null;
        }
    }

    private void v() {
        SmartGoHomeStatus goHomeStatus = DataFlycGetPushSmartBattery.getInstance().getGoHomeStatus();
        if (goHomeStatus == SmartGoHomeStatus.NON_GOHOME && DataOsdGetPushCommon.getInstance().isMotorUp()) {
            if (this.aH == null || !this.aH.isShowing()) {
                if (DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown() > 0) {
                    b(8193);
                }
            } else if (this.aH.isShowing() && 8193 == this.aI) {
                a(false, DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown());
            }
        } else if ((goHomeStatus == SmartGoHomeStatus.GOHOME_ALREADY || goHomeStatus == SmartGoHomeStatus.GOHOME) && 8193 == this.aI) {
            A();
        }
        if (!bh) {
            int status = DataFlycGetPushSmartBattery.getInstance().getStatus();
            if (status != this.bf) {
                this.bf = status;
                if ((this.bf & 2048) == 2048) {
                    if (this.bg == null) {
                        this.bg = new b(this);
                        this.bg.a(1);
                        this.bg.a(new 3(this));
                        this.bg.d(8);
                        this.bg.a(8, 0).e(8);
                        this.bg.a(8, "");
                        this.bg.a(false);
                        this.bg.b(getString(R.string.battery_first_charge_not_full));
                    }
                    this.bg.show();
                    bh = true;
                }
            }
        }
    }

    private void w() {
        boolean isBeginnerMode = DataOsdGetPushHome.getInstance().isBeginnerMode();
        if (this.ax != isBeginnerMode) {
            this.ax = isBeginnerMode;
            if (!this.ax) {
                dji.pilot.fpv.d.b.a(2, true);
            } else if (dji.pilot.fpv.d.b.a(2)) {
                dji.pilot.fpv.d.b.a(2, false);
                this.L.hideDialog();
                b(8194);
            }
        }
        isBeginnerMode = DataOsdGetPushHome.getInstance().isReatchLimitHeight();
        boolean isReatchLimitDistance = DataOsdGetPushHome.getInstance().isReatchLimitDistance();
        if (this.aB >= 3) {
            DJIErrorPopView.b bVar;
            if (this.az != isBeginnerMode) {
                this.az = isBeginnerMode;
                if (this.az) {
                    bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.fpv_errorpop_altitude_limit;
                    dji.thirdparty.a.c.a().e(bVar);
                    dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.y);
                }
            }
            if (this.aA != isReatchLimitDistance) {
                this.aA = isReatchLimitDistance;
                if (this.aA) {
                    bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.fpv_errorpop_distance_limit;
                    dji.thirdparty.a.c.a().e(bVar);
                    dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.x);
                }
            }
        }
    }

    private void x() {
        int i = R.string.fpv_height_imperial;
        if (this.aC != null && this.aC.isShowing() && this.aD != FLYC_STATE.GoHome) {
            float height;
            float b;
            if (this.aD == FLYC_STATE.AutoLanding) {
                height = (float) ((int) (((float) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1f));
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    b = (float) ((int) DJIGenSettingDataManager.getInstance().b(height));
                } else {
                    i = R.string.fpv_height_metric;
                    b = height;
                }
                this.aC.a(getString(i, new Object[]{Float.valueOf(b)}));
            } else if (this.aD == FLYC_STATE.AutoTakeoff) {
                height = (float) ((int) (((float) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1f));
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    b = (float) ((int) DJIGenSettingDataManager.getInstance().b(height));
                } else {
                    i = R.string.fpv_height_metric;
                    b = height;
                }
                this.aC.a(getString(i, new Object[]{Float.valueOf(b)}));
            }
        }
    }

    private void y() {
        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.DropGohome).start(new 4(this));
    }

    private void b(int i) {
        if (this.aH == null) {
            this.aH = new b(this);
            this.aH.a(new 5(this));
            this.aH.setOnDismissListener(new 6(this));
        }
        if (!dji.pilot.publics.control.a.getInstance().l() && this.aH != null && !this.aH.isShowing()) {
            this.aI = i;
            if (i == 8193) {
                a(true, DataFlycGetPushSmartBattery.getInstance().getGoHomeCountDown());
            } else if (i == 8194) {
                z();
            }
            this.aH.show();
        }
    }

    private void a(boolean z, int i) {
        this.aH.a(getString(R.string.fpv_before_gohome_title, new Object[]{Integer.valueOf(i)}));
        if (z) {
            this.aH.a(1);
            this.aH.b(R.drawable.leftmenu_dlg_gohome);
            this.aH.a(8, 0);
            this.aH.e(8);
            this.aH.d(0);
            this.aH.b(getString(R.string.fpv_before_gohome_above_desc));
            this.aH.c(getString(R.string.app_cancel));
        }
    }

    private void z() {
        this.aH.a(4);
        this.aH.a(8, 0);
        this.aH.e(8);
        this.aH.d(8);
        this.aH.a(getString(R.string.fpv_novice_takeoff_title));
        this.aH.b(getString(R.string.fpv_novice_takeoff_desc));
        this.aH.c(getString(R.string.app_isee));
    }

    private void c(int i) {
        if (this.aI == 8193) {
            DataFlycSmartAck.getInstance().setAck((byte) 1).start(null);
        }
    }

    private void d(int i) {
        if (this.aI == 8193) {
            DataFlycSmartAck.getInstance().setAck((byte) 2).start(null);
        } else if (this.aI == 8194) {
            A();
        }
    }

    private void A() {
        if (this.aH != null && this.aH.isShowing()) {
            this.aH.dismiss();
        }
    }

    private void B() {
        dji.pilot.publics.widget.b.a(this, R.string.app_tip, R.string.fpv_adb_debug_tip, R.string.app_cancel, new 7(this), R.string.app_setting, new 8(this)).show();
    }

    private void C() {
        boolean z;
        boolean z2;
        boolean z3 = false;
        boolean z4 = true;
        if ((this.F & 64) != 0) {
            this.F &= -65;
            z = true;
        } else {
            z = false;
        }
        if ((this.F & 32) != 0) {
            q();
            this.F &= -33;
        }
        if ((this.F & 1) != 0) {
            r();
            b(true);
            this.W.d();
            this.F &= -2;
            z2 = true;
        } else {
            z2 = false;
        }
        if ((this.F & 16) != 0) {
            this.F &= -17;
            z2 = true;
        }
        if ((this.F & 4) != 0) {
            this.F &= -5;
        }
        if ((this.F & 2) != 0) {
            if (this.aD == FLYC_STATE.GoHome) {
                x();
            }
            w();
            this.aJ.a(DataOsdGetPushHome.getInstance());
            this.W.e();
            this.F &= -3;
            z3 = true;
        }
        if ((this.F & 8) != 0) {
            v();
            this.F &= -9;
        } else {
            z4 = z;
        }
        if (this.ay != FLYC_STATE.GoHome || r1) {
            if (z2) {
                this.M.update(z3);
            }
        } else if (z2) {
            this.M.update(z3);
        }
    }

    public void l() {
        this.M.disconnect();
        E();
        dji.pilot.battery.a.a.getInstance().d();
        dji.pilot.battery.a.a.getInstance().f();
        DJIGenSettingDataManager.getInstance().f();
        this.bm.b();
        super.l();
    }

    private void E() {
        this.av = null;
        this.at = TRIPOD_STATUS.UNKNOWN;
        this.as = false;
        this.aD = FLYC_STATE.OTHER;
        this.aw = MotorStartFailedCause.None;
        this.ay = FLYC_STATE.OTHER;
    }

    private void b(boolean z, int i) {
        if (!z) {
        }
    }

    public void onEventMainThread(dji.pilot.fpv.model.n.b bVar) {
        if (bVar == dji.pilot.fpv.model.n.b.a) {
            if (this.L.getVisibility() == 0) {
                a(false, true);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.b) {
            if (this.L.getVisibility() == 0) {
                b(false, true);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.e) {
            if (this.L.getVisibility() == 0) {
                a(false, false);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.f) {
            if (this.L.getVisibility() == 0) {
                b(false, false);
            }
        } else if (bVar == dji.pilot.fpv.model.n.b.d && this.L.getVisibility() == 0) {
            this.W.h();
        }
    }

    private void a(boolean z, boolean z2) {
        if (z) {
            this.T.go();
            this.aq.a(false);
            this.L.hideDialog();
        } else {
            this.aq.a(true);
        }
        if (!z2) {
            this.L.go();
            this.L.startAnimation(this.am);
        }
        this.ac.hideMenu(true);
        this.M.go();
    }

    private void c(boolean z) {
        a(z, false);
    }

    private void b(boolean z, boolean z2) {
        if (z) {
            this.aq.f();
            F();
        } else if (T()) {
            this.af.postDelayed(new 9(this), 50);
        }
        this.ac.showMenu();
        if (T()) {
            this.M.show();
        }
        if (!z2) {
            this.L.show();
            this.L.startAnimation(this.al);
        }
        if (dji.pilot.c.d.f == 1) {
            this.W.g();
        }
    }

    private void d(boolean z) {
        b(z, false);
    }

    private void e(boolean z) {
        DJILogHelper.getInstance().LOGD("", "FastCamera visible[" + z + dji.pilot.usercenter.protocol.d.H, false, true);
        if (z) {
            this.aq.a(false);
            this.L.setVisibleAeLock(false);
            return;
        }
        this.aq.f();
        this.L.setVisibleAeLock(true);
    }

    private void F() {
        int k = DJIGenSettingDataManager.getInstance().k();
        if (k == 0) {
            this.T.go();
            return;
        }
        if (k == 1) {
            this.T.setType(1);
        } else if (k == 2) {
            this.T.setType(2);
        } else if (k == 3) {
            this.T.setType(4);
        }
        this.T.show();
    }

    private void H() {
    }

    private void I() {
        this.ak.h();
    }

    private void J() {
        this.ak.i();
    }

    public void finishThis() {
        if (this.ab == null || this.ab.getVisibility() != 0) {
            this.af.removeMessages(8192);
            this.af.removeMessages(4096);
            this.af.removeMessages(16384);
            this.af.removeMessages(24576);
            this.ag.b();
            dji.thirdparty.a.c.a().e(DJIHubActivity.a.b);
            K();
            dji.pilot.c.a.n = false;
            L();
            finish();
            overridePendingTransition(0, 0);
        } else if (ServiceManager.getInstance().isConnected()) {
            DataSpecialControl.getInstance().setPlayBackType(false).start(20);
        }
    }

    private void K() {
        if (this.ae != null) {
            this.ae.a(null);
            this.ae.b();
            this.ae = null;
        }
        if (this.aQ != null) {
            this.aQ.c();
            this.aQ = null;
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.ak.a(bundle);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.ak.d();
    }

    private void L() {
        if (this.aO != null) {
            if (this.ab != null && this.ab.getVisibility() == 0) {
                this.ab.go();
            }
            this.aO.a();
            P();
            DJIGenSettingDataManager.getInstance().b(this.ai);
            this.aq.a();
            this.M.dispatchOnDestroy();
            this.L.dispatchOnDestroy();
            this.U.dispatchOnDestroy();
            this.ac.dispatchOnDestroy();
            this.e.a();
            this.aO = null;
            unregisterReceiver(this.aR);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.ak.e();
        X();
        Log.d(this.TAG, "onDestroy");
    }

    protected void onResume() {
        super.onResume();
        this.ak.b();
        dji.pilot.groundStation.a.a.a(this);
        dji.pilot.groundStation.a.a.getInstance(this).a(this.ak);
        dji.pilot.groundStation.a.a.getInstance(this).a(this.aq);
        dji.pilot.groundStation.a.a.getInstance(this).c(true);
        dji.pilot.dji_groundstation.controller.a.getInstance(this);
    }

    protected void onPause() {
        dji.pilot.groundStation.a.a.getInstance(this).c(false);
        super.onPause();
        this.ak.c();
    }

    protected void onStart() {
        super.onStart();
        this.ar = true;
        if (this.F != 0) {
            this.af.sendEmptyMessageDelayed(4096, 200);
        }
        Log.d(this.TAG, "onStart" + System.currentTimeMillis());
        e.b((Context) this);
        ServiceManager.getInstance().pauseService(false);
        dji.pilot.flyunlimit.b.getInstance(this).a(this.ak);
        dji.pilot.flyforbid.a.getInstance(this).c((Context) this);
    }

    protected void onStop() {
        this.ar = false;
        Log.d(this.TAG, "onStop");
        super.onStop();
        e.c((Context) this);
        dji.pilot.flyunlimit.b.getInstance(this).a(null);
        dji.pilot.flyforbid.a.getInstance(this).f();
    }

    public void onBackPressed() {
        finishThis();
    }

    public void oneFrameComeIn() {
        this.an++;
    }

    public void resetVideoSurface(int i, int i2) {
        this.af.sendMessage(this.af.obtainMessage(12288, i, i2));
    }

    private void a(int i, int i2) {
        DJILogHelper.getInstance().LOGD(this.TAG, "videoWidth=" + i + "  videoHeight=" + i2, false, true);
        M();
    }

    private void M() {
        boolean z;
        int i = DJIVideoDecoder.width;
        int i2 = DJIVideoDecoder.height;
        if (Math.abs(DJIBaseActivity.screenRatio - dji.midware.util.a.b.a) < Math.abs(DJIBaseActivity.screenRatio - dji.midware.util.a.b.b)) {
            this.bj = RatioType.R_16_9;
        } else {
            this.bj = RatioType.R_4_3;
        }
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if (c == ProductType.litchiC || c == ProductType.litchiS) {
            z = false;
        } else {
            z = true;
        }
        DJILogHelper.getInstance().LOGD(this.TAG, "isLong=" + z + " productType=" + c, false, true);
        DJILogHelper.getInstance().LOGD(this.TAG, "ratioType=" + this.bl, false, true);
        DJILogHelper.getInstance().LOGD(this.TAG, "screenRatioType=" + this.bj, false, true);
        if (this.bl == RatioType.R_4_3 && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
            this.bk = RatioType.R_4_3;
        } else {
            this.bk = RatioType.R_16_9;
        }
        DJILogHelper.getInstance().LOGD(this.TAG, "videoRatioType=" + this.bk, false, true);
        LayoutParams layoutParams = (LayoutParams) this.J.getLayoutParams();
        int i3;
        if (z) {
            if (this.bj == RatioType.R_16_9) {
                layoutParams.width = DJIBaseActivity.screenWidth;
                layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
            } else {
                layoutParams.width = DJIBaseActivity.screenWidth;
                layoutParams.height = (int) (((((float) i2) * 1.0f) / ((float) i)) * ((float) DJIBaseActivity.screenWidth));
            }
            this.J.setLayoutParams(layoutParams);
            this.K.setLayoutParams(layoutParams);
            if (this.bj == RatioType.R_4_3) {
                if (this.bk == RatioType.R_4_3) {
                    this.J.setScaleX(dji.midware.util.a.b.b);
                    this.K.setScaleX(dji.midware.util.a.b.b);
                    layoutParams.width = DJIBaseActivity.screenWidth;
                    layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.b);
                } else {
                    this.J.setScaleX(1.0f);
                    this.K.setScaleX(1.0f);
                    layoutParams.width = DJIBaseActivity.screenWidth;
                    layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                }
                this.aL[0] = 0;
                this.aL[1] = layoutParams.width;
                i3 = (DJIBaseActivity.screenHeight - layoutParams.height) / 2;
                this.aM[0] = i3;
                this.aM[1] = DJIBaseActivity.screenHeight - i3;
                N();
                this.T.setLayoutParams(layoutParams);
                return;
            }
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(0, 0);
            layoutParams2.addRule(13, -1);
            if (this.bk == RatioType.R_4_3) {
                layoutParams2.width = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.b);
                layoutParams2.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                i3 = (DJIBaseActivity.screenWidth - layoutParams2.width) / 2;
                this.aL[0] = i3;
                this.aL[1] = DJIBaseActivity.screenWidth - i3;
                i3 = (DJIBaseActivity.screenHeight - layoutParams2.height) / 2;
                this.aM[0] = i3;
                this.aM[1] = DJIBaseActivity.screenHeight - i3;
                N();
            } else {
                layoutParams2.width = DJIBaseActivity.screenWidth;
                layoutParams2.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
                this.aL[0] = 0;
                this.aL[1] = layoutParams2.width;
                i3 = (DJIBaseActivity.screenHeight - layoutParams2.height) / 2;
                this.aM[0] = i3;
                this.aM[1] = DJIBaseActivity.screenHeight - i3;
                N();
            }
            this.T.setLayoutParams(layoutParams2);
            return;
        }
        if (this.bk == RatioType.R_16_9) {
            layoutParams.width = DJIBaseActivity.screenWidth;
            layoutParams.height = (int) (((float) DJIBaseActivity.screenWidth) / dji.midware.util.a.b.a);
            this.aL[0] = 0;
            this.aL[1] = layoutParams.width;
            i3 = (DJIBaseActivity.screenHeight - layoutParams.height) / 2;
            this.aM[0] = i3;
            this.aM[1] = DJIBaseActivity.screenHeight - i3;
            N();
        } else {
            layoutParams.width = (int) (((float) DJIBaseActivity.screenHeight) * dji.midware.util.a.b.b);
            layoutParams.height = DJIBaseActivity.screenHeight;
            i3 = (DJIBaseActivity.screenWidth - layoutParams.width) / 2;
            this.aL[0] = i3;
            this.aL[1] = DJIBaseActivity.screenWidth - i3;
            this.aM[0] = 0;
            this.aM[1] = layoutParams.height;
            N();
        }
        this.J.setLayoutParams(layoutParams);
        this.K.setLayoutParams(layoutParams);
        this.T.setLayoutParams(layoutParams);
    }

    private void N() {
        this.b = (this.aL[1] - this.aL[0]) / this.c;
        this.a = (this.aM[1] - this.aM[0]) / this.d;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        DJILogHelper.getInstance().LOGD(this.TAG, "onSurfaceTextureAvailable mVideoDecoder", false, true);
        this.aQ = dji.midware.media.h.e.a(getClass());
        this.aQ.a(surfaceTexture, i, i2);
        this.aQ.a(DJIGenSettingDataManager.getInstance().F(), R.raw.overexposure);
        if (this.ae == null) {
            a(this.aQ);
        } else {
            this.ae.a(this.aQ);
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.aQ != null) {
            this.aQ.a(i, i2);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.ae != null) {
            this.ae.a(null);
        }
        if (this.aQ != null) {
            this.aQ.c();
            this.aQ = null;
        }
        DJILogHelper.getInstance().LOGD(this.TAG, "onSurfaceTextureDestroyed");
        return false;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    private void a(dji.midware.media.h.b.b bVar) {
        this.ae = new dji.pilot.publics.c.f(this, bVar);
        this.ae.a(this);
        FPVController.native_setDecodeMode(dji.pilot.publics.objects.g.b((Context) this, "DecodeMode", false));
    }

    private void O() {
        dji.thirdparty.a.c.a().a((Object) this);
    }

    private void P() {
        dji.thirdparty.a.c.a().d((Object) this);
    }

    public void onEventBackgroundThread(ProductType productType) {
        if (productType != ProductType.Grape2 && productType != ProductType.A2) {
            finishThis();
        }
    }

    private void Q() {
        DJILogHelper.getInstance().LOGD(this.TAG, "showNoVideoGoHomeDialog isRemoteOK=" + ServiceManager.getInstance().isRemoteOK(), false, true);
        if (ServiceManager.getInstance().isRemoteOK()) {
        }
        if (this.bc == null) {
            this.bc = new b(this);
            this.bc.a(1);
            this.bc.a(new 13(this));
            this.bc.d(getString(R.string.disconnect_gohome_alert_gohome_button));
            this.bc.d(0);
            this.bc.a(8, 0).e(8);
            this.bc.a(0, getString(R.string.disconnect_gohome_alert_title2));
        }
        this.bd = 5;
        this.bc.c();
        this.bc.show();
        DJILogHelper.getInstance().LOGD(this.TAG, "showNoVideoGoHomeDialog show", false, true);
        dji.pilot.publics.c.h.getInstance().a(dji.pilot.publics.c.h.b.v);
    }

    private void R() {
        if (this.bc != null && this.bc.isShowing()) {
            if (this.bd == 0) {
                this.bc.dismiss();
                S();
                return;
            }
            this.bc.b(getString(R.string.disconnect_gohome_alert_title, new Object[]{Integer.valueOf(this.bd)}));
            this.af.sendEmptyMessageDelayed(s, 1000);
            this.bd--;
        }
    }

    private void S() {
        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.GOHOME).start(new 14(this));
    }

    public void onEventBackgroundThread(dji.midware.data.manager.P3.m mVar) {
        switch (27.a[mVar.ordinal()]) {
            case 1:
                this.af.post(this.ba);
                this.af.removeMessages(r);
                this.af.removeMessages(s);
                if (this.bc != null && this.bc.isShowing()) {
                    this.bc.dismiss();
                    return;
                }
                return;
            case 2:
                if (this.isVisible) {
                    this.af.post(this.bb);
                    if (ServiceManager.getInstance().isRemoteOK() && DataOsdGetPushCommon.getInstance().groundOrSky() == 2 && DataCameraGetPushStateInfo.getInstance().getMode() != MODE.PLAYBACK) {
                        this.af.sendEmptyMessageDelayed(r, ToolTipPopup.a);
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
        switch (27.b[oVar.ordinal()]) {
            case 1:
                k();
                this.au = System.currentTimeMillis();
                this.af.removeMessages(16384);
                this.af.removeMessages(4096);
                this.F = 127;
                this.af.sendEmptyMessageDelayed(4096, 200);
                this.af.removeMessages(8192);
                this.af.sendEmptyMessageDelayed(8192, H);
                this.af.sendEmptyMessage(24576);
                return;
            case 2:
                this.be = false;
                this.bi = null;
                if (this.ab != null) {
                    this.ab.setIsBackPBack(true);
                }
                this.F = 0;
                this.af.removeMessages(4096);
                this.af.removeMessages(8192);
                this.af.sendEmptyMessageDelayed(16384, 200);
                return;
            default:
                return;
        }
    }

    private void e(int i) {
        if (this.ar && (this.F & i) == 0) {
            this.F |= i;
            if (!this.af.hasMessages(4096)) {
                this.af.sendEmptyMessageDelayed(4096, 200);
            }
        }
    }

    private boolean T() {
        DroneType droneType = DataOsdGetPushCommon.getInstance().getDroneType();
        return (droneType == DroneType.NoFlyc || droneType == DroneType.Unknown) ? false : true;
    }

    protected void onBackgroundThreadOver(DataOsdGetPushCommon dataOsdGetPushCommon) {
        e(1);
        if (this.ar && !this.af.hasMessages(8192)) {
            this.af.sendEmptyMessageDelayed(8192, H);
        }
        this.g = true;
        this.af.removeMessages(t);
        this.af.sendEmptyMessageDelayed(t, 1000);
        boolean T = T();
        if (this.be != T) {
            this.be = T;
            if (this.be) {
                this.af.sendMessage(this.af.obtainMessage(12, 1, 0));
            } else {
                this.af.sendMessage(this.af.obtainMessage(12, 0, 0));
            }
        }
    }

    public void onEventBackgroundThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        e(2);
    }

    public void onEventBackgroundThread(DataRcGetPushGpsInfo dataRcGetPushGpsInfo) {
        e(4);
    }

    public void b() {
        int i;
        if (this.aT == 0) {
            i = 1;
            dji.midware.usb.P3.a.getInstance().a(dji.midware.usb.P3.a.c.b);
            if (this.aK != null && this.aK.isShowing()) {
                this.aK.dismiss();
            }
            this.aK = dji.pilot.publics.widget.b.a(this, getString(R.string.app_tip), getString(R.string.fpv_hd_bandwidth_slave_change_ext), getString(R.string.app_enter), new 15(this));
            this.aK.show();
        } else if (this.aT == 10) {
            i = 0;
            dji.midware.usb.P3.a.getInstance().a(dji.midware.usb.P3.a.c.a);
            if (this.aK != null && this.aK.isShowing()) {
                this.aK.dismiss();
            }
            this.aK = dji.pilot.publics.widget.b.a(this, getString(R.string.app_tip), getString(R.string.fpv_hd_bandwidth_slave_change_lb), getString(R.string.app_enter), new 16(this));
            this.aK.show();
        } else {
            return;
        }
        new DataDm368SetGParams().a(CmdId.l, i).start(new 17(this));
    }

    public void onEventBackgroundThread(DataRcGetPushParams dataRcGetPushParams) {
        boolean z = this.aT == -1;
        if (this.aT != dataRcGetPushParams.getBandWidth()) {
            this.aT = dataRcGetPushParams.getBandWidth();
            DJILogHelper.getInstance().LOGD("fuck", "收到推送bandwidth:" + this.aT, false, true);
            dji.pilot.c.d.e = this.aT;
            if (dji.pilot.c.d.b == DataRcSetMaster.MODE.Slave && !z) {
                this.af.sendEmptyMessage(v);
            }
        }
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
        if (mode != this.bi) {
            this.bi = mode;
            this.af.sendEmptyMessage(12288);
            if (this.bi == MODE.PLAYBACK) {
                this.af.sendMessage(this.af.obtainMessage(32768, 0, 0));
            }
            DJILogHelper.getInstance().LOGD(this.TAG, "cameramode=" + this.bi, false, true);
            if (this.bi == MODE.DOWNLOAD && (this.ab == null || this.ab.isBackPBack())) {
                DataCameraSetMode.getInstance().a(MODE.PLAYBACK).start(new 18(this));
            }
            if (this.ab != null) {
                this.ab.setIsBackPBack(false);
            }
            if (this.bi == MODE.NEW_PLAYBACK) {
                DataCameraSetMode.getInstance().a(dji.pilot.c.d.a).start(null);
            }
            if ((this.bi == MODE.TAKEPHOTO || this.bi == MODE.RECORD) && this.aQ != null) {
                this.aQ.a(DJIGenSettingDataManager.getInstance().F(), R.raw.overexposure);
            }
        }
    }

    public void onEventMainThread(dji.midware.media.h.a.f.a aVar) {
        if (this.aQ != null) {
            this.aQ.a(aVar.a, aVar.b);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (dataCameraGetPushShotParams.isAELock()) {
            this.af.sendMessage(this.af.obtainMessage(32768, 0, 0));
        }
        RatioType imageRatio = DataCameraGetPushShotParams.getInstance().getImageRatio();
        if (imageRatio != this.bl) {
            this.bl = imageRatio;
            this.af.sendEmptyMessage(12288);
        }
    }

    public void onEventBackgroundThread(dji.pilot.fpv.control.b.a aVar) {
        switch (27.c[aVar.ordinal()]) {
            case 1:
                if (this.c == 0) {
                    DataCameraGetMeteringArea.getInstance().start(this.aU);
                    return;
                }
                return;
            case 2:
                U();
                return;
            default:
                return;
        }
    }

    public void a(int i) {
        if (i == 0) {
            this.bp.a = SdModeView.a.c;
        } else {
            this.bp.a = SdModeView.a.a;
        }
        DJILogHelper.getInstance().LOGD("", "lose_osd blackMode=" + i, false, true);
        onEventMainThread(this.bp);
    }

    public void onEventMainThread(DataFlycGetPushAvoid dataFlycGetPushAvoid) {
        if (dji.pilot.fpv.d.b.j(null)) {
            this.bm.onEventMainThread(dataFlycGetPushAvoid);
        }
    }

    public void onEventMainThread(SdModeView sdModeView) {
        this.bp = sdModeView;
        SdModeView.a aVar = sdModeView.a;
        if (this.bo == null) {
            this.bo = new dji.pilot.publics.widget.b(this, false);
            this.bo.a(R.string.app_tip);
            this.bo.d(R.string.app_enter);
            this.bo.a(new 19(this));
        }
        switch (27.d[aVar.ordinal()]) {
            case 1:
                this.bo.b(R.string.mc_switch_sd_mode_success);
                this.bo.show();
                sdModeView.setBlackStatus(false);
                dji.thirdparty.a.c.a().e(aVar);
                return;
            case 2:
                this.bn = 0;
                this.af.sendEmptyMessageDelayed(u, 1000);
                return;
            default:
                this.bo.b(R.string.mc_switch_sd_mode_error);
                this.bo.show();
                sdModeView.setBlackStatus(false);
                return;
        }
    }

    public void onEventMainThread(DJICustomType dJICustomType) {
        boolean z = true;
        dji.pilot.groundStation.a.a instance;
        switch (27.e[dJICustomType.ordinal()]) {
            case 1:
                if (!this.mGuideShowing && this.aq.h()) {
                    this.aq.b();
                    return;
                }
                return;
            case 2:
                instance = dji.pilot.groundStation.a.a.getInstance(null);
                if (instance == null || !instance.c() || !instance.p()) {
                    if ((this.ab != null && this.ab.isShown()) || this.mGuideShowing || !this.aq.c() || this.L.hasDlgShowing()) {
                        return;
                    }
                    return;
                }
                return;
            case 3:
                instance = dji.pilot.groundStation.a.a.getInstance(null);
                if (instance == null || !instance.c() || !instance.p()) {
                    if ((this.ab == null || !this.ab.isShown()) && !this.mGuideShowing) {
                        this.L.handleBatteryClickPush();
                        return;
                    }
                    return;
                }
                return;
            case 4:
                DataSpecialControl.getInstance().resetGimbal().start(20);
                return;
            case 6:
                DataRcSetGimbalControlMode.MODE mode;
                if (DataRcGetGimbalControlMode.getInstance().getMode() == DataRcSetGimbalControlMode.MODE.a) {
                    mode = DataRcSetGimbalControlMode.MODE.c;
                } else {
                    mode = DataRcSetGimbalControlMode.MODE.a;
                }
                DataRcSetGimbalControlMode.getInstance().a(mode).start(new 20(this, mode));
                return;
            case 7:
                dji.pilot.groundStation.a.a instance2 = dji.pilot.groundStation.a.a.getInstance(null);
                if (instance2 != null && instance2.q()) {
                    this.aq.a(true);
                    this.M.go();
                    this.ac.hideMenu(true);
                    return;
                }
                return;
            case 8:
                if (this.aq.c()) {
                }
                if (T()) {
                    this.aq.f();
                    this.M.show();
                    this.ac.showMenu();
                    return;
                }
                return;
            case 9:
                if (this.aq.h()) {
                    this.aq.b();
                    return;
                }
                this.aq.g();
                this.aq.b();
                this.aq.a(true);
                return;
            case 10:
                if ((this.ab == null || !this.ab.isShown()) && !this.mGuideShowing) {
                    U();
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

    private void f(boolean z) {
        this.ag.a(z, DataOsdGetPushCommon.getInstance().getVoltageWarning(), DataCenterGetPushBatteryCommon.getInstance().getRelativeCapacity());
    }

    private void U() {
        if (ServiceManager.getInstance().isConnected()) {
            this.af.sendEmptyMessage(n);
            this.bq.a(dji.midware.data.config.P3.b.a.SetMetering).a(0).start(new 21(this));
        }
    }

    private void V() {
        this.af.sendMessage(this.af.obtainMessage(32768, 1, 0));
        this.af.sendMessageDelayed(this.af.obtainMessage(32768, 0, 0), 2000);
    }

    private void a(MotionEvent motionEvent) {
        if (this.b != 0) {
            int x = ((int) ((motionEvent.getX() - ((float) this.aL[0])) / ((float) this.b))) + (((int) ((motionEvent.getY() - ((float) this.aM[0])) / ((float) this.a))) * this.c);
            this.af.sendMessage(this.af.obtainMessage(20480, motionEvent));
            DJILogHelper.getInstance().LOGD(this.TAG, "meteringarea index=" + x, false, false);
            DataCameraSetMeteringArea.getInstance().a(x).start(new 22(this));
        }
    }

    private void W() {
        X();
    }

    private void X() {
        this.af.removeMessages(p);
        this.aN.a();
    }

    public void c() {
        e.a("FPV_LongPressGesture_ControlGimbal");
        e.c(s.dp);
        this.aN.a(this.bt);
    }

    public Bitmap D() {
        return this.J.getBitmap(Bitmap.createBitmap(this.J.getWidth() / 2, this.J.getHeight() / 2, Config.RGB_565));
    }

    public static void d() {
        aP.runOnUiThread(new 25());
    }

    public static void e() {
        aP.runOnUiThread(new 26());
    }

    public void onEventMainThread(dji.pilot.flyforbid.a.b bVar) {
        switch (27.f[bVar.ordinal()]) {
            case 1:
                this.ak.a(null);
                return;
            case 2:
                this.ak.a(dji.pilot.flyforbid.a.getInstance(this).a());
                return;
            case 3:
                dji.pilot.flyforbid.a.getInstance(this).b();
                return;
            case 4:
                if (this.L.hasDlgShowing()) {
                    this.bv = true;
                    return;
                } else {
                    dji.pilot.flyforbid.a.getInstance(this).a((Context) this);
                    return;
                }
            case 5:
                NFZLogUtil.savedLOG("nfz log 6 b d limits" + dji.pilot.flyforbid.a.getInstance(this).c().b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + dji.pilot.flyforbid.a.getInstance(this).c().c);
                this.ak.b(dji.pilot.flyforbid.a.getInstance(this).c());
                NFZLogUtil.savedLOG("nfz log 6 a d limits");
                return;
            case 6:
                if (!this.L.hasDlgShowing()) {
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

    public boolean f() {
        return this.L.getVisibility() == 0;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 2 && motionEvent.getAction() == 261) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.bw < 800) {
                try {
                    Class.forName("com.dji.tools.base.InnerToolsDialog").getMethod("showInnerTools", new Class[]{Context.class}).invoke(null, new Object[]{this});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.bw = currentTimeMillis;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
