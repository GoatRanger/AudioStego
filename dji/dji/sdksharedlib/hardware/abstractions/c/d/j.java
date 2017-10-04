package dji.sdksharedlib.hardware.abstractions.c.d;

import android.graphics.RectF;
import dji.common.camera.CameraUtils.RecordVideoTimeoutLock;
import dji.common.camera.CameraUtils.ShootPhotoTimeoutLock;
import dji.common.camera.DJICameraSettingsDef.CameraMode;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoFileFormat;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoIntervalParam;
import dji.common.camera.DJICameraSettingsDef.CameraShootPhotoMode;
import dji.common.camera.DJICameraSettingsDef.CameraThermalDigitalZoomScale;
import dji.common.camera.DJICameraSettingsDef.CameraThermalExternalParamProfile;
import dji.common.camera.DJICameraSettingsDef.CameraThermalFFCMode;
import dji.common.camera.DJICameraSettingsDef.CameraThermalFrameRateUpperBound;
import dji.common.camera.DJICameraSettingsDef.CameraThermalGainMode;
import dji.common.camera.DJICameraSettingsDef.CameraThermalIsothermUnit;
import dji.common.camera.DJICameraSettingsDef.CameraThermalLensFocalLength;
import dji.common.camera.DJICameraSettingsDef.CameraThermalPalette;
import dji.common.camera.DJICameraSettingsDef.CameraThermalProfile;
import dji.common.camera.DJICameraSettingsDef.CameraThermalROI;
import dji.common.camera.DJICameraSettingsDef.CameraThermalResolution;
import dji.common.camera.DJICameraSettingsDef.CameraThermalScene;
import dji.common.camera.DJICameraSettingsDef.CameraVideoStandard;
import dji.common.camera.DJICameraThermalMeasurementMode;
import dji.common.camera.ThermalAreaTemperatureAggregations;
import dji.common.camera.ThermalSpotMeteringTargetPoint;
import dji.common.error.DJICameraError;
import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraGetStateInfo.PhotoState;
import dji.midware.data.model.P3.DataCameraLoadParams;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.midware.data.model.P3.DataCameraSetFocusParam;
import dji.midware.data.model.P3.DataCameraSetFocusParam.ZoomMode;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCameraSetTimeParams;
import dji.midware.data.model.P3.DataCameraSetTimeParams.TYPE;
import dji.midware.data.model.P3.DataCameraSetVideoFormat;
import dji.midware.data.model.P3.DataCameraTauAreaAxis;
import dji.midware.data.model.P3.DataCameraTauExterParamType;
import dji.midware.data.model.P3.DataCameraTauExterParamType.ExterParamType;
import dji.midware.data.model.P3.DataCameraTauExterParams;
import dji.midware.data.model.P3.DataCameraTauExterParams.ExterParamId;
import dji.midware.data.model.P3.DataCameraTauFFCMode;
import dji.midware.data.model.P3.DataCameraTauFFCMode.FFCMode;
import dji.midware.data.model.P3.DataCameraTauParamAGC;
import dji.midware.data.model.P3.DataCameraTauParamAGC.AGCType;
import dji.midware.data.model.P3.DataCameraTauParamBrightness;
import dji.midware.data.model.P3.DataCameraTauParamConstrast;
import dji.midware.data.model.P3.DataCameraTauParamDigitalInc;
import dji.midware.data.model.P3.DataCameraTauParamGainMode;
import dji.midware.data.model.P3.DataCameraTauParamGainMode.GainMode;
import dji.midware.data.model.P3.DataCameraTauParamIsothermEnable;
import dji.midware.data.model.P3.DataCameraTauParamIsothermUnit;
import dji.midware.data.model.P3.DataCameraTauParamIsothermValue;
import dji.midware.data.model.P3.DataCameraTauParamOptizate;
import dji.midware.data.model.P3.DataCameraTauParamROI;
import dji.midware.data.model.P3.DataCameraTauParamROI.ROIType;
import dji.midware.data.model.P3.DataCameraTauParamThermometric;
import dji.midware.data.model.P3.DataCameraTauParamThermometricEnable;
import dji.midware.data.model.P3.DataCameraTauParamThermometricEnable.ThermometricType;
import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;
import dji.midware.data.model.P3.DataCameraTauTriggerFFC;
import dji.sdksharedlib.b.b;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.b.f;
import dji.sdksharedlib.hardware.abstractions.c.a;

public class j extends a {
    private static final int F = 0;
    private static final int G = 1;
    private static final int H = 2;
    private static final int I = 3;
    private static final int J = 4;
    private static final int K = 5;
    private static final int L = 6;
    private static final int M = 7;
    private static final int N = 8;
    private static final int O = 9;
    private static float o = 10000.0f;
    private static int p = 100;
    private static b q = b.getInstance();
    private static c r = c.getInstance();
    private static PhotoState s = PhotoState.NO;
    private static RecordType t = RecordType.NO;
    private float u;

    public void a(String str, int i, c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
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
        onEventBackgroundThread(DataCameraGetPushTauParam.getInstance());
    }

    protected boolean F() {
        return true;
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        super.onEventBackgroundThread(dataCameraGetPushStateInfo);
        V();
        W();
    }

