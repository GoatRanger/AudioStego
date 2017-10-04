package dji.pilot.active;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIActiveController$2 implements d {
    final /* synthetic */ DJIActiveController a;

    DJIActiveController$2(DJIActiveController dJIActiveController) {
        this.a = dJIActiveController;
    }

    public void onSuccess(Object obj) {
        DJIActiveController.a(this.a, DeviceType.GIMBAL);
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive gimbal success", false, true);
    }

    public void onFailure(a aVar) {
        DJIActiveController.b(this.a, DeviceType.GIMBAL);
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive gimbal fail " + aVar, false, true);
    }
}
