package dji.sdksharedlib.hardware.abstractions.d;

import android.location.Location;
import android.util.Log;
import com.here.posclient.analytics.TrackerEvent;
import dji.common.error.DJIError;
import dji.common.error.DJIFlightControllerError;
import dji.common.flightcontroller.DJIAircraftRemainingBatteryState;
import dji.common.flightcontroller.DJICompassCalibrationStatus;
import dji.common.flightcontroller.DJIFlightControllerAdvancedGoHomeState;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.common.flightcontroller.DJIFlightControllerFlightMode;
import dji.common.flightcontroller.DJIFlightControllerPreciseLandingState;
import dji.common.flightcontroller.DJIFlightFailsafeOperation;
import dji.common.flightcontroller.DJIFlightOrientationMode;
import dji.common.flightcontroller.DJIGPSSignalStatus;
import dji.common.flightcontroller.DJIGoHomeStatus;
import dji.common.flightcontroller.DJIIMUCalibrationStatus;
import dji.common.flightcontroller.DJIIMUSensorState;
import dji.common.flightcontroller.DJILocationCoordinate2D;
import dji.common.flightcontroller.DJISimulatorInitializationData;
import dji.common.flightcontroller.DJISimulatorStateData;
import dji.common.flightcontroller.DJIVirtualStickFlightControlData;
import dji.common.flightcontroller.DJIVirtualStickFlightCoordinateSystem;
import dji.common.flightcontroller.DJIVirtualStickRollPitchControlMode;
import dji.common.flightcontroller.DJIVirtualStickVerticalControlMode;
import dji.common.flightcontroller.DJIVirtualStickYawControlMode;
import dji.common.util.CallbackUtils;
import dji.common.util.LocationUtils;
import dji.common.util.MultiModeEnabledUtil;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetFsAction;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPushAvoid;
import dji.midware.data.model.P3.DataFlycGetPushFlycInstallError;
import dji.midware.data.model.P3.DataFlycGetPushParamsByHash;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionInfo;
import dji.midware.data.model.P3.DataFlycGetVoltageWarnning;
import dji.midware.data.model.P3.DataFlycGetVoltageWarnning.WarnningLevel;
import dji.midware.data.model.P3.DataFlycJoystick;
import dji.midware.data.model.P3.DataFlycNavigationSwitch;
import dji.midware.data.model.P3.DataFlycNavigationSwitch.GS_COMMAND;
import dji.midware.data.model.P3.DataFlycSetHomePoint;
import dji.midware.data.model.P3.DataFlycSetHomePoint.HOMETYPE;
import dji.midware.data.model.P3.DataFlycSetLVoltageWarnning;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataFlycSetSendOnBoard;
import dji.midware.data.model.P3.DataFlycStopIoc;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.GOHOME_STATUS;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataSimulatorGetPushConnectHeartPacket;
import dji.midware.data.model.P3.DataSimulatorGetPushFlightStatusParams;
import dji.midware.data.model.P3.DataSimulatorSimulateFlightCommend;
import dji.midware.data.params.P3.ParamInfo;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b.f;
import dji.sdksharedlib.hardware.abstractions.d.a.a;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.Timer;

public class d extends b {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 6;
    private static final int h = 7;
    private static final int i = 8;
    private static final int j = 9;
    private static final int k = 10;
    private static final int l = 14;
    private static final int m = 15;
    private static final int n = 16;
    private static final int o = 17;
    private static final int p = 255;
    private static final int q = 0;
    private static final int r = 1;
    private static final int s = 2;
    protected a F;
    protected dji.sdksharedlib.hardware.abstractions.d.a.b G;
    protected DJILocationCoordinate2D H;
    private boolean I;
    private long J;
    private DJIGoHomeStatus K;
    private DJICompassCalibrationStatus L;
    private boolean M;
    private b N;
    private Timer O;
    private boolean P;
    private dji.sdksharedlib.e.a Q;
    private boolean t;
    private boolean u;

    public d() {
        this.M = false;
        this.N = b.a;
        this.P = false;
        this.Q = dji.sdksharedlib.e.a.getInstance();
        this.H = new DJILocationCoordinate2D();
        this.I = false;
        this.L = DJICompassCalibrationStatus.Normal;
        this.u = false;
        this.K = DJIGoHomeStatus.None;
        this.N = b.b;
    }

    protected void g_() {
        a((Object) Boolean.valueOf(false), c("IsLandingGearMovable"));
        a((Object) Boolean.valueOf(false), c(e.i));
        a((Object) Boolean.valueOf(false), c(e.f));
        a((Object) Integer.valueOf(1), c(e.e));
        a((Object) Boolean.valueOf(false), c(e.s));
        a((Object) Boolean.valueOf(true), c(dji.sdksharedlib.b.d.ck));
    }

    public void a(String str, int i, c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        p();
        g_();
        this.F = new a();
        this.G = new dji.sdksharedlib.hardware.abstractions.d.a.b();
    }

