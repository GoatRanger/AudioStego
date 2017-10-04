package dji.pilot.fpv.activity;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIPreviewActivityGrape$17 implements d {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$17(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD("", "mode Success", false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD("", "slave mode fail", false, true);
    }
}
