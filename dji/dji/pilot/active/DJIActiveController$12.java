package dji.pilot.active;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIActiveController$12 implements d {
    final /* synthetic */ DJIActiveController a;

    DJIActiveController$12(DJIActiveController dJIActiveController) {
        this.a = dJIActiveController;
    }

    public void onSuccess(Object obj) {
        DJIActiveController.a(this.a, DeviceType.BATTERY);
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive battery success", false, true);
    }

    public void onFailure(a aVar) {
        DJIActiveController.b(this.a, DeviceType.BATTERY);
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive battery fail " + aVar, false, true);
    }
}