    public void onEventBackgroundThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        if (dataCameraGetPushTauParam != null) {
            if (dataCameraGetPushTauParam.getThermometricType() == ThermometricType.b) {
                this.u = dataCameraGetPushTauParam.getThermometricTemp();
                a(Float.valueOf(this.u), c(b.am));
            }
            CameraPhotoFileFormat e = e(dataCameraGetPushTauParam.getImageFormat());
            CameraVideoStandard a = a(dataCameraGetPushTauParam.getVideoFormat(), dataCameraGetPushTauParam.getVideoFps());
            CameraPhotoIntervalParam a2 = a(dataCameraGetPushTauParam.getPhotoInterval());
            Object obj = CameraThermalROI.values()[dataCameraGetPushTauParam.getROIType().a()];
            CameraThermalProfile a3 = a(dataCameraGetPushTauParam.getLenFocusLength().value(), dataCameraGetPushTauParam.getVideoResolution().value(), dataCameraGetPushTauParam.getLenFps().ordinal());
            int zoomScale = dataCameraGetPushTauParam.getZoomScale();
            boolean z = dataCameraGetPushTauParam.getThermometricType() == ThermometricType.b;
            short isothermLower = dataCameraGetPushTauParam.getIsothermLower();
            short isothermMiddle = dataCameraGetPushTauParam.getIsothermMiddle();
            short isothermUpper = dataCameraGetPushTauParam.getIsothermUpper();
            Object obj2 = CameraThermalIsothermUnit.values()[dataCameraGetPushTauParam.getIsothermUnit()];
            boolean isIsothermEnable = dataCameraGetPushTauParam.isIsothermEnable();
            Object obj3 = CameraThermalScene.values()[dataCameraGetPushTauParam.getAGC().a()];
            CameraThermalPalette c = c(dataCameraGetPushTauParam.getDigitalFilter());
            CameraThermalGainMode cameraThermalGainMode = CameraThermalGainMode.values()[DataCameraGetPushTauParam.getInstance().getGainMode().a()];
            a(e, c(b.j));
            a(a, c(b.g));
            a(a2, c(b.l));
            a(obj, c(b.X));
            a(a3, c(b.aF));
            a(b(zoomScale), c(b.an));
            a(Boolean.valueOf(z), c(b.al));
            a(Integer.valueOf(isothermLower), c(b.aj));
            a(Integer.valueOf(isothermMiddle), c(b.ai));
            a(Integer.valueOf(isothermUpper), c(b.ah));
            a(obj2, c(b.ag));
            a(Boolean.valueOf(isIsothermEnable), c(b.af));
            a(obj3, c(b.Z));
            a(c, c(b.Y));
            a(cameraThermalGainMode, c(b.ak));
            if (b()) {
                a(Boolean.valueOf(b()), c(b.av));
                a(CameraThermalFFCMode.values()[dataCameraGetPushTauParam.getFFCMode().ordinal()], c(b.ao));
                a(new ThermalSpotMeteringTargetPoint(dataCameraGetPushTauParam.getThermometricXAxis(), dataCameraGetPushTauParam.getThermometricYAxis()), c(b.at));
                a(new RectF(((float) dataCameraGetPushTauParam.getAreaThermometricLeft()) / o, ((float) dataCameraGetPushTauParam.getAreaThermometricTop()) / o, ((float) dataCameraGetPushTauParam.getAreaThermometricRight()) / o, ((float) dataCameraGetPushTauParam.getAreaThermometricBottom()) / o), c(b.ar));
                a(new ThermalAreaTemperatureAggregations(dataCameraGetPushTauParam.getAreaThermometricAverage(), dataCameraGetPushTauParam.getAreaThermometricMin(), dataCameraGetPushTauParam.getAreaThermometricMinX(), dataCameraGetPushTauParam.getAreaThermometricMinY(), dataCameraGetPushTauParam.getAreaThermometricMax(), dataCameraGetPushTauParam.getAreaThermometricMaxX(), dataCameraGetPushTauParam.getAreaThermometricMaxY()), c(b.au));
                b(CameraThermalExternalParamProfile.find(dataCameraGetPushTauParam.getExterParamType().ordinal()), b.aw);
                b(Short.valueOf((short) (dataCameraGetPushTauParam.getAtmosphereTemperature() / 100)), b.ax);
                b(Short.valueOf((short) (dataCameraGetPushTauParam.getAtmosphereTransmission() / 100)), b.ay);
                b(Short.valueOf((short) (dataCameraGetPushTauParam.getBackgroundTemperature() / 100)), b.az);
                b(Short.valueOf((short) (dataCameraGetPushTauParam.getTargetEmissivity() / 100)), b.aA);
                b(Short.valueOf((short) (dataCameraGetPushTauParam.getWindowReflection() / 100)), b.aB);
                b(Short.valueOf((short) (dataCameraGetPushTauParam.getWindowReflectedTemperature() / 100)), b.aC);
                b(Short.valueOf((short) (dataCameraGetPushTauParam.getWindowTemperature() / 100)), b.aD);
                b(Short.valueOf((short) (dataCameraGetPushTauParam.getWindowTransmission() / 100)), b.aE);
            }
        }
    }

    public boolean H() {
        return true;
    }

    protected boolean b() {
        return DataCameraGetPushTauParam.getInstance().supportSpotThermometric();
    }

    private boolean P() {
        return DataCameraGetPushTauParam.getInstance().isThermometricValid();
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "CameraMode")
    public void a(CameraMode cameraMode, e eVar) {
        if (cameraMode == CameraMode.Unknown) {
            if (eVar != null) {
                eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
            }
        } else if (CameraMode.Playback != cameraMode || E()) {
            if (CameraMode.MediaDownload == cameraMode) {
                cameraMode = CameraMode.Playback;
            }
            int value = cameraMode.value();
            DataCameraSetMode instance = DataCameraSetMode.getInstance();
            instance.a(MODE.find(value));
            instance.start(new 1(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "PhotoFileFormat")
    public void a(CameraPhotoFileFormat cameraPhotoFileFormat, e eVar) {
        if (a(cameraPhotoFileFormat)) {
            int b = b(cameraPhotoFileFormat);
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a("ImageFormat");
            dataBaseCameraSetting.a(b);
            dataBaseCameraSetting.start(new 12(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "VideoStandard")
    public void a(CameraVideoStandard cameraVideoStandard, e eVar) {
        if (ServiceManager.getInstance().isConnected()) {
            if (cameraVideoStandard != CameraVideoStandard.Unknown) {
                int i;
                int i2;
                if (CameraVideoStandard.NTSC == cameraVideoStandard) {
                    i = 3;
                    i2 = 0;
                } else if (CameraVideoStandard.PAL == cameraVideoStandard) {
                    i2 = 26;
                    i = 2;
                } else {
                    eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
                    return;
                }
                DataCameraSetVideoFormat dataCameraSetVideoFormat = new DataCameraSetVideoFormat();
                dataCameraSetVideoFormat.a();
                dataCameraSetVideoFormat.a(i2);
                dataCameraSetVideoFormat.b(i);
                dataCameraSetVideoFormat.c(0);
                dataCameraSetVideoFormat.start(new 23(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
            }
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_CONNECTION_NOT_OK);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "PhotoIntervalParam")
    public void a(CameraPhotoIntervalParam cameraPhotoIntervalParam, e eVar) {
        if (cameraPhotoIntervalParam == null || eVar == null) {
            if (eVar != null) {
                eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
            }
        } else if (cameraPhotoIntervalParam.captureCount != 255) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        } else if (cameraPhotoIntervalParam.timeIntervalInSeconds < 1 || cameraPhotoIntervalParam.timeIntervalInSeconds > 60) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        } else {
            DataCameraSetTimeParams.getInstance().a(255).b(cameraPhotoIntervalParam.timeIntervalInSeconds).a(TYPE.a).start(new 34(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "LoadFactorySettings")
    public void b(e eVar) {
        DataCameraLoadParams instance = DataCameraLoadParams.getInstance();
        instance.setMode(USER.DEFAULT);
        instance.start(new 36(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalContrast")
    public void a(int i, e eVar) {
        if (DataCameraGetPushTauParam.getInstance().getAGC().a() != 5) {
            if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
            }
        } else if (i >= 0 && i <= 255) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a(dji.midware.data.config.P3.b.a.SetContrast).a(i);
            dataBaseCameraSetting.start(new 37(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ThermalContrast")
    public void c(e eVar) {
        if (eVar != null) {
            if (DataCameraGetPushTauParam.getInstance().getAGC().a() < 5 || DataCameraGetPushTauParam.getInstance().getAGC().a() > 8) {
                eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
            } else {
                eVar.a(Integer.valueOf(DataCameraGetPushTauParam.getInstance().getContrast()));
            }
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalROI")
    public void a(CameraThermalROI cameraThermalROI, e eVar) {
        if (cameraThermalROI != CameraThermalROI.Unknown) {
            DataCameraTauParamROI dataCameraTauParamROI = new DataCameraTauParamROI();
            dataCameraTauParamROI.a(ROIType.find(cameraThermalROI.ordinal())).b(false);
            dataCameraTauParamROI.start(new 38(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalPalette")
    public void a(CameraThermalPalette cameraThermalPalette, e eVar) {
        int i;
        switch (cameraThermalPalette.ordinal()) {
            case 0:
                i = 24;
                break;
            case 1:
                i = 25;
                break;
            case 2:
                i = 26;
                break;
            case 3:
                i = 27;
                break;
            case 4:
                i = 28;
                break;
            case 5:
                i = 29;
                break;
            case 6:
                i = 30;
                break;
            case 7:
                i = 31;
                break;
            case 8:
                i = 32;
                break;
            case 9:
                i = 33;
                break;
            case 10:
                i = 34;
                break;
            case 11:
                i = 35;
                break;
            case 12:
                i = 36;
                break;
            case 13:
                i = 37;
                break;
            default:
                i = -1;
                break;
        }
        if (i != -1) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a(dji.midware.data.config.P3.b.a.SetDigitalFilter).a(i);
            dataBaseCameraSetting.start(new 39(this, eVar));
            return;
        }
        eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalScene")
    public void a(CameraThermalScene cameraThermalScene, e eVar) {
        if (cameraThermalScene == CameraThermalScene.Unknown) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            return;
        }
        int ordinal = cameraThermalScene.ordinal();
        DataCameraTauParamAGC dataCameraTauParamAGC = new DataCameraTauParamAGC();
        dataCameraTauParamAGC.a(AGCType.find(ordinal)).b(false);
        dataCameraTauParamAGC.start(new 40(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalDDE")
    public void b(int i, e eVar) {
        if (DataCameraGetPushTauParam.getInstance().getAGC().a() < 5 || DataCameraGetPushTauParam.getInstance().getAGC().a() > 8) {
            eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
        } else if (i < -20 || i > 100) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else {
            DataCameraTauParamDigitalInc dataCameraTauParamDigitalInc = new DataCameraTauParamDigitalInc();
            dataCameraTauParamDigitalInc.a(i).b(false);
            dataCameraTauParamDigitalInc.start(new 2(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ThermalDDE")
    public void d(e eVar) {
        if (eVar != null) {
            if (DataCameraGetPushTauParam.getInstance().getAGC().a() < 5 || DataCameraGetPushTauParam.getInstance().getAGC().a() > 8) {
                eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
            } else {
                eVar.a(Integer.valueOf(DataCameraGetPushTauParam.getInstance().getDDE()));
            }
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalACE")
    public void c(int i, e eVar) {
        if (DataCameraGetPushTauParam.getInstance().getAGC().a() < 6 || DataCameraGetPushTauParam.getInstance().getAGC().a() > 8) {
            eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
        } else if (i < -8 || i > 8) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else {
            DataCameraTauParamConstrast dataCameraTauParamConstrast = new DataCameraTauParamConstrast();
            dataCameraTauParamConstrast.a(i).b(false);
            dataCameraTauParamConstrast.start(new 3(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ThermalACE")
    public void e(e eVar) {
        if (eVar != null) {
            if (DataCameraGetPushTauParam.getInstance().getAGC().a() < 5 || DataCameraGetPushTauParam.getInstance().getAGC().a() > 8) {
                eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
            } else if (eVar != null) {
                eVar.a(Integer.valueOf(DataCameraGetPushTauParam.getInstance().getACE()));
            }
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalSSO")
    public void d(int i, e eVar) {
        if (DataCameraGetPushTauParam.getInstance().getAGC().a() < 6 || DataCameraGetPushTauParam.getInstance().getAGC().a() > 8) {
            eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
        } else if (i < 0 || i > 100) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else {
            DataCameraTauParamOptizate dataCameraTauParamOptizate = new DataCameraTauParamOptizate();
            dataCameraTauParamOptizate.a(i).b(false);
            dataCameraTauParamOptizate.start(new 4(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ThermalSSO")
    public void f(e eVar) {
        if (eVar != null) {
            if (DataCameraGetPushTauParam.getInstance().getAGC().a() < 5 || DataCameraGetPushTauParam.getInstance().getAGC().a() > 8) {
                eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
            } else {
                eVar.a(Integer.valueOf(DataCameraGetPushTauParam.getInstance().getSSO()));
            }
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalBrightness")
    public void e(int i, e eVar) {
        if (DataCameraGetPushTauParam.getInstance().getAGC().a() != 5) {
            eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
        } else if (i < 0 || i > dji.pilot.newfpv.topbar.widget.a.s) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else {
            DataCameraTauParamBrightness dataCameraTauParamBrightness = new DataCameraTauParamBrightness();
            dataCameraTauParamBrightness.a(i).b(false);
            dataCameraTauParamBrightness.start(new 5(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ThermalBrightness")
    public void g(e eVar) {
        if (DataCameraGetPushTauParam.getInstance().getAGC().a() < 5 || DataCameraGetPushTauParam.getInstance().getAGC().a() > 8) {
            eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
        } else if (eVar != null) {
            eVar.a(Integer.valueOf(DataCameraGetPushTauParam.getInstance().getBrightness()));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalIsothermEnabled")
    public void a(boolean z, e eVar) {
        DataCameraTauParamIsothermEnable dataCameraTauParamIsothermEnable = new DataCameraTauParamIsothermEnable();
        dataCameraTauParamIsothermEnable.a(z).b(false);
        dataCameraTauParamIsothermEnable.start(new 6(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalIsothermUnit")
    public void a(CameraThermalIsothermUnit cameraThermalIsothermUnit, e eVar) {
        if (cameraThermalIsothermUnit == CameraThermalIsothermUnit.Unknown) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            return;
        }
        DataCameraTauParamIsothermUnit dataCameraTauParamIsothermUnit = new DataCameraTauParamIsothermUnit();
        dataCameraTauParamIsothermUnit.a(cameraThermalIsothermUnit.ordinal()).b(false);
        dataCameraTauParamIsothermUnit.start(new 7(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalIsothermUpperValue")
    public void f(int i, e eVar) {
        if (DataCameraGetPushTauParam.getInstance().getIsothermUnit() == 0 && (i < 0 || i > 100)) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else if (DataCameraGetPushTauParam.getInstance().getIsothermUnit() == 1 && (i < -40 || i > 1000)) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else if (i < DataCameraGetPushTauParam.getInstance().getIsothermMiddle()) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else {
            DataCameraTauParamIsothermValue dataCameraTauParamIsothermValue = new DataCameraTauParamIsothermValue();
            dataCameraTauParamIsothermValue.a(ParamCmd.m).a((short) i).b(false);
            dataCameraTauParamIsothermValue.start(new 8(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalIsothermMiddleValue")
    public void g(int i, e eVar) {
        if (DataCameraGetPushTauParam.getInstance().getIsothermUnit() == 0 && (i < 0 || i > 100)) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else if (DataCameraGetPushTauParam.getInstance().getIsothermUnit() == 1 && (i < -40 || i > 1000)) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else if (i < DataCameraGetPushTauParam.getInstance().getIsothermLower() || i > DataCameraGetPushTauParam.getInstance().getIsothermUpper()) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else {
            DataCameraTauParamIsothermValue dataCameraTauParamIsothermValue = new DataCameraTauParamIsothermValue();
            dataCameraTauParamIsothermValue.a(ParamCmd.l).a((short) i).b(false);
            dataCameraTauParamIsothermValue.start(new 9(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalIsothermLowerValue")
    public void h(int i, e eVar) {
        if (DataCameraGetPushTauParam.getInstance().getIsothermUnit() == 0 && (i < 0 || i > 100)) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else if (DataCameraGetPushTauParam.getInstance().getIsothermUnit() == 1 && (i < -40 || i > 1000)) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else if (i > DataCameraGetPushTauParam.getInstance().getIsothermMiddle()) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else {
            DataCameraTauParamIsothermValue dataCameraTauParamIsothermValue = new DataCameraTauParamIsothermValue();
            dataCameraTauParamIsothermValue.a(ParamCmd.k).a((short) i).b(false);
            dataCameraTauParamIsothermValue.start(new 10(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalTemperatureDataEnabled")
    public void b(boolean z, e eVar) {
        DataCameraTauParamThermometricEnable dataCameraTauParamThermometricEnable = new DataCameraTauParamThermometricEnable();
        if (z) {
            dataCameraTauParamThermometricEnable.a(ThermometricType.b);
        } else {
            dataCameraTauParamThermometricEnable.a(ThermometricType.a);
        }
        dataCameraTauParamThermometricEnable.start(new 11(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalDigitalZoomScale")
    public void a(CameraThermalDigitalZoomScale cameraThermalDigitalZoomScale, e eVar) {
        if (cameraThermalDigitalZoomScale == CameraThermalDigitalZoomScale.Unknown) {
            if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            }
        } else if (!I() || cameraThermalDigitalZoomScale != CameraThermalDigitalZoomScale.x8) {
            int i;
            switch (35.a[cameraThermalDigitalZoomScale.ordinal()]) {
                case 1:
                    i = 1;
                    break;
                case 2:
                    i = 2;
                    break;
                case 3:
                    i = 4;
                    break;
                case 4:
                    i = 8;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (i != -1) {
                DataCameraSetFocusParam instance = DataCameraSetFocusParam.getInstance();
                instance.d(true).b(ZoomMode.b).d((float) i);
                instance.start(new 13(this, eVar));
                return;
            }
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        } else if (eVar != null) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalGainMode")
    public void a(CameraThermalGainMode cameraThermalGainMode, e eVar) {
        if (cameraThermalGainMode == CameraThermalGainMode.Unknown) {
            eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            return;
        }
        DataCameraTauParamGainMode dataCameraTauParamGainMode = new DataCameraTauParamGainMode();
        dataCameraTauParamGainMode.a(GainMode.find(cameraThermalGainMode.ordinal()));
        dataCameraTauParamGainMode.start(new 14(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalFFCMode")
    public void a(CameraThermalFFCMode cameraThermalFFCMode, e eVar) {
        if (N()) {
            if (cameraThermalFFCMode != CameraThermalFFCMode.Unknown) {
                DataCameraTauFFCMode dataCameraTauFFCMode = new DataCameraTauFFCMode();
                dataCameraTauFFCMode.a(FFCMode.values()[cameraThermalFFCMode.ordinal()]);
                dataCameraTauFFCMode.start(new 15(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            }
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "ThermalTriggerFFC")
    public void a(e eVar) {
        if (N()) {
            new DataCameraTauTriggerFFC().start(new 16(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalSpotMeteringTargetPoint")
    public void a(ThermalSpotMeteringTargetPoint thermalSpotMeteringTargetPoint, e eVar) {
        if (!b()) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        } else if (thermalSpotMeteringTargetPoint.getX() < 0.0f || thermalSpotMeteringTargetPoint.getX() > 1.0f || thermalSpotMeteringTargetPoint.getY() < 0.0f || thermalSpotMeteringTargetPoint.getY() > 1.0f) {
            eVar.a(DJIError.COMMON_PARAM_INVALID);
        } else {
            DataCameraTauParamThermometric dataCameraTauParamThermometric = new DataCameraTauParamThermometric();
            dataCameraTauParamThermometric.a(thermalSpotMeteringTargetPoint.getX(), thermalSpotMeteringTargetPoint.getY());
            dataCameraTauParamThermometric.start(new 17(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalMeasurementMode")
    public void a(DJICameraThermalMeasurementMode dJICameraThermalMeasurementMode, e eVar) {
        if (dJICameraThermalMeasurementMode == null || dJICameraThermalMeasurementMode == DJICameraThermalMeasurementMode.Unknown) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_INVALID);
        } else {
            new DataCameraTauParamThermometricEnable().a(ThermometricType.find(dJICameraThermalMeasurementMode.value())).start(new 18(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ThermalMeasurementMode")
    public void h(e eVar) {
        DataCameraTauParamThermometricEnable dataCameraTauParamThermometricEnable = new DataCameraTauParamThermometricEnable();
        CallbackUtils.onSuccess(eVar, DJICameraThermalMeasurementMode.find(DataCameraGetPushTauParam.getInstance().getThermometricType().a()));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalSpotMeteringArea")
    public void a(RectF rectF, e eVar) {
        if (!b()) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        } else if (rectF.left < 0.0f || rectF.left > 1.0f || rectF.top < 0.0f || rectF.top > 1.0f || rectF.right < 0.0f || rectF.right > 1.0f || rectF.bottom < 0.0f || rectF.bottom > 1.0f) {
            eVar.a(DJIError.COMMON_PARAM_INVALID);
        } else {
            DataCameraTauAreaAxis dataCameraTauAreaAxis = new DataCameraTauAreaAxis();
            dataCameraTauAreaAxis.a((short) ((int) (rectF.left * o)), (short) ((int) (rectF.top * o)), (short) ((int) (rectF.right * o)), (short) ((int) (rectF.bottom * o)));
            dataCameraTauAreaAxis.start(new 19(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalCustomExternalSceneSettingsProfile")
    public void a(CameraThermalExternalParamProfile cameraThermalExternalParamProfile, e eVar) {
        if (b()) {
            if (cameraThermalExternalParamProfile != CameraThermalExternalParamProfile.Unknown) {
                DataCameraTauExterParamType dataCameraTauExterParamType = new DataCameraTauExterParamType();
                dataCameraTauExterParamType.a(ExterParamType.values()[cameraThermalExternalParamProfile.ordinal()]);
                dataCameraTauExterParamType.start(new 20(this, eVar));
            } else if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
            }
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalAtmosphericTemperature")
    public void a(short s, e eVar) {
        if (b()) {
            short s2 = (short) (p * s);
            if (s2 < (short) 5000) {
                eVar.a(DJIError.COMMON_PARAM_INVALID);
                return;
            }
            DataCameraTauExterParams dataCameraTauExterParams = new DataCameraTauExterParams();
            dataCameraTauExterParams.a(ExterParamId.e, s2);
            dataCameraTauExterParams.start(new 21(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalAtmosphericTransmissionCoefficient")
    public void b(short s, e eVar) {
        if (b()) {
            short s2 = (short) (p * s);
            if (s2 < (short) 5000 || s2 > (short) 10000) {
                eVar.a(DJIError.COMMON_PARAM_INVALID);
                return;
            }
            DataCameraTauExterParams dataCameraTauExterParams = new DataCameraTauExterParams();
            dataCameraTauExterParams.a(ExterParamId.d, s2);
            dataCameraTauExterParams.start(new 22(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalBackgroundTemperature")
    public void c(short s, e eVar) {
        if (b()) {
            short s2 = (short) (p * s);
            if (s2 < (short) 5000) {
                eVar.a(DJIError.COMMON_PARAM_INVALID);
                return;
            }
            DataCameraTauExterParams dataCameraTauExterParams = new DataCameraTauExterParams();
            dataCameraTauExterParams.a(ExterParamId.c, s2);
            dataCameraTauExterParams.start(new 24(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalSceneEmissivity")
    public void d(short s, e eVar) {
        if (b()) {
            short s2 = (short) (s * 100);
            if (s2 < (short) 5000 || s2 > (short) 10000) {
                eVar.a(DJIError.COMMON_PARAM_INVALID);
                return;
            }
            DataCameraTauExterParams dataCameraTauExterParams = new DataCameraTauExterParams();
            dataCameraTauExterParams.a(ExterParamId.b, s2);
            dataCameraTauExterParams.start(new 25(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalWindowReflection")
    public void e(short s, e eVar) {
        if (b()) {
            short s2 = (short) (p * s);
            if (s2 < (short) 0 || s2 > DataCameraGetPushTauParam.getInstance().getWindowTransmission() * p) {
                eVar.a(DJIError.COMMON_PARAM_INVALID);
                return;
            }
            DataCameraTauExterParams dataCameraTauExterParams = new DataCameraTauExterParams();
            dataCameraTauExterParams.a(ExterParamId.g, s2);
            dataCameraTauExterParams.start(new 26(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalWindowReflectedTemperature")
    public void f(short s, e eVar) {
        if (b()) {
            short s2 = (short) (p * s);
            if (s2 < (short) 5000) {
                eVar.a(DJIError.COMMON_PARAM_INVALID);
                return;
            }
            DataCameraTauExterParams dataCameraTauExterParams = new DataCameraTauExterParams();
            dataCameraTauExterParams.a(ExterParamId.i, s2);
            dataCameraTauExterParams.start(new 27(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalWindowTemperature")
    public void g(short s, e eVar) {
        if (b()) {
            short s2 = (short) (p * s);
            if (s2 < (short) 5000) {
                eVar.a(DJIError.COMMON_PARAM_INVALID);
                return;
            }
            DataCameraTauExterParams dataCameraTauExterParams = new DataCameraTauExterParams();
            dataCameraTauExterParams.a(ExterParamId.h, s2);
            dataCameraTauExterParams.start(new 28(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ThermalWindowTransmissionCoefficient")
    public void h(short s, e eVar) {
        if (b()) {
            short s2 = (short) (p * s);
            if (s2 < (short) 5000 || s2 > 10000 - (DataCameraGetPushTauParam.getInstance().getWindowReflection() * p)) {
                eVar.a(DJIError.COMMON_PARAM_INVALID);
                return;
            }
            DataCameraTauExterParams dataCameraTauExterParams = new DataCameraTauExterParams();
            dataCameraTauExterParams.a(ExterParamId.f, s);
            dataCameraTauExterParams.start(new 29(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    protected boolean a(CameraShootPhotoMode cameraShootPhotoMode) {
        if (CameraShootPhotoMode.Single == cameraShootPhotoMode || CameraShootPhotoMode.Interval == cameraShootPhotoMode) {
            return true;
        }
        return false;
    }

    private DJIError Q() {
        if (!DataCameraGetPushStateInfo.getInstance().getEnabledPhoto()) {
            return DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE;
        }
        if (DataCameraGetPushStateInfo.getInstance().getMode() != MODE.TAKEPHOTO && DataCameraGetPushStateInfo.getInstance().getMode() != MODE.RECORD) {
            return DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE;
        }
        if (DataCameraGetPushStateInfo.getInstance().getIsStoring() || DataCameraGetPushStateInfo.getInstance().getIsTimePhotoing() || DataCameraGetPushStateInfo.getInstance().getPhotoState() == PhotoState.Single) {
            return DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE;
        }
        if (DataCameraGetPushStateInfo.getInstance().getIsTimePhotoing()) {
            s = PhotoState.OTHER;
        } else if (DataCameraGetPushStateInfo.getInstance().getPhotoState() != PhotoState.Single) {
            s = DataCameraGetPushStateInfo.getInstance().getPhotoState();
        }
        return S();
    }

    private DJIError R() {
        if (DataCameraGetPushStateInfo.getInstance().getMode() != MODE.TAKEPHOTO && DataCameraGetPushStateInfo.getInstance().getMode() != MODE.RECORD) {
            return DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE;
        }
        if (DataCameraGetPushStateInfo.getInstance().getIsTimePhotoing()) {
            s = PhotoState.OTHER;
        } else {
            s = DataCameraGetPushStateInfo.getInstance().getPhotoState();
        }
        return S();
    }

    private DJIError S() {
        if (!DataCameraGetPushStateInfo.getInstance().getSDCardInsertState()) {
            return DJICameraError.CAMERA_NO_SDCARD;
        }
        switch (35.b[DataCameraGetPushStateInfo.getInstance().getSDCardState().ordinal()]) {
            case 1:
                return null;
            case 2:
                return DJICameraError.CAMERASDCARDFULL;
            case 3:
                return DJICameraError.CAMERA_NO_SDCARD;
            default:
                return DJICameraError.CAMERA_SDCARD_ERROR;
        }
    }

    private DJIError T() {
        if (DataCameraGetPushStateInfo.getInstance().getMode() != MODE.RECORD) {
            return DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE;
        }
        if (DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.START || DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.STARTING || DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.STOP) {
            return DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE;
        }
        t = DataCameraGetPushStateInfo.getInstance().getRecordState();
        return S();
    }

    private DJIError U() {
        if (DataCameraGetPushStateInfo.getInstance().getMode() != MODE.RECORD) {
            return DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE;
        }
        if (DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.NO || DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.STOP) {
            return DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE;
        }
        t = DataCameraGetPushStateInfo.getInstance().getRecordState();
        return S();
    }

    private static void V() {
        b bVar = q;
        if (b.c() == a.a && s == PhotoState.NO) {
            if (DataCameraGetPushStateInfo.getInstance().getIsTimePhotoing()) {
                if (ShootPhotoTimeoutLock.getInstance().getThreadInitiatedState()) {
                    ShootPhotoTimeoutLock.getInstance().setResult(true);
                } else {
                    q.a();
                }
                s = PhotoState.OTHER;
            } else if (DataCameraGetPushStateInfo.getInstance().getPhotoState() == PhotoState.Single) {
                if (ShootPhotoTimeoutLock.getInstance().getThreadInitiatedState()) {
                    ShootPhotoTimeoutLock.getInstance().setResult(true);
                } else {
                    q.a();
                }
                s = PhotoState.Single;
            }
        }
        bVar = q;
        if (b.c() == a.b && s != PhotoState.NO) {
            if (s == PhotoState.OTHER) {
                if (!DataCameraGetPushStateInfo.getInstance().getIsTimePhotoing()) {
                    if (ShootPhotoTimeoutLock.getInstance().getThreadInitiatedState()) {
                        ShootPhotoTimeoutLock.getInstance().setResult(true);
                    }
                    q.b();
                }
            } else if (DataCameraGetPushStateInfo.getInstance().getPhotoState() != PhotoState.Single) {
                if (ShootPhotoTimeoutLock.getInstance().getThreadInitiatedState()) {
                    ShootPhotoTimeoutLock.getInstance().setResult(true);
                }
                q.b();
            }
        }
        if (DataCameraGetPushStateInfo.getInstance().getIsTimePhotoing()) {
            s = PhotoState.OTHER;
        } else {
            s = DataCameraGetPushStateInfo.getInstance().getPhotoState();
        }
    }

    private void W() {
        DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
        if (c.c() == a.a) {
            if (t != RecordType.NO) {
                return;
            }
            if (instance.getRecordState() == RecordType.STARTING || instance.getRecordState() == RecordType.START || instance.getRecordState() == RecordType.STOP) {
                if (RecordVideoTimeoutLock.getInstance().getThreadInitiatedState()) {
                    RecordVideoTimeoutLock.getInstance().setResult(true);
                }
                c.getInstance().a();
                t = instance.getRecordState();
            }
        } else if (instance.getRecordState() != RecordType.START && instance.getRecordState() != RecordType.STARTING && instance.getRecordState() != RecordType.STOP) {
            if (RecordVideoTimeoutLock.getInstance().getThreadInitiatedState()) {
                RecordVideoTimeoutLock.getInstance().setResult(true);
            }
            c.getInstance().b();
            t = RecordType.NO;
        }
    }

    private CameraVideoStandard a(int i, int i2) {
        if (i == 0 && i2 == 3) {
            return CameraVideoStandard.NTSC;
        }
        if (i == 26 && i2 == 2) {
            return CameraVideoStandard.PAL;
        }
        return CameraVideoStandard.Unknown;
    }

    private CameraPhotoIntervalParam a(int i) {
        CameraPhotoIntervalParam cameraPhotoIntervalParam = new CameraPhotoIntervalParam();
        cameraPhotoIntervalParam.captureCount = 255;
        cameraPhotoIntervalParam.timeIntervalInSeconds = i;
        return cameraPhotoIntervalParam;
    }

    private CameraThermalProfile a(int i, int i2, int i3) {
        CameraThermalProfile cameraThermalProfile = new CameraThermalProfile();
        cameraThermalProfile.focalLength = CameraThermalLensFocalLength.values()[i];
        cameraThermalProfile.frameRateUpperBound = CameraThermalFrameRateUpperBound.values()[i3];
        switch (i2) {
            case 0:
                cameraThermalProfile.thermalResolution = CameraThermalResolution.Resolution_640x512;
                break;
            case 1:
                cameraThermalProfile.thermalResolution = CameraThermalResolution.Resolution_336x256;
                break;
            default:
                cameraThermalProfile.thermalResolution = CameraThermalResolution.Unknown;
                break;
        }
        return cameraThermalProfile;
    }

    private CameraThermalDigitalZoomScale b(int i) {
        if (i == 100) {
            return CameraThermalDigitalZoomScale.x1;
        }
        if (i == 200) {
            return CameraThermalDigitalZoomScale.x2;
        }
        if (i == 400) {
            return CameraThermalDigitalZoomScale.x4;
        }
        if (i != dji.gs.c.e.g) {
            return CameraThermalDigitalZoomScale.Unknown;
        }
        if (DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeTau640) {
            return CameraThermalDigitalZoomScale.x8;
        }
        return CameraThermalDigitalZoomScale.x4;
    }

    private CameraThermalPalette c(int i) {
        switch (i) {
            case 24:
                i = 0;
                break;
            case 25:
                i = 1;
                break;
            case 26:
                i = 2;
                break;
            case 27:
                i = 3;
                break;
            case 28:
                i = 4;
                break;
            case 29:
                i = 5;
                break;
            case 30:
                i = 6;
                break;
            case 31:
                i = 7;
                break;
            case 32:
                i = 8;
                break;
            case 33:
                i = 9;
                break;
            case 34:
                i = 10;
                break;
            case 35:
                i = 11;
                break;
            case 36:
                i = 12;
                break;
            case 37:
                i = 13;
                break;
        }
        return CameraThermalPalette.values()[i];
    }

    private CameraMode d(int i) {
        switch (i) {
            case 6:
                i = 2;
                break;
            case 7:
                i = 4;
                break;
        }
        return CameraMode.find(i);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartShootPhoto")
    public void a(e eVar, CameraShootPhotoMode cameraShootPhotoMode) {
        if (a(cameraShootPhotoMode)) {
            DJIError Q = Q();
            if (Q == null) {
                b bVar = q;
                if (b.c() == a.b) {
                    eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
                    return;
                } else {
                    new 30(this, cameraShootPhotoMode, eVar).start();
                    return;
                }
            } else if (eVar != null) {
                eVar.a(Q);
                return;
            } else {
                return;
            }
        }
        eVar.a(DJICameraError.CAMERA_INVALID_PARAM);
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StopShootPhoto")
    public void i(e eVar) {
        DJIError R = R();
        if (R == null) {
            b bVar = q;
            if (b.c() != a.a) {
                new 31(this, eVar).start();
            } else if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
            }
        } else if (eVar != null) {
            eVar.a(R);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StartRecordVideo")
    public void j(e eVar) {
        DJIError T = T();
        if (T == null) {
            c cVar = r;
            if (c.c() == a.a) {
                if (DataCameraGetPushStateInfo.getInstance().getRecordState() != RecordType.STARTING || DataCameraGetPushStateInfo.getInstance().getRecordState() != RecordType.START) {
                    new 32(this, eVar).start();
                }
            } else if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
            }
        } else if (eVar != null) {
            eVar.a(T);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "StopRecordVideo")
    public void k(e eVar) {
        DJIError U = U();
        if (U == null) {
            c cVar = r;
            if (c.c() == a.b) {
                if (DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.STARTING || DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.START) {
                    new 33(this, eVar).start();
                }
            } else if (eVar != null) {
                eVar.a(DJICameraError.CAMERA_UNSUPPORTED_CMD_STATE);
            }
        } else if (eVar != null) {
            eVar.a(U);
        }
    }

    private boolean a(CameraPhotoFileFormat cameraPhotoFileFormat) {
        boolean z = DataCameraGetPushStateInfo.getInstance().getVerstion() >= 3;
        boolean supportSpotThermometric = DataCameraGetPushTauParam.getInstance().supportSpotThermometric();
        switch (35.c[cameraPhotoFileFormat.ordinal()]) {
            case 1:
            case 2:
                return true;
            case 3:
                return z;
            case 4:
            case 5:
                return supportSpotThermometric;
            default:
                return false;
        }
    }

    private CameraPhotoFileFormat e(int i) {
        switch (i) {
            case 0:
            case 4:
                return CameraPhotoFileFormat.TIFF14Bit;
            case 1:
                return CameraPhotoFileFormat.JPEG;
            case 5:
                return CameraPhotoFileFormat.TIFF14BitLinearLowTempResolution;
            case 6:
                return CameraPhotoFileFormat.TIFF14BitLinearHighTempResolution;
            case 7:
            case 8:
            case 9:
                return CameraPhotoFileFormat.RadiometricJPEG;
            default:
                return CameraPhotoFileFormat.Unknown;
        }
    }

    private int b(CameraPhotoFileFormat cameraPhotoFileFormat) {
        switch (35.c[cameraPhotoFileFormat.ordinal()]) {
            case 2:
                return 4;
            case 3:
                return 7;
            case 4:
                return 8;
            case 5:
                return 9;
            default:
                return 1;
        }
    }
}
