package dji.pilot.fpv.activity;

import dji.common.error.DJIError;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.sdksharedlib.c.h;
import dji.thirdparty.a.c;

class DJIPreviewActivity$35 implements h {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$35(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void a() {
    }

    public void a(DJIError dJIError) {
        b bVar = new b();
        bVar.a = d.b;
        bVar.b = R.string.fpv_tap_zoom_fail;
        c.a().e(bVar);
        dji.pilot2.usercenter.activate.c.a("TapZoom Fail error=" + dJIError);
    }
}
