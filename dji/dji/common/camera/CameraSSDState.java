package dji.common.camera;

import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;

public class CameraSSDState {
    private CameraSSDCapacity capacity;
    private boolean isConnected;
    private long remainingCapacity;
    private int remainingTime;
    private CameraSSDOperationState ssdState;
    private CameraVideoFrameRate videoFrameRate;
    private CameraVideoResolution videoResolution;

    public CameraSSDOperationState getSSDOperationState() {
        return this.ssdState;
    }

    public void setSsdState(CameraSSDOperationState cameraSSDOperationState) {
        this.ssdState = cameraSSDOperationState;
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    public void setIsConnected(boolean z) {
        this.isConnected = z;
    }

    public CameraSSDCapacity getTotalSpace() {
        return this.capacity;
    }

    public void setCapacity(CameraSSDCapacity cameraSSDCapacity) {
        this.capacity = cameraSSDCapacity;
    }

    public int getAvailableRecordingTimeInSeconds() {
        return this.remainingTime;
    }

    public void setRemainingTime(int i) {
        this.remainingTime = i;
    }

    public long getRemainingSpaceInMegaBytes() {
        return this.remainingCapacity;
    }

    public void setRemainingCapacity(long j) {
        this.remainingCapacity = j;
    }

    public CameraVideoResolution getVideoResolution() {
        return this.videoResolution;
    }

    public void setVideoResolution(CameraVideoResolution cameraVideoResolution) {
        this.videoResolution = cameraVideoResolution;
    }

    public CameraVideoFrameRate getVideoFrameRate() {
        return this.videoFrameRate;
    }

    public void setVideoFrameRate(CameraVideoFrameRate cameraVideoFrameRate) {
        this.videoFrameRate = cameraVideoFrameRate;
    }
}
