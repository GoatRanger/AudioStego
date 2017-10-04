package dji.sdksharedlib.b;

import dji.common.flightcontroller.DJICompassCalibrationStatus;
import dji.common.flightcontroller.DJIFlightControllerControlMode;
import dji.common.flightcontroller.DJIFlightControllerFlightMode;
import dji.common.flightcontroller.DJIFlightFailsafeOperation;
import dji.common.flightcontroller.DJIFlightOrientationMode;
import dji.common.flightcontroller.DJIGPSSignalStatus;
import dji.common.flightcontroller.DJIGoHomeStatus;
import dji.common.flightcontroller.DJIIMUCalibrationStatus;
import dji.common.flightcontroller.DJILandingGearMode;
import dji.common.flightcontroller.DJILandingGearStatus;
import dji.common.flightcontroller.DJILocationCoordinate2D;
import dji.common.flightcontroller.DJIRTKPositioningSolution;
import dji.common.flightcontroller.DJISimulatorInitializationData;
import dji.common.flightcontroller.DJISimulatorStateData;
import dji.common.flightcontroller.DJIVirtualStickFlightControlData;
import dji.common.flightcontroller.DJIVirtualStickFlightCoordinateSystem;
import dji.common.flightcontroller.DJIVirtualStickRollPitchControlMode;
import dji.common.flightcontroller.DJIVirtualStickVerticalControlMode;
import dji.common.flightcontroller.DJIVirtualStickYawControlMode;
import dji.common.flightcontroller.DJIVisionDetectionSector;
import dji.common.flightcontroller.DJIVisionSystemWarning;
import dji.midware.data.model.P3.DataFlycGetPushForbidStatus.DJIFlightLimitAreaState;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.IMUStatus;
import dji.sdksharedlib.b.b.c;
import dji.sdksharedlib.b.b.d;
import dji.sdksharedlib.b.b.f;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import dji.sdksharedlib.hardware.abstractions.d.a;
import dji.sdksharedlib.hardware.abstractions.d.b;
import dji.sdksharedlib.hardware.abstractions.d.h;