    protected void p() {
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            onEventBackgroundThread(DataOsdGetPushCommon.getInstance());
            onEventBackgroundThread(DataOsdGetPushHome.getInstance());
        }
        onEventBackgroundThread(DataFlycGetPushAvoid.getInstance());
        onEventBackgroundThread(DataSimulatorGetPushConnectHeartPacket.getInstance());
        onEventBackgroundThread(DataFlycGetPushParamsByHash.getInstance());
        onEventBackgroundThread(DataOsdGetPushHome.getInstance());
        onEventBackgroundThread(DataFlycGetPushSmartBattery.getInstance());
        onEventBackgroundThread(DataEyeGetPushException.getInstance());
    }

    public void e() {
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
        super.e();
    }

    protected void a() {
        a(e.class, getClass());
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartSimulator")
    public void a(b.e eVar, DJISimulatorInitializationData dJISimulatorInitializationData) {
        Log.d(e.bS, "click");
        if (this.N != b.b && this.N != b.g) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else if (dJISimulatorInitializationData == null || !LocationUtils.checkValidGPSCoordinate(dJISimulatorInitializationData.latitude, dJISimulatorInitializationData.longitude) || dJISimulatorInitializationData.simulationStateUpdateFrequency > 150 || dJISimulatorInitializationData.simulationStateUpdateFrequency < 2 || dJISimulatorInitializationData.numOfSatellites < 0 || dJISimulatorInitializationData.numOfSatellites > 20) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else {
            this.N = b.c;
            this.O = new Timer();
            this.O.schedule(new a(this, null), 0, 1000);
            DataSimulatorSimulateFlightCommend.getInstance().a(dJISimulatorInitializationData.latitude).b(dJISimulatorInitializationData.longitude).c(0.0d).b((int) (600.0f / ((float) dJISimulatorInitializationData.simulationStateUpdateFrequency))).a(true).b(false).c(false).a(dJISimulatorInitializationData.numOfSatellites).d(true).e(true).f(true).g(true).h(true).i(true).j(true).k(true).l(true).m(true).n(true).a().start(new 1(this));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StopSimulator")
    public void j(b.e eVar) {
        if (this.N == b.a) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_DISCONNECTED);
        } else if (this.N != b.e) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_EXECUTIONFAILED);
        } else {
            this.N = b.f;
            DataSimulatorSimulateFlightCommend.getInstance().b().start(new 12(this));
            CallbackUtils.onSuccess(eVar, null);
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushAvoid dataFlycGetPushAvoid) {
        a((Object) Boolean.valueOf(dataFlycGetPushAvoid.isVisualSensorEnable()), c(e.bZ));
        a((Object) Boolean.valueOf(dataFlycGetPushAvoid.isVisualSensorWork()), c(e.ca));
    }

    public void onEventBackgroundThread(DataSimulatorGetPushConnectHeartPacket dataSimulatorGetPushConnectHeartPacket) {
    }

    public void onEventBackgroundThread(DataSimulatorGetPushFlightStatusParams dataSimulatorGetPushFlightStatusParams) {
        DJISimulatorStateData dJISimulatorStateData = new DJISimulatorStateData();
        dJISimulatorStateData.setMotorsOn(dataSimulatorGetPushFlightStatusParams.hasMotorTurnedOn());
        dJISimulatorStateData.setFlying(dataSimulatorGetPushFlightStatusParams.isInTheAir());
        dJISimulatorStateData.setRoll((float) (((double) (a(dataSimulatorGetPushFlightStatusParams, 0) * 180.0f)) / 3.141592653589793d));
        dJISimulatorStateData.setPitch(-((float) (((double) (a(dataSimulatorGetPushFlightStatusParams, 1) * 180.0f)) / 3.141592653589793d)));
        dJISimulatorStateData.setYaw((float) (((double) (a(dataSimulatorGetPushFlightStatusParams, 2) * 180.0f)) / 3.141592653589793d));
        dJISimulatorStateData.setPositionX(a(dataSimulatorGetPushFlightStatusParams, 3));
        dJISimulatorStateData.setPositionY(a(dataSimulatorGetPushFlightStatusParams, 4));
        dJISimulatorStateData.setPositionZ(a(dataSimulatorGetPushFlightStatusParams, 5));
        if (dji.internal.a.getInstance().d() == null || !dji.internal.a.getInstance().d().startsWith("03.01")) {
            dJISimulatorStateData.setLatitude((((double) a(dataSimulatorGetPushFlightStatusParams, 6)) * 180.0d) / 3.14d);
            dJISimulatorStateData.setLongitude((((double) a(dataSimulatorGetPushFlightStatusParams, 7)) * 180.0d) / 3.14d);
        } else {
            dJISimulatorStateData.setLatitude((double) a(dataSimulatorGetPushFlightStatusParams, 6));
            dJISimulatorStateData.setLongitude((double) a(dataSimulatorGetPushFlightStatusParams, 7));
        }
        a((Object) dJISimulatorStateData, c(e.br));
    }

    public void onEventBackgroundThread(DataFlycGetPushFlycInstallError dataFlycGetPushFlycInstallError) {
        if (dataFlycGetPushFlycInstallError.getYawInstallErrorLevel() >= 2) {
            a((Object) Integer.valueOf(2), c(e.cV));
        }
        if (dataFlycGetPushFlycInstallError.getRollInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getPitchInstallErrorLevel() >= 2) {
            a((Object) Integer.valueOf(2), c(e.cW));
        }
        if (dataFlycGetPushFlycInstallError.getGyroXInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getGyroYInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getGyroZInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getAccXInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getAccYInstallErrorLevel() >= 2 || dataFlycGetPushFlycInstallError.getAccZInstallErrorLevel() >= 2) {
            a((Object) Integer.valueOf(2), c(e.cX));
        }
        if (dataFlycGetPushFlycInstallError.getThrustInstallErrorLevel() >= 2) {
            a((Object) Integer.valueOf(2), c(e.cY));
        }
    }

    private float a(DataSimulatorGetPushFlightStatusParams dataSimulatorGetPushFlightStatusParams, int i) {
        return dji.midware.util.c.d(dji.midware.util.c.e(dataSimulatorGetPushFlightStatusParams.getResult(), (i * 4) + 2, 4));
    }

    public void onEventBackgroundThread(DataFlycGetPushParamsByHash dataFlycGetPushParamsByHash) {
        a(dataFlycGetPushParamsByHash);
    }

    protected void a(DataFlycGetPushParamsByHash dataFlycGetPushParamsByHash) {
        Object obj;
        Object obj2;
        int intValue = dji.midware.data.manager.P3.d.valueOf("imu_app_temp_cali.cali_cnt_0").intValue();
        DJIIMUCalibrationStatus a = a("imu_app_temp_cali.state_0");
        if (a.equals(DJIIMUCalibrationStatus.InProgress)) {
            obj = DJIIMUSensorState.Calibrating;
            obj2 = DJIIMUSensorState.Calibrating;
        } else if (DataOsdGetPushCommon.getInstance().isImuInitError()) {
            obj = DJIIMUSensorState.DataException;
            obj2 = DJIIMUSensorState.DataException;
        } else if (DataOsdGetPushCommon.getInstance().isImuPreheatd()) {
            obj = DJIIMUSensorState.BiasNormal;
            obj2 = DJIIMUSensorState.BiasNormal;
        } else {
            obj = DJIIMUSensorState.WarmingUp;
            obj2 = DJIIMUSensorState.WarmingUp;
        }
        dji.sdksharedlib.b.c.a b = new dji.sdksharedlib.b.c.a().b(e.a).a(Integer.valueOf(0)).c("IMU").b(Integer.valueOf(0));
        a(obj, b.d(e.p).a());
        a(obj2, b.d(e.q).a());
        a((Object) Integer.valueOf(intValue), b.d(e.O).a());
        a((Object) a, b.d(e.r).a());
    }

    protected DJIIMUCalibrationStatus a(String str) {
        int intValue = dji.midware.data.manager.P3.d.read(str).value.intValue();
        if (intValue == 1 || intValue == 2) {
            if (this.P) {
                return DJIIMUCalibrationStatus.InProgress;
            }
            this.P = true;
            return DJIIMUCalibrationStatus.Initialization;
        } else if (intValue == 80 || intValue == 81) {
            if (!this.P) {
                return DJIIMUCalibrationStatus.None;
            }
            this.P = false;
            return DJIIMUCalibrationStatus.Succeed;
        } else if (intValue < 0) {
            return DJIIMUCalibrationStatus.Failed;
        } else {
            return DJIIMUCalibrationStatus.None;
        }
    }

    public void onEventBackgroundThread(DataEyeGetPushException dataEyeGetPushException) {
        if (dataEyeGetPushException.isInAdvanceHoming()) {
            a((Object) Boolean.valueOf(true), c(e.cJ));
            a((Object) DJIFlightControllerAdvancedGoHomeState.find(dataEyeGetPushException.getAdvanceGoHomeState().value()), c(e.cN));
        } else {
            a((Object) Boolean.valueOf(false), c(e.cJ));
            a((Object) DJIFlightControllerAdvancedGoHomeState.None, c(e.cN));
        }
        if (dataEyeGetPushException.isInPreciseLanding()) {
            a((Object) Boolean.valueOf(true), c(e.cM));
            a((Object) DJIFlightControllerPreciseLandingState.find(dataEyeGetPushException.getPreciseLandingState().value()), c(e.cO));
            return;
        }
        a((Object) Boolean.valueOf(false), c(e.cM));
        a((Object) DJIFlightControllerPreciseLandingState.None, c(e.cO));
    }

    public void onEventBackgroundThread(DataFlycGetPushWayPointMissionInfo dataFlycGetPushWayPointMissionInfo) {
        this.J = System.currentTimeMillis();
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        boolean z = true;
        if (dataOsdGetPushCommon.getRecDataLen() != 0) {
            double d;
            boolean z2;
            a((Object) Boolean.valueOf(dataOsdGetPushCommon.isVisionUsed()), c(e.aj));
            a((Object) Boolean.valueOf(dataOsdGetPushCommon.isGpsUsed()), c(e.cU));
            a((Object) Integer.valueOf(dataOsdGetPushCommon.getFlycVersion()), c(e.cm));
            a((Object) DJIFlightControllerFlightMode.find(dataOsdGetPushCommon.getFlycState().value()), c(e.bk));
            a((Object) Boolean.valueOf(dataOsdGetPushCommon.getCompassError()), c(e.L));
            a((Object) Boolean.valueOf(dataOsdGetPushCommon.getFlightAction().equals(FLIGHT_ACTION.OUTOF_CONTROL_GOHOME)), c(e.af));
            a((Object) Boolean.valueOf(!dataOsdGetPushCommon.isImuPreheatd()), c(e.ag));
            a((Object) Boolean.valueOf(dataOsdGetPushCommon.getWaveError()), c(e.ay));
            a((Object) Integer.valueOf(dataOsdGetPushCommon.getFlyTime()), c(e.az));
            int[] a = a(DataOsdGetPushCommon.getInstance().getFlycState(), DataOsdGetPushCommon.getInstance().isVisionUsed());
            DJIGoHomeStatus dJIGoHomeStatus = DJIGoHomeStatus.None;
            if (a[0] == 2) {
                dJIGoHomeStatus = DJIGoHomeStatus.GoDownToGround;
            } else if (dataOsdGetPushCommon.getGohomeStatus().equals(GOHOME_STATUS.ASCENDING)) {
                dJIGoHomeStatus = DJIGoHomeStatus.GoUpToHeight;
            } else {
                dJIGoHomeStatus = DJIGoHomeStatus.find(dataOsdGetPushCommon.getGohomeStatus().value());
            }
            if (!(a[0] == 2 || a[0] == 4 || !r0.equals(DJIGoHomeStatus.AutoFlyToHomePoint))) {
                dJIGoHomeStatus = DJIGoHomeStatus.None;
            }
            if (this.K.equals(DJIGoHomeStatus.GoDownToGround) && r0.equals(DJIGoHomeStatus.None)) {
                dJIGoHomeStatus = DJIGoHomeStatus.Completion;
            }
            a((Object) dJIGoHomeStatus, c(e.aA));
            this.K = dJIGoHomeStatus;
            a((Object) Integer.valueOf(dataOsdGetPushCommon.getGpsNum()), c(e.Q));
            if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 6 || c()) {
                a((Object) DJIGPSSignalStatus.find(b(dataOsdGetPushCommon.getGpsNum())), c(e.ae));
            } else {
                a((Object) DJIGPSSignalStatus.find(dataOsdGetPushCommon.getGpsLevel()), c(e.ae));
            }
            if (dataOsdGetPushCommon.getLatitude() == 0.0d) {
                d = Double.NaN;
            } else {
                d = dataOsdGetPushCommon.getLatitude();
            }
            a((Object) Double.valueOf(d), c(e.R));
            if (dataOsdGetPushCommon.getLongitude() == 0.0d) {
                d = Double.NaN;
            } else {
                d = dataOsdGetPushCommon.getLongitude();
            }
            a((Object) Double.valueOf(d), c(e.S));
            a((Object) Float.valueOf(((float) dataOsdGetPushCommon.getHeight()) * 0.1f), c(e.W));
            if (dataOsdGetPushCommon.getFlycState().equals(FLYC_STATE.GoHome) || dataOsdGetPushCommon.getFlycState().equals(FLYC_STATE.AutoLanding)) {
                z2 = true;
            } else {
                z2 = false;
            }
            a((Object) Boolean.valueOf(z2), c(e.aB));
            a((Object) Boolean.valueOf(dataOsdGetPushCommon.isMotorUp()), c(e.ak));
            a((Object) Float.valueOf(((float) dataOsdGetPushCommon.getXSpeed()) / 10.0f), c(e.T));
            a((Object) Float.valueOf(((float) dataOsdGetPushCommon.getYSpeed()) / 10.0f), c(e.U));
            a((Object) Float.valueOf(((float) dataOsdGetPushCommon.getZSpeed()) / 10.0f), c(e.V));
            a((Object) DJIAircraftRemainingBatteryState.find(dataOsdGetPushCommon.getVoltageWarning()), c(e.aa));
            a((Object) Boolean.valueOf(dataOsdGetPushCommon.groundOrSky() == 2), c(e.ab));
            if (dataOsdGetPushCommon.getFlycState() != FLYC_STATE.AutoLanding) {
                z = false;
            }
            a((Object) Boolean.valueOf(z), c(e.ac));
            a((Object) Double.valueOf(((double) dataOsdGetPushCommon.getPitch()) / 10.0d), c(e.X));
            a((Object) Double.valueOf(((double) dataOsdGetPushCommon.getRoll()) / 10.0d), c(e.Y));
            a((Object) Double.valueOf(((double) dataOsdGetPushCommon.getYaw()) / 10.0d), c(e.Z));
            a((Object) Double.valueOf(((double) dataOsdGetPushCommon.getYaw()) / 10.0d), c(e.K));
            a((Object) DJIFlightControllerFlightMode.find(dataOsdGetPushCommon.getFlycState().value()).toString(), c(e.ad));
            if (i.getInstance().c().equals(ProductType.litchiC)) {
                a((Object) Boolean.valueOf(false), c(e.ah));
            } else {
                a((Object) Boolean.valueOf(dataOsdGetPushCommon.isSwaveWork()), c(e.ah));
            }
            if (dataOsdGetPushCommon.isSwaveWork()) {
                a((Object) Float.valueOf(((float) dataOsdGetPushCommon.getSwaveHeight()) * 0.1f), c(e.ai));
            } else {
                a((Object) Float.valueOf(0.0f), c(e.ai));
            }
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        if (dataOsdGetPushHome.getRecDataLen() != 0) {
            Object find;
            a((Object) Boolean.valueOf(dataOsdGetPushHome.isCompassCeleing()), c(e.M));
            DJICompassCalibrationStatus dJICompassCalibrationStatus = DJICompassCalibrationStatus.Normal;
            if (dataOsdGetPushHome.isCompassCeleing()) {
                find = DJICompassCalibrationStatus.find(dataOsdGetPushHome.getCompassCeleStatus());
                this.L = DJICompassCalibrationStatus.find(dataOsdGetPushHome.getCompassCeleStatus());
                this.u = false;
            } else {
                find = DJICompassCalibrationStatus.Normal;
                if (!this.u && this.L.equals(DJICompassCalibrationStatus.Vertical)) {
                    find = DJICompassCalibrationStatus.Succeeded;
                }
                this.u = true;
                this.L = DJICompassCalibrationStatus.Normal;
            }
            a(find, c(e.N));
            a((Object) Float.valueOf((float) dataOsdGetPushHome.getGoHomeHeight()), c(e.x));
            if (LocationUtils.validateLatitude(dataOsdGetPushHome.getLatitude()) || LocationUtils.validateLongitude(dataOsdGetPushHome.getLongitude())) {
                a((Object) Double.valueOf(Double.NaN), c(e.al));
                a((Object) Double.valueOf(Double.NaN), c(e.am));
            } else {
                a((Object) Double.valueOf(dataOsdGetPushHome.getLatitude()), c(e.al));
                a((Object) Double.valueOf(dataOsdGetPushHome.getLongitude()), c(e.am));
            }
            a((Object) Boolean.valueOf(dataOsdGetPushHome.isHomeRecord()), c(e.aC));
            if (!dataOsdGetPushHome.isHomeRecord()) {
                a((Object) Float.valueOf(Float.NaN), c(e.aD));
            } else if (!this.t) {
                a((Object) Float.valueOf(((float) DataOsdGetPushCommon.getInstance().getHeight()) / 10.0f), c(e.aD));
                this.t = true;
            }
            a(dataOsdGetPushHome.isIOCEnabled() ? DJIFlightOrientationMode.find(dataOsdGetPushHome.getIOCMode().value()) : DJIFlightOrientationMode.DefaultAircraftHeading, c(e.aE));
            a((Object) Short.valueOf(dataOsdGetPushHome.getCourseLockAngle()), c(e.aF));
            a((Object) Boolean.valueOf(dataOsdGetPushHome.isMultipleModeOpen()), c(e.aG));
            a((Object) Boolean.valueOf(dataOsdGetPushHome.isReatchLimitDistance()), c(e.bt));
            a((Object) Boolean.valueOf(dataOsdGetPushHome.isReatchLimitHeight()), c(e.bs));
        }
        this.M = dataOsdGetPushHome.isFlycInSimulationMode();
        a((Object) Boolean.valueOf(this.M), c(e.bq));
        if (this.M) {
            if (this.N != b.c && this.N != b.f) {
                this.N = b.e;
            }
        } else if (this.N != b.c && this.N != b.d) {
            this.N = b.g;
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        boolean z = true;
        if (dataFlycGetPushSmartBattery.getRecDataLen() != 0) {
            a((Object) Boolean.valueOf(dataFlycGetPushSmartBattery.getGoHomeStatus().value() > (byte) 0), c(e.ax));
            a((Object) Integer.valueOf(dataFlycGetPushSmartBattery.getGoHomeBattery()), c(e.au));
            if (dataFlycGetPushSmartBattery.getGoHomeStatus().value() <= (byte) 0) {
                z = false;
            }
            a((Object) Boolean.valueOf(z), c(e.ax));
            a((Object) Float.valueOf(dataFlycGetPushSmartBattery.getSafeFlyRadius()), c(e.aw));
            a((Object) Integer.valueOf(dataFlycGetPushSmartBattery.getUsefulTime()), c(e.ar));
            if (DataOsdGetPushCommon.getInstance().getFlycState().equals(FLYC_STATE.AutoLanding)) {
                a((Object) Integer.valueOf(dataFlycGetPushSmartBattery.getLandTime()), c(e.as));
            } else {
                a((Object) Integer.valueOf(dataFlycGetPushSmartBattery.getGoHomeTime()), c(e.as));
            }
            a((Object) Integer.valueOf(dataFlycGetPushSmartBattery.getLandTime()), c(e.as));
            b((Object) Integer.valueOf(dataFlycGetPushSmartBattery.getLowWarning()), e.z);
            b((Object) Integer.valueOf(dataFlycGetPushSmartBattery.getSeriousLowWarning()), e.A);
            b((Object) Integer.valueOf(dataFlycGetPushSmartBattery.getGoHomeBattery()), e.cS);
            b((Object) Integer.valueOf(dataFlycGetPushSmartBattery.getLandBattery()), e.cT);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FirmwareVersion")
    public void k(b.e eVar) {
        String str = ".";
        DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.FLYC);
        dataCommonGetVersion.start(new 23(this, dataCommonGetVersion, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SerialNumber")
    public void l(b.e eVar) {
        if (eVar != null) {
            DataFlycActiveStatus dataFlycActiveStatus = new DataFlycActiveStatus();
            dataFlycActiveStatus.setType(dji.midware.data.model.b.a.b.b).start(new 27(this, dataFlycActiveStatus, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "InternalSerialNumber")
    public void m(b.e eVar) {
        if (eVar != null) {
            DataFlycActiveStatus dataFlycActiveStatus = new DataFlycActiveStatus();
            dataFlycActiveStatus.setVersion(DataFlycActiveStatus.getInstance().getVersion()).setType(dji.midware.data.model.b.a.b.b).start(new 28(this, dataFlycActiveStatus, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "HomeLocationUsingCurrentAircraftLocation")
    public void n(b.e eVar) {
        if (DataOsdGetPushCommon.getInstance().getGpsLevel() >= 4) {
            DataFlycSetHomePoint.getInstance().a(HOMETYPE.a).start(new 29(this, eVar));
        } else if (eVar != null) {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.FLIGHT_CONTROLLER_GPS_IS_NOT_HIGH_ENOUGH);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "HomeLocation")
    public void a(DJILocationCoordinate2D dJILocationCoordinate2D, b.e eVar) {
        double latitude = dJILocationCoordinate2D.getLatitude();
        double longitude = dJILocationCoordinate2D.getLongitude();
        if (latitude < -90.0d || latitude > 90.0d || longitude < -180.0d || longitude > 180.0d) {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.FLIGHT_CONTROLLER_INVALID_PARAMETER);
        } else if (a(dJILocationCoordinate2D)) {
            DataFlycSetHomePoint.getInstance().a(HOMETYPE.c).a(LocationUtils.DegreeToRadian(latitude), LocationUtils.DegreeToRadian(longitude)).a((byte) 100).start(new 30(this, eVar));
        } else if (eVar != null) {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.HOMEPOINT_TOO_FAR);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "HomeLocation")
    public void o(b.e eVar) {
        if (eVar != null) {
            DJILocationCoordinate2D dJILocationCoordinate2D = new DJILocationCoordinate2D(DataOsdGetPushHome.getInstance().getLatitude(), DataOsdGetPushHome.getInstance().getLongitude());
            if (dJILocationCoordinate2D != null) {
                CallbackUtils.onSuccess(eVar, dJILocationCoordinate2D);
            } else {
                CallbackUtils.onFailure(eVar, DJIFlightControllerError.FLIGHT_CONTROLLER_OBJECT_EMPTY_OR_NOT_AVAILABLE);
            }
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "GoHomeAltitude")
    public void a(float f, b.e eVar) {
        if (f < 20.0f) {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.GOHOME_ALTITUDE_TOO_LOW);
        } else if (f > DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition) {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.GOHOME_ALTITUDE_TOO_HIGH);
        } else {
            DataFlycGetParams.getInstance().setInfos(new String[]{"g_config.go_home.fixed_go_home_altitude_0"}).start(new 31(this, dji.midware.data.manager.P3.d.read("g_config.go_home.fixed_go_home_altitude_0"), f, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "GoHomeAltitude")
    public void p(b.e eVar) {
        this.F.a("g_config.go_home.fixed_go_home_altitude_0", new 32(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "FlightFailSafeOperation")
    public void a(DJIFlightFailsafeOperation dJIFlightFailsafeOperation, b.e eVar) {
        if (dJIFlightFailsafeOperation == DJIFlightFailsafeOperation.Unknown) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else {
            new DataFlycSetParams().a("g_config.fail_safe.protect_action_0", Integer.valueOf(dJIFlightFailsafeOperation.value())).start(new 2(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FlightFailSafeOperation")
    public void q(b.e eVar) {
        if (eVar != null) {
            DataFlycGetFsAction.getInstance().start(new 3(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "GoHomeBatteryThreshold")
    public void a(int i, b.e eVar) {
        if (i > 50 || i < 15) {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.COMMON_PARAM_ILLEGAL);
        } else {
            a(i, WarnningLevel.First, eVar);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "LandImmediatelyBatteryThreshold")
    public void b(int i, b.e eVar) {
        int a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(e.z));
        if (i < 10 || i > a) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else {
            a(i, WarnningLevel.Second, eVar);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "AircraftName")
    public void a(String str, b.e eVar) {
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "AircraftName")
    public void r(b.e eVar) {
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "LEDsEnabled")
    public void a(boolean z, b.e eVar) {
        int i = 1;
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(dji.midware.data.params.P3.a.g);
        Number[] numberArr = new Number[1];
        if (!z) {
            i = 0;
        }
        numberArr[0] = Integer.valueOf(i);
        dataFlycSetParams.a(numberArr);
        dataFlycSetParams.start(new 4(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "LEDsEnabled")
    public void s(b.e eVar) {
        new DataFlycGetParams().setInfos(new String[]{dji.midware.data.params.P3.a.g}).start(new 5(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "TakeOff")
    public void a(b.e eVar) {
        if (q() || eVar == null) {
            DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.AUTO_FLY).start(new 6(this, eVar));
        } else {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.UNABLE_TO_TAKE_OFF);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "CancelTakeOff")
    public void t(b.e eVar) {
        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.DropTakeOff).start(new 7(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "AutoLanding")
    public void b(b.e eVar) {
        a(FLYC_COMMAND.AUTO_LANDING, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "CancelAutoLanding")
    public void c(b.e eVar) {
        a(FLYC_COMMAND.DropLanding, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "TurnOnMotors")
    public void d(b.e eVar) {
        CallbackUtils.onFailure(eVar, DJIError.COMMAND_NOT_SUPPORTED_BY_FIRMWARE);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "TurnOffMotors")
    public void e(b.e eVar) {
        if (DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.AIRCRAFT_FLYING_ERROR);
        } else {
            a(FLYC_COMMAND.STOP_MOTOR, eVar);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "GoHome")
    public void f(b.e eVar) {
        a(FLYC_COMMAND.GOHOME, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "CancelGoHome")
    public void g(b.e eVar) {
        a(FLYC_COMMAND.DropGohome, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "LockCourseUsingCurrentDirection")
    public void u(b.e eVar) {
        a(FLYC_COMMAND.HOMEPOINT_LOC, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartIMUCalibration")
    public void h(b.e eVar) {
        ParamInfo read = dji.midware.data.manager.P3.d.read("imu_app_temp_cali.start_flag_0");
        if (!DataOsdGetPushCommon.getInstance().isMotorUp()) {
            new DataFlycSetParams().a(read.name, Integer.valueOf(1)).start(new 8(this, eVar));
        } else if (eVar != null) {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.IMU_CALIBRATION_ERROR_IN_THE_AIR_OR_MOTORS_ON);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "FlightOrientationMode")
    public void a(DJIFlightOrientationMode dJIFlightOrientationMode, b.e eVar) {
        if (dJIFlightOrientationMode == null && eVar != null) {
            CallbackUtils.onFailure(eVar, DJIFlightControllerError.MISSION_RESULT_FAILED);
        }
        if (dJIFlightOrientationMode == DJIFlightOrientationMode.DefaultAircraftHeading) {
            i(eVar);
        } else {
            w(new 9(this, dJIFlightOrientationMode, eVar));
        }
    }

    private void i(b.e eVar) {
        DataFlycStopIoc.getInstance().start(new 10(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "ExitNavigationMode")
    public void v(b.e eVar) {
        DataFlycNavigationSwitch.getInstance().setCommand(GS_COMMAND.CLOSE_GROUND_STATION).start(new 11(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "EnterNavigationMode")
    public void w(b.e eVar) {
        MultiModeEnabledUtil.setMultiModeEnabled(new 13(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartIMUCalibrationWithID")
    public void a(b.e eVar, int i) {
        CallbackUtils.onFailure(eVar, DJIError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "SendDataToOnboardSDKDevice")
    public void a(b.e eVar, byte[] bArr) {
        if (!dji.sdksharedlib.a.a.b(dji.sdksharedlib.a.a.e(e.i))) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_UNSUPPORTED);
        } else if (bArr == null || bArr.length == 0 || bArr.length > 100) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else {
            DataFlycSetSendOnBoard.getInstance().a(bArr).start(new 14(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "SendVirtualStickFlightControlData")
    public void a(b.e eVar, DJIVirtualStickFlightControlData dJIVirtualStickFlightControlData, DJIVirtualStickVerticalControlMode dJIVirtualStickVerticalControlMode, DJIVirtualStickRollPitchControlMode dJIVirtualStickRollPitchControlMode, DJIVirtualStickYawControlMode dJIVirtualStickYawControlMode, DJIVirtualStickFlightCoordinateSystem dJIVirtualStickFlightCoordinateSystem, boolean z) {
        DataFlycJoystick instance = DataFlycJoystick.getInstance();
        if (dJIVirtualStickVerticalControlMode.equals(DJIVirtualStickVerticalControlMode.Velocity) && (dJIVirtualStickFlightControlData.getVerticalThrottle() < DJIFlightControllerDataType.DJIVirtualStickVerticalControlMinVelocity || dJIVirtualStickFlightControlData.getVerticalThrottle() > DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity)) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else if (dJIVirtualStickVerticalControlMode.equals(DJIVirtualStickVerticalControlMode.Position) && (dJIVirtualStickFlightControlData.getVerticalThrottle() < 0.0f || dJIVirtualStickFlightControlData.getVerticalThrottle() > DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition)) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else if (dJIVirtualStickRollPitchControlMode.equals(DJIVirtualStickRollPitchControlMode.Angle) && (dJIVirtualStickFlightControlData.getPitch() < DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMinAngle || dJIVirtualStickFlightControlData.getPitch() > 30.0f || dJIVirtualStickFlightControlData.getRoll() < DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMinAngle || dJIVirtualStickFlightControlData.getRoll() > 30.0f)) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else if (dJIVirtualStickRollPitchControlMode.equals(DJIVirtualStickRollPitchControlMode.Velocity) && (dJIVirtualStickFlightControlData.getPitch() < DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMinVelocity || dJIVirtualStickFlightControlData.getPitch() > DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity || dJIVirtualStickFlightControlData.getRoll() < DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMinVelocity || dJIVirtualStickFlightControlData.getRoll() > DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity)) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else if (dJIVirtualStickYawControlMode.equals(DJIVirtualStickYawControlMode.Angle) && (dJIVirtualStickFlightControlData.getYaw() < DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle || dJIVirtualStickFlightControlData.getYaw() > 180.0f)) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else if (!dJIVirtualStickYawControlMode.equals(DJIVirtualStickYawControlMode.AngularVelocity) || (dJIVirtualStickFlightControlData.getYaw() >= DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngularVelocity && dJIVirtualStickFlightControlData.getYaw() <= DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)) {
            instance.setFlag(a(dJIVirtualStickVerticalControlMode, dJIVirtualStickRollPitchControlMode, dJIVirtualStickYawControlMode, dJIVirtualStickFlightCoordinateSystem, z));
            instance.setYaw(dJIVirtualStickFlightControlData.getYaw());
            instance.setPitch(dJIVirtualStickFlightControlData.getPitch());
            instance.setRoll(dJIVirtualStickFlightControlData.getRoll());
            instance.setThrottle(dJIVirtualStickFlightControlData.getVerticalThrottle()).start();
            CallbackUtils.onSuccess(eVar, null);
        } else {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "CompassStartCalibration")
    public void x(b.e eVar) {
        a(FLYC_COMMAND.Calibration, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "CompassStopCalibration")
    public void y(b.e eVar) {
        a(FLYC_COMMAND.DropCalibration, eVar);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MaxFlightHeight")
    public void b(float f, b.e eVar) {
        if (f < 20.0f || f > DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
            return;
        }
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a("g_config.flying_limit.max_height_0");
        dataFlycSetParams.a(Float.valueOf(f));
        dataFlycSetParams.start(new 15(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MaxFlightHeight")
    public void z(b.e eVar) {
        new DataFlycGetParams().setInfos(new String[]{"g_config.flying_limit.max_height_0"}).start(new 16(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MaxFlightRadius")
    public void a(double d, b.e eVar) {
        if (d < 15.0d || d > 500.0d) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
            return;
        }
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a("g_config.flying_limit.max_radius_0");
        dataFlycSetParams.a(Double.valueOf(d));
        dataFlycSetParams.start(new 17(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MaxFlightRadius")
    public void A(b.e eVar) {
        new DataFlycGetParams().setInfos(new String[]{"g_config.flying_limit.max_radius_0"}).start(new 18(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MaxFlightRadiusEnabled")
    public void b(boolean z, b.e eVar) {
        int i = 1;
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a("g_config.advanced_function.radius_limit_enabled_0");
        Number[] numberArr = new Number[1];
        if (!z) {
            i = 0;
        }
        numberArr[0] = Integer.valueOf(i);
        dataFlycSetParams.a(numberArr);
        dataFlycSetParams.start(new 19(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MaxFlightRadiusEnabled")
    public void B(b.e eVar) {
        new DataFlycGetParams().setInfos(new String[]{"g_config.advanced_function.radius_limit_enabled_0"}).start(new 20(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "IsVirtualStickControlModeAvailable")
    public boolean C(b.e eVar) {
        FLYC_STATE flycState = DataOsdGetPushCommon.getInstance().getFlycState();
        if (flycState == FLYC_STATE.GPS_HotPoint || flycState == FLYC_STATE.NaviGo || flycState == FLYC_STATE.NaviMissionFollow || System.currentTimeMillis() - this.J > 200) {
            return false;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (System.currentTimeMillis() - this.J < 200) {
            return true;
        }
        return false;
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "GeoFeatureInSimulatorEnabled")
    public void c(boolean z, b.e eVar) {
        int i = 1;
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a("g_config.airport_limit_cfg.cfg_sim_disable_limit_0");
        Number[] numberArr = new Number[1];
        if (z) {
            i = 0;
        }
        numberArr[0] = Integer.valueOf(i);
        dataFlycSetParams.a(numberArr);
        dataFlycSetParams.start(new 21(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "GeoFeatureInSimulatorEnabled")
    public void D(b.e eVar) {
        new DataFlycGetParams().setInfos(new String[]{"g_config.airport_limit_cfg.cfg_sim_disable_limit_0"}).start(new 22(this, eVar));
    }

    private boolean a(DJILocationCoordinate2D dJILocationCoordinate2D) {
        Location location = new Location("Next Home Point");
        location.setLatitude(dJILocationCoordinate2D.getLatitude());
        location.setLongitude(dJILocationCoordinate2D.getLongitude());
        double gps2m = LocationUtils.gps2m(DataOsdGetPushHome.getInstance().getLatitude(), DataOsdGetPushHome.getInstance().getLongitude(), location.getLatitude(), location.getLongitude());
        double gps2m2 = LocationUtils.gps2m(location.getLatitude(), location.getLongitude(), DataOsdGetPushCommon.getInstance().getLatitude(), DataOsdGetPushCommon.getInstance().getLongitude());
        if ((dJILocationCoordinate2D == null || gps2m >= 30.0d) && ((dJILocationCoordinate2D == null || gps2m2 >= 30.0d) && ((dJILocationCoordinate2D == null || !LocationUtils.checkLocationPermission() || LocationUtils.getLastBestLocation() == null || LocationUtils.gps2m(location.getLatitude(), location.getLongitude(), LocationUtils.getLastBestLocation().getLatitude(), LocationUtils.getLastBestLocation().getLongitude()) >= 30.0d) && (dJILocationCoordinate2D == null || ((!i.getInstance().c().equals(ProductType.N1) && !i.getInstance().c().equals(ProductType.BigBanana) && !i.getInstance().c().equals(ProductType.Orange)) || LocationUtils.gps2m(location.getLatitude(), location.getLongitude(), DataRcGetPushGpsInfo.getInstance().getLatitude(), DataRcGetPushGpsInfo.getInstance().getLongitude()) >= 30.0d))))) {
            return false;
        }
        return true;
    }

    private void a(int i, WarnningLevel warnningLevel, b.e eVar) {
        dji.midware.e.d 24 = new 24(this, eVar);
        DataFlycSetLVoltageWarnning instance = DataFlycSetLVoltageWarnning.getInstance();
        instance.a(warnningLevel);
        instance.a(i);
        if (warnningLevel == WarnningLevel.First) {
            instance.a(true);
        } else {
            instance.b(true);
        }
        instance.start(24);
    }

    private void a(WarnningLevel warnningLevel, b.e eVar) {
        dji.midware.e.d 25 = new 25(this, eVar);
        DataFlycGetVoltageWarnning instance = DataFlycGetVoltageWarnning.getInstance();
        instance.setWarnningLevel(warnningLevel);
        instance.start(25);
    }

    public boolean q() {
        if (DataOsdGetPushCommon.getInstance().isMotorUp() && DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
            return false;
        }
        return true;
    }

    protected void a(FLYC_COMMAND flyc_command, b.e eVar) {
        DataFlycFunctionControl.getInstance().setCommand(flyc_command).start(new 26(this, eVar));
    }

    private byte a(DJIVirtualStickVerticalControlMode dJIVirtualStickVerticalControlMode, DJIVirtualStickRollPitchControlMode dJIVirtualStickRollPitchControlMode, DJIVirtualStickYawControlMode dJIVirtualStickYawControlMode, DJIVirtualStickFlightCoordinateSystem dJIVirtualStickFlightCoordinateSystem, boolean z) {
        return (byte) (((byte) (z ? 1 : 0)) + (((byte) (dJIVirtualStickFlightCoordinateSystem.value() << 1)) + ((((byte) (dJIVirtualStickRollPitchControlMode.value() << 6)) + ((byte) (dJIVirtualStickVerticalControlMode.value() << 4))) + ((byte) (dJIVirtualStickYawControlMode.value() << 3)))));
    }

    static DJIFlightControllerError a(int i) {
        switch (i) {
            case 1:
                return DJIFlightControllerError.MISSION_RESULT_BEGAN;
            case 2:
                return DJIFlightControllerError.MISSION_RESULT_CANCELED;
            case 3:
                return DJIFlightControllerError.MISSION_RESULT_FAILED;
            case 4:
                return DJIFlightControllerError.MISSION_RESULT_TIMEOUT;
            case 5:
                return DJIFlightControllerError.MISSION_RESULT_MODE_ERROR;
            case 6:
                return DJIFlightControllerError.MISSION_RESULT_GPS_NOT_READY;
            case 7:
                return DJIFlightControllerError.MISSION_RESULT_MOTOR_NOT_START;
            case 8:
                return DJIFlightControllerError.MISSION_RESULT_TAKEOFF;
            case 9:
                return DJIFlightControllerError.MISSION_RESULT_IS_FLYING;
            case 10:
                return DJIFlightControllerError.MISSION_RESULT_NOT_AUTO_MODE;
            case 11:
                return DJIFlightControllerError.MISSION_RESULT_UPLOAD_WAYPOINT_NUM_MAX_LIMIT;
            case 12:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_UPLOADING;
            case 13:
                return DJIFlightControllerError.MISSION_RESULT_KEY_LEVEL_LOW;
            case 15:
                return DJIFlightControllerError.MISSION_RESULT_NAVIGATION_IS_NOT_OPEN;
            case 160:
                return DJIFlightControllerError.MISSION_RESULT_TOO_CLOSE_TO_HOMEPOINT;
            case 161:
                return DJIFlightControllerError.MISSION_RESULT_IOC_TYPE_UNKNOWN;
            case 162:
                return DJIFlightControllerError.MISSION_RESULT_HOMEPOINT_VALUE_INVALID;
            case 163:
                return DJIFlightControllerError.MISSION_RESULT_HOMEPOINT_LOCATION_INVALID;
            case 166:
                return DJIFlightControllerError.MISSION_RESULT_HOMEPOINT_DIRECTION_UNKNOWN;
            case 169:
                return DJIFlightControllerError.MISSION_RESULT_HOMEPOINT_PAUSED;
            case 170:
                return DJIFlightControllerError.MISSION_RESULT_HOMEPOINT_NOT_PAUSED;
            case 176:
                return DJIFlightControllerError.MISSION_RESULT_FOLLOWME_DISTANCE_TOO_LARGE;
            case 177:
                return DJIFlightControllerError.MISSION_RESULT_FOLLOWME_DISCONNECT_TIME_TOO_LONG;
            case 178:
                return DJIFlightControllerError.MISSION_RESULT_FOLLOWME_GIMBAL_PITCH_ERROR;
            case 192:
                return DJIFlightControllerError.MISSION_RESULT_ALTITUDE_TOO_HIGH;
            case 193:
                return DJIFlightControllerError.MISSION_RESULT_ALTITUDE_TOO_LOW;
            case 194:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_RADIUS_INVALID;
            case 195:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_SPEED_TOO_LARGE;
            case 196:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_ENTRYPOINT_INVALID;
            case 197:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_HEADING_MODE_INVALID;
            case 198:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_RESUME_FAILED;
            case 199:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_RADIUS_OVERLIMITED;
            case 200:
                return DJIFlightControllerError.MISSION_RESULT_UNSUPPORTED_NAVIGATION_FOR_THE_PRODUCT;
            case dji.pilot.usercenter.f.d.y /*201*/:
                return DJIFlightControllerError.MISSION_RESULT_DISTANCE_FROM_MISSION_TARGET_TOO_LONG;
            case FTPCodes.SUPERFLOUS_COMMAND /*202*/:
                return DJIFlightControllerError.MISSION_RESULT_IN_NOVICE_MODE;
            case 208:
                return DJIFlightControllerError.MISSION_RESULT_RC_MODE_ERROR;
            case 209:
                return DJIFlightControllerError.MISSION_RESULT_NAVIGATION_IS_NOT_OPEN;
            case 210:
                return DJIFlightControllerError.MISSION_RESULT_IOC_WORKING;
            case 211:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_NOT_INIT;
            case 212:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_NOT_EXIST;
            case 213:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_CONFLICT;
            case FTPCodes.HELP_MESSAGE /*214*/:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_ESTIMATE_TIME_TOO_LONG;
            case FTPCodes.NAME_SYSTEM_TIME /*215*/:
                return DJIFlightControllerError.MISSION_RESULT_HIGH_PRIORITY_MISSION_EXECUTING;
            case 216:
                return DJIFlightControllerError.MISSION_RESULT_GPS_SIGNAL_WEAK;
            case 217:
                return DJIFlightControllerError.MISSION_RESULT_LOW_BATTERY;
            case 218:
                return DJIFlightControllerError.MISSION_RESULT_AIRCRAFT_NOT_IN_THE_AIR;
            case 219:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_PARAM_INVALID;
            case FTPCodes.SERVICE_READY_FOR_NEW_USER /*220*/:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_CONDITION_NOT_SATISFIED;
            case 221:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_ACROSS_NOFLYZONE;
            case TrackerEvent.RadioMapManualCommonIndoor /*222*/:
                return DJIFlightControllerError.MISSION_RESULT_HOMEPOINT_NOT_RECORDED;
            case TrackerEvent.RadioMapManualPrivateIndoor /*223*/:
                return DJIFlightControllerError.MISSION_RESULT_AIRCRAFT_IN_NOFLYZONE;
            case dji.thirdparty.g.b.a.a.fw_ /*224*/:
                return DJIFlightControllerError.MISSION_RESULT_MISSION_INFO_INVALID;
            case FTPCodes.DATA_CONNECTION_OPEN /*225*/:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_INFO_INVALID;
            case FTPCodes.DATA_CONNECTION_CLOSING /*226*/:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_TRACE_TOO_LONG;
            case FTPCodes.ENTER_PASSIVE_MODE /*227*/:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_TOTAL_TRACE_TOO_LONG;
            case 228:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_INDEX_OVERRANGE;
            case 229:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_DISTANCE_TOO_CLOSE;
            case FTPCodes.USER_LOGGED_IN /*230*/:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_DISTANCE_TOO_LONG;
            case 231:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_DAMPING_CHECK_FAILED;
            case 232:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_ACTION_PARAM_INVALID;
            case 233:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_UPLOADING;
            case 234:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_MISSION_INFO_NOT_UPLOADED;
            case 235:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_UPLOAD_NOT_COMPLETE;
            case 236:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_REQUEST_IS_RUNNING;
            case 237:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_NOT_RUNNING;
            case 238:
                return DJIFlightControllerError.MISSION_RESULT_WAYPOINT_IDLE_VELOCITY_INVALID;
            case 240:
                return DJIFlightControllerError.MISSION_RESULT_AIRCRAFT_TAKINGOFF;
            case 241:
                return DJIFlightControllerError.MISSION_RESULT_AIRCRAFT_LANDING;
            case 242:
                return DJIFlightControllerError.MISSION_RESULT_AIRCRAFT_GOINGHOME;
            case 243:
                return DJIFlightControllerError.MISSION_RESULT_AIRCRAFT_STARTING_MOTOR;
            case 244:
                return DJIFlightControllerError.MISSION_RESULT_WRONG_CMD;
            case 255:
                return DJIFlightControllerError.MISSION_RESULT_UNKNOWN;
            default:
                return null;
        }
    }

    private int[] a(FLYC_STATE flyc_state, boolean z) {
        int[] iArr = new int[]{255, 255};
        DataRcGetPushParams instance = DataRcGetPushParams.getInstance();
        if (FLYC_STATE.Manula == flyc_state) {
            iArr[0] = 0;
        } else if (FLYC_STATE.Atti == flyc_state) {
            iArr[0] = 1;
        } else if (FLYC_STATE.Atti_CL == flyc_state) {
            iArr[0] = 1;
            iArr[1] = 0;
        } else if (FLYC_STATE.Atti_Hover == flyc_state) {
            iArr[0] = 1;
        } else if (FLYC_STATE.Atti_Limited == flyc_state) {
            iArr[0] = 1;
        } else if (FLYC_STATE.AttiLangding == flyc_state) {
            iArr[0] = 2;
        } else if (FLYC_STATE.AutoLanding == flyc_state) {
            iArr[0] = 2;
        } else if (FLYC_STATE.AssitedTakeoff == flyc_state) {
            iArr[0] = 3;
        } else if (FLYC_STATE.AutoTakeoff == flyc_state) {
            iArr[0] = 3;
        } else if (FLYC_STATE.GoHome == flyc_state) {
            iArr[0] = 4;
        } else if (FLYC_STATE.GPS_Atti == flyc_state) {
            iArr[0] = 10;
        } else if (FLYC_STATE.GPS_Atti_Limited == flyc_state) {
            iArr[0] = 10;
        } else if (FLYC_STATE.GPS_Blake == flyc_state) {
            iArr[0] = 10;
        } else if (FLYC_STATE.GPS_CL == flyc_state) {
            iArr[0] = 10;
            iArr[1] = 0;
        } else if (FLYC_STATE.GPS_HomeLock == flyc_state) {
            iArr[0] = 10;
            iArr[1] = 1;
        } else if (FLYC_STATE.GPS_HotPoint == flyc_state) {
            iArr[0] = 10;
            iArr[1] = 2;
        } else if (FLYC_STATE.Hover == flyc_state) {
            iArr[0] = 10;
        } else if (FLYC_STATE.Joystick == flyc_state) {
            iArr[0] = 5;
        } else if (FLYC_STATE.NaviGo == flyc_state) {
            iArr[0] = 6;
        } else if (FLYC_STATE.ClickGo == flyc_state) {
            iArr[0] = 7;
        } else if (FLYC_STATE.NaviSubMode_Tracking == flyc_state) {
            iArr[0] = 14;
        } else if (FLYC_STATE.NaviSubMode_Pointing == flyc_state) {
            iArr[0] = 15;
        } else if (FLYC_STATE.SPORT == flyc_state) {
            iArr[0] = 16;
        } else if (FLYC_STATE.NOVICE == flyc_state) {
            iArr[0] = 17;
        }
        if (iArr[0] == 10) {
            if (z) {
                iArr[0] = 9;
            }
        } else if (iArr[0] == 1) {
            int value = DataOsdGetPushCommon.getInstance().getModeChannel().value();
            if (!DataOsdGetPushHome.getInstance().isMultipleModeOpen() || value == 0 || value == 2) {
                iArr[0] = 8;
            }
        }
        if ((iArr[0] == 10 || iArr[0] == 8 || iArr[0] == 9) && instance.getMode() == 2) {
            iArr[0] = iArr[0] + 3;
        }
        return iArr;
    }

    private boolean c() {
        DroneType droneType = DataOsdGetPushCommon.getInstance().getDroneType();
        return droneType == DroneType.A2 || droneType == DroneType.WKM || droneType == DroneType.NAZA;
    }

    private int b(int i) {
        if (i == 0 || i >= 50) {
            return 0;
        }
        if (i <= 7) {
            return 1;
        }
        if (i > 10) {
            return 5;
        }
        return i - 6;
    }
}
