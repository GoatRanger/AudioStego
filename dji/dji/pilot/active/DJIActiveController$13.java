package dji.pilot.active;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.fpv.d.b;

class DJIActiveController$13 implements d {
    final /* synthetic */ DJIActiveController a;

    DJIActiveController$13(DJIActiveController dJIActiveController) {
        this.a = dJIActiveController;
    }

    public void onSuccess(Object obj) {
        if (b.h(null)) {
            DJIActiveController.a(this.a, DeviceType.OFDM);
        } else {
            DJIActiveController.a(this.a, DeviceType.OSD);
        }
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive rc success", false, true);
    }

    public void onFailure(a aVar) {
        if (b.h(null)) {
            DJIActiveController.b(this.a, DeviceType.OFDM);
        } else {
            DJIActiveController.b(this.a, DeviceType.OSD);
        }
        DJILogHelper.getInstance().LOGD("DJIActiveController", "setactive rc fail " + aVar, false, true);
    }
}
