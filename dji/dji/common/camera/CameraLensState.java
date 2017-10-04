package dji.common.camera;

import dji.common.camera.DJICameraSettingsDef.CameraLensFocusMode;
import dji.common.camera.DJICameraSettingsDef.CameraLensFocusStatus;
import dji.common.camera.DJICameraSettingsDef.CameraLensType;

public class CameraLensState {
    private CameraLensFocusMode focusMode;
    private CameraLensFocusStatus focusStatus;
    private CameraLensType lensType;
    private boolean mIsAFSwitchOn;
    private boolean mIsFocusAssistantEnabledForAF;
    private boolean mIsFocusAssistantEnabledForMF;
    private boolean mIsFocusAssistantWorking;
    private boolean mIsLensDetected;

    public boolean isFocusAssistantEnabledForMF() {
        return this.mIsFocusAssistantEnabledForMF;
    }

    public void setFocusAssistantEnabledForMF(boolean z) {
        this.mIsFocusAssistantEnabledForMF = z;
    }

    public boolean isFocusAssistantEnabledForAF() {
        return this.mIsFocusAssistantEnabledForAF;
    }

    public void setFocusAssistantEnabledForAF(boolean z) {
        this.mIsFocusAssistantEnabledForAF = z;
    }

    public boolean isFocusAssistantWorking() {
        return this.mIsFocusAssistantWorking;
    }

    public void setFocusAssistantWorking(boolean z) {
        this.mIsFocusAssistantWorking = z;
    }

    public boolean isLensInstalled() {
        return this.mIsLensDetected;
    }

    public void setLensDetected(boolean z) {
        this.mIsLensDetected = z;
    }

    public CameraLensType getLensType() {
        return this.lensType;
    }

    public void setLensType(CameraLensType cameraLensType) {
        this.lensType = cameraLensType;
    }

    public boolean isAFSwitchOn() {
        return this.mIsAFSwitchOn;
    }

    public void setAFSwitchOn(boolean z) {
        this.mIsAFSwitchOn = z;
    }

    public CameraLensFocusStatus getFocusStatus() {
        return this.focusStatus;
    }

    public void setFocusStatus(CameraLensFocusStatus cameraLensFocusStatus) {
        this.focusStatus = cameraLensFocusStatus;
    }

    public CameraLensFocusMode getFocusMode() {
        return this.focusMode;
    }

    public void setFocusMode(CameraLensFocusMode cameraLensFocusMode) {
        this.focusMode = cameraLensFocusMode;
    }
}
