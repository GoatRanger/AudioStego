package dji.pilot.fpv.activity;

import java.util.TimerTask;

class DJIBaseNewPreviewActivity$23 extends TimerTask {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$23(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void run() {
        DJIBaseNewPreviewActivity.c(this.a).post(DJIBaseNewPreviewActivity.b(this.a));
    }
}
