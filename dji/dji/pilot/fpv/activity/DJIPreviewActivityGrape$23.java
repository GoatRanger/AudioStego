package dji.pilot.fpv.activity;

import dji.midware.natives.FPVController;

class DJIPreviewActivityGrape$23 implements Runnable {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$23(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void run() {
        DJIPreviewActivityGrape.e(this.a).setText("FPS:" + DJIPreviewActivityGrape.d(this.a) + "\nQueueSize:" + FPVController.native_getQueueSize());
        DJIPreviewActivityGrape.a(this.a, 0);
    }
}
