package dji.common.camera;

import dji.common.camera.DJICameraSettingsDef.CameraWhiteBalance;

public class CameraWhiteBalanceAndColorTemperature {
    private int colorTemperature;
    private CameraWhiteBalance whiteBalance;

    public void setWhiteBalance(CameraWhiteBalance cameraWhiteBalance) {
        this.whiteBalance = cameraWhiteBalance;
    }

    public void setColorTemperature(int i) {
        this.colorTemperature = i;
    }

    public CameraWhiteBalance getWhiteBalance() {
        return this.whiteBalance;
    }

    public int getColorTemperature() {
        return this.colorTemperature;
    }
}
