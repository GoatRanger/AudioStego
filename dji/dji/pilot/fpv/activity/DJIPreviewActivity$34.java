package dji.pilot.fpv.activity;

import dji.pilot.fpv.topbar.DJIFpvTopBaseView.b;
import dji.pilot.groundStation.a.a;
import dji.thirdparty.a.c;

class DJIPreviewActivity$34 implements b {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$34(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void a() {
        if (dji.pilot.liveshare.b.getInstance().isRunning() || dji.pilot.liveshare.b.getInstance().isLaunch()) {
            this.a.c();
        } else {
            this.a.finishThis();
        }
    }

    public void a(int i, boolean z) {
        DJIPreviewActivity.a(this.a, false);
        c.a().e(a.c.MainViewShowSettingDialog);
    }

    public void b(int i, boolean z) {
        if (DJIPreviewActivity.f(this.a) == null || !DJIPreviewActivity.f(this.a).isShown()) {
            DJIPreviewActivity.b(this.a, false);
        }
        c.a().e(a.c.MainViewHideSettingDialog);
        if (DJIPreviewActivity.g(this.a)) {
            DJIPreviewActivity.c(this.a, false);
            dji.pilot.flyforbid.a.getInstance(this.a).a(this.a);
        }
    }
}
