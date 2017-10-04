package dji.pilot.fpv.activity;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIPreviewActivityGrape$21 implements d {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$21(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void onSuccess(Object obj) {
        DJIPreviewActivityGrape.E(this.a);
    }

    public void onFailure(a aVar) {
    }
}
