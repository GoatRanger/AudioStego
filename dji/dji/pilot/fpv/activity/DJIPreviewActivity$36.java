package dji.pilot.fpv.activity;

import dji.pilot.R;
import dji.pilot.publics.widget.h;

class DJIPreviewActivity$36 implements Runnable {
    DJIPreviewActivity$36() {
    }

    public void run() {
        if (DJIPreviewActivity.j() == null) {
            DJIPreviewActivity.a(new h(DJIPreviewActivity.k()));
        }
        DJIPreviewActivity.j().d(false);
        DJIPreviewActivity.j().c(false);
        DJIPreviewActivity.j().c((int) R.drawable.fpv_alert_icon_takeoff);
        DJIPreviewActivity.j().d((int) R.string.fpv_rcsetting_calibrate_note);
        DJIPreviewActivity.j().show();
    }
}
