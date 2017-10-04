package dji.pilot.fpv.activity;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIPreviewActivityGrape$18 implements d {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$18(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "********************playback success", false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "********************playback" + aVar, false, true);
    }
}
