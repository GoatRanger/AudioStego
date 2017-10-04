package dji.pilot.publics.control;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$5 implements d {
    final /* synthetic */ a a;

    a$5(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "版本获取 368g success", false, true);
        this.a.r();
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "版本获取 368g " + aVar, false, true);
        this.a.r();
    }
}
