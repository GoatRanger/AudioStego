package dji.pilot.visual.a;

import android.content.Context;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.Data2100GetPushCheckStatus;
import dji.midware.data.model.P3.DataEyeGetPushAvoidanceParam;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance.SensorType;
import dji.midware.data.model.P3.DataEyeGetPushFunctionList;
import dji.midware.data.model.P3.DataEyeGetPushPointState;
import dji.midware.data.model.P3.DataEyeGetPushSensorException;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataSingleSetPointPos.TapMode;
import dji.midware.data.model.P3.DataSingleVisualParam.TrackingMode;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.visual.util.c;
import java.util.HashMap;
import java.util.Map.Entry;

public class a implements g {
    private ProductType a = ProductType.OTHER;
    private volatile boolean b = false;
    private volatile RcModeChannel c = RcModeChannel.CHANNEL_UNKNOWN;
    private volatile FLYC_STATE d = FLYC_STATE.GPS_Atti;
    private TrackingMode e = TrackingMode.a;
    private TapMode f = TapMode.a;
    private final HashMap<SensorType, g$b> g = new HashMap(5);
    private final Context h = DJIApplication.a();
    private final e i = new e();

    a() {
        this.g.put(SensorType.Front, new g$b(SensorType.Front));
        this.g.put(SensorType.Back, new g$b(SensorType.Back));
        this.g.put(SensorType.Left, new g$b(SensorType.Left));
        this.g.put(SensorType.Right, new g$b(SensorType.Right));
        this.g.put(SensorType.Top, new g$b(SensorType.Top));
    }

    void a() {
        a(i.getInstance().c());
        if (DataFlycGetPushAvoidParam.getInstance().isGetted()) {
            a(DataFlycGetPushAvoidParam.getInstance());
        }
        if (DataEyeGetPushFunctionList.getInstance().isGetted()) {
            a(DataEyeGetPushFunctionList.getInstance());
        }
        if (DataEyeGetPushAvoidanceParam.getInstance().isGetted()) {
            a(DataEyeGetPushAvoidanceParam.getInstance());
        }
    }

    void b() {
        c();
        d();
    }

    void a(ProductType productType) {
        if (productType != this.a) {
            for (Entry entry : this.g.entrySet()) {
                ((g$b) entry.getValue()).a(c.a((SensorType) entry.getKey()), c.e());
            }
            dji.thirdparty.a.c.a().e(g$d.AVOID_CHANGED);
            this.a = productType;
        }
    }

    void c() {
        this.e = TrackingMode.a;
        this.f = TapMode.a;
        this.b = false;
        this.c = RcModeChannel.CHANNEL_UNKNOWN;
        this.d = FLYC_STATE.GPS_Atti;
        for (Entry entry : this.g.entrySet()) {
            ((g$b) entry.getValue()).a(c.a((SensorType) entry.getKey()), c.e());
        }
        dji.thirdparty.a.c.a().e(g$d.AVOID_CHANGED);
        a(Integer.MAX_VALUE, false);
    }

    void d() {
        this.i.b();
    }

    void a(int i, boolean z) {
        this.i.a(b(i), z);
    }

    boolean a(SensorType sensorType) {
        return g.c.c == ((g$b) this.g.get(sensorType)).c;
    }

    boolean e() {
        boolean z = false;
        for (Entry value : this.g.entrySet()) {
            boolean z2;
            g$b dji_pilot_visual_a_g_b = (g$b) value.getValue();
            if (dji_pilot_visual_a_g_b.a()) {
                if (!dji_pilot_visual_a_g_b.c.a()) {
                    z = false;
                    break;
                }
                z2 = true;
            } else {
                z2 = z;
            }
            z = z2;
        }
        if (z) {
            return false;
        }
        return true;
    }

    g.c b(SensorType sensorType) {
        return ((g$b) this.g.get(sensorType)).c;
    }

