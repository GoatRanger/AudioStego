package dji.pilot.fpv.activity;

import dji.common.error.DJIError;
import dji.log.DJILogHelper;
import dji.sdksharedlib.c.h;

class DJIPreviewActivity$15 implements h {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$15(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void a() {
        DJILogHelper.getInstance().LOGE("tap_zoom", "tap_zoom success", "tap_zoom");
    }

    public void a(DJIError dJIError) {
        DJILogHelper.getInstance().LOGE("tap_zoom", "tap_zoom fail", "tap_zoom");
    }
}
