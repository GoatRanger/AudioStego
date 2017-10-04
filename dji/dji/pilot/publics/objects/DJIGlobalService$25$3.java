package dji.pilot.publics.objects;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIGlobalService.25;

class DJIGlobalService$25$3 implements d {
    final /* synthetic */ 25 a;

    DJIGlobalService$25$3(25 25) {
        this.a = 25;
    }

    public void onSuccess(Object obj) {
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD("", "同步RC时间 " + aVar, false, true);
    }
}