    private boolean i() {
        for (Entry value : this.g.entrySet()) {
            g$b dji_pilot_visual_a_g_b = (g$b) value.getValue();
            if (dji_pilot_visual_a_g_b.a() && dji_pilot_visual_a_g_b.c == g.c.e) {
                return true;
            }
        }
        return false;
    }

    boolean f() {
        boolean z = false;
        for (Entry value : this.g.entrySet()) {
            boolean z2;
            g$b dji_pilot_visual_a_g_b = (g$b) value.getValue();
            if (!dji_pilot_visual_a_g_b.a()) {
                z2 = z;
            } else if (!dji_pilot_visual_a_g_b.c.a()) {
                return false;
            } else {
                z2 = true;
            }
            z = z2;
        }
        return z;
    }

    g.c g() {
        g.c cVar = g.c.a;
        g.c cVar2 = cVar;
        for (Entry value : this.g.entrySet()) {
            g$b dji_pilot_visual_a_g_b = (g$b) value.getValue();
            if (!dji_pilot_visual_a_g_b.a()) {
                cVar = cVar2;
            } else if (dji_pilot_visual_a_g_b.c.a()) {
                cVar = g.c.e;
            } else if (dji_pilot_visual_a_g_b.c == g.c.a) {
                return g.c.a;
            } else {
                return g.c.b;
            }
            cVar2 = cVar;
        }
        return cVar2;
    }

    g$b c(SensorType sensorType) {
        return (g$b) this.g.get(sensorType);
    }

    int d(SensorType sensorType) {
        return ((g$b) this.g.get(sensorType)).d;
    }

    private void a(boolean z, RcModeChannel rcModeChannel) {
        if (!z) {
            rcModeChannel = RcModeChannel.CHANNEL_P;
        }
        if (this.c != rcModeChannel) {
            this.c = rcModeChannel;
            j();
        }
    }

    void a(g$f dji_pilot_visual_a_g_f) {
        if (g$f.NONE == dji_pilot_visual_a_g_f) {
            this.e = TrackingMode.a;
            this.f = TapMode.a;
            j();
        }
    }

    void a(DataEyeGetPushTrackStatus dataEyeGetPushTrackStatus) {
        TrackingMode trackingMode = dataEyeGetPushTrackStatus.getTrackingMode();
        if (trackingMode != this.e) {
            this.e = trackingMode;
            j();
        }
    }

    void a(DataEyeGetPushPointState dataEyeGetPushPointState) {
        TapMode tapMode = dataEyeGetPushPointState.getTapMode();
        if (this.f != tapMode) {
            this.f = tapMode;
            j();
        }
    }

    void h() {
        if (!c.getInstance().c() && TapMode.a != this.f) {
            this.f = TapMode.a;
            j();
        }
    }

    private void j() {
        for (Entry entry : this.g.entrySet()) {
            if (((g$b) entry.getValue()).a()) {
                e((SensorType) entry.getKey());
            }
        }
    }

    private void e(SensorType sensorType) {
        if (!c.e()) {
            a(sensorType, g.c.a);
        } else if (b.a(this.d) || FLYC_STATE.SPORT == this.d || FLYC_STATE.AutoLanding == this.d || !c.a(this.e) || !c.a(this.f)) {
            a(sensorType, g.c.b);
        } else if (l(sensorType)) {
            a(sensorType, g.c.d);
        } else if (((g$b) this.g.get(sensorType)).b >= 5) {
            a(sensorType, g.c.c);
        } else {
            a(sensorType, g.c.e);
        }
    }

    private boolean f(SensorType sensorType) {
        return Data2100GetPushCheckStatus.getInstance().isPropellerCover();
    }

    private boolean k() {
        DataEyeGetPushFunctionList instance = DataEyeGetPushFunctionList.getInstance();
        return instance.isGetted() && instance.sensorStatusSource();
    }

