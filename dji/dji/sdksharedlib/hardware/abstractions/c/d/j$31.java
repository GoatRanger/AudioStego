package dji.sdksharedlib.hardware.abstractions.c.d;

import dji.common.camera.CameraUtils.ShootPhotoTimeoutLock;
import dji.common.error.DJICameraError;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.sdksharedlib.hardware.abstractions.b.e;

class j$31 extends Thread {
    final /* synthetic */ e a;
    final /* synthetic */ j b;

    j$31(j jVar, e eVar) {
        this.b = jVar;
        this.a = eVar;
    }

    public void run() {
        try {
            ShootPhotoTimeoutLock.getInstance().reset();
            DataSpecialControl.getInstance().setPhotoType(TYPE.a).start(20);
            ShootPhotoTimeoutLock.getInstance().waitResult();
            j.O();
            if (ShootPhotoTimeoutLock.getInstance().getResult()) {
                j$b.getInstance().b();
                if (this.a != null) {
                    this.a.a(null);
                }
            } else if (this.a != null) {
                this.a.a(DJICameraError.CAMERA_EXEC_TIMEOUT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
