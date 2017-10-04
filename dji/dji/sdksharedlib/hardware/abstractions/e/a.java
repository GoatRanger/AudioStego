package dji.sdksharedlib.hardware.abstractions.e;

import android.util.Log;
import dji.common.error.DJIError;
import dji.common.error.DJIGimbalError;
import dji.common.gimbal.DJIGimbalAdvancedSettingsProfile;
import dji.common.gimbal.DJIGimbalAngleRotation;
import dji.common.gimbal.DJIGimbalAttitude;
import dji.common.gimbal.DJIGimbalBalanceTestResult;
import dji.common.gimbal.DJIGimbalCapabilityKey;
import dji.common.gimbal.DJIGimbalControllerMode;
import dji.common.gimbal.DJIGimbalMotorControlPreset;
import dji.common.gimbal.DJIGimbalRotateAngleMode;
import dji.common.gimbal.DJIGimbalRotateDirection;
import dji.common.gimbal.DJIGimbalSpeedRotation;
import dji.common.gimbal.DJIGimbalState;
import dji.common.gimbal.DJIGimbalWorkMode;
import dji.common.util.DJIParamCapability;
import dji.common.util.DJIParamMinMaxCapability;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataGimbalAbsAngleControl;
import dji.midware.data.model.P3.DataGimbalAutoCalibration;
import dji.midware.data.model.P3.DataGimbalControl.MODE;
import dji.midware.data.model.P3.DataGimbalGetPushAutoCalibrationStatus;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalGetPushUserParams;
import dji.midware.data.model.P3.DataGimbalGetUserParams;
import dji.midware.data.model.P3.DataGimbalResetUserParams;
import dji.midware.data.model.P3.DataGimbalRollFinetune;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.data.model.P3.DataGimbalSpeedControl;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.b.f;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public abstract class a extends b {
    private static final String a = "DJIGimbalAbstraction";
    protected int b = 0;
    protected DJIGimbalState c;
    protected boolean d = false;
    protected boolean e = true;
    protected boolean f = false;
    protected DJIGimbalBalanceTestResult g = DJIGimbalBalanceTestResult.Unknown;
    protected DJIGimbalBalanceTestResult h = DJIGimbalBalanceTestResult.Unknown;
    protected DataGimbalGetPushUserParams i = null;
    protected DJIGimbalAttitude j;
    public Map<DJIGimbalCapabilityKey, DJIParamCapability> k;
    private int l = Integer.MIN_VALUE;
    private DJIError m;
    private CountDownLatch n;
    private double o = 1.0d;

    public void a(String str, int i, c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        this.b = i;
        this.i = DataGimbalGetPushUserParams.getInstance();
        r();
        dji.thirdparty.a.c.a().a((Object) this);
        b();
        c();
    }

    private void r() {
        this.j = new DJIGimbalAttitude(0.0f, 0.0f, 0.0f);
        this.c = new DJIGimbalState(this.j, 0, DJIGimbalWorkMode.YawFollowMode, false, false, false, false, false, false, DJIGimbalBalanceTestResult.Unknown, DJIGimbalBalanceTestResult.Unknown);
        this.d = false;
    }

    public void e() {
        dji.thirdparty.a.c.a().d((Object) this);
        super.e();
    }

    protected void a() {
        a(dji.sdksharedlib.b.f.class, getClass());
    }

    protected void b() {
        this.k = new HashMap();
        a(DJIGimbalCapabilityKey.AdjustPitch);
        a(DJIGimbalCapabilityKey.AdjustYaw);
        a(DJIGimbalCapabilityKey.AdjustRoll);
        a(DJIGimbalCapabilityKey.PitchRangeExtension);
        a(DJIGimbalCapabilityKey.ControllerSpeedPitch);
        a(DJIGimbalCapabilityKey.ControllerSpeedYaw);
        a(DJIGimbalCapabilityKey.ControllerSmoothingPitch);
        a(DJIGimbalCapabilityKey.ControllerSmoothingYaw);
        a(DJIGimbalCapabilityKey.ControllerDeadbandPitch);
        a(DJIGimbalCapabilityKey.ControllerDeadbandYaw);
        a(DJIGimbalCapabilityKey.SmoothTrackEnabledPitch);
        a(DJIGimbalCapabilityKey.SmoothTrackEnabledYaw);
        a(DJIGimbalCapabilityKey.SmoothTrackAccelerationPitch);
        a(DJIGimbalCapabilityKey.SmoothTrackAccelerationYaw);
        a(DJIGimbalCapabilityKey.SmoothTrackSpeedPitch);
        a(DJIGimbalCapabilityKey.SmoothTrackSpeedYaw);
        a(DJIGimbalCapabilityKey.SmoothTrackDeadbandPitch);
        a(DJIGimbalCapabilityKey.SmoothTrackDeadbandYaw);
        a(DJIGimbalCapabilityKey.EndpointPitchUp);
        a(DJIGimbalCapabilityKey.EndpointPitchDown);
        a(DJIGimbalCapabilityKey.EndpointYawLeft);
        a(DJIGimbalCapabilityKey.EndpointYawRight);
        a(DJIGimbalCapabilityKey.MotorControlStiffnessPitch);
        a(DJIGimbalCapabilityKey.MotorControlStiffnessYaw);
        a(DJIGimbalCapabilityKey.MotorControlStiffnessRoll);
        a(DJIGimbalCapabilityKey.MotorControlStrengthPitch);
        a(DJIGimbalCapabilityKey.MotorControlStrengthYaw);
        a(DJIGimbalCapabilityKey.MotorControlStrengthRoll);
        a(DJIGimbalCapabilityKey.MotorControlGyroFilteringPitch);
        a(DJIGimbalCapabilityKey.MotorControlGyroFilteringYaw);
        a(DJIGimbalCapabilityKey.MotorControlGyroFilteringRoll);
        a(DJIGimbalCapabilityKey.MotorControlPrecontrolPitch);
        a(DJIGimbalCapabilityKey.MotorControlPrecontrolYaw);
        a(DJIGimbalCapabilityKey.MotorControlPrecontrolRoll);
        a(DJIGimbalCapabilityKey.AdvancedSettingsProfile);
    }

    protected void a(DJIGimbalCapabilityKey dJIGimbalCapabilityKey) {
        if (this.k == null) {
            b();
        }
        if (DJIParamMinMaxCapability.class.equals(dJIGimbalCapabilityKey.capabilityClass())) {
            this.k.put(dJIGimbalCapabilityKey, new DJIParamMinMaxCapability(false, Integer.valueOf(0), Integer.valueOf(0)));
        } else {
            this.k.put(dJIGimbalCapabilityKey, new DJIParamCapability(false));
        }
    }

    protected void a(DJIGimbalCapabilityKey dJIGimbalCapabilityKey, boolean z) {
        if (this.k == null) {
            b();
        }
        if (DJIParamMinMaxCapability.class.equals(dJIGimbalCapabilityKey.capabilityClass())) {
            this.k.put(dJIGimbalCapabilityKey, new DJIParamMinMaxCapability(z, Integer.valueOf(0), Integer.valueOf(0)));
        } else {
            this.k.put(dJIGimbalCapabilityKey, new DJIParamCapability(z));
        }
    }

    protected void a(DJIGimbalCapabilityKey dJIGimbalCapabilityKey, Number number, Number number2) {
        if (this.k == null) {
            b();
        }
        if (DJIParamMinMaxCapability.class.equals(dJIGimbalCapabilityKey.capabilityClass())) {
            this.k.put(dJIGimbalCapabilityKey, new DJIParamMinMaxCapability(true, number, number2));
        } else {
            this.k.put(dJIGimbalCapabilityKey, new DJIParamCapability(true));
        }
    }

    public void c() {
        d();
    }

    protected void d() {
        a((Object) this.k, dji.sdksharedlib.b.f.b);
    }

    protected DJIParamMinMaxCapability a(DJIParamCapability dJIParamCapability) {
        if (dJIParamCapability == null || !(dJIParamCapability instanceof DJIParamMinMaxCapability)) {
            return null;
        }
        return (DJIParamMinMaxCapability) dJIParamCapability;
    }

    protected Number b(DJIGimbalCapabilityKey dJIGimbalCapabilityKey) {
        if (this.k == null || a((DJIParamCapability) this.k.get(dJIGimbalCapabilityKey)) == null) {
            return Integer.valueOf(Integer.MAX_VALUE);
        }
        return a((DJIParamCapability) this.k.get(dJIGimbalCapabilityKey)).getMin();
    }

    protected Number c(DJIGimbalCapabilityKey dJIGimbalCapabilityKey) {
        if (this.k == null || a((DJIParamCapability) this.k.get(dJIGimbalCapabilityKey)) == null) {
            return Integer.valueOf(Integer.MIN_VALUE);
        }
        return a((DJIParamCapability) this.k.get(dJIGimbalCapabilityKey)).getMax();
    }

    protected boolean a(Number number, Number number2, Number number3) {
        return number.doubleValue() >= number2.doubleValue() && number.doubleValue() <= number3.doubleValue();
    }

    protected boolean a(Number number, Number number2, Number number3, e eVar) {
        if (number == null || number2 == null || number3 == null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
            return false;
        } else if (a(number, number2, number3)) {
            return true;
        } else {
            if (eVar == null) {
                return false;
            }
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
            return false;
        }
    }

    protected void a(int i, a aVar, e eVar) {
        if (aVar == null) {
            eVar.a(DJIGimbalError.COMMON_PARAM_ILLEGAL);
            return;
        }
        switch (2.a[aVar.ordinal()]) {
            case 2:
                if (!a(Integer.valueOf(i), b(DJIGimbalCapabilityKey.SmoothTrackSpeedPitch), c(DJIGimbalCapabilityKey.SmoothTrackSpeedPitch), eVar)) {
                    return;
                }
                break;
            case 3:
                if (!a(Integer.valueOf(i), b(DJIGimbalCapabilityKey.SmoothTrackSpeedYaw), c(DJIGimbalCapabilityKey.SmoothTrackSpeedYaw), eVar)) {
                    return;
                }
                break;
            case 4:
                if (!a(Integer.valueOf(i), Integer.valueOf(1), Integer.valueOf(100), eVar)) {
                    return;
                }
                break;
            case 5:
                if (!a(Integer.valueOf(i), b(DJIGimbalCapabilityKey.SmoothTrackDeadbandPitch), c(DJIGimbalCapabilityKey.SmoothTrackDeadbandPitch), eVar)) {
                    return;
                }
                break;
            case 6:
                if (!a(Integer.valueOf(i), b(DJIGimbalCapabilityKey.SmoothTrackDeadbandYaw), c(DJIGimbalCapabilityKey.SmoothTrackDeadbandYaw), eVar)) {
                    return;
                }
                break;
            case 7:
                if (!a(Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(30), eVar)) {
                    return;
                }
                break;
            case 8:
                if (!a(Integer.valueOf(i), b(DJIGimbalCapabilityKey.SmoothTrackAccelerationPitch), c(DJIGimbalCapabilityKey.SmoothTrackAccelerationPitch), eVar)) {
                    return;
                }
                break;
            case 9:
                if (!a(Integer.valueOf(i), b(DJIGimbalCapabilityKey.SmoothTrackAccelerationYaw), c(DJIGimbalCapabilityKey.SmoothTrackAccelerationYaw), eVar)) {
                    return;
                }
                break;
            case 10:
                if (!a(Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(30), eVar)) {
                    return;
                }
                break;
            case 11:
                if (!a(Integer.valueOf(i), b(DJIGimbalCapabilityKey.ControllerSpeedPitch), c(DJIGimbalCapabilityKey.ControllerSpeedPitch), eVar)) {
                    return;
                }
                break;
            case 12:
                if (!a(Integer.valueOf(i), b(DJIGimbalCapabilityKey.ControllerSpeedYaw), c(DJIGimbalCapabilityKey.ControllerSpeedYaw), eVar)) {
                    return;
                }
                break;
            case 13:
                if (!a(Integer.valueOf(i), b(DJIGimbalCapabilityKey.ControllerSmoothingPitch), c(DJIGimbalCapabilityKey.ControllerSmoothingPitch), eVar)) {
                    return;
                }
                break;
            case 14:
                if (!a(Integer.valueOf(i), b(DJIGimbalCapabilityKey.ControllerSmoothingYaw), c(DJIGimbalCapabilityKey.ControllerSmoothingYaw), eVar)) {
                    return;
                }
                break;
        }
        DataGimbalSetUserParams.getInstance().a(aVar.a(), Integer.valueOf(i)).start(new 1(this, eVar, i));
        new Thread(new 3(this, aVar, i, eVar)).start();
    }

    protected void a(a aVar, e eVar) {
        if (aVar != null) {
            DataGimbalGetUserParams.getInstance().setInfos(new String[]{aVar.a()}).start(new 4(this, eVar, aVar));
        }
    }

    protected void a(boolean z) {
        this.d = z;
    }

    protected void b(boolean z) {
        this.e = z;
    }

    protected void c(boolean z) {
        this.f = z;
    }

    protected void a(DJIGimbalBalanceTestResult dJIGimbalBalanceTestResult) {
        this.g = dJIGimbalBalanceTestResult;
    }

    protected void b(DJIGimbalBalanceTestResult dJIGimbalBalanceTestResult) {
        this.h = dJIGimbalBalanceTestResult;
    }

    public DJIGimbalAttitude p() {
        return this.j;
    }

    void a(DJIGimbalAttitude dJIGimbalAttitude) {
        this.j = dJIGimbalAttitude;
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "Capabilities")
    public void a(e eVar) {
        eVar.a(this.k);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CompletionTimeForControlAngleAction")
    public void b(e eVar) {
        eVar.a(Double.valueOf(this.o));
    }

    public double q() {
        return this.o;
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "CompletionTimeForControlAngleAction")
    public void a(double d, e eVar) {
        double d2 = 25.5d;
        double d3 = 0.1d;
        if (d <= 25.5d) {
            d2 = d;
        }
        if (d2 >= 0.1d) {
            d3 = d2;
        }
        this.o = d3;
        eVar.a(Double.valueOf(this.o));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FirmwareVersion")
    public void c(e eVar) {
        String str = ".";
        DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.GIMBAL);
        dataCommonGetVersion.start(new 5(this, dataCommonGetVersion, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "WorkModeSetting")
    public void a(DJIGimbalWorkMode dJIGimbalWorkMode, e eVar) {
        if ((dJIGimbalWorkMode == null || dJIGimbalWorkMode.equals(DJIGimbalWorkMode.Unknown)) && eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
            return;
        }
        DataSpecialControl.getInstance().setGimbalMode(MODE.find(dJIGimbalWorkMode.value())).start(20);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "RotateByAngle")
    public void a(e eVar, DJIGimbalRotateAngleMode dJIGimbalRotateAngleMode, DJIGimbalAngleRotation dJIGimbalAngleRotation, DJIGimbalAngleRotation dJIGimbalAngleRotation2, DJIGimbalAngleRotation dJIGimbalAngleRotation3) {
        int i = -1;
        int i2 = 1;
        if (!ServiceManager.getInstance().isConnected()) {
            eVar.a(DJIError.COMMON_DISCONNECTED);
        } else if ((dJIGimbalAngleRotation != null && dJIGimbalAngleRotation.angle != 0.0f && !((DJIParamCapability) this.k.get(DJIGimbalCapabilityKey.AdjustPitch)).isSuppported()) || ((dJIGimbalAngleRotation2 != null && dJIGimbalAngleRotation2.angle != 0.0f && !((DJIParamCapability) this.k.get(DJIGimbalCapabilityKey.AdjustRoll)).isSuppported()) || (dJIGimbalAngleRotation3 != null && dJIGimbalAngleRotation3.angle != 0.0f && !((DJIParamCapability) this.k.get(DJIGimbalCapabilityKey.AdjustYaw)).isSuppported()))) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        } else if (dJIGimbalRotateAngleMode == null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        } else {
            int i3;
            float f = this.c.getAttitudeInDegrees().yaw;
            f = this.c.getAttitudeInDegrees().pitch;
            f = this.c.getAttitudeInDegrees().roll;
            DataGimbalAbsAngleControl controlMode = DataGimbalAbsAngleControl.getInstance().setControlMode(true);
            controlMode.setPitch((short) 0);
            controlMode.setRoll((short) 0);
            controlMode.setYaw((short) 0);
            if (dJIGimbalAngleRotation == null) {
                dJIGimbalAngleRotation = new DJIGimbalAngleRotation(false, 0.0f, DJIGimbalRotateDirection.Clockwise);
            }
            if (dJIGimbalAngleRotation2 == null) {
                dJIGimbalAngleRotation2 = new DJIGimbalAngleRotation(false, 0.0f, DJIGimbalRotateDirection.Clockwise);
            }
            if (dJIGimbalAngleRotation3 == null) {
                dJIGimbalAngleRotation3 = new DJIGimbalAngleRotation(false, 0.0f, DJIGimbalRotateDirection.Clockwise);
            }
            if (!dJIGimbalAngleRotation.enable) {
                controlMode.setPitchInvalid(true);
            } else if (dJIGimbalRotateAngleMode == DJIGimbalRotateAngleMode.AbsoluteAngle) {
                if (dJIGimbalAngleRotation.direction == DJIGimbalRotateDirection.Clockwise) {
                    i3 = 1;
                } else {
                    i3 = -1;
                }
                f = ((float) i3) * dJIGimbalAngleRotation.angle;
                if (f >= ((float) b(DJIGimbalCapabilityKey.AdjustPitch).intValue()) && f <= ((float) c(DJIGimbalCapabilityKey.AdjustPitch).intValue())) {
                    controlMode.setPitch((short) ((int) (f * 10.0f)));
                    controlMode.setPitchInvalid(false);
                    controlMode.setControlMode(true);
                } else if (eVar != null) {
                    eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
                    return;
                } else {
                    return;
                }
            } else if (dJIGimbalRotateAngleMode == DJIGimbalRotateAngleMode.RelativeAngle) {
                if (dJIGimbalAngleRotation.direction == DJIGimbalRotateDirection.Clockwise) {
                    i3 = 1;
                } else {
                    i3 = -1;
                }
                controlMode.setPitch((short) ((int) ((((float) i3) * dJIGimbalAngleRotation.angle) * 10.0f)));
                controlMode.setPitchInvalid(false);
                controlMode.setControlMode(false);
            }
            if (!dJIGimbalAngleRotation2.enable) {
                controlMode.setRollInvalid(true);
            } else if (dJIGimbalRotateAngleMode == DJIGimbalRotateAngleMode.AbsoluteAngle) {
                if (dJIGimbalAngleRotation2.direction == DJIGimbalRotateDirection.Clockwise) {
                    i3 = 1;
                } else {
                    i3 = -1;
                }
                f = ((float) i3) * dJIGimbalAngleRotation2.angle;
                if (f >= ((float) b(DJIGimbalCapabilityKey.AdjustRoll).intValue()) && f <= ((float) c(DJIGimbalCapabilityKey.AdjustRoll).intValue())) {
                    controlMode.setRoll((short) ((int) (f * 10.0f)));
                    controlMode.setRollInvalid(false);
                    controlMode.setControlMode(true);
                } else if (eVar != null) {
                    eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
                    return;
                } else {
                    return;
                }
            } else if (dJIGimbalRotateAngleMode == DJIGimbalRotateAngleMode.RelativeAngle) {
                if (dJIGimbalAngleRotation2.direction == DJIGimbalRotateDirection.Clockwise) {
                    i3 = 1;
                } else {
                    i3 = -1;
                }
                controlMode.setRoll((short) ((int) ((((float) i3) * dJIGimbalAngleRotation2.angle) * 10.0f)));
                controlMode.setRollInvalid(false);
                controlMode.setControlMode(false);
            }
            if (!dJIGimbalAngleRotation3.enable) {
                controlMode.setYawInvalid(true);
            } else if (dJIGimbalRotateAngleMode == DJIGimbalRotateAngleMode.AbsoluteAngle) {
                if (dJIGimbalAngleRotation3.direction == DJIGimbalRotateDirection.Clockwise) {
                    i = 1;
                }
                f = dJIGimbalAngleRotation3.angle * ((float) i);
                if (f >= ((float) b(DJIGimbalCapabilityKey.AdjustYaw).intValue()) && f <= ((float) c(DJIGimbalCapabilityKey.AdjustYaw).intValue())) {
                    controlMode.setYaw((short) ((int) (f * 10.0f)));
                    controlMode.setYawInvalid(false);
                    controlMode.setControlMode(true);
                } else if (eVar != null) {
                    eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
                    return;
                } else {
                    return;
                }
            } else if (dJIGimbalRotateAngleMode == DJIGimbalRotateAngleMode.RelativeAngle) {
                if (dJIGimbalAngleRotation3.direction != DJIGimbalRotateDirection.Clockwise) {
                    i2 = -1;
                }
                controlMode.setYaw((short) ((int) ((((float) i2) * dJIGimbalAngleRotation3.angle) * 10.0f)));
                controlMode.setYawInvalid(false);
                controlMode.setControlMode(false);
            }
            controlMode.setOvertime((int) (q() * 10.0d));
            controlMode.start();
            eVar.a(null);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "RotateBySpeed")
    public void a(e eVar, DJIGimbalSpeedRotation dJIGimbalSpeedRotation, DJIGimbalSpeedRotation dJIGimbalSpeedRotation2, DJIGimbalSpeedRotation dJIGimbalSpeedRotation3) {
        int i;
        int i2 = -1;
        boolean z = true;
        if (dJIGimbalSpeedRotation == null) {
            dJIGimbalSpeedRotation = new DJIGimbalSpeedRotation(0.0f, DJIGimbalRotateDirection.Clockwise);
        }
        if (dJIGimbalSpeedRotation2 == null) {
            dJIGimbalSpeedRotation2 = new DJIGimbalSpeedRotation(0.0f, DJIGimbalRotateDirection.Clockwise);
        }
        if (dJIGimbalSpeedRotation3 == null) {
            dJIGimbalSpeedRotation3 = new DJIGimbalSpeedRotation(0.0f, DJIGimbalRotateDirection.Clockwise);
        }
        DataGimbalSpeedControl.getInstance().setPitch((int) (((float) (dJIGimbalSpeedRotation.direction == DJIGimbalRotateDirection.Clockwise ? 1 : -1)) * (dJIGimbalSpeedRotation.angleVelocity * 10.0f)));
        DataGimbalSpeedControl instance = DataGimbalSpeedControl.getInstance();
        float f = dJIGimbalSpeedRotation2.angleVelocity * 10.0f;
        if (dJIGimbalSpeedRotation2.direction == DJIGimbalRotateDirection.Clockwise) {
            i = 1;
        } else {
            i = -1;
        }
        instance.setRoll((int) (((float) i) * f));
        DataGimbalSpeedControl instance2 = DataGimbalSpeedControl.getInstance();
        float f2 = dJIGimbalSpeedRotation3.angleVelocity * 10.0f;
        if (dJIGimbalSpeedRotation3.direction == DJIGimbalRotateDirection.Clockwise) {
            i2 = 1;
        }
        instance2.setYaw((int) (((float) i2) * f2));
        instance2 = DataGimbalSpeedControl.getInstance();
        if (dJIGimbalSpeedRotation.angleVelocity == 0.0f && dJIGimbalSpeedRotation2.angleVelocity == 0.0f && dJIGimbalSpeedRotation3.angleVelocity == 0.0f) {
            z = false;
        }
        instance2.setPermission(z);
        DataGimbalSpeedControl.getInstance().start();
        eVar.a(null);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "ResetGimbal")
    public void d(e eVar) {
        DataSpecialControl.getInstance().resetGimbal().start(20);
        eVar.a(null);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartGimbalAutoCalibration")
    public void e(e eVar) {
        DataGimbalAutoCalibration.getInstance().start(new 6(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "FineTuneGimbalRollInDegrees")
    public void a(e eVar, float f) {
        if ((f > 2.0f || f < -2.0f) && eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
        int i = ((int) f) * 10;
        if (i.getInstance().c().equals(ProductType.PM820)) {
            Log.i(a, "fineTuneGimbalRollInDegrees start time:" + System.currentTimeMillis());
            DataGimbalRollFinetune.getInstance().setFineTuneValue((byte) i).start(new 7(this, eVar));
        } else {
            Log.i(a, "fineTuneGimbalRollInDegrees start time:" + System.currentTimeMillis());
            DataGimbalRollFinetune.getInstance().setFineTuneValue((byte) i).start(new 7(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "PitchRangeExtensionEnabled")
    public void a(boolean z, e eVar) {
        int i;
        DataGimbalSetUserParams instance = DataGimbalSetUserParams.getInstance();
        String str = "pitch_exp";
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        instance.a(str, Integer.valueOf(i)).start(new 8(this));
        new Thread(new 9(this, z, eVar)).start();
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "PitchRangeExtensionEnabled")
    public void f(e eVar) {
        boolean z = true;
        if (dji.midware.data.manager.P3.e.read("pitch_exp").value.intValue() != 1) {
            z = false;
        }
        eVar.a(Boolean.valueOf(z));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "LoadFactorySettings")
    public void g(e eVar) {
        DataGimbalResetUserParams.getInstance().start(new 10(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SmoothTrackEnabledPitch")
    public void b(boolean z, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SmoothTrackEnabledYaw")
    public void c(boolean z, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SmoothTrackEnabledPitch")
    public void h(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SmoothTrackEnabledYaw")
    public void i(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "AdvancedSettingsProfile")
    public void a(DJIGimbalAdvancedSettingsProfile dJIGimbalAdvancedSettingsProfile, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "AdvancedSettingsProfile")
    public void j(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SmoothTrackSpeedPitch")
    public void a(int i, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SmoothTrackSpeedYaw")
    public void b(int i, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SmoothTrackSpeedPitch")
    public void k(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SmoothTrackSpeedYaw")
    public void l(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SmoothTrackDeadbandPitch")
    public void c(int i, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SmoothTrackDeadbandYaw")
    public void d(int i, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SmoothTrackDeadbandPitch")
    public void m(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SmoothTrackDeadbandYaw")
    public void n(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SmoothTrackAccelerationPitch")
    public void e(int i, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SmoothTrackAccelerationYaw")
    public void f(int i, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SmoothTrackAccelerationPitch")
    public void o(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SmoothTrackAccelerationYaw")
    public void p(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ControllerSmoothingPitch")
    public void g(int i, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ControllerSmoothingYaw")
    public void h(int i, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerSmoothingPitch")
    public void q(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerSmoothingYaw")
    public void r(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ControllerSpeedPitch")
    public void i(int i, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ControllerSpeedYaw")
    public void j(int i, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerSpeedPitch")
    public void s(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerSpeedYaw")
    public void t(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "MotorControl")
    public void a(e eVar, DJIGimbalMotorControlPreset dJIGimbalMotorControlPreset) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "CameraUprightEnabled")
    public void d(boolean z, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CameraUprightEnabled")
    public void u(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MotorEnabled")
    public void e(boolean z, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "MotorEnabled")
    public void v(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartGimbalBalanceTest")
    public void w(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "ToggleSelfie")
    public void x(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ControllerMode")
    public void a(DJIGimbalControllerMode dJIGimbalControllerMode, e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControllerMode")
    public void y(e eVar) {
        eVar.a(DJIGimbalError.COMMON_UNSUPPORTED);
    }

    public void onEventBackgroundThread(DataGimbalGetPushUserParams dataGimbalGetPushUserParams) {
        this.i = dataGimbalGetPushUserParams;
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (dataGimbalGetPushParams != null) {
            this.j.pitch = (float) (((double) dataGimbalGetPushParams.getPitch()) * 0.1d);
            this.j.roll = (float) (((double) dataGimbalGetPushParams.getRoll()) * 0.1d);
            this.j.yaw = (float) (((double) dataGimbalGetPushParams.getYaw()) * 0.1d);
            a((Object) this.j, c(dji.sdksharedlib.b.f.c));
            this.c.setAttitude(this.j);
            byte rollAdjust = dataGimbalGetPushParams.getRollAdjust();
            a((Object) Integer.valueOf(rollAdjust), c(dji.sdksharedlib.b.f.g));
            this.c.setRollFineTune(rollAdjust);
            MODE mode = dataGimbalGetPushParams.getMode();
            if (mode == MODE.YawNoFollow) {
                this.c.setWorkMode(DJIGimbalWorkMode.FreeMode);
            } else if (mode == MODE.FPV) {
                this.c.setWorkMode(DJIGimbalWorkMode.FpvMode);
            } else if (mode == MODE.YawFollow) {
                this.c.setWorkMode(DJIGimbalWorkMode.YawFollowMode);
            } else if (mode == MODE.OTHER) {
                this.c.setWorkMode(DJIGimbalWorkMode.Unknown);
            }
            a((Object) this.c.getWorkMode(), c(dji.sdksharedlib.b.f.i));
            if (dji.midware.c.a.getInstance().g() != dji.midware.c.a.b.b) {
                this.c.setCalibrating(dataGimbalGetPushParams.isAutoCalibration());
                if (i.getInstance().c().equals(ProductType.Longan)) {
                    this.c.setCalibrating(DataGimbalGetPushAutoCalibrationStatus.getInstance().getStatus() == 1);
                } else {
                    this.c.setCalibrating(dataGimbalGetPushParams.isAutoCalibration());
                }
            } else {
                this.c.setCalibrating(this.d);
            }
            a((Object) Boolean.valueOf(this.c.isCalibrating()), c(dji.sdksharedlib.b.f.k));
            this.c.setPitchReachMax(dataGimbalGetPushParams.isPitchInLimit());
            a((Object) Boolean.valueOf(this.c.isPitchAtStop()), c(dji.sdksharedlib.b.f.m));
            this.c.setRollReachMax(dataGimbalGetPushParams.isRollInLimit());
            a((Object) Boolean.valueOf(this.c.isRollAtStop()), c(dji.sdksharedlib.b.f.n));
            this.c.setYawReachMax(dataGimbalGetPushParams.isYawInLimit());
            a((Object) Boolean.valueOf(this.c.isYawAtStop()), c(dji.sdksharedlib.b.f.o));
            if (dji.midware.c.a.getInstance().g() != dji.midware.c.a.b.b) {
                if (this.c.isCalibrating()) {
                    this.d = this.c.isCalibrating();
                }
                if (!this.d) {
                    this.c.setIsCalibrationSuccess(false);
                } else if (!this.c.isCalibrating()) {
                    this.d = false;
                    this.c.setIsCalibrationSuccess(true);
                }
            } else {
                this.c.setIsCalibrationSuccess(this.e);
            }
            a((Object) Boolean.valueOf(this.c.isCalibrationSuccess()), c(dji.sdksharedlib.b.f.l));
            a((Object) Integer.valueOf(dataGimbalGetPushParams.getYawAngle() / 10), c(dji.sdksharedlib.b.f.d));
            a((Object) Boolean.valueOf(dataGimbalGetPushParams.isStuck()), c(dji.sdksharedlib.b.f.e));
            a((Object) Integer.valueOf(dataGimbalGetPushParams.getSubMode()), c(dji.sdksharedlib.b.f.f));
        }
    }
}
