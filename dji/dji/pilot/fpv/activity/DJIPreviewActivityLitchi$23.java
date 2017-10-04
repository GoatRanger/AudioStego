package dji.pilot.fpv.activity;

import dji.midware.natives.FPVController;

class DJIPreviewActivityLitchi$23 implements Runnable {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$23(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void run() {
        DJIPreviewActivityLitchi.e(this.a).setText("FPS:" + DJIPreviewActivityLitchi.d(this.a) + "\nQueueSize:" + FPVController.native_getQueueSize());
        DJIPreviewActivityLitchi.a(this.a, 0);
    }
}
