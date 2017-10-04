package dji.pilot.active;

import dji.log.DJILogHelper;
import dji.thirdparty.afinal.f.a;

class DJIActiveController$1 extends a<String> {
    final /* synthetic */ DJIActiveController a;

    DJIActiveController$1(DJIActiveController dJIActiveController) {
        this.a = dJIActiveController;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGD("DJIActiveController", "errorlog 上传成功" + str, false, true);
    }

    public void a(Throwable th, int i, String str) {
        DJILogHelper.getInstance().LOGD("DJIActiveController", "errorlog 上传失败", false, true);
    }
}
