package dji.pilot.fpv.activity;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIPreviewActivityLitchi$28 implements d {
    final /* synthetic */ float a;
    final /* synthetic */ float b;
    final /* synthetic */ DJIPreviewActivityLitchi c;

    DJIPreviewActivityLitchi$28(DJIPreviewActivityLitchi dJIPreviewActivityLitchi, float f, float f2) {
        this.c = dJIPreviewActivityLitchi;
        this.a = f;
        this.b = f2;
    }

    public void onSuccess(Object obj) {
        DJIPreviewActivityLitchi.a(this.c, this.a, this.b);
    }

    public void onFailure(a aVar) {
    }
}
