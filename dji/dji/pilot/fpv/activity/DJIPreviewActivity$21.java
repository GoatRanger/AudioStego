package dji.pilot.fpv.activity;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.thirdparty.a.c;

class DJIPreviewActivity$21 implements d {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$21(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void onSuccess(Object obj) {
    }

    public void onFailure(a aVar) {
        b bVar = new b();
        bVar.b = R.string.fpv_leftmenu_tip_gohome;
        bVar.d = dji.pilot.fpv.d.b.a(aVar);
        c.a().e(bVar);
    }
}
