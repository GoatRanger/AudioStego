package dji.pilot.fpv.activity;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIBaseNewPreviewActivity$19 implements d {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$19(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "********************playback success", false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "********************playback" + aVar, false, true);
    }
}
