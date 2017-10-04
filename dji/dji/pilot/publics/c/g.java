package dji.pilot.publics.c;

import android.content.Context;
import android.os.Handler.Callback;
import android.os.Message;
import dji.common.flightcontroller.DJIFlightControllerAdvancedGoHomeState;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.Data2100GetPushCheckStatus;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataEyeGetPushAvoidanceParam;
import dji.midware.data.model.P3.DataFlycGetPushAvoid;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataGimbalGetPushCheckStatus;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOfdmGetPushCheckStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.IMU_INITFAIL_REASON;
import dji.midware.data.model.P3.DataOsdGetPushCommon.MotorFailReason;
import dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdGetPushHome.HeightLimitStatus;
import dji.midware.data.model.P3.DataOsdGetPushSDRNfParams;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.pilot.R;
import dji.pilot.fpv.control.o$a;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k.a;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;
import java.io.IOException;
import java.io.RandomAccessFile;

public class g implements Callback, a {
    private static final float N = 0.6f;
    private static final int c = 4096;
    private static final long d = 3000;
    private static final int e = 4097;
    private static final long f = 1000;
    private boolean A = false;
    private boolean B = false;
    private FLYC_STATE C = FLYC_STATE.OTHER;
    private RcModeChannel D = RcModeChannel.CHANNEL_UNKNOWN;
    private boolean E = false;
    private HeightLimitStatus F = HeightLimitStatus.NON_LIMIT;
    private boolean G = false;
    private int H = 0;
    private boolean I = false;
    private boolean J = false;
    private MotorFailReason K = MotorFailReason.OTHER;
    private d L = new d(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
            if (aVar2 != null && aVar2.e() != null && cVar.f().equals(e.cN)) {
                switch (AnonymousClass3.b[((DJIFlightControllerAdvancedGoHomeState) aVar2.e()).ordinal()]) {
                    case 1:
                        b bVar = new b();
                        bVar.d = R.string.fpv_advanced_go_home_hovering_at_safe_point;
                        bVar.b = R.string.fpv_advanced_go_home_hovering_at_safe_point;
                        bVar.a = DJIErrorPopView.d.a;
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private boolean M = false;
    private boolean O = false;
    private Runnable P = new Runnable(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.e() <= 0.6f) {
                this.a.O = false;
            } else if (!this.a.O) {
                this.a.O = true;
                b bVar = new b();
                bVar.d = R.string.fpv_cpu_observer_description;
                bVar.b = R.string.fpv_cpu_observer_title;
                bVar.a = DJIErrorPopView.d.b;
                bVar.f = DJIErrorPopView.c.c;
                dji.thirdparty.a.c.a().e(bVar);
            }
            dji.midware.util.b.a(this.a.P, 3000);
        }
    };
    MotorStartFailedCause a = MotorStartFailedCause.OTHER;
    IMU_INITFAIL_REASON b = IMU_INITFAIL_REASON.None;
    private boolean g = false;
    private Context h;
    private k i = null;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private volatile boolean m = false;
    private volatile int n = -1;
    private volatile int o = -1;
    private volatile boolean p = false;
    private volatile boolean q = true;
    private volatile boolean r = true;
    private volatile boolean s = true;
    private volatile boolean t = true;
    private volatile int u = -1;
    private boolean v = false;
    private int w = -1;
    private int x = -1;
    private int y = -1;
    private boolean z = false;

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[o.values().length];
        static final /* synthetic */ int[] b = new int[DJIFlightControllerAdvancedGoHomeState.values().length];

        static {
            try {
                b[DJIFlightControllerAdvancedGoHomeState.HoveringAtSafePoint.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public g(Context context) {
        this.h = context;
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        if (Data2100GetPushCheckStatus.getInstance().isGetted()) {
            onEventBackgroundThread(Data2100GetPushCheckStatus.getInstance());
        }
        this.i = new k(this, this);
        b();
    }

    public void a() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        DJISDKCache.getInstance().stopListening(this.L);
    }

    public void onEventBackgroundThread(DataFlycGetPushAvoidParam dataFlycGetPushAvoidParam) {
        boolean isAvoidOvershotAct = dataFlycGetPushAvoidParam.isAvoidOvershotAct();
        if (isAvoidOvershotAct != this.j && dji.pilot.fpv.d.b.n(i.getInstance().c())) {
            this.j = isAvoidOvershotAct;
            if (isAvoidOvershotAct) {
                b.b(DJIErrorPopView.d.a, R.string.vision_overshot_now_tip, 0, DJIErrorPopView.c.c, f.a);
            } else {
                b.b(DJIErrorPopView.d.a, R.string.vision_overshot_now_tip, 0, DJIErrorPopView.c.c, f.b);
            }
        }
    }

    public void onEventBackgroundThread(o$a dji_pilot_fpv_control_o_a) {
        if (dji.pilot.fpv.d.b.j(null) && o$a.REMOVE_ALL == dji_pilot_fpv_control_o_a) {
            b.b(DJIErrorPopView.d.b, R.string.guidance_ass_no_effect_title, 0, DJIErrorPopView.c.c, f.b);
            b.b(DJIErrorPopView.d.a, R.string.guidance_ass_effect_title, 0, DJIErrorPopView.c.c, f.b);
            this.n = -1;
        }
    }

    public void onEventBackgroundThread(DataRcGetPushParams dataRcGetPushParams) {
        boolean z = (dataRcGetPushParams.getAileron() == 1024 && dataRcGetPushParams.getThrottle() == 1024 && dataRcGetPushParams.getElevator() == 1024 && dataRcGetPushParams.getRudder() == 1024) ? false : true;
        if (z != this.p) {
            this.p = z;
            if (!z || !DataOfdmGetPushCheckStatus.getInstance().isFirmwareNotMatch() || !dji.pilot.publics.control.a.getInstance().o()) {
                return;
            }
            if (!dji.pilot.publics.control.a.getInstance().m()) {
                b.b(DJIErrorPopView.d.b, R.string.upgrade_ground_air_not_match_rc, 0, DJIErrorPopView.c.c, f.a);
            } else if (dji.pilot.fpv.d.b.s(null)) {
                b.b(DJIErrorPopView.d.b, R.string.upgrade_ground_air_not_match_air, 0, DJIErrorPopView.c.c, f.a);
            } else {
                b.b(DJIErrorPopView.d.b, R.string.upgrade_ground_air_not_match_air_other, 0, DJIErrorPopView.c.c, f.a);
            }
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushAvoid dataFlycGetPushAvoid) {
        int i;
        if (dji.pilot.fpv.d.b.j(null) && dji.pilot.fpv.control.o.a()) {
            boolean isInStop = dataFlycGetPushAvoid.isInStop();
            if (this.m != isInStop) {
                this.m = isInStop;
                if (isInStop) {
                    b bVar = new b();
                    bVar.b = R.string.guidance_brake_tip;
                    bVar.a = DJIErrorPopView.d.a;
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
        }
        if (dji.pilot.fpv.d.b.j(null) && dji.pilot.fpv.control.o.a()) {
            i = dataFlycGetPushAvoid.isVisualSensorEnable() ? 1 : 0;
            if (this.o != i) {
                this.o = i;
                b bVar2 = new b();
                if (i == 1) {
                    bVar2.b = R.string.guidance_visual_sensor_effect_title;
                    bVar2.a = DJIErrorPopView.d.a;
                } else {
                    bVar2.b = R.string.guidance_visual_sensor_no_effect_title;
                    bVar2.a = DJIErrorPopView.d.b;
                }
                dji.thirdparty.a.c.a().e(bVar2);
            }
        }
        if (dji.pilot.fpv.d.b.j(null) && dji.pilot.fpv.control.o.a()) {
            if (dataFlycGetPushAvoid.isVisualSensorWork()) {
                i = 1;
            } else {
                i = 0;
            }
            if (this.n != i) {
                this.n = i;
                if (!DataOsdGetPushCommon.getInstance().isMotorUp()) {
                    return;
                }
                if (i == 1) {
                    b.b(DJIErrorPopView.d.b, R.string.guidance_ass_no_effect_title, 0, DJIErrorPopView.c.c, f.b);
                    b.b(DJIErrorPopView.d.a, R.string.guidance_ass_effect_title, 0, DJIErrorPopView.c.c, f.a);
                    return;
                }
                b.b(DJIErrorPopView.d.a, R.string.guidance_ass_effect_title, 0, DJIErrorPopView.c.c, f.b);
                b.b(DJIErrorPopView.d.b, R.string.guidance_ass_no_effect_title, 0, DJIErrorPopView.c.c, f.a);
            }
        }
    }

    public void onEventBackgroundThread(DataEyeGetPushAvoidanceParam dataEyeGetPushAvoidanceParam) {
        if (DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
            boolean isBraking = dataEyeGetPushAvoidanceParam.isBraking();
            if (isBraking != this.k) {
                this.k = isBraking;
                if (isBraking && ProductType.Pomato == i.getInstance().c()) {
                    e.getInstance().a(new long[]{100, 300, 500, 300}, -1);
                }
            }
            isBraking = dataEyeGetPushAvoidanceParam.allowLeft();
            if (this.q != isBraking) {
                this.q = isBraking;
                if (!isBraking) {
                    b.b(DJIErrorPopView.d.b, R.string.vision_brake_disallow_left, 0, DJIErrorPopView.c.a, f.a);
                }
            }
            isBraking = dataEyeGetPushAvoidanceParam.allowRight();
            if (isBraking != this.r) {
                this.r = isBraking;
                if (!isBraking) {
                    b.b(DJIErrorPopView.d.b, R.string.vision_brake_disallow_right, 0, DJIErrorPopView.c.a, f.a);
                }
            }
            isBraking = dataEyeGetPushAvoidanceParam.beShuttleMode();
            if (!isBraking) {
                this.l = false;
            } else if (this.l != isBraking && DataFlycGetPushAvoidParam.getInstance().isUserAvoidEnable() && DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
                this.l = isBraking;
                b.b(DJIErrorPopView.d.a, R.string.fpv_errorpop_shuttle_mode, 0, DJIErrorPopView.c.a, f.a);
            }
        }
    }

    public void onEventBackgroundThread(Data2100GetPushCheckStatus data2100GetPushCheckStatus) {
        int i = data2100GetPushCheckStatus.isPropellerCover() ? 1 : 0;
        if (i != this.u) {
            this.u = i;
            if (i == 1) {
                b.b(DJIErrorPopView.d.b, R.string.check_2100_propeller_cover, 0, DJIErrorPopView.c.c, f.a);
            } else {
                b.b(DJIErrorPopView.d.b, R.string.check_2100_propeller_cover, 0, DJIErrorPopView.c.c, f.b);
            }
        }
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        int i = 0;
        b bVar;
        if (dji.pilot.fpv.d.b.k(null)) {
            int i2 = dataGimbalGetPushParams.isPitchInLimit() ? 1 : 0;
            if (i2 != this.w) {
                this.w = i2;
                if (1 == i2) {
                    bVar = new b();
                    bVar.b = R.string.gimbal_pitch_in_limit;
                    bVar.a = DJIErrorPopView.d.a;
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
            i2 = dataGimbalGetPushParams.isYawInLimit() ? 1 : 0;
            if (i2 != this.x) {
                this.x = i2;
                if (1 == i2) {
                    bVar = new b();
                    bVar.b = R.string.gimbal_yaw_in_limit;
                    bVar.a = DJIErrorPopView.d.a;
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
            if (dataGimbalGetPushParams.isRollInLimit()) {
                i = 1;
            }
            if (i != this.y) {
                this.y = i;
                if (1 == i) {
                    bVar = new b();
                    bVar.b = R.string.gimbal_roll_in_limit;
                    bVar.a = DJIErrorPopView.d.a;
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
        } else if (dji.pilot.fpv.d.b.a(null) && dji.pilot.fpv.d.b.c(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            if (dataGimbalGetPushParams.isRollInLimit()) {
                i = 1;
            }
            if (i != this.y) {
                this.y = i;
                if (1 == i) {
                    bVar = new b();
                    bVar.b = R.string.gimbal_roll_in_limit;
                    bVar.a = DJIErrorPopView.d.a;
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        b bVar;
        MotorStartFailedCause motorFailedCause = dataOsdGetPushCommon.getMotorFailedCause();
        if (motorFailedCause != this.a) {
            this.a = motorFailedCause;
            int[] a = dji.pilot.publics.e.d.a(motorFailedCause);
            if (a[0] > 0) {
                bVar = new b();
                bVar.b = a[0];
                bVar.d = a[1];
                if (this.a == MotorStartFailedCause.DeviceLocked) {
                    bVar.f = DJIErrorPopView.c.c;
                }
                dji.thirdparty.a.c.a().e(bVar);
            } else if (a[0] >= 0 && motorFailedCause.relvalue() > 0 && this.a != MotorStartFailedCause.FlyForbiddenError) {
                b bVar2 = new b();
                bVar2.c = this.h.getResources().getString(R.string.takeoff_failed_tips_unknow, new Object[]{Integer.valueOf(motorFailedCause.relvalue())});
                dji.thirdparty.a.c.a().e(bVar2);
            }
        }
        boolean waveError = dataOsdGetPushCommon.getWaveError();
        if (waveError != this.g) {
            this.g = waveError;
            if (waveError) {
                b.b(DJIErrorPopView.d.b, R.string.fpv_errorpop_swave_error, 0, DJIErrorPopView.c.c, f.a);
            } else {
                b.b(DJIErrorPopView.d.b, R.string.fpv_errorpop_swave_error, 0, DJIErrorPopView.c.c, f.b);
            }
        }
        MotorFailReason motorFailReason = dataOsdGetPushCommon.getMotorFailReason();
        if (this.K != motorFailReason) {
            this.K = motorFailReason;
            int a2 = dji.pilot.fpv.d.b.a(motorFailReason);
            if (a2 != 0) {
                bVar2 = new b();
                bVar2.a = DJIErrorPopView.d.b;
                bVar2.b = a2;
                dji.thirdparty.a.c.a().e(bVar2);
            }
        }
        RcModeChannel modeChannel = dataOsdGetPushCommon.getModeChannel();
        DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
        boolean a3 = dji.pilot.fpv.d.b.a(instance.isBeginnerMode(), instance.isMultipleModeOpen());
        if (this.D != modeChannel) {
            if ((dji.pilot.fpv.d.b.q(i.getInstance().c()) || dji.pilot.fpv.d.b.r(null)) && dji.pilot.fpv.d.b.j() && !a3 && this.D != RcModeChannel.CHANNEL_UNKNOWN && (modeChannel == RcModeChannel.CHANNEL_A || modeChannel == RcModeChannel.CHANNEL_S || modeChannel == RcModeChannel.CHANNEL_F || modeChannel == RcModeChannel.CHANNEL_P)) {
                b bVar3 = new b();
                bVar3.a = DJIErrorPopView.d.b;
                bVar3.b = R.string.fpv_flight_mode_asmode_tip;
                dji.thirdparty.a.c.a().e(bVar3);
            }
            a(this.C, a3, modeChannel, false);
        }
        FLYC_STATE flycState = dataOsdGetPushCommon.getFlycState();
        if (this.C != flycState) {
            a(flycState, a3, modeChannel, true);
        }
        waveError = dataOsdGetPushCommon.isMotorUp();
        if (this.E != waveError) {
            this.E = waveError;
            if (dataOsdGetPushCommon.getFlycVersion() >= 13 && waveError && instance.getHeightLimitStatus() == HeightLimitStatus.ORIENTATION_NEED_CALI) {
                b.a(DJIErrorPopView.d.b, this.h.getString(R.string.mc_campass_limit_height_active, new Object[]{Float.valueOf(instance.getHeightLimitValue())}), null, DJIErrorPopView.c.a, f.a);
            }
            if (waveError) {
                this.i.removeMessages(4096);
            }
        }
        waveError = dataOsdGetPushCommon.isMotorBlock();
        if (waveError != this.I) {
            this.I = waveError;
            if (this.I) {
                bVar = new b();
                bVar.a = DJIErrorPopView.d.b;
                bVar.b = R.string.fpv_errorpop_flightaction_motor_block;
                bVar.f = DJIErrorPopView.c.c;
                dji.thirdparty.a.c.a().e(bVar);
            }
        }
        waveError = dataOsdGetPushCommon.isPropellerCatapult();
        if (waveError != this.J) {
            this.J = waveError;
            if (waveError) {
                bVar = new b();
                bVar.a = DJIErrorPopView.d.b;
                bVar.b = R.string.fpv_errorpop_flightaction_propeller_catapult;
                bVar.f = DJIErrorPopView.c.c;
                dji.thirdparty.a.c.a().e(bVar);
            }
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        boolean a = dji.pilot.fpv.d.b.a(dataOsdGetPushHome.isBeginnerMode(), dataOsdGetPushHome.isMultipleModeOpen());
        if (this.B != a) {
            a(this.C, a, this.D, false);
        }
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 13) {
            HeightLimitStatus heightLimitStatus = dataOsdGetPushHome.getHeightLimitStatus();
            if (this.F != heightLimitStatus) {
                if (this.F == HeightLimitStatus.ORIENTATION_NEED_CALI && heightLimitStatus == HeightLimitStatus.NORMAL_LIMIT) {
                    b.b(DJIErrorPopView.d.a, R.string.mc_campass_limit_height_deactive, 0, DJIErrorPopView.c.a, f.a);
                }
                this.F = heightLimitStatus;
            }
            a = dataOsdGetPushHome.isReatchLimitHeight();
            if (this.G != a) {
                this.G = a;
                if (a) {
                    if (HeightLimitStatus.ORIENTATION_NEED_CALI == this.F) {
                        b.b(DJIErrorPopView.d.b, R.string.mc_limit_height_reached_reason_campass, 0, DJIErrorPopView.c.a, f.a);
                    } else if (HeightLimitStatus.NON_GPS == this.F) {
                        b.b(DJIErrorPopView.d.b, R.string.mc_limit_height_reachded_reason_gps, 0, DJIErrorPopView.c.a, f.a);
                    }
                }
            }
        }
        a = dataOsdGetPushHome.isBigGale();
        if (a != this.z) {
            if (a) {
                b.b(DJIErrorPopView.d.c, R.string.flyc_gale_warning, 0, DJIErrorPopView.c.c, f.a);
            } else {
                b.b(DJIErrorPopView.d.c, R.string.flyc_gale_warning, 0, DJIErrorPopView.c.c, f.b);
            }
            this.z = a;
        }
        a = dataOsdGetPushHome.isCompassInstallErr();
        if (a != this.A) {
            this.A = a;
            if (this.A && dji.pilot.fpv.d.b.r(null)) {
                b.b(DJIErrorPopView.d.c, R.string.flyc_compass_install_error_tip, 0, DJIErrorPopView.c.c, f.a);
            }
        }
    }

    private void a(FLYC_STATE flyc_state, boolean z, RcModeChannel rcModeChannel, boolean z2) {
        if (z && rcModeChannel == RcModeChannel.CHANNEL_A) {
            this.i.removeMessages(4097);
            b.b(DJIErrorPopView.d.b, R.string.patti_error_top_tip, 0, DJIErrorPopView.c.c, f.b);
            b.b(DJIErrorPopView.d.b, R.string.fpv_flight_mode_patti_tip, 0, DJIErrorPopView.c.c, f.b);
        } else if (z2) {
            if (dji.pilot.fpv.d.b.a(flyc_state)) {
                if (!(dji.pilot.fpv.d.b.a(this.C) || this.C == FLYC_STATE.GoHome)) {
                    this.i.sendEmptyMessageDelayed(4097, 1000);
                }
            } else if (flyc_state != FLYC_STATE.GoHome) {
                this.i.removeMessages(4097);
                b.b(DJIErrorPopView.d.b, R.string.patti_error_top_tip, 0, DJIErrorPopView.c.c, f.b);
                b.b(DJIErrorPopView.d.b, R.string.fpv_flight_mode_patti_tip, 0, DJIErrorPopView.c.c, f.b);
            }
        }
        if (dji.pilot.fpv.d.b.j() && !DataOsdGetPushCommon.getInstance().isMotorUp()) {
            if (z && rcModeChannel != RcModeChannel.CHANNEL_P && flyc_state == FLYC_STATE.GPS_Atti) {
                if (this.C == FLYC_STATE.OTHER || this.D == RcModeChannel.CHANNEL_UNKNOWN) {
                    this.i.sendEmptyMessageDelayed(4096, 3000);
                }
            } else if (this.D != rcModeChannel) {
                this.i.removeMessages(4096);
            }
        }
        this.D = rcModeChannel;
        this.C = flyc_state;
        this.B = z;
    }

    public void onEventBackgroundThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        int status = dataFlycGetPushSmartBattery.getStatus();
        if (this.H != status) {
            if ((status & 8192) != 0) {
                if ((this.H & 16384) != 0) {
                    b.b(DJIErrorPopView.d.c, R.string.fpv_lowvoltage_warning_title, R.string.fpv_lowvoltage_warning_desc, DJIErrorPopView.c.c, f.b);
                }
                if ((this.H & 8192) == 0) {
                    b.b(DJIErrorPopView.d.c, R.string.fpv_lowvoltage_dangerous_title, R.string.fpv_lowvoltage_dangerous_desc, DJIErrorPopView.c.c, f.a);
                }
            } else if ((status & 16384) != 0) {
                if ((this.H & 8192) != 0) {
                    b.b(DJIErrorPopView.d.c, R.string.fpv_lowvoltage_dangerous_title, R.string.fpv_lowvoltage_dangerous_desc, DJIErrorPopView.c.c, f.b);
                }
                if ((this.H & 16384) == 0 || (this.H & 8192) != 0) {
                    b.b(DJIErrorPopView.d.c, R.string.fpv_lowvoltage_warning_title, R.string.fpv_lowvoltage_warning_desc, DJIErrorPopView.c.c, f.a);
                }
            } else {
                b.b(DJIErrorPopView.d.c, R.string.fpv_lowvoltage_warning_title, R.string.fpv_lowvoltage_warning_desc, DJIErrorPopView.c.c, f.b);
                b.b(DJIErrorPopView.d.c, R.string.fpv_lowvoltage_dangerous_title, R.string.fpv_lowvoltage_dangerous_desc, DJIErrorPopView.c.c, f.b);
            }
            this.H = status;
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass3.a[oVar.ordinal()]) {
            case 2:
                this.a = null;
                this.g = false;
                this.K = MotorFailReason.OTHER;
                if (dji.pilot.fpv.d.b.j(null)) {
                    b.b(DJIErrorPopView.d.b, R.string.guidance_ass_no_effect_title, 0, DJIErrorPopView.c.c, f.b);
                    b.b(DJIErrorPopView.d.a, R.string.guidance_ass_effect_title, 0, DJIErrorPopView.c.c, f.b);
                }
                this.n = -1;
                this.o = -1;
                this.m = false;
                this.r = true;
                this.q = true;
                this.s = true;
                this.t = true;
                this.v = false;
                this.w = -1;
                this.y = -1;
                this.x = -1;
                this.D = RcModeChannel.CHANNEL_UNKNOWN;
                this.C = FLYC_STATE.OTHER;
                this.B = false;
                this.E = false;
                this.F = HeightLimitStatus.NON_LIMIT;
                this.G = false;
                this.u = -1;
                this.p = false;
                this.H = 0;
                this.z = false;
                this.j = false;
                this.k = false;
                this.l = false;
                this.i.removeCallbacksAndMessages(null);
                return;
            default:
                return;
        }
    }

    public boolean isFinished() {
        return false;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 4096:
                if (this.B && this.D != RcModeChannel.CHANNEL_P && this.C == FLYC_STATE.GPS_Atti) {
                    b.b(DJIErrorPopView.d.a, R.string.fpv_flight_mode_not_pmode, 0, DJIErrorPopView.c.a, f.a);
                    break;
                }
            case 4097:
                if (dji.pilot.fpv.d.b.j() && (!(this.B && this.D == RcModeChannel.CHANNEL_A) && dji.pilot.fpv.d.b.a(this.C))) {
                    DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
                    if (instance.getFlycVersion() >= 5 && dji.pilot.fpv.d.f.a(instance.getNonGpsCause())) {
                        b.b(DJIErrorPopView.d.b, R.string.patti_error_top_tip, 0, DJIErrorPopView.c.c, f.a);
                        break;
                    }
                    b.b(DJIErrorPopView.d.b, R.string.fpv_flight_mode_patti_tip, 0, DJIErrorPopView.c.c, f.a);
                    break;
                }
                break;
        }
        return false;
    }

    private void b() {
        dji.sdksharedlib.a.a.g(this.L, new String[]{e.cJ, e.cM, e.cN, e.cO});
    }

    public void onEventBackgroundThread(DataGimbalGetPushCheckStatus dataGimbalGetPushCheckStatus) {
        if (dataGimbalGetPushCheckStatus.getLimitStatus() != 1 || this.M) {
            this.M = false;
            return;
        }
        this.M = true;
        b bVar = new b();
        bVar.d = R.string.fpv_gimbal_over_load_desc;
        bVar.b = R.string.fpv_gimbal_over_load_title;
        bVar.a = DJIErrorPopView.d.b;
        bVar.f = DJIErrorPopView.c.a;
        bVar.h = 8000;
        dji.thirdparty.a.c.a().e(bVar);
    }

    private void c() {
        dji.midware.util.b.a(this.P);
    }

    private void d() {
        dji.midware.util.b.b(this.P);
    }

    private float e() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/proc/stat", "r");
            String[] split = randomAccessFile.readLine().split(" +");
            long parseLong = Long.parseLong(split[4]);
            long parseLong2 = ((((Long.parseLong(split[2]) + Long.parseLong(split[3])) + Long.parseLong(split[5])) + Long.parseLong(split[6])) + Long.parseLong(split[7])) + Long.parseLong(split[8]);
            try {
                Thread.sleep(360);
            } catch (Exception e) {
            }
            randomAccessFile.seek(0);
            String readLine = randomAccessFile.readLine();
            randomAccessFile.close();
            String[] split2 = readLine.split(" +");
            long parseLong3 = Long.parseLong(split2[4]);
            long parseLong4 = Long.parseLong(split2[8]) + ((((Long.parseLong(split2[2]) + Long.parseLong(split2[3])) + Long.parseLong(split2[5])) + Long.parseLong(split2[6])) + Long.parseLong(split2[7]));
            return ((float) (parseLong4 - parseLong2)) / ((float) ((parseLong4 + parseLong3) - (parseLong + parseLong2)));
        } catch (IOException e2) {
            e2.printStackTrace();
            return 0.0f;
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushSDRNfParams dataOsdGetPushSDRNfParams) {
    }
}
