package dji.pilot.publics.objects;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIBaseActivity$9 implements d {
    final /* synthetic */ DJIBaseActivity a;

    DJIBaseActivity$9(DJIBaseActivity dJIBaseActivity) {
        this.a = dJIBaseActivity;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD("", "ControlCmd start success", false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD("", "ControlCmd start " + aVar, false, true);
    }
}
