package dji.pilot.active;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot2.usercenter.activate.c;

class DJIActiveController$8 implements d {
    final /* synthetic */ DJIActiveController a;

    DJIActiveController$8(DJIActiveController dJIActiveController) {
        this.a = dJIActiveController;
    }

    public void onSuccess(Object obj) {
        DJIActiveController.a(this.a, DeviceType.FLYC);
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive Flyc success", false, true);
    }

    public void onFailure(a aVar) {
        DJIActiveController.b(this.a, DeviceType.FLYC);
        c.a("set active flyc fail code=" + aVar);
    }
}