    private boolean g(SensorType sensorType) {
        if (SensorType.Back == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isBackOverExposure();
        }
        if (SensorType.Right == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isRightOverExposure();
        }
        if (SensorType.Left == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isLeftOverExposure();
        }
        if (SensorType.Bottom == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isBottomOverExposure();
        }
        if (SensorType.Front != sensorType) {
            return false;
        }
        if (k()) {
            return DataEyeGetPushSensorException.getInstance().isFrontOverExposure();
        }
        return DataEyeGetPushException.getInstance().isFronImageOverExposure();
    }

    private boolean h(SensorType sensorType) {
        if (SensorType.Back == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isBackUnderExposure();
        }
        if (SensorType.Right == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isRightUnderExposure();
        }
        if (SensorType.Left == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isLeftUnderExposure();
        }
        if (SensorType.Bottom == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isBottomUnderExposure();
        }
        if (SensorType.Front != sensorType) {
            return false;
        }
        if (k()) {
            return DataEyeGetPushSensorException.getInstance().isFrontUnderExposure();
        }
        return DataEyeGetPushException.getInstance().isFronImageUnderExposure();
    }

    private int i(SensorType sensorType) {
        int i = R.string.visual_radar_dected_fail_diff;
        if (SensorType.Back == sensorType) {
            if (DataEyeGetPushSensorException.getInstance().isBackImageDiff()) {
                return R.string.visual_radar_dected_fail_diff_back;
            }
            return 0;
        } else if (SensorType.Right == sensorType) {
            if (DataEyeGetPushSensorException.getInstance().isRightImageDiff()) {
                return R.string.visual_radar_dected_fail_diff_right;
            }
            return 0;
        } else if (SensorType.Left == sensorType) {
            if (DataEyeGetPushSensorException.getInstance().isLeftImageDiff()) {
                return R.string.visual_radar_dected_fail_diff_left;
            }
            return 0;
        } else if (SensorType.Bottom == sensorType) {
            if (DataEyeGetPushSensorException.getInstance().isBottomImageDiff()) {
                return R.string.visual_radar_dected_fail_diff_bottom;
            }
            return 0;
        } else if (SensorType.Front != sensorType) {
            return 0;
        } else {
            if (k()) {
                return DataEyeGetPushSensorException.getInstance().isFrontImageDiff() ? R.string.visual_radar_dected_fail_diff : 0;
            } else {
                if (!DataEyeGetPushException.getInstance().isFrontImageDiff()) {
                    i = 0;
                }
                return i;
            }
        }
    }

    private int j(SensorType sensorType) {
        if (SensorType.Back == sensorType) {
            return R.string.visual_radar_detect_abnormal_back;
        }
        if (SensorType.Right == sensorType) {
            return R.string.visual_radar_detect_abnormal_right;
        }
        if (SensorType.Left == sensorType) {
            return R.string.visual_radar_detect_abnormal_left;
        }
        if (SensorType.Bottom == sensorType) {
            return R.string.visual_radar_detect_abnormal_bottom;
        }
        return R.string.visual_radar_detect_abnormal;
    }

    private boolean k(SensorType sensorType) {
        if (SensorType.Back == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isBackImageExposureTooLong();
        }
        if (SensorType.Right == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isRightImageExposureTooLong();
        }
        if (SensorType.Left == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isLeftImageExposureTooLong();
        }
        if (SensorType.Bottom == sensorType) {
            return DataEyeGetPushSensorException.getInstance().isBottomImageExposureTooLong();
        }
        return DataEyeGetPushSensorException.getInstance().isFrontImageExposureTooLong();
    }

