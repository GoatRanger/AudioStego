package dji.pilot.fpv.activity;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIPreviewActivityLitchi$22 implements d {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$22(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void onSuccess(Object obj) {
        DJIPreviewActivityLitchi.f(this.a, false);
        DJILogHelper.getInstance().LOGD(this.a.TAG, "dm368SetParams success");
    }

    public void onFailure(a aVar) {
        DJIPreviewActivityLitchi.f(this.a, false);
        DJILogHelper.getInstance().LOGD(this.a.TAG, "dm368SetParams " + aVar);
    }
}