public class e extends d {
    @d(a = Integer.class, c = 6, f = {a.class})
    public static final String A = "LandImmediatelyBatteryThreshold";
    @d(a = String.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String B = "AircraftName";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String C = "LEDsEnabled";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class})
    public static final String D = "CollisionAvoidanceEnabled";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String E = "VisionPositioningEnabled";
    public static final String F = "RthCollisionAvoidanceEnabled";
    public static final String G = "PrecisionModeEnabled";
    @d(a = Float.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String H = "MaxFlightHeight";
    @d(a = Float.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String I = "MaxFlightRadius";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String J = "MaxFlightRadiusEnabled";
    @d(a = Float.class, c = 4)
    public static final String K = "CompassHeading";
    @d(a = Boolean.class, c = 4)
    public static final String L = "CompassHasError";
    @d(a = Boolean.class, c = 4)
    public static final String M = "CompassIsCalibrating";
    @d(a = DJICompassCalibrationStatus.class, c = 4)
    public static final String N = "CompassCalibrationStatus";
    @d(a = Integer.class, c = 4)
    public static final String O = "IMUStateCalibrationProgress";
    @d(a = Boolean.class, c = 4)
    public static final String P = "IsLandingGearMovable";
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String Q = "SatelliteCount";
    @d(a = Double.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String R = "AircraftLocationLatitude";
    @d(a = Double.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String S = "AircraftLocationLongitude";
    @d(a = Float.class, c = 4)
    public static final String T = "VelocityX";
    @d(a = Float.class, c = 4)
    public static final String U = "VelocityY";
    @d(a = Float.class, c = 4)
    public static final String V = "VelocityZ";
    @d(a = Float.class, c = 4)
    public static final String W = "Altitude";
    @d(a = Double.class, c = 4)
    public static final String X = "AttitudePitch";
    @d(a = Double.class, c = 4)
    public static final String Y = "AttitudeRoll";
    @d(a = Double.class, c = 4)
    public static final String Z = "AttitudeYaw";
    public static final String a = "FlightController";
    @d(a = DJIGoHomeStatus.class, c = 4)
    public static final String aA = "GoHomeStatus";
    @d(a = Boolean.class, c = 4)
    public static final String aB = "IsGoingHome";
    @d(a = Boolean.class, c = 4)
    public static final String aC = "IsHomePointSet";
    @d(a = Float.class, c = 4)
    public static final String aD = "HomePointAltitude";
    @d(a = DJIFlightOrientationMode.class, c = 4)
    public static final String aE = "IocMode";
    @f
    @d(a = Short.class, c = 4)
    @c
    public static final String aF = "CourseLockAngle";
    @d(a = Boolean.class, c = 4)
    public static final String aG = "MultiModeOpen";
    @d(a = Double.class, c = 4, e = {b.class})
    public static final String aH = "RTKAirAltitude";
    @d(a = Double.class, c = 4, e = {b.class})
    public static final String aI = "RTKAirLatitude";
    @d(a = Double.class, c = 4, e = {b.class})
    public static final String aJ = "RTKAirLongitude";
    @d(a = Double.class, c = 4, e = {b.class})
    public static final String aK = "RTKDirectAngle";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String aL = "RTKDirectEnabled";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String aM = "RTKEnabled";
    @d(a = Integer.class, c = 4, e = {b.class})
    public static final String aN = "RTKError";
    @d(a = Double.class, c = 4, e = {b.class})
    public static final String aO = "RTKGroundAltitude";
    @d(a = Integer.class, c = 4, e = {b.class})
    public static final String aP = "RTKGroundBeidoutCount";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String aQ = "RTKGroundBeidoutCountIsOn";
    @d(a = Integer.class, c = 4, e = {b.class})
    public static final String aR = "RTKGroundGPSCount";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String aS = "RTKGroundGPSCountIsOn";
    @d(a = Integer.class, c = 4, e = {b.class})
    public static final String aT = "RTKGroundGlonassCount";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String aU = "RTKGroundGlonassCountIsOn";
    @d(a = Double.class, c = 4, e = {b.class})
    public static final String aV = "RTKGroundLatitude";
    @d(a = Double.class, c = 4, e = {b.class})
    public static final String aW = "RTKGroundLongitude";
    @d(a = Integer.class, c = 4, e = {b.class})
    public static final String aX = "RTKMainBeidouCount";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String aY = "RTKMainBeidouCountIsOn";
    @d(a = Integer.class, c = 4, e = {b.class})
    public static final String aZ = "RTKMainGPSCount";
    @d(a = Integer.class, c = 4)
    public static final String aa = "RemainingBattery";
    @d(a = Boolean.class, c = 4)
    public static final String ab = "IsFlying";
    @d(a = Boolean.class, c = 4)
    public static final String ac = "IsAutoLanding";
    @d(a = String.class, c = 4)
    public static final String ad = "FlightModeKey";
    @d(a = DJIGPSSignalStatus.class, c = 4)
    public static final String ae = "GPSSignalStatus";
    @d(a = Boolean.class, c = 4)
    public static final String af = "IsFailSafe";
    @d(a = Boolean.class, c = 4)
    public static final String ag = "IsIMUPreheating";
    @d(a = Boolean.class, c = 4)
    public static final String ah = "IsUltrasonicBeingUsed";
    @d(a = Float.class, c = 4)
    public static final String ai = "UltrasonicHeight";
    @d(a = Boolean.class, c = 4)
    public static final String aj = "IsVisionSensorBeingUsed";
    @d(a = Boolean.class, c = 4)
    public static final String ak = "AreMotorsOn";
    @d(a = Double.class, c = 4)
    public static final String al = "HomeLocationLatitude";
    @d(a = Double.class, c = 4)
    public static final String am = "HomeLocationLongitude";
    @d(a = Boolean.class, c = 4)
    public static final String an = "IsNearHeightLimit";
    @d(a = Boolean.class, c = 4)
    public static final String ao = "IsNearDistanceLimit";
    @d(a = DJIFlightLimitAreaState.class, c = 4)
    public static final String ap = "FlightLimitState";
    @d(a = Integer.class, c = 4)
    public static final String aq = "FlightLimitSpaceNum";
    @d(a = Long.class, c = 4)
    public static final String ar = "RemainingFlightTime";
    @d(a = Integer.class, c = 4)
    public static final String as = "TimeNeededToGoHome";
    @d(a = Integer.class, c = 4)
    public static final String at = "TimeNeededToLandFromCurrentHeight";
    @d(a = Integer.class, c = 4)
    public static final String au = "BatteryPercentageNeededToGoHome";
    @d(a = Integer.class, c = 4)
    public static final String av = "BatteryPercentageNeededToLandFromCurrentHeight";
    @d(a = Double.class, c = 4)
    public static final String aw = "MaxRadiusAircraftCanFlyAndGoHome";
    @d(a = Boolean.class, c = 4)
    public static final String ax = "AircraftShouldGoHome";
    @d(a = Boolean.class, c = 4)
    public static final String ay = "UltrasonicError";
    @d(a = Integer.class, c = 4)
    public static final String az = "FlyTime";
    public static final String b = "IsLanding";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String bA = "CancelAutoLanding";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String bB = "TurnOnMotors";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String bC = "TurnOffMotors";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String bD = "GoHome";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String bE = "CancelGoHome";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String bF = "LockCourseUsingCurrentDirection";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bG = "StartIMUCalibration";
    @d(a = Integer.class, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String bH = "StartIMUCalibrationWithID";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String bI = "SendDataToOnboardSDKDevice";
    @d(b = {DJIVirtualStickFlightControlData.class, DJIVirtualStickVerticalControlMode.class, DJIVirtualStickRollPitchControlMode.class, DJIVirtualStickYawControlMode.class, DJIVirtualStickFlightCoordinateSystem.class, Boolean.class}, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bJ = "SendVirtualStickFlightControlData";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bK = "StartIOCMode";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bL = "StopIOCMode";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.d.f.class})
    public static final String bM = "TurnOnAutoLandingGear";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.d.f.class})
    public static final String bN = "TurnOffAutoLandingGear";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.d.f.class})
    public static final String bO = "ExitTransportMode";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.d.f.class})
    public static final String bP = "EnterTransportMode";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.d.f.class})
    public static final String bQ = "RetractLandingGear";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.d.f.class})
    public static final String bR = "DeployLandingGear";
    @d(a = DJISimulatorInitializationData.class, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bS = "StartSimulator";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bT = "StopSimulator";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bU = "EnterNavigationMode";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bV = "ExitNavigationMode";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bW = "CompassStartCalibration";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bX = "CompassStopCalibration";
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    @c
    public static final String bY = "InternalSerialNumber";
    @f
    @c
    public static final String bZ = "IsVisionSensorEnable";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String ba = "RTKMainGPSCountIsOn";
    @d(a = Integer.class, c = 4, e = {b.class})
    public static final String bb = "RTKMainGlonassCount";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String bc = "RTKMainGlonassCountIsOn";
    @d(a = Integer.class, c = 4, e = {b.class})
    public static final String bd = "RTKSatelliteBeidouCount";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String be = "RTKSatelliteBeidouCountIsOn";
    @d(a = Integer.class, c = 4, e = {b.class})
    public static final String bf = "RTKSatelliteGPSCount";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String bg = "RTKSatelliteGPSCountIsOn";
    @d(a = Integer.class, c = 4, e = {b.class})
    public static final String bh = "RTKSatelliteGlonassCount";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String bi = "RTKSatelliteGlonassCountIsOn";
    @d(a = DJIRTKPositioningSolution.class, c = 4, e = {b.class})
    public static final String bj = "RTKStatus";
    @d(a = DJIFlightControllerFlightMode.class, c = 4)
    public static final String bk = "FlightMode";
    @d(a = DJIFlightControllerControlMode.class, c = 1, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.d.c.class})
    public static final String bl = "ControlMode";
    @d(a = Boolean.class, c = 4, e = {h.class})
    public static final String bm = "IsBraking";
    @d(a = Boolean.class, c = 4, e = {h.class})
    public static final String bn = "IsSensorWorking";
    @d(a = DJIVisionSystemWarning.class, c = 4, e = {h.class})
    public static final String bo = "VisionSystemWarning";
    @d(a = DJIVisionDetectionSector[].class, c = 4, e = {h.class})
    public static final String bp = "DetectionSectors";
    @d(a = DJISimulatorInitializationData.class, c = 4)
    public static final String bq = "IsSimulatorStarted";
    @d(a = DJISimulatorStateData.class, c = 4)
    public static final String br = "SimulatorState";
    @d(a = Boolean.class, c = 4)
    public static final String bs = "HasReachedMaxFlightHeight";
    @d(a = Boolean.class, c = 4)
    public static final String bt = "HasReachedMaxFlightRadius";
    @d(a = DJILandingGearStatus.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.d.f.class})
    public static final String bu = "LandingGearStatus";
    @d(a = DJILandingGearMode.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.d.f.class})
    public static final String bv = "LandingGearMode";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String bw = "HomeLocationUsingCurrentAircraftLocation";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String bx = "TakeOff";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String by = "CancelTakeOff";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String bz = "AutoLanding";
    public static final String c = "Imu";
    @f
    @c
    public static final String cA = "FlightControllerConfigSportTiltExpMiddlePoint";
    @f
    @c
    public static final String cB = "FlightControllerConfigSportTorsionExpMiddlePoint";
    @f
    @c
    public static final String cC = "FlightControllerConfigSportLiftExpMiddlePoint";
    @f
    @c
    public static final String cD = "FlightControllerConfigVerticalAtti";
    @f
    @c
    public static final String cE = "FlightControllerConfigBasicPitch";
    @f
    @c
    public static final String cF = "FlightControllerConfigBasicYaw";
    @f
    @c
    public static final String cG = "FlightControllerConfigBasicRoll";
    @f
    @c
    public static final String cH = "FlightControllerConfigBasicTail";
    @f
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.d.e.class})
    @c
    public static final String cI = "Tripod";
    @f
    @c
    public static final String cJ = "FlightControllerIsInAdvancedGoHome";
    @f
    @c
    public static final String cK = "FlightControllerConfigPreciseLandingSwitch";
    @f
    @c
    public static final String cL = "FlightControllerConfigAdvancedGoHomeSwitch";
    @f
    @c
    public static final String cM = "IsInPreciseLanding";
    @f
    @c
    public static final String cN = "AdvancedGoHomeState";
    @f
    @c
    public static final String cO = "PreciseLandingState";
    @f
    @c
    public static final String cP = "FlightControllerConfigOnGroundHideGear";
    @f
    @c
    public static final String cQ = "FlightControllerConfigHideGear";
    @f
    @c
    public static final String cR = "FlightControllerConfigLandingCheckSwitch";
    @d(a = Integer.class, c = 4, f = {a.class})
    public static final String cS = "CurrentGoHomeBattery";
    @d(a = Integer.class, c = 4, f = {a.class})
    public static final String cT = "CurrentLandImmediatelyBattery";
    @f
    @d(a = Boolean.class, c = 4)
    @c
    public static final String cU = "IsGpsBeingUsed";
    @f
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @c
    public static final String cV = "DeviceInstallErrorYaw";
    @f
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @c
    public static final String cW = "DeviceInstallErrorMassCenter";
    @f
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @c
    public static final String cX = "DeviceInstallErrorVibration";
    @f
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @c
    public static final String cY = "DeviceInstallErrorHoverThrustLow";
    @f
    @c
    public static final String ca = "IsVisionSensorWork";
    @f
    @c
    public static final String cb = "IntelligentFlightAssistantVisionAssistantStatus";
    @f
    @c
    public static final String cc = "FlightControllerConfigAttitudeRange";
    @f
    @c
    public static final String cd = "FlightControllerConfigTorsionRate";
    @f
    @c
    public static final String ce = "FlightControllerConfigRcTiltSensitive";
    @f
    @c
    public static final String cf = "FlightControllerConfigTiltSensitive";
    @f
    @c
    public static final String cg = "FlightControllerConfigBrakeSensitive";
    @f
    @c
    public static final String ch = "FlightControllerConfigTorsionGyroRange";
    @f
    @c
    public static final String cm = "InternalFlightControllerVersion";
    @f
    @c
    public static final String cn = "FlightControllerConfigThrottleExperienceMidPoint";
    @f
    @c
    public static final String co = "FlightControllerConfigTiltExperienceMidPoint";
    @f
    @c
    public static final String cp = "FlightControllerConfigYawExperienceMidPoint";
    @f
    @c
    public static final String cq = "FlightControllerConfigSportThrottleExperienceMidPoint";
    @f
    @c
    public static final String cr = "FlightControllerConfigSportTiltExperienceMidPoint";
    @f
    @c
    public static final String cs = "FlightControllerConfigSportYawExperienceMidPoint";
    @f
    @c
    public static final String ct = "FlightControllerConfigImuTempRealCtlOutPer";
    @f
    @c
    public static final String cu = "FlightControllerConfigGentleTiltExpMiddlePoint";
    @f
    @c
    public static final String cv = "FlightControllerConfigGentleTorsionExpMiddlePoint";
    @f
    @c
    public static final String cw = "FlightControllerConfigGentleLiftExpMiddlePoint";
    @f
    @c
    public static final String cx = "FlightControllerConfigNormalTiltExpMiddlePoint";
    @f
    @c
    public static final String cy = "FlightControllerConfigNormalTorsionExpMiddlePoint";
    @f
    @c
    public static final String cz = "FlightControllerConfigNormalLiftExpMiddlePoint";
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String d = "SerialNumber";
    @d(a = Integer.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String e = "ImuCount";
    @d(a = Boolean.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String f = "RtkSupported";
    @d(c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String g = "IsVirtualStickControlModeAvailable";
    @d(a = Boolean.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String h = "IsLandingGearMovable";
    @d(a = Boolean.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String i = "IsOnBoardSDKAvailable";
    @d(a = Float.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String j = "IMUStateAccelerationX";
    @d(a = Float.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String k = "IMUStateAccelerationY";
    @d(a = Float.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String l = "IMUStateAccelerationZ";
    @d(a = Float.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String m = "IMUStateGyroscopeX";
    @d(a = Float.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String n = "IMUStateGyroscopeY";
    @d(a = Float.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String o = "IMUStateGyroscopeZ";
    @d(a = DJIIMUCalibrationStatus.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String p = "IMUStateGyroscopeState";
    @d(a = DJIIMUCalibrationStatus.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String q = "IMUStateAcceleratorState";
    @d(a = IMUStatus.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String r = "IMUStateCalibrationState";
    @d(a = Boolean.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String s = "IntelligentFlightAssistantSupported";
    @d(a = Boolean.class, c = 2, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String t = "AdvancedFlightModeEnabled";
    @d(a = DJIFlightOrientationMode.class, c = 2, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String u = "FlightOrientationMode";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String v = "GeoFeatureInSimulatorEnabled";
    @d(a = DJILocationCoordinate2D.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})
    public static final String w = "HomeLocation";
    @dji.sdksharedlib.b.b.a(a = {@d(a = Float.class, c = 4, f = {a.class}), @d(a = Float.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {a.class})})
    public static final String x = "GoHomeAltitude";
    @d(a = DJIFlightFailsafeOperation.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String y = "FlightFailSafeOperation";
    @d(a = Integer.class, c = 6, f = {a.class})
    public static final String z = "GoHomeBatteryThreshold";

    public e(String str) {
        super(str);
    }

    protected String a() {
        return a;
    }
}
