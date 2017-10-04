package dji.pilot.newfpv.topbar.widget;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.m;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.Data2100GetPushCheckStatus;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.EncryptStatus;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon.ConnStatus;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPushCheckStatus;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataGimbalGetPushCheckStatus;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOfdmGetPushCheckStatus;
import dji.midware.data.model.P3.DataOsdGetPushChannalStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.BatteryType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.GOHOME_STATUS;
import dji.midware.data.model.P3.DataOsdGetPushCommon.IMU_INITFAIL_REASON;
import dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdGetPushSignalQuality;
import dji.midware.data.model.P3.DataRcGetPushBatteryInfo;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.midware.data.model.P3.DataWifiGetPushElecSignal;
import dji.midware.data.model.P3.DataWifiGetPushSignal;
import dji.pilot.R;
import dji.pilot.battery.a.c;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.c$a;
import dji.pilot.fpv.d.c.h;
import dji.pilot.fpv.d.f;
import dji.pilot.publics.control.a$d;
import dji.pilot.publics.e.e;
import dji.pilot.publics.widget.DJIMarqueeTextViewV2;
import dji.pilot2.simulator.d;
import dji.publics.DJIUI.DJIImageView;
import dji.sdksharedlib.DJISDKCache;
import java.lang.ref.WeakReference;

