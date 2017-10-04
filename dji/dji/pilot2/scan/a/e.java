package dji.pilot2.scan.a;

import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Log;

public final class e {
    private static final String a = e.class.getName();

    private e() {
    }

    @SuppressLint({"NewApi"})
    public static Camera a(int i) {
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras == 0) {
            Log.w(a, "No cameras!");
            return null;
        }
        int i2 = i >= 0 ? 1 : 0;
        if (i2 == 0) {
            i = 0;
            while (i < numberOfCameras) {
                CameraInfo cameraInfo = new CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == 0) {
                    break;
                }
                i++;
            }
        }
        if (i < numberOfCameras) {
            Log.i(a, "Opening camera #" + i);
            return Camera.open(i);
        } else if (i2 != 0) {
            Log.w(a, "Requested camera does not exist: " + i);
            return null;
        } else {
            Log.i(a, "No camera facing back; returning camera #0");
            return Camera.open(0);
        }
    }

    public static Camera a() {
        return a(-1);
    }
}
