package dji.sdksharedlib.hardware.abstractions.c.d;

import dji.common.camera.CameraUtils.RecordVideoTimeoutLock;
import dji.common.error.DJICameraError;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.sdksharedlib.hardware.abstractions.b.e;

class j$32 extends Thread {
    final /* synthetic */ e a;
    final /* synthetic */ j b;

    j$32(j jVar, e eVar) {
        this.b = jVar;
        this.a = eVar;
    }

    public void run() {
        try {
            RecordVideoTimeoutLock.getInstance().reset();
            DataSpecialControl.getInstance().setRecordType(true).start(20);
            RecordVideoTimeoutLock.getInstance().waitResult();
            j.a(this.b);
            if (RecordVideoTimeoutLock.getInstance().getResult()) {
                j$c.getInstance().a();
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
