package dji.sdksharedlib.b;

import android.graphics.RectF;
import dji.common.camera.CameraLensFocusAssistant;
import dji.common.camera.CameraLensFocusTargetPoint;
import dji.common.camera.CameraPhotoTimeLapseParam;
import dji.common.camera.CameraRotationAngleType;
import dji.common.camera.CameraSSDCapacity;
import dji.common.camera.CameraSSDOperationState;
import dji.common.camera.CameraSSDRawVideoResolutionAndFrameRate;
import dji.common.camera.CameraSpotMeteringArea;
import dji.common.camera.CameraTapZoomTargetPoint;
import dji.common.camera.CameraVideoResolutionAndFrameRate;
import dji.common.camera.CameraWhiteBalanceAndColorTemperature;
import dji.common.camera.DJICameraExposureParameters;
import dji.common.camera.DJICameraSettingsDef.CameraAntiFlicker;
import dji.common.camera.DJICameraSettingsDef.CameraAperture;
import dji.common.camera.DJICameraSettingsDef.CameraApertureRange;
import dji.common.camera.DJICameraSettingsDef.CameraContrast;
import dji.common.camera.DJICameraSettingsDef.CameraCustomSettings;
import dji.common.camera.DJICameraSettingsDef.CameraDigitalFilter;
import dji.common.camera.DJICameraSettingsDef.CameraExposureCompensation;
import dji.common.camera.DJICameraSettingsDef.CameraExposureCompensationRange;
import dji.common.camera.DJICameraSettingsDef.CameraExposureMode;
import dji.common.camera.DJICameraSettingsDef.CameraExposureModeRange;
import dji.common.camera.DJICameraSettingsDef.CameraFileIndexMode;
import dji.common.camera.DJICameraSettingsDef.CameraISO;
import dji.common.camera.DJICameraSettingsDef.CameraISORange;
import dji.common.camera.DJICameraSettingsDef.CameraLensFocusMode;
import dji.common.camera.DJICameraSettingsDef.CameraLensFocusStatus;
import dji.common.camera.DJICameraSettingsDef.CameraLensType;
import dji.common.camera.DJICameraSettingsDef.CameraMeteringMode;
import dji.common.camera.DJICameraSettingsDef.CameraMode;
import dji.common.camera.DJICameraSettingsDef.CameraModeRange;
import dji.common.camera.DJICameraSettingsDef.CameraOpticalZoomSpec;
import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoAEBParam;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoAspectRatio;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoBurstCount;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoFileFormat;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoIntervalParam;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoQuality;
import dji.common.camera.DJICameraSettingsDef.CameraSharpness;
import dji.common.camera.DJICameraSettingsDef.CameraShootPhotoMode;
import dji.common.camera.DJICameraSettingsDef.CameraShutterSpeed;
import dji.common.camera.DJICameraSettingsDef.CameraShutterSpeedRange;
import dji.common.camera.DJICameraSettingsDef.CameraThermalDigitalZoomScale;
import dji.common.camera.DJICameraSettingsDef.CameraThermalExternalParamProfile;
import dji.common.camera.DJICameraSettingsDef.CameraThermalFFCMode;
import dji.common.camera.DJICameraSettingsDef.CameraThermalGainMode;
import dji.common.camera.DJICameraSettingsDef.CameraThermalIsothermUnit;
import dji.common.camera.DJICameraSettingsDef.CameraThermalPalette;
import dji.common.camera.DJICameraSettingsDef.CameraThermalProfile;
import dji.common.camera.DJICameraSettingsDef.CameraThermalROI;
import dji.common.camera.DJICameraSettingsDef.CameraThermalScene;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFileFormat;
import dji.common.camera.DJICameraSettingsDef.CameraVideoStandard;
import dji.common.camera.DJICameraSettingsDef.OpticalZoomDirection;
import dji.common.camera.DJICameraSettingsDef.OpticalZoomSpeed;
import dji.common.camera.DJICameraThermalMeasurementMode;
import dji.common.camera.ThermalAreaTemperatureAggregations;
import dji.common.camera.ThermalSpotMeteringTargetPoint;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.sdksharedlib.b.b.c;
import dji.sdksharedlib.b.b.d;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import dji.sdksharedlib.hardware.abstractions.c.b.a;
import dji.sdksharedlib.hardware.abstractions.c.c.e;
import dji.sdksharedlib.hardware.abstractions.c.c.f;
import dji.sdksharedlib.hardware.abstractions.c.d.g;
import dji.sdksharedlib.hardware.abstractions.c.d.h;
import dji.sdksharedlib.hardware.abstractions.c.d.i;
import dji.sdksharedlib.hardware.abstractions.c.d.k;
import dji.sdksharedlib.hardware.abstractions.c.d.l;

