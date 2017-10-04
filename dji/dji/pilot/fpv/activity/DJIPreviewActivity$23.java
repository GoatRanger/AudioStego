package dji.pilot.fpv.activity;

import dji.midware.natives.FPVController;

class DJIPreviewActivity$23 implements Runnable {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$23(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void run() {
        DJIPreviewActivity.e(this.a).setText("FPS:" + DJIPreviewActivity.d(this.a) + "\nQueueSize:" + FPVController.native_getQueueSize());
        DJIPreviewActivity.a(this.a, 0);
    }
}
