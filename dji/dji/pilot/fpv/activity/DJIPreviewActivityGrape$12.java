package dji.pilot.fpv.activity;

import java.util.TimerTask;

class DJIPreviewActivityGrape$12 extends TimerTask {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$12(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void run() {
        DJIPreviewActivityGrape.c(this.a).post(DJIPreviewActivityGrape.b(this.a));
    }
}
