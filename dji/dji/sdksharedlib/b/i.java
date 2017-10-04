package dji.sdksharedlib.b;

import dji.common.remotecontroller.DJIRCBatteryInfo;
import dji.common.remotecontroller.DJIRCControlMode;
import dji.common.remotecontroller.DJIRCGPSData;
import dji.common.remotecontroller.DJIRCGimbalControlDirection;
import dji.common.remotecontroller.DJIRCGimbalControlSpeed;
import dji.common.remotecontroller.DJIRCHardwareState.DJIRCHardwareFlightModeSwitchState;
import dji.common.remotecontroller.DJIRCHardwareState.DJIRCHardwareTransformationSwitchState;
import dji.common.remotecontroller.DJIRCInfo;
import dji.common.remotecontroller.DJIRCRemoteFocusState.DJIRCRemoteFocusControlDirection;
import dji.common.remotecontroller.DJIRCRequestGimbalControlResult;
import dji.common.remotecontroller.DJIRCToAircraftPairingState;
import dji.common.remotecontroller.JoinMasterParams;
import dji.common.remotecontroller.JoinedMasterNameAndPasswordResult;
import dji.common.remotecontroller.RCCustomButtonTagParam;
import dji.common.remotecontroller.RemoteControllerModeParam;
import dji.sdksharedlib.b.b.d;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import dji.sdksharedlib.hardware.abstractions.h.b;

public class i extends d {
    @d(a = DJIRCRequestGimbalControlResult.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String A = "SlaveGimbalControlRight";
    @d(a = DJIRCControlMode.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String B = "RCControlMode";
    @d(a = DJIRCGimbalControlDirection.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String C = "RCControlGimbalDirection";
    @d(a = RCCustomButtonTagParam.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String D = "RCCustomButtonTag";
    @d(a = DJIRCToAircraftPairingState.class, c = 1)
    public static final String E = "RCToAircraftPairingState";
    @d(a = DJIRCGimbalControlSpeed.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String F = "SlaveJoystickControlGimbalSpeed";
    @d(a = String.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String G = "RCName";
    @d(a = String.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String H = "RCPassword";
    @d(a = DJIRCControlMode.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String I = "SlaveControlMode";
    @d(a = DJIRCInfo[].class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String J = "AvailableMasters";
    @d(a = Boolean.class, c = 1)
    public static final String K = "MasterRCSearchState";
    @d(a = DJIRCInfo[].class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String L = "SlaveList";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String M = "EnterRCToAircraftPairingMode";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String N = "ExitRCToAircraftPairingMode";
    @d(a = RemoteControllerModeParam.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String O = "RemoteControllerMode";
    @d(a = JoinMasterParams.class, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String P = "JoinMaster";
    @d(a = JoinedMasterNameAndPasswordResult.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String Q = "JoinedMasterNameAndPassword";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String R = "StartMasterRCSearch";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String S = "StopMasterRCSearch";
    @d(a = Integer.class, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String T = "RemoveSlave";
    @d(a = Integer.class, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String U = "RemoveMaster";
    @d(a = Short.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String V = "RCWheelGimbalSpeed";
    @d(a = Boolean.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String W = "IsMasterSlaveModeSupported";
    @d(a = Boolean.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String X = "IsRCRemoteFocusCheckingSupported";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String Y = "RemoteControllerCalibration";
    public static final String Z = "RemoteControllerCalibrationState";
    public static final String a = "RemoteController";
    @d(a = Integer.class, c = 1, e = {b.class})
    public static final String aa = "RemoteControllerCalibrationNumberOfFragment";
    @d(a = Integer.class, c = 1, e = {b.class})
    public static final String ab = "RemoteControllerAAxisStatus";
    @d(a = Integer.class, c = 1, e = {b.class})
    public static final String ac = "RemoteControllerBAxisStatus";
    @d(a = Integer.class, c = 1, e = {b.class})
    public static final String ad = "RemoteControllerCAxisStatus";
    @d(a = Integer.class, c = 1, e = {b.class})
    public static final String ae = "RemoteControllerDAxisStatus";
    @d(a = Integer.class, c = 1, e = {b.class})
    public static final String af = "RemoteControllerEAxisStatus";
    @d(a = Integer.class, c = 1, e = {b.class})
    public static final String ag = "RemoteControllerFAxisStatus";
    @d(a = Integer.class, c = 1, e = {b.class})
    public static final String ah = "RemoteControllerGAxisStatus";
    @d(a = Integer.class, c = 1, e = {b.class})
    public static final String ai = "RemoteControllerHAxisStatus";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String aj = "FiveDimensButtonPushUp";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String ak = "FiveDimensButtonPushDown";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String al = "FiveDimensButtonPushPressed";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String am = "FiveDimensButtonPushLeft";
    @d(a = Boolean.class, c = 4, e = {b.class})
    public static final String an = "FiveDimensButtonPushRight";
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String b = "SerialNumber";
    @d(a = Integer.class, c = 4)
    public static final String c = "LeftHorizontalValue";
    @d(a = Integer.class, c = 4)
    public static final String d = "LeftVerticalValue";
    @d(a = Integer.class, c = 4)
    public static final String e = "RightHorizontalValue";
    @d(a = Integer.class, c = 4)
    public static final String f = "RightVerticalValue";
    @d(a = Integer.class, c = 4)
    public static final String g = "LeftWheelValue";
    @d(a = Boolean.class, c = 4)
    public static final String h = "RightWheelButtonDown";
    public static final String i = "RightWheelChanged";
    public static final String j = "RightWheelDirection";
    @d(a = Integer.class, c = 4)
    public static final String k = "RightWheelValue";
    @d(a = DJIRCHardwareTransformationSwitchState.class, c = 4)
    public static final String l = "TransformationSwitchState";
    @d(a = DJIRCHardwareFlightModeSwitchState.class, c = 4)
    public static final String m = "RCHardwareFlightModeSwitchState";
    @d(a = Boolean.class, c = 4)
    public static final String n = "GoHomeButtonDown";
    @d(a = Boolean.class, c = 4)
    public static final String o = "RecordButtonDown";
    @d(a = Boolean.class, c = 4)
    public static final String p = "ShutterButtonDown";
    @d(a = Boolean.class, c = 4)
    public static final String q = "CustomButton1Down";
    @d(a = Boolean.class, c = 4)
    public static final String r = "CustomButton2Down";
    public static final String s = "PauseButtonIsPresent";
    @d(c = 4)
    public static final String t = "PauseButtonDown";
    @d(a = Boolean.class, c = 4)
    public static final String u = "PlaybackButtonDown";
    @d(a = DJIRCGPSData.class, c = 4)
    public static final String v = "GPSData";
    @d(a = DJIRCBatteryInfo.class, c = 4)
    public static final String w = "RemainingEnergyInfo";
    @d(a = Boolean.class, c = 4)
    public static final String x = "FocusStateIsFocusControlWorks";
    public static final String y = "FocusStateControlType";
    @d(a = DJIRCRemoteFocusControlDirection.class, c = 4)
    public static final String z = "FocusStateDirection";

    public i(String str) {
        super(str);
    }

    protected String a() {
        return a;
    }
}
