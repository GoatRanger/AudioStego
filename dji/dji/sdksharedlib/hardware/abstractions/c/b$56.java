package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.camera.DJICameraSettingsDef.CameraVideoStandard;
import dji.common.error.DJICameraError;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$56 implements Runnable {
    final /* synthetic */ CameraVideoStandard a;
    final /* synthetic */ e b;
    final /* synthetic */ b c;

    b$56(b bVar, CameraVideoStandard cameraVideoStandard, e eVar) {
        this.c = bVar;
        this.a = cameraVideoStandard;
        this.b = eVar;
    }

    public void run() {
        if (this.a.value() == DataCameraGetPushShotParams.getInstance().getVideoStandard()) {
            if (this.b != null) {
                this.b.a(null);
            }
        } else if (this.b != null) {
            this.b.a(DJICameraError.CAMERA_PARAMETERS_SET_FAILED);
        }
    }
}
