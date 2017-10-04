package dji.pilot.fpv.activity;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIBaseNewPreviewActivity$18 implements d {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$18(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void onSuccess(Object obj) {
        DJIBaseNewPreviewActivity.b(this.a, false);
        DJILogHelper.getInstance().LOGD(this.a.TAG, "dm368SetParams success");
    }

    public void onFailure(a aVar) {
        DJIBaseNewPreviewActivity.b(this.a, false);
        DJILogHelper.getInstance().LOGD(this.a.TAG, "dm368SetParams " + aVar);
    }
}