public class b extends d {
    @d(a = Boolean.class, c = 3, e = {g.class, i.class, dji.sdksharedlib.hardware.abstractions.c.d.d.class, l.class})
    public static final String A = "AudioRecordEnabled";
    @d(a = Boolean.class, c = 3, e = {e.class, f.class, i.class, dji.sdksharedlib.hardware.abstractions.c.d.d.class, l.class})
    public static final String B = "VideoSlowMotionEnabled";
    @d(a = CameraPhotoTimeLapseParam.class, c = 3, e = {i.class, dji.sdksharedlib.hardware.abstractions.c.d.d.class, l.class})
    public static final String C = "PhotoTimeLapseIntervalDurationAndFileFormat";
    @d(a = CameraAperture.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, a.class})
    public static final String D = "Aperture";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraApertureRange.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class})
    @c
    public static final String E = "ApertureRange";
    @d(a = CameraShutterSpeed.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String F = "ShutterSpeed";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraShutterSpeedRange.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    @c
    public static final String G = "ShutterSpeedRange";
    @d(a = CameraISO.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String H = "ISO";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraISORange.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    @c
    public static final String I = "ISORange";
    @d(a = CameraExposureCompensation.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String J = "ExposureCompensation";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraExposureCompensationRange.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    @c
    public static final String K = "ExposureCompensationRange";
    @d(a = Boolean.class, c = 3)
    public static final String L = "VideoCaptionEnabled";
    @d(a = CameraFileIndexMode.class, c = 3)
    public static final String M = "FileIndexMode";
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String N = "FirmwareVersion";
    @d(a = Integer.class, c = 3, e = {dji.sdksharedlib.hardware.abstractions.c.d.d.class, g.class, i.class, l.class})
    public static final String O = "AudioGain";
    @d(a = Boolean.class, c = 3, e = {dji.sdksharedlib.hardware.abstractions.c.d.d.class, l.class})
    public static final String P = "TurnOffFanWhenPossible";
    @d(a = Float.class, c = 7, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String Q = "DigitalZoomScale";
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, a.class})
    public static final String R = "LensInformation";
    @d(a = CameraLensFocusTargetPoint.class, c = 6, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, k.class, l.class, dji.sdksharedlib.hardware.abstractions.c.a.a.class, dji.sdksharedlib.hardware.abstractions.c.a.b.class})
    public static final String S = "LensFocusTarget";
    @d(a = Integer.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, a.class})
    public static final String T = "LensFocusRingValue";
    @d(a = Integer.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, a.class})
    public static final String U = "LensFocusRingValueUpperBound";
    @d(a = CameraOpticalZoomSpec.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, k.class, l.class, a.class})
    public static final String V = "OpticalZoomSpec";
    @d(a = Integer.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, k.class, l.class, a.class})
    public static final String W = "OpticalZoomFocalLength";
    @d(a = CameraThermalROI.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String X = "ThermalROI";
    @d(a = CameraThermalPalette.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String Y = "ThermalPalette";
    @d(a = CameraThermalScene.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String Z = "ThermalScene";
    public static final String a = "Camera";
    @d(a = Short.class, c = 2, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aA = "ThermalSceneEmissivity";
    @d(a = Short.class, c = 2, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aB = "ThermalWindowReflection";
    @d(a = Short.class, c = 2, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aC = "ThermalWindowReflectedTemperature";
    @d(a = Short.class, c = 2, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aD = "ThermalWindowTemperature";
    @d(a = Short.class, c = 2, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aE = "ThermalWindowTransmissionCoefficient";
    @d(a = CameraThermalProfile.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String aF = "ThermalProfile";
    @d(a = String.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String aG = "SerialNumber";
    @d(a = String.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String aH = "DisplayName";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aI = "SDCardIsInitializing";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aJ = "SDCardHasError";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aK = "SDCardIsReadOnly";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aL = "SDCardIsFormatInvalid";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aM = "SDCardIsFormatted";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aN = "SDCardIsFormatting";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aO = "SDCardIsFull";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aP = "SDCardIsVerified";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aQ = "SDCardIsInserted";
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aR = "SDCardTotalSpaceInMB";
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aS = "SDCardRemainingSpaceInMB";
    @d(a = Long.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aT = "SDCardAvailablePhotoCount";
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aU = "SDCardAvailableRecordingTimeInSeconds";
    @dji.sdksharedlib.b.b.f
    @c
    public static final String aV = "SDCardIsBusy";
    @d(a = DJICameraExposureParameters.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aW = "CurrentExposureValues";
    @d(a = Boolean.class, c = 4)
    public static final String aX = "IsShootingSinglePhoto";
    @d(a = Boolean.class, c = 4)
    public static final String aY = "IsShootingSinglePhotoInRAWFormat";
    @d(a = Boolean.class, c = 4)
    public static final String aZ = "IsShootingIntervalPhoto";
    @d(a = Integer.class, c = 3, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String aa = "ThermalDDE";
    @d(a = Integer.class, c = 3, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String ab = "ThermalACE";
    @d(a = Integer.class, c = 3, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String ac = "ThermalSSO";
    @d(a = Integer.class, c = 3, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String ad = "ThermalBrightness";
    @d(a = Integer.class, c = 3, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String ae = "ThermalContrast";
    @d(a = Boolean.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String af = "ThermalIsothermEnabled";
    @d(a = CameraThermalIsothermUnit.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String ag = "ThermalIsothermUnit";
    @d(a = Integer.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String ah = "ThermalIsothermUpperValue";
    @d(a = Integer.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String ai = "ThermalIsothermMiddleValue";
    @d(a = Integer.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String aj = "ThermalIsothermLowerValue";
    @d(a = CameraThermalGainMode.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String ak = "ThermalGainMode";
    @d(a = Boolean.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String al = "ThermalTemperatureDataEnabled";
    @d(a = Float.class, c = 5, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String am = "ThermalTemperatureData";
    @d(a = CameraThermalDigitalZoomScale.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String an = "ThermalDigitalZoomScale";
    @d(a = CameraThermalFFCMode.class, c = 6, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String ao = "ThermalFFCMode";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @c
    public static final String ap = "ThermalIsFFCModeSupported";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String aq = "ThermalTriggerFFC";
    @d(a = RectF.class, c = 6, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String ar = "ThermalSpotMeteringArea";
    @d(a = DJICameraThermalMeasurementMode.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String as = "ThermalMeasurementMode";
    @d(a = ThermalSpotMeteringTargetPoint.class, c = 6, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String at = "ThermalSpotMeteringTargetPoint";
    @d(a = ThermalAreaTemperatureAggregations.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String au = "ThermalSpotMeteringAreaTemperatureAggregations";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @c
    public static final String av = "ThermalIsOverallTemperatureMeterSupported";
    @d(a = CameraThermalExternalParamProfile.class, c = 6, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String aw = "ThermalCustomExternalSceneSettingsProfile";
    @d(a = Short.class, c = 2, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String ax = "ThermalAtmosphericTemperature";
    @d(a = Short.class, c = 2, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String ay = "ThermalAtmosphericTransmissionCoefficient";
    @d(a = Short.class, c = 2, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String az = "ThermalBackgroundTemperature";
    @d(a = CameraMode.class, c = 6)
    public static final String b = "CameraMode";
    @d(a = CameraLensFocusAssistant.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, k.class, l.class})
    public static final String bA = "LensFocusAssistantEnabledForMFAndAF";
    @d(a = CameraSSDOperationState.class, c = 4, e = {h.class, i.class})
    public static final String bB = "SSDOperationState";
    @d(a = Boolean.class, c = 4, e = {h.class, i.class})
    public static final String bC = "SSDIsConnected";
    @d(a = CameraSSDCapacity.class, c = 4, e = {h.class, i.class})
    public static final String bD = "SSDTotalSpace";
    @d(a = Integer.class, c = 4, e = {h.class, i.class})
    public static final String bE = "SSDAvailableRecordingTimeInSeconds";
    @d(a = Long.class, c = 4, e = {h.class, i.class})
    public static final String bF = "SSDRemainingSpaceInMB";
    @d(a = CameraSSDRawVideoResolutionAndFrameRate.class, c = 6, e = {h.class, i.class})
    public static final String bG = "SSDRawVideoResolutionAndFrameRate";
    @dji.sdksharedlib.b.b.f
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.c.a.b.class})
    public static final String bH = "LiveViewOutputEnabled";
    @d(b = {CameraShootPhotoMode.class}, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bI = "StartShootPhoto";
    @dji.sdksharedlib.b.b.f
    @d(a = Boolean.class, c = 6, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.c.a.a.class, dji.sdksharedlib.hardware.abstractions.c.a.b.class, f.class})
    public static final String bJ = "TurnOffLEDDuringShootPhotoEnabled";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bK = "StopShootPhoto";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bL = "StartRecordVideo";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bM = "StopRecordVideo";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bN = "LoadFactorySettings";
    @d(b = {CameraCustomSettings.class}, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bO = "SaveSettings";
    @d(b = {CameraCustomSettings.class}, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bP = "LoadSettings";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {h.class, i.class})
    public static final String bQ = "FormatSSD";
    @d(b = {OpticalZoomDirection.class, OpticalZoomSpeed.class}, c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, k.class, l.class, a.class})
    public static final String bR = "StartContinuousOpticalZoom";
    @dji.sdksharedlib.b.b.f
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {a.class})
    @c
    public static final String bS = "OneKeyZoom";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, k.class, l.class, a.class})
    public static final String bT = "StopContinuousOpticalZoom";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bU = "FormatSDCard";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraRotationAngleType.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC)
    @c
    public static final String bV = "CameraRotateMode";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraOrientation.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC)
    @c
    public static final String bW = "Orientation";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraType.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @c
    public static final String bX = "CameraType";
    @dji.sdksharedlib.b.b.f
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @c
    public static final String bY = "IsShootingPhoto";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraShootPhotoMode.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @c
    public static final String bZ = "ShootPhotoMode";
    @d(a = Boolean.class, c = 4)
    public static final String ba = "IsShootingBurstPhoto";
    @d(a = Boolean.class, c = 4)
    public static final String bb = "IsStoringPhoto";
    @d(a = Boolean.class, c = 4)
    public static final String bc = "IsRecording";
    @d(a = Boolean.class, c = 4)
    public static final String bd = "IsCameraOverHeated";
    @d(a = Boolean.class, c = 4)
    public static final String be = "HasCameraError";
    @d(a = Boolean.class, c = 4)
    public static final String bf = "IsShootPhotoEnabled";
    @d(a = Integer.class, c = 4)
    public static final String bg = "CurrentRecordingTimeInSeconds";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bh = "IsAudioRecordSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bi = "IsPlaybackSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bj = "IsMediaDownloadModeSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bk = "IsTimeLapseSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bl = "IsDigitalZoomScaleSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bm = "IsSlowMotionSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bn = "IsPhotoQuickViewSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bo = "IsChangeableLensSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bp = "IsAdjustableApertureSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bq = "IsAdjustableFocalPointSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String br = "IsSSDSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bs = "IsOpticalZoomSupported";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String bt = "IsThermalImagingCamera";
    @d(a = CameraLensType.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class})
    public static final String bu = "LensType";
    @d(a = Boolean.class, c = 4, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class})
    public static final String bv = "LensIsInstalled";
    @d(a = Boolean.class, c = 4, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class})
    public static final String bw = "LensIsAFSwitchOn";
    @d(a = CameraLensFocusStatus.class, c = 4, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, k.class, l.class, a.class})
    public static final String bx = "LensFocusStatus";
    @d(a = CameraLensFocusMode.class, c = 6, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, k.class, l.class, a.class})
    public static final String by = "LensFocusMode";
    @d(a = Boolean.class, c = 4, e = {dji.sdksharedlib.hardware.abstractions.c.d.e.class, g.class, h.class, i.class, k.class, l.class})
    public static final String bz = "LensIsFocusAssistantWorking";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraModeRange.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    @c
    public static final String c = "CameraModeRange";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String ca = "CameraTrackingMode";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.c.a.a.class, dji.sdksharedlib.hardware.abstractions.c.a.b.class})
    public static final String cb = "LiveViewOutputMode";
    @dji.sdksharedlib.b.b.f
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {a.class})
    public static final String cc = "TapZoomEnabled";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraTapZoomTargetPoint.class, c = 2, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {a.class})
    public static final String cd = "TapZoomTarget";
    @dji.sdksharedlib.b.b.f
    @d(a = Integer.class, c = 6, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {a.class})
    public static final String ce = "TapZoomMultiplier";
    @dji.sdksharedlib.b.b.f
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {a.class})
    @c
    public static final String cf = "TapZoomWorking";
    @dji.sdksharedlib.b.b.f
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {a.class})
    public static final String cg = "DefogEnabled";
    @dji.sdksharedlib.b.b.f
    @d(a = Float.class, c = 4, e = {a.class})
    public static final String ch = "OpticalZoomScale";
    @d(a = CameraVideoResolutionAndFrameRate.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class, a.class})
    public static final String d = "VideoResolutionAndFrameRate";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraVideoResolutionAndFrameRate.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    @c
    public static final String e = "VideoResolutionFrameRateRange";
    @d(a = CameraVideoFileFormat.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String f = "VideoFileFormat";
    @d(a = CameraVideoStandard.class, c = 6)
    public static final String g = "VideoStandard";
    @d(a = CameraPhotoAspectRatio.class, c = 3, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String h = "PhotoRatio";
    @d(a = CameraPhotoQuality.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class, a.class})
    public static final String i = "PhotoQuality";
    @d(a = CameraPhotoFileFormat.class, c = 6)
    public static final String j = "PhotoFileFormat";
    @d(a = CameraPhotoBurstCount.class, c = 3, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String k = "PhotoBurstCount";
    @d(a = CameraPhotoIntervalParam.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String l = "PhotoIntervalParam";
    @d(a = CameraExposureMode.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String m = "ExposureMode";
    @dji.sdksharedlib.b.b.f
    @d(a = CameraExposureModeRange.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    @c
    public static final String n = "ExposureModeRange";
    @d(a = CameraWhiteBalanceAndColorTemperature.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String o = "WhiteBalanceAndColorTemperature";
    @d(a = CameraMeteringMode.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String p = "MeteringMode";
    @d(a = CameraAntiFlicker.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String q = "AntiFlicker";
    @d(a = CameraSharpness.class, c = 3, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String r = "Sharpness";
    @d(a = CameraContrast.class, c = 3, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String s = "Contrast";
    @d(a = Integer.class, c = 3, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String t = "Saturation";
    @d(a = Integer.class, c = 3, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String u = "Hue";
    @d(a = CameraSpotMeteringArea.class, c = 3, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String v = "SpotMeteringArea";
    @d(a = CameraDigitalFilter.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String w = "DigitalFilter";
    @d(a = Boolean.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class})
    public static final String x = "AELock";
    @d(a = CameraPhotoAEBParam.class, c = 6, f = {dji.sdksharedlib.hardware.abstractions.c.d.a.class, dji.sdksharedlib.hardware.abstractions.c.d.b.class, a.class})
    public static final String y = "PhotoAEBParam";
    @d(a = Integer.class, c = 3)
    public static final String z = "PhotoQuickViewDuration";

    public b(String str) {
        super(str);
    }

    protected String a() {
        return a;
    }
}