    private void a(SensorType sensorType, g.c cVar) {
        g$b dji_pilot_visual_a_g_b = (g$b) this.g.get(sensorType);
        if (cVar != dji_pilot_visual_a_g_b.c) {
            dji_pilot_visual_a_g_b.c = cVar;
            if (cVar != g.c.e) {
                dji_pilot_visual_a_g_b.e = Integer.MAX_VALUE;
                a(l(), false);
            }
            if (cVar == g.c.c) {
                if (f(sensorType)) {
                    dji_pilot_visual_a_g_b.d = R.string.visual_radar_dected_faile_propeller_cover;
                } else if (g(sensorType)) {
                    dji_pilot_visual_a_g_b.d = R.string.visual_radar_detect_fail_light;
                } else if (h(sensorType)) {
                    dji_pilot_visual_a_g_b.d = R.string.visual_radar_detect_fail_dark;
                } else {
                    int i = i(sensorType);
                    if (i != 0) {
                        dji_pilot_visual_a_g_b.d = i;
                    } else if (k(sensorType)) {
                        dji_pilot_visual_a_g_b.d = R.string.visual_radar_dected_fail_exposure_toolong;
                    } else {
                        dji_pilot_visual_a_g_b.d = j(sensorType);
                    }
                }
            }
            if (g.c.a == cVar || g.c.b == cVar) {
                dji_pilot_visual_a_g_b.b = 0;
            }
            dji.thirdparty.a.c.a().e(dji_pilot_visual_a_g_b);
        }
    }

    public void a(DataOsdGetPushCommon dataOsdGetPushCommon) {
        RcModeChannel modeChannel = dataOsdGetPushCommon.getModeChannel();
        DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
        boolean a = b.a(instance.isBeginnerMode(), instance.isMultipleModeOpen());
        FLYC_STATE flycState = dataOsdGetPushCommon.getFlycState();
        a(a, modeChannel);
        if (!(flycState == this.d || flycState == FLYC_STATE.AssitedTakeoff || flycState == FLYC_STATE.AutoTakeoff || flycState == FLYC_STATE.GoHome)) {
            if (i() && FLYC_STATE.AutoLanding == flycState) {
                DJIErrorPopView.b.b(d.b, R.string.visual_radar_dected_fail_landing, 0, DJIErrorPopView.c.c, f.a);
            }
            if (!(this.e == TrackingMode.a || (flycState == FLYC_STATE.NaviSubMode_Tracking && flycState == FLYC_STATE.TRACK_HEADLOCK))) {
                this.e = TrackingMode.a;
            }
            this.d = flycState;
            j();
        }
        boolean isMotorUp = dataOsdGetPushCommon.isMotorUp();
        if (this.b != isMotorUp) {
            this.b = isMotorUp;
            if (isMotorUp) {
                a(DataEyeGetPushAvoidanceParam.getInstance());
                return;
            }
            for (Entry value : this.g.entrySet()) {
                g$b dji_pilot_visual_a_g_b = (g$b) value.getValue();
                if (dji_pilot_visual_a_g_b.a()) {
                    dji_pilot_visual_a_g_b.e = Integer.MAX_VALUE;
                }
            }
            a(Integer.MAX_VALUE, false);
        }
    }

    void a(DataOsdGetPushHome dataOsdGetPushHome) {
        a(b.a(dataOsdGetPushHome.isBeginnerMode(), dataOsdGetPushHome.isMultipleModeOpen()), DataOsdGetPushCommon.getInstance().getModeChannel());
    }

