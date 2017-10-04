package dji.sdksharedlib.hardware.abstractions.c;

import android.os.Handler;
import dji.common.camera.CameraLensFocusAssistant;
import dji.common.camera.CameraLensFocusTargetPoint;
import dji.common.camera.CameraPhotoTimeLapseParam;
import dji.common.camera.CameraSSDRawVideoResolutionAndFrameRate;
import dji.common.camera.CameraSpotMeteringArea;
import dji.common.camera.CameraUtils;
import dji.common.camera.CameraVideoResolutionAndFrameRate;
import dji.common.camera.CameraWhiteBalanceAndColorTemperature;
import dji.common.camera.DJICameraExposureParameters;
import dji.common.camera.DJICameraSettingsDef.CameraAntiFlicker;
import dji.common.camera.DJICameraSettingsDef.CameraAperture;
import dji.common.camera.DJICameraSettingsDef.CameraContrast;
import dji.common.camera.DJICameraSettingsDef.CameraCustomSettings;
import dji.common.camera.DJICameraSettingsDef.CameraDigitalFilter;
import dji.common.camera.DJICameraSettingsDef.CameraExposureCompensation;
import dji.common.camera.DJICameraSettingsDef.CameraExposureMode;
import dji.common.camera.DJICameraSettingsDef.CameraFileIndexMode;
import dji.common.camera.DJICameraSettingsDef.CameraISO;
import dji.common.camera.DJICameraSettingsDef.CameraLensFocusMode;
import dji.common.camera.DJICameraSettingsDef.CameraLensFocusStatus;
import dji.common.camera.DJICameraSettingsDef.CameraLensType;
import dji.common.camera.DJICameraSettingsDef.CameraMeteringMode;
import dji.common.camera.DJICameraSettingsDef.CameraMode;
import dji.common.camera.DJICameraSettingsDef.CameraOpticalZoomSpec;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoAEBParam;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoAspectRatio;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoBurstCount;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoFileFormat;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoIntervalParam;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoQuality;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoTimeLapseFileFormat;
import dji.common.camera.DJICameraSettingsDef.CameraSharpness;
import dji.common.camera.DJICameraSettingsDef.CameraShootPhotoMode;
import dji.common.camera.DJICameraSettingsDef.CameraShutterSpeed;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFileFormat;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;
import dji.common.camera.DJICameraSettingsDef.CameraVideoStandard;
import dji.common.camera.DJICameraSettingsDef.CameraWhiteBalance;
import dji.common.camera.DJICameraSettingsDef.OpticalZoomDirection;
import dji.common.camera.DJICameraSettingsDef.OpticalZoomSpeed;
import dji.common.error.DJICameraError;
import dji.common.error.DJIError;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.common.util.BytesUtil;
import dji.common.util.DJICameraEnumMappingUtil;
import dji.common.util.DJILensFeatureUtils;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.b.a;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataBaseCameraGetting;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraActiveStatus;
import dji.midware.data.model.P3.DataCameraFormatSDCard;
import dji.midware.data.model.P3.DataCameraFormatSSD;
import dji.midware.data.model.P3.DataCameraGetAudio;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetImageSize.SizeType;
import dji.midware.data.model.P3.DataCameraGetIso;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.ShotFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.ZoomFocusType;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetQuickPlayBack;
import dji.midware.data.model.P3.DataCameraGetRecordFan;
import dji.midware.data.model.P3.DataCameraGetShotInfo;
import dji.midware.data.model.P3.DataCameraGetVOutParams;
import dji.midware.data.model.P3.DataCameraGetVideoCaption;
import dji.midware.data.model.P3.DataCameraLoadParams;
import dji.midware.data.model.P3.DataCameraSaveParams;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.midware.data.model.P3.DataCameraSetAEBParms;
import dji.midware.data.model.P3.DataCameraSetAELock;
import dji.midware.data.model.P3.DataCameraSetAperture;
import dji.midware.data.model.P3.DataCameraSetAudio;
import dji.midware.data.model.P3.DataCameraSetExposureMode;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetFocusAid;
import dji.midware.data.model.P3.DataCameraSetFocusArea;
import dji.midware.data.model.P3.DataCameraSetFocusDistance;
import dji.midware.data.model.P3.DataCameraSetFocusParam;
import dji.midware.data.model.P3.DataCameraSetFocusParam.ZoomMode;
import dji.midware.data.model.P3.DataCameraSetFocusStroke;
import dji.midware.data.model.P3.DataCameraSetImageSize;
import dji.midware.data.model.P3.DataCameraSetIso;
import dji.midware.data.model.P3.DataCameraSetMeteringArea;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.OpticsZommMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.ZoomSpeed;
import dji.midware.data.model.P3.DataCameraSetPhoto;
import dji.midware.data.model.P3.DataCameraSetQuickPlayBack;
import dji.midware.data.model.P3.DataCameraSetRecordFan;
import dji.midware.data.model.P3.DataCameraSetSSDVideoFormat;
import dji.midware.data.model.P3.DataCameraSetShutterSpeed;
import dji.midware.data.model.P3.DataCameraSetTimeParams;
import dji.midware.data.model.P3.DataCameraSetTimeParams.TYPE;
import dji.midware.data.model.P3.DataCameraSetVOutParams;
import dji.midware.data.model.P3.DataCameraSetVideoCaption;
import dji.midware.data.model.P3.DataCameraSetVideoFormat;
import dji.midware.data.model.P3.DataCameraSetVideoRecordMode;
import dji.midware.data.model.P3.DataCameraSetWhiteBalance;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataOsdGetMicGain;
import dji.midware.data.model.P3.DataOsdSetMicGain;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.b.f;
import java.util.EnumMap;

public class b extends a {
    private static final String H = "DJISDKCacheBaseCameraAbstraction";
    protected int F = -1;
    Handler G = new Handler(dji.sdksharedlib.e.b.a());
    protected int o = -1;
    protected int p = -1;
    protected ZoomSpeed q = null;
    protected DJICameraEnumMappingUtil r = null;
    protected DJILensFeatureUtils s = null;
    protected EnumMap<CameraAperture, Short> t = null;
    protected int u = -1;

    public void a(String str, int i, c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        this.r = new DJICameraEnumMappingUtil();
        this.t = CameraUtils.buildApertureMap();
        c();
    }

    public void e() {
        super.e();
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
    }

