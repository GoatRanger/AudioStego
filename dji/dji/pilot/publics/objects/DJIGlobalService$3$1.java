package dji.pilot.publics.objects;

import android.util.Log;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdGetPushConfig;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIGlobalService.3;

class DJIGlobalService$3$1 implements d {
    final /* synthetic */ 3 a;

    DJIGlobalService$3$1(3 3) {
        this.a = 3;
    }

    public void onSuccess(Object obj) {
        dji.pilot.c.d.d = DataOsdGetPushConfig.getInstance().getMcs();
        dji.pilot.c.d.c = DataOsdGetPushConfig.getInstance().getIsAuto() ? 1 : 0;
    }

    public void onFailure(a aVar) {
        Log.d("", "DataOsdGetConfig=" + aVar);
        DJILogHelper.getInstance().LOGD(DJIGlobalService.b(this.a.a), "DataOsdGetConfig=" + aVar, false, true);
    }
}
