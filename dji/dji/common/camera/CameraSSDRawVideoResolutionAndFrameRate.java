package dji.common.camera;

import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;

public class CameraSSDRawVideoResolutionAndFrameRate implements Comparable<CameraSSDRawVideoResolutionAndFrameRate> {
    private CameraVideoFrameRate frameRate;
    private CameraVideoResolution resolution;

    public void setFrameRate(CameraVideoFrameRate cameraVideoFrameRate) {
        this.frameRate = cameraVideoFrameRate;
    }

    public void setResolution(CameraVideoResolution cameraVideoResolution) {
        this.resolution = cameraVideoResolution;
    }

    public CameraVideoFrameRate getFrameRate() {
        return this.frameRate;
    }

    public CameraVideoResolution getResolution() {
        return this.resolution;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CameraSSDRawVideoResolutionAndFrameRate cameraSSDRawVideoResolutionAndFrameRate = (CameraSSDRawVideoResolutionAndFrameRate) obj;
        if (this.resolution != cameraSSDRawVideoResolutionAndFrameRate.resolution) {
            return false;
        }
        if (this.frameRate != cameraSSDRawVideoResolutionAndFrameRate.frameRate) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.resolution.hashCode() * 31) + this.frameRate.hashCode();
    }

    public String toString() {
        return "CameraVideoResolutionAndFrameRate{resolution=" + this.resolution + ", frameRate=" + this.frameRate + '}';
    }

    public int compareTo(CameraSSDRawVideoResolutionAndFrameRate cameraSSDRawVideoResolutionAndFrameRate) {
        if (this.resolution.value() > cameraSSDRawVideoResolutionAndFrameRate.resolution.value()) {
            return 1;
        }
        if (this.resolution.value() < cameraSSDRawVideoResolutionAndFrameRate.resolution.value()) {
            return -1;
        }
        return 0;
    }
}