    protected void c() {
        super.c();
        onEventBackgroundThread(DataCameraGetPushShotParams.getInstance());
        onEventBackgroundThread(DataCameraGetPushShotInfo.getInstance());
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (dataCameraGetPushShotParams != null) {
            DJICameraExposureParameters dJICameraExposureParameters = new DJICameraExposureParameters();
            CameraISO realCameraISO = CameraUtils.getRealCameraISO(dataCameraGetPushShotParams.getRelISO());
            CameraExposureCompensation realCameraExposureCompensation = CameraUtils.getRealCameraExposureCompensation(dataCameraGetPushShotParams.getRelExposureCompensation());
            CameraAperture realCameraAperture = CameraUtils.getRealCameraAperture(dataCameraGetPushShotParams.getRealApertureSize());
            CameraShutterSpeed realShutterSpeed = CameraUtils.getRealShutterSpeed(dataCameraGetPushShotParams.isRelReciprocal(), dataCameraGetPushShotParams.getRelShutter(), dataCameraGetPushShotParams.getRelShutterSpeedDecimal());
            dJICameraExposureParameters.setAperture(realCameraAperture);
            dJICameraExposureParameters.setISO(realCameraISO);
            dJICameraExposureParameters.setExposureCompensation(realCameraExposureCompensation);
            dJICameraExposureParameters.setShutterSpeed(realShutterSpeed);
            realCameraISO = CameraISO.find(dataCameraGetPushShotParams.getISO());
            realCameraExposureCompensation = CameraUtils.getRealCameraExposureCompensation(dataCameraGetPushShotParams.getExposureCompensation());
            realCameraAperture = CameraUtils.getRealCameraAperture(dataCameraGetPushShotParams.getApertureSize());
            realShutterSpeed = CameraUtils.getRealShutterSpeed(dataCameraGetPushShotParams.isReciprocal(), dataCameraGetPushShotParams.getShutter(), dataCameraGetPushShotParams.getShutterSpeedDecimal());
            CameraVideoStandard find = CameraVideoStandard.find(dataCameraGetPushShotParams.getVideoStandard());
            boolean isAELock = dataCameraGetPushShotParams.isAELock();
            CameraPhotoQuality find2 = CameraPhotoQuality.find(dataCameraGetPushShotParams.getImageQuality());
            Object obj = CameraPhotoFileFormat.values()[dataCameraGetPushShotParams.getImageFormat()];
            CameraPhotoIntervalParam cameraPhotoIntervalParam = new CameraPhotoIntervalParam();
            cameraPhotoIntervalParam.captureCount = dataCameraGetPushShotParams.getTimeParamsNum();
            cameraPhotoIntervalParam.timeIntervalInSeconds = dataCameraGetPushShotParams.getTimeParamsPeriod();
            CameraExposureMode find3 = CameraExposureMode.find(dataCameraGetPushShotParams.getExposureMode().a());
            CameraVideoResolutionAndFrameRate a = a(dataCameraGetPushShotParams.getVideoFormat(), dataCameraGetPushShotParams.getVideoFps());
            CameraVideoFileFormat find4 = CameraVideoFileFormat.find(dataCameraGetPushShotParams.getVideoStoreFormat());
            CameraMeteringMode find5 = CameraMeteringMode.find(dataCameraGetPushShotParams.getMetering());
            CameraWhiteBalanceAndColorTemperature cameraWhiteBalanceAndColorTemperature = new CameraWhiteBalanceAndColorTemperature();
            cameraWhiteBalanceAndColorTemperature.setWhiteBalance(CameraWhiteBalance.find(dataCameraGetPushShotParams.getWhiteBalance()));
            cameraWhiteBalanceAndColorTemperature.setColorTemperature(dataCameraGetPushShotParams.getColorTemp());
            CameraDigitalFilter find6 = CameraDigitalFilter.find(dataCameraGetPushShotParams.getDigitalFilter());
            CameraAntiFlicker find7 = CameraAntiFlicker.find(dataCameraGetPushShotParams.getAntiFlicker());
            CameraShootPhotoMode find8 = CameraShootPhotoMode.find(dataCameraGetPushShotParams.getPhotoType());
            CameraPhotoAspectRatio find9 = CameraPhotoAspectRatio.find(dataCameraGetPushShotParams.getImageRatio().ordinal());
            CameraSharpness find10 = CameraSharpness.find(d(dataCameraGetPushShotParams.getSharpe()));
            CameraContrast find11 = CameraContrast.find(d(dataCameraGetPushShotParams.getContrast()));
            int saturation = dataCameraGetPushShotParams.getSaturation();
            int tonal = dataCameraGetPushShotParams.getTonal();
            a(CameraPhotoBurstCount.find(dataCameraGetPushShotParams.getContinuous()), c(dji.sdksharedlib.b.b.k));
            a(Integer.valueOf(tonal), c(dji.sdksharedlib.b.b.u));
            a(Integer.valueOf(saturation), c(dji.sdksharedlib.b.b.t));
            a(find10, c(dji.sdksharedlib.b.b.r));
            a(find11, c(dji.sdksharedlib.b.b.s));
            a(find9, c(dji.sdksharedlib.b.b.h));
            a(dJICameraExposureParameters, c(dji.sdksharedlib.b.b.aW));
            a(realCameraISO, c("ISO"));
            a(realCameraExposureCompensation, c(dji.sdksharedlib.b.b.J));
            a(realCameraAperture, c(dji.sdksharedlib.b.b.D));
            a(realShutterSpeed, c(dji.sdksharedlib.b.b.F));
            a(find, c(dji.sdksharedlib.b.b.g));
            a(Boolean.valueOf(isAELock), c(dji.sdksharedlib.b.b.x));
            a(find2, c(dji.sdksharedlib.b.b.i));
            a(obj, c(dji.sdksharedlib.b.b.j));
            a(cameraPhotoIntervalParam, c(dji.sdksharedlib.b.b.l));
            a(find3, c(dji.sdksharedlib.b.b.m));
            a(a, c(dji.sdksharedlib.b.b.d));
            a(find4, c(dji.sdksharedlib.b.b.f));
            a(find5, c(dji.sdksharedlib.b.b.p));
            a(cameraWhiteBalanceAndColorTemperature, c(dji.sdksharedlib.b.b.o));
            a(find6, c(dji.sdksharedlib.b.b.w));
            a(find7, c(dji.sdksharedlib.b.b.q));
            a(find8, c(dji.sdksharedlib.b.b.bZ));
            a(Boolean.valueOf(dataCameraGetPushShotParams.autoTurnOffForeLed()), c(dji.sdksharedlib.b.b.bJ));
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        boolean z = true;
        if (dataCameraGetPushShotInfo != null) {
            boolean z2;
            boolean isShotConnected = dataCameraGetPushShotInfo.isShotConnected();
            if (dataCameraGetPushShotInfo.getShotFocusMode() == ShotFocusMode.Auto) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (1 != dataCameraGetPushShotInfo.getMFFocusStatus()) {
                z = false;
            }
            boolean isDigitalFocusAEnable = dataCameraGetPushShotInfo.isDigitalFocusAEnable();
            boolean isDigitalFocusMEnable = dataCameraGetPushShotInfo.isDigitalFocusMEnable();
            CameraLensFocusAssistant cameraLensFocusAssistant = new CameraLensFocusAssistant();
            cameraLensFocusAssistant.setEnabledAF(isDigitalFocusAEnable);
            cameraLensFocusAssistant.setEnabledMF(isDigitalFocusMEnable);
            CameraLensType find = CameraLensType.find(dataCameraGetPushShotInfo.getShotType().value());
            CameraLensFocusStatus find2 = CameraLensFocusStatus.find(dataCameraGetPushShotInfo.getFocusStatus());
            CameraLensFocusMode a = a(dataCameraGetPushShotInfo);
            int shotFocusCurStroke = dataCameraGetPushShotInfo.getShotFocusCurStroke();
            CameraLensFocusTargetPoint cameraLensFocusTargetPoint = new CameraLensFocusTargetPoint();
            cameraLensFocusTargetPoint.setX(dataCameraGetPushShotInfo.getSpotAFAxisX());
            cameraLensFocusTargetPoint.setY(dataCameraGetPushShotInfo.getSpotAFAxisY());
            a(Boolean.valueOf(isShotConnected), c(dji.sdksharedlib.b.b.bv));
            a(Boolean.valueOf(z2), c(dji.sdksharedlib.b.b.bw));
            a(find, c(dji.sdksharedlib.b.b.bu));
            a(find2, c(dji.sdksharedlib.b.b.bx));
            a(a, c(dji.sdksharedlib.b.b.by));
            a(Boolean.valueOf(z), c(dji.sdksharedlib.b.b.bz));
            a(cameraLensFocusAssistant, c(dji.sdksharedlib.b.b.bA));
            a(Integer.valueOf(shotFocusCurStroke), c(dji.sdksharedlib.b.b.T));
            a(cameraLensFocusTargetPoint, c(dji.sdksharedlib.b.b.S));
            if (G()) {
                a(Integer.valueOf(dataCameraGetPushShotInfo.getCurFocusDistance()), c(dji.sdksharedlib.b.b.W));
            }
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "CameraMode")
    public void a(CameraMode cameraMode, e eVar) {
        if (cameraMode != CameraMode.Unknown) {
            int value = cameraMode.value();
            if (CameraMode.MediaDownload == cameraMode) {
                value = 7;
                if (!F()) {
                    if (eVar != null) {
                        eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
                        return;
                    }
                    return;
                }
            }
            if (CameraMode.Playback != cameraMode || E()) {
                a(value, eVar);
            } else if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    protected void a(int i, e eVar) {
        DataCameraSetMode instance = DataCameraSetMode.getInstance();
        instance.a(MODE.find(i));
        instance.start(new 1(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "FileIndexMode")
    public void a(CameraFileIndexMode cameraFileIndexMode, e eVar) {
        if (cameraFileIndexMode != CameraFileIndexMode.Unknown) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a(dji.sdksharedlib.b.b.M);
            dataBaseCameraSetting.a(cameraFileIndexMode.value());
            dataBaseCameraSetting.start(new 12(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FileIndexMode")
    public void a(e eVar) {
        DataBaseCameraGetting dataBaseCameraGetting = new DataBaseCameraGetting();
        dataBaseCameraGetting.setCmdId(dji.sdksharedlib.b.b.M);
        dataBaseCameraGetting.start(new 23(this, eVar, dataBaseCameraGetting));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "VideoResolutionAndFrameRate")
    public void a(CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate, e eVar) {
        int i = 4;
        CameraVideoResolution resolution = cameraVideoResolutionAndFrameRate.getResolution();
        CameraVideoFrameRate frameRate = cameraVideoResolutionAndFrameRate.getFrameRate();
        if (resolution != CameraVideoResolution.Resolution_3840x2160 || frameRate != CameraVideoFrameRate.FrameRate_60fps) {
            int i2;
            switch (68.a[resolution.ordinal()]) {
                case 1:
                    i2 = 4;
                    break;
                case 2:
                    i2 = 10;
                    break;
                case 3:
                    i2 = 24;
                    break;
                case 4:
                    i2 = 16;
                    break;
                case 5:
                    i2 = 22;
                    break;
                default:
                    i2 = -1;
                    break;
            }
            switch (68.b[frameRate.ordinal()]) {
                case 1:
                    i = 1;
                    break;
                case 2:
                    i = 2;
                    break;
                case 3:
                    i = 3;
                    break;
                case 4:
                    break;
                case 5:
                    i = 5;
                    break;
                case 6:
                    i = 6;
                    break;
                case 7:
                    i = 7;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i2 != -1 && i != -1) {
                DataCameraSetVideoFormat dataCameraSetVideoFormat = new DataCameraSetVideoFormat();
                dataCameraSetVideoFormat.a();
                dataCameraSetVideoFormat.a(i2);
                dataCameraSetVideoFormat.b(i);
                dataCameraSetVideoFormat.c(0);
                dataCameraSetVideoFormat.start(new 34(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "VideoFileFormat")
    public void a(CameraVideoFileFormat cameraVideoFileFormat, e eVar) {
        if (cameraVideoFileFormat != CameraVideoFileFormat.Unknown) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a("VideoStoreFormat");
            dataBaseCameraSetting.a(cameraVideoFileFormat.value());
            dataBaseCameraSetting.start(new 45(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "VideoStandard")
    public void a(CameraVideoStandard cameraVideoStandard, e eVar) {
        if (!ServiceManager.getInstance().isConnected()) {
            eVar.a(DJICameraError.CAMERA_CONNECTION_NOT_OK);
        } else if (cameraVideoStandard == CameraVideoStandard.Unknown) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        } else {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a("Standard");
            dataBaseCameraSetting.a(cameraVideoStandard.value());
            dataBaseCameraSetting.start(null);
            new Handler(dji.sdksharedlib.e.b.a()).postDelayed(new 56(this, cameraVideoStandard, eVar), 1000);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "PhotoRatio")
    public void a(CameraPhotoAspectRatio cameraPhotoAspectRatio, e eVar) {
        SizeType sizeType = SizeType.DEFAULT;
        RatioType ratioType = RatioType.OTHER;
        if (cameraPhotoAspectRatio == CameraPhotoAspectRatio.AspectRatio_4_3) {
            ratioType = RatioType.R_4_3;
        } else if (cameraPhotoAspectRatio == CameraPhotoAspectRatio.AspectRatio_16_9) {
            ratioType = RatioType.R_16_9;
        }
        if (ratioType != RatioType.OTHER) {
            DataCameraSetImageSize.getInstance().a(sizeType).a(ratioType).start(new 67(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "PhotoQuality")
    public void a(CameraPhotoQuality cameraPhotoQuality, e eVar) {
        if (cameraPhotoQuality != CameraPhotoQuality.Unknown) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a("ImageQuality");
            dataBaseCameraSetting.a(cameraPhotoQuality.value());
            dataBaseCameraSetting.start(new 69(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "PhotoFileFormat")
    public void a(CameraPhotoFileFormat cameraPhotoFileFormat, e eVar) {
        if (cameraPhotoFileFormat != CameraPhotoFileFormat.Unknown) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a("ImageFormat");
            dataBaseCameraSetting.a(cameraPhotoFileFormat.value());
            dataBaseCameraSetting.start(new 70(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "PhotoBurstCount")
    public void a(CameraPhotoBurstCount cameraPhotoBurstCount, e eVar) {
        DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
        dataBaseCameraSetting.a("Continuous");
        dataBaseCameraSetting.a(cameraPhotoBurstCount.value());
        dataBaseCameraSetting.a(0, 1);
        dataBaseCameraSetting.start(new 2(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "PhotoIntervalParam")
    public void a(CameraPhotoIntervalParam cameraPhotoIntervalParam, e eVar) {
        if (cameraPhotoIntervalParam == null) {
            if (eVar != null) {
                eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
            }
        } else if (b(cameraPhotoIntervalParam.timeIntervalInSeconds)) {
            DataCameraSetTimeParams.getInstance().a(cameraPhotoIntervalParam.captureCount).b(cameraPhotoIntervalParam.timeIntervalInSeconds).a(TYPE.a).start(new 3(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    protected boolean O() {
        return this.n == CameraType.DJICameraTypeFC550;
    }

    protected boolean P() {
        ExposureMode exposureMode = DataCameraGetPushShotParams.getInstance().getExposureMode();
        if (O()) {
            if (exposureMode == ExposureMode.P || exposureMode == ExposureMode.d) {
                return true;
            }
            return false;
        } else if (exposureMode == ExposureMode.P) {
            return true;
        } else {
            return false;
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ExposureMode")
    public void a(CameraExposureMode cameraExposureMode, e eVar) {
        if (cameraExposureMode != CameraExposureMode.Unknown) {
            DataCameraSetExposureMode.getInstance().a(cameraExposureMode.value()).start(new 4(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ISO")
    public void a(CameraISO cameraISO, e eVar) {
        DataCameraGetIso.TYPE find = DataCameraGetIso.TYPE.find(cameraISO.value());
        if (find == DataCameraGetIso.TYPE.OTHER) {
            if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            }
        } else if (DataCameraGetPushShotParams.getInstance().getExposureMode() != ExposureMode.M || find != DataCameraGetIso.TYPE.AUTO) {
            DataCameraSetIso dataCameraSetIso = new DataCameraSetIso();
            dataCameraSetIso.a(true);
            dataCameraSetIso.a(find);
            dataCameraSetIso.start(new 5(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ShutterSpeed")
    public void a(CameraShutterSpeed cameraShutterSpeed, e eVar) {
        int i = 5;
        int i2 = 2;
        boolean z = true;
        if (cameraShutterSpeed == null) {
            if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            }
        } else if (!P()) {
            boolean z2;
            if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_8000) {
                i2 = dji.gs.c.e.b;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_6400) {
                i2 = 6400;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_5000) {
                i2 = 5000;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_4000) {
                i2 = 4000;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_3200) {
                i2 = 3200;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_2500) {
                i2 = 2500;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_2000) {
                i2 = 2000;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_1600) {
                i2 = 1600;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_1250) {
                i2 = 1250;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_1000) {
                i2 = 1000;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_800) {
                i2 = dji.gs.c.e.g;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_640) {
                i2 = 640;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_500) {
                i2 = 500;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_400) {
                i2 = 400;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_320) {
                i2 = 320;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_240) {
                i2 = 240;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_200) {
                i2 = 200;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_160) {
                i2 = 160;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_120) {
                i2 = 120;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_100) {
                i2 = 100;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_80) {
                i2 = 80;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_60) {
                i2 = 60;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_50) {
                i2 = 50;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_40) {
                i2 = 40;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_30) {
                i2 = 30;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_25) {
                i2 = 25;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_20) {
                i2 = 20;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_15) {
                i2 = 15;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_12p5) {
                i2 = 12;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_10) {
                i2 = 10;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_8) {
                i2 = 8;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_6p25) {
                i2 = 6;
                i = 25;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_5) {
                i2 = 5;
                z2 = true;
                i = 0;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_4) {
                i2 = 4;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_3) {
                i2 = 3;
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_2p5) {
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_2) {
                i = 0;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_1p67) {
                i = 67;
                i2 = 1;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1_1p25) {
                i = 25;
                i2 = 1;
                z2 = true;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1p0) {
                i = 0;
                i2 = 1;
                z2 = false;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1p3) {
                i = 3;
                i2 = 1;
                z2 = false;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed1p6) {
                i = 6;
                i2 = 1;
                z2 = false;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed2p0) {
                i = 0;
                z2 = false;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed2p5) {
                z2 = false;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed3p0) {
                i2 = 3;
                i = 0;
                z2 = false;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed3p2) {
                z2 = false;
                i = 2;
                i2 = 3;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed4p0) {
                i2 = 4;
                i = 0;
                z2 = false;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed5p0) {
                i2 = 5;
                z2 = false;
                i = 0;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed6p0) {
                i2 = 6;
                i = 0;
                z2 = false;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed7p0) {
                i2 = 7;
                i = 0;
                z2 = false;
            } else if (cameraShutterSpeed == CameraShutterSpeed.ShutterSpeed8p0) {
                i2 = 8;
                i = 0;
                z2 = false;
            } else {
                i = -1;
                i2 = -1;
                z2 = true;
            }
            if (!z2 && i2 != -1 && i != -1) {
                DataCameraSetShutterSpeed instance = DataCameraSetShutterSpeed.getInstance();
                if (!z2) {
                    z = false;
                }
                instance.a(z, i2, i).start(new 6(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "WhiteBalanceAndColorTemperature")
    public void a(CameraWhiteBalanceAndColorTemperature cameraWhiteBalanceAndColorTemperature, e eVar) {
        CameraWhiteBalance whiteBalance = cameraWhiteBalanceAndColorTemperature.getWhiteBalance();
        int colorTemperature = cameraWhiteBalanceAndColorTemperature.getColorTemperature();
        if (a(whiteBalance, colorTemperature)) {
            DataCameraSetWhiteBalance dataCameraSetWhiteBalance = new DataCameraSetWhiteBalance();
            if (CameraWhiteBalance.CustomColorTemperature == whiteBalance) {
                dataCameraSetWhiteBalance.a();
                dataCameraSetWhiteBalance.a(6);
                dataCameraSetWhiteBalance.b(colorTemperature);
            } else {
                dataCameraSetWhiteBalance.a();
                dataCameraSetWhiteBalance.a(whiteBalance.value());
                dataCameraSetWhiteBalance.b(0);
            }
            dataCameraSetWhiteBalance.start(new 7(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    protected boolean a(CameraWhiteBalance cameraWhiteBalance, int i) {
        if (cameraWhiteBalance == CameraWhiteBalance.Unknown) {
            return false;
        }
        if (CameraWhiteBalance.CustomColorTemperature != cameraWhiteBalance || (i >= 20 && i <= 100)) {
            return true;
        }
        return false;
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "MeteringMode")
    public void a(CameraMeteringMode cameraMeteringMode, e eVar) {
        if (cameraMeteringMode.value() != CameraMeteringMode.Unknown.value()) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a(a.SetMetering);
            dataBaseCameraSetting.a(cameraMeteringMode.value());
            dataBaseCameraSetting.start(new 8(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ExposureCompensation")
    public void a(CameraExposureCompensation cameraExposureCompensation, e eVar) {
        if (cameraExposureCompensation != CameraExposureCompensation.Unknown) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a(dji.sdksharedlib.b.b.J);
            dataBaseCameraSetting.a(cameraExposureCompensation.value());
            dataBaseCameraSetting.a(0, 1);
            dataBaseCameraSetting.start(new 9(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "AntiFlicker")
    public void a(CameraAntiFlicker cameraAntiFlicker, e eVar) {
        if (DataCameraGetPushShotParams.getInstance().getExposureMode() != ExposureMode.P) {
            if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
            }
        } else if (cameraAntiFlicker != CameraAntiFlicker.Unknown) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a(dji.sdksharedlib.b.b.q);
            dataBaseCameraSetting.a(cameraAntiFlicker.value());
            dataBaseCameraSetting.a(0, 1);
            dataBaseCameraSetting.start(new 10(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "Sharpness")
    public void a(CameraSharpness cameraSharpness, e eVar) {
        int i;
        switch (68.c[cameraSharpness.ordinal()]) {
            case 1:
                i = 0;
                break;
            case 2:
                i = 3;
                break;
            case 3:
                i = -3;
                break;
            default:
                i = -1;
                break;
        }
        if (i != -1) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a("Sharpe");
            dataBaseCameraSetting.a(i);
            dataBaseCameraSetting.a(0, 1);
            dataBaseCameraSetting.start(new 11(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "Contrast")
    public void a(CameraContrast cameraContrast, e eVar) {
        int i;
        switch (68.d[cameraContrast.ordinal()]) {
            case 1:
                i = 0;
                break;
            case 2:
                i = 3;
                break;
            case 3:
                i = -3;
                break;
            default:
                i = -1;
                break;
        }
        if (i != -1) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a(dji.sdksharedlib.b.b.s);
            dataBaseCameraSetting.a(i);
            dataBaseCameraSetting.a(0, 1);
            dataBaseCameraSetting.start(new 13(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "Saturation")
    public void b(int i, e eVar) {
        if (i >= -3 && i <= 3) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a(dji.sdksharedlib.b.b.t);
            dataBaseCameraSetting.a(i);
            dataBaseCameraSetting.start(new 14(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "Hue")
    public void c(int i, e eVar) {
        if (i >= -3 && i <= 3) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a("Tonal");
            dataBaseCameraSetting.a(i);
            dataBaseCameraSetting.start(new 15(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SpotMeteringArea")
    public void a(CameraSpotMeteringArea cameraSpotMeteringArea, e eVar) {
        if (!DataCameraGetPushShotParams.getInstance().isAELock()) {
            int colIndex = cameraSpotMeteringArea.getColIndex();
            int rowIndex = cameraSpotMeteringArea.getRowIndex();
            if (rowIndex < 0 || rowIndex > 7) {
                if (eVar != null) {
                    eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
                }
            } else if (colIndex >= 0 && colIndex <= 11) {
                DataCameraSetMeteringArea.getInstance().a(colIndex + (rowIndex * 12)).start(new 16(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_CANNOT_SET_PARAMETERS_IN_THIS_STATE);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SpotMeteringArea")
    public void b(e eVar) {
        if (eVar != null) {
            DataBaseCameraGetting dataBaseCameraGetting = new DataBaseCameraGetting();
            dataBaseCameraGetting.setCmdId("MeteringArea");
            dataBaseCameraGetting.start(new 17(this, dataBaseCameraGetting, eVar));
        }
    }

    protected int a(int i) {
        return i;
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "DigitalFilter")
    public void a(CameraDigitalFilter cameraDigitalFilter, e eVar) {
        if (cameraDigitalFilter != CameraDigitalFilter.Unknown && cameraDigitalFilter != CameraDigitalFilter.Portrait) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a(dji.sdksharedlib.b.b.w);
            dataBaseCameraSetting.a(cameraDigitalFilter.value());
            dataBaseCameraSetting.start(new 18(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "AELock")
    public void a(boolean z, e eVar) {
        DataCameraSetAELock.getInstance().a(z).start(new 19(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "PhotoAEBParam")
    public void a(CameraPhotoAEBParam cameraPhotoAEBParam, e eVar) {
        if (cameraPhotoAEBParam != null && eVar != null) {
            DataCameraSetAEBParms.getInstance().a(cameraPhotoAEBParam.exposureOffset).b(cameraPhotoAEBParam.captureCount).start(new 20(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "PhotoQuickViewDuration")
    public void d(int i, e eVar) {
        if (x()) {
            if (i >= 0 && i <= 10) {
                DataCameraSetQuickPlayBack instance = DataCameraSetQuickPlayBack.getInstance();
                instance.a(BytesUtil.getByte(i));
                if (i == 0) {
                    instance.a(false);
                } else {
                    instance.a(true);
                }
                instance.start(new 21(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "PhotoQuickViewDuration")
    public void c(e eVar) {
        DataCameraGetQuickPlayBack.getInstance().start(new 22(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "VideoCaptionEnabled")
    public void b(boolean z, e eVar) {
        DataCameraSetVideoCaption.getInstance().a().a(z).start(new 24(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "VideoCaptionEnabled")
    public void d(e eVar) {
        DataCameraGetVideoCaption instance = DataCameraGetVideoCaption.getInstance();
        instance.start(new 25(this, eVar, instance));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FirmwareVersion")
    public void e(e eVar) {
        String str = ".";
        DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.CAMERA);
        dataCommonGetVersion.start(new 26(this, dataCommonGetVersion, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "AudioRecordEnabled")
    public void c(boolean z, e eVar) {
        if (y()) {
            new DataCameraSetAudio().a(z).start(new 27(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "AudioRecordEnabled")
    public void f(e eVar) {
        if (y()) {
            DataCameraGetAudio dataCameraGetAudio = new DataCameraGetAudio();
            dataCameraGetAudio.start(new 28(this, eVar, dataCameraGetAudio));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "AudioGain")
    public void e(int i, e eVar) {
        if (y()) {
            if (i >= 0 && i <= 100) {
                DataOsdSetMicGain.getInstance().a(i).start(new 29(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "AudioGain")
    public void g(e eVar) {
        if (y()) {
            DataOsdGetMicGain instance = DataOsdGetMicGain.getInstance();
            instance.start(new 30(this, eVar, instance));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "TurnOffFanWhenPossible")
    public void d(boolean z, e eVar) {
        if (t()) {
            if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 4) {
                new DataCameraSetRecordFan().a(z).start(new 31(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJICameraError.COMMAND_NOT_SUPPORTED_BY_FIRMWARE);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "TurnOffFanWhenPossible")
    public void h(e eVar) {
        if (t()) {
            if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 4) {
                DataCameraGetRecordFan dataCameraGetRecordFan = new DataCameraGetRecordFan();
                dataCameraGetRecordFan.start(new 32(this, eVar, dataCameraGetRecordFan));
            } else if (eVar != null) {
                eVar.a(DJICameraError.COMMAND_NOT_SUPPORTED_BY_FIRMWARE);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "DigitalZoomScale")
    public void a(float f, e eVar) {
        if (v()) {
            DJIError Q = Q();
            if (Q != null) {
                if (eVar != null) {
                    eVar.a(Q);
                }
            } else if (((double) f) >= 1.0d && ((double) f) <= 2.0d) {
                DataCameraSetFocusParam instance = DataCameraSetFocusParam.getInstance();
                instance.d(true).b(ZoomMode.b).d(f);
                instance.start(new 33(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "DigitalZoomScale")
    public void i(e eVar) {
        if (v()) {
            if (DataCameraGetPushStateInfo.getInstance().getVerstion() < 4) {
                if (eVar != null) {
                    eVar.a(DJICameraError.COMMAND_NOT_SUPPORTED_BY_FIRMWARE);
                }
            } else if (eVar != null) {
                eVar.a(Float.valueOf(((float) DataCameraGetPushShotParams.getInstance().getDigitalZoomScale()) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_CAMERA_UNKNOWN);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "PhotoTimeLapseIntervalDurationAndFileFormat")
    public void a(CameraPhotoTimeLapseParam cameraPhotoTimeLapseParam, e eVar) {
        CameraPhotoTimeLapseFileFormat fileFormat = cameraPhotoTimeLapseParam.getFileFormat();
        int interval = cameraPhotoTimeLapseParam.getInterval();
        int duration = cameraPhotoTimeLapseParam.getDuration();
        if (CameraPhotoTimeLapseFileFormat.JPEGAndVideo == fileFormat) {
            if (interval < 20 || interval > 1000) {
                eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
                return;
            }
        } else if (interval < 10 || interval > 1000) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
            return;
        }
        if (duration < 0) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        } else if (fileFormat == CameraPhotoTimeLapseFileFormat.Unknown) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        } else {
            if (fileFormat == CameraPhotoTimeLapseFileFormat.JPEGAndVideo && interval < 20) {
                eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
            }
            int value = fileFormat.value();
            if (value == 1) {
                value = 2;
            }
            DataCameraSetVideoRecordMode dataCameraSetVideoRecordMode = new DataCameraSetVideoRecordMode();
            dataCameraSetVideoRecordMode.a(1, interval, duration).a(0).b(value);
            dataCameraSetVideoRecordMode.start(new 35(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "PhotoTimeLapseIntervalDurationAndFileFormat")
    public void j(e eVar) {
        if (eVar != null) {
            DataCameraGetPushShotParams instance = DataCameraGetPushShotParams.getInstance();
            int videoRecordIntervalTime = instance.getVideoRecordIntervalTime();
            int timelapseDuration = instance.getTimelapseDuration();
            int timelapseSaveType = instance.getTimelapseSaveType();
            if (timelapseSaveType == 2) {
                timelapseSaveType = 1;
            }
            CameraPhotoTimeLapseFileFormat find = CameraPhotoTimeLapseFileFormat.find(timelapseSaveType);
            Object cameraPhotoTimeLapseParam = new CameraPhotoTimeLapseParam();
            cameraPhotoTimeLapseParam.setDuration(timelapseDuration);
            cameraPhotoTimeLapseParam.setFileFormat(find);
            cameraPhotoTimeLapseParam.setInterval(videoRecordIntervalTime);
            eVar.a(cameraPhotoTimeLapseParam);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "VideoSlowMotionEnabled")
    public void e(boolean z, e eVar) {
        if (!s()) {
            CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate = new CameraVideoResolutionAndFrameRate();
            if (z) {
                cameraVideoResolutionAndFrameRate.setFrameRate(CameraVideoFrameRate.FrameRate_120fps);
                cameraVideoResolutionAndFrameRate.setResolution(CameraVideoResolution.Resolution_1920x1080);
            } else {
                cameraVideoResolutionAndFrameRate.setFrameRate(CameraVideoFrameRate.FrameRate_48fps);
                cameraVideoResolutionAndFrameRate.setResolution(CameraVideoResolution.Resolution_1920x1080);
            }
            a(cameraVideoResolutionAndFrameRate, eVar);
        } else if (z) {
            Runnable 36 = new 36(this, eVar);
            DataCameraSetVideoFormat dataCameraSetVideoFormat = new DataCameraSetVideoFormat();
            dataCameraSetVideoFormat.a();
            dataCameraSetVideoFormat.a(10);
            dataCameraSetVideoFormat.b(7);
            dataCameraSetVideoFormat.start(new 37(this, eVar, 36));
        } else {
            DataCameraSetVideoRecordMode dataCameraSetVideoRecordMode = new DataCameraSetVideoRecordMode();
            dataCameraSetVideoRecordMode.a(0, 0, 0);
            dataCameraSetVideoRecordMode.start(new 38(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "VideoSlowMotionEnabled")
    public void k(e eVar) {
        if (eVar == null) {
            return;
        }
        if (s()) {
            DataCameraGetPushShotParams instance = DataCameraGetPushShotParams.getInstance();
            if (instance.getVideoRecordMode() == 2) {
                eVar.a(Boolean.valueOf(true));
                return;
            } else if (instance.getVideoRecordMode() == 0 || instance.getVideoRecordMode() == 1) {
                eVar.a(Boolean.valueOf(false));
                return;
            } else {
                eVar.a(DJICameraError.COMMON_UNKNOWN);
                return;
            }
        }
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(new dji.sdksharedlib.b.c.a().b(dji.sdksharedlib.b.b.a).d(dji.sdksharedlib.b.b.d).a());
        if (availableValue != null) {
            CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate = (CameraVideoResolutionAndFrameRate) availableValue.e();
            if (cameraVideoResolutionAndFrameRate.getResolution() == CameraVideoResolution.Resolution_1920x1080 && cameraVideoResolutionAndFrameRate.getFrameRate() == CameraVideoFrameRate.FrameRate_120fps) {
                eVar.a(Boolean.valueOf(true));
                return;
            } else {
                eVar.a(Boolean.valueOf(false));
                return;
            }
        }
        eVar.a(DJICameraError.CAMERA_PARAMETERS_GET_FAILED);
    }

    protected boolean y() {
        return false;
    }

    protected boolean x() {
        return true;
    }

    protected boolean b(int i) {
        return true;
    }

    public DJIError Q() {
        if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD) {
            int videoFormat = DataCameraGetPushShotParams.getInstance().getVideoFormat();
            int videoFps = DataCameraGetPushShotParams.getInstance().getVideoFps();
            if (videoFormat > 10 && videoFormat != 24 && videoFormat != 25 && videoFormat != 31) {
                return DJICameraError.CAMERA_INVALID_PARAM;
            }
            if (videoFps >= 7) {
                return DJICameraError.CAMERA_INVALID_PARAM;
            }
        }
        if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO && DataCameraGetPushShotParams.getInstance().getPhotoType() == DataCameraSetPhoto.TYPE.h) {
            return DJICameraError.CAMERA_INVALID_PARAM;
        }
        return null;
    }

    private CameraVideoResolutionAndFrameRate a(int i, int i2) {
        CameraVideoFrameRate cameraVideoFrameRate;
        Object obj = null;
        CameraVideoResolution cameraVideoResolution = CameraVideoResolution.Unknown;
        int i3;
        switch (i) {
            case 4:
                cameraVideoResolution = CameraVideoResolution.Resolution_1280x720;
                i3 = 1;
                break;
            case 10:
                cameraVideoResolution = CameraVideoResolution.Resolution_1920x1080;
                i3 = 1;
                break;
            case 16:
                cameraVideoResolution = CameraVideoResolution.Resolution_3840x2160;
                i3 = 1;
                break;
            case 22:
                cameraVideoResolution = CameraVideoResolution.Resolution_4096x2160;
                i3 = 1;
                break;
            case 24:
                cameraVideoResolution = CameraVideoResolution.Resolution_2704X1520;
                i3 = 1;
                break;
            default:
                Object obj2 = null;
                break;
        }
        CameraVideoFrameRate cameraVideoFrameRate2 = CameraVideoFrameRate.Unknown;
        int i4;
        switch (i2) {
            case 1:
                i4 = 1;
                cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_24fps;
                break;
            case 2:
                i4 = 1;
                cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_25fps;
                break;
            case 3:
                i4 = 1;
                cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_30fps;
                break;
            case 4:
                i4 = 1;
                cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_48fps;
                break;
            case 5:
                i4 = 1;
                cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_50fps;
                break;
            case 6:
                i4 = 1;
                cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_60fps;
                break;
            case 7:
                i4 = 1;
                cameraVideoFrameRate = CameraVideoFrameRate.FrameRate_120fps;
                break;
            default:
                cameraVideoFrameRate = cameraVideoFrameRate2;
                break;
        }
        if (obj2 == null || r3 == null) {
            return null;
        }
        CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate = new CameraVideoResolutionAndFrameRate();
        cameraVideoResolutionAndFrameRate.setFrameRate(cameraVideoFrameRate);
        cameraVideoResolutionAndFrameRate.setResolution(cameraVideoResolution);
        return cameraVideoResolutionAndFrameRate;
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "LiveViewOutputMode")
    public void f(boolean z, e eVar) {
        if (DataCameraGetPushStateInfo.getInstance().beInTrackingMode() && eVar != null) {
            eVar.a(DJICameraError.COMMON_SYSTEM_BUSY);
        }
        DataCameraSetVOutParams.getInstance().a(true).b(z).start(new 39(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "LiveViewOutputMode")
    public void l(e eVar) {
        if (eVar != null) {
            DataCameraGetVOutParams.getInstance().start(new 40(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartShootPhoto")
    public void a(e eVar, CameraShootPhotoMode cameraShootPhotoMode) {
        if (!a(cameraShootPhotoMode)) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else if (!DataCameraGetPushStateInfo.getInstance().getIsStoring()) {
            int[] iArr = new int[]{-1};
            int[] iArr2 = new int[]{2, 5};
            Runnable 41 = new 41(this, iArr, eVar);
            Runnable 42 = new 42(this, iArr2, eVar, 41);
            Runnable 44 = new 44(this, eVar, iArr2, new 43(this, iArr2, eVar, 41));
            if (cameraShootPhotoMode != CameraShootPhotoMode.TimeLapse) {
                if (cameraShootPhotoMode == CameraShootPhotoMode.Single) {
                    iArr[0] = CameraShootPhotoMode.Single.getInernalTypeValue();
                } else if (cameraShootPhotoMode == CameraShootPhotoMode.HDR) {
                    if (D()) {
                        iArr[0] = CameraShootPhotoMode.HDR.getInernalTypeValue();
                    } else if (eVar != null) {
                        eVar.a(DJICameraError.COMMON_UNSUPPORTED);
                        return;
                    } else {
                        return;
                    }
                } else if (cameraShootPhotoMode == CameraShootPhotoMode.Burst) {
                    iArr[0] = CameraShootPhotoMode.Burst.getInernalTypeValue();
                } else if (cameraShootPhotoMode == CameraShootPhotoMode.AEBCapture) {
                    iArr[0] = CameraShootPhotoMode.AEBCapture.getInernalTypeValue();
                } else if (cameraShootPhotoMode == CameraShootPhotoMode.Interval) {
                    iArr[0] = CameraShootPhotoMode.Interval.getInernalTypeValue();
                    this.G.post(44);
                    return;
                } else if (eVar != null) {
                    eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
                    return;
                } else {
                    return;
                }
                this.G.post(41);
            } else if (u()) {
                iArr[0] = CameraShootPhotoMode.TimeLapse.getInernalTypeValue();
                this.G.post(new 46(this, eVar, iArr2, 42));
            } else if (eVar != null) {
                eVar.a(DJICameraError.COMMON_UNSUPPORTED);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
        }
    }

    protected boolean a(CameraShootPhotoMode cameraShootPhotoMode) {
        return true;
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StopShootPhoto")
    public void m(e eVar) {
        DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
        dataBaseCameraSetting.a("Photo");
        dataBaseCameraSetting.a(0);
        dataBaseCameraSetting.a(0, 1);
        dataBaseCameraSetting.start(new 47(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartRecordVideo")
    public void n(e eVar) {
        DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
        dataBaseCameraSetting.a("Record");
        dataBaseCameraSetting.a(1);
        dataBaseCameraSetting.a(0, 1);
        dataBaseCameraSetting.start(new 48(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StopRecordVideo")
    public void o(e eVar) {
        DataSpecialControl.getInstance().setRecordType(false).start(20);
        if (eVar != null) {
            eVar.a(null);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "FormatSDCard")
    public void p(e eVar) {
        DataCameraFormatSDCard.getInstance().start(new 49(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "LoadFactorySettings")
    public void q(e eVar) {
        DataCameraLoadParams instance = DataCameraLoadParams.getInstance();
        instance.setMode(USER.DEFAULT);
        instance.start(new 50(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "SaveSettings")
    public void a(e eVar, CameraCustomSettings cameraCustomSettings) {
        if (CameraCustomSettings.Unknown != cameraCustomSettings) {
            DataCameraSaveParams instance = DataCameraSaveParams.getInstance();
            instance.setMode(USER.find(cameraCustomSettings.value()));
            instance.start(new 51(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "LoadSettings")
    public void b(e eVar, CameraCustomSettings cameraCustomSettings) {
        DataCameraLoadParams instance = DataCameraLoadParams.getInstance();
        instance.setMode(USER.find(cameraCustomSettings.value()));
        instance.start(new 52(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "LensFocusMode")
    public void a(CameraLensFocusMode cameraLensFocusMode, e eVar) {
        new DataBaseCameraSetting().a(a.SetFocusMode).a(cameraLensFocusMode.value()).start(new 53(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "LensFocusTarget")
    public void a(CameraLensFocusTargetPoint cameraLensFocusTargetPoint, e eVar) {
        DataCameraSetFocusArea.getInstance().a(cameraLensFocusTargetPoint.getX()).b(cameraLensFocusTargetPoint.getY()).start(new 54(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "LensFocusAssistantEnabledForMFAndAF")
    public void a(CameraLensFocusAssistant cameraLensFocusAssistant, e eVar) {
        DataCameraSetFocusAid instance = DataCameraSetFocusAid.getInstance();
        instance.a(cameraLensFocusAssistant.isEnabledAF(), cameraLensFocusAssistant.isEnabledMF());
        instance.start(new 55(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "LensFocusRingValueUpperBound")
    public void r(e eVar) {
        int shotFocusMaxStroke = DataCameraGetPushShotInfo.getInstance().getShotFocusMaxStroke();
        this.p = shotFocusMaxStroke;
        if (eVar != null) {
            eVar.a(Integer.valueOf(shotFocusMaxStroke));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "LensFocusRingValue")
    public void f(int i, e eVar) {
        if (-1 == this.p) {
            this.p = DataCameraGetPushShotInfo.getInstance().getShotFocusMaxStroke();
        }
        if (this.o <= i && this.p >= i) {
            DataCameraSetFocusStroke.getInstance().a(i).start(new 57(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    protected boolean G() {
        return DataCameraGetPushShotInfo.getInstance().getZoomFocusType() == ZoomFocusType.AutoZoomFocus;
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "OpticalZoomSpec")
    public void s(e eVar) {
        if (eVar != null) {
            if (G()) {
                DataCameraGetPushShotInfo instance = DataCameraGetPushShotInfo.getInstance();
                Object cameraOpticalZoomSpec = new CameraOpticalZoomSpec();
                cameraOpticalZoomSpec.maxFocalLength = instance.getMaxFocusDistance();
                cameraOpticalZoomSpec.minFocalLength = instance.getMinFocusDistance();
                cameraOpticalZoomSpec.focalLengthStep = instance.getMinFocusDistanceStep();
                eVar.a(cameraOpticalZoomSpec);
                return;
            }
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "OpticalZoomFocalLength")
    public void g(int i, e eVar) {
        if (G()) {
            if (c(i)) {
                h(i, eVar);
            } else if (eVar != null) {
                eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SSDRawVideoResolutionAndFrameRate")
    public void a(CameraSSDRawVideoResolutionAndFrameRate cameraSSDRawVideoResolutionAndFrameRate, e eVar) {
        int i = 4;
        if (B()) {
            int i2;
            CameraVideoResolution resolution = cameraSSDRawVideoResolutionAndFrameRate.getResolution();
            CameraVideoFrameRate frameRate = cameraSSDRawVideoResolutionAndFrameRate.getFrameRate();
            switch (68.a[resolution.ordinal()]) {
                case 1:
                    i2 = 4;
                    break;
                case 2:
                    i2 = 10;
                    break;
                case 3:
                    i2 = 24;
                    break;
                case 4:
                    i2 = 16;
                    break;
                case 5:
                    i2 = 22;
                    break;
                default:
                    i2 = -1;
                    break;
            }
            switch (68.b[frameRate.ordinal()]) {
                case 1:
                    i = 1;
                    break;
                case 2:
                    i = 2;
                    break;
                case 3:
                    i = 3;
                    break;
                case 4:
                    break;
                case 5:
                    i = 5;
                    break;
                case 6:
                    i = 6;
                    break;
                case 7:
                    i = 7;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i2 != -1 && i != -1) {
                DataCameraSetSSDVideoFormat.getInstance().a(i2).b(i).start(new 58(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "Aperture")
    public void a(CameraAperture cameraAperture, e eVar) {
        if (a(cameraAperture)) {
            new DataCameraSetAperture().a((short) cameraAperture.value()).start(new 59(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    protected boolean a(CameraAperture cameraAperture) {
        if (-1 == this.u || -1 == this.F) {
            this.u = DataCameraGetPushShotInfo.getInstance().getMinAperture();
            this.F = DataCameraGetPushShotInfo.getInstance().getMaxAperture();
        }
        return cameraAperture.value() > this.u && cameraAperture.value() < this.F;
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "LensInformation")
    public void t(e eVar) {
        if (eVar != null) {
            DataCameraGetShotInfo instance = DataCameraGetShotInfo.getInstance();
            instance.start(new 60(this, instance, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartContinuousOpticalZoom")
    public void a(e eVar, OpticalZoomDirection opticalZoomDirection, OpticalZoomSpeed opticalZoomSpeed) {
        if (R()) {
            new DataCameraSetOpticsZoomMode().a(OpticsZommMode.CONTINUOUS, ZoomSpeed.find(opticalZoomSpeed.value()), opticalZoomDirection.ordinal(), 0).start(new 61(this, opticalZoomSpeed, eVar));
        } else {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StopContinuousOpticalZoom")
    public void u(e eVar) {
        if (R()) {
            if (S()) {
                new DataCameraSetOpticsZoomMode().a(OpticsZommMode.STOPZOOM, this.q, 0, 0).start(new 62(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    protected boolean R() {
        if (p()) {
            return true;
        }
        if (s() && G()) {
            return true;
        }
        return false;
    }

    protected boolean S() {
        if (this.q == null) {
            return false;
        }
        return true;
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "FormatSSD")
    public void v(e eVar) {
        if (B()) {
            DataCameraFormatSSD.getInstance().a(1).start(new 63(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    protected void h(int i, e eVar) {
        DataCameraSetFocusDistance.getInstance().a(i).start(new 64(this, eVar));
    }

    protected boolean c(int i) {
        int minFocusDistance = DataCameraGetPushShotInfo.getInstance().getMinFocusDistance();
        int maxFocusDistance = DataCameraGetPushShotInfo.getInstance().getMaxFocusDistance();
        int minFocusDistanceStep = DataCameraGetPushShotInfo.getInstance().getMinFocusDistanceStep();
        if (i < minFocusDistance || i > maxFocusDistance || i % minFocusDistanceStep != 0) {
            return false;
        }
        return true;
    }

    protected CameraLensFocusMode a(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        if (K() && !L()) {
            return CameraLensFocusMode.Manual;
        }
        if (!L() || K()) {
            return CameraLensFocusMode.find(dataCameraGetPushShotInfo.getFuselageFocusMode().value());
        }
        return CameraLensFocusMode.Auto;
    }

    protected boolean T() {
        return false;
    }

    protected boolean U() {
        return false;
    }

    private boolean W() {
        CameraVideoResolutionAndFrameRate wrapCameraVideoResolutionAndFrameRate = this.r.wrapCameraVideoResolutionAndFrameRate(DataCameraGetPushShotParams.getInstance().getVideoFormat(), DataCameraGetPushShotParams.getInstance().getVideoFps());
        return wrapCameraVideoResolutionAndFrameRate.getResolution() == CameraVideoResolution.Resolution_2704X1520 || wrapCameraVideoResolutionAndFrameRate.getResolution() == CameraVideoResolution.Resolution_2720x1530 || wrapCameraVideoResolutionAndFrameRate.getResolution() == CameraVideoResolution.Resolution_1920x1080;
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SerialNumber")
    public void w(e eVar) {
        a(eVar, 0);
    }

    private void a(e eVar, int i) {
        if (V()) {
            new dji.midware.data.model.b.b().a(DeviceType.CAMERA).start(new 65(this, i, eVar));
        } else {
            DataCameraActiveStatus.getInstance().setType(dji.midware.data.model.b.a.b.b).start(new 66(this, i, eVar));
        }
    }

    protected boolean V() {
        return false;
    }

    private int d(int i) {
        switch (i) {
            case -3:
                return 2;
            case 3:
                return 1;
            default:
                return 0;
        }
    }
}
