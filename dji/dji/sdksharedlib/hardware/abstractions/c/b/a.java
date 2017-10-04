package dji.sdksharedlib.hardware.abstractions.c.b;

import dji.common.camera.CameraTapZoomTargetPoint;
import dji.common.camera.CameraUtils;
import dji.common.camera.CameraVideoResolutionAndFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraAntiFlicker;
import dji.common.camera.DJICameraSettingsDef.CameraAperture;
import dji.common.camera.DJICameraSettingsDef.CameraContrast;
import dji.common.camera.DJICameraSettingsDef.CameraDigitalFilter;
import dji.common.camera.DJICameraSettingsDef.CameraMode;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoAspectRatio;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoBurstCount;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoFileFormat;
import dji.common.camera.DJICameraSettingsDef.CameraPhotoIntervalParam;
import dji.common.camera.DJICameraSettingsDef.CameraSharpness;
import dji.common.camera.DJICameraSettingsDef.CameraShootPhotoMode;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFileFormat;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;
import dji.common.camera.DJICameraSettingsDef.CameraWhiteBalance;
import dji.common.error.DJICameraError;
import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.common.util.DJILensFeatureUtils;
import dji.midware.data.model.P3.DataCameraGetDefogEnabled;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushTapZoomStateInfo;
import dji.midware.data.model.P3.DataCameraGetTapZoom;
import dji.midware.data.model.P3.DataCameraSetDefogEnabled;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.OpticsZommMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.ZoomSpeed;
import dji.midware.data.model.P3.DataCameraSetTapZoom;
import dji.midware.data.model.P3.DataCameraSetTapZoomTarget;
import dji.sdksharedlib.b.b;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.b.f;
import dji.sdksharedlib.hardware.abstractions.c.d.k;

public class a extends k implements d {
    private boolean H = false;

    public void a(String str, int i, c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        this.t = CameraUtils.buildApertureMap();
        this.s = new DJILensFeatureUtils();
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        c();
        dji.sdksharedlib.a.a.b((d) this, b.cc);
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (aVar2 != null && aVar2.e() != null && cVar.f().equals(b.cc)) {
            this.H = ((Boolean) aVar2.e()).booleanValue();
        }
    }

    protected void c() {
        super.c();
        onEventBackgroundThread(DataCameraGetPushTapZoomStateInfo.getInstance());
        onEventBackgroundThread(DataCameraGetPushShotParams.getInstance());
    }

    protected boolean E() {
        return false;
    }

    protected boolean F() {
        return true;
    }

    protected boolean A() {
        return true;
    }

    protected boolean L() {
        return true;
    }

    protected boolean K() {
        return true;
    }

    protected boolean q() {
        return true;
    }

    protected boolean r() {
        return true;
    }

    protected boolean G() {
        return true;
    }

    protected boolean p() {
        return true;
    }

    protected boolean v() {
        return false;
    }

    protected String M() {
        return dji.sdksharedlib.hardware.abstractions.c.a.m;
    }

