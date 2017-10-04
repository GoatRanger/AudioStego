package dji.pilot.publics.objects;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.flyforbid.a$c;
import dji.pilot.publics.objects.DJIGlobalService.3;
import dji.thirdparty.a.c;

class DJIGlobalService$3$8 implements d {
    final /* synthetic */ 3 a;

    DJIGlobalService$3$8(3 3) {
        this.a = 3;
    }

    public void onSuccess(Object obj) {
        DJIGlobalService.f = DJIGlobalService.d(this.a.a).getSN();
        DJIGlobalService.j = DJIGlobalService.d(this.a.a).getTime();
        c.a().e(a$c.SUCCESS);
        DJILogHelper.getInstance().LOGD(DJIGlobalService.b(this.a.a), "flycActiveStatus success " + DJIGlobalService.d(this.a.a).getSN(), false, true);
    }

    public void onFailure(a aVar) {
        c.a().e(a$c.FAIL);
        DJILogHelper.getInstance().LOGD(DJIGlobalService.b(this.a.a), "flycActiveStatus=" + aVar, false, true);
    }
}
