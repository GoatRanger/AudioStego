package dji.pilot.fpv.activity;

import dji.pilot.fpv.control.q.b;

class DJIPreviewActivityGrape$30 implements b {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$30(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void a(boolean z) {
        DJIPreviewActivityGrape.a(this.a).b(z);
        DJIPreviewActivityGrape.l(this.a).hideMenu(true);
        DJIPreviewActivityGrape.m(this.a).setSmallMap(z);
        DJIPreviewActivityGrape.m(this.a).changeBg(z);
    }
}
