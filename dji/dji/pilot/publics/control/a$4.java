package dji.pilot.publics.control;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$4 implements d {
    final /* synthetic */ a a;

    a$4(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD("", "版本获取 1765 success", false, true);
        a.i(this.a);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD("", "版本获取 1765 " + aVar, false, true);
        a.i(this.a);
    }
}
