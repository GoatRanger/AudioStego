package dji.common.util;

import dji.common.camera.CameraSSDRawVideoResolutionAndFrameRate;
import dji.common.camera.CameraVideoResolutionAndFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;

public class DJICameraEnumMappingUtil {
    public CameraVideoResolutionAndFrameRate wrapCameraVideoResolutionAndFrameRate(int i, int i2) {
        CameraVideoResolution mapProtocolToResolution = mapProtocolToResolution(i);
        CameraVideoFrameRate mapProtocolToFrameRate = mapProtocolToFrameRate(i2);
        CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate = new CameraVideoResolutionAndFrameRate();
        cameraVideoResolutionAndFrameRate.setFrameRate(mapProtocolToFrameRate);
        cameraVideoResolutionAndFrameRate.setResolution(mapProtocolToResolution);
        return cameraVideoResolutionAndFrameRate;
    }

    public CameraVideoResolution mapProtocolToResolution(int i) {
        CameraVideoResolution cameraVideoResolution = CameraVideoResolution.Unknown;
        switch (i) {
            case 4:
                return CameraVideoResolution.Resolution_1280x720;
            case 10:
                return CameraVideoResolution.Resolution_1920x1080;
            case 16:
                return CameraVideoResolution.Resolution_3840x2160;
            case 22:
                return CameraVideoResolution.Resolution_4096x2160;
            case 24:
                return CameraVideoResolution.Resolution_2704X1520;
            case 31:
                return CameraVideoResolution.Resolution_2720x1530;
            default:
                return cameraVideoResolution;
        }
    }

    public CameraVideoFrameRate mapProtocolToFrameRate(int i) {
        CameraVideoFrameRate cameraVideoFrameRate = CameraVideoFrameRate.Unknown;
        switch (i) {
            case 1:
                return CameraVideoFrameRate.FrameRate_24fps;
            case 2:
                return CameraVideoFrameRate.FrameRate_25fps;
            case 3:
                return CameraVideoFrameRate.FrameRate_30fps;
            case 4:
                return CameraVideoFrameRate.FrameRate_48fps;
            case 5:
                return CameraVideoFrameRate.FrameRate_50fps;
            case 6:
                return CameraVideoFrameRate.FrameRate_60fps;
            case 7:
                return CameraVideoFrameRate.FrameRate_120fps;
            case 11:
                return CameraVideoFrameRate.FrameRate_96fps;
            default:
                return cameraVideoFrameRate;
        }
    }

    public CameraSSDRawVideoResolutionAndFrameRate mapResolutionAndFrameRateToSSD(CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate) {
        CameraSSDRawVideoResolutionAndFrameRate cameraSSDRawVideoResolutionAndFrameRate = new CameraSSDRawVideoResolutionAndFrameRate();
        cameraSSDRawVideoResolutionAndFrameRate.setFrameRate(cameraVideoResolutionAndFrameRate.getFrameRate());
        cameraSSDRawVideoResolutionAndFrameRate.setResolution(cameraVideoResolutionAndFrameRate.getResolution());
        return cameraSSDRawVideoResolutionAndFrameRate;
    }
}
