package dji.common.camera;

import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;

public class CameraVideoResolutionAndFrameRate implements Comparable<CameraVideoResolutionAndFrameRate> {
    private CameraVideoFrameRate frameRate;
    private CameraVideoResolution resolution;

    public CameraVideoResolutionAndFrameRate(CameraVideoResolution cameraVideoResolution, CameraVideoFrameRate cameraVideoFrameRate) {
        this.resolution = cameraVideoResolution;
        this.frameRate = cameraVideoFrameRate;
    }

    public void setResolution(CameraVideoResolution cameraVideoResolution) {
        this.resolution = cameraVideoResolution;
    }

    public void setFrameRate(CameraVideoFrameRate cameraVideoFrameRate) {
        this.frameRate = cameraVideoFrameRate;
    }

    public CameraVideoResolution getResolution() {
        return this.resolution;
    }

    public CameraVideoFrameRate getFrameRate() {
        return this.frameRate;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate = (CameraVideoResolutionAndFrameRate) obj;
        if (this.resolution != cameraVideoResolutionAndFrameRate.resolution) {
            return false;
        }
        if (this.frameRate != cameraVideoResolutionAndFrameRate.frameRate) {
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

    public int compareTo(CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate) {
        if (this.resolution.value() > cameraVideoResolutionAndFrameRate.resolution.value()) {
            return 1;
        }
        if (this.resolution.value() < cameraVideoResolutionAndFrameRate.resolution.value()) {
            return -1;
        }
        return 0;
    }
}
