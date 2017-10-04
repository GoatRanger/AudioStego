package dji.pilot.publics.objects;

import dji.log.DJILogHelper;
import dji.midware.e.d;
import dji.pilot.fpv.control.k.a;
import dji.pilot.publics.objects.DJIGlobalService.3;
import dji.thirdparty.a.c;

class DJIGlobalService$3$11 implements d {
    final /* synthetic */ 3 a;

    DJIGlobalService$3$11(3 3) {
        this.a = 3;
    }

    public void onSuccess(Object obj) {
        c.a().e(a.UPDATE);
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
        DJILogHelper.getInstance().LOGD("", "flyc GetParams radius " + aVar, false, true);
    }
}
