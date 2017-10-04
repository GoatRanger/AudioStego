package dji.pilot.fpv.activity;

import dji.pilot.fpv.topbar.DJIFpvTopBaseView.b;
import dji.pilot.groundStation.a.a;
import dji.thirdparty.a.c;

class DJIPreviewActivityGrape$28 implements b {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$28(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void a() {
        this.a.finishThis();
    }

    public void a(int i, boolean z) {
        DJIPreviewActivityGrape.a(this.a, false);
        c.a().e(a.c.MainViewShowSettingDialog);
    }

    public void b(int i, boolean z) {
        if (DJIPreviewActivityGrape.f(this.a) == null || !DJIPreviewActivityGrape.f(this.a).isShown()) {
            DJIPreviewActivityGrape.b(this.a, false);
        }
        c.a().e(a.c.MainViewHideSettingDialog);
        if (DJIPreviewActivityGrape.g(this.a)) {
            DJIPreviewActivityGrape.c(this.a, false);
            dji.pilot.flyforbid.a.getInstance(this.a).a(this.a);
        }
    }
}