    private void a(SensorType sensorType, g$a dji_pilot_visual_a_g_a, DataEyeGetPushFunctionList dataEyeGetPushFunctionList) {
        if (SensorType.Front == sensorType) {
            a(dataEyeGetPushFunctionList.isFrontDisableWhenAutoLanding(), dataEyeGetPushFunctionList.isFrontDisableByTripod(), dataEyeGetPushFunctionList.isFrontDisableBySwitchSensor(), dataEyeGetPushFunctionList.isFrontAttiTooLarge(), dji_pilot_visual_a_g_a, sensorType);
        } else if (SensorType.Back == sensorType) {
            a(dataEyeGetPushFunctionList.isBackDisableWhenAutoLanding(), dataEyeGetPushFunctionList.isBackDisableByTripod(), dataEyeGetPushFunctionList.isBackDisableBySwitchSensor(), dataEyeGetPushFunctionList.isBackAttiTooLarge(), dji_pilot_visual_a_g_a, sensorType);
        } else if (SensorType.Left == sensorType) {
            a(dataEyeGetPushFunctionList.isLeftDisableWhenAutoLanding(), dataEyeGetPushFunctionList.isLeftDisableByTripod(), false, dataEyeGetPushFunctionList.isLeftAttiTooLarge(), dji_pilot_visual_a_g_a, sensorType);
        } else if (SensorType.Right == sensorType) {
            a(dataEyeGetPushFunctionList.isRightDisableWhenAutoLanding(), dataEyeGetPushFunctionList.isRightDisableByTripod(), false, dataEyeGetPushFunctionList.isRightAttiTooLarge(), dji_pilot_visual_a_g_a, sensorType);
        }
    }

    private void a(boolean z, boolean z2, boolean z3, boolean z4, g$a dji_pilot_visual_a_g_a, SensorType sensorType) {
        int i = this.c == RcModeChannel.CHANNEL_P ? 1 : 0;
        String a = c.a(this.h, sensorType);
        if (z != dji_pilot_visual_a_g_a.a) {
            dji_pilot_visual_a_g_a.a = z;
        }
        if (z2 != dji_pilot_visual_a_g_a.b) {
            dji_pilot_visual_a_g_a.b = z2;
            if (z2) {
                DJIErrorPopView.b.a(d.b, this.h.getString(R.string.visual_radar_dected_fail_tripod, new Object[]{a}), null, DJIErrorPopView.c.a, f.a);
            }
        }
        if (z3 != dji_pilot_visual_a_g_a.c) {
            dji_pilot_visual_a_g_a.c = z3;
        }
        if (z4 != dji_pilot_visual_a_g_a.d) {
            dji_pilot_visual_a_g_a.d = z4;
            if (z4 && i != 0) {
                DJIErrorPopView.b.a(d.b, this.h.getString(R.string.visual_radar_dected_fail_attilarge, new Object[]{a}), null, DJIErrorPopView.c.a, f.a);
            }
        }
    }

    void a(DataEyeGetPushFunctionList dataEyeGetPushFunctionList) {
        j();
        for (Entry entry : this.g.entrySet()) {
            g$b dji_pilot_visual_a_g_b = (g$b) entry.getValue();
            if (dji_pilot_visual_a_g_b.b() && dji_pilot_visual_a_g_b.a() && dji_pilot_visual_a_g_b.c.a()) {
                a((SensorType) entry.getKey(), dji_pilot_visual_a_g_b.g, dataEyeGetPushFunctionList);
            }
        }
    }

    void a(DataFlycGetPushAvoidParam dataFlycGetPushAvoidParam) {
        j();
    }

    private boolean l(SensorType sensorType) {
        DataEyeGetPushFunctionList instance = DataEyeGetPushFunctionList.getInstance();
        if (SensorType.Front == sensorType) {
            return instance.isFrontDisable();
        }
        if (SensorType.Back == sensorType) {
            return instance.isBackDisable();
        }
        if (SensorType.Left == sensorType) {
            return instance.isLeftDisable();
        }
        if (SensorType.Right == sensorType) {
            return instance.isRightDisable();
        }
        return false;
    }

    private boolean a(SensorType sensorType, boolean z) {
        return dji.pilot2.simulator.d.h() || z;
    }

