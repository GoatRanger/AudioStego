package dji.pilot.fpv.activity;

import dji.midware.natives.FPVController;

class DJIBaseNewPreviewActivity$30 implements Runnable {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$30(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void run() {
        DJIBaseNewPreviewActivity.e(this.a).setText("FPS:" + DJIBaseNewPreviewActivity.d(this.a) + "\nQueueSize:" + FPVController.native_getQueueSize());
        DJIBaseNewPreviewActivity.a(this.a, 0);
    }
}
