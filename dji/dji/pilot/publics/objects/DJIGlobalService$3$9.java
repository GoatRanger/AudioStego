package dji.pilot.publics.objects;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIGlobalService.3;

class DJIGlobalService$3$9 implements d {
    final /* synthetic */ 3 a;

    DJIGlobalService$3$9(3 3) {
        this.a = 3;
    }

    public void onSuccess(Object obj) {
        DJIGlobalService.h = DJIGlobalService.e(this.a.a).getSN();
        DJILogHelper.getInstance().LOGD(DJIGlobalService.b(this.a.a), "rcActiveStatus success " + DJIGlobalService.h, false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD(DJIGlobalService.b(this.a.a), "rcActiveStatus=" + aVar, false, true);
    }
}
