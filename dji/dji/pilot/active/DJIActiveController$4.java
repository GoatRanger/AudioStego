package dji.pilot.active;

import dji.log.DJILogHelper;
import dji.thirdparty.afinal.f.a;

class DJIActiveController$4 extends a<String> {
    final /* synthetic */ DJIActiveController a;

    DJIActiveController$4(DJIActiveController dJIActiveController) {
        this.a = dJIActiveController;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGD("DJIActiveController", "DJIMethod : onSuccess (1231)" + str, false, true);
    }

    public void a(Throwable th, int i, String str) {
        DJILogHelper.getInstance().LOGE("DJIActiveController", "DJIMethod : onFailure (1236)i:" + i + "s:" + str, true, true);
    }
}
