package dji.pilot.fpv.activity;

import dji.pilot.fpv.topbar.DJIFpvTopBaseView.b;
import dji.pilot.groundStation.a.a;
import dji.thirdparty.a.c;

class DJIPreviewActivityLitchi$33 implements b {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$33(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void a() {
        if (dji.pilot.liveshare.b.getInstance().isRunning() || dji.pilot.liveshare.b.getInstance().isLaunch()) {
            this.a.i();
        } else {
            this.a.finishThis();
        }
    }

    public void a(int i, boolean z) {
        this.a.a(false);
        c.a().e(a.c.MainViewShowSettingDialog);
    }

    public void b(int i, boolean z) {
        if (DJIPreviewActivityLitchi.f(this.a) == null || !DJIPreviewActivityLitchi.f(this.a).isShown()) {
            this.a.b(false);
            c.a().e(a.c.MainViewHideSettingDialog);
            if (DJIPreviewActivityLitchi.g(this.a)) {
                DJIPreviewActivityLitchi.a(this.a, false);
                dji.pilot.flyforbid.a.getInstance(this.a).a(this.a);
            }
        }
    }
}
