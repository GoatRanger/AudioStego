package dji.pilot.publics.objects;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIGlobalService.3;

class DJIGlobalService$3$3 implements d {
    final /* synthetic */ 3 a;

    DJIGlobalService$3$3(3 3) {
        this.a = 3;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD("Test", "synchronize 1860 sucess", false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD("Test", "synchronize 1860 fail " + aVar, false, true);
    }
}