@SuppressLint({"NewApi"})
public class DJIFpvTipView extends FrameLayout implements c$a, h, a {
    private static final long D = 5000;
    private static long E = 0;
    private static long F;
    private static long G;
    private static long H;
    private static long I;
    private static long J;
    private static long K;
    private static long L;
    private static long M;
    private static long N;
    private static long O;
    private static long P;
    private static long Q;
    private static long R;
    private static long S;
    private static long T;
    private static long U;
    private static long V;
    private static long W;
    protected static final String a = DJIFpvTipView.class.getSimpleName();
    private static long aA;
    private static long aB;
    private static long aC;
    private static long aD;
    private static long aE;
    private static long aF;
    private static long aG;
    private static long aH;
    private static long aI;
    private static long aJ;
    private static long aK;
    private static long aL;
    private static long aM;
    private static long aN;
    private static long aO;
    private static long aP;
    private static long aQ;
    private static long aR;
    private static long aS;
    private static long aT;
    private static long aU;
    private static long aa;
    private static long ab;
    private static long ac;
    private static long ad;
    private static long ae;
    private static long af;
    private static long ag;
    private static long ah;
    private static long ai;
    private static long aj;
    private static long ak;
    private static long al;
    private static long am;
    private static long an;
    private static long ao;
    private static long ap;
    private static long aq;
    private static long ar;
    private static long as;
    private static long at;
    private static long au;
    private static long av;
    private static long aw;
    private static long ax;
    private static long ay;
    private static long az;
    private long aV = aU;
    private dji.pilot.publics.c.c$a aW = dji.pilot.publics.c.c$a.Normal;
    private c aX = new c();
    private int aY = 0;
    private int aZ = 0;
    private int ba = 0;
    private int bb = 0;
    private ObjectAnimator bc = null;
    private int bd = 0;
    private int be = 0;
    private boolean bf = false;
    private String bg = "";
    private Context bh = null;
    private DJIImageView bi = null;
    private DJIMarqueeTextViewV2 bj = null;
    private long bk = 0;
    private b bl = null;
    private volatile int bm = 0;
    private final Runnable bn = new Runnable(this) {
        final /* synthetic */ DJIFpvTipView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!dji.pilot.publics.control.a.getInstance().l() && !DataOfdmGetPushCheckStatus.getInstance().isFirmwareNotMatch() && this.a.a(this.a.aV, DJIFpvTipView.ar)) {
                this.a.aV = this.a.aV & (DJIFpvTipView.ar ^ -1);
                this.a.updateView();
            }
        }
    };
    private a[] bo;
    private boolean bp = false;
    final DataFlycGetParams n = new DataFlycGetParams();

    private class a {
        public int a;
        public int b;
        public boolean c;
        public long d;
        final /* synthetic */ DJIFpvTipView e;

        public a(DJIFpvTipView dJIFpvTipView, int i, boolean z, int i2, long j) {
            this.e = dJIFpvTipView;
            this.a = i2;
            this.b = i;
            this.c = z;
            this.d = j;
        }
    }

    private static final class b extends Handler {
        private final WeakReference<DJIFpvTipView> a;

        public b(DJIFpvTipView dJIFpvTipView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIFpvTipView);
        }

        public void handleMessage(Message message) {
            DJIFpvTipView dJIFpvTipView = (DJIFpvTipView) this.a.get();
            if (dJIFpvTipView != null) {
                switch (message.what) {
                    case 256:
                        dJIFpvTipView.a(false);
                        return;
                    case 4096:
                        dJIFpvTipView.c();
                        return;
                    case 4097:
                        dJIFpvTipView.d();
                        return;
                    case 4098:
                        dJIFpvTipView.f();
                        return;
                    case 4099:
                        dJIFpvTipView.e();
                        return;
                    case 4100:
                        dJIFpvTipView.g();
                        return;
                    case 4101:
                        dJIFpvTipView.h();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    static {
        F = 0;
        G = 0;
        H = 0;
        I = 0;
        J = 0;
        K = 0;
        L = 0;
        M = 0;
        N = 0;
        O = 0;
        P = 0;
        Q = 0;
        R = 0;
        S = 0;
        T = 0;
        U = 0;
        V = 0;
        W = 0;
        aa = 0;
        ab = 0;
        ac = 0;
        ad = 0;
        ae = 0;
        af = 0;
        ag = 0;
        ah = 0;
        ai = 0;
        aj = 0;
        ak = 0;
        al = 0;
        am = 0;
        an = 0;
        ao = 0;
        ap = 0;
        aq = 0;
        ar = 0;
        as = 0;
        at = 0;
        au = 0;
        av = 0;
        aw = 0;
        ax = 0;
        ay = 0;
        az = 0;
        aA = 0;
        aB = 0;
        aC = 0;
        aD = 0;
        aE = 0;
        aF = 0;
        aG = 0;
        aH = 0;
        aI = 0;
        aJ = 0;
        aK = 0;
        aL = 0;
        aM = 0;
        aN = 0;
        aO = 0;
        aP = 0;
        aQ = 0;
        aR = 0;
        aS = 0;
        aT = 0;
        aU = F | I;
        F = 1 << null;
        G = 1 << 1;
        H = 1 << 2;
        I = 1 << 3;
        J = 1 << 4;
        K = 1 << 5;
        L = 1 << 6;
        M = 1 << 7;
        N = 1 << 8;
        O = 1 << 9;
        P = 1 << 10;
        Q = 1 << 11;
        R = 1 << 12;
        S = 1 << 13;
        aR = 1 << 14;
        T = 1 << 15;
        U = 1 << 16;
        aP = 1 << 17;
        V = 1 << 18;
        W = 1 << 19;
        aa = 1 << 20;
        ab = 1 << 21;
        aO = 1 << 22;
        ac = 1 << 23;
        ad = 1 << 24;
        ae = 1 << 25;
        af = 1 << 26;
        ag = 1 << 27;
        ah = 1 << 28;
        ai = 1 << 29;
        aj = 1 << 30;
        ak = 1 << 31;
        al = 1 << 32;
        am = 1 << 33;
        an = 1 << 34;
        ao = 1 << 35;
        ap = 1 << 36;
        aQ = 1 << 37;
        aq = 1 << 38;
        ar = 1 << 39;
        as = 1 << 40;
        at = 1 << 41;
        au = 1 << 42;
        aS = a(43);
        av = 1 << 43;
        aw = 1 << 44;
        ax = 1 << 45;
        ay = 1 << 46;
        aA = 1 << 47;
        az = 1 << 48;
        aB = 1 << 49;
        aC = 1 << 50;
        aD = 1 << 51;
        aE = 1 << 52;
        aF = 1 << 53;
        aG = 1 << 54;
        aH = 1 << 55;
        aI = 1 << 56;
        aJ = 1 << 57;
        aK = 1 << 58;
        aL = 1 << 59;
        aT = a(60);
        aM = 1 << 60;
        aN = 1 << 61;
        aU = F | I;
    }

    private static long a(int i) {
        long j = 0;
        while (i > 0) {
            j = (j << 1) | 1;
            i--;
        }
        return j;
    }

    public DJIFpvTipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.bh = context;
        if (!isInEditMode()) {
            this.aY = R.drawable.fpv_topbar_tips_bg_gray;
            this.aZ = R.drawable.fpv_topbar_tips_bg_red;
            this.ba = R.drawable.fpv_topbar_tips_bg_yellow;
            this.bb = R.drawable.fpv_topbar_tips_bg_green;
            this.bc = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.c);
            this.bl = new b(this);
        }
    }

    public void initRes() {
        this.bo = new a[]{new a(this, this.bb, false, R.string.fpv_tip_normal, 0), new a(this, this.aY, false, R.string.fpv_tip_disconnect, F), new a(this, this.ba, false, R.string.mc_sd_mode_toptips, G), new a(this, this.aZ, false, this.aW.b(), H), new a(this, this.aZ, false, R.string.fpv_tip_no_video_signal, I), new a(this, this.aZ, false, R.string.fpv_tip_mc_error, J), new a(this, this.aZ, false, R.string.fpv_tip_imu_cali, K), new a(this, this.aZ, false, R.string.fpv_tip_camera_encrypt_error, L), new a(this, this.aZ, false, R.string.fpv_tip_compass_error, M), new a(this, this.aZ, false, R.string.fpv_tip_esc_error_sky, N), new a(this, this.aZ, false, R.string.fpv_tip_esc_error, O), new a(this, this.aZ, false, R.string.patti_error_top_tip, P), new a(this, this.aZ, false, R.string.fpv_tip_barometer_dead, Q), new a(this, this.aZ, false, R.string.fpv_tip_front_vision_cali, R), new a(this, this.aZ, false, R.string.fpv_tip_down_vision_cali, S), new a(this, this.aZ, false, R.string.fpv_tip_back_vision_cali, aR), new a(this, this.aZ, false, R.string.fpv_tip_vision_error, T), new a(this, this.aZ, false, R.string.fpv_tip_battery_exception, U), new a(this, this.aZ, false, R.string.battery_top_tips_exception, aP), new a(this, this.aZ, true, R.string.fpv_tip_battery_broken, V), new a(this, this.aZ, true, R.string.fpv_tip_battery_over_current, W), new a(this, this.aZ, true, R.string.fpv_tip_battery_over_temp, aa), new a(this, this.aZ, true, R.string.fpv_tip_battery_low_temp, ab), new a(this, this.aZ, false, R.string.fpv_tip_normal, aO), new a(this, this.ba, false, R.string.fpv_tip_imu_initializing, ac), new a(this, this.aZ, false, R.string.fpv_tip_sensor_error, ad), new a(this, this.aZ, false, R.string.fpv_tip_imu_error, ae), new a(this, this.aZ, false, R.string.fpv_tip_compass_error, af), new a(this, this.ba, true, R.string.fpv_tip_imu_heating, ag), new a(this, this.aZ, false, R.string.fpv_tip_cant_takeoff_locked, ah), new a(this, this.aZ, false, R.string.fpv_tip_cant_takeoff_novice, ai), new a(this, this.aZ, false, R.string.fpv_tip_cant_takeoff, aj), new a(this, this.aZ, true, R.string.fpv_tip_serious_low_voltage_landing, ak), new a(this, this.aZ, true, R.string.fpv_tip_serious_low_voltage, al), new a(this, this.aZ, true, R.string.fpv_tip_serious_low_power_landing, am), new a(this, this.aZ, true, R.string.fpv_tip_serious_low_power, an), new a(this, this.aZ, true, R.string.fpv_tip_smart_low_power_landing, ao), new a(this, this.aZ, true, R.string.fpv_tip_smart_low_power, ap), new a(this, this.aZ, true, R.string.fpv_tip_low_voltage, aQ), new a(this, this.ba, true, R.string.fpv_tip_not_enough_force, aq), new a(this, this.aZ, false, R.string.fpv_tip_firmware_no_match, ar), new a(this, this.aZ, false, R.string.fpv_tip_gohome_failed, as), new a(this, this.aZ, true, R.string.fpv_tip_failsafe_gohome, at), new a(this, this.aZ, true, R.string.fpv_tip_failsafe, au), new a(this, this.aZ, true, R.string.fpv_tip_low_power_gohome, av), new a(this, this.aZ, true, R.string.fpv_tip_low_power, aw), new a(this, this.aZ, true, R.string.fpv_tip_low_rc_power, ax), new a(this, this.aZ, true, R.string.fpv_tip_low_rc_signal, ay), new a(this, this.aZ, true, R.string.fpv_tip_rc_signal_disturb, aA), new a(this, this.aZ, true, R.string.fpv_tip_low_radio_signal, az), new a(this, this.aZ, true, R.string.fpv_tip_radio_signal_disturb, aB), new a(this, this.ba, false, R.string.fpv_tip_gale_warning, aC), new a(this, this.ba, false, R.string.fpv_tip_gimbal_stuck, aD), new a(this, this.ba, false, R.string.check_list_gimbal_startup_block_detail, aE), new a(this, this.ba, false, R.string.check_list_gimbal_wait_restart_detail, aF), new a(this, this.ba, false, R.string.check_gimbal_vibration_detail, aG), new a(this, this.ba, false, R.string.fpv_tip_non_gps_in_the_air, aH), new a(this, this.ba, false, R.string.fpv_tip_non_gps, aI), new a(this, this.ba, false, R.string.fpv_tip_atti_state_in_the_air, aJ), new a(this, this.ba, false, R.string.fpv_tip_atti_state, aK), new a(this, this.ba, false, R.string.fpv_tip_chlstatus_poor, aL), new a(this, this.bb, false, R.string.fpv_tip_gohome, aM), new a(this, this.bb, false, R.string.fpv_tip_normal_in_the_air, aN)};
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            dji.thirdparty.a.c.a().a(this);
            resetStatus(true);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().d(this);
    }

    public void resetStatus(boolean z) {
        if (z) {
            if (ServiceManager.getInstance().isConnected()) {
                connect(true);
            } else {
                disconnect();
            }
            if (ServiceManager.getInstance().isRemoteOK()) {
                cameraConnect(true);
                return;
            } else {
                cameraDisconnect();
                return;
            }
        }
        cameraDisconnect();
        disconnect();
    }

    public void connect(boolean z) {
        this.aV &= F ^ -1;
        if (d.h()) {
            this.aV &= I ^ -1;
        }
        onEventMainThread(dji.pilot.publics.c.c.getInstance().a());
        needUptate(dji.pilot.publics.control.a.getInstance().o(), dji.pilot.publics.control.a.getInstance().l());
        updateView();
    }

    public void disconnect() {
        if (!isInEditMode()) {
            boolean a = a(this.aV, I);
            this.aV = F;
            if (a && !d.h()) {
                this.aV |= I;
            }
            this.bl.removeCallbacksAndMessages(null);
            updateView();
        }
    }

    public void cameraConnect(boolean z) {
        needUptate(dji.pilot.publics.control.a.getInstance().o(), dji.pilot.publics.control.a.getInstance().l());
        a(true);
        g();
    }

    public void cameraDisconnect() {
        this.aV &= G ^ -1;
        updateView();
    }

    protected void a(int i, boolean z) {
        if ((this.bm & i) == 0) {
            this.bm |= i;
            if (!this.bl.hasMessages(256)) {
                this.bl.sendEmptyMessageDelayed(256, 250);
            }
        }
    }

    private void a(boolean z) {
        if (z) {
            this.bm |= a.s;
        }
        long j = this.aV;
        if ((this.bm & 1) != 0) {
            d(false);
            this.bm &= -2;
        }
        if ((this.bm & 2) != 0) {
            j(false);
            this.bm &= -3;
        }
        if ((this.bm & 4) != 0) {
            k(false);
            this.bm &= -5;
        }
        if ((this.bm & 8) != 0) {
            f(false);
            this.bm &= -9;
        }
        if ((this.bm & 16) != 0) {
            l(false);
            this.bm &= -17;
        }
        if ((this.bm & 32) != 0) {
            e(false);
            this.bm &= -33;
        }
        if ((this.bm & 128) != 0) {
            m(false);
            this.bm &= -129;
        }
        if ((this.bm & 256) != 0) {
            o(false);
            this.bm &= -257;
        }
        if ((this.bm & 512) != 0) {
            p(false);
            this.bm &= -513;
        }
        if ((this.bm & 1024) != 0) {
            i(false);
            this.bm &= -1025;
        }
        if ((this.bm & 4096) != 0) {
            n(false);
            this.bm &= -4097;
        }
        if ((this.bm & 8192) != 0) {
            c(false);
            this.bm &= -8193;
        }
        if ((this.bm & 16384) != 0) {
            b(false);
            this.bm &= -16385;
        }
        if (this.aV != j) {
            updateView();
        }
    }

    private void b(boolean z) {
        DataGimbalGetPushCheckStatus instance = DataGimbalGetPushCheckStatus.getInstance();
        if (instance.isGetted()) {
            long j = this.aV;
            if (instance.getVibrateStatus()) {
                j |= aG;
            } else {
                j &= aG ^ -1;
            }
            if (instance.getLimitStatus() == 1) {
                j = (j | aE) & (aF ^ -1);
            } else if (instance.getLimitStatus() == 2) {
                j = (j & (aE ^ -1)) | aF;
            } else {
                j = (j & (aE ^ -1)) & (aF ^ -1);
            }
            if (this.aV != j) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    private void c(boolean z) {
        DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
        if (instance.isGetted()) {
            long j;
            long j2 = this.aV;
            if (instance.isBigGaleWarning()) {
                j = aC | j2;
            } else {
                j = (aC ^ -1) & j2;
            }
            if (this.aV != j) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    private void d(boolean z) {
        Data2100GetPushCheckStatus instance = Data2100GetPushCheckStatus.getInstance();
        if (instance.isGetted()) {
            long j = this.aV;
            if (instance.isForeSightDemarkAbnormal()) {
                j |= R;
            } else {
                j &= R ^ -1;
            }
            if (instance.isDownSightDemarkAbnormal()) {
                j |= S;
            } else {
                j &= S ^ -1;
            }
            if (instance.isBackSightDemarkAbnormal()) {
                j |= aR;
            } else {
                j &= aR ^ -1;
            }
            if (instance.isAutoExpAbnormal() || instance.isDepthImageAbnormal() || instance.isVOAbnormal() || instance.isAvoidanceAbnormal() || instance.isStoreAbnormal() || instance.isInnerAbnormal() || instance.isLRTAbnormal()) {
                j |= T;
            } else {
                j &= T ^ -1;
            }
            if (this.aV != j) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    private void e(boolean z) {
        DataGimbalGetPushParams instance = DataGimbalGetPushParams.getInstance();
        if (instance.isGetted()) {
            long j;
            long j2 = this.aV;
            if (instance.isStuck()) {
                j = aD | j2;
            } else {
                j = (aD ^ -1) & j2;
            }
            if (this.aV != j) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    private void f(boolean z) {
        DataFlycGetPushCheckStatus instance = DataFlycGetPushCheckStatus.getInstance();
        if (instance.isGetted()) {
            long j;
            long j2 = this.aV;
            if (instance.getIMUAdvanceCaliStatus() || instance.getIMUBasicCaliStatus() || instance.getVersionStatus()) {
                a(j2, K, c$a.aU_);
                j2 |= K;
            } else {
                j2 &= K ^ -1;
            }
            if (instance.getIMUHorizontalCaliStatus() || instance.getIMUDirectionStatus() || instance.getIMUInitStatus() || instance.getPressInitStatus() || instance.getAccDataStatus() || instance.getGyroscopeStatus() || instance.getPressDataStatus() || instance.getAircraftAttiStatus() || instance.getIMUDataStatus() || instance.getDataLoggerStatus()) {
                a(j2, J, c$a.aV_);
                j = J | j2;
            } else {
                j = (J ^ -1) & j2;
            }
            if (this.aV != j) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    private void g(boolean z) {
        DataOsdGetPushChannalStatus instance = DataOsdGetPushChannalStatus.getInstance();
        if (instance.isGetted()) {
            long j;
            long j2 = this.aV;
            if (dji.pilot.fpv.d.b.a(instance.getChannelStatus())) {
                j = aL | j2;
            } else {
                j = (aL ^ -1) & j2;
            }
            if (j != this.aV) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    private void h(boolean z) {
        DataWifiGetPushElecSignal instance = DataWifiGetPushElecSignal.getInstance();
        if (instance.isGetted()) {
            long j;
            long j2 = this.aV;
            if (dji.pilot.fpv.d.b.a(instance.getSignalStatus())) {
                j = aL | j2;
            } else {
                j = (aL ^ -1) & j2;
            }
            if (j != this.aV) {
                this.aV = j;
                updateView();
            }
        }
    }

    private void i(boolean z) {
        DataSmartBatteryGetPushDynamicData instance = DataSmartBatteryGetPushDynamicData.getInstance();
        if (instance.isGetted()) {
            long status = instance.getStatus();
            long j = this.aV;
            if ((3 & status) != 0) {
                j |= W;
            } else {
                j &= W ^ -1;
            }
            if ((12 & status) != 0) {
                j |= aa;
            } else {
                j &= aa ^ -1;
            }
            if ((48 & status) != 0) {
                j |= ab;
            } else {
                j &= ab ^ -1;
            }
            if ((2097152 & status) != 0) {
                j |= aO;
            } else {
                j &= aO ^ -1;
            }
            if ((status & 4128768) != 0) {
                j |= aP;
            } else {
                j &= aP ^ -1;
            }
            if (this.aV != j) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    private void j(boolean z) {
        DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
        if (instance.isGetted()) {
            long j = this.aV;
            if (a(j, F)) {
                j &= F ^ -1;
            }
            if (instance.getEncryptStatus() != EncryptStatus.CHECK_SUCCESS) {
                j |= L;
            } else {
                j &= L ^ -1;
            }
            if (this.aV != j) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    private void k(boolean z) {
        DataCenterGetPushBatteryCommon instance = DataCenterGetPushBatteryCommon.getInstance();
        if (instance.isGetted()) {
            long j;
            long j2 = this.aV;
            ConnStatus connStatus = instance.getConnStatus();
            if (dji.pilot.publics.e.a.b()) {
                DataSmartBatteryGetPushDynamicData instance2 = DataSmartBatteryGetPushDynamicData.getInstance();
                connStatus = instance2.isGetted() ? ConnStatus.ofData((int) instance2.getStatus()) : ConnStatus.EXCEPTION;
            }
            Object obj = 1;
            if (BatteryType.Smart != DataOsdGetPushCommon.getInstance().getBatteryType()) {
                obj = null;
            }
            if ((connStatus == ConnStatus.INVALID || connStatus == ConnStatus.EXCEPTION) && r1 != null) {
                a(j2, U, c$a.aT_);
                j = U | j2;
            } else {
                j = (U ^ -1) & j2;
            }
            this.aX.b(instance.getErrorType());
            if (this.aX.l() != (byte) 0) {
                j |= V;
            } else {
                j &= V ^ -1;
            }
            if (this.aX.d() || this.aX.e()) {
                j |= W;
            } else {
                j &= W ^ -1;
            }
            if (this.aX.f() || this.aX.g()) {
                j |= aa;
            } else {
                j &= aa ^ -1;
            }
            if (this.aX.h() || this.aX.i()) {
                j |= ab;
            } else {
                j &= ab ^ -1;
            }
            if (this.aV != j) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    private void l(boolean z) {
        DataFlycGetPushSmartBattery instance = DataFlycGetPushSmartBattery.getInstance();
        if (instance.isGetted()) {
            long j;
            long j2 = this.aV;
            int status = instance.getStatus();
            if ((status & 32) != 0) {
                j = (al | j2) & (aQ ^ -1);
            } else if ((status & 16) != 0) {
                j = (aQ | j2) & (al ^ -1);
            } else {
                j = ((aQ ^ -1) & j2) & (al ^ -1);
            }
            if (this.aV != j) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    private void m(boolean z) {
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        if (instance.isGetted()) {
            boolean z2;
            long j;
            long j2 = this.aV;
            if (a(j2, F)) {
                j2 &= F ^ -1;
            }
            FLYC_STATE flycState = instance.getFlycState();
            boolean a = a(flycState);
            Object obj = !instance.getRcState() ? 1 : null;
            IMU_INITFAIL_REASON iMUinitFailReason = instance.getIMUinitFailReason();
            if (i() && dji.pilot.publics.e.a.c()) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 != this.bp) {
                this.bp = z2;
                updateView();
            }
            if (!instance.isNotEnoughForce()) {
                this.bl.removeMessages(4099);
                j = j2 & (aq ^ -1);
            } else if (a(j2, aq) || this.bl.hasMessages(4099)) {
                j = j2;
            } else {
                this.bl.sendEmptyMessageDelayed(4099, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                j = j2;
            }
            if (dji.pilot.fpv.d.b.a(instance)) {
                if (!instance.isBarometerDeadInAir()) {
                    j = (O ^ -1) & ((N ^ -1) & j);
                } else if (instance.groundOrSky() == 2) {
                    j = (O ^ -1) & (N | j);
                } else {
                    j = O | ((N ^ -1) & j);
                }
            } else if (instance.isBarometerDeadInAir()) {
                a(j, Q, c$a.cr_);
                j |= Q;
            } else {
                j &= Q ^ -1;
            }
            if (iMUinitFailReason == IMU_INITFAIL_REASON.GyroDead || iMUinitFailReason == IMU_INITFAIL_REASON.AcceDead || iMUinitFailReason == IMU_INITFAIL_REASON.CompassDead || iMUinitFailReason == IMU_INITFAIL_REASON.BarometerDead || iMUinitFailReason == IMU_INITFAIL_REASON.BarometerNegative || iMUinitFailReason == IMU_INITFAIL_REASON.BarometerNoiseTooLarge) {
                a(j, ad, c$a.aW_);
                j |= ad;
            } else {
                j &= ad ^ -1;
            }
            if (iMUinitFailReason == IMU_INITFAIL_REASON.CompassModTooLarge || iMUinitFailReason == IMU_INITFAIL_REASON.CompassNoiseTooLarge) {
                a(j, af, c$a.aO_);
                j2 = af | j;
            } else {
                j2 = (af ^ -1) & j;
            }
            if (!dji.pilot.fpv.d.b.m()) {
                if (iMUinitFailReason == IMU_INITFAIL_REASON.GyroBiasTooLarge || iMUinitFailReason == IMU_INITFAIL_REASON.AcceBiasTooLarge) {
                    j2 |= ae;
                } else {
                    j2 &= ae ^ -1;
                }
            }
            if (iMUinitFailReason == IMU_INITFAIL_REASON.WaitingMcStationary || iMUinitFailReason == IMU_INITFAIL_REASON.AcceMoveTooLarge || iMUinitFailReason == IMU_INITFAIL_REASON.McHeaderMoved || iMUinitFailReason == IMU_INITFAIL_REASON.McVirbrated) {
                j = ac | j2;
            } else {
                j = (ac ^ -1) & j2;
            }
            if (obj == null || dji.logic.c.b.getInstance().a(null)) {
                j2 = (au ^ -1) & j;
            } else {
                a(j, au, c$a.aR_);
                j2 = au | j;
            }
            if (flycState == FLYC_STATE.GoHome) {
                j2 |= aM;
            } else {
                j2 &= aM ^ -1;
            }
            if (obj == null || !a(j2, aM)) {
                j = (at ^ -1) & j2;
            } else {
                j = at | j2;
                a(j, at, c$a.aX_);
            }
            int gpsNum = instance.getGpsNum();
            if (dji.pilot.fpv.d.b.e()) {
                j2 = (aI ^ -1) & j;
            } else {
                j2 = aI | j;
            }
            if (dji.pilot.fpv.d.b.e()) {
                j2 &= aH ^ -1;
            } else if (i()) {
                j2 = (j2 | aH) & (aI ^ -1);
            } else {
                j2 &= aH ^ -1;
            }
            if (!dji.pilot.fpv.d.b.a(flycState) && dji.pilot.fpv.d.b.b(gpsNum)) {
                j = (as ^ -1) & j2;
            } else if (a(j2, aM)) {
                j = as | j2;
            } else {
                j = (as ^ -1) & j2;
            }
            int voltageWarning = instance.getVoltageWarning();
            FLIGHT_ACTION flightAction = instance.getFlightAction();
            if (voltageWarning == 2 && a) {
                a(j, am, c$a.aQ_);
                j2 = am | j;
            } else {
                j2 = (am ^ -1) & j;
            }
            if (!(i.getInstance().c() == ProductType.A3 || i.getInstance().c() == ProductType.N3)) {
                if (flightAction != FLIGHT_ACTION.SERIOUS_LOW_VOLTAGE_LANDING) {
                    j2 = (j2 & (ak ^ -1)) & (al ^ -1);
                } else if (a) {
                    j2 = (j2 | ak) & (al ^ -1);
                } else {
                    j2 = (j2 | al) & (ak ^ -1);
                }
            }
            if (flightAction != FLIGHT_ACTION.SMART_POWER_LANDING) {
                j2 = (j2 & (ao ^ -1)) & (ap ^ -1);
            } else if (a) {
                j2 = (j2 | ao) & (ap ^ -1);
            } else {
                j2 = (j2 | ap) & (ao ^ -1);
            }
            if (voltageWarning == 2) {
                j2 = ((j2 | an) & (aw ^ -1)) & (av ^ -1);
            } else if (voltageWarning == 1) {
                j = (an ^ -1) & (j2 | aw);
                if (a(j, aM)) {
                    a(j, av, c$a.aN_);
                    j2 = av | j;
                } else {
                    j2 = (av ^ -1) & j;
                }
            } else {
                j2 = ((j2 & (an ^ -1)) & (aw ^ -1)) & (av ^ -1);
            }
            if (!dji.pilot.fpv.d.b.m()) {
                if (!instance.getCompassError()) {
                    this.bl.removeMessages(4097);
                    j2 &= M ^ -1;
                } else if (!this.bl.hasMessages(4097)) {
                    this.bl.sendEmptyMessageDelayed(4097, 1000);
                }
            }
            if (instance.isImuPreheatd()) {
                j2 &= ag ^ -1;
            } else {
                j2 |= ag;
            }
            MotorStartFailedCause motorStartFailedCause = MotorStartFailedCause.None;
            if (!instance.isMotorUp()) {
                motorStartFailedCause = instance.getMotorStartCauseNoStartAction();
            }
            boolean i = i();
            boolean isGpsUsed = instance.isGpsUsed();
            if (MotorStartFailedCause.None == motorStartFailedCause || MotorStartFailedCause.OTHER == motorStartFailedCause) {
                j2 = ((j2 & (ah ^ -1)) & (aj ^ -1)) & (ai ^ -1);
            } else if (motorStartFailedCause == MotorStartFailedCause.DeviceLocked) {
                j2 = ((j2 | ah) & (ai ^ -1)) & (aj ^ -1);
            } else if (motorStartFailedCause == MotorStartFailedCause.NoviceProtected) {
                j2 = ((j2 & (aj ^ -1)) | ai) & (ah ^ -1);
            } else {
                j2 = ((j2 & (ah ^ -1)) & (ai ^ -1)) | aj;
            }
            int[] a2 = dji.pilot.fpv.d.b.a(instance.getFlycState(), instance.isVisionUsed(), false);
            if (a2[0] == R.string.ctrl_mode_atti || a2[0] == R.string.ctrl_mode_patti) {
                if (i) {
                    j2 |= aJ;
                } else {
                    j2 |= aK;
                }
                j2 = (j2 & (aH ^ -1)) & (aI ^ -1);
            } else {
                j2 = (j2 & (aK ^ -1)) & (aJ ^ -1);
                if (i && !isGpsUsed) {
                    j2 |= aH;
                } else if (!(i || isGpsUsed)) {
                    j2 |= aI;
                }
            }
            if (dji.pilot.fpv.d.b.e()) {
                j = (aJ ^ -1) & j2;
            } else if (i()) {
                j = (aK ^ -1) & (j2 | aJ);
            } else {
                j = (aJ ^ -1) & j2;
            }
            if (instance.getFlycVersion() >= 5) {
                if (f.a(instance.getNonGpsCause())) {
                    a(j, P, c$a.aP_);
                    j |= P;
                    dji.pilot.fpv.d.b.a(true);
                } else {
                    j &= P ^ -1;
                }
            } else if (this.bk != 0) {
                if (instance.groundOrSky() == 2 && a2[0] == R.string.ctrl_mode_patti && dji.pilot.fpv.d.b.b(gpsNum)) {
                    if (a(j, P)) {
                        this.bk = System.currentTimeMillis();
                    } else {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - this.bk > 1000) {
                            a(j, P, c$a.aP_);
                            j |= P;
                            dji.pilot.fpv.d.b.a(true);
                            this.bk = currentTimeMillis;
                        }
                    }
                } else if (!a(j, P)) {
                    this.bk = 0;
                } else if (System.currentTimeMillis() - this.bk > 1000) {
                    j &= P ^ -1;
                    this.bk = 0;
                }
            } else if (instance.groundOrSky() == 2 && a2[0] == R.string.ctrl_mode_patti && dji.pilot.fpv.d.b.b(gpsNum)) {
                this.bk = System.currentTimeMillis();
            }
            if (this.aV != j) {
                this.aV = j;
                updateView();
            } else if (!a(j, aS) && a(j, av)) {
                a(b((int) R.string.fpv_tip_low_power_gohome));
            } else if (!a(j, aT) && a(j, aM)) {
                a(getGoHomeDesc());
            }
            dji.pilot.fpv.d.b.h(a2[0]);
        }
    }

    private boolean a(String str) {
        if (this.bg.equals(str)) {
            return false;
        }
        this.bg = str;
        this.bj.setText(this.bg);
        return true;
    }

    private void n(boolean z) {
        DataWifiGetPushSignal instance = DataWifiGetPushSignal.getInstance();
        if (instance.isGetted() && dji.pilot.fpv.d.b.l(null)) {
            long j;
            long j2 = this.aV;
            if (instance.getSignal() > 50 || d.h()) {
                j = (az ^ -1) & j2;
            } else {
                j = az | j2;
            }
            if (this.aV != j) {
                this.aV = j;
                updateView();
            }
        }
    }

    private void o(boolean z) {
        DataOsdGetPushSignalQuality instance = DataOsdGetPushSignalQuality.getInstance();
        if (instance.isGetted()) {
            int upSignalQuality = instance.getUpSignalQuality();
            long j = this.aV;
            if (upSignalQuality < 50) {
                j |= ay;
            } else {
                j &= ay ^ -1;
            }
            j = b(j, upSignalQuality);
            if (!dji.pilot.fpv.d.b.l(null)) {
                int downSignalQuality;
                if (dji.pilot.c.d.i == 1) {
                    downSignalQuality = instance.getDownSignalQuality();
                } else {
                    downSignalQuality = dji.pilot.fpv.d.b.g(instance.getDownSignalQuality());
                }
                if (downSignalQuality < 50) {
                    j |= az;
                } else {
                    j &= az ^ -1;
                }
                j = a(j, downSignalQuality);
            }
            if (dji.logic.c.b.getInstance().a(null)) {
                j &= ay ^ -1;
            }
            if (this.aV != j) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    private long a(long j, int i) {
        if (!dji.pilot.publics.e.a.f(null)) {
            return j;
        }
        long j2 = ((az ^ -1) & j) & (aB ^ -1);
        if (i == 5 || i == 15) {
            j2 |= az;
        } else if (i == 6 || i == 16) {
            j2 |= aB;
        }
        return j2;
    }

    private long b(long j, int i) {
        if (!dji.pilot.publics.e.a.f(null)) {
            return j;
        }
        long j2 = ((ay ^ -1) & j) & (aA ^ -1);
        if (i == 5 || i == 15) {
            j2 |= ay;
        } else if (i == 6 || i == 16) {
            j2 |= aA;
        }
        return j2;
    }

    private void p(boolean z) {
        DataRcGetPushBatteryInfo instance = DataRcGetPushBatteryInfo.getInstance();
        if (instance.isGetted()) {
            long j;
            int battery = instance.getBattery();
            long j2 = this.aV;
            if (dji.pilot.fpv.d.b.i(battery)) {
                j = ax | j2;
            } else {
                j = (ax ^ -1) & j2;
            }
            if (this.aV != j) {
                this.aV = j;
                if (z) {
                    updateView();
                }
            }
        }
    }

    public void onEventBackgroundThread(Data2100GetPushCheckStatus data2100GetPushCheckStatus) {
        a(1, false);
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        a(32, false);
    }

    public void onEventBackgroundThread(DataFlycGetPushCheckStatus dataFlycGetPushCheckStatus) {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 4) {
            a(8, false);
        }
    }

    public void onEventBackgroundThread(DataGimbalGetPushCheckStatus dataGimbalGetPushCheckStatus) {
        a(16384, false);
    }

    public void onEventBackgroundThread(DataOsdGetPushChannalStatus dataOsdGetPushChannalStatus) {
    }

    public void onEventBackgroundThread(DataWifiGetPushElecSignal dataWifiGetPushElecSignal) {
    }

    public void onEventBackgroundThread(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        a(1024, false);
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        a(2, false);
    }

    public void onEventBackgroundThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        a(4, false);
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (dataOsdGetPushCommon.isGetted() && dataOsdGetPushCommon.getDroneType() != DroneType.NoFlyc) {
            a(128, false);
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        if (i.getInstance().c() == ProductType.A3 || i.getInstance().c() == ProductType.N3) {
            a(16, false);
        }
    }

    public void onEventBackgroundThread(DataWifiGetPushSignal dataWifiGetPushSignal) {
        a(4096, false);
    }

    public void onEventBackgroundThread(DataOsdGetPushSignalQuality dataOsdGetPushSignalQuality) {
        a(256, false);
    }

    public void onEventBackgroundThread(DataRcGetPushBatteryInfo dataRcGetPushBatteryInfo) {
        a(512, false);
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        a(8192, false);
    }

    public void onEventMainThread(dji.setting.ui.flyc.SdModeView.a aVar) {
        long j = this.aV;
        if (dji.setting.ui.flyc.SdModeView.a.SUCCESS == aVar) {
            j |= G;
            dji.pilot.fpv.d.b.b(true);
        } else {
            j &= G ^ -1;
            dji.pilot.fpv.d.b.b(false);
        }
        if (this.aV != j) {
            this.aV = j;
            updateView();
        }
    }

    public void onEventMainThread(dji.pilot.publics.c.c$a dji_pilot_publics_c_c_a) {
        long j = this.aV;
        this.aW = dji_pilot_publics_c_c_a;
        if (dji_pilot_publics_c_c_a == dji.pilot.publics.c.c$a.Normal) {
            j &= H ^ -1;
        } else if (!d.h()) {
            j |= H;
        }
        if (this.aV != j) {
            this.aV = j;
            updateView();
        }
    }

    public void onEventMainThread(a$d dji_pilot_publics_control_a_d) {
        switch (dji_pilot_publics_control_a_d) {
            case YES:
            case NoMatch:
            case YES_G:
            case NoMatch_G:
                needUptate(true, dji.pilot.publics.control.a.getInstance().l());
                return;
            default:
                needUptate(false, dji.pilot.publics.control.a.getInstance().l());
                return;
        }
    }

    public void onEventMainThread(m mVar) {
        long j = this.aV;
        if (m.a == mVar && !d.h()) {
            j |= I;
        } else if (m.b == mVar) {
            j &= I ^ -1;
        }
        if (this.aV != j) {
            this.aV = j;
            updateView();
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            cameraConnect(false);
        } else if (oVar == o.a) {
            cameraDisconnect();
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.b) {
            connect(false);
            g(true);
        } else if (pVar == p.a) {
            disconnect();
        }
    }

    private float getHomeDistance() {
        double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
        double longitude = DataOsdGetPushCommon.getInstance().getLongitude();
        double latitude2 = DataOsdGetPushHome.getInstance().getLatitude();
        double longitude2 = DataOsdGetPushHome.getInstance().getLongitude();
        if (!dji.pilot.fpv.d.b.a(latitude) || !dji.pilot.fpv.d.b.a(latitude2) || !dji.pilot.fpv.d.b.b(longitude) || !dji.pilot.fpv.d.b.b(longitude2)) {
            return 0.0f;
        }
        float[] fArr = new float[2];
        Location.distanceBetween(latitude2, longitude2, latitude, longitude, fArr);
        return fArr[0];
    }

    public void needUptate(boolean z, boolean z2) {
        long j = this.aV;
        if (!z || DataOsdGetPushCommon.getInstance().isMotorUp()) {
            j &= ar ^ -1;
        } else {
            j |= ar;
        }
        if (this.aV != j) {
            this.aV = j;
            updateView();
        }
    }

    private String getGoHomeDesc() {
        GOHOME_STATUS gohomeStatus = DataOsdGetPushCommon.getInstance().getGohomeStatus();
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 5) {
            return b((int) R.string.fpv_tip_gohome);
        }
        if (gohomeStatus == GOHOME_STATUS.PREASCENDING) {
            return this.bh.getString(R.string.gohome_step_preascending);
        }
        if (gohomeStatus == GOHOME_STATUS.ALIGN) {
            return this.bh.getString(R.string.gohome_step_align);
        }
        if (gohomeStatus == GOHOME_STATUS.ASCENDING) {
            return this.bh.getString(R.string.gohome_step_ascending);
        }
        if (gohomeStatus == GOHOME_STATUS.CRUISE) {
            return b((int) R.string.gohome_step_cruise);
        }
        return b((int) R.string.gohome_step_cruise);
    }

    private String b(int i) {
        String string;
        DJILogHelper.getInstance().LOGD("", "distance[" + getHomeDistance() + dji.pilot.usercenter.protocol.d.H, false, true);
        if (DJIGenSettingDataManager.getInstance().v() == 0) {
            string = this.bh.getString(R.string.fpv_imperial, new Object[]{Float.valueOf(e.f(r0))});
        } else {
            string = this.bh.getString(R.string.fpv_metric, new Object[]{Float.valueOf(r0)});
        }
        return this.bh.getString(i, new Object[]{string});
    }

    @SuppressLint({"NewApi"})
    private boolean a(FLYC_STATE flyc_state) {
        if (flyc_state == FLYC_STATE.AutoLanding || flyc_state == FLYC_STATE.AttiLangding) {
            return true;
        }
        return false;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.bi = (DJIImageView) findViewById(R.id.ac6);
            this.bj = (DJIMarqueeTextViewV2) findViewById(R.id.ac7);
            this.bj.setDelay(0);
            this.bc.setTarget(this.bi);
            updateView();
        }
    }

    private boolean a(long j, long j2) {
        return (j & j2) != 0;
    }

    private void a(long j, long j2, String str) {
        if (!a(j, j2)) {
            dji.pilot.fpv.d.e.c(str);
        }
    }

    private void b() {
        boolean z;
        int i;
        String string;
        boolean z2;
        int i2 = R.string.fpv_tip_normal;
        if (i.getInstance().c() == ProductType.Grape2 && DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.NoFlyc) {
            i2 = R.string.fpv_tip_normal_lb2;
        }
        if (i() && dji.pilot.publics.e.a.c()) {
            i2 = R.string.fpv_tip_normal_in_the_air;
        }
        int i3 = this.bb;
        ProductType c = i.getInstance().c();
        int pos = getPos(this.aV);
        if (this.bo == null) {
            initRes();
        }
        if (pos < 0 || pos >= this.bo.length) {
            int i4 = i3;
            z = false;
            i = i2;
            i2 = i4;
        } else {
            a aVar = this.bo[pos];
            i = aVar.a;
            z = aVar.c;
            i2 = aVar.b;
        }
        long lowestOneBit = Long.lowestOneBit(this.aV);
        if (H == lowestOneBit) {
            i = this.aZ;
            pos = this.aW.b();
            string = this.bh.getString(pos);
            i4 = i;
            z2 = z;
            i3 = i4;
        } else if (lowestOneBit != I || (dji.pilot.fpv.d.b.o() && DataGimbalGetPushParams.getInstance().isPushLosed())) {
            if (lowestOneBit != L || ((c == ProductType.A3 || dji.pilot.publics.e.a.d(c) || c == ProductType.N3) && DataGimbalGetPushParams.getInstance().isPushLosed())) {
            }
            String str;
            if (lowestOneBit == ar) {
                pos = R.string.fpv_tip_firmware_no_match;
                String string2 = this.bh.getString(R.string.fpv_tip_firmware_no_match);
                if (dji.pilot.publics.control.a.getInstance().l() || DataOfdmGetPushCheckStatus.getInstance().isFirmwareNotMatch()) {
                    str = string2;
                    z2 = z;
                    i3 = i2;
                    string = str;
                } else {
                    postDelayed(this.bn, 5000);
                    str = string2;
                    z2 = z;
                    i3 = i2;
                    string = str;
                }
            } else if (a(this.aV, av)) {
                pos = R.string.fpv_tip_low_power_gohome;
                z2 = true;
                i3 = i2;
                string = b((int) R.string.fpv_tip_low_power_gohome);
            } else if (lowestOneBit == au) {
                i = R.string.fpv_tip_failsafe;
                r1 = this.bh.getString(R.string.fpv_tip_failsafe);
                if (dji.logic.c.b.getInstance().a(i.getInstance().c())) {
                    i = R.string.fpv_tip_failsafe_wifi_no_rc;
                    r1 = this.bh.getString(R.string.fpv_tip_failsafe_wifi_no_rc);
                }
                str = r1;
                i3 = i2;
                string = str;
                pos = i;
                z2 = true;
            } else if (lowestOneBit == az) {
                i = R.string.fpv_tip_low_radio_signal;
                r1 = this.bh.getString(R.string.fpv_tip_low_radio_signal);
                if (dji.logic.c.b.getInstance().a(i.getInstance().c())) {
                    i = R.string.fpv_tip_bad_channel_quality;
                    r1 = this.bh.getString(R.string.fpv_tip_bad_channel_quality);
                }
                str = r1;
                i3 = i2;
                string = str;
                pos = i;
                z2 = true;
            } else if (a(this.aV, aM)) {
                pos = R.string.fpv_tip_gohome;
                string = getGoHomeDesc();
                z2 = z;
                i3 = this.bb;
            } else {
                pos = i;
                z2 = z;
                i3 = i2;
                string = null;
            }
        } else if (d.h()) {
            this.aV &= I ^ -1;
            pos = i;
            z2 = z;
            i3 = this.bb;
            string = null;
        } else {
            i = this.aZ;
            pos = R.string.fpv_tip_no_video_signal;
            string = this.bh.getString(R.string.fpv_tip_no_video_signal);
            i4 = i;
            z2 = z;
            i3 = i4;
        }
        if (string == null) {
            string = this.bh.getString(pos);
        }
        a(string, i3, z2);
    }

    public int getPos(long j) {
        long a = a(j);
        for (int i = 1; i <= 64; i++) {
            if (a % 2 != 0) {
                return i;
            }
            a >>= 1;
        }
        return -1;
    }

    private long a(long j) {
        return ((I ^ -1) & j) & (L ^ -1);
    }

    public void updateView() {
        b();
    }

    private void a(String str, int i, boolean z) {
        a(str);
        if (this.bf != z) {
            if (this.bf) {
                this.bc.cancel();
                this.bi.setImageAlpha(255);
                this.bi.setAlpha(1.0f);
            } else {
                this.bc.start();
            }
            this.bf = z;
        }
        if (this.be != i) {
            this.be = i;
            this.bi.setBackgroundResource(i);
        }
    }

    private void c() {
        if (!a(this.aV, P)) {
            this.aV |= P;
            dji.pilot.fpv.d.b.a(true);
            updateView();
        }
    }

    private void d() {
        if (!a(this.aV, M)) {
            this.aV |= M;
            updateView();
        }
    }

    private void e() {
        if (DataOsdGetPushCommon.getInstance().isNotEnoughForce() && !a(this.aV, aq)) {
            this.aV |= aq;
            updateView();
        }
    }

    private void f() {
        if (!a(this.aV, aS) && a(this.aV, av)) {
            a(b((int) R.string.fpv_tip_low_power_gohome));
        }
    }

    private void g() {
        if (dji.pilot.fpv.d.b.m()) {
            this.n.setInfos(C).start(new dji.midware.e.d(this) {
                final /* synthetic */ DJIFpvTipView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.bl.sendEmptyMessage(4101);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.bl.sendEmptyMessageDelayed(4100, 2000);
                }
            });
        }
    }

    private void h() {
        if (dji.pilot.fpv.d.b.m()) {
            long j = this.aV;
            int intValue = dji.midware.data.manager.P3.d.read(C[0]).value.intValue();
            if (intValue == 1 || intValue == 0 || intValue == 2) {
                j &= ae ^ -1;
            } else {
                j |= ae;
            }
            intValue = dji.midware.data.manager.P3.d.read(C[1]).value.intValue();
            if (intValue == 1 || intValue == 0) {
                j &= M ^ -1;
            } else {
                j |= M;
            }
            if (j != this.aV) {
                this.aV = j;
                updateView();
            }
            this.bl.sendEmptyMessageDelayed(4100, 2000);
        }
    }

    private boolean i() {
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(dji.sdksharedlib.a.b.f(dji.sdksharedlib.b.e.ab));
        if (availableValue != null) {
            return dji.sdksharedlib.a.a.b(availableValue.e());
        }
        return false;
    }
}
