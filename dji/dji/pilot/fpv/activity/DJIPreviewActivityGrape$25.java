package dji.pilot.fpv.activity;

import dji.pilot.R;
import dji.pilot.publics.widget.h;

class DJIPreviewActivityGrape$25 implements Runnable {
    DJIPreviewActivityGrape$25() {
    }

    public void run() {
        if (DJIPreviewActivityGrape.g() == null) {
            DJIPreviewActivityGrape.a(new h(DJIPreviewActivityGrape.h()));
        }
        DJIPreviewActivityGrape.g().d(false);
        DJIPreviewActivityGrape.g().c(false);
        DJIPreviewActivityGrape.g().c((int) R.drawable.fpv_alert_icon_takeoff);
        DJIPreviewActivityGrape.g().d((int) R.string.fpv_rcsetting_calibrate_note);
        DJIPreviewActivityGrape.g().show();
    }
}
