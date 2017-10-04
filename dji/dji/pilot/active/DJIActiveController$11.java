package dji.pilot.active;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.e.d;
import dji.pilot.active.c.a;
import dji.thirdparty.a.c;

class DJIActiveController$11 implements d {
    final /* synthetic */ DJIActiveController a;

    DJIActiveController$11(DJIActiveController dJIActiveController) {
        this.a = dJIActiveController;
    }

    public void onSuccess(Object obj) {
        DJIActiveController.a(this.a, DeviceType.BATTERY);
        c.a().e(a.TRUE);
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive battery success index: " + DJIActiveController.c(this.a).c(), false, true);
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
        DJIActiveController.b(this.a, DeviceType.BATTERY);
        c.a().e(a.TRUE);
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive battery fail " + aVar, false, true);
    }
}
