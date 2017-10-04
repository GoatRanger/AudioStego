package dji.pilot.fpv.activity;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetGimbalControlMode;
import dji.midware.data.model.P3.DataRcSetGimbalControlMode.MODE;
import dji.midware.e.d;

class DJIPreviewActivityLitchi$26 implements d {
    final /* synthetic */ MODE a;
    final /* synthetic */ DJIPreviewActivityLitchi b;

    DJIPreviewActivityLitchi$26(DJIPreviewActivityLitchi dJIPreviewActivityLitchi, MODE mode) {
        this.b = dJIPreviewActivityLitchi;
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
