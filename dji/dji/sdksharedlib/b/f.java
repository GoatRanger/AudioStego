package dji.sdksharedlib.b;

import dji.common.gimbal.DJIGimbalAdvancedSettingsProfile;
import dji.common.gimbal.DJIGimbalAngleRotation;
import dji.common.gimbal.DJIGimbalAttitude;
import dji.common.gimbal.DJIGimbalBalanceTestResult;
import dji.common.gimbal.DJIGimbalControllerMode;
import dji.common.gimbal.DJIGimbalMotorControlPreset;
import dji.common.gimbal.DJIGimbalRotateAngleMode;
import dji.common.gimbal.DJIGimbalSpeedRotation;
import dji.common.gimbal.DJIGimbalWorkMode;
import dji.sdksharedlib.b.b.d;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import dji.sdksharedlib.hardware.abstractions.e.e;
import dji.sdksharedlib.hardware.abstractions.e.g;
import dji.sdksharedlib.hardware.abstractions.e.h;
import dji.sdksharedlib.hardware.abstractions.e.i;
import java.util.Map;

public class f extends d {
    @d(a = Integer.class, c = 7)
    public static final String A = "SmoothTrackAccelerationYaw";
    @d(a = Integer.class, c = 7)
    public static final String B = "SmoothTrackAccelerationPitch";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String C = "MotorControlStiffnessYaw";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String D = "MotorControlStiffnessPitch";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String E = "MotorControlStiffnessRoll";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String F = "MotorControlStrengthYaw";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String G = "MotorControlStrengthPitch";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String H = "MotorControlStrengthRoll";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String I = "MotorControlGyroFilteringYaw";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String J = "MotorControlGyroFilteringPitch";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String K = "MotorControlGyroFilteringRoll";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String L = "MotorControlPrecontrolYaw";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String M = "MotorControlPrecontrolPitch";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String N = "MotorControlPrecontrolRoll";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String O = "MotorEnabled";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String P = "CameraUprightEnabled";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.e.f.class, g.class, i.class})
    public static final String Q = "ControllerDeadbandYaw";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.e.f.class, g.class, i.class})
    public static final String R = "ControllerDeadbandPitch";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.e.f.class, g.class, i.class})
    public static final String S = "ControllerSpeedYaw";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.e.f.class, g.class, i.class})
    public static final String T = "ControllerSpeedPitch";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.e.f.class, g.class, i.class})
    public static final String U = "ControllerSmoothingYaw";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.e.f.class, g.class, i.class})
    public static final String V = "ControllerSmoothingPitch";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {i.class})
    public static final String W = "EndpointPitchUp";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {i.class})
    public static final String X = "EndpointPitchDown";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {i.class})
    public static final String Y = "EndpointYawLeft";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {i.class})
    public static final String Z = "EndpointYawRight";
    public static final String a = "Gimbal";
    @d(a = Double.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String aa = "CompletionTimeForControlAngleAction";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String ab = "PitchRangeExtensionEnabled";
    @d(a = DJIGimbalControllerMode.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.e.f.class, g.class, i.class})
    public static final String ac = "ControllerMode";
    @d(a = DJIGimbalMotorControlPreset.class, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String ad = "MotorControl";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.e.f.class, g.class, i.class})
    public static final String ae = "ToggleSelfie";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String af = "StartGimbalBalanceTest";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String ag = "LoadFactorySettings";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String ah = "StartGimbalAutoCalibration";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String ai = "ResetGimbal";
    @d(b = {DJIGimbalRotateAngleMode.class, DJIGimbalAngleRotation.class, DJIGimbalAngleRotation.class, DJIGimbalAngleRotation.class}, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String aj = "RotateByAngle";
    @d(b = {DJIGimbalSpeedRotation.class, DJIGimbalSpeedRotation.class, DJIGimbalSpeedRotation.class}, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.e.f.class, g.class, i.class, e.class, dji.sdksharedlib.hardware.abstractions.e.d.class})
    public static final String ak = "RotateBySpeed";
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String al = "FirmwareVersion";
    @dji.sdksharedlib.b.b.f
    @d(b = {Boolean.class}, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.e.d.class, e.class})
    public static final String am = "InvertControlEnabledYaw";
    @dji.sdksharedlib.b.b.f
    @d(b = {Boolean.class}, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.e.d.class, e.class})
    public static final String an = "InvertControlEnabledPitch";
    public static final String ao = "isMobileDeviceMounted";
    @dji.sdksharedlib.b.b.f
    public static final String ap = "GimbalLoadingBalanceStatus";
    @dji.sdksharedlib.b.b.f
    public static final String aq = "GimbalMotorStatus";
    @d(a = Map.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String b = "Capabilities";
    @d(a = DJIGimbalAttitude.class, c = 4)
    public static final String c = "AttitudeInDegrees";
    @d(a = Integer.class, c = 4)
    public static final String d = "YawAngelWithAircraftInDegree";
    @d(a = Boolean.class, c = 4)
    public static final String e = "IsStuck";
    @d(a = Integer.class, c = 4)
    public static final String f = "FpvSubMode";
    @d(a = Float.class, c = 4)
    public static final String g = "RollFineTuneInDegrees";
    @d(a = Float.class, c = 8)
    public static final String h = "FineTuneGimbalRollInDegrees";
    @d(a = DJIGimbalWorkMode.class, c = 6)
    public static final String i = "WorkModeSetting";
    @d(a = Boolean.class, c = 4)
    public static final String j = "IsAttitudeReset";
    @d(a = Boolean.class, c = 4)
    public static final String k = "IsCalibrating";
    @d(a = Boolean.class, c = 4)
    public static final String l = "IsCalibrationSuccessful";
    @d(a = Boolean.class, c = 4)
    public static final String m = "IsPitchAtStop";
    @d(a = Boolean.class, c = 4)
    public static final String n = "IsRollAtStop";
    @d(a = Boolean.class, c = 4)
    public static final String o = "IsYawAtStop";
    @d(a = Boolean.class, c = 4, e = {h.class})
    public static final String p = "IsTestingBalance";
    @d(a = DJIGimbalBalanceTestResult.class, c = 4, e = {h.class})
    public static final String q = "PitchTestResult";
    @d(a = DJIGimbalBalanceTestResult.class, c = 4, e = {h.class})
    public static final String r = "RollTestResult";
    @d(a = Integer.class, c = 4)
    public static final String s = "BatteryRemainingInPercent";
    @d(a = DJIGimbalAdvancedSettingsProfile.class, c = 7)
    public static final String t = "AdvancedSettingsProfile";
    @d(a = Boolean.class, c = 7)
    public static final String u = "SmoothTrackEnabledYaw";
    @d(a = Boolean.class, c = 7)
    public static final String v = "SmoothTrackEnabledPitch";
    @d(a = Integer.class, c = 7)
    public static final String w = "SmoothTrackSpeedYaw";
    @d(a = Integer.class, c = 7)
    public static final String x = "SmoothTrackSpeedPitch";
    @d(a = Integer.class, c = 7)
    public static final String y = "SmoothTrackDeadbandYaw";
    @d(a = Integer.class, c = 7)
    public static final String z = "SmoothTrackDeadbandPitch";

    public f(String str) {
        super(str);
    }

    protected String a() {
        return a;
    }
}
