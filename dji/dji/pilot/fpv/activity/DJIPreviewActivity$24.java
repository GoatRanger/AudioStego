package dji.pilot.fpv.activity;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIPreviewActivity$24 implements d {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$24(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "********************playback success", false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "********************playback" + aVar, false, true);
    }
}
