package dji.pilot.fpv.activity;

import java.util.TimerTask;

class DJIPreviewActivityLitchi$12 extends TimerTask {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$12(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void run() {
        DJIPreviewActivityLitchi.c(this.a).post(DJIPreviewActivityLitchi.b(this.a));
    }
}
