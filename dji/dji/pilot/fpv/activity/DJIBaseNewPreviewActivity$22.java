package dji.pilot.fpv.activity;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetGimbalControlMode;
import dji.midware.data.model.P3.DataRcSetGimbalControlMode.MODE;
import dji.midware.e.d;

class DJIBaseNewPreviewActivity$22 implements d {
    final /* synthetic */ MODE a;
    final /* synthetic */ DJIBaseNewPreviewActivity b;

    DJIBaseNewPreviewActivity$22(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity, MODE mode) {
        this.b = dJIBaseNewPreviewActivity;
        this.a = mode;
    }

    public void onSuccess(Object obj) {
        DataRcGetGimbalControlMode.getInstance().setMode(this.a);
        DJILogHelper.getInstance().LOGD(this.b.TAG, "DataRcSetGimbalControlMode success");
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD(this.b.TAG, "DataRcSetGimbalControlMode " + aVar);
    }
}
