package dji.common.camera;

import dji.common.camera.DJICameraSettingsDef.CameraMode;

public class CameraSystemState {
    private CameraMode cameraMode;
    private int currentVideoRecordingTimeInSeconds;
    private boolean isCameraError;
    private boolean isCameraOverHeated;
    private boolean isPhotoStoring;
    private boolean isRecording;
    private boolean isShootingBurstPhoto;
    private boolean isShootingIntervalPhoto;
    private boolean isShootingRawPhoto;
    private boolean isShootingSinglePhoto;
    private boolean isUSBMode;

    public boolean isShootingSinglePhoto() {
        return this.isShootingSinglePhoto;
    }

    public void setShootingSinglePhoto(boolean z) {
        this.isShootingSinglePhoto = z;
    }

    public boolean isShootingSinglePhotoInRAWFormat() {
        return this.isShootingRawPhoto;
    }

    public void setShootingRawPhoto(boolean z) {
        this.isShootingRawPhoto = z;
    }

    public boolean isShootingIntervalPhoto() {
        return this.isShootingIntervalPhoto;
    }

    public void setShootingIntervalPhoto(boolean z) {
        this.isShootingIntervalPhoto = z;
    }

    public boolean isShootingBurstPhoto() {
        return this.isShootingBurstPhoto;
    }

    public void setShootingBurstPhotos(boolean z) {
        this.isShootingBurstPhoto = z;
    }

    public boolean isStoringPhoto() {
        return this.isPhotoStoring;
    }

    public void setPhotoStoring(boolean z) {
        this.isPhotoStoring = z;
    }

    public boolean isRecording() {
        return this.isRecording;
    }

    public void setRecording(boolean z) {
        this.isRecording = z;
    }

    public boolean isCameraOverHeated() {
        return this.isCameraOverHeated;
    }

    public void setCameraOverHeated(boolean z) {
        this.isCameraOverHeated = z;
    }

    public boolean isCameraError() {
        return this.isCameraError;
    }

    public void setCameraError(boolean z) {
        this.isCameraError = z;
    }

    public boolean isUSBMode() {
        return this.isUSBMode;
    }

    public void setUSBMode(boolean z) {
        this.isUSBMode = z;
    }

    public CameraMode getCameraMode() {
        return this.cameraMode;
    }

    public void setCameraMode(CameraMode cameraMode) {
        this.cameraMode = cameraMode;
    }

    public int getCurrentVideoRecordingTimeInSeconds() {
        return this.currentVideoRecordingTimeInSeconds;
    }

    public void setCurrentVideoRecordingTimeInSeconds(int i) {
        this.currentVideoRecordingTimeInSeconds = i;
    }
}