    public void onEventBackgroundThread(DataCameraGetPushTapZoomStateInfo dataCameraGetPushTapZoomStateInfo) {
        if (dataCameraGetPushTapZoomStateInfo.isGetted()) {
            a(Integer.valueOf(dataCameraGetPushTapZoomStateInfo.getMultiplier()), c(b.ce));
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a(Float.valueOf((float) (((double) dataCameraGetPushShotParams.getOpticsScale()) / 100.0d)), c(b.ch));
        a(Float.valueOf((float) (((double) dataCameraGetPushShotParams.getDigitalZoomScale()) / 100.0d)), c(b.Q));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "CameraMode")
    public void a(CameraMode cameraMode, e eVar) {
        int value = cameraMode.value();
        if (cameraMode == CameraMode.MediaDownload) {
            value = 7;
        }
        if (cameraMode == CameraMode.Playback) {
            value = 6;
        }
        a(value, eVar);
    }

    protected boolean a(CameraAperture cameraAperture) {
        return true;
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "TapZoomTarget")
    public void a(CameraTapZoomTargetPoint cameraTapZoomTargetPoint, e eVar) {
        float x = cameraTapZoomTargetPoint.getX();
        DataCameraSetTapZoomTarget.getInstance().a(x).b(cameraTapZoomTargetPoint.getY()).start(CallbackUtils.getSetterDJIDataCallback(eVar));
    }

    protected boolean a(CameraWhiteBalance cameraWhiteBalance, int i) {
        if (cameraWhiteBalance == CameraWhiteBalance.WaterSuface || cameraWhiteBalance == CameraWhiteBalance.IndoorFluorescent) {
            return false;
        }
        return super.a(cameraWhiteBalance, i);
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "TapZoomEnabled")
    public void g(boolean z, e eVar) {
        DataCameraSetTapZoom.getInstance().a(z).start(CallbackUtils.getSetterDJIDataCallback(eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "TapZoomEnabled")
    public void x(e eVar) {
        DataCameraGetTapZoom.getInstance().start(new 1(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "TapZoomMultiplier")
    public void i(int i, e eVar) {
        if (i >= 1 && i <= 5) {
            DataCameraSetTapZoom.getInstance().a(i).start(CallbackUtils.getSetterDJIDataCallback(eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "TapZoomMultiplier")
    public void y(e eVar) {
        if (eVar == null) {
            return;
        }
        if (this.H) {
            DataCameraGetTapZoom.getInstance().start(new 2(this, eVar));
        } else {
            eVar.a(DJIError.COMMON_SYSTEM_BUSY);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "DefogEnabled")
    public void h(boolean z, e eVar) {
        DataCameraSetDefogEnabled.getInstance().a(z).start(CallbackUtils.getSetterDJIDataCallback(eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "DefogEnabled")
    public void z(e eVar) {
        if (eVar != null) {
            DataCameraGetDefogEnabled.getInstance().start(new 3(this, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "OpticalZoomScale")
    public void A(e eVar) {
        CallbackUtils.onSuccess(eVar, Integer.valueOf(DataCameraGetPushShotParams.getInstance().getOpticsScale()));
    }

    public void a(CameraPhotoIntervalParam cameraPhotoIntervalParam, e eVar) {
        if (cameraPhotoIntervalParam.captureCount == 255) {
            super.a(cameraPhotoIntervalParam, eVar);
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void a(CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate, e eVar) {
        CameraVideoResolution resolution = cameraVideoResolutionAndFrameRate.getResolution();
        CameraVideoFrameRate frameRate = cameraVideoResolutionAndFrameRate.getFrameRate();
        if (resolution != CameraVideoResolution.Resolution_1920x1080) {
            if (eVar != null) {
                eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
            }
        } else if (frameRate == CameraVideoFrameRate.FrameRate_25fps || frameRate == CameraVideoFrameRate.FrameRate_30fps) {
            super.a(cameraVideoResolutionAndFrameRate, eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void a(CameraPhotoAspectRatio cameraPhotoAspectRatio, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(CameraPhotoFileFormat cameraPhotoFileFormat, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(CameraPhotoBurstCount cameraPhotoBurstCount, e eVar) {
        if (cameraPhotoBurstCount == CameraPhotoBurstCount.BurstCount_3 || cameraPhotoBurstCount == CameraPhotoBurstCount.BurstCount_5) {
            super.a(cameraPhotoBurstCount, eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void a(CameraAntiFlicker cameraAntiFlicker, e eVar) {
        if (cameraAntiFlicker != CameraAntiFlicker.Auto) {
            super.a(cameraAntiFlicker, eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    protected boolean a(CameraShootPhotoMode cameraShootPhotoMode) {
        if (cameraShootPhotoMode == CameraShootPhotoMode.Single || cameraShootPhotoMode == CameraShootPhotoMode.Interval || cameraShootPhotoMode == CameraShootPhotoMode.Burst) {
            return true;
        }
        return false;
    }

    public void a(CameraDigitalFilter cameraDigitalFilter, e eVar) {
        if (cameraDigitalFilter == CameraDigitalFilter.None || cameraDigitalFilter == CameraDigitalFilter.Inverse || cameraDigitalFilter == CameraDigitalFilter.BlackAndWhite) {
            super.a(cameraDigitalFilter, eVar);
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void a(CameraVideoFileFormat cameraVideoFileFormat, e eVar) {
        if (cameraVideoFileFormat == CameraVideoFileFormat.MOV || cameraVideoFileFormat == CameraVideoFileFormat.MP4) {
            super.a(cameraVideoFileFormat, eVar);
        } else if (eVar != null) {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void a(CameraSharpness cameraSharpness, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void a(CameraContrast cameraContrast, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void b(int i, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    public void c(int i, e eVar) {
        if (eVar != null) {
            eVar.a(DJIError.COMMON_UNSUPPORTED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "OneKeyZoom")
    public void B(e eVar) {
        new DataCameraSetOpticsZoomMode().a(OpticsZommMode.SETZOOM, ZoomSpeed.FASTEST, 0, 0).start(new 4(this, eVar));
    }
}
