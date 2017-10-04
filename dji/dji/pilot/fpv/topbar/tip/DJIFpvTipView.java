package dji.pilot.fpv.topbar.tip;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.m;
import dji.midware.data.model.P3.Data2100GetPushCheckStatus;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.EncryptStatus;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon.ConnStatus;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPushCheckStatus;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
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
import dji.midware.data.model.P3.DataOsdGetPushCommon.NON_GPS_CAUSE;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdGetPushSignalQuality;
import dji.midware.data.model.P3.DataRcGetPushBatteryInfo;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.midware.data.model.P3.DataWifiGetPushElecSignal;
import dji.midware.data.model.P3.DataWifiGetPushSignal;
import dji.pilot.R;
import dji.pilot.battery.a.c;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.d.c$a;
import dji.pilot.fpv.d.c.h;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.publics.e.e;
import dji.pilot.publics.widget.DJIMarqueeTextViewV2;
import dji.pilot.publics.widget.DJIRoundFrameLayout;
import dji.pilot2.simulator.d;
import dji.publics.DJIUI.DJIImageView;
import dji.sdksharedlib.DJISDKCache;
import java.lang.ref.WeakReference;

@SuppressLint({"NewApi"})
public class DJIFpvTipView extends DJIRoundFrameLayout implements c$a, h, a {
    private static long A = 0;
    private static long B = 0;
    private static long C = 0;
    private static long D = 0;
    private static long E = 0;
    private static long F = 0;
    private static long G = 0;
    private static long H = 0;
    private static long I = 0;
    private static long J = 0;
    private static long K = 0;
    private static long L = 0;
    private static long M = 0;
    private static long N = 0;
    private static long O = 0;
    private static long P = 0;
    private static long Q = 0;
    private static long R = 0;
    private static long S = 0;
    private static long T = 0;
    private static long U = 0;
    private static long V = 0;
    private static long W = 0;
    protected static final String a = DJIFpvTipView.class.getSimpleName();
    private static long aa = 0;
    private static long ab = 0;
    private static long ac = 0;
    private static long ad = 0;
    private static long ae = 0;
    private static long af = 0;
    private static long ag = 0;
    private static long ah = 0;
    private static long ai = 0;
    private static long aj = 0;
    private static long ak = 0;
    private static long al = 0;
    private static long am = 0;
    private static long an = 0;
    private static long ao = 0;
    private static long ap = 0;
    private static long aq = 0;
    private static long ar = 0;
    private static long as = 0;
    private static long at = 0;
    private static long au = 0;
    private static long av = 0;
    private static final long n = 5000;
    private static long o;
    private static long p;
    private static long q;
    private static long r;
    private static long s;
    private static long t;
    private static long u;
    private static long v;
    private static long w;
    private static long x;
    private static long y;
    private static long z;
    private int aA = 0;
    private ObjectAnimator aB = null;
    private int aC = 0;
    private int aD = 0;
    private boolean aE = false;
    private String aF = "";
    private Context aG = null;
    private DJIImageView aH = null;
    private DJIMarqueeTextViewV2 aI = null;
    private long aJ = 0;
    private a aK = null;
    private final Runnable aL = new Runnable(this) {
        final /* synthetic */ DJIFpvTipView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!dji.pilot.publics.control.a.getInstance().l() && !DataOfdmGetPushCheckStatus.getInstance().isFirmwareNotMatch() && this.a.a(this.a.aw, DJIFpvTipView.aa)) {
                this.a.aw = this.a.aw & (DJIFpvTipView.aa ^ -1);
                this.a.b();
            }
        }
    };
    private dji.pilot.publics.c.c$a aM = dji.pilot.publics.c.c$a.Normal;
    private c aN = new c();
    private long aw = av;
    private int ax = 0;
    private int ay = 0;
    private int az = 0;

    private static final class a extends Handler {
        private final WeakReference<DJIFpvTipView> a;

        public a(DJIFpvTipView dJIFpvTipView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIFpvTipView);
        }

        public void handleMessage(Message message) {
            DJIFpvTipView dJIFpvTipView = (DJIFpvTipView) this.a.get();
            if (dJIFpvTipView != null) {
                switch (message.what) {
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
        o = 0;
        p = 0;
        q = 0;
        r = 0;
        s = 0;
        t = 0;
        u = 0;
        v = 0;
        w = 0;
        x = 0;
        y = 0;
        z = 0;
        A = 0;
        B = 0;
        C = 0;
        D = 0;
        E = 0;
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
        av = o | r;
        o = 1 << null;
        p = 1 << 1;
        q = 1 << 2;
        r = 1 << 3;
        s = 1 << 4;
        t = 1 << 5;
        u = 1 << 6;
        v = 1 << 7;
        w = 1 << 8;
        x = 1 << 9;
        y = 1 << 10;
        z = 1 << 11;
        A = 1 << 12;
        B = 1 << 13;
        C = 1 << 14;
        D = 1 << 15;
        ap = 1 << 16;
        E = 1 << 17;
        F = 1 << 18;
        G = 1 << 19;
        H = 1 << 20;
        ao = 1 << 21;
        I = 1 << 22;
        J = 1 << 23;
        K = 1 << 24;
        L = 1 << 25;
        M = 1 << 26;
        N = 1 << 27;
        O = 1 << 28;
        P = 1 << 29;
        Q = 1 << 30;
        R = 1 << 31;
        S = 1 << 32;
        T = 1 << 33;
        U = 1 << 34;
        V = 1 << 35;
        aq = 1 << 36;
        W = 1 << 37;
        aa = 1 << 38;
        ab = 1 << 39;
        ac = 1 << 40;
        ad = 1 << 41;
        at = a(42);
        ae = 1 << 42;
        af = 1 << 43;
        ag = 1 << 44;
        ah = 1 << 45;
        ai = 1 << 46;
        aj = 1 << 47;
        ak = 1 << 48;
        al = 1 << 49;
        am = 1 << 50;
        au = a(51);
        an = 1 << 51;
        ar = 1152921504606846976L;
        as = 2305843009213693952L;
        av = o | r;
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
        this.aG = context;
        if (!isInEditMode()) {
            Resources resources = context.getResources();
            this.ax = resources.getColor(R.color.dn);
            this.ay = resources.getColor(R.color.dp);
            this.az = resources.getColor(R.color.dq);
            this.aA = resources.getColor(R.color.do);
            this.aB = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.c);
            this.aK = new a(this);
        }
    }

    public void resetStatus(boolean z) {
        if (!z) {
            disconnect();
        } else if (ServiceManager.getInstance().isConnected()) {
            connect();
        } else {
            disconnect();
        }
    }

    public void connect() {
        this.aw &= o ^ -1;
        if (d.h()) {
            this.aw &= r ^ -1;
        }
        update(DataOsdGetPushCommon.getInstance());
        update(DataOsdGetPushSignalQuality.getInstance());
        update(DataRcGetPushBatteryInfo.getInstance());
        b();
        g();
        needUptate(dji.pilot.publics.control.a.getInstance().o(), dji.pilot.publics.control.a.getInstance().l());
    }

    public void disconnect() {
        boolean a = a(this.aw, r);
        this.aw = o;
        if (a && !d.h()) {
            this.aw |= r;
        }
        this.aK.removeMessages(4100);
        this.aK.removeMessages(4101);
        b();
    }

    public void cameraConnect() {
        b();
        needUptate(dji.pilot.publics.control.a.getInstance().o(), dji.pilot.publics.control.a.getInstance().l());
        if (DataFlycGetPushCheckStatus.getInstance().isGetted()) {
            update(DataFlycGetPushCheckStatus.getInstance());
        }
    }

    public void cameraDisconnect() {
        this.aw &= p ^ -1;
        b();
    }

    public void update(m mVar) {
        long j = this.aw;
        if (m.a == mVar && !d.h()) {
            j |= r;
        } else if (m.b == mVar) {
            j &= r ^ -1;
        }
        if (this.aw != j) {
            this.aw = j;
            b();
        }
    }

    public void update(DataOsdGetPushChannalStatus dataOsdGetPushChannalStatus) {
    }

    public void update(DataWifiGetPushElecSignal dataWifiGetPushElecSignal) {
    }

    public void update(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            long j = this.aw;
            if (a(j, o)) {
                j &= o ^ -1;
            }
            if (dataCameraGetPushStateInfo.getEncryptStatus() != EncryptStatus.CHECK_SUCCESS) {
                j |= u;
            } else {
                j &= u ^ -1;
            }
            if (this.aw != j) {
                this.aw = j;
                b();
            }
        }
    }

    public void update(Data2100GetPushCheckStatus data2100GetPushCheckStatus) {
        long j = this.aw;
        if (data2100GetPushCheckStatus.isForeSightDemarkAbnormal()) {
            j |= A;
        } else {
            j &= A ^ -1;
        }
        if (data2100GetPushCheckStatus.isDownSightDemarkAbnormal()) {
            j |= B;
        } else {
            j &= B ^ -1;
        }
        if (data2100GetPushCheckStatus.isAutoExpAbnormal() || data2100GetPushCheckStatus.isDepthImageAbnormal() || data2100GetPushCheckStatus.isVOAbnormal() || data2100GetPushCheckStatus.isAvoidanceAbnormal() || data2100GetPushCheckStatus.isStoreAbnormal() || data2100GetPushCheckStatus.isInnerAbnormal() || data2100GetPushCheckStatus.isLRTAbnormal()) {
            j |= C;
        } else {
            j &= C ^ -1;
        }
        if (this.aw != j) {
            this.aw = j;
            b();
        }
    }

    public void update(dji.setting.ui.flyc.SdModeView.a aVar) {
        long j = this.aw;
        if (dji.setting.ui.flyc.SdModeView.a.SUCCESS == aVar) {
            j |= p;
            b.b(true);
        } else {
            j &= p ^ -1;
            b.b(false);
        }
        if (this.aw != j) {
            this.aw = j;
            b();
        }
    }

    public void update(dji.pilot.publics.c.c$a dji_pilot_publics_c_c_a) {
        long j = this.aw;
        this.aM = dji_pilot_publics_c_c_a;
        if (dji_pilot_publics_c_c_a == dji.pilot.publics.c.c$a.Normal) {
            j &= q ^ -1;
        } else if (!d.h()) {
            j |= q;
        }
        if (this.aw != j) {
            this.aw = j;
            b();
        }
    }

    public void update(DataGimbalGetPushParams dataGimbalGetPushParams) {
        long j = this.aw;
        if (dataGimbalGetPushParams.isStuck()) {
            j |= aj;
        } else {
            j &= aj ^ -1;
        }
        if (this.aw != j) {
            this.aw = j;
            b();
        }
    }

    public void update(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        long j;
        long j2 = this.aw;
        ConnStatus connStatus = dataCenterGetPushBatteryCommon.getConnStatus();
        if (dji.pilot.publics.e.a.b()) {
            DataSmartBatteryGetPushDynamicData instance = DataSmartBatteryGetPushDynamicData.getInstance();
            connStatus = instance.isGetted() ? ConnStatus.ofData((int) instance.getStatus()) : ConnStatus.EXCEPTION;
        }
        Object obj = 1;
        if (BatteryType.Smart != DataOsdGetPushCommon.getInstance().getBatteryType()) {
            obj = null;
        }
        if ((connStatus == ConnStatus.INVALID || connStatus == ConnStatus.EXCEPTION) && r1 != null) {
            a(j2, D, c$a.aT_);
            j = D | j2;
        } else {
            j = (D ^ -1) & j2;
        }
        this.aN.b(dataCenterGetPushBatteryCommon.getErrorType());
        if (this.aN.l() != (byte) 0) {
            j |= E;
        } else {
            j &= E ^ -1;
        }
        if (this.aN.d() || this.aN.e()) {
            j |= F;
        } else {
            j &= F ^ -1;
        }
        if (this.aN.f() || this.aN.g()) {
            j |= G;
        } else {
            j &= G ^ -1;
        }
        if (this.aN.h() || this.aN.i()) {
            j |= H;
        } else {
            j &= H ^ -1;
        }
        if (this.aw != j) {
            this.aw = j;
            b();
        }
    }

    public void update(DataFlycGetPushCheckStatus dataFlycGetPushCheckStatus) {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 4) {
            long j;
            long j2 = this.aw;
            if (dataFlycGetPushCheckStatus.getIMUAdvanceCaliStatus() || dataFlycGetPushCheckStatus.getIMUBasicCaliStatus() || dataFlycGetPushCheckStatus.getVersionStatus()) {
                a(j2, t, c$a.aU_);
                j2 |= t;
            } else {
                j2 &= t ^ -1;
            }
            if (dataFlycGetPushCheckStatus.getIMUHorizontalCaliStatus() || dataFlycGetPushCheckStatus.getIMUDirectionStatus() || dataFlycGetPushCheckStatus.getIMUInitStatus() || dataFlycGetPushCheckStatus.getPressInitStatus() || dataFlycGetPushCheckStatus.getAccDataStatus() || dataFlycGetPushCheckStatus.getGyroscopeStatus() || dataFlycGetPushCheckStatus.getPressDataStatus() || dataFlycGetPushCheckStatus.getAircraftAttiStatus() || dataFlycGetPushCheckStatus.getIMUDataStatus() || dataFlycGetPushCheckStatus.getDataLoggerStatus()) {
                a(j2, s, c$a.aV_);
                j = s | j2;
            } else {
                j = (s ^ -1) & j2;
            }
            if (this.aw != j) {
                this.aw = j;
                b();
            }
        }
    }

    private float getHomeDistance() {
        double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
        double longitude = DataOsdGetPushCommon.getInstance().getLongitude();
        double latitude2 = DataOsdGetPushHome.getInstance().getLatitude();
        double longitude2 = DataOsdGetPushHome.getInstance().getLongitude();
        if (!b.a(latitude) || !b.a(latitude2) || !b.b(longitude) || !b.b(longitude2)) {
            return 0.0f;
        }
        float[] fArr = new float[2];
        Location.distanceBetween(latitude2, longitude2, latitude, longitude, fArr);
        return fArr[0];
    }

    public void update(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (dataOsdGetPushCommon.isGetted() && dataOsdGetPushCommon.getDroneType() != DroneType.NoFlyc) {
            Object obj;
            long j;
            long j2 = this.aw;
            if (a(j2, o)) {
                j2 &= o ^ -1;
            }
            FLYC_STATE flycState = dataOsdGetPushCommon.getFlycState();
            boolean a = a(flycState);
            if (dataOsdGetPushCommon.getRcState()) {
                obj = null;
            } else {
                obj = 1;
            }
            IMU_INITFAIL_REASON iMUinitFailReason = dataOsdGetPushCommon.getIMUinitFailReason();
            if (dataOsdGetPushCommon.isMotorBlock()) {
                j2 |= ar;
            } else {
                j2 &= ar ^ -1;
            }
            if (dataOsdGetPushCommon.isPropellerCatapult()) {
                j2 |= as;
            } else {
                j2 &= as ^ -1;
            }
            if (!dataOsdGetPushCommon.isNotEnoughForce()) {
                this.aK.removeMessages(4099);
                j = j2 & (W ^ -1);
            } else if (a(j2, W) || this.aK.hasMessages(4099)) {
                j = j2;
            } else {
                this.aK.sendEmptyMessageDelayed(4099, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                j = j2;
            }
            if (b.a(dataOsdGetPushCommon)) {
                if (!dataOsdGetPushCommon.isBarometerDeadInAir()) {
                    j = (x ^ -1) & ((w ^ -1) & j);
                } else if (dataOsdGetPushCommon.groundOrSky() == 2) {
                    j = (x ^ -1) & (w | j);
                } else {
                    j = x | ((w ^ -1) & j);
                }
            } else if (dataOsdGetPushCommon.isBarometerDeadInAir()) {
                a(j, z, c$a.cr_);
                j |= z;
            } else {
                j &= z ^ -1;
            }
            if (iMUinitFailReason == IMU_INITFAIL_REASON.GyroDead || iMUinitFailReason == IMU_INITFAIL_REASON.AcceDead || iMUinitFailReason == IMU_INITFAIL_REASON.CompassDead || iMUinitFailReason == IMU_INITFAIL_REASON.BarometerDead || iMUinitFailReason == IMU_INITFAIL_REASON.BarometerNegative || iMUinitFailReason == IMU_INITFAIL_REASON.BarometerNoiseTooLarge) {
                a(j, J, c$a.aW_);
                j |= J;
            } else {
                j &= J ^ -1;
            }
            if (iMUinitFailReason == IMU_INITFAIL_REASON.CompassModTooLarge || iMUinitFailReason == IMU_INITFAIL_REASON.CompassNoiseTooLarge) {
                a(j, L, c$a.aO_);
                j2 = L | j;
            } else {
                j2 = (L ^ -1) & j;
            }
            if (!b.m()) {
                if (iMUinitFailReason == IMU_INITFAIL_REASON.GyroBiasTooLarge || iMUinitFailReason == IMU_INITFAIL_REASON.AcceBiasTooLarge) {
                    j2 |= K;
                } else {
                    j2 &= K ^ -1;
                }
            }
            if (iMUinitFailReason == IMU_INITFAIL_REASON.WaitingMcStationary || iMUinitFailReason == IMU_INITFAIL_REASON.AcceMoveTooLarge || iMUinitFailReason == IMU_INITFAIL_REASON.McHeaderMoved || iMUinitFailReason == IMU_INITFAIL_REASON.McVirbrated) {
                j = I | j2;
            } else {
                j = (I ^ -1) & j2;
            }
            if (obj != null) {
                a(j, ad, c$a.aR_);
                j2 = ad | j;
            } else {
                j2 = (ad ^ -1) & j;
            }
            if (flycState == FLYC_STATE.GoHome) {
                j2 |= an;
            } else {
                j2 &= an ^ -1;
            }
            if (obj == null || !a(j2, an)) {
                j = (ac ^ -1) & j2;
            } else {
                j = ac | j2;
                a(j, ac, c$a.aX_);
            }
            int gpsNum = dataOsdGetPushCommon.getGpsNum();
            if (b.e()) {
                j2 = (ak ^ -1) & j;
            } else {
                j2 = ak | j;
            }
            if (!b.a(flycState) && b.b(gpsNum)) {
                j = (ab ^ -1) & j2;
            } else if (a(j2, an)) {
                j = ab | j2;
            } else {
                j = (ab ^ -1) & j2;
            }
            int voltageWarning = dataOsdGetPushCommon.getVoltageWarning();
            FLIGHT_ACTION flightAction = dataOsdGetPushCommon.getFlightAction();
            if (voltageWarning == 2 && a) {
                a(j, S, c$a.aQ_);
                j2 = S | j;
            } else {
                j2 = (S ^ -1) & j;
            }
            if (!(i.getInstance().c() == ProductType.A3 || i.getInstance().c() == ProductType.N3)) {
                if (flightAction != FLIGHT_ACTION.SERIOUS_LOW_VOLTAGE_LANDING) {
                    j2 = (j2 & (Q ^ -1)) & (R ^ -1);
                } else if (a) {
                    j2 = (j2 | Q) & (R ^ -1);
                } else {
                    j2 = (j2 | R) & (Q ^ -1);
                }
            }
            if (flightAction != FLIGHT_ACTION.SMART_POWER_LANDING) {
                j2 = (j2 & (U ^ -1)) & (V ^ -1);
            } else if (a) {
                j2 = (j2 | U) & (V ^ -1);
            } else {
                j2 = (j2 | V) & (U ^ -1);
            }
            if (voltageWarning == 2) {
                j2 = ((j2 | T) & (af ^ -1)) & (ae ^ -1);
            } else if (voltageWarning == 1) {
                j = (T ^ -1) & (j2 | af);
                if (a(j, an)) {
                    a(j, ae, c$a.aN_);
                    j2 = ae | j;
                } else {
                    j2 = (ae ^ -1) & j;
                }
            } else {
                j2 = ((j2 & (T ^ -1)) & (af ^ -1)) & (ae ^ -1);
            }
            if (!b.m()) {
                if (!dataOsdGetPushCommon.getCompassError()) {
                    this.aK.removeMessages(4097);
                    j2 &= v ^ -1;
                } else if (!this.aK.hasMessages(4097)) {
                    this.aK.sendEmptyMessageDelayed(4097, 1000);
                }
            }
            if (dataOsdGetPushCommon.isImuPreheatd()) {
                j2 &= M ^ -1;
            } else {
                j2 |= M;
            }
            MotorStartFailedCause motorStartFailedCause = MotorStartFailedCause.None;
            if (!dataOsdGetPushCommon.isMotorUp()) {
                motorStartFailedCause = dataOsdGetPushCommon.getMotorStartCauseNoStartAction();
            }
            if (MotorStartFailedCause.None == motorStartFailedCause || MotorStartFailedCause.OTHER == motorStartFailedCause) {
                j2 = ((j2 & (N ^ -1)) & (P ^ -1)) & (O ^ -1);
            } else if (motorStartFailedCause == MotorStartFailedCause.DeviceLocked) {
                j2 = ((j2 | N) & (O ^ -1)) & (P ^ -1);
            } else if (motorStartFailedCause == MotorStartFailedCause.NoviceProtected) {
                j2 = ((j2 & (P ^ -1)) | O) & (N ^ -1);
            } else {
                j2 = ((j2 & (N ^ -1)) & (O ^ -1)) | P;
            }
            int[] a2 = b.a(dataOsdGetPushCommon.getFlycState(), dataOsdGetPushCommon.isVisionUsed(), false);
            if (a2[0] == R.string.ctrl_mode_atti || a2[0] == R.string.ctrl_mode_patti) {
                j = al | j2;
            } else {
                j = (al ^ -1) & j2;
            }
            if (dataOsdGetPushCommon.getFlycVersion() >= 5) {
                if (a(dataOsdGetPushCommon.getNonGpsCause())) {
                    a(j, y, c$a.aP_);
                    j |= y;
                    b.a(true);
                } else {
                    j &= y ^ -1;
                }
            } else if (this.aJ != 0) {
                if (a2[0] == R.string.ctrl_mode_patti && b.b(gpsNum) && dataOsdGetPushCommon.groundOrSky() == 2) {
                    if (a(j, y)) {
                        this.aJ = System.currentTimeMillis();
                    } else {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - this.aJ > 1000) {
                            a(j, y, c$a.aP_);
                            j |= y;
                            b.a(true);
                            this.aJ = currentTimeMillis;
                        }
                    }
                } else if (!a(j, y)) {
                    this.aJ = 0;
                } else if (System.currentTimeMillis() - this.aJ > 1000) {
                    j &= y ^ -1;
                    this.aJ = 0;
                }
            } else if (a2[0] == R.string.ctrl_mode_patti && b.b(gpsNum) && dataOsdGetPushCommon.groundOrSky() == 2) {
                this.aJ = System.currentTimeMillis();
            }
            if (dataOsdGetPushCommon.getFlycVersion() >= 8) {
                DJIErrorPopView.b bVar;
                if (a(j, ar)) {
                    bVar = new DJIErrorPopView.b();
                    bVar.a = DJIErrorPopView.d.b;
                    bVar.b = R.string.fpv_errorpop_flightaction_motor_block;
                    bVar.f = DJIErrorPopView.c.c;
                    dji.thirdparty.a.c.a().e(bVar);
                }
                if (a(j, as)) {
                    bVar = new DJIErrorPopView.b();
                    bVar.a = DJIErrorPopView.d.b;
                    bVar.b = R.string.fpv_errorpop_flightaction_propeller_catapult;
                    bVar.f = DJIErrorPopView.c.c;
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
            if (this.aw != j) {
                this.aw = j;
                b();
            } else if (!a(j, at) && a(j, ae)) {
                this.aI.setText(b((int) R.string.fpv_tip_low_power_gohome));
            } else if (!a(j, au) && a(j, an)) {
                this.aI.setText(getGoHomeDesc());
            }
            b.h(a2[0]);
        }
    }

    public void update(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        if (i.getInstance().c() == ProductType.A3 || i.getInstance().c() == ProductType.N3) {
            long j = this.aw;
            int status = dataFlycGetPushSmartBattery.getStatus();
            if ((status & 32) != 0) {
                j = (j | R) & (aq ^ -1);
            } else if ((status & 16) != 0) {
                j = (j | aq) & (R ^ -1);
            } else {
                j = (j & (aq ^ -1)) & (R ^ -1);
            }
            if (this.aw != j) {
                this.aw = j;
                b();
            }
        }
    }

    private boolean a(NON_GPS_CAUSE non_gps_cause) {
        if (non_gps_cause == NON_GPS_CAUSE.COMPASS_ERROR_LARGE || non_gps_cause == NON_GPS_CAUSE.SPEED_ERROR_LARGE || non_gps_cause == NON_GPS_CAUSE.YAW_ERROR_LARGE) {
            return true;
        }
        return false;
    }

    public void update(DataWifiGetPushSignal dataWifiGetPushSignal) {
        if (b.l(null)) {
            long j = this.aw;
            if (dataWifiGetPushSignal.getSignal() > 50 || d.h()) {
                j &= ai ^ -1;
            } else {
                j |= ai;
            }
            if (this.aw != j) {
                this.aw = j;
                b();
            }
        }
    }

    public void update(DataOsdGetPushSignalQuality dataOsdGetPushSignalQuality) {
        long j;
        int upSignalQuality = dataOsdGetPushSignalQuality.getUpSignalQuality();
        long j2 = this.aw;
        if (upSignalQuality < 50) {
            j = ah | j2;
        } else {
            j = (ah ^ -1) & j2;
        }
        if (!b.l(null)) {
            int downSignalQuality;
            if (dji.pilot.c.d.i == 1) {
                downSignalQuality = dataOsdGetPushSignalQuality.getDownSignalQuality();
            } else {
                downSignalQuality = b.g(dataOsdGetPushSignalQuality.getDownSignalQuality());
            }
            if (downSignalQuality < 50) {
                j |= ai;
            } else {
                j &= ai ^ -1;
            }
        }
        if (dji.logic.c.b.getInstance().a(null)) {
            j &= ah ^ -1;
        }
        if (this.aw != j) {
            this.aw = j;
            b();
        }
    }

    public void update(DataRcGetPushBatteryInfo dataRcGetPushBatteryInfo) {
        if (dataRcGetPushBatteryInfo.isGetted()) {
            long j;
            int battery = dataRcGetPushBatteryInfo.getBattery();
            long j2 = this.aw;
            if (b.i(battery)) {
                j = ag | j2;
            } else {
                j = (ag ^ -1) & j2;
            }
            if (this.aw != j) {
                this.aw = j;
                b();
            }
        }
    }

    public void needUptate(boolean z, boolean z2) {
        DJILogHelper.getInstance().LOGD("", "=====needupdate[" + z + "]f[" + z2 + dji.pilot.usercenter.protocol.d.H, false, true);
        long j = this.aw;
        if (!z || DataOsdGetPushCommon.getInstance().isMotorUp()) {
            j &= aa ^ -1;
        } else {
            j |= aa;
        }
        if (this.aw != j) {
            this.aw = j;
            b();
        }
    }

    private String getGoHomeDesc() {
        GOHOME_STATUS gohomeStatus = DataOsdGetPushCommon.getInstance().getGohomeStatus();
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 5) {
            return b((int) R.string.fpv_tip_gohome);
        }
        if (gohomeStatus == GOHOME_STATUS.PREASCENDING) {
            return this.aG.getString(R.string.gohome_step_preascending);
        }
        if (gohomeStatus == GOHOME_STATUS.ALIGN) {
            return this.aG.getString(R.string.gohome_step_align);
        }
        if (gohomeStatus == GOHOME_STATUS.ASCENDING) {
            return this.aG.getString(R.string.gohome_step_ascending);
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
            string = this.aG.getString(R.string.fpv_imperial, new Object[]{Float.valueOf(e.f(r0))});
        } else {
            string = this.aG.getString(R.string.fpv_metric, new Object[]{Float.valueOf(r0)});
        }
        return this.aG.getString(i, new Object[]{string});
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
            this.aH = (DJIImageView) findViewById(R.id.ac6);
            this.aI = (DJIMarqueeTextViewV2) findViewById(R.id.ac7);
            this.aI.setDelay(0);
            this.aB.setTarget(this.aH);
            b();
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
        String string;
        int i;
        boolean z;
        this.aw &= r ^ -1;
        int i2 = R.string.fpv_tip_normal;
        if (i.getInstance().c() == ProductType.Grape2 && DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.NoFlyc) {
            i2 = R.string.fpv_tip_normal_lb2;
        }
        if (i()) {
            i2 = R.string.fpv_tip_normal_in_the_air;
        }
        String string2 = this.aG.getString(i2);
        int i3 = this.ay;
        ProductType c = i.getInstance().c();
        if (a(this.aw, o)) {
            string = this.aG.getString(R.string.fpv_tip_disconnect);
            i2 = this.ax;
            i = R.string.fpv_tip_disconnect;
            z = false;
        } else if (a(this.aw, p)) {
            string = this.aG.getString(R.string.mc_sd_mode_toptips);
            i2 = this.az;
            i = R.string.mc_sd_mode_toptips;
            z = false;
        } else if (a(this.aw, q)) {
            r1 = this.aM.b();
            i = r1;
            string = this.aG.getString(r1);
            i2 = i3;
            z = false;
        } else if (!a(this.aw, r) || (b.o() && DataGimbalGetPushParams.getInstance().isPushLosed())) {
            if (a(this.aw, s)) {
                i = R.string.fpv_tip_mc_error;
                string = this.aG.getString(R.string.fpv_tip_mc_error);
                i2 = i3;
                z = false;
            } else if (a(this.aw, t)) {
                i = R.string.fpv_tip_imu_cali;
                string = this.aG.getString(R.string.fpv_tip_imu_cali);
                i2 = i3;
                z = false;
            } else {
                if (!a(this.aw, u) || (!(c == ProductType.A3 || dji.pilot.publics.e.a.d(c) || c == ProductType.N3) || DataGimbalGetPushParams.getInstance().isPushLosed())) {
                }
                if (a(this.aw, v)) {
                    string = this.aG.getString(R.string.fpv_tip_compass_error);
                    i = R.string.fpv_tip_compass_error;
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, w)) {
                    i = R.string.fpv_tip_esc_error_sky;
                    string = this.aG.getString(R.string.fpv_tip_esc_error_sky);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, x)) {
                    i = R.string.fpv_tip_esc_error;
                    string = this.aG.getString(R.string.fpv_tip_esc_error);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, y)) {
                    i = R.string.fpv_tip_patti_error;
                    string = this.aG.getString(R.string.fpv_tip_patti_error);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, z)) {
                    string = this.aG.getString(R.string.fpv_tip_barometer_dead);
                    i = R.string.fpv_tip_barometer_dead;
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, A)) {
                    i = R.string.fpv_tip_front_vision_cali;
                    string = this.aG.getString(R.string.fpv_tip_front_vision_cali);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, B)) {
                    i = R.string.fpv_tip_down_vision_cali;
                    string = this.aG.getString(R.string.fpv_tip_down_vision_cali);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, C)) {
                    i = R.string.fpv_tip_vision_error;
                    string = this.aG.getString(R.string.fpv_tip_vision_error);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, D)) {
                    string = this.aG.getString(R.string.fpv_tip_battery_exception);
                    i = R.string.fpv_tip_battery_exception;
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, ap)) {
                    i = R.string.battery_top_tips_exception;
                    string = this.aG.getString(R.string.battery_top_tips_exception);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, E)) {
                    i = R.string.fpv_tip_battery_broken;
                    string = this.aG.getString(R.string.fpv_tip_battery_broken);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, F)) {
                    i = R.string.fpv_tip_battery_over_current;
                    string = this.aG.getString(R.string.fpv_tip_battery_over_current);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, G)) {
                    i = R.string.fpv_tip_battery_over_temp;
                    string = this.aG.getString(R.string.fpv_tip_battery_over_temp);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, H)) {
                    i = R.string.fpv_tip_battery_low_temp;
                    string = this.aG.getString(R.string.fpv_tip_battery_low_temp);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, I)) {
                    string = this.aG.getString(R.string.fpv_tip_imu_initializing);
                    i2 = this.az;
                    i = R.string.fpv_tip_imu_initializing;
                    z = false;
                } else if (a(this.aw, J)) {
                    i = R.string.fpv_tip_sensor_error;
                    string = this.aG.getString(R.string.fpv_tip_sensor_error);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, K)) {
                    i = R.string.fpv_tip_imu_error;
                    string = this.aG.getString(R.string.fpv_tip_imu_error);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, L)) {
                    string = this.aG.getString(R.string.fpv_tip_compass_error);
                    i = R.string.fpv_tip_compass_error;
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, M)) {
                    string = this.aG.getString(R.string.fpv_tip_imu_heating);
                    i2 = this.az;
                    i = R.string.fpv_tip_imu_heating;
                    z = true;
                } else if (a(this.aw, N)) {
                    i = R.string.fpv_tip_cant_takeoff_locked;
                    string = this.aG.getString(R.string.fpv_tip_cant_takeoff_locked);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, O)) {
                    i = R.string.fpv_tip_cant_takeoff_novice;
                    string = this.aG.getString(R.string.fpv_tip_cant_takeoff_novice);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, P)) {
                    i = R.string.fpv_tip_cant_takeoff;
                    string = this.aG.getString(R.string.fpv_tip_cant_takeoff);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, Q)) {
                    i = R.string.fpv_tip_serious_low_voltage_landing;
                    string = this.aG.getString(R.string.fpv_tip_serious_low_voltage_landing);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, R)) {
                    i = R.string.fpv_tip_serious_low_voltage;
                    string = this.aG.getString(R.string.fpv_tip_serious_low_voltage);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, S)) {
                    i = R.string.fpv_tip_serious_low_power_landing;
                    string = this.aG.getString(R.string.fpv_tip_serious_low_power_landing);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, T)) {
                    i = R.string.fpv_tip_serious_low_power;
                    string = this.aG.getString(R.string.fpv_tip_serious_low_power);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, U)) {
                    i = R.string.fpv_tip_smart_low_power_landing;
                    string = this.aG.getString(R.string.fpv_tip_smart_low_power_landing);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, V)) {
                    i = R.string.fpv_tip_smart_low_power;
                    string = this.aG.getString(R.string.fpv_tip_smart_low_power);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, aq)) {
                    i = R.string.fpv_tip_low_voltage;
                    string = this.aG.getString(R.string.fpv_tip_low_voltage);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, W)) {
                    string = this.aG.getString(R.string.fpv_tip_not_enough_force);
                    i2 = this.az;
                    i = R.string.fpv_tip_not_enough_force;
                    z = true;
                } else if (a(this.aw, aa)) {
                    r0 = this.aG.getString(R.string.fpv_tip_firmware_no_match);
                    if (dji.pilot.publics.control.a.getInstance().l() || DataOfdmGetPushCheckStatus.getInstance().isFirmwareNotMatch()) {
                        i = R.string.fpv_tip_firmware_no_match;
                        string = r0;
                        i2 = i3;
                        z = false;
                    } else {
                        postDelayed(this.aL, 5000);
                        i = R.string.fpv_tip_firmware_no_match;
                        string = r0;
                        i2 = i3;
                        z = false;
                    }
                } else if (a(this.aw, ab)) {
                    i = R.string.fpv_tip_gohome_failed;
                    string = this.aG.getString(R.string.fpv_tip_gohome_failed);
                    i2 = i3;
                    z = false;
                } else if (a(this.aw, ac)) {
                    i = R.string.fpv_tip_failsafe_gohome;
                    string = this.aG.getString(R.string.fpv_tip_failsafe_gohome);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, ad)) {
                    r1 = R.string.fpv_tip_failsafe;
                    r0 = this.aG.getString(R.string.fpv_tip_failsafe);
                    if (dji.logic.c.b.getInstance().a(i.getInstance().c())) {
                        r1 = R.string.fpv_tip_failsafe_wifi_no_rc;
                        r0 = this.aG.getString(R.string.fpv_tip_failsafe_wifi_no_rc);
                    }
                    i = r1;
                    string = r0;
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, ae)) {
                    i = R.string.fpv_tip_low_power_gohome;
                    string = b((int) R.string.fpv_tip_low_power_gohome);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, af)) {
                    i = R.string.fpv_tip_low_power;
                    string = this.aG.getString(R.string.fpv_tip_low_power);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, ag)) {
                    i = R.string.fpv_tip_low_rc_power;
                    string = this.aG.getString(R.string.fpv_tip_low_rc_power);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, ah)) {
                    i = R.string.fpv_tip_low_rc_signal;
                    string = this.aG.getString(R.string.fpv_tip_low_rc_signal);
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, ai)) {
                    r1 = R.string.fpv_tip_low_radio_signal;
                    r0 = this.aG.getString(R.string.fpv_tip_low_radio_signal);
                    if (dji.logic.c.b.getInstance().a(i.getInstance().c())) {
                        r1 = R.string.fpv_tip_bad_channel_quality;
                        r0 = this.aG.getString(R.string.fpv_tip_bad_channel_quality);
                    }
                    i = r1;
                    string = r0;
                    i2 = i3;
                    z = true;
                } else if (a(this.aw, aj)) {
                    string = this.aG.getString(R.string.fpv_tip_gimbal_stuck);
                    i2 = this.az;
                    i = R.string.fpv_tip_gimbal_stuck;
                    z = false;
                } else if (a(this.aw, al)) {
                    i2 = !i() ? R.string.fpv_tip_atti_state : R.string.fpv_tip_atti_state_in_the_air;
                    r2 = this.aG.getString(R.string.fpv_tip_atti_state);
                    i = i2;
                    i2 = this.az;
                    string = r2;
                    z = false;
                } else if (a(this.aw, ak)) {
                    if (DataOsdGetPushCommon.getInstance().isVisionUsed()) {
                        if (i()) {
                            i2 = R.string.fpv_tip_non_gps_in_the_air;
                        } else {
                            i2 = R.string.fpv_tip_non_gps;
                        }
                    } else if (i()) {
                        i2 = R.string.fpv_tip_non_gps_in_the_air_nonvision;
                    } else {
                        i2 = R.string.fpv_tip_non_gps_nonvision;
                    }
                    r2 = this.aG.getString(i2);
                    i = i2;
                    i2 = this.az;
                    string = r2;
                    z = false;
                } else if (a(this.aw, am)) {
                    string = this.aG.getString(R.string.fpv_tip_chlstatus_poor);
                    i2 = this.az;
                    i = R.string.fpv_tip_chlstatus_poor;
                    z = false;
                } else if (a(this.aw, an)) {
                    string = getGoHomeDesc();
                    i2 = this.aA;
                    i = R.string.fpv_tip_gohome;
                    z = false;
                } else {
                    z = false;
                    r14 = string2;
                    i = i2;
                    i2 = this.aA;
                    string = r14;
                }
            }
        } else if (d.h()) {
            this.aw &= r ^ -1;
            z = false;
            r14 = string2;
            i = i2;
            i2 = this.aA;
            string = r14;
        } else {
            i = R.string.fpv_tip_no_video_signal;
            string = this.aG.getString(R.string.fpv_tip_no_video_signal);
            i2 = i3;
            z = false;
        }
        a(i, string, i2, z);
    }

    private void a(int i, String str, int i2, boolean z) {
        if (this.aE != z) {
            if (this.aE) {
                this.aB.cancel();
                this.aH.setImageAlpha(255);
                this.aH.setAlpha(1.0f);
            } else {
                this.aB.start();
            }
            this.aE = z;
        }
        if (this.aD != i2) {
            this.aD = i2;
            this.aH.setBackgroundColor(i2);
        }
        if (!this.aF.equals(str)) {
            this.aF = str;
            this.aI.setText(str);
        }
    }

    private void c() {
        if (!a(this.aw, y)) {
            this.aw |= y;
            b.a(true);
            b();
        }
    }

    private void d() {
        if (!a(this.aw, v)) {
            this.aw |= v;
            b();
        }
    }

    private void e() {
        if (DataOsdGetPushCommon.getInstance().isNotEnoughForce() && !a(this.aw, W)) {
            this.aw |= W;
            b();
        }
    }

    private void f() {
        if (!a(this.aw, at) && a(this.aw, ae)) {
            this.aI.setText(b((int) R.string.fpv_tip_low_power_gohome));
        }
    }

    private void g() {
        if (b.m()) {
            new DataFlycGetParams().setInfos(i).start(new dji.midware.e.d(this) {
                final /* synthetic */ DJIFpvTipView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.aK.sendEmptyMessage(4101);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.aK.sendEmptyMessageDelayed(4100, 2000);
                }
            });
        }
    }

    private void h() {
        if (b.m()) {
            long j = this.aw;
            int intValue = dji.midware.data.manager.P3.d.read(i[0]).value.intValue();
            if (intValue == 1 || intValue == 0 || intValue == 2) {
                j &= K ^ -1;
            } else {
                j |= K;
            }
            intValue = dji.midware.data.manager.P3.d.read(i[1]).value.intValue();
            if (intValue == 1 || intValue == 0) {
                j &= v ^ -1;
            } else {
                j |= v;
            }
            if (j != this.aw) {
                this.aw = j;
                b();
            }
            this.aK.sendEmptyMessageDelayed(4100, 2000);
        }
    }

    public void update(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        long status = dataSmartBatteryGetPushDynamicData.getStatus();
        long j = this.aw;
        if ((3 & status) != 0) {
            j |= F;
        } else {
            j &= F ^ -1;
        }
        if ((12 & status) != 0) {
            j |= G;
        } else {
            j &= G ^ -1;
        }
        if ((48 & status) != 0) {
            j |= H;
        } else {
            j &= H ^ -1;
        }
        if ((2097152 & status) != 0) {
            j |= ao;
        } else {
            j &= ao ^ -1;
        }
        if ((status & 4128768) != 0) {
            j |= ap;
        } else {
            j &= ap ^ -1;
        }
        if (this.aw != j) {
            this.aw = j;
            b();
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
