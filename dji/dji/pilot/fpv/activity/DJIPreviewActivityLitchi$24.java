package dji.pilot.fpv.activity;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIPreviewActivityLitchi$24 implements d {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$24(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "********************playback success", false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "********************playback" + aVar, false, true);
    }
}
