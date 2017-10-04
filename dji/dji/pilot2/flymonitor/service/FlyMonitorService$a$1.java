package dji.pilot2.flymonitor.service;

import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot2.flymonitor.model.response.FlyUploadConfigModel;
import dji.thirdparty.afinal.f.a;

class FlyMonitorService$a$1 extends a<String> {
    final /* synthetic */ FlyMonitorService.a a;

    FlyMonitorService$a$1(FlyMonitorService.a aVar) {
        this.a = aVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        if (str != null) {
            FlyUploadConfigModel flyUploadConfigModel = (FlyUploadConfigModel) h.b(str, FlyUploadConfigModel.class);
            if (flyUploadConfigModel != null) {
                FlyMonitorService.a(this.a.a, flyUploadConfigModel.should_active_monitor == 1);
                FlyMonitorService.b(this.a.a, true);
                FlyMonitorService.a(this.a.a, flyUploadConfigModel.upload_time_interval);
                DJILogHelper.getInstance().LOGI(FlyMonitorService.a, "Upload interval updated: " + FlyMonitorService.a(this.a.a));
            }
            DJILogHelper.getInstance().LOGD(FlyMonitorService.a, "FLY_RECORD_CONFIG response: " + str);
        }
    }

    public void a(Throwable th, int i, String str) {
        DJILogHelper.getInstance().LOGE(FlyMonitorService.a, "FLY_RECORD_CONFIG request failed");
    }
}
