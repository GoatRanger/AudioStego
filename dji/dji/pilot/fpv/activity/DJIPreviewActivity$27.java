package dji.pilot.fpv.activity;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIPreviewActivity$27 implements d {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$27(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void onSuccess(Object obj) {
        DJIPreviewActivity.ah(this.a);
    }

    public void onFailure(a aVar) {
    }
}
