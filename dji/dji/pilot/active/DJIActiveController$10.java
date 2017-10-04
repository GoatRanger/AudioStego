package dji.pilot.active;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot2.usercenter.activate.c;

class DJIActiveController$10 implements d {
    final /* synthetic */ DJIActiveController a;

    DJIActiveController$10(DJIActiveController dJIActiveController) {
        this.a = dJIActiveController;
    }

    public void onSuccess(Object obj) {
        DJIActiveController.a(this.a, DeviceType.GLASS);
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive glass success", false, true);
    }

    public void onFailure(a aVar) {
        DJIActiveController.b(this.a, DeviceType.GLASS);
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive glass fail " + aVar, false, true);
        c.a("setactive glass fail code=" + aVar);
    }
}
