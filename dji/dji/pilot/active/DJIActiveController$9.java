package dji.pilot.active;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot2.usercenter.activate.c;

class DJIActiveController$9 implements d {
    final /* synthetic */ DJIActiveController a;

    DJIActiveController$9(DJIActiveController dJIActiveController) {
        this.a = dJIActiveController;
    }

    public void onSuccess(Object obj) {
        DJIActiveController.a(this.a, DeviceType.CAMERA);
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive camera success", false, true);
    }

    public void onFailure(a aVar) {
        DJIActiveController.b(this.a, DeviceType.CAMERA);
        c.a("setactive camera fail code=" + aVar);
    }
}
