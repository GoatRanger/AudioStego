package dji.pilot2.flymonitor.service;

import dji.log.DJILogHelper;
import dji.thirdparty.afinal.f.a;

class FlyMonitorService$a$2 extends a<String> {
    final /* synthetic */ FlyMonitorService.a a;

    FlyMonitorService$a$2(FlyMonitorService.a aVar) {
        this.a = aVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGD(FlyMonitorService.a, "FLY_RECORD_ORDER response: " + str);
    }

    public void a(Throwable th, int i, String str) {
        DJILogHelper.getInstance().LOGE(FlyMonitorService.a, "FLY_RECORD_ORDER failed");
    }
}
