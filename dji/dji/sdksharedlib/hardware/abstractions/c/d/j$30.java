package dji.sdksharedlib.hardware.abstractions.c.d;

import dji.common.camera.CameraUtils.ShootPhotoTimeoutLock;
import dji.common.camera.DJICameraSettingsDef.CameraShootPhotoMode;
import dji.common.error.DJICameraError;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.sdksharedlib.hardware.abstractions.b.e;

class j$30 extends Thread {
    final /* synthetic */ CameraShootPhotoMode a;
    final /* synthetic */ e b;
    final /* synthetic */ j c;

    j$30(j jVar, CameraShootPhotoMode cameraShootPhotoMode, e eVar) {
        this.c = jVar;
        this.a = cameraShootPhotoMode;
        this.b = eVar;
    }

    public void run() {
        try {
            ShootPhotoTimeoutLock.getInstance().reset();
            if (this.a == CameraShootPhotoMode.Single) {
                DataSpecialControl.getInstance().setPhotoType(TYPE.b).start(20);
            }
            if (this.a == CameraShootPhotoMode.Interval) {
                DataSpecialControl.getInstance().setPhotoType(TYPE.g, 255, DataCameraGetPushTauParam.getInstance().getPhotoInterval()).start(20);
            }
            ShootPhotoTimeoutLock.getInstance().waitResult();
            j.O();
            if (ShootPhotoTimeoutLock.getInstance().getResult()) {
                j$b.getInstance().a();
                if (this.b != null) {
                    this.b.a(null);
                }
            } else if (this.b != null) {
                this.b.a(DJICameraError.CAMERA_EXEC_TIMEOUT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