    private void a(SensorType sensorType, boolean z, boolean z2, int i) {
        g$b dji_pilot_visual_a_g_b = (g$b) this.g.get(sensorType);
        if (g.c.e == dji_pilot_visual_a_g_b.c || g.c.c == dji_pilot_visual_a_g_b.c) {
            if (z) {
                if (dji_pilot_visual_a_g_b.b != 0) {
                    dji_pilot_visual_a_g_b.b = 0;
                    e(sensorType);
                }
            } else if (!a(sensorType, z2)) {
                dji_pilot_visual_a_g_b.b++;
                e(sensorType);
            }
        }
        if (g.c.e == dji_pilot_visual_a_g_b.c && this.b && !dji.pilot2.simulator.d.h()) {
            if (15 == i) {
                i = Integer.MAX_VALUE;
            }
            if (i != dji_pilot_visual_a_g_b.e) {
                dji_pilot_visual_a_g_b.e = i;
                a(l(), true);
            }
        }
    }

    private int l() {
        int i = Integer.MAX_VALUE;
        for (Entry value : this.g.entrySet()) {
            int i2;
            g$b dji_pilot_visual_a_g_b = (g$b) value.getValue();
            if (dji_pilot_visual_a_g_b.b() && dji_pilot_visual_a_g_b.a() && dji_pilot_visual_a_g_b.e < i) {
                i2 = dji_pilot_visual_a_g_b.e;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    private boolean a(SensorType sensorType, DataEyeGetPushAvoidanceParam dataEyeGetPushAvoidanceParam) {
        if (SensorType.Front == sensorType) {
            return dataEyeGetPushAvoidanceParam.isAvoidFrontWork();
        }
        if (SensorType.Back == sensorType) {
            return dataEyeGetPushAvoidanceParam.isAvoidBehindWork();
        }
        if (SensorType.Left == sensorType) {
            return dataEyeGetPushAvoidanceParam.isAvoidLeftWork();
        }
        if (SensorType.Right == sensorType) {
            return dataEyeGetPushAvoidanceParam.isAvoidRightWork();
        }
        return false;
    }

    private int a(int i) {
        return (1 > i || i > 4) ? Integer.MAX_VALUE : i;
    }

    private int b(SensorType sensorType, DataEyeGetPushAvoidanceParam dataEyeGetPushAvoidanceParam) {
        int i = Integer.MAX_VALUE;
        if (SensorType.Front == sensorType) {
            i = dataEyeGetPushAvoidanceParam.getAvoidFrontAlertLevel();
        } else if (SensorType.Back == sensorType) {
            i = dataEyeGetPushAvoidanceParam.getAvoidBehindAlertLevel();
        } else if (SensorType.Left == sensorType) {
            i = dataEyeGetPushAvoidanceParam.getAvoidLeftAlertLevel();
        } else if (SensorType.Right == sensorType) {
            i = dataEyeGetPushAvoidanceParam.getAvoidRightAlertLevel();
        }
        return a(i);
    }

    void a(DataEyeGetPushAvoidanceParam dataEyeGetPushAvoidanceParam) {
        for (Entry entry : this.g.entrySet()) {
            g$b dji_pilot_visual_a_g_b = (g$b) entry.getValue();
            if (dji_pilot_visual_a_g_b.b() && dji_pilot_visual_a_g_b.a()) {
                SensorType sensorType = (SensorType) entry.getKey();
                a(sensorType, a(sensorType, dataEyeGetPushAvoidanceParam), dataEyeGetPushAvoidanceParam.isBraking(), b(sensorType, dataEyeGetPushAvoidanceParam));
            }
        }
    }

    private dji.pilot.visual.a.e.a b(int i) {
        dji.pilot.visual.a.e.a aVar = dji.pilot.visual.a.e.a.BEEP_NONE;
        if (4 == i) {
            return dji.pilot.visual.a.e.a.BEEP_100;
        }
        if (3 == i) {
            return dji.pilot.visual.a.e.a.BEEP_250;
        }
        if (2 == i) {
            return dji.pilot.visual.a.e.a.BEEP_500;
        }
        if (1 == i) {
            return dji.pilot.visual.a.e.a.BEEP_1000;
        }
        return aVar;
    }
}
