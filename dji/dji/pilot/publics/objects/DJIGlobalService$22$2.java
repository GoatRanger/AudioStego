package dji.pilot.publics.objects;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIGlobalService.22;

class DJIGlobalService$22$2 implements d {
    final /* synthetic */ 22 a;

    DJIGlobalService$22$2(22 22) {
        this.a = 22;
    }

    public void onSuccess(Object obj) {
        DJIGlobalService.i = DJIGlobalService.c(this.a.a).getSN();
        DJILogHelper.getInstance().LOGD(DJIGlobalService.b(this.a.a), "batteryActiveStatus success " + DJIGlobalService.i, false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD(DJIGlobalService.b(this.a.a), "batteryActiveStatus=" + aVar, false, true);
    }
}
