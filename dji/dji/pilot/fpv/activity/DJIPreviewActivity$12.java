package dji.pilot.fpv.activity;

import java.util.TimerTask;

class DJIPreviewActivity$12 extends TimerTask {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$12(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void run() {
        DJIPreviewActivity.c(this.a).post(DJIPreviewActivity.b(this.a));
    }
}
