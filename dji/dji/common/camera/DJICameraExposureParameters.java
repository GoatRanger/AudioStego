package dji.common.camera;

import dji.common.camera.DJICameraSettingsDef.CameraAperture;
import dji.common.camera.DJICameraSettingsDef.CameraExposureCompensation;
import dji.common.camera.DJICameraSettingsDef.CameraISO;
import dji.common.camera.DJICameraSettingsDef.CameraShutterSpeed;

public class DJICameraExposureParameters {
    private CameraAperture aerture;
    private CameraISO cameraISO;
    private CameraExposureCompensation exposureCompensation;
    private CameraShutterSpeed shutterSpeed;

    public void setAperture(CameraAperture cameraAperture) {
        this.aerture = cameraAperture;
    }

    public void setShutterSpeed(CameraShutterSpeed cameraShutterSpeed) {
        this.shutterSpeed = cameraShutterSpeed;
    }

    public void setISO(CameraISO cameraISO) {
        this.cameraISO = cameraISO;
    }

    public void setExposureCompensation(CameraExposureCompensation cameraExposureCompensation) {
        this.exposureCompensation = cameraExposureCompensation;
    }

    public CameraAperture getAperture() {
        return this.aerture;
    }

    public CameraShutterSpeed getShutterSpeed() {
        return this.shutterSpeed;
    }

    public CameraISO getISO() {
        return this.cameraISO;
    }

    public CameraExposureCompensation getExposureCompensation() {
        return this.exposureCompensation;
    }
}
