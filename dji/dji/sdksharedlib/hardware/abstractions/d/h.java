package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.common.flightcontroller.DJIVisionDetectionSector;
import dji.common.flightcontroller.DJIVisionSectorWarning;
import dji.common.flightcontroller.DJIVisionSystemWarning;
import dji.midware.data.model.P3.DataEyeGetPushAvoidanceParam;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPushParamsByHash;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.params.P3.a;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.f;
import java.util.ArrayList;
import java.util.List;

public class h extends d {
    private static final String a = "g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0";
    private static final String b = "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0";
    private static final String c = "g_config.mvo_cfg.mvo_func_en_0";
    private static final String d = "g_config.go_home.avoid_enable_0";
    private final String[] e = new String[]{a.w, a.x, "g_status.acc_gyro[0].cali_cnt_0", "g_status.acc_gyro[1].cali_cnt_0"};

    protected void g_() {
        a((Object) Boolean.valueOf(false), c("IsLandingGearMovable"));
        a((Object) Boolean.valueOf(false), c(e.i));
        a((Object) Boolean.valueOf(false), c(e.f));
        a((Object) Integer.valueOf(2), c(e.e));
        a((Object) Boolean.valueOf(true), c(e.s));
    }

    protected void a() {
        super.a();
    }

    @f(a = "CollisionAvoidanceEnabled")
    public void e(boolean z, b.e eVar) {
        if (z) {
            DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
            dataFlycSetParams.a("g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0", "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0", "g_config.go_home.avoid_enable_0");
            dataFlycSetParams.a(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1));
            dataFlycSetParams.start(new 1(this, eVar));
            return;
        }
        dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a("g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0", "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0", "g_config.go_home.avoid_enable_0");
        dataFlycSetParams.a(Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
        dataFlycSetParams.start(new 2(this, eVar));
    }

    @f(a = "VisionPositioningEnabled")
    public void f(boolean z, b.e eVar) {
        if (z) {
            new DataFlycSetParams().a("g_config.mvo_cfg.mvo_func_en_0", Integer.valueOf(1)).start(new 3(this, eVar));
        } else {
            new DataFlycSetParams().a("g_config.mvo_cfg.mvo_func_en_0", Integer.valueOf(0)).start(new 4(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "VisionPositioningEnabled")
    public void E(b.e eVar) {
        this.F.a("g_config.mvo_cfg.mvo_func_en_0", new 5(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartIMUCalibration")
    public void h(b.e eVar) {
        String[] strArr = new String[]{a.k, a.l};
        Number[] numberArr = new Number[]{Integer.valueOf(1), Integer.valueOf(1)};
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(strArr);
        dataFlycSetParams.a(numberArr);
        dataFlycSetParams.start(new 6(this, eVar));
    }

    protected void a(DataFlycGetPushParamsByHash dataFlycGetPushParamsByHash) {
        if (DataOsdGetPushCommon.getInstance().groundOrSky() != 2) {
            new DataFlycGetParams().setInfos(this.e).start(new 7(this));
        }
    }

    public void onEventBackgroundThread(DataEyeGetPushAvoidanceParam dataEyeGetPushAvoidanceParam) {
        a((Object) Boolean.valueOf(dataEyeGetPushAvoidanceParam.isBraking()), c(e.bm));
        boolean z = dataEyeGetPushAvoidanceParam.isVisualSensorWorking() && dataEyeGetPushAvoidanceParam.isAvoidFrontWork();
        a((Object) Boolean.valueOf(z), c(e.bn));
        a((Object) DJIVisionSystemWarning.find(dataEyeGetPushAvoidanceParam.getAvoidFrontAlertLevel()), c(e.bo));
    }

    public void onEventBackgroundThread(DataEyeGetPushFrontAvoidance dataEyeGetPushFrontAvoidance) {
        List arrayList = new ArrayList(dataEyeGetPushFrontAvoidance.getObserveCount());
        int[] observeValues = dataEyeGetPushFrontAvoidance.getObserveValues();
        int i = 0;
        while (i < Math.max(arrayList.size(), observeValues.length)) {
            if (i >= arrayList.size()) {
                arrayList.add(new DJIVisionDetectionSector(b(observeValues[i]), ((float) observeValues[i]) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
                break;
            } else if (i >= observeValues.length) {
                arrayList.remove(i);
                int i2 = i - 1;
                break;
            } else if (arrayList.get(i) == null) {
                arrayList.set(i, new DJIVisionDetectionSector(b(observeValues[i]), ((float) observeValues[i]) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
                break;
            } else {
                ((DJIVisionDetectionSector) arrayList.get(i)).setWarningLevel(b(observeValues[i]));
                ((DJIVisionDetectionSector) arrayList.get(i)).setObstacleDistanceInMeters(((float) observeValues[i]) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
                i++;
            }
        }
        a((Object) (DJIVisionDetectionSector[]) arrayList.toArray(new DJIVisionDetectionSector[arrayList.size()]), c(e.bp));
    }

    private DJIVisionSectorWarning b(int i) {
        if (i < 0) {
            return DJIVisionSectorWarning.Unknown;
        }
        if (i >= 7000) {
            return DJIVisionSectorWarning.Invalid;
        }
        if (i <= 200) {
            return DJIVisionSectorWarning.Level4;
        }
        if (i <= 300) {
            return DJIVisionSectorWarning.Level3;
        }
        if (i <= 400) {
            return DJIVisionSectorWarning.Level2;
        }
        return DJIVisionSectorWarning.Level1;
    }
}
