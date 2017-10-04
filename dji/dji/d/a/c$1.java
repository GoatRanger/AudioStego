package dji.d.a;

import dji.common.camera.CameraVideoResolutionAndFrameRate;
import dji.common.camera.CameraWhiteBalanceAndColorTemperature;
import dji.common.camera.DJICameraSettingsDef.CameraAperture;
import dji.common.camera.DJICameraSettingsDef.CameraExposureCompensation;
import dji.common.camera.DJICameraSettingsDef.CameraISO;
import dji.common.camera.DJICameraSettingsDef.CameraShutterSpeed;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;
import dji.sdksharedlib.b.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class c$1 implements o<Long, d<Boolean>> {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public d<Boolean> a(Long l) {
        c.a(this.a);
        c.a(this.a, CameraISO.values()[c.b(this.a) % 11], this.a.a("ISO"));
        c.b(this.a, CameraAperture.values()[c.b(this.a) % 25], this.a.a(b.D));
        c.c(this.a, CameraExposureCompensation.values()[c.b(this.a) % 32], this.a.a(b.J));
        c.d(this.a, CameraShutterSpeed.values()[c.b(this.a) % 51], this.a.a(b.F));
        CameraWhiteBalanceAndColorTemperature cameraWhiteBalanceAndColorTemperature = new CameraWhiteBalanceAndColorTemperature();
        cameraWhiteBalanceAndColorTemperature.setColorTemperature((c.b(this.a) % 50) * 100);
        c.e(this.a, cameraWhiteBalanceAndColorTemperature, this.a.a(b.o));
        CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate = new CameraVideoResolutionAndFrameRate();
        cameraVideoResolutionAndFrameRate.setFrameRate(CameraVideoFrameRate.values()[c.b(this.a) % 8]);
        cameraVideoResolutionAndFrameRate.setResolution(CameraVideoResolution.values()[c.b(this.a) % 6]);
        c.f(this.a, cameraVideoResolutionAndFrameRate, this.a.a(b.d));
        c.g(this.a, Integer.valueOf(1000 - (c.b(this.a) % 1000)), this.a.a(b.aU));
        c.a(this.a, Boolean.valueOf(true), b.bt);
        return d.b(Boolean.valueOf(true));
    }
}
