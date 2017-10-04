package dji.sdksharedlib.hardware.abstractions.c.d;

import android.os.Handler;
import android.os.Looper;
import dji.common.camera.CameraVideoResolutionAndFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraDigitalFilter;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;
import dji.common.error.DJICameraError;
import dji.common.error.DJIError;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.c.a;
import dji.sdksharedlib.hardware.abstractions.c.b;
import dji.sdksharedlib.hardware.abstractions.f;

public class d extends b {
    private Handler H = new Handler(Looper.getMainLooper());

    protected void a() {
        a(dji.sdksharedlib.b.b.class, getClass());
    }

    protected boolean E() {
        return false;
    }

    protected boolean v() {
        return true;
    }

    protected boolean F() {
        return true;
    }

    protected boolean s() {
        return true;
    }

    @f(a = "DigitalFilter")
    public void a(CameraDigitalFilter cameraDigitalFilter, e eVar) {
        if (cameraDigitalFilter != CameraDigitalFilter.Unknown) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a(dji.sdksharedlib.b.b.w);
            dataBaseCameraSetting.a(cameraDigitalFilter.value());
            dataBaseCameraSetting.start(new 1(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    protected boolean y() {
        return true;
    }

    protected boolean u() {
        return true;
    }

    protected boolean w() {
        return true;
    }

    @f(a = "VideoResolutionAndFrameRate")
    public void a(CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate, e eVar) {
        CameraVideoResolution resolution = cameraVideoResolutionAndFrameRate.getResolution();
        CameraVideoFrameRate frameRate = cameraVideoResolutionAndFrameRate.getFrameRate();
        if (resolution == CameraVideoResolution.Resolution_1920x1080 && frameRate == CameraVideoFrameRate.FrameRate_120fps) {
            e(true, eVar);
        } else {
            super.a(cameraVideoResolutionAndFrameRate, new 2(this, cameraVideoResolutionAndFrameRate, eVar));
        }
    }

    protected String M() {
        return a.g;
    }

    public DJIError Q() {
        if (DataCameraGetPushStateInfo.getInstance().getVerstion() < 4) {
            return DJICameraError.COMMAND_NOT_SUPPORTED_BY_FIRMWARE;
        }
        return super.Q();
    }

    protected boolean t() {
        return true;
    }
}
