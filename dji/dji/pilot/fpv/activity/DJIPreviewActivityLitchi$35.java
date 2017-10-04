package dji.pilot.fpv.activity;

import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.pilot.R;
import dji.pilot.c.d;
import dji.pilot.fpv.control.b;
import dji.pilot.fpv.control.b$b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.thirdparty.a.c;

class DJIPreviewActivityLitchi$35 implements b$b {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$35(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void a() {
        DJIPreviewActivityLitchi.m(this.a);
        if (b.p) {
            DJIPreviewActivityLitchi.m(this.a);
            b.p = false;
            this.a.h();
            return;
        }
        DataCameraSetMode.getInstance().a(d.a).start(null);
        DJILogHelper.getInstance().LOGD("playback", "onGoPlayBackMode tochangemode " + d.a, false, true);
    }

    public void b() {
        DJIPreviewActivityLitchi.n(this.a);
    }

    public void c() {
        if (dji.pilot2.simulator.d.h()) {
            DJIErrorPopView.b bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.b;
            bVar.b = R.string.v2_smlt_not_support_camera_setting;
            c.a().e(bVar);
            return;
        }
        DJIPreviewActivityLitchi.o(this.a);
    }

    public void d() {
        DJIPreviewActivityLitchi.p(this.a).handleFnClick();
    }
}
